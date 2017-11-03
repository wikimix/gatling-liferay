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

import io.gatling.liferay.exception.NoSuchProcessScenarioLinkException;
import io.gatling.liferay.model.ProcessScenarioLink;

/**
 * The persistence utility for the process scenario link service. This utility wraps {@link ProcessScenarioLinkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcessScenarioLinkPersistence
 * @see ProcessScenarioLinkPersistenceImpl
 * @generated
 */
public class ProcessScenarioLinkUtil {
    private static ProcessScenarioLinkPersistence _persistence;

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
    public static void clearCache(ProcessScenarioLink processScenarioLink) {
        getPersistence().clearCache(processScenarioLink);
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
    public static List<ProcessScenarioLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ProcessScenarioLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ProcessScenarioLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ProcessScenarioLink update(
        ProcessScenarioLink processScenarioLink) throws SystemException {
        return getPersistence().update(processScenarioLink);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ProcessScenarioLink update(
        ProcessScenarioLink processScenarioLink, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(processScenarioLink, serviceContext);
    }

    /**
    * Returns all the process scenario links where process_id = &#63;.
    *
    * @param process_id the process_id
    * @return the matching process scenario links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<io.gatling.liferay.model.ProcessScenarioLink> findByprocessId(
        long process_id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByprocessId(process_id);
    }

    /**
    * Returns a range of all the process scenario links where process_id = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link io.gatling.liferay.model.impl.ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param process_id the process_id
    * @param start the lower bound of the range of process scenario links
    * @param end the upper bound of the range of process scenario links (not inclusive)
    * @return the range of matching process scenario links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<io.gatling.liferay.model.ProcessScenarioLink> findByprocessId(
        long process_id, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByprocessId(process_id, start, end);
    }

    /**
    * Returns an ordered range of all the process scenario links where process_id = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link io.gatling.liferay.model.impl.ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param process_id the process_id
    * @param start the lower bound of the range of process scenario links
    * @param end the upper bound of the range of process scenario links (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching process scenario links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<io.gatling.liferay.model.ProcessScenarioLink> findByprocessId(
        long process_id, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByprocessId(process_id, start, end, orderByComparator);
    }

    /**
    * Returns the first process scenario link in the ordered set where process_id = &#63;.
    *
    * @param process_id the process_id
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching process scenario link
    * @throws io.gatling.liferay.NoSuchProcessScenarioLinkException if a matching process scenario link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink findByprocessId_First(
        long process_id,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessScenarioLinkException, NoSuchProcessScenarioLinkException {
        return getPersistence()
                   .findByprocessId_First(process_id, orderByComparator);
    }

    /**
    * Returns the first process scenario link in the ordered set where process_id = &#63;.
    *
    * @param process_id the process_id
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching process scenario link, or <code>null</code> if a matching process scenario link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink fetchByprocessId_First(
        long process_id,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByprocessId_First(process_id, orderByComparator);
    }

    /**
    * Returns the last process scenario link in the ordered set where process_id = &#63;.
    *
    * @param process_id the process_id
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching process scenario link
    * @throws io.gatling.liferay.NoSuchProcessScenarioLinkException if a matching process scenario link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink findByprocessId_Last(
        long process_id,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessScenarioLinkException, NoSuchProcessScenarioLinkException {
        return getPersistence()
                   .findByprocessId_Last(process_id, orderByComparator);
    }

    /**
    * Returns the last process scenario link in the ordered set where process_id = &#63;.
    *
    * @param process_id the process_id
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching process scenario link, or <code>null</code> if a matching process scenario link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink fetchByprocessId_Last(
        long process_id,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByprocessId_Last(process_id, orderByComparator);
    }

    /**
    * Returns the process scenario links before and after the current process scenario link in the ordered set where process_id = &#63;.
    *
    * @param psl_id the primary key of the current process scenario link
    * @param process_id the process_id
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next process scenario link
    * @throws io.gatling.liferay.NoSuchProcessScenarioLinkException if a process scenario link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink[] findByprocessId_PrevAndNext(
        long psl_id, long process_id,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessScenarioLinkException, NoSuchProcessScenarioLinkException {
        return getPersistence()
                   .findByprocessId_PrevAndNext(psl_id, process_id,
            orderByComparator);
    }

    /**
    * Removes all the process scenario links where process_id = &#63; from the database.
    *
    * @param process_id the process_id
    * @throws SystemException if a system exception occurred
    */
    public static void removeByprocessId(long process_id)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByprocessId(process_id);
    }

    /**
    * Returns the number of process scenario links where process_id = &#63;.
    *
    * @param process_id the process_id
    * @return the number of matching process scenario links
    * @throws SystemException if a system exception occurred
    */
    public static int countByprocessId(long process_id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByprocessId(process_id);
    }

    /**
    * Returns all the process scenario links where scenario_id = &#63;.
    *
    * @param scenario_id the scenario_id
    * @return the matching process scenario links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<io.gatling.liferay.model.ProcessScenarioLink> findByscenarioId(
        long scenario_id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByscenarioId(scenario_id);
    }

    /**
    * Returns a range of all the process scenario links where scenario_id = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link io.gatling.liferay.model.impl.ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param scenario_id the scenario_id
    * @param start the lower bound of the range of process scenario links
    * @param end the upper bound of the range of process scenario links (not inclusive)
    * @return the range of matching process scenario links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<io.gatling.liferay.model.ProcessScenarioLink> findByscenarioId(
        long scenario_id, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByscenarioId(scenario_id, start, end);
    }

    /**
    * Returns an ordered range of all the process scenario links where scenario_id = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link io.gatling.liferay.model.impl.ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param scenario_id the scenario_id
    * @param start the lower bound of the range of process scenario links
    * @param end the upper bound of the range of process scenario links (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching process scenario links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<io.gatling.liferay.model.ProcessScenarioLink> findByscenarioId(
        long scenario_id, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByscenarioId(scenario_id, start, end, orderByComparator);
    }

    /**
    * Returns the first process scenario link in the ordered set where scenario_id = &#63;.
    *
    * @param scenario_id the scenario_id
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching process scenario link
    * @throws io.gatling.liferay.NoSuchProcessScenarioLinkException if a matching process scenario link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink findByscenarioId_First(
        long scenario_id,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessScenarioLinkException, NoSuchProcessScenarioLinkException {
        return getPersistence()
                   .findByscenarioId_First(scenario_id, orderByComparator);
    }

    /**
    * Returns the first process scenario link in the ordered set where scenario_id = &#63;.
    *
    * @param scenario_id the scenario_id
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching process scenario link, or <code>null</code> if a matching process scenario link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink fetchByscenarioId_First(
        long scenario_id,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByscenarioId_First(scenario_id, orderByComparator);
    }

    /**
    * Returns the last process scenario link in the ordered set where scenario_id = &#63;.
    *
    * @param scenario_id the scenario_id
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching process scenario link
    * @throws io.gatling.liferay.NoSuchProcessScenarioLinkException if a matching process scenario link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink findByscenarioId_Last(
        long scenario_id,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessScenarioLinkException, NoSuchProcessScenarioLinkException {
        return getPersistence()
                   .findByscenarioId_Last(scenario_id, orderByComparator);
    }

    /**
    * Returns the last process scenario link in the ordered set where scenario_id = &#63;.
    *
    * @param scenario_id the scenario_id
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching process scenario link, or <code>null</code> if a matching process scenario link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink fetchByscenarioId_Last(
        long scenario_id,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByscenarioId_Last(scenario_id, orderByComparator);
    }

    /**
    * Returns the process scenario links before and after the current process scenario link in the ordered set where scenario_id = &#63;.
    *
    * @param psl_id the primary key of the current process scenario link
    * @param scenario_id the scenario_id
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next process scenario link
    * @throws io.gatling.liferay.NoSuchProcessScenarioLinkException if a process scenario link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink[] findByscenarioId_PrevAndNext(
        long psl_id, long scenario_id,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessScenarioLinkException, NoSuchProcessScenarioLinkException {
        return getPersistence()
                   .findByscenarioId_PrevAndNext(psl_id, scenario_id,
            orderByComparator);
    }

    /**
    * Removes all the process scenario links where scenario_id = &#63; from the database.
    *
    * @param scenario_id the scenario_id
    * @throws SystemException if a system exception occurred
    */
    public static void removeByscenarioId(long scenario_id)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByscenarioId(scenario_id);
    }

    /**
    * Returns the number of process scenario links where scenario_id = &#63;.
    *
    * @param scenario_id the scenario_id
    * @return the number of matching process scenario links
    * @throws SystemException if a system exception occurred
    */
    public static int countByscenarioId(long scenario_id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByscenarioId(scenario_id);
    }

    /**
    * Returns the process scenario link where process_id = &#63; and scenario_id = &#63; and order = &#63; or throws a {@link io.gatling.liferay.NoSuchProcessScenarioLinkException} if it could not be found.
    *
    * @param process_id the process_id
    * @param scenario_id the scenario_id
    * @param order the order
    * @return the matching process scenario link
    * @throws io.gatling.liferay.NoSuchProcessScenarioLinkException if a matching process scenario link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink findByPause(
        long process_id, long scenario_id, int order)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessScenarioLinkException, NoSuchProcessScenarioLinkException {
        return getPersistence().findByPause(process_id, scenario_id, order);
    }

    /**
    * Returns the process scenario link where process_id = &#63; and scenario_id = &#63; and order = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param process_id the process_id
    * @param scenario_id the scenario_id
    * @param order the order
    * @return the matching process scenario link, or <code>null</code> if a matching process scenario link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink fetchByPause(
        long process_id, long scenario_id, int order)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPause(process_id, scenario_id, order);
    }

    /**
    * Returns the process scenario link where process_id = &#63; and scenario_id = &#63; and order = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param process_id the process_id
    * @param scenario_id the scenario_id
    * @param order the order
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching process scenario link, or <code>null</code> if a matching process scenario link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink fetchByPause(
        long process_id, long scenario_id, int order, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPause(process_id, scenario_id, order,
            retrieveFromCache);
    }

    /**
    * Removes the process scenario link where process_id = &#63; and scenario_id = &#63; and order = &#63; from the database.
    *
    * @param process_id the process_id
    * @param scenario_id the scenario_id
    * @param order the order
    * @return the process scenario link that was removed
    * @throws SystemException if a system exception occurred
     * @throws NoSuchProcessScenarioLinkException 
    */
    public static io.gatling.liferay.model.ProcessScenarioLink removeByPause(
        long process_id, long scenario_id, int order)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessScenarioLinkException, NoSuchProcessScenarioLinkException {
        return getPersistence().removeByPause(process_id, scenario_id, order);
    }

    /**
    * Returns the number of process scenario links where process_id = &#63; and scenario_id = &#63; and order = &#63;.
    *
    * @param process_id the process_id
    * @param scenario_id the scenario_id
    * @param order the order
    * @return the number of matching process scenario links
    * @throws SystemException if a system exception occurred
    */
    public static int countByPause(long process_id, long scenario_id, int order)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByPause(process_id, scenario_id, order);
    }

    /**
    * Caches the process scenario link in the entity cache if it is enabled.
    *
    * @param processScenarioLink the process scenario link
    */
    public static void cacheResult(
        io.gatling.liferay.model.ProcessScenarioLink processScenarioLink) {
        getPersistence().cacheResult(processScenarioLink);
    }

    /**
    * Caches the process scenario links in the entity cache if it is enabled.
    *
    * @param processScenarioLinks the process scenario links
    */
    public static void cacheResult(
        java.util.List<io.gatling.liferay.model.ProcessScenarioLink> processScenarioLinks) {
        getPersistence().cacheResult(processScenarioLinks);
    }

    /**
    * Creates a new process scenario link with the primary key. Does not add the process scenario link to the database.
    *
    * @param psl_id the primary key for the new process scenario link
    * @return the new process scenario link
    */
    public static io.gatling.liferay.model.ProcessScenarioLink create(
        long psl_id) {
        return getPersistence().create(psl_id);
    }

    /**
    * Removes the process scenario link with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param psl_id the primary key of the process scenario link
    * @return the process scenario link that was removed
    * @throws io.gatling.liferay.NoSuchProcessScenarioLinkException if a process scenario link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink remove(
        long psl_id)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessScenarioLinkException, NoSuchProcessScenarioLinkException {
        return getPersistence().remove(psl_id);
    }

    public static io.gatling.liferay.model.ProcessScenarioLink updateImpl(
        io.gatling.liferay.model.ProcessScenarioLink processScenarioLink)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(processScenarioLink);
    }

    /**
    * Returns the process scenario link with the primary key or throws a {@link io.gatling.liferay.NoSuchProcessScenarioLinkException} if it could not be found.
    *
    * @param psl_id the primary key of the process scenario link
    * @return the process scenario link
    * @throws io.gatling.liferay.NoSuchProcessScenarioLinkException if a process scenario link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink findByPrimaryKey(
        long psl_id)
        throws com.liferay.portal.kernel.exception.SystemException,
            io.gatling.liferay.NoSuchProcessScenarioLinkException, NoSuchProcessScenarioLinkException {
        return getPersistence().findByPrimaryKey(psl_id);
    }

    /**
    * Returns the process scenario link with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param psl_id the primary key of the process scenario link
    * @return the process scenario link, or <code>null</code> if a process scenario link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static io.gatling.liferay.model.ProcessScenarioLink fetchByPrimaryKey(
        long psl_id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(psl_id);
    }

    /**
    * Returns all the process scenario links.
    *
    * @return the process scenario links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<io.gatling.liferay.model.ProcessScenarioLink> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the process scenario links.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link io.gatling.liferay.model.impl.ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of process scenario links
    * @param end the upper bound of the range of process scenario links (not inclusive)
    * @return the range of process scenario links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<io.gatling.liferay.model.ProcessScenarioLink> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the process scenario links.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link io.gatling.liferay.model.impl.ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of process scenario links
    * @param end the upper bound of the range of process scenario links (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of process scenario links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<io.gatling.liferay.model.ProcessScenarioLink> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the process scenario links from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of process scenario links.
    *
    * @return the number of process scenario links
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ProcessScenarioLinkPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ProcessScenarioLinkPersistence) PortletBeanLocatorUtil.locate(io.gatling.liferay.service.ClpSerializer.getServletContextName(),
                    ProcessScenarioLinkPersistence.class.getName());

            ReferenceRegistry.registerReference(ProcessScenarioLinkUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ProcessScenarioLinkPersistence persistence) {
    }
}
