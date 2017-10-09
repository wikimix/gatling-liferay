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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import io.gatling.liferay.exception.NoSuchSiteMapException;
import io.gatling.liferay.model.SiteMap;
import io.gatling.liferay.model.impl.SiteMapImpl;
import io.gatling.liferay.model.impl.SiteMapModelImpl;
import io.gatling.liferay.service.persistence.SiteMapPersistence;

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
 * The persistence implementation for the site map service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SiteMapPersistence
 * @see io.gatling.liferay.service.persistence.SiteMapUtil
 * @generated
 */
@ProviderType
public class SiteMapPersistenceImpl extends BasePersistenceImpl<SiteMap>
	implements SiteMapPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SiteMapUtil} to access the site map persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SiteMapImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
			SiteMapModelImpl.FINDER_CACHE_ENABLED, SiteMapImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
			SiteMapModelImpl.FINDER_CACHE_ENABLED, SiteMapImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
			SiteMapModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
			SiteMapModelImpl.FINDER_CACHE_ENABLED, SiteMapImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			SiteMapModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
			SiteMapModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the site map where name = &#63; or throws a {@link NoSuchSiteMapException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching site map
	 * @throws NoSuchSiteMapException if a matching site map could not be found
	 */
	@Override
	public SiteMap findByName(String name) throws NoSuchSiteMapException {
		SiteMap siteMap = fetchByName(name);

		if (siteMap == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSiteMapException(msg.toString());
		}

		return siteMap;
	}

	/**
	 * Returns the site map where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching site map, or <code>null</code> if a matching site map could not be found
	 */
	@Override
	public SiteMap fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the site map where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching site map, or <code>null</code> if a matching site map could not be found
	 */
	@Override
	public SiteMap fetchByName(String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof SiteMap) {
			SiteMap siteMap = (SiteMap)result;

			if (!Objects.equals(name, siteMap.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SITEMAP_WHERE);

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

				List<SiteMap> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					SiteMap siteMap = list.get(0);

					result = siteMap;

					cacheResult(siteMap);

					if ((siteMap.getName() == null) ||
							!siteMap.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, siteMap);
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
			return (SiteMap)result;
		}
	}

	/**
	 * Removes the site map where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the site map that was removed
	 */
	@Override
	public SiteMap removeByName(String name) throws NoSuchSiteMapException {
		SiteMap siteMap = findByName(name);

		return remove(siteMap);
	}

	/**
	 * Returns the number of site maps where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching site maps
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SITEMAP_WHERE);

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "siteMap.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "siteMap.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(siteMap.name IS NULL OR siteMap.name = '')";

	public SiteMapPersistenceImpl() {
		setModelClass(SiteMap.class);
	}

	/**
	 * Caches the site map in the entity cache if it is enabled.
	 *
	 * @param siteMap the site map
	 */
	@Override
	public void cacheResult(SiteMap siteMap) {
		entityCache.putResult(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
			SiteMapImpl.class, siteMap.getPrimaryKey(), siteMap);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { siteMap.getName() }, siteMap);

		siteMap.resetOriginalValues();
	}

	/**
	 * Caches the site maps in the entity cache if it is enabled.
	 *
	 * @param siteMaps the site maps
	 */
	@Override
	public void cacheResult(List<SiteMap> siteMaps) {
		for (SiteMap siteMap : siteMaps) {
			if (entityCache.getResult(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
						SiteMapImpl.class, siteMap.getPrimaryKey()) == null) {
				cacheResult(siteMap);
			}
			else {
				siteMap.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all site maps.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SiteMapImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the site map.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SiteMap siteMap) {
		entityCache.removeResult(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
			SiteMapImpl.class, siteMap.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SiteMapModelImpl)siteMap, true);
	}

	@Override
	public void clearCache(List<SiteMap> siteMaps) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SiteMap siteMap : siteMaps) {
			entityCache.removeResult(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
				SiteMapImpl.class, siteMap.getPrimaryKey());

			clearUniqueFindersCache((SiteMapModelImpl)siteMap, true);
		}
	}

	protected void cacheUniqueFindersCache(SiteMapModelImpl siteMapModelImpl) {
		Object[] args = new Object[] { siteMapModelImpl.getName() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAME, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME, args,
			siteMapModelImpl, false);
	}

	protected void clearUniqueFindersCache(SiteMapModelImpl siteMapModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { siteMapModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}

		if ((siteMapModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { siteMapModelImpl.getOriginalName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new site map with the primary key. Does not add the site map to the database.
	 *
	 * @param siteMapId the primary key for the new site map
	 * @return the new site map
	 */
	@Override
	public SiteMap create(long siteMapId) {
		SiteMap siteMap = new SiteMapImpl();

		siteMap.setNew(true);
		siteMap.setPrimaryKey(siteMapId);

		return siteMap;
	}

	/**
	 * Removes the site map with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param siteMapId the primary key of the site map
	 * @return the site map that was removed
	 * @throws NoSuchSiteMapException if a site map with the primary key could not be found
	 */
	@Override
	public SiteMap remove(long siteMapId) throws NoSuchSiteMapException {
		return remove((Serializable)siteMapId);
	}

	/**
	 * Removes the site map with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the site map
	 * @return the site map that was removed
	 * @throws NoSuchSiteMapException if a site map with the primary key could not be found
	 */
	@Override
	public SiteMap remove(Serializable primaryKey)
		throws NoSuchSiteMapException {
		Session session = null;

		try {
			session = openSession();

			SiteMap siteMap = (SiteMap)session.get(SiteMapImpl.class, primaryKey);

			if (siteMap == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSiteMapException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(siteMap);
		}
		catch (NoSuchSiteMapException nsee) {
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
	protected SiteMap removeImpl(SiteMap siteMap) {
		siteMap = toUnwrappedModel(siteMap);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(siteMap)) {
				siteMap = (SiteMap)session.get(SiteMapImpl.class,
						siteMap.getPrimaryKeyObj());
			}

			if (siteMap != null) {
				session.delete(siteMap);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (siteMap != null) {
			clearCache(siteMap);
		}

		return siteMap;
	}

	@Override
	public SiteMap updateImpl(SiteMap siteMap) {
		siteMap = toUnwrappedModel(siteMap);

		boolean isNew = siteMap.isNew();

		SiteMapModelImpl siteMapModelImpl = (SiteMapModelImpl)siteMap;

		Session session = null;

		try {
			session = openSession();

			if (siteMap.isNew()) {
				session.save(siteMap);

				siteMap.setNew(false);
			}
			else {
				siteMap = (SiteMap)session.merge(siteMap);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SiteMapModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
			SiteMapImpl.class, siteMap.getPrimaryKey(), siteMap, false);

		clearUniqueFindersCache(siteMapModelImpl, false);
		cacheUniqueFindersCache(siteMapModelImpl);

		siteMap.resetOriginalValues();

		return siteMap;
	}

	protected SiteMap toUnwrappedModel(SiteMap siteMap) {
		if (siteMap instanceof SiteMapImpl) {
			return siteMap;
		}

		SiteMapImpl siteMapImpl = new SiteMapImpl();

		siteMapImpl.setNew(siteMap.isNew());
		siteMapImpl.setPrimaryKey(siteMap.getPrimaryKey());

		siteMapImpl.setSiteMapId(siteMap.getSiteMapId());
		siteMapImpl.setName(siteMap.getName());

		return siteMapImpl;
	}

	/**
	 * Returns the site map with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the site map
	 * @return the site map
	 * @throws NoSuchSiteMapException if a site map with the primary key could not be found
	 */
	@Override
	public SiteMap findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSiteMapException {
		SiteMap siteMap = fetchByPrimaryKey(primaryKey);

		if (siteMap == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSiteMapException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return siteMap;
	}

	/**
	 * Returns the site map with the primary key or throws a {@link NoSuchSiteMapException} if it could not be found.
	 *
	 * @param siteMapId the primary key of the site map
	 * @return the site map
	 * @throws NoSuchSiteMapException if a site map with the primary key could not be found
	 */
	@Override
	public SiteMap findByPrimaryKey(long siteMapId)
		throws NoSuchSiteMapException {
		return findByPrimaryKey((Serializable)siteMapId);
	}

	/**
	 * Returns the site map with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the site map
	 * @return the site map, or <code>null</code> if a site map with the primary key could not be found
	 */
	@Override
	public SiteMap fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
				SiteMapImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SiteMap siteMap = (SiteMap)serializable;

		if (siteMap == null) {
			Session session = null;

			try {
				session = openSession();

				siteMap = (SiteMap)session.get(SiteMapImpl.class, primaryKey);

				if (siteMap != null) {
					cacheResult(siteMap);
				}
				else {
					entityCache.putResult(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
						SiteMapImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
					SiteMapImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return siteMap;
	}

	/**
	 * Returns the site map with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param siteMapId the primary key of the site map
	 * @return the site map, or <code>null</code> if a site map with the primary key could not be found
	 */
	@Override
	public SiteMap fetchByPrimaryKey(long siteMapId) {
		return fetchByPrimaryKey((Serializable)siteMapId);
	}

	@Override
	public Map<Serializable, SiteMap> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SiteMap> map = new HashMap<Serializable, SiteMap>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SiteMap siteMap = fetchByPrimaryKey(primaryKey);

			if (siteMap != null) {
				map.put(primaryKey, siteMap);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
					SiteMapImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SiteMap)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SITEMAP_WHERE_PKS_IN);

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

			for (SiteMap siteMap : (List<SiteMap>)q.list()) {
				map.put(siteMap.getPrimaryKeyObj(), siteMap);

				cacheResult(siteMap);

				uncachedPrimaryKeys.remove(siteMap.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SiteMapModelImpl.ENTITY_CACHE_ENABLED,
					SiteMapImpl.class, primaryKey, nullModel);
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
	 * Returns all the site maps.
	 *
	 * @return the site maps
	 */
	@Override
	public List<SiteMap> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the site maps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SiteMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of site maps
	 * @param end the upper bound of the range of site maps (not inclusive)
	 * @return the range of site maps
	 */
	@Override
	public List<SiteMap> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the site maps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SiteMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of site maps
	 * @param end the upper bound of the range of site maps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of site maps
	 */
	@Override
	public List<SiteMap> findAll(int start, int end,
		OrderByComparator<SiteMap> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the site maps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SiteMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of site maps
	 * @param end the upper bound of the range of site maps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of site maps
	 */
	@Override
	public List<SiteMap> findAll(int start, int end,
		OrderByComparator<SiteMap> orderByComparator, boolean retrieveFromCache) {
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

		List<SiteMap> list = null;

		if (retrieveFromCache) {
			list = (List<SiteMap>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SITEMAP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SITEMAP;

				if (pagination) {
					sql = sql.concat(SiteMapModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SiteMap>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SiteMap>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the site maps from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SiteMap siteMap : findAll()) {
			remove(siteMap);
		}
	}

	/**
	 * Returns the number of site maps.
	 *
	 * @return the number of site maps
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SITEMAP);

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
		return SiteMapModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the site map persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SiteMapImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SITEMAP = "SELECT siteMap FROM SiteMap siteMap";
	private static final String _SQL_SELECT_SITEMAP_WHERE_PKS_IN = "SELECT siteMap FROM SiteMap siteMap WHERE siteMapId IN (";
	private static final String _SQL_SELECT_SITEMAP_WHERE = "SELECT siteMap FROM SiteMap siteMap WHERE ";
	private static final String _SQL_COUNT_SITEMAP = "SELECT COUNT(siteMap) FROM SiteMap siteMap";
	private static final String _SQL_COUNT_SITEMAP_WHERE = "SELECT COUNT(siteMap) FROM SiteMap siteMap WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "siteMap.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SiteMap exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SiteMap exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SiteMapPersistenceImpl.class);
}