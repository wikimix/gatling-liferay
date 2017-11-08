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

import io.gatling.liferay.exception.NoSuchUrlRecordException;
import io.gatling.liferay.model.UrlRecord;
import io.gatling.liferay.model.impl.UrlRecordImpl;
import io.gatling.liferay.model.impl.UrlRecordModelImpl;
import io.gatling.liferay.service.persistence.UrlRecordPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the url record service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UrlRecordPersistence
 * @see io.gatling.liferay.service.persistence.UrlRecordUtil
 * @generated
 */
@ProviderType
public class UrlRecordPersistenceImpl extends BasePersistenceImpl<UrlRecord>
	implements UrlRecordPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UrlRecordUtil} to access the url record persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UrlRecordImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
			UrlRecordModelImpl.FINDER_CACHE_ENABLED, UrlRecordImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
			UrlRecordModelImpl.FINDER_CACHE_ENABLED, UrlRecordImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
			UrlRecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RECORDID = new FinderPath(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
			UrlRecordModelImpl.FINDER_CACHE_ENABLED, UrlRecordImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRecordId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECORDID =
		new FinderPath(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
			UrlRecordModelImpl.FINDER_CACHE_ENABLED, UrlRecordImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRecordId",
			new String[] { Long.class.getName() },
			UrlRecordModelImpl.RECORDID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RECORDID = new FinderPath(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
			UrlRecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRecordId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the url records where recordId = &#63;.
	 *
	 * @param recordId the record ID
	 * @return the matching url records
	 */
	@Override
	public List<UrlRecord> findByRecordId(long recordId) {
		return findByRecordId(recordId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the url records where recordId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UrlRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param recordId the record ID
	 * @param start the lower bound of the range of url records
	 * @param end the upper bound of the range of url records (not inclusive)
	 * @return the range of matching url records
	 */
	@Override
	public List<UrlRecord> findByRecordId(long recordId, int start, int end) {
		return findByRecordId(recordId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the url records where recordId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UrlRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param recordId the record ID
	 * @param start the lower bound of the range of url records
	 * @param end the upper bound of the range of url records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching url records
	 */
	@Override
	public List<UrlRecord> findByRecordId(long recordId, int start, int end,
		OrderByComparator<UrlRecord> orderByComparator) {
		return findByRecordId(recordId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the url records where recordId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UrlRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param recordId the record ID
	 * @param start the lower bound of the range of url records
	 * @param end the upper bound of the range of url records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching url records
	 */
	@Override
	public List<UrlRecord> findByRecordId(long recordId, int start, int end,
		OrderByComparator<UrlRecord> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECORDID;
			finderArgs = new Object[] { recordId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RECORDID;
			finderArgs = new Object[] { recordId, start, end, orderByComparator };
		}

		List<UrlRecord> list = null;

		if (retrieveFromCache) {
			list = (List<UrlRecord>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UrlRecord urlRecord : list) {
					if ((recordId != urlRecord.getRecordId())) {
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

			query.append(_SQL_SELECT_URLRECORD_WHERE);

			query.append(_FINDER_COLUMN_RECORDID_RECORDID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UrlRecordModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(recordId);

				if (!pagination) {
					list = (List<UrlRecord>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UrlRecord>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first url record in the ordered set where recordId = &#63;.
	 *
	 * @param recordId the record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching url record
	 * @throws NoSuchUrlRecordException if a matching url record could not be found
	 */
	@Override
	public UrlRecord findByRecordId_First(long recordId,
		OrderByComparator<UrlRecord> orderByComparator)
		throws NoSuchUrlRecordException {
		UrlRecord urlRecord = fetchByRecordId_First(recordId, orderByComparator);

		if (urlRecord != null) {
			return urlRecord;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("recordId=");
		msg.append(recordId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUrlRecordException(msg.toString());
	}

	/**
	 * Returns the first url record in the ordered set where recordId = &#63;.
	 *
	 * @param recordId the record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching url record, or <code>null</code> if a matching url record could not be found
	 */
	@Override
	public UrlRecord fetchByRecordId_First(long recordId,
		OrderByComparator<UrlRecord> orderByComparator) {
		List<UrlRecord> list = findByRecordId(recordId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last url record in the ordered set where recordId = &#63;.
	 *
	 * @param recordId the record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching url record
	 * @throws NoSuchUrlRecordException if a matching url record could not be found
	 */
	@Override
	public UrlRecord findByRecordId_Last(long recordId,
		OrderByComparator<UrlRecord> orderByComparator)
		throws NoSuchUrlRecordException {
		UrlRecord urlRecord = fetchByRecordId_Last(recordId, orderByComparator);

		if (urlRecord != null) {
			return urlRecord;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("recordId=");
		msg.append(recordId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUrlRecordException(msg.toString());
	}

	/**
	 * Returns the last url record in the ordered set where recordId = &#63;.
	 *
	 * @param recordId the record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching url record, or <code>null</code> if a matching url record could not be found
	 */
	@Override
	public UrlRecord fetchByRecordId_Last(long recordId,
		OrderByComparator<UrlRecord> orderByComparator) {
		int count = countByRecordId(recordId);

		if (count == 0) {
			return null;
		}

		List<UrlRecord> list = findByRecordId(recordId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the url records before and after the current url record in the ordered set where recordId = &#63;.
	 *
	 * @param urlRecordId the primary key of the current url record
	 * @param recordId the record ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next url record
	 * @throws NoSuchUrlRecordException if a url record with the primary key could not be found
	 */
	@Override
	public UrlRecord[] findByRecordId_PrevAndNext(long urlRecordId,
		long recordId, OrderByComparator<UrlRecord> orderByComparator)
		throws NoSuchUrlRecordException {
		UrlRecord urlRecord = findByPrimaryKey(urlRecordId);

		Session session = null;

		try {
			session = openSession();

			UrlRecord[] array = new UrlRecordImpl[3];

			array[0] = getByRecordId_PrevAndNext(session, urlRecord, recordId,
					orderByComparator, true);

			array[1] = urlRecord;

			array[2] = getByRecordId_PrevAndNext(session, urlRecord, recordId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UrlRecord getByRecordId_PrevAndNext(Session session,
		UrlRecord urlRecord, long recordId,
		OrderByComparator<UrlRecord> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_URLRECORD_WHERE);

		query.append(_FINDER_COLUMN_RECORDID_RECORDID_2);

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
			query.append(UrlRecordModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(recordId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(urlRecord);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UrlRecord> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the url records where recordId = &#63; from the database.
	 *
	 * @param recordId the record ID
	 */
	@Override
	public void removeByRecordId(long recordId) {
		for (UrlRecord urlRecord : findByRecordId(recordId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(urlRecord);
		}
	}

	/**
	 * Returns the number of url records where recordId = &#63;.
	 *
	 * @param recordId the record ID
	 * @return the number of matching url records
	 */
	@Override
	public int countByRecordId(long recordId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RECORDID;

		Object[] finderArgs = new Object[] { recordId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_URLRECORD_WHERE);

			query.append(_FINDER_COLUMN_RECORDID_RECORDID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(recordId);

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

	private static final String _FINDER_COLUMN_RECORDID_RECORDID_2 = "urlRecord.recordId = ?";

	public UrlRecordPersistenceImpl() {
		setModelClass(UrlRecord.class);
	}

	/**
	 * Caches the url record in the entity cache if it is enabled.
	 *
	 * @param urlRecord the url record
	 */
	@Override
	public void cacheResult(UrlRecord urlRecord) {
		entityCache.putResult(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
			UrlRecordImpl.class, urlRecord.getPrimaryKey(), urlRecord);

		urlRecord.resetOriginalValues();
	}

	/**
	 * Caches the url records in the entity cache if it is enabled.
	 *
	 * @param urlRecords the url records
	 */
	@Override
	public void cacheResult(List<UrlRecord> urlRecords) {
		for (UrlRecord urlRecord : urlRecords) {
			if (entityCache.getResult(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
						UrlRecordImpl.class, urlRecord.getPrimaryKey()) == null) {
				cacheResult(urlRecord);
			}
			else {
				urlRecord.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all url records.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UrlRecordImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the url record.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UrlRecord urlRecord) {
		entityCache.removeResult(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
			UrlRecordImpl.class, urlRecord.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<UrlRecord> urlRecords) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UrlRecord urlRecord : urlRecords) {
			entityCache.removeResult(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
				UrlRecordImpl.class, urlRecord.getPrimaryKey());
		}
	}

	/**
	 * Creates a new url record with the primary key. Does not add the url record to the database.
	 *
	 * @param urlRecordId the primary key for the new url record
	 * @return the new url record
	 */
	@Override
	public UrlRecord create(long urlRecordId) {
		UrlRecord urlRecord = new UrlRecordImpl();

		urlRecord.setNew(true);
		urlRecord.setPrimaryKey(urlRecordId);

		return urlRecord;
	}

	/**
	 * Removes the url record with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param urlRecordId the primary key of the url record
	 * @return the url record that was removed
	 * @throws NoSuchUrlRecordException if a url record with the primary key could not be found
	 */
	@Override
	public UrlRecord remove(long urlRecordId) throws NoSuchUrlRecordException {
		return remove((Serializable)urlRecordId);
	}

	/**
	 * Removes the url record with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the url record
	 * @return the url record that was removed
	 * @throws NoSuchUrlRecordException if a url record with the primary key could not be found
	 */
	@Override
	public UrlRecord remove(Serializable primaryKey)
		throws NoSuchUrlRecordException {
		Session session = null;

		try {
			session = openSession();

			UrlRecord urlRecord = (UrlRecord)session.get(UrlRecordImpl.class,
					primaryKey);

			if (urlRecord == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUrlRecordException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(urlRecord);
		}
		catch (NoSuchUrlRecordException nsee) {
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
	protected UrlRecord removeImpl(UrlRecord urlRecord) {
		urlRecord = toUnwrappedModel(urlRecord);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(urlRecord)) {
				urlRecord = (UrlRecord)session.get(UrlRecordImpl.class,
						urlRecord.getPrimaryKeyObj());
			}

			if (urlRecord != null) {
				session.delete(urlRecord);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (urlRecord != null) {
			clearCache(urlRecord);
		}

		return urlRecord;
	}

	@Override
	public UrlRecord updateImpl(UrlRecord urlRecord) {
		urlRecord = toUnwrappedModel(urlRecord);

		boolean isNew = urlRecord.isNew();

		UrlRecordModelImpl urlRecordModelImpl = (UrlRecordModelImpl)urlRecord;

		Session session = null;

		try {
			session = openSession();

			if (urlRecord.isNew()) {
				session.save(urlRecord);

				urlRecord.setNew(false);
			}
			else {
				urlRecord = (UrlRecord)session.merge(urlRecord);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !UrlRecordModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((urlRecordModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECORDID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						urlRecordModelImpl.getOriginalRecordId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RECORDID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECORDID,
					args);

				args = new Object[] { urlRecordModelImpl.getRecordId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RECORDID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECORDID,
					args);
			}
		}

		entityCache.putResult(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
			UrlRecordImpl.class, urlRecord.getPrimaryKey(), urlRecord, false);

		urlRecord.resetOriginalValues();

		return urlRecord;
	}

	protected UrlRecord toUnwrappedModel(UrlRecord urlRecord) {
		if (urlRecord instanceof UrlRecordImpl) {
			return urlRecord;
		}

		UrlRecordImpl urlRecordImpl = new UrlRecordImpl();

		urlRecordImpl.setNew(urlRecord.isNew());
		urlRecordImpl.setPrimaryKey(urlRecord.getPrimaryKey());

		urlRecordImpl.setUrlRecordId(urlRecord.getUrlRecordId());
		urlRecordImpl.setRecordId(urlRecord.getRecordId());
		urlRecordImpl.setUrl(urlRecord.getUrl());
		urlRecordImpl.setType(urlRecord.getType());
		urlRecordImpl.setOrder(urlRecord.getOrder());
		urlRecordImpl.setPauseTime(urlRecord.getPauseTime());

		return urlRecordImpl;
	}

	/**
	 * Returns the url record with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the url record
	 * @return the url record
	 * @throws NoSuchUrlRecordException if a url record with the primary key could not be found
	 */
	@Override
	public UrlRecord findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUrlRecordException {
		UrlRecord urlRecord = fetchByPrimaryKey(primaryKey);

		if (urlRecord == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUrlRecordException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return urlRecord;
	}

	/**
	 * Returns the url record with the primary key or throws a {@link NoSuchUrlRecordException} if it could not be found.
	 *
	 * @param urlRecordId the primary key of the url record
	 * @return the url record
	 * @throws NoSuchUrlRecordException if a url record with the primary key could not be found
	 */
	@Override
	public UrlRecord findByPrimaryKey(long urlRecordId)
		throws NoSuchUrlRecordException {
		return findByPrimaryKey((Serializable)urlRecordId);
	}

	/**
	 * Returns the url record with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the url record
	 * @return the url record, or <code>null</code> if a url record with the primary key could not be found
	 */
	@Override
	public UrlRecord fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
				UrlRecordImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		UrlRecord urlRecord = (UrlRecord)serializable;

		if (urlRecord == null) {
			Session session = null;

			try {
				session = openSession();

				urlRecord = (UrlRecord)session.get(UrlRecordImpl.class,
						primaryKey);

				if (urlRecord != null) {
					cacheResult(urlRecord);
				}
				else {
					entityCache.putResult(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
						UrlRecordImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
					UrlRecordImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return urlRecord;
	}

	/**
	 * Returns the url record with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param urlRecordId the primary key of the url record
	 * @return the url record, or <code>null</code> if a url record with the primary key could not be found
	 */
	@Override
	public UrlRecord fetchByPrimaryKey(long urlRecordId) {
		return fetchByPrimaryKey((Serializable)urlRecordId);
	}

	@Override
	public Map<Serializable, UrlRecord> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, UrlRecord> map = new HashMap<Serializable, UrlRecord>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			UrlRecord urlRecord = fetchByPrimaryKey(primaryKey);

			if (urlRecord != null) {
				map.put(primaryKey, urlRecord);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
					UrlRecordImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (UrlRecord)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_URLRECORD_WHERE_PKS_IN);

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

			for (UrlRecord urlRecord : (List<UrlRecord>)q.list()) {
				map.put(urlRecord.getPrimaryKeyObj(), urlRecord);

				cacheResult(urlRecord);

				uncachedPrimaryKeys.remove(urlRecord.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(UrlRecordModelImpl.ENTITY_CACHE_ENABLED,
					UrlRecordImpl.class, primaryKey, nullModel);
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
	 * Returns all the url records.
	 *
	 * @return the url records
	 */
	@Override
	public List<UrlRecord> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the url records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UrlRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of url records
	 * @param end the upper bound of the range of url records (not inclusive)
	 * @return the range of url records
	 */
	@Override
	public List<UrlRecord> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the url records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UrlRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of url records
	 * @param end the upper bound of the range of url records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of url records
	 */
	@Override
	public List<UrlRecord> findAll(int start, int end,
		OrderByComparator<UrlRecord> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the url records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UrlRecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of url records
	 * @param end the upper bound of the range of url records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of url records
	 */
	@Override
	public List<UrlRecord> findAll(int start, int end,
		OrderByComparator<UrlRecord> orderByComparator,
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

		List<UrlRecord> list = null;

		if (retrieveFromCache) {
			list = (List<UrlRecord>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_URLRECORD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_URLRECORD;

				if (pagination) {
					sql = sql.concat(UrlRecordModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UrlRecord>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UrlRecord>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the url records from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UrlRecord urlRecord : findAll()) {
			remove(urlRecord);
		}
	}

	/**
	 * Returns the number of url records.
	 *
	 * @return the number of url records
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_URLRECORD);

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
		return UrlRecordModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the url record persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(UrlRecordImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_URLRECORD = "SELECT urlRecord FROM UrlRecord urlRecord";
	private static final String _SQL_SELECT_URLRECORD_WHERE_PKS_IN = "SELECT urlRecord FROM UrlRecord urlRecord WHERE urlRecordId IN (";
	private static final String _SQL_SELECT_URLRECORD_WHERE = "SELECT urlRecord FROM UrlRecord urlRecord WHERE ";
	private static final String _SQL_COUNT_URLRECORD = "SELECT COUNT(urlRecord) FROM UrlRecord urlRecord";
	private static final String _SQL_COUNT_URLRECORD_WHERE = "SELECT COUNT(urlRecord) FROM UrlRecord urlRecord WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "urlRecord.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UrlRecord exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UrlRecord exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(UrlRecordPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type", "order"
			});
}