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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.ArrayList;
import java.util.List;

import io.gatling.liferay.NoSuchProcessException;
import io.gatling.liferay.NoSuchProcessScenarioLinkException;
import io.gatling.liferay.model.Process;
import io.gatling.liferay.model.ProcessScenarioLink;
import io.gatling.liferay.model.ProcessType;
import io.gatling.liferay.service.ProcessLocalServiceUtil;
import io.gatling.liferay.service.base.ProcessLocalServiceBaseImpl;
import io.gatling.liferay.service.persistence.ProcessUtil;

/**
 * The implementation of the process local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link io.gatling.liferay.service.ProcessLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see io.gatling.liferay.service.base.ProcessLocalServiceBaseImpl
 * @see io.gatling.liferay.service.ProcessLocalServiceUtil
 */
public class ProcessLocalServiceImpl extends ProcessLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link io.gatling.liferay.service.ProcessLocalServiceUtil} to access the process local service.
     */
	
	@Override
	public List<Process> findProcessFromScenarioId(long scenarioId) throws SystemException, PortalException{
		List<Process> processes = new ArrayList<>();
		List<ProcessScenarioLink> links = processScenarioLinkPersistence.findByscenarioId(scenarioId);
		for (ProcessScenarioLink link : links) {
			processes.add(ProcessLocalServiceUtil.getProcess(link.getProcess_id()));
		}
		return processes;
	}
	
	@Override
	public int findPause(long scenarioId, long processesId, int order) throws NoSuchProcessScenarioLinkException, SystemException{
		ProcessScenarioLink link = processScenarioLinkPersistence.findByPause(processesId, scenarioId, order);
		return link.getPause();
	}
	
	@Override
	public Process createProcess(String name, ProcessType type, Long feederId) throws SystemException{
		Process process = ProcessUtil.create(CounterLocalServiceUtil.increment(Process.class.getName()));
		process.setName(name);
		process.setType(type.name());
		process.setFeederId(feederId);
		process.persist();
		return process;
	}
	
	@Override
	public Process findByName(String name) throws NoSuchProcessException, SystemException{
		return processPersistence.findByName(name);
	}
	
	@Override
	public Process updateProcess(long id, String name, ProcessType type, Long feederId) throws NoSuchProcessException, SystemException{
		Process process = processPersistence.findByPrimaryKey(id);
		process.setName(name);
		process.setType(type.name());
		process.setFeederId(feederId);
		process.persist();
		return process;
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
