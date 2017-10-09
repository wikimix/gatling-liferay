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

import io.gatling.liferay.exception.NoSuchProcessScenarioLinkException;
import io.gatling.liferay.model.ProcessScenarioLink;
import io.gatling.liferay.model.impl.ProcessScenarioLinkImpl;
import io.gatling.liferay.model.impl.ProcessScenarioLinkModelImpl;
import io.gatling.liferay.service.persistence.ProcessScenarioLinkPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the process scenario link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcessScenarioLinkPersistence
 * @see io.gatling.liferay.service.persistence.ProcessScenarioLinkUtil
 * @generated
 */
@ProviderType
public class ProcessScenarioLinkPersistenceImpl extends BasePersistenceImpl<ProcessScenarioLink>
	implements ProcessScenarioLinkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProcessScenarioLinkUtil} to access the process scenario link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProcessScenarioLinkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
			ProcessScenarioLinkModelImpl.FINDER_CACHE_ENABLED,
			ProcessScenarioLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
			ProcessScenarioLinkModelImpl.FINDER_CACHE_ENABLED,
			ProcessScenarioLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
			ProcessScenarioLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROCESSID =
		new FinderPath(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
			ProcessScenarioLinkModelImpl.FINDER_CACHE_ENABLED,
			ProcessScenarioLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByprocessId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSID =
		new FinderPath(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
			ProcessScenarioLinkModelImpl.FINDER_CACHE_ENABLED,
			ProcessScenarioLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByprocessId",
			new String[] { Long.class.getName() },
			ProcessScenarioLinkModelImpl.PROCESS_ID_COLUMN_BITMASK |
			ProcessScenarioLinkModelImpl.ORDER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PROCESSID = new FinderPath(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
			ProcessScenarioLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByprocessId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the process scenario links where process_id = &#63;.
	 *
	 * @param process_id the process_id
	 * @return the matching process scenario links
	 */
	@Override
	public List<ProcessScenarioLink> findByprocessId(long process_id) {
		return findByprocessId(process_id, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process scenario links where process_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param process_id the process_id
	 * @param start the lower bound of the range of process scenario links
	 * @param end the upper bound of the range of process scenario links (not inclusive)
	 * @return the range of matching process scenario links
	 */
	@Override
	public List<ProcessScenarioLink> findByprocessId(long process_id,
		int start, int end) {
		return findByprocessId(process_id, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process scenario links where process_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param process_id the process_id
	 * @param start the lower bound of the range of process scenario links
	 * @param end the upper bound of the range of process scenario links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process scenario links
	 */
	@Override
	public List<ProcessScenarioLink> findByprocessId(long process_id,
		int start, int end,
		OrderByComparator<ProcessScenarioLink> orderByComparator) {
		return findByprocessId(process_id, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process scenario links where process_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param process_id the process_id
	 * @param start the lower bound of the range of process scenario links
	 * @param end the upper bound of the range of process scenario links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process scenario links
	 */
	@Override
	public List<ProcessScenarioLink> findByprocessId(long process_id,
		int start, int end,
		OrderByComparator<ProcessScenarioLink> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSID;
			finderArgs = new Object[] { process_id };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROCESSID;
			finderArgs = new Object[] { process_id, start, end, orderByComparator };
		}

		List<ProcessScenarioLink> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessScenarioLink>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessScenarioLink processScenarioLink : list) {
					if ((process_id != processScenarioLink.getProcess_id())) {
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

			query.append(_SQL_SELECT_PROCESSSCENARIOLINK_WHERE);

			query.append(_FINDER_COLUMN_PROCESSID_PROCESS_ID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessScenarioLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(process_id);

				if (!pagination) {
					list = (List<ProcessScenarioLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessScenarioLink>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first process scenario link in the ordered set where process_id = &#63;.
	 *
	 * @param process_id the process_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process scenario link
	 * @throws NoSuchProcessScenarioLinkException if a matching process scenario link could not be found
	 */
	@Override
	public ProcessScenarioLink findByprocessId_First(long process_id,
		OrderByComparator<ProcessScenarioLink> orderByComparator)
		throws NoSuchProcessScenarioLinkException {
		ProcessScenarioLink processScenarioLink = fetchByprocessId_First(process_id,
				orderByComparator);

		if (processScenarioLink != null) {
			return processScenarioLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("process_id=");
		msg.append(process_id);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProcessScenarioLinkException(msg.toString());
	}

	/**
	 * Returns the first process scenario link in the ordered set where process_id = &#63;.
	 *
	 * @param process_id the process_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process scenario link, or <code>null</code> if a matching process scenario link could not be found
	 */
	@Override
	public ProcessScenarioLink fetchByprocessId_First(long process_id,
		OrderByComparator<ProcessScenarioLink> orderByComparator) {
		List<ProcessScenarioLink> list = findByprocessId(process_id, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process scenario link in the ordered set where process_id = &#63;.
	 *
	 * @param process_id the process_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process scenario link
	 * @throws NoSuchProcessScenarioLinkException if a matching process scenario link could not be found
	 */
	@Override
	public ProcessScenarioLink findByprocessId_Last(long process_id,
		OrderByComparator<ProcessScenarioLink> orderByComparator)
		throws NoSuchProcessScenarioLinkException {
		ProcessScenarioLink processScenarioLink = fetchByprocessId_Last(process_id,
				orderByComparator);

		if (processScenarioLink != null) {
			return processScenarioLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("process_id=");
		msg.append(process_id);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProcessScenarioLinkException(msg.toString());
	}

	/**
	 * Returns the last process scenario link in the ordered set where process_id = &#63;.
	 *
	 * @param process_id the process_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process scenario link, or <code>null</code> if a matching process scenario link could not be found
	 */
	@Override
	public ProcessScenarioLink fetchByprocessId_Last(long process_id,
		OrderByComparator<ProcessScenarioLink> orderByComparator) {
		int count = countByprocessId(process_id);

		if (count == 0) {
			return null;
		}

		List<ProcessScenarioLink> list = findByprocessId(process_id, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process scenario links before and after the current process scenario link in the ordered set where process_id = &#63;.
	 *
	 * @param psl_id the primary key of the current process scenario link
	 * @param process_id the process_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process scenario link
	 * @throws NoSuchProcessScenarioLinkException if a process scenario link with the primary key could not be found
	 */
	@Override
	public ProcessScenarioLink[] findByprocessId_PrevAndNext(long psl_id,
		long process_id,
		OrderByComparator<ProcessScenarioLink> orderByComparator)
		throws NoSuchProcessScenarioLinkException {
		ProcessScenarioLink processScenarioLink = findByPrimaryKey(psl_id);

		Session session = null;

		try {
			session = openSession();

			ProcessScenarioLink[] array = new ProcessScenarioLinkImpl[3];

			array[0] = getByprocessId_PrevAndNext(session, processScenarioLink,
					process_id, orderByComparator, true);

			array[1] = processScenarioLink;

			array[2] = getByprocessId_PrevAndNext(session, processScenarioLink,
					process_id, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessScenarioLink getByprocessId_PrevAndNext(Session session,
		ProcessScenarioLink processScenarioLink, long process_id,
		OrderByComparator<ProcessScenarioLink> orderByComparator,
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

		query.append(_SQL_SELECT_PROCESSSCENARIOLINK_WHERE);

		query.append(_FINDER_COLUMN_PROCESSID_PROCESS_ID_2);

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
			query.append(ProcessScenarioLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(process_id);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processScenarioLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessScenarioLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process scenario links where process_id = &#63; from the database.
	 *
	 * @param process_id the process_id
	 */
	@Override
	public void removeByprocessId(long process_id) {
		for (ProcessScenarioLink processScenarioLink : findByprocessId(
				process_id, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processScenarioLink);
		}
	}

	/**
	 * Returns the number of process scenario links where process_id = &#63;.
	 *
	 * @param process_id the process_id
	 * @return the number of matching process scenario links
	 */
	@Override
	public int countByprocessId(long process_id) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PROCESSID;

		Object[] finderArgs = new Object[] { process_id };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSSCENARIOLINK_WHERE);

			query.append(_FINDER_COLUMN_PROCESSID_PROCESS_ID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(process_id);

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

	private static final String _FINDER_COLUMN_PROCESSID_PROCESS_ID_2 = "processScenarioLink.process_id = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SCENARIOID =
		new FinderPath(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
			ProcessScenarioLinkModelImpl.FINDER_CACHE_ENABLED,
			ProcessScenarioLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByscenarioId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOID =
		new FinderPath(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
			ProcessScenarioLinkModelImpl.FINDER_CACHE_ENABLED,
			ProcessScenarioLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByscenarioId",
			new String[] { Long.class.getName() },
			ProcessScenarioLinkModelImpl.SCENARIO_ID_COLUMN_BITMASK |
			ProcessScenarioLinkModelImpl.ORDER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCENARIOID = new FinderPath(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
			ProcessScenarioLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByscenarioId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the process scenario links where scenario_id = &#63;.
	 *
	 * @param scenario_id the scenario_id
	 * @return the matching process scenario links
	 */
	@Override
	public List<ProcessScenarioLink> findByscenarioId(long scenario_id) {
		return findByscenarioId(scenario_id, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process scenario links where scenario_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scenario_id the scenario_id
	 * @param start the lower bound of the range of process scenario links
	 * @param end the upper bound of the range of process scenario links (not inclusive)
	 * @return the range of matching process scenario links
	 */
	@Override
	public List<ProcessScenarioLink> findByscenarioId(long scenario_id,
		int start, int end) {
		return findByscenarioId(scenario_id, start, end, null);
	}

	/**
	 * Returns an ordered range of all the process scenario links where scenario_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scenario_id the scenario_id
	 * @param start the lower bound of the range of process scenario links
	 * @param end the upper bound of the range of process scenario links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching process scenario links
	 */
	@Override
	public List<ProcessScenarioLink> findByscenarioId(long scenario_id,
		int start, int end,
		OrderByComparator<ProcessScenarioLink> orderByComparator) {
		return findByscenarioId(scenario_id, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process scenario links where scenario_id = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param scenario_id the scenario_id
	 * @param start the lower bound of the range of process scenario links
	 * @param end the upper bound of the range of process scenario links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching process scenario links
	 */
	@Override
	public List<ProcessScenarioLink> findByscenarioId(long scenario_id,
		int start, int end,
		OrderByComparator<ProcessScenarioLink> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOID;
			finderArgs = new Object[] { scenario_id };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SCENARIOID;
			finderArgs = new Object[] { scenario_id, start, end, orderByComparator };
		}

		List<ProcessScenarioLink> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessScenarioLink>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProcessScenarioLink processScenarioLink : list) {
					if ((scenario_id != processScenarioLink.getScenario_id())) {
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

			query.append(_SQL_SELECT_PROCESSSCENARIOLINK_WHERE);

			query.append(_FINDER_COLUMN_SCENARIOID_SCENARIO_ID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProcessScenarioLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scenario_id);

				if (!pagination) {
					list = (List<ProcessScenarioLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessScenarioLink>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first process scenario link in the ordered set where scenario_id = &#63;.
	 *
	 * @param scenario_id the scenario_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process scenario link
	 * @throws NoSuchProcessScenarioLinkException if a matching process scenario link could not be found
	 */
	@Override
	public ProcessScenarioLink findByscenarioId_First(long scenario_id,
		OrderByComparator<ProcessScenarioLink> orderByComparator)
		throws NoSuchProcessScenarioLinkException {
		ProcessScenarioLink processScenarioLink = fetchByscenarioId_First(scenario_id,
				orderByComparator);

		if (processScenarioLink != null) {
			return processScenarioLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scenario_id=");
		msg.append(scenario_id);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProcessScenarioLinkException(msg.toString());
	}

	/**
	 * Returns the first process scenario link in the ordered set where scenario_id = &#63;.
	 *
	 * @param scenario_id the scenario_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching process scenario link, or <code>null</code> if a matching process scenario link could not be found
	 */
	@Override
	public ProcessScenarioLink fetchByscenarioId_First(long scenario_id,
		OrderByComparator<ProcessScenarioLink> orderByComparator) {
		List<ProcessScenarioLink> list = findByscenarioId(scenario_id, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last process scenario link in the ordered set where scenario_id = &#63;.
	 *
	 * @param scenario_id the scenario_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process scenario link
	 * @throws NoSuchProcessScenarioLinkException if a matching process scenario link could not be found
	 */
	@Override
	public ProcessScenarioLink findByscenarioId_Last(long scenario_id,
		OrderByComparator<ProcessScenarioLink> orderByComparator)
		throws NoSuchProcessScenarioLinkException {
		ProcessScenarioLink processScenarioLink = fetchByscenarioId_Last(scenario_id,
				orderByComparator);

		if (processScenarioLink != null) {
			return processScenarioLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("scenario_id=");
		msg.append(scenario_id);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProcessScenarioLinkException(msg.toString());
	}

	/**
	 * Returns the last process scenario link in the ordered set where scenario_id = &#63;.
	 *
	 * @param scenario_id the scenario_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching process scenario link, or <code>null</code> if a matching process scenario link could not be found
	 */
	@Override
	public ProcessScenarioLink fetchByscenarioId_Last(long scenario_id,
		OrderByComparator<ProcessScenarioLink> orderByComparator) {
		int count = countByscenarioId(scenario_id);

		if (count == 0) {
			return null;
		}

		List<ProcessScenarioLink> list = findByscenarioId(scenario_id,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the process scenario links before and after the current process scenario link in the ordered set where scenario_id = &#63;.
	 *
	 * @param psl_id the primary key of the current process scenario link
	 * @param scenario_id the scenario_id
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next process scenario link
	 * @throws NoSuchProcessScenarioLinkException if a process scenario link with the primary key could not be found
	 */
	@Override
	public ProcessScenarioLink[] findByscenarioId_PrevAndNext(long psl_id,
		long scenario_id,
		OrderByComparator<ProcessScenarioLink> orderByComparator)
		throws NoSuchProcessScenarioLinkException {
		ProcessScenarioLink processScenarioLink = findByPrimaryKey(psl_id);

		Session session = null;

		try {
			session = openSession();

			ProcessScenarioLink[] array = new ProcessScenarioLinkImpl[3];

			array[0] = getByscenarioId_PrevAndNext(session,
					processScenarioLink, scenario_id, orderByComparator, true);

			array[1] = processScenarioLink;

			array[2] = getByscenarioId_PrevAndNext(session,
					processScenarioLink, scenario_id, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProcessScenarioLink getByscenarioId_PrevAndNext(Session session,
		ProcessScenarioLink processScenarioLink, long scenario_id,
		OrderByComparator<ProcessScenarioLink> orderByComparator,
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

		query.append(_SQL_SELECT_PROCESSSCENARIOLINK_WHERE);

		query.append(_FINDER_COLUMN_SCENARIOID_SCENARIO_ID_2);

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
			query.append(ProcessScenarioLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(scenario_id);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(processScenarioLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProcessScenarioLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the process scenario links where scenario_id = &#63; from the database.
	 *
	 * @param scenario_id the scenario_id
	 */
	@Override
	public void removeByscenarioId(long scenario_id) {
		for (ProcessScenarioLink processScenarioLink : findByscenarioId(
				scenario_id, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(processScenarioLink);
		}
	}

	/**
	 * Returns the number of process scenario links where scenario_id = &#63;.
	 *
	 * @param scenario_id the scenario_id
	 * @return the number of matching process scenario links
	 */
	@Override
	public int countByscenarioId(long scenario_id) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SCENARIOID;

		Object[] finderArgs = new Object[] { scenario_id };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROCESSSCENARIOLINK_WHERE);

			query.append(_FINDER_COLUMN_SCENARIOID_SCENARIO_ID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scenario_id);

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

	private static final String _FINDER_COLUMN_SCENARIOID_SCENARIO_ID_2 = "processScenarioLink.scenario_id = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_PAUSE = new FinderPath(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
			ProcessScenarioLinkModelImpl.FINDER_CACHE_ENABLED,
			ProcessScenarioLinkImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByPause",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			ProcessScenarioLinkModelImpl.PROCESS_ID_COLUMN_BITMASK |
			ProcessScenarioLinkModelImpl.SCENARIO_ID_COLUMN_BITMASK |
			ProcessScenarioLinkModelImpl.ORDER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PAUSE = new FinderPath(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
			ProcessScenarioLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPause",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns the process scenario link where process_id = &#63; and scenario_id = &#63; and order = &#63; or throws a {@link NoSuchProcessScenarioLinkException} if it could not be found.
	 *
	 * @param process_id the process_id
	 * @param scenario_id the scenario_id
	 * @param order the order
	 * @return the matching process scenario link
	 * @throws NoSuchProcessScenarioLinkException if a matching process scenario link could not be found
	 */
	@Override
	public ProcessScenarioLink findByPause(long process_id, long scenario_id,
		int order) throws NoSuchProcessScenarioLinkException {
		ProcessScenarioLink processScenarioLink = fetchByPause(process_id,
				scenario_id, order);

		if (processScenarioLink == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("process_id=");
			msg.append(process_id);

			msg.append(", scenario_id=");
			msg.append(scenario_id);

			msg.append(", order=");
			msg.append(order);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchProcessScenarioLinkException(msg.toString());
		}

		return processScenarioLink;
	}

	/**
	 * Returns the process scenario link where process_id = &#63; and scenario_id = &#63; and order = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param process_id the process_id
	 * @param scenario_id the scenario_id
	 * @param order the order
	 * @return the matching process scenario link, or <code>null</code> if a matching process scenario link could not be found
	 */
	@Override
	public ProcessScenarioLink fetchByPause(long process_id, long scenario_id,
		int order) {
		return fetchByPause(process_id, scenario_id, order, true);
	}

	/**
	 * Returns the process scenario link where process_id = &#63; and scenario_id = &#63; and order = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param process_id the process_id
	 * @param scenario_id the scenario_id
	 * @param order the order
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching process scenario link, or <code>null</code> if a matching process scenario link could not be found
	 */
	@Override
	public ProcessScenarioLink fetchByPause(long process_id, long scenario_id,
		int order, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { process_id, scenario_id, order };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_PAUSE,
					finderArgs, this);
		}

		if (result instanceof ProcessScenarioLink) {
			ProcessScenarioLink processScenarioLink = (ProcessScenarioLink)result;

			if ((process_id != processScenarioLink.getProcess_id()) ||
					(scenario_id != processScenarioLink.getScenario_id()) ||
					(order != processScenarioLink.getOrder())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_PROCESSSCENARIOLINK_WHERE);

			query.append(_FINDER_COLUMN_PAUSE_PROCESS_ID_2);

			query.append(_FINDER_COLUMN_PAUSE_SCENARIO_ID_2);

			query.append(_FINDER_COLUMN_PAUSE_ORDER_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(process_id);

				qPos.add(scenario_id);

				qPos.add(order);

				List<ProcessScenarioLink> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_PAUSE,
						finderArgs, list);
				}
				else {
					ProcessScenarioLink processScenarioLink = list.get(0);

					result = processScenarioLink;

					cacheResult(processScenarioLink);

					if ((processScenarioLink.getProcess_id() != process_id) ||
							(processScenarioLink.getScenario_id() != scenario_id) ||
							(processScenarioLink.getOrder() != order)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_PAUSE,
							finderArgs, processScenarioLink);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_PAUSE, finderArgs);

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
			return (ProcessScenarioLink)result;
		}
	}

	/**
	 * Removes the process scenario link where process_id = &#63; and scenario_id = &#63; and order = &#63; from the database.
	 *
	 * @param process_id the process_id
	 * @param scenario_id the scenario_id
	 * @param order the order
	 * @return the process scenario link that was removed
	 */
	@Override
	public ProcessScenarioLink removeByPause(long process_id, long scenario_id,
		int order) throws NoSuchProcessScenarioLinkException {
		ProcessScenarioLink processScenarioLink = findByPause(process_id,
				scenario_id, order);

		return remove(processScenarioLink);
	}

	/**
	 * Returns the number of process scenario links where process_id = &#63; and scenario_id = &#63; and order = &#63;.
	 *
	 * @param process_id the process_id
	 * @param scenario_id the scenario_id
	 * @param order the order
	 * @return the number of matching process scenario links
	 */
	@Override
	public int countByPause(long process_id, long scenario_id, int order) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PAUSE;

		Object[] finderArgs = new Object[] { process_id, scenario_id, order };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_PROCESSSCENARIOLINK_WHERE);

			query.append(_FINDER_COLUMN_PAUSE_PROCESS_ID_2);

			query.append(_FINDER_COLUMN_PAUSE_SCENARIO_ID_2);

			query.append(_FINDER_COLUMN_PAUSE_ORDER_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(process_id);

				qPos.add(scenario_id);

				qPos.add(order);

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

	private static final String _FINDER_COLUMN_PAUSE_PROCESS_ID_2 = "processScenarioLink.process_id = ? AND ";
	private static final String _FINDER_COLUMN_PAUSE_SCENARIO_ID_2 = "processScenarioLink.scenario_id = ? AND ";
	private static final String _FINDER_COLUMN_PAUSE_ORDER_2 = "processScenarioLink.order = ?";

	public ProcessScenarioLinkPersistenceImpl() {
		setModelClass(ProcessScenarioLink.class);
	}

	/**
	 * Caches the process scenario link in the entity cache if it is enabled.
	 *
	 * @param processScenarioLink the process scenario link
	 */
	@Override
	public void cacheResult(ProcessScenarioLink processScenarioLink) {
		entityCache.putResult(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
			ProcessScenarioLinkImpl.class, processScenarioLink.getPrimaryKey(),
			processScenarioLink);

		finderCache.putResult(FINDER_PATH_FETCH_BY_PAUSE,
			new Object[] {
				processScenarioLink.getProcess_id(),
				processScenarioLink.getScenario_id(),
				processScenarioLink.getOrder()
			}, processScenarioLink);

		processScenarioLink.resetOriginalValues();
	}

	/**
	 * Caches the process scenario links in the entity cache if it is enabled.
	 *
	 * @param processScenarioLinks the process scenario links
	 */
	@Override
	public void cacheResult(List<ProcessScenarioLink> processScenarioLinks) {
		for (ProcessScenarioLink processScenarioLink : processScenarioLinks) {
			if (entityCache.getResult(
						ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
						ProcessScenarioLinkImpl.class,
						processScenarioLink.getPrimaryKey()) == null) {
				cacheResult(processScenarioLink);
			}
			else {
				processScenarioLink.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all process scenario links.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProcessScenarioLinkImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the process scenario link.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProcessScenarioLink processScenarioLink) {
		entityCache.removeResult(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
			ProcessScenarioLinkImpl.class, processScenarioLink.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ProcessScenarioLinkModelImpl)processScenarioLink,
			true);
	}

	@Override
	public void clearCache(List<ProcessScenarioLink> processScenarioLinks) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProcessScenarioLink processScenarioLink : processScenarioLinks) {
			entityCache.removeResult(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
				ProcessScenarioLinkImpl.class,
				processScenarioLink.getPrimaryKey());

			clearUniqueFindersCache((ProcessScenarioLinkModelImpl)processScenarioLink,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ProcessScenarioLinkModelImpl processScenarioLinkModelImpl) {
		Object[] args = new Object[] {
				processScenarioLinkModelImpl.getProcess_id(),
				processScenarioLinkModelImpl.getScenario_id(),
				processScenarioLinkModelImpl.getOrder()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_PAUSE, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_PAUSE, args,
			processScenarioLinkModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ProcessScenarioLinkModelImpl processScenarioLinkModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					processScenarioLinkModelImpl.getProcess_id(),
					processScenarioLinkModelImpl.getScenario_id(),
					processScenarioLinkModelImpl.getOrder()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PAUSE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PAUSE, args);
		}

		if ((processScenarioLinkModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_PAUSE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					processScenarioLinkModelImpl.getOriginalProcess_id(),
					processScenarioLinkModelImpl.getOriginalScenario_id(),
					processScenarioLinkModelImpl.getOriginalOrder()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PAUSE, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_PAUSE, args);
		}
	}

	/**
	 * Creates a new process scenario link with the primary key. Does not add the process scenario link to the database.
	 *
	 * @param psl_id the primary key for the new process scenario link
	 * @return the new process scenario link
	 */
	@Override
	public ProcessScenarioLink create(long psl_id) {
		ProcessScenarioLink processScenarioLink = new ProcessScenarioLinkImpl();

		processScenarioLink.setNew(true);
		processScenarioLink.setPrimaryKey(psl_id);

		return processScenarioLink;
	}

	/**
	 * Removes the process scenario link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param psl_id the primary key of the process scenario link
	 * @return the process scenario link that was removed
	 * @throws NoSuchProcessScenarioLinkException if a process scenario link with the primary key could not be found
	 */
	@Override
	public ProcessScenarioLink remove(long psl_id)
		throws NoSuchProcessScenarioLinkException {
		return remove((Serializable)psl_id);
	}

	/**
	 * Removes the process scenario link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the process scenario link
	 * @return the process scenario link that was removed
	 * @throws NoSuchProcessScenarioLinkException if a process scenario link with the primary key could not be found
	 */
	@Override
	public ProcessScenarioLink remove(Serializable primaryKey)
		throws NoSuchProcessScenarioLinkException {
		Session session = null;

		try {
			session = openSession();

			ProcessScenarioLink processScenarioLink = (ProcessScenarioLink)session.get(ProcessScenarioLinkImpl.class,
					primaryKey);

			if (processScenarioLink == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProcessScenarioLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(processScenarioLink);
		}
		catch (NoSuchProcessScenarioLinkException nsee) {
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
	protected ProcessScenarioLink removeImpl(
		ProcessScenarioLink processScenarioLink) {
		processScenarioLink = toUnwrappedModel(processScenarioLink);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(processScenarioLink)) {
				processScenarioLink = (ProcessScenarioLink)session.get(ProcessScenarioLinkImpl.class,
						processScenarioLink.getPrimaryKeyObj());
			}

			if (processScenarioLink != null) {
				session.delete(processScenarioLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (processScenarioLink != null) {
			clearCache(processScenarioLink);
		}

		return processScenarioLink;
	}

	@Override
	public ProcessScenarioLink updateImpl(
		ProcessScenarioLink processScenarioLink) {
		processScenarioLink = toUnwrappedModel(processScenarioLink);

		boolean isNew = processScenarioLink.isNew();

		ProcessScenarioLinkModelImpl processScenarioLinkModelImpl = (ProcessScenarioLinkModelImpl)processScenarioLink;

		Session session = null;

		try {
			session = openSession();

			if (processScenarioLink.isNew()) {
				session.save(processScenarioLink);

				processScenarioLink.setNew(false);
			}
			else {
				processScenarioLink = (ProcessScenarioLink)session.merge(processScenarioLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ProcessScenarioLinkModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((processScenarioLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processScenarioLinkModelImpl.getOriginalProcess_id()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PROCESSID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSID,
					args);

				args = new Object[] { processScenarioLinkModelImpl.getProcess_id() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PROCESSID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROCESSID,
					args);
			}

			if ((processScenarioLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						processScenarioLinkModelImpl.getOriginalScenario_id()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SCENARIOID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOID,
					args);

				args = new Object[] {
						processScenarioLinkModelImpl.getScenario_id()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_SCENARIOID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SCENARIOID,
					args);
			}
		}

		entityCache.putResult(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
			ProcessScenarioLinkImpl.class, processScenarioLink.getPrimaryKey(),
			processScenarioLink, false);

		clearUniqueFindersCache(processScenarioLinkModelImpl, false);
		cacheUniqueFindersCache(processScenarioLinkModelImpl);

		processScenarioLink.resetOriginalValues();

		return processScenarioLink;
	}

	protected ProcessScenarioLink toUnwrappedModel(
		ProcessScenarioLink processScenarioLink) {
		if (processScenarioLink instanceof ProcessScenarioLinkImpl) {
			return processScenarioLink;
		}

		ProcessScenarioLinkImpl processScenarioLinkImpl = new ProcessScenarioLinkImpl();

		processScenarioLinkImpl.setNew(processScenarioLink.isNew());
		processScenarioLinkImpl.setPrimaryKey(processScenarioLink.getPrimaryKey());

		processScenarioLinkImpl.setPsl_id(processScenarioLink.getPsl_id());
		processScenarioLinkImpl.setProcess_id(processScenarioLink.getProcess_id());
		processScenarioLinkImpl.setScenario_id(processScenarioLink.getScenario_id());
		processScenarioLinkImpl.setOrder(processScenarioLink.getOrder());
		processScenarioLinkImpl.setPause(processScenarioLink.getPause());

		return processScenarioLinkImpl;
	}

	/**
	 * Returns the process scenario link with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the process scenario link
	 * @return the process scenario link
	 * @throws NoSuchProcessScenarioLinkException if a process scenario link with the primary key could not be found
	 */
	@Override
	public ProcessScenarioLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProcessScenarioLinkException {
		ProcessScenarioLink processScenarioLink = fetchByPrimaryKey(primaryKey);

		if (processScenarioLink == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProcessScenarioLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return processScenarioLink;
	}

	/**
	 * Returns the process scenario link with the primary key or throws a {@link NoSuchProcessScenarioLinkException} if it could not be found.
	 *
	 * @param psl_id the primary key of the process scenario link
	 * @return the process scenario link
	 * @throws NoSuchProcessScenarioLinkException if a process scenario link with the primary key could not be found
	 */
	@Override
	public ProcessScenarioLink findByPrimaryKey(long psl_id)
		throws NoSuchProcessScenarioLinkException {
		return findByPrimaryKey((Serializable)psl_id);
	}

	/**
	 * Returns the process scenario link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the process scenario link
	 * @return the process scenario link, or <code>null</code> if a process scenario link with the primary key could not be found
	 */
	@Override
	public ProcessScenarioLink fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
				ProcessScenarioLinkImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProcessScenarioLink processScenarioLink = (ProcessScenarioLink)serializable;

		if (processScenarioLink == null) {
			Session session = null;

			try {
				session = openSession();

				processScenarioLink = (ProcessScenarioLink)session.get(ProcessScenarioLinkImpl.class,
						primaryKey);

				if (processScenarioLink != null) {
					cacheResult(processScenarioLink);
				}
				else {
					entityCache.putResult(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
						ProcessScenarioLinkImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
					ProcessScenarioLinkImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return processScenarioLink;
	}

	/**
	 * Returns the process scenario link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param psl_id the primary key of the process scenario link
	 * @return the process scenario link, or <code>null</code> if a process scenario link with the primary key could not be found
	 */
	@Override
	public ProcessScenarioLink fetchByPrimaryKey(long psl_id) {
		return fetchByPrimaryKey((Serializable)psl_id);
	}

	@Override
	public Map<Serializable, ProcessScenarioLink> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProcessScenarioLink> map = new HashMap<Serializable, ProcessScenarioLink>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProcessScenarioLink processScenarioLink = fetchByPrimaryKey(primaryKey);

			if (processScenarioLink != null) {
				map.put(primaryKey, processScenarioLink);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
					ProcessScenarioLinkImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProcessScenarioLink)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROCESSSCENARIOLINK_WHERE_PKS_IN);

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

			for (ProcessScenarioLink processScenarioLink : (List<ProcessScenarioLink>)q.list()) {
				map.put(processScenarioLink.getPrimaryKeyObj(),
					processScenarioLink);

				cacheResult(processScenarioLink);

				uncachedPrimaryKeys.remove(processScenarioLink.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProcessScenarioLinkModelImpl.ENTITY_CACHE_ENABLED,
					ProcessScenarioLinkImpl.class, primaryKey, nullModel);
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
	 * Returns all the process scenario links.
	 *
	 * @return the process scenario links
	 */
	@Override
	public List<ProcessScenarioLink> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the process scenario links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process scenario links
	 * @param end the upper bound of the range of process scenario links (not inclusive)
	 * @return the range of process scenario links
	 */
	@Override
	public List<ProcessScenarioLink> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the process scenario links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process scenario links
	 * @param end the upper bound of the range of process scenario links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of process scenario links
	 */
	@Override
	public List<ProcessScenarioLink> findAll(int start, int end,
		OrderByComparator<ProcessScenarioLink> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the process scenario links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessScenarioLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of process scenario links
	 * @param end the upper bound of the range of process scenario links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of process scenario links
	 */
	@Override
	public List<ProcessScenarioLink> findAll(int start, int end,
		OrderByComparator<ProcessScenarioLink> orderByComparator,
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

		List<ProcessScenarioLink> list = null;

		if (retrieveFromCache) {
			list = (List<ProcessScenarioLink>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROCESSSCENARIOLINK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROCESSSCENARIOLINK;

				if (pagination) {
					sql = sql.concat(ProcessScenarioLinkModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProcessScenarioLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProcessScenarioLink>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the process scenario links from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProcessScenarioLink processScenarioLink : findAll()) {
			remove(processScenarioLink);
		}
	}

	/**
	 * Returns the number of process scenario links.
	 *
	 * @return the number of process scenario links
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROCESSSCENARIOLINK);

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
		return ProcessScenarioLinkModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the process scenario link persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProcessScenarioLinkImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_PROCESSSCENARIOLINK = "SELECT processScenarioLink FROM ProcessScenarioLink processScenarioLink";
	private static final String _SQL_SELECT_PROCESSSCENARIOLINK_WHERE_PKS_IN = "SELECT processScenarioLink FROM ProcessScenarioLink processScenarioLink WHERE psl_id IN (";
	private static final String _SQL_SELECT_PROCESSSCENARIOLINK_WHERE = "SELECT processScenarioLink FROM ProcessScenarioLink processScenarioLink WHERE ";
	private static final String _SQL_COUNT_PROCESSSCENARIOLINK = "SELECT COUNT(processScenarioLink) FROM ProcessScenarioLink processScenarioLink";
	private static final String _SQL_COUNT_PROCESSSCENARIOLINK_WHERE = "SELECT COUNT(processScenarioLink) FROM ProcessScenarioLink processScenarioLink WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "processScenarioLink.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProcessScenarioLink exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProcessScenarioLink exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ProcessScenarioLinkPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"order"
			});
}