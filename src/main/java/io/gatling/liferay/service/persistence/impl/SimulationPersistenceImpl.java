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
package io.gatling.liferay.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import io.gatling.liferay.exception.NoSuchSimulationException;
import io.gatling.liferay.model.Simulation;
import io.gatling.liferay.model.impl.SimulationImpl;
import io.gatling.liferay.model.impl.SimulationModelImpl;
import io.gatling.liferay.service.persistence.SimulationPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the simulation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SimulationPersistence
 * @see io.gatling.liferay.service.persistence.SimulationUtil
 * @generated
 */
@ProviderType
public class SimulationPersistenceImpl extends BasePersistenceImpl<Simulation>
	implements SimulationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SimulationUtil} to access the simulation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SimulationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, SimulationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public SimulationPersistenceImpl() {
		setModelClass(Simulation.class);
	}

	/**
	 * Caches the simulation in the entity cache if it is enabled.
	 *
	 * @param simulation the simulation
	 */
	@Override
	public void cacheResult(Simulation simulation) {
		entityCache.putResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationImpl.class, simulation.getPrimaryKey(), simulation);

		simulation.resetOriginalValues();
	}

	/**
	 * Caches the simulations in the entity cache if it is enabled.
	 *
	 * @param simulations the simulations
	 */
	@Override
	public void cacheResult(List<Simulation> simulations) {
		for (Simulation simulation : simulations) {
			if (entityCache.getResult(
						SimulationModelImpl.ENTITY_CACHE_ENABLED,
						SimulationImpl.class, simulation.getPrimaryKey()) == null) {
				cacheResult(simulation);
			}
			else {
				simulation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all simulations.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SimulationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the simulation.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Simulation simulation) {
		entityCache.removeResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationImpl.class, simulation.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Simulation> simulations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Simulation simulation : simulations) {
			entityCache.removeResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
				SimulationImpl.class, simulation.getPrimaryKey());
		}
	}

	/**
	 * Creates a new simulation with the primary key. Does not add the simulation to the database.
	 *
	 * @param simulation_id the primary key for the new simulation
	 * @return the new simulation
	 */
	@Override
	public Simulation create(long simulation_id) {
		Simulation simulation = new SimulationImpl();

		simulation.setNew(true);
		simulation.setPrimaryKey(simulation_id);

		return simulation;
	}

	/**
	 * Removes the simulation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param simulation_id the primary key of the simulation
	 * @return the simulation that was removed
	 * @throws NoSuchSimulationException if a simulation with the primary key could not be found
	 */
	@Override
	public Simulation remove(long simulation_id)
		throws NoSuchSimulationException {
		return remove((Serializable)simulation_id);
	}

	/**
	 * Removes the simulation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the simulation
	 * @return the simulation that was removed
	 * @throws NoSuchSimulationException if a simulation with the primary key could not be found
	 */
	@Override
	public Simulation remove(Serializable primaryKey)
		throws NoSuchSimulationException {
		Session session = null;

		try {
			session = openSession();

			Simulation simulation = (Simulation)session.get(SimulationImpl.class,
					primaryKey);

			if (simulation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSimulationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(simulation);
		}
		catch (NoSuchSimulationException nsee) {
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
	protected Simulation removeImpl(Simulation simulation) {
		simulation = toUnwrappedModel(simulation);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(simulation)) {
				simulation = (Simulation)session.get(SimulationImpl.class,
						simulation.getPrimaryKeyObj());
			}

			if (simulation != null) {
				session.delete(simulation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (simulation != null) {
			clearCache(simulation);
		}

		return simulation;
	}

	@Override
	public Simulation updateImpl(Simulation simulation) {
		simulation = toUnwrappedModel(simulation);

		boolean isNew = simulation.isNew();

		Session session = null;

		try {
			session = openSession();

			if (simulation.isNew()) {
				session.save(simulation);

				simulation.setNew(false);
			}
			else {
				simulation = (Simulation)session.merge(simulation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
			SimulationImpl.class, simulation.getPrimaryKey(), simulation, false);

		simulation.resetOriginalValues();

		return simulation;
	}

	protected Simulation toUnwrappedModel(Simulation simulation) {
		if (simulation instanceof SimulationImpl) {
			return simulation;
		}

		SimulationImpl simulationImpl = new SimulationImpl();

		simulationImpl.setNew(simulation.isNew());
		simulationImpl.setPrimaryKey(simulation.getPrimaryKey());

		simulationImpl.setSimulation_id(simulation.getSimulation_id());
		simulationImpl.setName(simulation.getName());
		simulationImpl.setFeederContent(simulation.getFeederContent());
		simulationImpl.setIsFeederAFile(simulation.isIsFeederAFile());

		return simulationImpl;
	}

	/**
	 * Returns the simulation with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the simulation
	 * @return the simulation
	 * @throws NoSuchSimulationException if a simulation with the primary key could not be found
	 */
	@Override
	public Simulation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSimulationException {
		Simulation simulation = fetchByPrimaryKey(primaryKey);

		if (simulation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSimulationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return simulation;
	}

	/**
	 * Returns the simulation with the primary key or throws a {@link NoSuchSimulationException} if it could not be found.
	 *
	 * @param simulation_id the primary key of the simulation
	 * @return the simulation
	 * @throws NoSuchSimulationException if a simulation with the primary key could not be found
	 */
	@Override
	public Simulation findByPrimaryKey(long simulation_id)
		throws NoSuchSimulationException {
		return findByPrimaryKey((Serializable)simulation_id);
	}

	/**
	 * Returns the simulation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the simulation
	 * @return the simulation, or <code>null</code> if a simulation with the primary key could not be found
	 */
	@Override
	public Simulation fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
				SimulationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Simulation simulation = (Simulation)serializable;

		if (simulation == null) {
			Session session = null;

			try {
				session = openSession();

				simulation = (Simulation)session.get(SimulationImpl.class,
						primaryKey);

				if (simulation != null) {
					cacheResult(simulation);
				}
				else {
					entityCache.putResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
						SimulationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
					SimulationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return simulation;
	}

	/**
	 * Returns the simulation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param simulation_id the primary key of the simulation
	 * @return the simulation, or <code>null</code> if a simulation with the primary key could not be found
	 */
	@Override
	public Simulation fetchByPrimaryKey(long simulation_id) {
		return fetchByPrimaryKey((Serializable)simulation_id);
	}

	@Override
	public Map<Serializable, Simulation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Simulation> map = new HashMap<Serializable, Simulation>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Simulation simulation = fetchByPrimaryKey(primaryKey);

			if (simulation != null) {
				map.put(primaryKey, simulation);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
					SimulationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Simulation)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SIMULATION_WHERE_PKS_IN);

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

			for (Simulation simulation : (List<Simulation>)q.list()) {
				map.put(simulation.getPrimaryKeyObj(), simulation);

				cacheResult(simulation);

				uncachedPrimaryKeys.remove(simulation.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SimulationModelImpl.ENTITY_CACHE_ENABLED,
					SimulationImpl.class, primaryKey, nullModel);
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
	 * Returns all the simulations.
	 *
	 * @return the simulations
	 */
	@Override
	public List<Simulation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the simulations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @return the range of simulations
	 */
	@Override
	public List<Simulation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the simulations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of simulations
	 */
	@Override
	public List<Simulation> findAll(int start, int end,
		OrderByComparator<Simulation> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the simulations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SimulationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of simulations
	 * @param end the upper bound of the range of simulations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of simulations
	 */
	@Override
	public List<Simulation> findAll(int start, int end,
		OrderByComparator<Simulation> orderByComparator,
		boolean retrieveFromCache) {
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

		List<Simulation> list = null;

		if (retrieveFromCache) {
			list = (List<Simulation>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SIMULATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SIMULATION;

				if (pagination) {
					sql = sql.concat(SimulationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Simulation>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the simulations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Simulation simulation : findAll()) {
			remove(simulation);
		}
	}

	/**
	 * Returns the number of simulations.
	 *
	 * @return the number of simulations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SIMULATION);

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
		return SimulationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the simulation persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SimulationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SIMULATION = "SELECT simulation FROM Simulation simulation";
	private static final String _SQL_SELECT_SIMULATION_WHERE_PKS_IN = "SELECT simulation FROM Simulation simulation WHERE simulation_id IN (";
	private static final String _SQL_COUNT_SIMULATION = "SELECT COUNT(simulation) FROM Simulation simulation";
	private static final String _ORDER_BY_ENTITY_ALIAS = "simulation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Simulation exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(SimulationPersistenceImpl.class);
}