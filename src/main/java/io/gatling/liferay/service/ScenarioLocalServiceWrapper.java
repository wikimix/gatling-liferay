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
package io.gatling.liferay.service;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.service.ServiceWrapper;

import io.gatling.liferay.model.Scenario;

/**
 * Provides a wrapper for {@link ScenarioLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ScenarioLocalService
 * @generated
 */
public class ScenarioLocalServiceWrapper implements ScenarioLocalService,
    ServiceWrapper<ScenarioLocalService> {
    private ScenarioLocalService _scenarioLocalService;

    public ScenarioLocalServiceWrapper(
        ScenarioLocalService scenarioLocalService) {
        _scenarioLocalService = scenarioLocalService;
    }

    /**
    * Adds the scenario to the database. Also notifies the appropriate model listeners.
    *
    * @param scenario the scenario
    * @return the scenario that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public io.gatling.liferay.model.Scenario addScenario(
        io.gatling.liferay.model.Scenario scenario)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.addScenario(scenario);
    }

    /**
    * Creates a new scenario with the primary key. Does not add the scenario to the database.
    *
    * @param scenario_id the primary key for the new scenario
    * @return the new scenario
    */
    @Override
    public io.gatling.liferay.model.Scenario createScenario(long scenario_id) {
        return _scenarioLocalService.createScenario(scenario_id);
    }

    /**
    * Deletes the scenario with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param scenario_id the primary key of the scenario
    * @return the scenario that was removed
    * @throws PortalException if a scenario with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public io.gatling.liferay.model.Scenario deleteScenario(long scenario_id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.deleteScenario(scenario_id);
    }

    /**
    * Deletes the scenario from the database. Also notifies the appropriate model listeners.
    *
    * @param scenario the scenario
    * @return the scenario that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public io.gatling.liferay.model.Scenario deleteScenario(
        io.gatling.liferay.model.Scenario scenario)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.deleteScenario(scenario);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _scenarioLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link io.gatling.liferay.model.impl.ScenarioModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link io.gatling.liferay.model.impl.ScenarioModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public io.gatling.liferay.model.Scenario fetchScenario(long scenario_id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.fetchScenario(scenario_id);
    }

    /**
    * Returns the scenario with the primary key.
    *
    * @param scenario_id the primary key of the scenario
    * @return the scenario
    * @throws PortalException if a scenario with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public io.gatling.liferay.model.Scenario getScenario(long scenario_id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.getScenario(scenario_id);
    }

    @Override
    public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the scenarios.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link io.gatling.liferay.model.impl.ScenarioModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of scenarios
    * @param end the upper bound of the range of scenarios (not inclusive)
    * @return the range of scenarios
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<io.gatling.liferay.model.Scenario> getScenarios(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.getScenarios(start, end);
    }

    /**
    * Returns the number of scenarios.
    *
    * @return the number of scenarios
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getScenariosCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.getScenariosCount();
    }

    /**
    * Updates the scenario in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param scenario the scenario
    * @return the scenario that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public io.gatling.liferay.model.Scenario updateScenario(
        io.gatling.liferay.model.Scenario scenario)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.updateScenario(scenario);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _scenarioLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _scenarioLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _scenarioLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
    * Creates the empty default scenario, persists it and returns it.
    *
    * @param simulation The simulation that contains the new scenario
    * @param themeDisplay
    * @return The fresh default scenario
    * @throws SystemException If an error occures in services
    */
    @Override
    public io.gatling.liferay.model.Scenario createDefaultScenario(
        io.gatling.liferay.model.Simulation simulation)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.createDefaultScenario(simulation);
    }

    @Override
    public io.gatling.liferay.model.Scenario createScenario(
        java.lang.String name, long simulationId, java.lang.String injection,
        long numberOfUsers, long duration)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.createScenario(name, simulationId,
            injection, numberOfUsers, duration);
    }

    @Override
    public io.gatling.liferay.model.Scenario addScenario(
        java.lang.String name, long simulationId)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchScenarioException {
        return _scenarioLocalService.addScenario(name, simulationId);
    }

    @Override
    public void addProcess(long scenarioId, long processId, int order, int pause)
        throws com.liferay.portal.kernel.exception.SystemException {
        _scenarioLocalService.addProcess(scenarioId, processId, order, pause);
    }

    @Override
    public int countBySimulationId(long simulationId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.countBySimulationId(simulationId);
    }

    @Override
    public java.util.List<io.gatling.liferay.model.Scenario> findBySimulationId(
        long simulationId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.findBySimulationId(simulationId);
    }

    /**
    * Check if name is unique for {@link Scenario}
    */
    @Override
    public boolean isNameUnique(java.lang.String name, long idSimulation)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.isNameUnique(name, idSimulation);
    }

    /**
    * Count how many {@link Scenario} have this variableName
    */
    @Override
    public int countByVariableName(java.lang.String variableName,
        long idSimulation)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.countByVariableName(variableName,
            idSimulation);
    }

    /**
    * get {@link Scenario} have this variableName
    */
    @Override
    public java.util.List<io.gatling.liferay.model.Scenario> findByVariableName(
        java.lang.String variableName, long idSimulation)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _scenarioLocalService.findByVariableName(variableName,
            idSimulation);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ScenarioLocalService getWrappedScenarioLocalService() {
        return _scenarioLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedScenarioLocalService(
        ScenarioLocalService scenarioLocalService) {
        _scenarioLocalService = scenarioLocalService;
    }

    @Override
    public ScenarioLocalService getWrappedService() {
        return _scenarioLocalService;
    }

    @Override
    public void setWrappedService(ScenarioLocalService scenarioLocalService) {
        _scenarioLocalService = scenarioLocalService;
    }

	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel) throws PortalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		// TODO Auto-generated method stub
		return null;
	}
}
