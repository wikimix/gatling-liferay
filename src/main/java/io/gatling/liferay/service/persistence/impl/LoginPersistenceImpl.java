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

import io.gatling.liferay.exception.NoSuchLoginException;
import io.gatling.liferay.model.Login;
import io.gatling.liferay.model.impl.LoginImpl;
import io.gatling.liferay.model.impl.LoginModelImpl;
import io.gatling.liferay.service.persistence.LoginPersistence;

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
 * The persistence implementation for the login service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoginPersistence
 * @see io.gatling.liferay.service.persistence.LoginUtil
 * @generated
 */
@ProviderType
public class LoginPersistenceImpl extends BasePersistenceImpl<Login>
	implements LoginPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LoginUtil} to access the login persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LoginImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoginModelImpl.ENTITY_CACHE_ENABLED,
			LoginModelImpl.FINDER_CACHE_ENABLED, LoginImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoginModelImpl.ENTITY_CACHE_ENABLED,
			LoginModelImpl.FINDER_CACHE_ENABLED, LoginImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoginModelImpl.ENTITY_CACHE_ENABLED,
			LoginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(LoginModelImpl.ENTITY_CACHE_ENABLED,
			LoginModelImpl.FINDER_CACHE_ENABLED, LoginImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] { String.class.getName() },
			LoginModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(LoginModelImpl.ENTITY_CACHE_ENABLED,
			LoginModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the login where name = &#63; or throws a {@link NoSuchLoginException} if it could not be found.
	 *
	 * @param name the name
	 * @return the matching login
	 * @throws NoSuchLoginException if a matching login could not be found
	 */
	@Override
	public Login findByName(String name) throws NoSuchLoginException {
		Login login = fetchByName(name);

		if (login == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLoginException(msg.toString());
		}

		return login;
	}

	/**
	 * Returns the login where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching login, or <code>null</code> if a matching login could not be found
	 */
	@Override
	public Login fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the login where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching login, or <code>null</code> if a matching login could not be found
	 */
	@Override
	public Login fetchByName(String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result instanceof Login) {
			Login login = (Login)result;

			if (!Objects.equals(name, login.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_LOGIN_WHERE);

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

				List<Login> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					Login login = list.get(0);

					result = login;

					cacheResult(login);

					if ((login.getName() == null) ||
							!login.getName().equals(name)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, login);
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
			return (Login)result;
		}
	}

	/**
	 * Removes the login where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the login that was removed
	 */
	@Override
	public Login removeByName(String name) throws NoSuchLoginException {
		Login login = findByName(name);

		return remove(login);
	}

	/**
	 * Returns the number of logins where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching logins
	 */
	@Override
	public int countByName(String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LOGIN_WHERE);

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

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "login.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "login.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(login.name IS NULL OR login.name = '')";

	public LoginPersistenceImpl() {
		setModelClass(Login.class);
	}

	/**
	 * Caches the login in the entity cache if it is enabled.
	 *
	 * @param login the login
	 */
	@Override
	public void cacheResult(Login login) {
		entityCache.putResult(LoginModelImpl.ENTITY_CACHE_ENABLED,
			LoginImpl.class, login.getPrimaryKey(), login);

		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { login.getName() }, login);

		login.resetOriginalValues();
	}

	/**
	 * Caches the logins in the entity cache if it is enabled.
	 *
	 * @param logins the logins
	 */
	@Override
	public void cacheResult(List<Login> logins) {
		for (Login login : logins) {
			if (entityCache.getResult(LoginModelImpl.ENTITY_CACHE_ENABLED,
						LoginImpl.class, login.getPrimaryKey()) == null) {
				cacheResult(login);
			}
			else {
				login.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all logins.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LoginImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the login.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Login login) {
		entityCache.removeResult(LoginModelImpl.ENTITY_CACHE_ENABLED,
			LoginImpl.class, login.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LoginModelImpl)login, true);
	}

	@Override
	public void clearCache(List<Login> logins) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Login login : logins) {
			entityCache.removeResult(LoginModelImpl.ENTITY_CACHE_ENABLED,
				LoginImpl.class, login.getPrimaryKey());

			clearUniqueFindersCache((LoginModelImpl)login, true);
		}
	}

	protected void cacheUniqueFindersCache(LoginModelImpl loginModelImpl) {
		Object[] args = new Object[] { loginModelImpl.getName() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_NAME, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_NAME, args, loginModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(LoginModelImpl loginModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { loginModelImpl.getName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}

		if ((loginModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { loginModelImpl.getOriginalName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_NAME, args);
		}
	}

	/**
	 * Creates a new login with the primary key. Does not add the login to the database.
	 *
	 * @param userId the primary key for the new login
	 * @return the new login
	 */
	@Override
	public Login create(long userId) {
		Login login = new LoginImpl();

		login.setNew(true);
		login.setPrimaryKey(userId);

		return login;
	}

	/**
	 * Removes the login with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the login
	 * @return the login that was removed
	 * @throws NoSuchLoginException if a login with the primary key could not be found
	 */
	@Override
	public Login remove(long userId) throws NoSuchLoginException {
		return remove((Serializable)userId);
	}

	/**
	 * Removes the login with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the login
	 * @return the login that was removed
	 * @throws NoSuchLoginException if a login with the primary key could not be found
	 */
	@Override
	public Login remove(Serializable primaryKey) throws NoSuchLoginException {
		Session session = null;

		try {
			session = openSession();

			Login login = (Login)session.get(LoginImpl.class, primaryKey);

			if (login == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLoginException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(login);
		}
		catch (NoSuchLoginException nsee) {
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
	protected Login removeImpl(Login login) {
		login = toUnwrappedModel(login);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(login)) {
				login = (Login)session.get(LoginImpl.class,
						login.getPrimaryKeyObj());
			}

			if (login != null) {
				session.delete(login);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (login != null) {
			clearCache(login);
		}

		return login;
	}

	@Override
	public Login updateImpl(Login login) {
		login = toUnwrappedModel(login);

		boolean isNew = login.isNew();

		LoginModelImpl loginModelImpl = (LoginModelImpl)login;

		Session session = null;

		try {
			session = openSession();

			if (login.isNew()) {
				session.save(login);

				login.setNew(false);
			}
			else {
				login = (Login)session.merge(login);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LoginModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(LoginModelImpl.ENTITY_CACHE_ENABLED,
			LoginImpl.class, login.getPrimaryKey(), login, false);

		clearUniqueFindersCache(loginModelImpl, false);
		cacheUniqueFindersCache(loginModelImpl);

		login.resetOriginalValues();

		return login;
	}

	protected Login toUnwrappedModel(Login login) {
		if (login instanceof LoginImpl) {
			return login;
		}

		LoginImpl loginImpl = new LoginImpl();

		loginImpl.setNew(login.isNew());
		loginImpl.setPrimaryKey(login.getPrimaryKey());

		loginImpl.setUserId(login.getUserId());
		loginImpl.setName(login.getName());
		loginImpl.setData(login.getData());

		return loginImpl;
	}

	/**
	 * Returns the login with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the login
	 * @return the login
	 * @throws NoSuchLoginException if a login with the primary key could not be found
	 */
	@Override
	public Login findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLoginException {
		Login login = fetchByPrimaryKey(primaryKey);

		if (login == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLoginException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return login;
	}

	/**
	 * Returns the login with the primary key or throws a {@link NoSuchLoginException} if it could not be found.
	 *
	 * @param userId the primary key of the login
	 * @return the login
	 * @throws NoSuchLoginException if a login with the primary key could not be found
	 */
	@Override
	public Login findByPrimaryKey(long userId) throws NoSuchLoginException {
		return findByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns the login with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the login
	 * @return the login, or <code>null</code> if a login with the primary key could not be found
	 */
	@Override
	public Login fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(LoginModelImpl.ENTITY_CACHE_ENABLED,
				LoginImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Login login = (Login)serializable;

		if (login == null) {
			Session session = null;

			try {
				session = openSession();

				login = (Login)session.get(LoginImpl.class, primaryKey);

				if (login != null) {
					cacheResult(login);
				}
				else {
					entityCache.putResult(LoginModelImpl.ENTITY_CACHE_ENABLED,
						LoginImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(LoginModelImpl.ENTITY_CACHE_ENABLED,
					LoginImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return login;
	}

	/**
	 * Returns the login with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the login
	 * @return the login, or <code>null</code> if a login with the primary key could not be found
	 */
	@Override
	public Login fetchByPrimaryKey(long userId) {
		return fetchByPrimaryKey((Serializable)userId);
	}

	@Override
	public Map<Serializable, Login> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Login> map = new HashMap<Serializable, Login>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Login login = fetchByPrimaryKey(primaryKey);

			if (login != null) {
				map.put(primaryKey, login);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(LoginModelImpl.ENTITY_CACHE_ENABLED,
					LoginImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Login)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_LOGIN_WHERE_PKS_IN);

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

			for (Login login : (List<Login>)q.list()) {
				map.put(login.getPrimaryKeyObj(), login);

				cacheResult(login);

				uncachedPrimaryKeys.remove(login.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(LoginModelImpl.ENTITY_CACHE_ENABLED,
					LoginImpl.class, primaryKey, nullModel);
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
	 * Returns all the logins.
	 *
	 * @return the logins
	 */
	@Override
	public List<Login> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the logins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of logins
	 * @param end the upper bound of the range of logins (not inclusive)
	 * @return the range of logins
	 */
	@Override
	public List<Login> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the logins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of logins
	 * @param end the upper bound of the range of logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of logins
	 */
	@Override
	public List<Login> findAll(int start, int end,
		OrderByComparator<Login> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the logins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of logins
	 * @param end the upper bound of the range of logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of logins
	 */
	@Override
	public List<Login> findAll(int start, int end,
		OrderByComparator<Login> orderByComparator, boolean retrieveFromCache) {
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

		List<Login> list = null;

		if (retrieveFromCache) {
			list = (List<Login>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LOGIN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LOGIN;

				if (pagination) {
					sql = sql.concat(LoginModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Login>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Login>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the logins from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Login login : findAll()) {
			remove(login);
		}
	}

	/**
	 * Returns the number of logins.
	 *
	 * @return the number of logins
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LOGIN);

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
		return LoginModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the login persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(LoginImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_LOGIN = "SELECT login FROM Login login";
	private static final String _SQL_SELECT_LOGIN_WHERE_PKS_IN = "SELECT login FROM Login login WHERE userId IN (";
	private static final String _SQL_SELECT_LOGIN_WHERE = "SELECT login FROM Login login WHERE ";
	private static final String _SQL_COUNT_LOGIN = "SELECT COUNT(login) FROM Login login";
	private static final String _SQL_COUNT_LOGIN_WHERE = "SELECT COUNT(login) FROM Login login WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "login.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Login exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Login exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(LoginPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"data"
			});
}