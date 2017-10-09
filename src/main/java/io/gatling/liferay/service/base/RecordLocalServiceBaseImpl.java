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
package io.gatling.liferay.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import io.gatling.liferay.model.Record;
import io.gatling.liferay.service.RecordLocalService;
import io.gatling.liferay.service.persistence.FormParamPersistence;
import io.gatling.liferay.service.persistence.LoginPersistence;
import io.gatling.liferay.service.persistence.ProcessPersistence;
import io.gatling.liferay.service.persistence.ProcessScenarioLinkPersistence;
import io.gatling.liferay.service.persistence.RecordPersistence;
import io.gatling.liferay.service.persistence.ScenarioPersistence;
import io.gatling.liferay.service.persistence.SimulationPersistence;
import io.gatling.liferay.service.persistence.SiteMapPersistence;
import io.gatling.liferay.service.persistence.UrlRecordPersistence;
import io.gatling.liferay.service.persistence.UrlSiteMapPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the record local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link io.gatling.liferay.service.impl.RecordLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see io.gatling.liferay.service.impl.RecordLocalServiceImpl
 * @see io.gatling.liferay.service.RecordLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class RecordLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements RecordLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link io.gatling.liferay.service.RecordLocalServiceUtil} to access the record local service.
	 */

	/**
	 * Adds the record to the database. Also notifies the appropriate model listeners.
	 *
	 * @param record the record
	 * @return the record that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Record addRecord(Record record) {
		record.setNew(true);

		return recordPersistence.update(record);
	}

	/**
	 * Creates a new record with the primary key. Does not add the record to the database.
	 *
	 * @param recordId the primary key for the new record
	 * @return the new record
	 */
	@Override
	public Record createRecord(long recordId) {
		return recordPersistence.create(recordId);
	}

	/**
	 * Deletes the record with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param recordId the primary key of the record
	 * @return the record that was removed
	 * @throws PortalException if a record with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Record deleteRecord(long recordId) throws PortalException {
		return recordPersistence.remove(recordId);
	}

	/**
	 * Deletes the record from the database. Also notifies the appropriate model listeners.
	 *
	 * @param record the record
	 * @return the record that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Record deleteRecord(Record record) {
		return recordPersistence.remove(record);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Record.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return recordPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link io.gatling.liferay.model.impl.RecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return recordPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link io.gatling.liferay.model.impl.RecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return recordPersistence.findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return recordPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return recordPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Record fetchRecord(long recordId) {
		return recordPersistence.fetchByPrimaryKey(recordId);
	}

	/**
	 * Returns the record with the primary key.
	 *
	 * @param recordId the primary key of the record
	 * @return the record
	 * @throws PortalException if a record with the primary key could not be found
	 */
	@Override
	public Record getRecord(long recordId) throws PortalException {
		return recordPersistence.findByPrimaryKey(recordId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(recordLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Record.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("recordId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(recordLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Record.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("recordId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(recordLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Record.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("recordId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return recordLocalService.deleteRecord((Record)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return recordPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link io.gatling.liferay.model.impl.RecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of records
	 * @param end the upper bound of the range of records (not inclusive)
	 * @return the range of records
	 */
	@Override
	public List<Record> getRecords(int start, int end) {
		return recordPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of records.
	 *
	 * @return the number of records
	 */
	@Override
	public int getRecordsCount() {
		return recordPersistence.countAll();
	}

	/**
	 * Updates the record in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param record the record
	 * @return the record that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Record updateRecord(Record record) {
		return recordPersistence.update(record);
	}

	/**
	 * Returns the form param local service.
	 *
	 * @return the form param local service
	 */
	public io.gatling.liferay.service.FormParamLocalService getFormParamLocalService() {
		return formParamLocalService;
	}

	/**
	 * Sets the form param local service.
	 *
	 * @param formParamLocalService the form param local service
	 */
	public void setFormParamLocalService(
		io.gatling.liferay.service.FormParamLocalService formParamLocalService) {
		this.formParamLocalService = formParamLocalService;
	}

	/**
	 * Returns the form param persistence.
	 *
	 * @return the form param persistence
	 */
	public FormParamPersistence getFormParamPersistence() {
		return formParamPersistence;
	}

	/**
	 * Sets the form param persistence.
	 *
	 * @param formParamPersistence the form param persistence
	 */
	public void setFormParamPersistence(
		FormParamPersistence formParamPersistence) {
		this.formParamPersistence = formParamPersistence;
	}

	/**
	 * Returns the login local service.
	 *
	 * @return the login local service
	 */
	public io.gatling.liferay.service.LoginLocalService getLoginLocalService() {
		return loginLocalService;
	}

	/**
	 * Sets the login local service.
	 *
	 * @param loginLocalService the login local service
	 */
	public void setLoginLocalService(
		io.gatling.liferay.service.LoginLocalService loginLocalService) {
		this.loginLocalService = loginLocalService;
	}

	/**
	 * Returns the login persistence.
	 *
	 * @return the login persistence
	 */
	public LoginPersistence getLoginPersistence() {
		return loginPersistence;
	}

	/**
	 * Sets the login persistence.
	 *
	 * @param loginPersistence the login persistence
	 */
	public void setLoginPersistence(LoginPersistence loginPersistence) {
		this.loginPersistence = loginPersistence;
	}

	/**
	 * Returns the process local service.
	 *
	 * @return the process local service
	 */
	public io.gatling.liferay.service.ProcessLocalService getProcessLocalService() {
		return processLocalService;
	}

	/**
	 * Sets the process local service.
	 *
	 * @param processLocalService the process local service
	 */
	public void setProcessLocalService(
		io.gatling.liferay.service.ProcessLocalService processLocalService) {
		this.processLocalService = processLocalService;
	}

	/**
	 * Returns the process persistence.
	 *
	 * @return the process persistence
	 */
	public ProcessPersistence getProcessPersistence() {
		return processPersistence;
	}

	/**
	 * Sets the process persistence.
	 *
	 * @param processPersistence the process persistence
	 */
	public void setProcessPersistence(ProcessPersistence processPersistence) {
		this.processPersistence = processPersistence;
	}

	/**
	 * Returns the process scenario link local service.
	 *
	 * @return the process scenario link local service
	 */
	public io.gatling.liferay.service.ProcessScenarioLinkLocalService getProcessScenarioLinkLocalService() {
		return processScenarioLinkLocalService;
	}

	/**
	 * Sets the process scenario link local service.
	 *
	 * @param processScenarioLinkLocalService the process scenario link local service
	 */
	public void setProcessScenarioLinkLocalService(
		io.gatling.liferay.service.ProcessScenarioLinkLocalService processScenarioLinkLocalService) {
		this.processScenarioLinkLocalService = processScenarioLinkLocalService;
	}

	/**
	 * Returns the process scenario link persistence.
	 *
	 * @return the process scenario link persistence
	 */
	public ProcessScenarioLinkPersistence getProcessScenarioLinkPersistence() {
		return processScenarioLinkPersistence;
	}

	/**
	 * Sets the process scenario link persistence.
	 *
	 * @param processScenarioLinkPersistence the process scenario link persistence
	 */
	public void setProcessScenarioLinkPersistence(
		ProcessScenarioLinkPersistence processScenarioLinkPersistence) {
		this.processScenarioLinkPersistence = processScenarioLinkPersistence;
	}

	/**
	 * Returns the record local service.
	 *
	 * @return the record local service
	 */
	public RecordLocalService getRecordLocalService() {
		return recordLocalService;
	}

	/**
	 * Sets the record local service.
	 *
	 * @param recordLocalService the record local service
	 */
	public void setRecordLocalService(RecordLocalService recordLocalService) {
		this.recordLocalService = recordLocalService;
	}

	/**
	 * Returns the record persistence.
	 *
	 * @return the record persistence
	 */
	public RecordPersistence getRecordPersistence() {
		return recordPersistence;
	}

	/**
	 * Sets the record persistence.
	 *
	 * @param recordPersistence the record persistence
	 */
	public void setRecordPersistence(RecordPersistence recordPersistence) {
		this.recordPersistence = recordPersistence;
	}

	/**
	 * Returns the scenario local service.
	 *
	 * @return the scenario local service
	 */
	public io.gatling.liferay.service.ScenarioLocalService getScenarioLocalService() {
		return scenarioLocalService;
	}

	/**
	 * Sets the scenario local service.
	 *
	 * @param scenarioLocalService the scenario local service
	 */
	public void setScenarioLocalService(
		io.gatling.liferay.service.ScenarioLocalService scenarioLocalService) {
		this.scenarioLocalService = scenarioLocalService;
	}

	/**
	 * Returns the scenario persistence.
	 *
	 * @return the scenario persistence
	 */
	public ScenarioPersistence getScenarioPersistence() {
		return scenarioPersistence;
	}

	/**
	 * Sets the scenario persistence.
	 *
	 * @param scenarioPersistence the scenario persistence
	 */
	public void setScenarioPersistence(ScenarioPersistence scenarioPersistence) {
		this.scenarioPersistence = scenarioPersistence;
	}

	/**
	 * Returns the simulation local service.
	 *
	 * @return the simulation local service
	 */
	public io.gatling.liferay.service.SimulationLocalService getSimulationLocalService() {
		return simulationLocalService;
	}

	/**
	 * Sets the simulation local service.
	 *
	 * @param simulationLocalService the simulation local service
	 */
	public void setSimulationLocalService(
		io.gatling.liferay.service.SimulationLocalService simulationLocalService) {
		this.simulationLocalService = simulationLocalService;
	}

	/**
	 * Returns the simulation persistence.
	 *
	 * @return the simulation persistence
	 */
	public SimulationPersistence getSimulationPersistence() {
		return simulationPersistence;
	}

	/**
	 * Sets the simulation persistence.
	 *
	 * @param simulationPersistence the simulation persistence
	 */
	public void setSimulationPersistence(
		SimulationPersistence simulationPersistence) {
		this.simulationPersistence = simulationPersistence;
	}

	/**
	 * Returns the site map local service.
	 *
	 * @return the site map local service
	 */
	public io.gatling.liferay.service.SiteMapLocalService getSiteMapLocalService() {
		return siteMapLocalService;
	}

	/**
	 * Sets the site map local service.
	 *
	 * @param siteMapLocalService the site map local service
	 */
	public void setSiteMapLocalService(
		io.gatling.liferay.service.SiteMapLocalService siteMapLocalService) {
		this.siteMapLocalService = siteMapLocalService;
	}

	/**
	 * Returns the site map persistence.
	 *
	 * @return the site map persistence
	 */
	public SiteMapPersistence getSiteMapPersistence() {
		return siteMapPersistence;
	}

	/**
	 * Sets the site map persistence.
	 *
	 * @param siteMapPersistence the site map persistence
	 */
	public void setSiteMapPersistence(SiteMapPersistence siteMapPersistence) {
		this.siteMapPersistence = siteMapPersistence;
	}

	/**
	 * Returns the url record local service.
	 *
	 * @return the url record local service
	 */
	public io.gatling.liferay.service.UrlRecordLocalService getUrlRecordLocalService() {
		return urlRecordLocalService;
	}

	/**
	 * Sets the url record local service.
	 *
	 * @param urlRecordLocalService the url record local service
	 */
	public void setUrlRecordLocalService(
		io.gatling.liferay.service.UrlRecordLocalService urlRecordLocalService) {
		this.urlRecordLocalService = urlRecordLocalService;
	}

	/**
	 * Returns the url record persistence.
	 *
	 * @return the url record persistence
	 */
	public UrlRecordPersistence getUrlRecordPersistence() {
		return urlRecordPersistence;
	}

	/**
	 * Sets the url record persistence.
	 *
	 * @param urlRecordPersistence the url record persistence
	 */
	public void setUrlRecordPersistence(
		UrlRecordPersistence urlRecordPersistence) {
		this.urlRecordPersistence = urlRecordPersistence;
	}

	/**
	 * Returns the url site map local service.
	 *
	 * @return the url site map local service
	 */
	public io.gatling.liferay.service.UrlSiteMapLocalService getUrlSiteMapLocalService() {
		return urlSiteMapLocalService;
	}

	/**
	 * Sets the url site map local service.
	 *
	 * @param urlSiteMapLocalService the url site map local service
	 */
	public void setUrlSiteMapLocalService(
		io.gatling.liferay.service.UrlSiteMapLocalService urlSiteMapLocalService) {
		this.urlSiteMapLocalService = urlSiteMapLocalService;
	}

	/**
	 * Returns the url site map persistence.
	 *
	 * @return the url site map persistence
	 */
	public UrlSiteMapPersistence getUrlSiteMapPersistence() {
		return urlSiteMapPersistence;
	}

	/**
	 * Sets the url site map persistence.
	 *
	 * @param urlSiteMapPersistence the url site map persistence
	 */
	public void setUrlSiteMapPersistence(
		UrlSiteMapPersistence urlSiteMapPersistence) {
		this.urlSiteMapPersistence = urlSiteMapPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("io.gatling.liferay.model.Record",
			recordLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"io.gatling.liferay.model.Record");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return RecordLocalService.class.getName();
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return Record.class;
	}

	protected String getModelClassName() {
		return Record.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = recordPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = io.gatling.liferay.service.FormParamLocalService.class)
	protected io.gatling.liferay.service.FormParamLocalService formParamLocalService;
	@BeanReference(type = FormParamPersistence.class)
	protected FormParamPersistence formParamPersistence;
	@BeanReference(type = io.gatling.liferay.service.LoginLocalService.class)
	protected io.gatling.liferay.service.LoginLocalService loginLocalService;
	@BeanReference(type = LoginPersistence.class)
	protected LoginPersistence loginPersistence;
	@BeanReference(type = io.gatling.liferay.service.ProcessLocalService.class)
	protected io.gatling.liferay.service.ProcessLocalService processLocalService;
	@BeanReference(type = ProcessPersistence.class)
	protected ProcessPersistence processPersistence;
	@BeanReference(type = io.gatling.liferay.service.ProcessScenarioLinkLocalService.class)
	protected io.gatling.liferay.service.ProcessScenarioLinkLocalService processScenarioLinkLocalService;
	@BeanReference(type = ProcessScenarioLinkPersistence.class)
	protected ProcessScenarioLinkPersistence processScenarioLinkPersistence;
	@BeanReference(type = RecordLocalService.class)
	protected RecordLocalService recordLocalService;
	@BeanReference(type = RecordPersistence.class)
	protected RecordPersistence recordPersistence;
	@BeanReference(type = io.gatling.liferay.service.ScenarioLocalService.class)
	protected io.gatling.liferay.service.ScenarioLocalService scenarioLocalService;
	@BeanReference(type = ScenarioPersistence.class)
	protected ScenarioPersistence scenarioPersistence;
	@BeanReference(type = io.gatling.liferay.service.SimulationLocalService.class)
	protected io.gatling.liferay.service.SimulationLocalService simulationLocalService;
	@BeanReference(type = SimulationPersistence.class)
	protected SimulationPersistence simulationPersistence;
	@BeanReference(type = io.gatling.liferay.service.SiteMapLocalService.class)
	protected io.gatling.liferay.service.SiteMapLocalService siteMapLocalService;
	@BeanReference(type = SiteMapPersistence.class)
	protected SiteMapPersistence siteMapPersistence;
	@BeanReference(type = io.gatling.liferay.service.UrlRecordLocalService.class)
	protected io.gatling.liferay.service.UrlRecordLocalService urlRecordLocalService;
	@BeanReference(type = UrlRecordPersistence.class)
	protected UrlRecordPersistence urlRecordPersistence;
	@BeanReference(type = io.gatling.liferay.service.UrlSiteMapLocalService.class)
	protected io.gatling.liferay.service.UrlSiteMapLocalService urlSiteMapLocalService;
	@BeanReference(type = UrlSiteMapPersistence.class)
	protected UrlSiteMapPersistence urlSiteMapPersistence;
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private ClassLoader _classLoader;
	private RecordLocalServiceClpInvoker _clpInvoker = new RecordLocalServiceClpInvoker();
}