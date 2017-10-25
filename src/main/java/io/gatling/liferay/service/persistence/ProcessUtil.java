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
package io.gatling.liferay.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

import io.gatling.liferay.model.Process;

/**
 * The persistence utility for the process service. This utility wraps {@link ProcessPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcessPersistence
 * @see ProcessPersistenceImpl
 * @generated
 */
public class ProcessUtil {
    private static ProcessPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(Process process) {
        getPersistence().clearCache(process);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<Process> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Process> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Process> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static Process update(Process process) throws SystemException {
        return getPersistence().update(process);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static Process update(Process process, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(process, serviceContext);
    }

    /**
    * Returns the process where name = &#63; or throws a {@link io.gatling.liferay.NoSuchProcessException} if it could not be found.
    *
    * @param name the name
    * @return the matching process
    * @throws io.gatling.liferay.NoSuchProcessException if a matching process could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.Process findByName(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessException {
        return getPersistence().findByName(name);
    }

    /**
    * Returns the process where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param name the name
    * @return the matching process, or <code>null</code> if a matching process could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.Process fetchByName(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByName(name);
    }

    /**
    * Returns the process where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param name the name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching process, or <code>null</code> if a matching process could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.Process fetchByName(
        java.lang.String name, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByName(name, retrieveFromCache);
    }

    /**
    * Removes the process where name = &#63; from the database.
    *
    * @param name the name
    * @return the process that was removed
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.Process removeByName(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessException {
        return getPersistence().removeByName(name);
    }

    /**
    * Returns the number of processes where name = &#63;.
    *
    * @param name the name
    * @return the number of matching processes
    * @throws SystemException if a system exception occurred
    */
    public static int countByName(java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByName(name);
    }

    /**
    * Caches the process in the entity cache if it is enabled.
    *
    * @param process the process
    */
    public static void cacheResult(io.gatling.liferay.model.Process process) {
        getPersistence().cacheResult(process);
    }

    /**
    * Caches the processes in the entity cache if it is enabled.
    *
    * @param processes the processes
    */
    public static void cacheResult(
        java.util.List<io.gatling.liferay.model.Process> processes) {
        getPersistence().cacheResult(processes);
    }

    /**
    * Creates a new process with the primary key. Does not add the process to the database.
    *
    * @param process_id the primary key for the new process
    * @return the new process
    */
    public static io.gatling.liferay.model.Process create(long process_id) {
        return getPersistence().create(process_id);
    }

    /**
    * Removes the process with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param process_id the primary key of the process
    * @return the process that was removed
    * @throws io.gatling.liferay.NoSuchProcessException if a process with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.Process remove(long process_id)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessException {
        return getPersistence().remove(process_id);
    }

    public static io.gatling.liferay.model.Process updateImpl(
        io.gatling.liferay.model.Process process)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(process);
    }

    /**
    * Returns the process with the primary key or throws a {@link io.gatling.liferay.NoSuchProcessException} if it could not be found.
    *
    * @param process_id the primary key of the process
    * @return the process
    * @throws io.gatling.liferay.NoSuchProcessException if a process with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.Process findByPrimaryKey(
        long process_id)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessException {
        return getPersistence().findByPrimaryKey(process_id);
    }

    /**
    * Returns the process with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param process_id the primary key of the process
    * @return the process, or <code>null</code> if a process with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.Process fetchByPrimaryKey(
        long process_id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(process_id);
    }

    /**
    * Returns all the processes.
    *
    * @return the processes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<io.gatling.liferay.model.Process> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<io.gatling.liferay.model.Process> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the processes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link io.gatling.liferay.model.impl.ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of processes
    * @param end the upper bound of the range of processes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of processes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<io.gatling.liferay.model.Process> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the processes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of processes.
    *
    * @return the number of processes
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProcessPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProcessPersistence) PortletBeanLocatorUtil.locate(io.gatling.liferay.service.ClpSerializer.getServletContextName(),
                    ProcessPersistence.class.getName());

            ReferenceRegistry.registerReference(ProcessUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ProcessPersistence persistence) {
    }
}
