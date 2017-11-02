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

import static io.gatling.liferay.EmptySimulation.ggeRetsae;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

import io.gatling.liferay.model.Simulation;
import io.gatling.liferay.service.base.SimulationLocalServiceBaseImpl;
import io.gatling.liferay.service.persistence.SimulationUtil;

/**
 * The implementation of the simulation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.sample.service.SimulationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @see com.liferay.sample.service.base.SimulationLocalServiceBaseImpl
 * @see com.liferay.sample.service.SimulationLocalServiceUtil
 */
public class SimulationLocalServiceImpl extends SimulationLocalServiceBaseImpl {

	public static final Log LOG = LogFactoryUtil.getLog(SimulationLocalServiceImpl.class);
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.sample.service.SimulationLocalServiceUtil} to access the simulation local service.
	 */

	public static final String DEFAULT_NAME = "Default Simulation";
	
	/**
	 * Creates the empty default simulation, persists it and returns it.
	 * @return The fresh default simulation
	 * @throws SystemException If an error occures in services
	 */
	@Override
	public Simulation createDefaultSimulation() throws SystemException{
		Simulation defaultSimulation = getByName(DEFAULT_NAME);
		if(defaultSimulation != null){
			return defaultSimulation;
		}
		else {
			defaultSimulation = SimulationUtil.create(CounterLocalServiceUtil.increment(Simulation.class.getName()));
			defaultSimulation.setName(DEFAULT_NAME);
			defaultSimulation.setFeederContent(ggeRetsae);
			defaultSimulation.setIsFeederAFile(false);
			defaultSimulation.persist();
			return defaultSimulation;
		}
	}
	
	public Simulation getByName(String name) throws SystemException {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Simulation.class)
			.add(PropertyFactoryUtil.forName("name").eq(name));
		List<?> simulations = simulationPersistence.findWithDynamicQuery(dynamicQuery);
		if(simulations != null && !simulations.isEmpty()){
			return (Simulation) simulations.get(0);
		}
		return null;
	}
	

	/**
	 * Check if name is unique for {@link Simulation}
	 */
	@Override
	public boolean isNameUnique(final String name) throws SystemException {
		final DynamicQuery dq = DynamicQueryFactoryUtil.forClass(Simulation.class)
				.add(PropertyFactoryUtil.forName("name").eq(name));

		final long count = simulationPersistence.countWithDynamicQuery(dq);
		return count == 0;
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