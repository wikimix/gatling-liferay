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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import io.gatling.liferay.exception.NoSuchUrlSiteMapException;
import io.gatling.liferay.model.UrlSiteMap;
import io.gatling.liferay.model.impl.UrlSiteMapImpl;
import io.gatling.liferay.model.impl.UrlSiteMapModelImpl;
import io.gatling.liferay.service.persistence.UrlSiteMapPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the url site map service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UrlSiteMapPersistence
 * @see io.gatling.liferay.service.persistence.UrlSiteMapUtil
 * @generated
 */
@ProviderType
public class UrlSiteMapPersistenceImpl extends BasePersistenceImpl<UrlSiteMap>
	implements UrlSiteMapPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UrlSiteMapUtil} to access the url site map persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UrlSiteMapImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
			UrlSiteMapModelImpl.FINDER_CACHE_ENABLED, UrlSiteMapImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
			UrlSiteMapModelImpl.FINDER_CACHE_ENABLED, UrlSiteMapImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
			UrlSiteMapModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SITEMAPID =
		new FinderPath(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
			UrlSiteMapModelImpl.FINDER_CACHE_ENABLED, UrlSiteMapImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySiteMapId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SITEMAPID =
		new FinderPath(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
			UrlSiteMapModelImpl.FINDER_CACHE_ENABLED, UrlSiteMapImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySiteMapId",
			new String[] { Long.class.getName() },
			UrlSiteMapModelImpl.SITEMAPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SITEMAPID = new FinderPath(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
			UrlSiteMapModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySiteMapId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the url site maps where siteMapId = &#63;.
	 *
	 * @param siteMapId the site map ID
	 * @return the matching url site maps
	 */
	@Override
	public List<UrlSiteMap> findBySiteMapId(long siteMapId) {
		return findBySiteMapId(siteMapId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the url site maps where siteMapId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UrlSiteMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param siteMapId the site map ID
	 * @param start the lower bound of the range of url site maps
	 * @param end the upper bound of the range of url site maps (not inclusive)
	 * @return the range of matching url site maps
	 */
	@Override
	public List<UrlSiteMap> findBySiteMapId(long siteMapId, int start, int end) {
		return findBySiteMapId(siteMapId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the url site maps where siteMapId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UrlSiteMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param siteMapId the site map ID
	 * @param start the lower bound of the range of url site maps
	 * @param end the upper bound of the range of url site maps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching url site maps
	 */
	@Override
	public List<UrlSiteMap> findBySiteMapId(long siteMapId, int start, int end,
		OrderByComparator<UrlSiteMap> orderByComparator) {
		return findBySiteMapId(siteMapId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the url site maps where siteMapId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UrlSiteMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param siteMapId the site map ID
	 * @param start the lower bound of the range of url site maps
	 * @param end the upper bound of the range of url site maps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching url site maps
	 */
	@Override
	public List<UrlSiteMap> findBySiteMapId(long siteMapId, int start, int end,
		OrderByComparator<UrlSiteMap> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SITEMAPID;
			finderArgs = new Object[] { siteMapId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SITEMAPID;
			finderArgs = new Object[] { siteMapId, start, end, orderByComparator };
		}

		List<UrlSiteMap> list = null;

		if (retrieveFromCache) {
			list = (List<UrlSiteMap>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UrlSiteMap urlSiteMap : list) {
					if ((siteMapId != urlSiteMap.getSiteMapId())) {
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

			query.append(_SQL_SELECT_URLSITEMAP_WHERE);

			query.append(_FINDER_COLUMN_SITEMAPID_SITEMAPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UrlSiteMapModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(siteMapId);

				if (!pagination) {
					list = (List<UrlSiteMap>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UrlSiteMap>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first url site map in the ordered set where siteMapId = &#63;.
	 *
	 * @param siteMapId the site map ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching url site map
	 * @throws NoSuchUrlSiteMapException if a matching url site map could not be found
	 */
	@Override
	public UrlSiteMap findBySiteMapId_First(long siteMapId,
		OrderByComparator<UrlSiteMap> orderByComparator)
		throws NoSuchUrlSiteMapException {
		UrlSiteMap urlSiteMap = fetchBySiteMapId_First(siteMapId,
				orderByComparator);

		if (urlSiteMap != null) {
			return urlSiteMap;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("siteMapId=");
		msg.append(siteMapId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUrlSiteMapException(msg.toString());
	}

	/**
	 * Returns the first url site map in the ordered set where siteMapId = &#63;.
	 *
	 * @param siteMapId the site map ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching url site map, or <code>null</code> if a matching url site map could not be found
	 */
	@Override
	public UrlSiteMap fetchBySiteMapId_First(long siteMapId,
		OrderByComparator<UrlSiteMap> orderByComparator) {
		List<UrlSiteMap> list = findBySiteMapId(siteMapId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last url site map in the ordered set where siteMapId = &#63;.
	 *
	 * @param siteMapId the site map ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching url site map
	 * @throws NoSuchUrlSiteMapException if a matching url site map could not be found
	 */
	@Override
	public UrlSiteMap findBySiteMapId_Last(long siteMapId,
		OrderByComparator<UrlSiteMap> orderByComparator)
		throws NoSuchUrlSiteMapException {
		UrlSiteMap urlSiteMap = fetchBySiteMapId_Last(siteMapId,
				orderByComparator);

		if (urlSiteMap != null) {
			return urlSiteMap;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("siteMapId=");
		msg.append(siteMapId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUrlSiteMapException(msg.toString());
	}

	/**
	 * Returns the last url site map in the ordered set where siteMapId = &#63;.
	 *
	 * @param siteMapId the site map ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching url site map, or <code>null</code> if a matching url site map could not be found
	 */
	@Override
	public UrlSiteMap fetchBySiteMapId_Last(long siteMapId,
		OrderByComparator<UrlSiteMap> orderByComparator) {
		int count = countBySiteMapId(siteMapId);

		if (count == 0) {
			return null;
		}

		List<UrlSiteMap> list = findBySiteMapId(siteMapId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the url site maps before and after the current url site map in the ordered set where siteMapId = &#63;.
	 *
	 * @param urlSiteMapId the primary key of the current url site map
	 * @param siteMapId the site map ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next url site map
	 * @throws NoSuchUrlSiteMapException if a url site map with the primary key could not be found
	 */
	@Override
	public UrlSiteMap[] findBySiteMapId_PrevAndNext(long urlSiteMapId,
		long siteMapId, OrderByComparator<UrlSiteMap> orderByComparator)
		throws NoSuchUrlSiteMapException {
		UrlSiteMap urlSiteMap = findByPrimaryKey(urlSiteMapId);

		Session session = null;

		try {
			session = openSession();

			UrlSiteMap[] array = new UrlSiteMapImpl[3];

			array[0] = getBySiteMapId_PrevAndNext(session, urlSiteMap,
					siteMapId, orderByComparator, true);

			array[1] = urlSiteMap;

			array[2] = getBySiteMapId_PrevAndNext(session, urlSiteMap,
					siteMapId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UrlSiteMap getBySiteMapId_PrevAndNext(Session session,
		UrlSiteMap urlSiteMap, long siteMapId,
		OrderByComparator<UrlSiteMap> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_URLSITEMAP_WHERE);

		query.append(_FINDER_COLUMN_SITEMAPID_SITEMAPID_2);

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
			query.append(UrlSiteMapModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(siteMapId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(urlSiteMap);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UrlSiteMap> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the url site maps where siteMapId = &#63; from the database.
	 *
	 * @param siteMapId the site map ID
	 */
	@Override
	public void removeBySiteMapId(long siteMapId) {
		for (UrlSiteMap urlSiteMap : findBySiteMapId(siteMapId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(urlSiteMap);
		}
	}

	/**
	 * Returns the number of url site maps where siteMapId = &#63;.
	 *
	 * @param siteMapId the site map ID
	 * @return the number of matching url site maps
	 */
	@Override
	public int countBySiteMapId(long siteMapId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SITEMAPID;

		Object[] finderArgs = new Object[] { siteMapId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_URLSITEMAP_WHERE);

			query.append(_FINDER_COLUMN_SITEMAPID_SITEMAPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(siteMapId);

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

	private static final String _FINDER_COLUMN_SITEMAPID_SITEMAPID_2 = "urlSiteMap.siteMapId = ?";

	public UrlSiteMapPersistenceImpl() {
		setModelClass(UrlSiteMap.class);
	}

	/**
	 * Caches the url site map in the entity cache if it is enabled.
	 *
	 * @param urlSiteMap the url site map
	 */
	@Override
	public void cacheResult(UrlSiteMap urlSiteMap) {
		entityCache.putResult(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
			UrlSiteMapImpl.class, urlSiteMap.getPrimaryKey(), urlSiteMap);

		urlSiteMap.resetOriginalValues();
	}

	/**
	 * Caches the url site maps in the entity cache if it is enabled.
	 *
	 * @param urlSiteMaps the url site maps
	 */
	@Override
	public void cacheResult(List<UrlSiteMap> urlSiteMaps) {
		for (UrlSiteMap urlSiteMap : urlSiteMaps) {
			if (entityCache.getResult(
						UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
						UrlSiteMapImpl.class, urlSiteMap.getPrimaryKey()) == null) {
				cacheResult(urlSiteMap);
			}
			else {
				urlSiteMap.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all url site maps.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UrlSiteMapImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the url site map.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UrlSiteMap urlSiteMap) {
		entityCache.removeResult(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
			UrlSiteMapImpl.class, urlSiteMap.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<UrlSiteMap> urlSiteMaps) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UrlSiteMap urlSiteMap : urlSiteMaps) {
			entityCache.removeResult(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
				UrlSiteMapImpl.class, urlSiteMap.getPrimaryKey());
		}
	}

	/**
	 * Creates a new url site map with the primary key. Does not add the url site map to the database.
	 *
	 * @param urlSiteMapId the primary key for the new url site map
	 * @return the new url site map
	 */
	@Override
	public UrlSiteMap create(long urlSiteMapId) {
		UrlSiteMap urlSiteMap = new UrlSiteMapImpl();

		urlSiteMap.setNew(true);
		urlSiteMap.setPrimaryKey(urlSiteMapId);

		return urlSiteMap;
	}

	/**
	 * Removes the url site map with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param urlSiteMapId the primary key of the url site map
	 * @return the url site map that was removed
	 * @throws NoSuchUrlSiteMapException if a url site map with the primary key could not be found
	 */
	@Override
	public UrlSiteMap remove(long urlSiteMapId)
		throws NoSuchUrlSiteMapException {
		return remove((Serializable)urlSiteMapId);
	}

	/**
	 * Removes the url site map with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the url site map
	 * @return the url site map that was removed
	 * @throws NoSuchUrlSiteMapException if a url site map with the primary key could not be found
	 */
	@Override
	public UrlSiteMap remove(Serializable primaryKey)
		throws NoSuchUrlSiteMapException {
		Session session = null;

		try {
			session = openSession();

			UrlSiteMap urlSiteMap = (UrlSiteMap)session.get(UrlSiteMapImpl.class,
					primaryKey);

			if (urlSiteMap == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUrlSiteMapException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(urlSiteMap);
		}
		catch (NoSuchUrlSiteMapException nsee) {
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
	protected UrlSiteMap removeImpl(UrlSiteMap urlSiteMap) {
		urlSiteMap = toUnwrappedModel(urlSiteMap);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(urlSiteMap)) {
				urlSiteMap = (UrlSiteMap)session.get(UrlSiteMapImpl.class,
						urlSiteMap.getPrimaryKeyObj());
			}

			if (urlSiteMap != null) {
				session.delete(urlSiteMap);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (urlSiteMap != null) {
			clearCache(urlSiteMap);
		}

		return urlSiteMap;
	}

	@Override
	public UrlSiteMap updateImpl(UrlSiteMap urlSiteMap) {
		urlSiteMap = toUnwrappedModel(urlSiteMap);

		boolean isNew = urlSiteMap.isNew();

		UrlSiteMapModelImpl urlSiteMapModelImpl = (UrlSiteMapModelImpl)urlSiteMap;

		Session session = null;

		try {
			session = openSession();

			if (urlSiteMap.isNew()) {
				session.save(urlSiteMap);

				urlSiteMap.setNew(false);
			}
			else {
				urlSiteMap = (UrlSiteMap)session.merge(urlSiteMap);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !UrlSiteMapModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((urlSiteMapModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SITEMAPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						urlSiteMapModelImpl.getOriginalSiteMapId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SITEMAPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SITEMAPID,
					args);

				args = new Object[] { urlSiteMapModelImpl.getSiteMapId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SITEMAPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SITEMAPID,
					args);
			}
		}

		entityCache.putResult(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
			UrlSiteMapImpl.class, urlSiteMap.getPrimaryKey(), urlSiteMap, false);

		urlSiteMap.resetOriginalValues();

		return urlSiteMap;
	}

	protected UrlSiteMap toUnwrappedModel(UrlSiteMap urlSiteMap) {
		if (urlSiteMap instanceof UrlSiteMapImpl) {
			return urlSiteMap;
		}

		UrlSiteMapImpl urlSiteMapImpl = new UrlSiteMapImpl();

		urlSiteMapImpl.setNew(urlSiteMap.isNew());
		urlSiteMapImpl.setPrimaryKey(urlSiteMap.getPrimaryKey());

		urlSiteMapImpl.setUrlSiteMapId(urlSiteMap.getUrlSiteMapId());
		urlSiteMapImpl.setSiteMapId(urlSiteMap.getSiteMapId());
		urlSiteMapImpl.setGroup(urlSiteMap.getGroup());
		urlSiteMapImpl.setFriendlyUrl(urlSiteMap.getFriendlyUrl());
		urlSiteMapImpl.setUrl(urlSiteMap.getUrl());
		urlSiteMapImpl.setWeight(urlSiteMap.getWeight());

		return urlSiteMapImpl;
	}

	/**
	 * Returns the url site map with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the url site map
	 * @return the url site map
	 * @throws NoSuchUrlSiteMapException if a url site map with the primary key could not be found
	 */
	@Override
	public UrlSiteMap findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUrlSiteMapException {
		UrlSiteMap urlSiteMap = fetchByPrimaryKey(primaryKey);

		if (urlSiteMap == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUrlSiteMapException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return urlSiteMap;
	}

	/**
	 * Returns the url site map with the primary key or throws a {@link NoSuchUrlSiteMapException} if it could not be found.
	 *
	 * @param urlSiteMapId the primary key of the url site map
	 * @return the url site map
	 * @throws NoSuchUrlSiteMapException if a url site map with the primary key could not be found
	 */
	@Override
	public UrlSiteMap findByPrimaryKey(long urlSiteMapId)
		throws NoSuchUrlSiteMapException {
		return findByPrimaryKey((Serializable)urlSiteMapId);
	}

	/**
	 * Returns the url site map with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the url site map
	 * @return the url site map, or <code>null</code> if a url site map with the primary key could not be found
	 */
	@Override
	public UrlSiteMap fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
				UrlSiteMapImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		UrlSiteMap urlSiteMap = (UrlSiteMap)serializable;

		if (urlSiteMap == null) {
			Session session = null;

			try {
				session = openSession();

				urlSiteMap = (UrlSiteMap)session.get(UrlSiteMapImpl.class,
						primaryKey);

				if (urlSiteMap != null) {
					cacheResult(urlSiteMap);
				}
				else {
					entityCache.putResult(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
						UrlSiteMapImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
					UrlSiteMapImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return urlSiteMap;
	}

	/**
	 * Returns the url site map with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param urlSiteMapId the primary key of the url site map
	 * @return the url site map, or <code>null</code> if a url site map with the primary key could not be found
	 */
	@Override
	public UrlSiteMap fetchByPrimaryKey(long urlSiteMapId) {
		return fetchByPrimaryKey((Serializable)urlSiteMapId);
	}

	@Override
	public Map<Serializable, UrlSiteMap> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, UrlSiteMap> map = new HashMap<Serializable, UrlSiteMap>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			UrlSiteMap urlSiteMap = fetchByPrimaryKey(primaryKey);

			if (urlSiteMap != null) {
				map.put(primaryKey, urlSiteMap);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
					UrlSiteMapImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (UrlSiteMap)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_URLSITEMAP_WHERE_PKS_IN);

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

			for (UrlSiteMap urlSiteMap : (List<UrlSiteMap>)q.list()) {
				map.put(urlSiteMap.getPrimaryKeyObj(), urlSiteMap);

				cacheResult(urlSiteMap);

				uncachedPrimaryKeys.remove(urlSiteMap.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(UrlSiteMapModelImpl.ENTITY_CACHE_ENABLED,
					UrlSiteMapImpl.class, primaryKey, nullModel);
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
	 * Returns all the url site maps.
	 *
	 * @return the url site maps
	 */
	@Override
	public List<UrlSiteMap> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the url site maps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UrlSiteMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of url site maps
	 * @param end the upper bound of the range of url site maps (not inclusive)
	 * @return the range of url site maps
	 */
	@Override
	public List<UrlSiteMap> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the url site maps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UrlSiteMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of url site maps
	 * @param end the upper bound of the range of url site maps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of url site maps
	 */
	@Override
	public List<UrlSiteMap> findAll(int start, int end,
		OrderByComparator<UrlSiteMap> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the url site maps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UrlSiteMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of url site maps
	 * @param end the upper bound of the range of url site maps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of url site maps
	 */
	@Override
	public List<UrlSiteMap> findAll(int start, int end,
		OrderByComparator<UrlSiteMap> orderByComparator,
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

		List<UrlSiteMap> list = null;

		if (retrieveFromCache) {
			list = (List<UrlSiteMap>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_URLSITEMAP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_URLSITEMAP;

				if (pagination) {
					sql = sql.concat(UrlSiteMapModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UrlSiteMap>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UrlSiteMap>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the url site maps from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UrlSiteMap urlSiteMap : findAll()) {
			remove(urlSiteMap);
		}
	}

	/**
	 * Returns the number of url site maps.
	 *
	 * @return the number of url site maps
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_URLSITEMAP);

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
		return UrlSiteMapModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the url site map persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(UrlSiteMapImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_URLSITEMAP = "SELECT urlSiteMap FROM UrlSiteMap urlSiteMap";
	private static final String _SQL_SELECT_URLSITEMAP_WHERE_PKS_IN = "SELECT urlSiteMap FROM UrlSiteMap urlSiteMap WHERE urlSiteMapId IN (";
	private static final String _SQL_SELECT_URLSITEMAP_WHERE = "SELECT urlSiteMap FROM UrlSiteMap urlSiteMap WHERE ";
	private static final String _SQL_COUNT_URLSITEMAP = "SELECT COUNT(urlSiteMap) FROM UrlSiteMap urlSiteMap";
	private static final String _SQL_COUNT_URLSITEMAP_WHERE = "SELECT COUNT(urlSiteMap) FROM UrlSiteMap urlSiteMap WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "urlSiteMap.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UrlSiteMap exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UrlSiteMap exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(UrlSiteMapPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"group"
			});
}