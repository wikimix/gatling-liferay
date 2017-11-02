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
package io.gatling.liferay.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

import io.gatling.liferay.NoSuchProcessException;
import io.gatling.liferay.NoSuchRecordException;
import io.gatling.liferay.NoSuchSiteMapException;
import io.gatling.liferay.NoSuchUrlSiteMapException;
import io.gatling.liferay.model.Process;
import io.gatling.liferay.model.ProcessType;
import io.gatling.liferay.model.SiteMap;
import io.gatling.liferay.model.UrlSiteMap;
import io.gatling.liferay.service.UrlSiteMapLocalServiceUtil;
import io.gatling.liferay.service.base.SiteMapLocalServiceBaseImpl;
import io.gatling.liferay.service.persistence.SiteMapUtil;
import io.gatling.liferay.util.LiferayUtil;

/**
 * The implementation of the site map local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link io.gatling.liferay.service.SiteMapLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see io.gatling.liferay.service.base.SiteMapLocalServiceBaseImpl
 * @see io.gatling.liferay.service.SiteMapLocalServiceUtil
 */
public class SiteMapLocalServiceImpl extends SiteMapLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link io.gatling.liferay.service.SiteMapLocalServiceUtil} to access the site map local service.
     */
	
	public static final String DEFAULT_NAME = "_default_sitemap_";
	
	@Override
	public SiteMap siteMapCreation(ThemeDisplay themeDisplay, String portalUrl) throws SystemException {
		//Remove existing siteMap
		try {
			SiteMap s = siteMapPersistence.findByName(DEFAULT_NAME);
			List<UrlSiteMap> urls = UrlSiteMapLocalServiceUtil.findBySiteMapId(s.getSiteMapId());
			for (UrlSiteMap urlSiteMap : urls) {
				urlSiteMapPersistence.remove(urlSiteMap.getUrlSiteMapId());
			}
			siteMapPersistence.remove(s.getSiteMapId());
			
		} catch (NoSuchSiteMapException | NoSuchUrlSiteMapException e) {}
		
		SiteMap siteMap = createSiteMap(DEFAULT_NAME);
		
		List<Group> listGroups = LiferayUtil.getListOfSites();
		for (Group group : listGroups) {
			for (Layout layout : LiferayUtil.getSiteMap(group.getGroupId())) {
		    	long siteMapId = siteMap.getSiteMapId();
		    	String friendlyUrl =  layout.getFriendlyURL().substring(1);
		    	String urlGroup = group.getName();
		    	String url = LiferayUtil.getGroupFriendlyURL(themeDisplay, layout).replaceAll(portalUrl, "");
		    	UrlSiteMapLocalServiceUtil.createUrlSiteMap(siteMapId, friendlyUrl, urlGroup, url, 1);
		    }
		}
	    
	    return siteMap;
    }
	
	@Override
	public SiteMap createSiteMap(String name) throws SystemException{
		 SiteMap siteMap = SiteMapUtil.create(CounterLocalServiceUtil.increment(SiteMap.class.getName()));
		 siteMap.setName(name);
		 siteMap.persist();
		 return siteMap;
	}
	
	@Override
	public SiteMap findByProcessId(long processId) throws SystemException, NoSuchModelException, NoSuchProcessException {
		Process process = processPersistence.findByPrimaryKey(processId);
		Long feederId = process.getFeederId();
		if(ProcessType.valueOf(process.getType()) == ProcessType.RANDOMPAGE && feederId != null){
			return siteMapPersistence.findByPrimaryKey(feederId);
		}
		return null;
	}
	
	@Override
	public SiteMap findByName(String name) throws NoSuchRecordException, SystemException, NoSuchSiteMapException {
		return siteMapPersistence.findByName(name);
	}

	@Override
	public List dynamicQuery(DynamicQuery dynamicQuery) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end, OrderByComparator orderByComparator)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBeanIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		// TODO Auto-generated method stub
		
	}
	
}
