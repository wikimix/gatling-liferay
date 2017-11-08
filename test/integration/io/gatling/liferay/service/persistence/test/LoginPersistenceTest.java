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

package io.gatling.liferay.service.persistence.test;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import io.gatling.liferay.exception.NoSuchLoginException;
import io.gatling.liferay.model.Login;
import io.gatling.liferay.service.LoginLocalServiceUtil;
import io.gatling.liferay.service.persistence.LoginPersistence;
import io.gatling.liferay.service.persistence.LoginUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
public class LoginPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = LoginUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Login> iterator = _logins.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Login login = _persistence.create(pk);

		Assert.assertNotNull(login);

		Assert.assertEquals(login.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Login newLogin = addLogin();

		_persistence.remove(newLogin);

		Login existingLogin = _persistence.fetchByPrimaryKey(newLogin.getPrimaryKey());

		Assert.assertNull(existingLogin);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLogin();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Login newLogin = _persistence.create(pk);

		newLogin.setName(RandomTestUtil.randomString());

		newLogin.setData(RandomTestUtil.randomString());

		_logins.add(_persistence.update(newLogin));

		Login existingLogin = _persistence.findByPrimaryKey(newLogin.getPrimaryKey());

		Assert.assertEquals(existingLogin.getUserId(), newLogin.getUserId());
		Assert.assertEquals(existingLogin.getName(), newLogin.getName());
		Assert.assertEquals(existingLogin.getData(), newLogin.getData());
	}

	@Test
	public void testCountByName() throws Exception {
		_persistence.countByName(StringPool.BLANK);

		_persistence.countByName(StringPool.NULL);

		_persistence.countByName((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Login newLogin = addLogin();

		Login existingLogin = _persistence.findByPrimaryKey(newLogin.getPrimaryKey());

		Assert.assertEquals(existingLogin, newLogin);
	}

	@Test(expected = NoSuchLoginException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<Login> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("StressTool_Login",
			"userId", true, "name", true, "data", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Login newLogin = addLogin();

		Login existingLogin = _persistence.fetchByPrimaryKey(newLogin.getPrimaryKey());

		Assert.assertEquals(existingLogin, newLogin);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Login missingLogin = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLogin);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		Login newLogin1 = addLogin();
		Login newLogin2 = addLogin();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLogin1.getPrimaryKey());
		primaryKeys.add(newLogin2.getPrimaryKey());

		Map<Serializable, Login> logins = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, logins.size());
		Assert.assertEquals(newLogin1, logins.get(newLogin1.getPrimaryKey()));
		Assert.assertEquals(newLogin2, logins.get(newLogin2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Login> logins = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(logins.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		Login newLogin = addLogin();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLogin.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Login> logins = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, logins.size());
		Assert.assertEquals(newLogin, logins.get(newLogin.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Login> logins = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(logins.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		Login newLogin = addLogin();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLogin.getPrimaryKey());

		Map<Serializable, Login> logins = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, logins.size());
		Assert.assertEquals(newLogin, logins.get(newLogin.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LoginLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Login>() {
				@Override
				public void performAction(Login login) {
					Assert.assertNotNull(login);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Login newLogin = addLogin();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Login.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("userId",
				newLogin.getUserId()));

		List<Login> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Login existingLogin = result.get(0);

		Assert.assertEquals(existingLogin, newLogin);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Login.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("userId",
				RandomTestUtil.nextLong()));

		List<Login> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Login newLogin = addLogin();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Login.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("userId"));

		Object newUserId = newLogin.getUserId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("userId",
				new Object[] { newUserId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUserId = result.get(0);

		Assert.assertEquals(existingUserId, newUserId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Login.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("userId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("userId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Login newLogin = addLogin();

		_persistence.clearCache();

		Login existingLogin = _persistence.findByPrimaryKey(newLogin.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingLogin.getName(),
				ReflectionTestUtil.invoke(existingLogin, "getOriginalName",
					new Class<?>[0])));
	}

	protected Login addLogin() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Login login = _persistence.create(pk);

		login.setName(RandomTestUtil.randomString());

		login.setData(RandomTestUtil.randomString());

		_logins.add(_persistence.update(login));

		return login;
	}

	private List<Login> _logins = new ArrayList<Login>();
	private LoginPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}