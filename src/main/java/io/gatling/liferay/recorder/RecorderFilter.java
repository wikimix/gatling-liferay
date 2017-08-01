/**
 * Copyright 2011-2016 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
  * Copyright 2016 Gatling Corp (www.gatling.io)
 */
package io.gatling.liferay.recorder;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import io.gatling.liferay.model.Process;
import io.gatling.liferay.model.Record;
import io.gatling.liferay.recorder.records.GetURL;
import io.gatling.liferay.recorder.records.PostMultipartURL;
import io.gatling.liferay.recorder.records.PostURL;
import io.gatling.liferay.recorder.records.RecordURL;
import io.gatling.liferay.service.ProcessLocalServiceUtil;
import io.gatling.liferay.service.RecordLocalServiceUtil;



/**
 * Servlet Filter implementation class RecordFilter
 * 
 * The filter code migth be a little bit messy, two different recorder used to cohexist, 
 * requiring the use of conditionnal instructions to distinguish one from another.
 * It has not been cleaned properly and is more likely to contain old instructions no longer used.
 * 
 *  The architecture is made up in order to work for both use case, which means that despite the design pattern usages, some
 *  cumbersome codes remain.
 */

@MultipartConfig
public class RecorderFilter implements Filter {
	private static final Log LOG = LogFactoryUtil.getLog(RecorderFilter.class);
	private static final String NAMESPACE = "_gatling_WAR_gatlingliferayportlet_";
	private static final String URL_CONTROL_PANEL = "/group/control_panel/manage";
	private static final List<String> FORBIDDEN_PARAMS = new ArrayList<String>();

	//When we are not focused on a portlet but we are on a page, we put the id to -1
	private static final String INEXISTANT_PORTLET_ID = "_default_";
	private static final String CURRENT_VERSION = "6.2";
	
	private static final int INFO_PORTLET_ID = 0;
	private static final int INFO_RECORD_STATE = 1;
	private static final int INFO_RECORD_NAME = 2;
	
	static {
		FORBIDDEN_PARAMS.add("doAsGroupId");
	}

	/**
	 * Default constructor. 
	 */
	public RecorderFilter() {
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}
	
	/**
	 * Gatling's recorder filter. It records all the url visited from the <i>Portlet Config</i> in the plugin <b>Gatling Liferay</b>.
	 * 
	 * It uses session to store all the visited URLs and next saves it in DB.
	 */
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		LOG.debug("doFilterCalled");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		
		// ToogleRecord and prepare session with the request attributes
		toogleRecord(httpRequest, session);
		
		String infosRecorder = SessionParamUtil.getString(httpRequest, "GATLING_RECORD_STATE", null);
		if(infosRecorder != null) {
			String[] infos = infosRecorder.split(",");
			
			List<RecordURL> currentRecords = (List<RecordURL>) session.getAttribute("recordURL");
			if(currentRecords == null) { // if empty session recordURL create one
				currentRecords = new ArrayList<RecordURL>();
			}
			
			if (infos[INFO_RECORD_STATE].equalsIgnoreCase("RECORD")) { 
				saveURL(httpRequest, response, session, currentRecords);	
			} else if(infos[INFO_RECORD_STATE].equalsIgnoreCase("STOP")) {
				request.setAttribute("recordName", infos[INFO_RECORD_NAME]);
				stopRecording(session, infos, currentRecords);
			}
		}
		chain.doFilter(request, response);
	}
	
	
	/**
	 * Toogle the record mode if the portlet action is valid.
	 * @param httpRequest The HTTP request
	 * @param session The current session
	 */
	private static void toogleRecord(HttpServletRequest httpRequest, HttpSession session){
		String actionToggleRecord = ParamUtil.getString(httpRequest, NAMESPACE+"javax.portlet.action",null);
		
		if((actionToggleRecord != null && actionToggleRecord.equals("toggleRecord"))) {
			long start = System.nanoTime();
			session.setAttribute("GATLING_PAUSE_TIME", start);
			
			String recordState = ParamUtil.getString(httpRequest, NAMESPACE+"nextRecordState", null);
			String recordName = ParamUtil.getString(httpRequest, NAMESPACE+"useCaseRecordName", null);
			String portletId = ParamUtil.getString(httpRequest, NAMESPACE+"pagePortletId", null);
			LOG.debug("recordState: "+recordState+",\trecordeName: "+recordName+"\tportletId:"+portletId);
			if(recordState != null && recordName != null && portletId != null) {
				// Case where the record is focused on a single portlet
				session.setAttribute("GATLING_RECORD_STATE", portletId+","+recordState+","+recordName);
			}
			else if (recordState != null && recordName != null ) {
				// Case where the record is for the full portal
				session.setAttribute("GATLING_RECORD_STATE", INEXISTANT_PORTLET_ID +","+recordState+","+recordName);
			}
			else {
				session.removeAttribute("GATLING_RECORD_STATE");
			}
		}
	}
	
	
	
	/**
	 * Save the URL of the request in the currentRecords and update the recordURL session attribute.
	 * If a form is invalid, the function stores an error in a cookie.
	 * @param request The HTTP request
	 * @param response The HTTP Response
	 * @param session The current session
	 * @param currentRecords The current recorded urls
	 * @throws IOException If a buffering action failed
	 */
	private static void saveURL(HttpServletRequest request, ServletResponse response, HttpSession session, List<RecordURL> currentRecords) throws IOException{
		
		//Handle timer between two requests
		long start = (long) session.getAttribute("GATLING_PAUSE_TIME");
		long now = System.nanoTime();
		session.setAttribute("GATLING_PAUSE_TIME", now);
		int pauseTime = (int) Math.floor((now - start) / Math.pow(10, 9));
		
		Map<String, String[]> parametersMap = request.getParameterMap();

		String params = HttpUtil.parameterMapToString(filterParameters(parametersMap));
		
		//TODO: In future, the base URL should be customizable
		String requestURL = request.getRequestURI().replace(URL_CONTROL_PANEL, "");
		
		LOG.info("requestURL: " + requestURL);
		
		RecordURL record = null;
		if(request.getMethod().equalsIgnoreCase("post")) {	
			if (request.getContentType() != null && request.getContentType().toLowerCase().contains("multipart/form-data")) {
				//Multipart Form case
				record = computeDataFromMultiParts(request, requestURL, params);
			}
			else {
				//Normal Form case
				record = computeParamsFromNormalForm(request, requestURL, params);
			}
		}
		else {
			//Get Case
			record = new GetURL(requestURL, params);
		} 
		record.setPauseTime(pauseTime);
		LOG.debug(record);
		currentRecords.add(record);
		session.setAttribute("recordURL", currentRecords);
	}
	
	
	/**
	 * Stop the recording by persisting the record and all the URLs.
	 * @param session The current session
	 * @param infos The record infos
	 * @param currentRecords The current recorded urls
	 */
	private static void stopRecording(HttpSession session, String[] infos, List<RecordURL> currentRecords){
		// Remove from session
		session.removeAttribute("recordURL");
		// Check if we have something to record
		if(!currentRecords.isEmpty()) {
			try {
				
				Record record;
				
				if(infos[INFO_PORTLET_ID].equals(INEXISTANT_PORTLET_ID)){
					LOG.debug("Saving Smoothy record...");
					currentRecords.remove(0);
					
					//If the default record already exists, erase all his instances in BDD
					//List<Record> records = RecordLocalServiceUtil.findByPortletId(INEXISTANT_PORTLET_ID);
					/*for (Record defaultRecord : records) {
						//RecordLocalServiceUtil.deleteRecord(defaultRecord);
					}*/
					record = RecordLocalServiceUtil.save(infos[INFO_RECORD_NAME], INEXISTANT_PORTLET_ID, CURRENT_VERSION);
					Process process = ProcessLocalServiceUtil.createProcess(CounterLocalServiceUtil.increment(Process.class.getName()));
					process.setName("Record_"+record.getName());
					process.setType("RECORD");
					process.setFeederId(record.getPrimaryKey());
					process.persist();
				}
				
				/* Advanced case */
				else {
					String portletVersion = PortletLocalServiceUtil.getPortletById(infos[INFO_PORTLET_ID]).getPluginPackage().getVersion();
					record = RecordLocalServiceUtil.save(infos[INFO_RECORD_NAME], infos[INFO_PORTLET_ID], portletVersion);
				}
				
				LOG.debug("Record saved !");
				for (int i = 1; i < currentRecords.size(); i++) {
					currentRecords.get(i).saveURL(i, record.getRecordId(), currentRecords.get(i).getPauseTime());
				}
				LOG.debug("Urls saved !");

			} catch (SystemException e) {
				LOG.error("Error saving use case ...\n"+e.getMessage());
			}
		}
	}
	
	/**
	 * Computes the record url that will hold all the multiparts form parameters.
	 * @param request The current HTTP request
	 * @return the current url as a post url with multiparts data
	 * @throws IOException If an error occurs while extracting parts content
	 */
	private static RecordURL computeDataFromMultiParts(HttpServletRequest request, String requestURL, String params) throws IOException{
		StringBuffer content = new StringBuffer();
		String line = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while( (line = reader.readLine()) != null){
			content.append(line).append("\n");
		}
		return new PostMultipartURL(requestURL, params, content.toString());
	}

	
	/**
	 * Computes the record url that will hold all the form parameters 
	 * @param request The current HTTP request
	 * @return the current url as a normal post url with his parameters
	 */
	private static RecordURL computeParamsFromNormalForm(HttpServletRequest request, String RequestURL, String params){
		String queryString = request.getQueryString();
		LOG.debug("QueryString: "+queryString);

		
		// Create a Map of params from queryString, those are unwanted params filtered later
		Map<String, String> unwantedParam = new HashMap<>();
		try {
			String result = URLDecoder.decode(queryString, "UTF-8");
			String[] paramsURL = result.split("&");
			for(String param : paramsURL) {
				int indexEqual = param.indexOf("=");
				String key = param.substring(0, indexEqual);
				String value = param.substring(indexEqual, param.length());
				unwantedParam.put(key, value);
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// allParams contains both form param and url params
		Map<String, String[]> allParams = request.getParameterMap();
		
		// mapResult turns out to be the substraction of allParams and unwantedParam, containing only formParam
		Map<String, String> mapResult = new HashMap<>();
		for (String key : allParams.keySet()) {	
			if(!unwantedParam.containsKey(key)) {
				mapResult.put(key, allParams.get(key)[0]);
			}
		}
		
		return new PostURL(RequestURL, "?"+queryString, mapResult);
	}
	
	
	
	/**
	 * Remove forbidden parameters from URL. We return a cleaner URL for Gatling Scripts
	 * @param parameters
	 * @return
	 */
	private static Map<String,String[]> filterParameters(Map<String,String[]> parameters) {
		Map<String,String[]> params = new HashMap<String, String[]>(parameters);
		for (String key : FORBIDDEN_PARAMS) {
			params.remove(key);
		}
		return params;
	}
	
}
