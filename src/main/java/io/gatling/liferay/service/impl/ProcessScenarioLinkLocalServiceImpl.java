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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

import io.gatling.liferay.model.ProcessScenarioLink;
import io.gatling.liferay.service.base.ProcessScenarioLinkLocalServiceBaseImpl;
import io.gatling.liferay.service.persistence.ProcessScenarioLinkUtil;

/**
 * The implementation of the process scenario link local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link io.gatling.liferay.service.ProcessScenarioLinkLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see io.gatling.liferay.service.base.ProcessScenarioLinkLocalServiceBaseImpl
 * @see io.gatling.liferay.service.ProcessScenarioLinkLocalServiceUtil
 */
public class ProcessScenarioLinkLocalServiceImpl
    extends ProcessScenarioLinkLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link io.gatling.liferay.service.ProcessScenarioLinkLocalServiceUtil} to access the process scenario link local service.
     */	
	
	@Override
	public ProcessScenarioLink createLink(long scenarioId, long processId, int order, int pause) throws SystemException{
		ProcessScenarioLink link = ProcessScenarioLinkUtil.create(CounterLocalServiceUtil.increment(ProcessScenarioLink.class.getName()));
		link.setScenario_id(scenarioId);
		link.setProcess_id(processId);
		link.setOrder(order);
		link.setPause(pause);
		link.persist();
		return link;
	}
	
	public List<ProcessScenarioLink> findByscenarioId(long scenarioId) throws SystemException {
    	return processScenarioLinkPersistence.findByscenarioId(scenarioId);
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
