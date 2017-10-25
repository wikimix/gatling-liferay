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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import io.gatling.liferay.exception.NoSuchRecordException;
import io.gatling.liferay.model.Record;
import io.gatling.liferay.model.impl.RecordImpl;
import io.gatling.liferay.model.impl.RecordModelImpl;
import io.gatling.liferay.service.persistence.RecordPersistence;

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
 * The persistence implementation for the record service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecordPersistence
 * @see io.gatling.liferay.service.persistence.RecordUtil
 * @generated
 */
@ProviderType
public class RecordPersistenceImpl extends BasePersistenceImpl<Record>
	implements RecordPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RecordUtil} to access the record persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RecordImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RecordModelImpl.ENTITY_CACHE_ENABLED,
			RecordModelImpl.FINDER_CACHE_ENABLED, RecordImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RecordModelImpl.ENTITY_CACHE_ENABLED,
			RecordModelImpl.FINDER_CACHE_ENABLED, RecordImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RecordModelImpl.ENTITY_CACHE_ENABLED,
			RecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PORTLETID =
		new FinderPath(RecordModelImpl.ENTITY_CACHE_ENABLED,
			RecordModelImpl.FINDER_CACHE_ENABLED, RecordImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPortletId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID =
		new FinderPath(RecordModelImpl.ENTITY_CACHE_ENABLED,
			RecordModelImpl.FINDER_CACHE_ENABLED, RecordImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPortletId",
			new String[] { String.class.getName() },
			RecordModelImpl.PORTLETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PORTLETID = new FinderPath(RecordModelImpl.ENTITY_CACHE_ENABLED,
			RecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPortletId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the records where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @return the matching records
	 */
	@Override
	public List<Record> findByPortletId(String portletId) {
		return findByPortletId(portletId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the records where portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of records
	 * @param end the upper bound of the range of records (not inclusive)
	 * @return the range of matching records
	 */
	@Override
	public List<Record> findByPortletId(String portletId, int start, int end) {
		return findByPortletId(portletId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the records where portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of records
	 * @param end the upper bound of the range of records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching records
	 */
	@Override
	public List<Record> findByPortletId(String portletId, int start, int end,
		OrderByComparator<Record> orderByComparator) {
		return findByPortletId(portletId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the records where portletId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param portletId the portlet ID
	 * @param start the lower bound of the range of records
	 * @param end the upper bound of the range of records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching records
	 */
	@Override
	public List<Record> findByPortletId(String portletId, int start, int end,
		OrderByComparator<Record> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID;
			finderArgs = new Object[] { portletId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PORTLETID;
			finderArgs = new Object[] { portletId, start, end, orderByComparator };
		}

		List<Record> list = null;

		if (retrieveFromCache) {
			list = (List<Record>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Record record : list) {
					if (!Objects.equals(portletId, record.getPortletId())) {
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

			query.append(_SQL_SELECT_RECORD_WHERE);

			boolean bindPortletId = false;

			if (portletId == null) {
				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_1);
			}
			else if (portletId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RecordModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPortletId) {
					qPos.add(portletId);
				}

				if (!pagination) {
					list = (List<Record>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Record>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first record in the ordered set where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching record
	 * @throws NoSuchRecordException if a matching record could not be found
	 */
	@Override
	public Record findByPortletId_First(String portletId,
		OrderByComparator<Record> orderByComparator)
		throws NoSuchRecordException {
		Record record = fetchByPortletId_First(portletId, orderByComparator);

		if (record != null) {
			return record;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portletId=");
		msg.append(portletId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecordException(msg.toString());
	}

	/**
	 * Returns the first record in the ordered set where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching record, or <code>null</code> if a matching record could not be found
	 */
	@Override
	public Record fetchByPortletId_First(String portletId,
		OrderByComparator<Record> orderByComparator) {
		List<Record> list = findByPortletId(portletId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last record in the ordered set where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching record
	 * @throws NoSuchRecordException if a matching record could not be found
	 */
	@Override
	public Record findByPortletId_Last(String portletId,
		OrderByComparator<Record> orderByComparator)
		throws NoSuchRecordException {
		Record record = fetchByPortletId_Last(portletId, orderByComparator);

		if (record != null) {
			return record;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portletId=");
		msg.append(portletId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecordException(msg.toString());
	}

	/**
	 * Returns the last record in the ordered set where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching record, or <code>null</code> if a matching record could not be found
	 */
	@Override
	public Record fetchByPortletId_Last(String portletId,
		OrderByComparator<Record> orderByComparator) {
		int count = countByPortletId(portletId);

		if (count == 0) {
			return null;
		}

		List<Record> list = findByPortletId(portletId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the records before and after the current record in the ordered set where portletId = &#63;.
	 *
	 * @param recordId the primary key of the current record
	 * @param portletId the portlet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next record
	 * @throws NoSuchRecordException if a record with the primary key could not be found
	 */
	@Override
	public Record[] findByPortletId_PrevAndNext(long recordId,
		String portletId, OrderByComparator<Record> orderByComparator)
		throws NoSuchRecordException {
		Record record = findByPrimaryKey(recordId);

		Session session = null;

		try {
			session = openSession();

			Record[] array = new RecordImpl[3];

			array[0] = getByPortletId_PrevAndNext(session, record, portletId,
					orderByComparator, true);

			array[1] = record;

			array[2] = getByPortletId_PrevAndNext(session, record, portletId,
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

	protected Record getByPortletId_PrevAndNext(Session session, Record record,
		String portletId, OrderByComparator<Record> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RECORD_WHERE);

		boolean bindPortletId = false;

		if (portletId == null) {
			query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_1);
		}
		else if (portletId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_3);
		}
		else {
			bindPortletId = true;

			query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_2);
		}

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
			query.append(RecordModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPortletId) {
			qPos.add(portletId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(record);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Record> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the records where portletId = &#63; from the database.
	 *
	 * @param portletId the portlet ID
	 */
	@Override
	public void removeByPortletId(String portletId) {
		for (Record record : findByPortletId(portletId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(record);
		}
	}

	/**
	 * Returns the number of records where portletId = &#63;.
	 *
	 * @param portletId the portlet ID
	 * @return the number of matching records
	 */
	@Override
	public int countByPortletId(String portletId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PORTLETID;

		Object[] finderArgs = new Object[] { portletId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RECORD_WHERE);

			boolean bindPortletId = false;

			if (portletId == null) {
				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_1);
			}
			else if (portletId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_3);
			}
			else {
				bindPortletId = true;

				query.append(_FINDER_COLUMN_PORTLETID_PORTLETID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPortletId) {
					qPos.add(portletId);
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

	private static final String _FINDER_COLUMN_PORTLETID_PORTLETID_1 = "record.portletId IS NULL";
	private static final String _FINDER_COLUMN_PORTLETID_PORTLETID_2 = "record.portletId = ?";
	private static final String _FINDER_COLUMN_PORTLETID_PORTLETID_3 = "(record.portletId IS NULL OR record.portletId = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(RecordModelImpl.ENTITY_CACHE_ENABLED,
			RecordModelImpl.FINDER_CACHE_ENABLED, RecordImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			RecordModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(RecordModelImpl.ENTITY_CACHE_ENABLED,
			RecordModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the record where name = &#63; or throws a {@link NoSuchRecordException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching record
	 * @throws NoSuchRecordException if a matching record could not be found
	 */
	@Override
	public Record findByName(String name) throws NoSuchRecordException {
		Record record = fetchByName(name);

		if (record == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchRecordException(msg.toString());
		}

		return record;
	}

	/**
	 * Returns the record where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching record, or <code>null</code> if a matching record could not be found
	 */
	@Override
	public Record fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the record where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching record, or <code>null</code> if a matching record could not be found
	 */
	@Override
	public Record fetchByName(String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof Record) {
			Record record = (Record)result;

			if (!Objects.equals(name, record.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_RECORD_WHERE);

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

				List<Record> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					Record record = list.get(0);

					result = record;

					cacheResult(record);

					if ((record.getName() == null) ||
							!record.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, record);
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
			return (Record)result;
		}
	}

	/**
	 * Removes the record where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the record that was removed
	 */
	@Override
	public Record removeByName(String name) throws NoSuchRecordException {
		Record record = findByName(name);

		return remove(record);
	}

	/**
	 * Returns the number of records where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching records
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RECORD_WHERE);

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "record.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "record.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(record.name IS NULL OR record.name = '')";

	public RecordPersistenceImpl() {
		setModelClass(Record.class);
	}

	/**
	 * Caches the record in the entity cache if it is enabled.
	 *
	 * @param record the record
	 */
	@Override
	public void cacheResult(Record record) {
		entityCache.putResult(RecordModelImpl.ENTITY_CACHE_ENABLED,
			RecordImpl.class, record.getPrimaryKey(), record);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { record.getName() }, record);

		record.resetOriginalValues();
	}

	/**
	 * Caches the records in the entity cache if it is enabled.
	 *
	 * @param records the records
	 */
	@Override
	public void cacheResult(List<Record> records) {
		for (Record record : records) {
			if (entityCache.getResult(RecordModelImpl.ENTITY_CACHE_ENABLED,
						RecordImpl.class, record.getPrimaryKey()) == null) {
				cacheResult(record);
			}
			else {
				record.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all records.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RecordImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the record.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Record record) {
		entityCache.removeResult(RecordModelImpl.ENTITY_CACHE_ENABLED,
			RecordImpl.class, record.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((RecordModelImpl)record, true);
	}

	@Override
	public void clearCache(List<Record> records) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Record record : records) {
			entityCache.removeResult(RecordModelImpl.ENTITY_CACHE_ENABLED,
				RecordImpl.class, record.getPrimaryKey());

			clearUniqueFindersCache((RecordModelImpl)record, true);
		}
	}

	protected void cacheUniqueFindersCache(RecordModelImpl recordModelImpl) {
		Object[] args = new Object[] { recordModelImpl.getName() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAME, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME, args, recordModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(RecordModelImpl recordModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { recordModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}

		if ((recordModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { recordModelImpl.getOriginalName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new record with the primary key. Does not add the record to the database.
	 *
	 * @param recordId the primary key for the new record
	 * @return the new record
	 */
	@Override
	public Record create(long recordId) {
		Record record = new RecordImpl();

		record.setNew(true);
		record.setPrimaryKey(recordId);

		return record;
	}

	/**
	 * Removes the record with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param recordId the primary key of the record
	 * @return the record that was removed
	 * @throws NoSuchRecordException if a record with the primary key could not be found
	 */
	@Override
	public Record remove(long recordId) throws NoSuchRecordException {
		return remove((Serializable)recordId);
	}

	/**
	 * Removes the record with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the record
	 * @return the record that was removed
	 * @throws NoSuchRecordException if a record with the primary key could not be found
	 */
	@Override
	public Record remove(Serializable primaryKey) throws NoSuchRecordException {
		Session session = null;

		try {
			session = openSession();

			Record record = (Record)session.get(RecordImpl.class, primaryKey);

			if (record == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRecordException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(record);
		}
		catch (NoSuchRecordException nsee) {
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
	protected Record removeImpl(Record record) {
		record = toUnwrappedModel(record);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(record)) {
				record = (Record)session.get(RecordImpl.class,
						record.getPrimaryKeyObj());
			}

			if (record != null) {
				session.delete(record);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (record != null) {
			clearCache(record);
		}

		return record;
	}

	@Override
	public Record updateImpl(Record record) {
		record = toUnwrappedModel(record);

		boolean isNew = record.isNew();

		RecordModelImpl recordModelImpl = (RecordModelImpl)record;

		Session session = null;

		try {
			session = openSession();

			if (record.isNew()) {
				session.save(record);

				record.setNew(false);
			}
			else {
				record = (Record)session.merge(record);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !RecordModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((recordModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						recordModelImpl.getOriginalPortletId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PORTLETID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID,
					args);

				args = new Object[] { recordModelImpl.getPortletId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PORTLETID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PORTLETID,
					args);
			}
		}

		entityCache.putResult(RecordModelImpl.ENTITY_CACHE_ENABLED,
			RecordImpl.class, record.getPrimaryKey(), record, false);

		clearUniqueFindersCache(recordModelImpl, false);
		cacheUniqueFindersCache(recordModelImpl);

		record.resetOriginalValues();

		return record;
	}

	protected Record toUnwrappedModel(Record record) {
		if (record instanceof RecordImpl) {
			return record;
		}

		RecordImpl recordImpl = new RecordImpl();

		recordImpl.setNew(record.isNew());
		recordImpl.setPrimaryKey(record.getPrimaryKey());

		recordImpl.setRecordId(record.getRecordId());
		recordImpl.setPortletId(record.getPortletId());
		recordImpl.setVersionPortlet(record.getVersionPortlet());
		recordImpl.setName(record.getName());

		return recordImpl;
	}

	/**
	 * Returns the record with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the record
	 * @return the record
	 * @throws NoSuchRecordException if a record with the primary key could not be found
	 */
	@Override
	public Record findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRecordException {
		Record record = fetchByPrimaryKey(primaryKey);

		if (record == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRecordException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return record;
	}

	/**
	 * Returns the record with the primary key or throws a {@link NoSuchRecordException} if it could not be found.
	 *
	 * @param recordId the primary key of the record
	 * @return the record
	 * @throws NoSuchRecordException if a record with the primary key could not be found
	 */
	@Override
	public Record findByPrimaryKey(long recordId) throws NoSuchRecordException {
		return findByPrimaryKey((Serializable)recordId);
	}

	/**
	 * Returns the record with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the record
	 * @return the record, or <code>null</code> if a record with the primary key could not be found
	 */
	@Override
	public Record fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RecordModelImpl.ENTITY_CACHE_ENABLED,
				RecordImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Record record = (Record)serializable;

		if (record == null) {
			Session session = null;

			try {
				session = openSession();

				record = (Record)session.get(RecordImpl.class, primaryKey);

				if (record != null) {
					cacheResult(record);
				}
				else {
					entityCache.putResult(RecordModelImpl.ENTITY_CACHE_ENABLED,
						RecordImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RecordModelImpl.ENTITY_CACHE_ENABLED,
					RecordImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return record;
	}

	/**
	 * Returns the record with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param recordId the primary key of the record
	 * @return the record, or <code>null</code> if a record with the primary key could not be found
	 */
	@Override
	public Record fetchByPrimaryKey(long recordId) {
		return fetchByPrimaryKey((Serializable)recordId);
	}

	@Override
	public Map<Serializable, Record> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Record> map = new HashMap<Serializable, Record>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Record record = fetchByPrimaryKey(primaryKey);

			if (record != null) {
				map.put(primaryKey, record);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RecordModelImpl.ENTITY_CACHE_ENABLED,
					RecordImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Record)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RECORD_WHERE_PKS_IN);

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

			for (Record record : (List<Record>)q.list()) {
				map.put(record.getPrimaryKeyObj(), record);

				cacheResult(record);

				uncachedPrimaryKeys.remove(record.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RecordModelImpl.ENTITY_CACHE_ENABLED,
					RecordImpl.class, primaryKey, nullModel);
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
	 * Returns all the records.
	 *
	 * @return the records
	 */
	@Override
	public List<Record> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of records
	 * @param end the upper bound of the range of records (not inclusive)
	 * @return the range of records
	 */
	@Override
	public List<Record> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of records
	 * @param end the upper bound of the range of records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of records
	 */
	@Override
	public List<Record> findAll(int start, int end,
		OrderByComparator<Record> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the records.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecordModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of records
	 * @param end the upper bound of the range of records (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of records
	 */
	@Override
	public List<Record> findAll(int start, int end,
		OrderByComparator<Record> orderByComparator, boolean retrieveFromCache) {
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

		List<Record> list = null;

		if (retrieveFromCache) {
			list = (List<Record>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RECORD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RECORD;

				if (pagination) {
					sql = sql.concat(RecordModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Record>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Record>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the records from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Record record : findAll()) {
			remove(record);
		}
	}

	/**
	 * Returns the number of records.
	 *
	 * @return the number of records
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RECORD);

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
		return RecordModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the record persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RecordImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_RECORD = "SELECT record FROM Record record";
	private static final String _SQL_SELECT_RECORD_WHERE_PKS_IN = "SELECT record FROM Record record WHERE recordId IN (";
	private static final String _SQL_SELECT_RECORD_WHERE = "SELECT record FROM Record record WHERE ";
	private static final String _SQL_COUNT_RECORD = "SELECT COUNT(record) FROM Record record";
	private static final String _SQL_COUNT_RECORD_WHERE = "SELECT COUNT(record) FROM Record record WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "record.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Record exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Record exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(RecordPersistenceImpl.class);

	@Override
	public List<Record> findByPortletId(String portletId, int start, int end, OrderByComparator orderByComparator)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record findByPortletId_First(String portletId, OrderByComparator orderByComparator)
			throws SystemException, io.gatling.liferay.NoSuchRecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record fetchByPortletId_First(String portletId, OrderByComparator orderByComparator) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record findByPortletId_Last(String portletId, OrderByComparator orderByComparator)
			throws SystemException, io.gatling.liferay.NoSuchRecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record fetchByPortletId_Last(String portletId, OrderByComparator orderByComparator) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record[] findByPortletId_PrevAndNext(long recordId, String portletId, OrderByComparator orderByComparator)
			throws SystemException, io.gatling.liferay.NoSuchRecordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> findAll(int start, int end, OrderByComparator orderByComparator) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
}