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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

import io.gatling.liferay.exception.NoSuchProcessException;
import io.gatling.liferay.exception.NoSuchProcessScenarioLinkException;

/**
 * Provides the local service interface for Process. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @param <T>
 * @see ProcessLocalServiceUtil
 * @see io.gatling.liferay.service.base.ProcessLocalServiceBaseImpl
 * @see io.gatling.liferay.service.impl.ProcessLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ProcessLocalService<T> extends BaseLocalService,
    InvokableLocalService, PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProcessLocalServiceUtil} to access the process local service. Add custom service methods to {@link io.gatling.liferay.service.impl.ProcessLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the process to the database. Also notifies the appropriate model listeners.
    *
    * @param process the process
    * @return the process that was added
    * @throws SystemException if a system exception occurred
    */
    @com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
    public io.gatling.liferay.model.Process addProcess(
        io.gatling.liferay.model.Process process)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new process with the primary key. Does not add the process to the database.
    *
    * @param process_id the primary key for the new process
    * @return the new process
    */
    public io.gatling.liferay.model.Process createProcess(long process_id);

    /**
    * Deletes the process with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param process_id the primary key of the process
    * @return the process that was removed
    * @throws PortalException if a process with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
    public io.gatling.liferay.model.Process deleteProcess(long process_id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the process from the database. Also notifies the appropriate model listeners.
    *
    * @param process the process
    * @return the process that was removed
    * @throws SystemException if a system exception occurred
    */
    @com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
    public io.gatling.liferay.model.Process deleteProcess(
        io.gatling.liferay.model.Process process)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public io.gatling.liferay.model.Process fetchProcess(long process_id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the process with the primary key.
    *
    * @param process_id the primary key of the process
    * @return the process
    * @throws PortalException if a process with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public io.gatling.liferay.model.Process getProcess(long process_id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the processes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link io.gatling.liferay.model.impl.ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of processes
    * @param end the upper bound of the range of processes (not inclusive)
    * @return the range of processes
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<io.gatling.liferay.model.Process> getProcesses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of processes.
    *
    * @return the number of processes
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getProcessesCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the process in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param process the process
    * @return the process that was updated
    * @throws SystemException if a system exception occurred
    */
    @com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
    public io.gatling.liferay.model.Process updateProcess(
        io.gatling.liferay.model.Process process)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable;

    public java.util.List<io.gatling.liferay.model.Process> findProcessFromScenarioId(
        long scenarioId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public int findPause(long scenarioId, long processesId, int order)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessScenarioLinkException, NoSuchProcessScenarioLinkException;

    public io.gatling.liferay.model.Process createProcess(
        java.lang.String name, io.gatling.liferay.model.ProcessType type,
        java.lang.Long feederId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public io.gatling.liferay.model.Process findByName(java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessException, NoSuchProcessException;

    public io.gatling.liferay.model.Process updateProcess(long id,
        java.lang.String name, io.gatling.liferay.model.ProcessType type,
        java.lang.Long feederId)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessException, NoSuchProcessException;

	IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	ActionableDynamicQuery getActionableDynamicQuery();

	  /**
	    * Performs a dynamic query on the database and returns the matching rows.
	    *
	    * @param dynamicQuery the dynamic query
	    * @return the matching rows
	    * @throws SystemException if a system exception occurred
	    */
	List<T> dynamicQuery(DynamicQuery dynamicQuery);
	/**
	 * Performs a dynamic query on the database and returns a range of the
	 * matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of
	 * <code>end - start</code> instances. <code>start</code> and
	 * <code>end</code> are not primary keys, they are indexes in the result
	 * set. Thus, <code>0</code> refers to the first result in the set. Setting
	 * both <code>start</code> and <code>end</code> to
	 * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return
	 * the full result set. If <code>orderByComparator</code> is specified, then
	 * the query will include the given ORDER BY logic. If
	 * <code>orderByComparator</code> is absent and pagination is required
	 * (<code>start</code> and <code>end</code> are not
	 * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the
	 * query will include the default ORDER BY logic from
	 * {@link io.gatling.liferay.model.impl.ProcessModelImpl}. If both
	 * <code>orderByComparator</code> and pagination are absent, for performance
	 * reasons, the query will not have an ORDER BY clause and the returned
	 * result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 * @param <T>
	 *
	 * @param dynamicQuery
	 *            the dynamic query
	 * @param start
	 *            the lower bound of the range of model instances
	 * @param end
	 *            the upper bound of the range of model instances (not
	 *            inclusive)
	 * @return the range of matching rows
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	List<T> dynamicQuery(DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of
	 * the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of
	 * <code>end - start</code> instances. <code>start</code> and
	 * <code>end</code> are not primary keys, they are indexes in the result
	 * set. Thus, <code>0</code> refers to the first result in the set. Setting
	 * both <code>start</code> and <code>end</code> to
	 * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return
	 * the full result set. If <code>orderByComparator</code> is specified, then
	 * the query will include the given ORDER BY logic. If
	 * <code>orderByComparator</code> is absent and pagination is required
	 * (<code>start</code> and <code>end</code> are not
	 * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the
	 * query will include the default ORDER BY logic from
	 * {@link io.gatling.liferay.model.impl.ProcessModelImpl}. If both
	 * <code>orderByComparator</code> and pagination are absent, for performance
	 * reasons, the query will not have an ORDER BY clause and the returned
	 * result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery
	 *            the dynamic query
	 * @param start
	 *            the lower bound of the range of model instances
	 * @param end
	 *            the upper bound of the range of model instances (not
	 *            inclusive)
	 * @param orderByComparator
	 *            the comparator to order the results by (optionally
	 *            <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	List<T> dynamicQuery(DynamicQuery dynamicQuery, int start, int end, OrderByComparator<T> orderByComparator);

}
