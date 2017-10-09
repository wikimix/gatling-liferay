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

import io.gatling.liferay.exception.NoSuchFormParamException;
import io.gatling.liferay.model.FormParam;
import io.gatling.liferay.model.impl.FormParamImpl;
import io.gatling.liferay.model.impl.FormParamModelImpl;
import io.gatling.liferay.service.persistence.FormParamPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the form param service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FormParamPersistence
 * @see io.gatling.liferay.service.persistence.FormParamUtil
 * @generated
 */
@ProviderType
public class FormParamPersistenceImpl extends BasePersistenceImpl<FormParam>
	implements FormParamPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FormParamUtil} to access the form param persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FormParamImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FormParamModelImpl.ENTITY_CACHE_ENABLED,
			FormParamModelImpl.FINDER_CACHE_ENABLED, FormParamImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FormParamModelImpl.ENTITY_CACHE_ENABLED,
			FormParamModelImpl.FINDER_CACHE_ENABLED, FormParamImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FormParamModelImpl.ENTITY_CACHE_ENABLED,
			FormParamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_URLRECORDID = new FinderPath(FormParamModelImpl.ENTITY_CACHE_ENABLED,
			FormParamModelImpl.FINDER_CACHE_ENABLED, FormParamImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUrlRecordId",
			new String[] { Long.class.getName() },
			FormParamModelImpl.URLRECORDID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_URLRECORDID = new FinderPath(FormParamModelImpl.ENTITY_CACHE_ENABLED,
			FormParamModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUrlRecordId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the form param where urlRecordId = &#63; or throws a {@link NoSuchFormParamException} if it could not be found.
	 *
	 * @param urlRecordId the url record ID
	 * @return the matching form param
	 * @throws NoSuchFormParamException if a matching form param could not be found
	 */
	@Override
	public FormParam findByUrlRecordId(long urlRecordId)
		throws NoSuchFormParamException {
		FormParam formParam = fetchByUrlRecordId(urlRecordId);

		if (formParam == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("urlRecordId=");
			msg.append(urlRecordId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchFormParamException(msg.toString());
		}

		return formParam;
	}

	/**
	 * Returns the form param where urlRecordId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param urlRecordId the url record ID
	 * @return the matching form param, or <code>null</code> if a matching form param could not be found
	 */
	@Override
	public FormParam fetchByUrlRecordId(long urlRecordId) {
		return fetchByUrlRecordId(urlRecordId, true);
	}

	/**
	 * Returns the form param where urlRecordId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param urlRecordId the url record ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching form param, or <code>null</code> if a matching form param could not be found
	 */
	@Override
	public FormParam fetchByUrlRecordId(long urlRecordId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { urlRecordId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_URLRECORDID,
					finderArgs, this);
		}

		if (result instanceof FormParam) {
			FormParam formParam = (FormParam)result;

			if ((urlRecordId != formParam.getUrlRecordId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_FORMPARAM_WHERE);

			query.append(_FINDER_COLUMN_URLRECORDID_URLRECORDID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(urlRecordId);

				List<FormParam> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_URLRECORDID,
						finderArgs, list);
				}
				else {
					FormParam formParam = list.get(0);

					result = formParam;

					cacheResult(formParam);

					if ((formParam.getUrlRecordId() != urlRecordId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_URLRECORDID,
							finderArgs, formParam);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_URLRECORDID,
					finderArgs);

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
			return (FormParam)result;
		}
	}

	/**
	 * Removes the form param where urlRecordId = &#63; from the database.
	 *
	 * @param urlRecordId the url record ID
	 * @return the form param that was removed
	 */
	@Override
	public FormParam removeByUrlRecordId(long urlRecordId)
		throws NoSuchFormParamException {
		FormParam formParam = findByUrlRecordId(urlRecordId);

		return remove(formParam);
	}

	/**
	 * Returns the number of form params where urlRecordId = &#63;.
	 *
	 * @param urlRecordId the url record ID
	 * @return the number of matching form params
	 */
	@Override
	public int countByUrlRecordId(long urlRecordId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_URLRECORDID;

		Object[] finderArgs = new Object[] { urlRecordId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMPARAM_WHERE);

			query.append(_FINDER_COLUMN_URLRECORDID_URLRECORDID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(urlRecordId);

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

	private static final String _FINDER_COLUMN_URLRECORDID_URLRECORDID_2 = "formParam.urlRecordId = ?";

	public FormParamPersistenceImpl() {
		setModelClass(FormParam.class);
	}

	/**
	 * Caches the form param in the entity cache if it is enabled.
	 *
	 * @param formParam the form param
	 */
	@Override
	public void cacheResult(FormParam formParam) {
		entityCache.putResult(FormParamModelImpl.ENTITY_CACHE_ENABLED,
			FormParamImpl.class, formParam.getPrimaryKey(), formParam);

		finderCache.putResult(FINDER_PATH_FETCH_BY_URLRECORDID,
			new Object[] { formParam.getUrlRecordId() }, formParam);

		formParam.resetOriginalValues();
	}

	/**
	 * Caches the form params in the entity cache if it is enabled.
	 *
	 * @param formParams the form params
	 */
	@Override
	public void cacheResult(List<FormParam> formParams) {
		for (FormParam formParam : formParams) {
			if (entityCache.getResult(FormParamModelImpl.ENTITY_CACHE_ENABLED,
						FormParamImpl.class, formParam.getPrimaryKey()) == null) {
				cacheResult(formParam);
			}
			else {
				formParam.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all form params.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FormParamImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the form param.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FormParam formParam) {
		entityCache.removeResult(FormParamModelImpl.ENTITY_CACHE_ENABLED,
			FormParamImpl.class, formParam.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((FormParamModelImpl)formParam, true);
	}

	@Override
	public void clearCache(List<FormParam> formParams) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FormParam formParam : formParams) {
			entityCache.removeResult(FormParamModelImpl.ENTITY_CACHE_ENABLED,
				FormParamImpl.class, formParam.getPrimaryKey());

			clearUniqueFindersCache((FormParamModelImpl)formParam, true);
		}
	}

	protected void cacheUniqueFindersCache(
		FormParamModelImpl formParamModelImpl) {
		Object[] args = new Object[] { formParamModelImpl.getUrlRecordId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_URLRECORDID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_URLRECORDID, args,
			formParamModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		FormParamModelImpl formParamModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { formParamModelImpl.getUrlRecordId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_URLRECORDID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_URLRECORDID, args);
		}

		if ((formParamModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_URLRECORDID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					formParamModelImpl.getOriginalUrlRecordId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_URLRECORDID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_URLRECORDID, args);
		}
	}

	/**
	 * Creates a new form param with the primary key. Does not add the form param to the database.
	 *
	 * @param formParamId the primary key for the new form param
	 * @return the new form param
	 */
	@Override
	public FormParam create(long formParamId) {
		FormParam formParam = new FormParamImpl();

		formParam.setNew(true);
		formParam.setPrimaryKey(formParamId);

		return formParam;
	}

	/**
	 * Removes the form param with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formParamId the primary key of the form param
	 * @return the form param that was removed
	 * @throws NoSuchFormParamException if a form param with the primary key could not be found
	 */
	@Override
	public FormParam remove(long formParamId) throws NoSuchFormParamException {
		return remove((Serializable)formParamId);
	}

	/**
	 * Removes the form param with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the form param
	 * @return the form param that was removed
	 * @throws NoSuchFormParamException if a form param with the primary key could not be found
	 */
	@Override
	public FormParam remove(Serializable primaryKey)
		throws NoSuchFormParamException {
		Session session = null;

		try {
			session = openSession();

			FormParam formParam = (FormParam)session.get(FormParamImpl.class,
					primaryKey);

			if (formParam == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFormParamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(formParam);
		}
		catch (NoSuchFormParamException nsee) {
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
	protected FormParam removeImpl(FormParam formParam) {
		formParam = toUnwrappedModel(formParam);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(formParam)) {
				formParam = (FormParam)session.get(FormParamImpl.class,
						formParam.getPrimaryKeyObj());
			}

			if (formParam != null) {
				session.delete(formParam);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (formParam != null) {
			clearCache(formParam);
		}

		return formParam;
	}

	@Override
	public FormParam updateImpl(FormParam formParam) {
		formParam = toUnwrappedModel(formParam);

		boolean isNew = formParam.isNew();

		FormParamModelImpl formParamModelImpl = (FormParamModelImpl)formParam;

		Session session = null;

		try {
			session = openSession();

			if (formParam.isNew()) {
				session.save(formParam);

				formParam.setNew(false);
			}
			else {
				formParam = (FormParam)session.merge(formParam);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !FormParamModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(FormParamModelImpl.ENTITY_CACHE_ENABLED,
			FormParamImpl.class, formParam.getPrimaryKey(), formParam, false);

		clearUniqueFindersCache(formParamModelImpl, false);
		cacheUniqueFindersCache(formParamModelImpl);

		formParam.resetOriginalValues();

		return formParam;
	}

	protected FormParam toUnwrappedModel(FormParam formParam) {
		if (formParam instanceof FormParamImpl) {
			return formParam;
		}

		FormParamImpl formParamImpl = new FormParamImpl();

		formParamImpl.setNew(formParam.isNew());
		formParamImpl.setPrimaryKey(formParam.getPrimaryKey());

		formParamImpl.setFormParamId(formParam.getFormParamId());
		formParamImpl.setUrlRecordId(formParam.getUrlRecordId());
		formParamImpl.setData(formParam.getData());

		return formParamImpl;
	}

	/**
	 * Returns the form param with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the form param
	 * @return the form param
	 * @throws NoSuchFormParamException if a form param with the primary key could not be found
	 */
	@Override
	public FormParam findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFormParamException {
		FormParam formParam = fetchByPrimaryKey(primaryKey);

		if (formParam == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFormParamException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return formParam;
	}

	/**
	 * Returns the form param with the primary key or throws a {@link NoSuchFormParamException} if it could not be found.
	 *
	 * @param formParamId the primary key of the form param
	 * @return the form param
	 * @throws NoSuchFormParamException if a form param with the primary key could not be found
	 */
	@Override
	public FormParam findByPrimaryKey(long formParamId)
		throws NoSuchFormParamException {
		return findByPrimaryKey((Serializable)formParamId);
	}

	/**
	 * Returns the form param with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the form param
	 * @return the form param, or <code>null</code> if a form param with the primary key could not be found
	 */
	@Override
	public FormParam fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(FormParamModelImpl.ENTITY_CACHE_ENABLED,
				FormParamImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		FormParam formParam = (FormParam)serializable;

		if (formParam == null) {
			Session session = null;

			try {
				session = openSession();

				formParam = (FormParam)session.get(FormParamImpl.class,
						primaryKey);

				if (formParam != null) {
					cacheResult(formParam);
				}
				else {
					entityCache.putResult(FormParamModelImpl.ENTITY_CACHE_ENABLED,
						FormParamImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(FormParamModelImpl.ENTITY_CACHE_ENABLED,
					FormParamImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return formParam;
	}

	/**
	 * Returns the form param with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formParamId the primary key of the form param
	 * @return the form param, or <code>null</code> if a form param with the primary key could not be found
	 */
	@Override
	public FormParam fetchByPrimaryKey(long formParamId) {
		return fetchByPrimaryKey((Serializable)formParamId);
	}

	@Override
	public Map<Serializable, FormParam> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, FormParam> map = new HashMap<Serializable, FormParam>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			FormParam formParam = fetchByPrimaryKey(primaryKey);

			if (formParam != null) {
				map.put(primaryKey, formParam);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(FormParamModelImpl.ENTITY_CACHE_ENABLED,
					FormParamImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (FormParam)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FORMPARAM_WHERE_PKS_IN);

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

			for (FormParam formParam : (List<FormParam>)q.list()) {
				map.put(formParam.getPrimaryKeyObj(), formParam);

				cacheResult(formParam);

				uncachedPrimaryKeys.remove(formParam.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(FormParamModelImpl.ENTITY_CACHE_ENABLED,
					FormParamImpl.class, primaryKey, nullModel);
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
	 * Returns all the form params.
	 *
	 * @return the form params
	 */
	@Override
	public List<FormParam> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the form params.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormParamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of form params
	 * @param end the upper bound of the range of form params (not inclusive)
	 * @return the range of form params
	 */
	@Override
	public List<FormParam> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the form params.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormParamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of form params
	 * @param end the upper bound of the range of form params (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of form params
	 */
	@Override
	public List<FormParam> findAll(int start, int end,
		OrderByComparator<FormParam> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the form params.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormParamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of form params
	 * @param end the upper bound of the range of form params (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of form params
	 */
	@Override
	public List<FormParam> findAll(int start, int end,
		OrderByComparator<FormParam> orderByComparator,
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

		List<FormParam> list = null;

		if (retrieveFromCache) {
			list = (List<FormParam>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FORMPARAM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FORMPARAM;

				if (pagination) {
					sql = sql.concat(FormParamModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<FormParam>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormParam>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the form params from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FormParam formParam : findAll()) {
			remove(formParam);
		}
	}

	/**
	 * Returns the number of form params.
	 *
	 * @return the number of form params
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FORMPARAM);

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
		return FormParamModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the form param persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(FormParamImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_FORMPARAM = "SELECT formParam FROM FormParam formParam";
	private static final String _SQL_SELECT_FORMPARAM_WHERE_PKS_IN = "SELECT formParam FROM FormParam formParam WHERE formParamId IN (";
	private static final String _SQL_SELECT_FORMPARAM_WHERE = "SELECT formParam FROM FormParam formParam WHERE ";
	private static final String _SQL_COUNT_FORMPARAM = "SELECT COUNT(formParam) FROM FormParam formParam";
	private static final String _SQL_COUNT_FORMPARAM_WHERE = "SELECT COUNT(formParam) FROM FormParam formParam WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "formParam.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FormParam exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FormParam exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(FormParamPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"data"
			});
}