/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package io.gatling.liferay.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import io.gatling.liferay.exception.NoSuchScenarioException;
import io.gatling.liferay.model.Scenario;
import io.gatling.liferay.model.impl.ScenarioImpl;
import io.gatling.liferay.model.impl.ScenarioModelImpl;
import io.gatling.liferay.service.persistence.ScenarioPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the scenario service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScenarioPersistence
 * @see io.gatling.liferay.service.persistence.ScenarioUtil
 * @generated
 */
@ProviderType
public class ScenarioPersistenceImpl extends BasePersistenceImpl<Scenario>
	implements ScenarioPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ScenarioUtil} to access the scenario persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ScenarioImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
			ScenarioModelImpl.FINDER_CACHE_ENABLED, ScenarioImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
			ScenarioModelImpl.FINDER_CACHE_ENABLED, ScenarioImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
			ScenarioModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONID =
		new FinderPath(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
			ScenarioModelImpl.FINDER_CACHE_ENABLED, ScenarioImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySimulationId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID =
		new FinderPath(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
			ScenarioModelImpl.FINDER_CACHE_ENABLED, ScenarioImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySimulationId",
			new String[] { Long.class.getName() },
			ScenarioModelImpl.SIMULATION_ID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIMULATIONID = new FinderPath(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
			ScenarioModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySimulationId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the scenarios where simulation_id = &#63;.
	 *
	 * @param simulation_id the simulation_id
	 * @return the matching scenarios
	 */
	@Override
	public List<Scenario> findBySimulationId(long simulation_id) {
		return findBySimulationId(simulation_id, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scenarios where simulation_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScenarioModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulation_id the simulation_id
	 * @param start the lower bound of the range of scenarios
	 * @param end the upper bound of the range of scenarios (not inclusive)
	 * @return the range of matching scenarios
	 */
	@Override
	public List<Scenario> findBySimulationId(long simulation_id, int start,
		int end) {
		return findBySimulationId(simulation_id, start, end, null);
	}

	/**
	 * Returns an ordered range of all the scenarios where simulation_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScenarioModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulation_id the simulation_id
	 * @param start the lower bound of the range of scenarios
	 * @param end the upper bound of the range of scenarios (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching scenarios
	 */
	@Override
	public List<Scenario> findBySimulationId(long simulation_id, int start,
		int end, OrderByComparator<Scenario> orderByComparator) {
		return findBySimulationId(simulation_id, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the scenarios where simulation_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScenarioModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param simulation_id the simulation_id
	 * @param start the lower bound of the range of scenarios
	 * @param end the upper bound of the range of scenarios (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching scenarios
	 */
	@Override
	public List<Scenario> findBySimulationId(long simulation_id, int start,
		int end, OrderByComparator<Scenario> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID;
			finderArgs = new Object[] { simulation_id };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SIMULATIONID;
			finderArgs = new Object[] {
					simulation_id,
					
					start, end, orderByComparator
				};
		}

		List<Scenario> list = null;

		if (retrieveFromCache) {
			list = (List<Scenario>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Scenario scenario : list) {
					if ((simulation_id != scenario.getSimulation_id())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SCENARIO_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONID_SIMULATION_ID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ScenarioModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulation_id);

				if (!pagination) {
					list = (List<Scenario>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Scenario>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first scenario in the ordered set where simulation_id = &#63;.
	 *
	 * @param simulation_id the simulation_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scenario
	 * @throws NoSuchScenarioException if a matching scenario could not be found
	 */
	@Override
	public Scenario findBySimulationId_First(long simulation_id,
		OrderByComparator<Scenario> orderByComparator)
		throws NoSuchScenarioException {
		Scenario scenario = fetchBySimulationId_First(simulation_id,
				orderByComparator);

		if (scenario != null) {
			return scenario;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulation_id=");
		msg.append(simulation_id);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScenarioException(msg.toString());
	}

	/**
	 * Returns the first scenario in the ordered set where simulation_id = &#63;.
	 *
	 * @param simulation_id the simulation_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching scenario, or <code>null</code> if a matching scenario could not be found
	 */
	@Override
	public Scenario fetchBySimulationId_First(long simulation_id,
		OrderByComparator<Scenario> orderByComparator) {
		List<Scenario> list = findBySimulationId(simulation_id, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last scenario in the ordered set where simulation_id = &#63;.
	 *
	 * @param simulation_id the simulation_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scenario
	 * @throws NoSuchScenarioException if a matching scenario could not be found
	 */
	@Override
	public Scenario findBySimulationId_Last(long simulation_id,
		OrderByComparator<Scenario> orderByComparator)
		throws NoSuchScenarioException {
		Scenario scenario = fetchBySimulationId_Last(simulation_id,
				orderByComparator);

		if (scenario != null) {
			return scenario;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("simulation_id=");
		msg.append(simulation_id);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchScenarioException(msg.toString());
	}

	/**
	 * Returns the last scenario in the ordered set where simulation_id = &#63;.
	 *
	 * @param simulation_id the simulation_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching scenario, or <code>null</code> if a matching scenario could not be found
	 */
	@Override
	public Scenario fetchBySimulationId_Last(long simulation_id,
		OrderByComparator<Scenario> orderByComparator) {
		int count = countBySimulationId(simulation_id);

		if (count == 0) {
			return null;
		}

		List<Scenario> list = findBySimulationId(simulation_id, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the scenarios before and after the current scenario in the ordered set where simulation_id = &#63;.
	 *
	 * @param scenario_id the primary key of the current scenario
	 * @param simulation_id the simulation_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next scenario
	 * @throws NoSuchScenarioException if a scenario with the primary key could not be found
	 */
	@Override
	public Scenario[] findBySimulationId_PrevAndNext(long scenario_id,
		long simulation_id, OrderByComparator<Scenario> orderByComparator)
		throws NoSuchScenarioException {
		Scenario scenario = findByPrimaryKey(scenario_id);

		Session session = null;

		try {
			session = openSession();

			Scenario[] array = new ScenarioImpl[3];

			array[0] = getBySimulationId_PrevAndNext(session, scenario,
					simulation_id, orderByComparator, true);

			array[1] = scenario;

			array[2] = getBySimulationId_PrevAndNext(session, scenario,
					simulation_id, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Scenario getBySimulationId_PrevAndNext(Session session,
		Scenario scenario, long simulation_id,
		OrderByComparator<Scenario> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SCENARIO_WHERE);

		query.append(_FINDER_COLUMN_SIMULATIONID_SIMULATION_ID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ScenarioModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(simulation_id);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(scenario);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Scenario> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the scenarios where simulation_id = &#63; from the database.
	 *
	 * @param simulation_id the simulation_id
	 */
	@Override
	public void removeBySimulationId(long simulation_id) {
		for (Scenario scenario : findBySimulationId(simulation_id,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(scenario);
		}
	}

	/**
	 * Returns the number of scenarios where simulation_id = &#63;.
	 *
	 * @param simulation_id the simulation_id
	 * @return the number of matching scenarios
	 */
	@Override
	public int countBySimulationId(long simulation_id) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIMULATIONID;

		Object[] finderArgs = new Object[] { simulation_id };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCENARIO_WHERE);

			query.append(_FINDER_COLUMN_SIMULATIONID_SIMULATION_ID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(simulation_id);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SIMULATIONID_SIMULATION_ID_2 = "scenario.simulation_id = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
			ScenarioModelImpl.FINDER_CACHE_ENABLED, ScenarioImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			ScenarioModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
			ScenarioModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the scenario where name = &#63; or throws a {@link NoSuchScenarioException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching scenario
	 * @throws NoSuchScenarioException if a matching scenario could not be found
	 */
	@Override
	public Scenario findByName(String name) throws NoSuchScenarioException {
		Scenario scenario = fetchByName(name);

		if (scenario == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchScenarioException(msg.toString());
		}

		return scenario;
	}

	/**
	 * Returns the scenario where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching scenario, or <code>null</code> if a matching scenario could not be found
	 */
	@Override
	public Scenario fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the scenario where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching scenario, or <code>null</code> if a matching scenario could not be found
	 */
	@Override
	public Scenario fetchByName(String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof Scenario) {
			Scenario scenario = (Scenario)result;

			if (!Objects.equals(name, scenario.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SCENARIO_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				List<Scenario> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					Scenario scenario = list.get(0);

					result = scenario;

					cacheResult(scenario);

					if ((scenario.getName() == null) ||
							!scenario.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, scenario);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Scenario)result;
		}
	}

	/**
	 * Removes the scenario where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the scenario that was removed
	 */
	@Override
	public Scenario removeByName(String name) throws NoSuchScenarioException {
		Scenario scenario = findByName(name);

		return remove(scenario);
	}

	/**
	 * Returns the number of scenarios where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching scenarios
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SCENARIO_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "scenario.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "scenario.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(scenario.name IS NULL OR scenario.name = '')";

	public ScenarioPersistenceImpl() {
		setModelClass(Scenario.class);
	}

	/**
	 * Caches the scenario in the entity cache if it is enabled.
	 *
	 * @param scenario the scenario
	 */
	@Override
	public void cacheResult(Scenario scenario) {
		entityCache.putResult(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
			ScenarioImpl.class, scenario.getPrimaryKey(), scenario);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { scenario.getName() }, scenario);

		scenario.resetOriginalValues();
	}

	/**
	 * Caches the scenarios in the entity cache if it is enabled.
	 *
	 * @param scenarios the scenarios
	 */
	@Override
	public void cacheResult(List<Scenario> scenarios) {
		for (Scenario scenario : scenarios) {
			if (entityCache.getResult(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
						ScenarioImpl.class, scenario.getPrimaryKey()) == null) {
				cacheResult(scenario);
			}
			else {
				scenario.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all scenarios.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ScenarioImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the scenario.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Scenario scenario) {
		entityCache.removeResult(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
			ScenarioImpl.class, scenario.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ScenarioModelImpl)scenario, true);
	}

	@Override
	public void clearCache(List<Scenario> scenarios) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Scenario scenario : scenarios) {
			entityCache.removeResult(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
				ScenarioImpl.class, scenario.getPrimaryKey());

			clearUniqueFindersCache((ScenarioModelImpl)scenario, true);
		}
	}

	protected void cacheUniqueFindersCache(ScenarioModelImpl scenarioModelImpl) {
		Object[] args = new Object[] { scenarioModelImpl.getName() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAME, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME, args,
			scenarioModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ScenarioModelImpl scenarioModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { scenarioModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}

		if ((scenarioModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { scenarioModelImpl.getOriginalName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new scenario with the primary key. Does not add the scenario to the database.
	 *
	 * @param scenario_id the primary key for the new scenario
	 * @return the new scenario
	 */
	@Override
	public Scenario create(long scenario_id) {
		Scenario scenario = new ScenarioImpl();

		scenario.setNew(true);
		scenario.setPrimaryKey(scenario_id);

		return scenario;
	}

	/**
	 * Removes the scenario with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scenario_id the primary key of the scenario
	 * @return the scenario that was removed
	 * @throws NoSuchScenarioException if a scenario with the primary key could not be found
	 */
	@Override
	public Scenario remove(long scenario_id) throws NoSuchScenarioException {
		return remove((Serializable)scenario_id);
	}

	/**
	 * Removes the scenario with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the scenario
	 * @return the scenario that was removed
	 * @throws NoSuchScenarioException if a scenario with the primary key could not be found
	 */
	@Override
	public Scenario remove(Serializable primaryKey)
		throws NoSuchScenarioException {
		Session session = null;

		try {
			session = openSession();

			Scenario scenario = (Scenario)session.get(ScenarioImpl.class,
					primaryKey);

			if (scenario == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchScenarioException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(scenario);
		}
		catch (NoSuchScenarioException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Scenario removeImpl(Scenario scenario) {
		scenario = toUnwrappedModel(scenario);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(scenario)) {
				scenario = (Scenario)session.get(ScenarioImpl.class,
						scenario.getPrimaryKeyObj());
			}

			if (scenario != null) {
				session.delete(scenario);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (scenario != null) {
			clearCache(scenario);
		}

		return scenario;
	}

	@Override
	public Scenario updateImpl(Scenario scenario) {
		scenario = toUnwrappedModel(scenario);

		boolean isNew = scenario.isNew();

		ScenarioModelImpl scenarioModelImpl = (ScenarioModelImpl)scenario;

		Session session = null;

		try {
			session = openSession();

			if (scenario.isNew()) {
				session.save(scenario);

				scenario.setNew(false);
			}
			else {
				scenario = (Scenario)session.merge(scenario);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ScenarioModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((scenarioModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						scenarioModelImpl.getOriginalSimulation_id()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID,
					args);

				args = new Object[] { scenarioModelImpl.getSimulation_id() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SIMULATIONID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SIMULATIONID,
					args);
			}
		}

		entityCache.putResult(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
			ScenarioImpl.class, scenario.getPrimaryKey(), scenario, false);

		clearUniqueFindersCache(scenarioModelImpl, false);
		cacheUniqueFindersCache(scenarioModelImpl);

		scenario.resetOriginalValues();

		return scenario;
	}

	protected Scenario toUnwrappedModel(Scenario scenario) {
		if (scenario instanceof ScenarioImpl) {
			return scenario;
		}

		ScenarioImpl scenarioImpl = new ScenarioImpl();

		scenarioImpl.setNew(scenario.isNew());
		scenarioImpl.setPrimaryKey(scenario.getPrimaryKey());

		scenarioImpl.setScenario_id(scenario.getScenario_id());
		scenarioImpl.setName(scenario.getName());
		scenarioImpl.setUrl_site(scenario.getUrl_site());
		scenarioImpl.setGroup_id(scenario.getGroup_id());
		scenarioImpl.setSimulation_id(scenario.getSimulation_id());
		scenarioImpl.setNumberOfUsers(scenario.getNumberOfUsers());
		scenarioImpl.setDuration(scenario.getDuration());
		scenarioImpl.setInjection(scenario.getInjection());

		return scenarioImpl;
	}

	/**
	 * Returns the scenario with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the scenario
	 * @return the scenario
	 * @throws NoSuchScenarioException if a scenario with the primary key could not be found
	 */
	@Override
	public Scenario findByPrimaryKey(Serializable primaryKey)
		throws NoSuchScenarioException {
		Scenario scenario = fetchByPrimaryKey(primaryKey);

		if (scenario == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchScenarioException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return scenario;
	}

	/**
	 * Returns the scenario with the primary key or throws a {@link NoSuchScenarioException} if it could not be found.
	 *
	 * @param scenario_id the primary key of the scenario
	 * @return the scenario
	 * @throws NoSuchScenarioException if a scenario with the primary key could not be found
	 */
	@Override
	public Scenario findByPrimaryKey(long scenario_id)
		throws NoSuchScenarioException {
		return findByPrimaryKey((Serializable)scenario_id);
	}

	/**
	 * Returns the scenario with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the scenario
	 * @return the scenario, or <code>null</code> if a scenario with the primary key could not be found
	 */
	@Override
	public Scenario fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
				ScenarioImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Scenario scenario = (Scenario)serializable;

		if (scenario == null) {
			Session session = null;

			try {
				session = openSession();

				scenario = (Scenario)session.get(ScenarioImpl.class, primaryKey);

				if (scenario != null) {
					cacheResult(scenario);
				}
				else {
					entityCache.putResult(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
						ScenarioImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
					ScenarioImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return scenario;
	}

	/**
	 * Returns the scenario with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param scenario_id the primary key of the scenario
	 * @return the scenario, or <code>null</code> if a scenario with the primary key could not be found
	 */
	@Override
	public Scenario fetchByPrimaryKey(long scenario_id) {
		return fetchByPrimaryKey((Serializable)scenario_id);
	}

	@Override
	public Map<Serializable, Scenario> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Scenario> map = new HashMap<Serializable, Scenario>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Scenario scenario = fetchByPrimaryKey(primaryKey);

			if (scenario != null) {
				map.put(primaryKey, scenario);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
					ScenarioImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Scenario)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SCENARIO_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Scenario scenario : (List<Scenario>)q.list()) {
				map.put(scenario.getPrimaryKeyObj(), scenario);

				cacheResult(scenario);

				uncachedPrimaryKeys.remove(scenario.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ScenarioModelImpl.ENTITY_CACHE_ENABLED,
					ScenarioImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the scenarios.
	 *
	 * @return the scenarios
	 */
	@Override
	public List<Scenario> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the scenarios.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScenarioModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of scenarios
	 * @param end the upper bound of the range of scenarios (not inclusive)
	 * @return the range of scenarios
	 */
	@Override
	public List<Scenario> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the scenarios.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScenarioModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of scenarios
	 * @param end the upper bound of the range of scenarios (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of scenarios
	 */
	@Override
	public List<Scenario> findAll(int start, int end,
		OrderByComparator<Scenario> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the scenarios.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScenarioModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of scenarios
	 * @param end the upper bound of the range of scenarios (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of scenarios
	 */
	@Override
	public List<Scenario> findAll(int start, int end,
		OrderByComparator<Scenario> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Scenario> list = null;

		if (retrieveFromCache) {
			list = (List<Scenario>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SCENARIO);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SCENARIO;

				if (pagination) {
					sql = sql.concat(ScenarioModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Scenario>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Scenario>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the scenarios from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Scenario scenario : findAll()) {
			remove(scenario);
		}
	}

	/**
	 * Returns the number of scenarios.
	 *
	 * @return the number of scenarios
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SCENARIO);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ScenarioModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the scenario persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ScenarioImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SCENARIO = "SELECT scenario FROM Scenario scenario";
	private static final String _SQL_SELECT_SCENARIO_WHERE_PKS_IN = "SELECT scenario FROM Scenario scenario WHERE scenario_id IN (";
	private static final String _SQL_SELECT_SCENARIO_WHERE = "SELECT scenario FROM Scenario scenario WHERE ";
	private static final String _SQL_COUNT_SCENARIO = "SELECT COUNT(scenario) FROM Scenario scenario";
	private static final String _SQL_COUNT_SCENARIO_WHERE = "SELECT COUNT(scenario) FROM Scenario scenario WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "scenario.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Scenario exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Scenario exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ScenarioPersistenceImpl.class);
}