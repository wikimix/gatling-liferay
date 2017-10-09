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
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import io.gatling.liferay.exception.NoSuchProcessException;
import io.gatling.liferay.model.Process;
import io.gatling.liferay.model.impl.ProcessImpl;
import io.gatling.liferay.model.impl.ProcessModelImpl;
import io.gatling.liferay.service.persistence.ProcessPersistence;

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
 * The persistence implementation for the process service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcessPersistence
 * @see io.gatling.liferay.service.persistence.ProcessUtil
 * @generated
 */
@ProviderType
public class ProcessPersistenceImpl extends BasePersistenceImpl<Process>
	implements ProcessPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProcessUtil} to access the process persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProcessImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProcessModelImpl.ENTITY_CACHE_ENABLED,
			ProcessModelImpl.FINDER_CACHE_ENABLED, ProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProcessModelImpl.ENTITY_CACHE_ENABLED,
			ProcessModelImpl.FINDER_CACHE_ENABLED, ProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProcessModelImpl.ENTITY_CACHE_ENABLED,
			ProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(ProcessModelImpl.ENTITY_CACHE_ENABLED,
			ProcessModelImpl.FINDER_CACHE_ENABLED, ProcessImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			ProcessModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(ProcessModelImpl.ENTITY_CACHE_ENABLED,
			ProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the process where name = &#63; or throws a {@link NoSuchProcessException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching process
	 * @throws NoSuchProcessException if a matching process could not be found
	 */
	@Override
	public Process findByName(String name) throws NoSuchProcessException {
		Process process = fetchByName(name);

		if (process == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchProcessException(msg.toString());
		}

		return process;
	}

	/**
	 * Returns the process where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching process, or <code>null</code> if a matching process could not be found
	 */
	@Override
	public Process fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the process where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process, or <code>null</code> if a matching process could not be found
	 */
	@Override
	public Process fetchByName(String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof Process) {
			Process process = (Process)result;

			if (!Objects.equals(name, process.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PROCESS_WHERE);

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

				List<Process> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					Process process = list.get(0);

					result = process;

					cacheResult(process);

					if ((process.getName() == null) ||
							!process.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, process);
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
			return (Process)result;
		}
	}

	/**
	 * Removes the process where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the process that was removed
	 */
	@Override
	public Process removeByName(String name) throws NoSuchProcessException {
		Process process = findByName(name);

		return remove(process);
	}

	/**
	 * Returns the number of processes where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching processes
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESS_WHERE);

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "process.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "process.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(process.name IS NULL OR process.name = '')";

	public ProcessPersistenceImpl() {
		setModelClass(Process.class);
	}

	/**
	 * Caches the process in the entity cache if it is enabled.
	 *
	 * @param process the process
	 */
	@Override
	public void cacheResult(Process process) {
		entityCache.putResult(ProcessModelImpl.ENTITY_CACHE_ENABLED,
			ProcessImpl.class, process.getPrimaryKey(), process);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { process.getName() }, process);

		process.resetOriginalValues();
	}

	/**
	 * Caches the processes in the entity cache if it is enabled.
	 *
	 * @param processes the processes
	 */
	@Override
	public void cacheResult(List<Process> processes) {
		for (Process process : processes) {
			if (entityCache.getResult(ProcessModelImpl.ENTITY_CACHE_ENABLED,
						ProcessImpl.class, process.getPrimaryKey()) == null) {
				cacheResult(process);
			}
			else {
				process.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all processes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProcessImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the process.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Process process) {
		entityCache.removeResult(ProcessModelImpl.ENTITY_CACHE_ENABLED,
			ProcessImpl.class, process.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ProcessModelImpl)process, true);
	}

	@Override
	public void clearCache(List<Process> processes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Process process : processes) {
			entityCache.removeResult(ProcessModelImpl.ENTITY_CACHE_ENABLED,
				ProcessImpl.class, process.getPrimaryKey());

			clearUniqueFindersCache((ProcessModelImpl)process, true);
		}
	}

	protected void cacheUniqueFindersCache(ProcessModelImpl processModelImpl) {
		Object[] args = new Object[] { processModelImpl.getName() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAME, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME, args,
			processModelImpl, false);
	}

	protected void clearUniqueFindersCache(ProcessModelImpl processModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { processModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}

		if ((processModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { processModelImpl.getOriginalName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new process with the primary key. Does not add the process to the database.
	 *
	 * @param process_id the primary key for the new process
	 * @return the new process
	 */
	@Override
	public Process create(long process_id) {
		Process process = new ProcessImpl();

		process.setNew(true);
		process.setPrimaryKey(process_id);

		return process;
	}

	/**
	 * Removes the process with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param process_id the primary key of the process
	 * @return the process that was removed
	 * @throws NoSuchProcessException if a process with the primary key could not be found
	 */
	@Override
	public Process remove(long process_id) throws NoSuchProcessException {
		return remove((Serializable)process_id);
	}

	/**
	 * Removes the process with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the process
	 * @return the process that was removed
	 * @throws NoSuchProcessException if a process with the primary key could not be found
	 */
	@Override
	public Process remove(Serializable primaryKey)
		throws NoSuchProcessException {
		Session session = null;

		try {
			session = openSession();

			Process process = (Process)session.get(ProcessImpl.class, primaryKey);

			if (process == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProcessException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(process);
		}
		catch (NoSuchProcessException nsee) {
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
	protected Process removeImpl(Process process) {
		process = toUnwrappedModel(process);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(process)) {
				process = (Process)session.get(ProcessImpl.class,
						process.getPrimaryKeyObj());
			}

			if (process != null) {
				session.delete(process);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (process != null) {
			clearCache(process);
		}

		return process;
	}

	@Override
	public Process updateImpl(Process process) {
		process = toUnwrappedModel(process);

		boolean isNew = process.isNew();

		ProcessModelImpl processModelImpl = (ProcessModelImpl)process;

		Session session = null;

		try {
			session = openSession();

			if (process.isNew()) {
				session.save(process);

				process.setNew(false);
			}
			else {
				process = (Process)session.merge(process);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ProcessModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(ProcessModelImpl.ENTITY_CACHE_ENABLED,
			ProcessImpl.class, process.getPrimaryKey(), process, false);

		clearUniqueFindersCache(processModelImpl, false);
		cacheUniqueFindersCache(processModelImpl);

		process.resetOriginalValues();

		return process;
	}

	protected Process toUnwrappedModel(Process process) {
		if (process instanceof ProcessImpl) {
			return process;
		}

		ProcessImpl processImpl = new ProcessImpl();

		processImpl.setNew(process.isNew());
		processImpl.setPrimaryKey(process.getPrimaryKey());

		processImpl.setProcess_id(process.getProcess_id());
		processImpl.setName(process.getName());
		processImpl.setType(process.getType());
		processImpl.setFeederId(process.getFeederId());

		return processImpl;
	}

	/**
	 * Returns the process with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the process
	 * @return the process
	 * @throws NoSuchProcessException if a process with the primary key could not be found
	 */
	@Override
	public Process findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProcessException {
		Process process = fetchByPrimaryKey(primaryKey);

		if (process == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProcessException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return process;
	}

	/**
	 * Returns the process with the primary key or throws a {@link NoSuchProcessException} if it could not be found.
	 *
	 * @param process_id the primary key of the process
	 * @return the process
	 * @throws NoSuchProcessException if a process with the primary key could not be found
	 */
	@Override
	public Process findByPrimaryKey(long process_id)
		throws NoSuchProcessException {
		return findByPrimaryKey((Serializable)process_id);
	}

	/**
	 * Returns the process with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the process
	 * @return the process, or <code>null</code> if a process with the primary key could not be found
	 */
	@Override
	public Process fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProcessModelImpl.ENTITY_CACHE_ENABLED,
				ProcessImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Process process = (Process)serializable;

		if (process == null) {
			Session session = null;

			try {
				session = openSession();

				process = (Process)session.get(ProcessImpl.class, primaryKey);

				if (process != null) {
					cacheResult(process);
				}
				else {
					entityCache.putResult(ProcessModelImpl.ENTITY_CACHE_ENABLED,
						ProcessImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProcessModelImpl.ENTITY_CACHE_ENABLED,
					ProcessImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return process;
	}

	/**
	 * Returns the process with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param process_id the primary key of the process
	 * @return the process, or <code>null</code> if a process with the primary key could not be found
	 */
	@Override
	public Process fetchByPrimaryKey(long process_id) {
		return fetchByPrimaryKey((Serializable)process_id);
	}

	@Override
	public Map<Serializable, Process> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Process> map = new HashMap<Serializable, Process>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Process process = fetchByPrimaryKey(primaryKey);

			if (process != null) {
				map.put(primaryKey, process);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProcessModelImpl.ENTITY_CACHE_ENABLED,
					ProcessImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Process)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROCESS_WHERE_PKS_IN);

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

			for (Process process : (List<Process>)q.list()) {
				map.put(process.getPrimaryKeyObj(), process);

				cacheResult(process);

				uncachedPrimaryKeys.remove(process.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProcessModelImpl.ENTITY_CACHE_ENABLED,
					ProcessImpl.class, primaryKey, nullModel);
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
	 * Returns all the processes.
	 *
	 * @return the processes
	 */
	@Override
	public List<Process> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of processes
	 * @param end the upper bound of the range of processes (not inclusive)
	 * @return the range of processes
	 */
	@Override
	public List<Process> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of processes
	 * @param end the upper bound of the range of processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of processes
	 */
	@Override
	public List<Process> findAll(int start, int end,
		OrderByComparator<Process> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of processes
	 * @param end the upper bound of the range of processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of processes
	 */
	@Override
	public List<Process> findAll(int start, int end,
		OrderByComparator<Process> orderByComparator, boolean retrieveFromCache) {
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

		List<Process> list = null;

		if (retrieveFromCache) {
			list = (List<Process>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROCESS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROCESS;

				if (pagination) {
					sql = sql.concat(ProcessModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Process>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Process>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the processes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Process process : findAll()) {
			remove(process);
		}
	}

	/**
	 * Returns the number of processes.
	 *
	 * @return the number of processes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROCESS);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ProcessModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the process persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProcessImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_PROCESS = "SELECT process FROM Process process";
	private static final String _SQL_SELECT_PROCESS_WHERE_PKS_IN = "SELECT process FROM Process process WHERE process_id IN (";
	private static final String _SQL_SELECT_PROCESS_WHERE = "SELECT process FROM Process process WHERE ";
	private static final String _SQL_COUNT_PROCESS = "SELECT COUNT(process) FROM Process process";
	private static final String _SQL_COUNT_PROCESS_WHERE = "SELECT COUNT(process) FROM Process process WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "process.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Process exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Process exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ProcessPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
}