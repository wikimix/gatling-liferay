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
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import io.gatling.liferay.exception.NoSuchUrlSiteMapException;
import io.gatling.liferay.model.UrlSiteMap;
import io.gatling.liferay.service.UrlSiteMapLocalServiceUtil;
import io.gatling.liferay.service.persistence.UrlSiteMapPersistence;
import io.gatling.liferay.service.persistence.UrlSiteMapUtil;

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
import java.util.Set;

/**
 * @generated
 */
public class UrlSiteMapPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = UrlSiteMapUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<UrlSiteMap> iterator = _urlSiteMaps.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UrlSiteMap urlSiteMap = _persistence.create(pk);

		Assert.assertNotNull(urlSiteMap);

		Assert.assertEquals(urlSiteMap.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		UrlSiteMap newUrlSiteMap = addUrlSiteMap();

		_persistence.remove(newUrlSiteMap);

		UrlSiteMap existingUrlSiteMap = _persistence.fetchByPrimaryKey(newUrlSiteMap.getPrimaryKey());

		Assert.assertNull(existingUrlSiteMap);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addUrlSiteMap();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UrlSiteMap newUrlSiteMap = _persistence.create(pk);

		newUrlSiteMap.setSiteMapId(RandomTestUtil.nextLong());

		newUrlSiteMap.setGroup(RandomTestUtil.randomString());

		newUrlSiteMap.setFriendlyUrl(RandomTestUtil.randomString());

		newUrlSiteMap.setUrl(RandomTestUtil.randomString());

		newUrlSiteMap.setWeight(RandomTestUtil.nextInt());

		_urlSiteMaps.add(_persistence.update(newUrlSiteMap));

		UrlSiteMap existingUrlSiteMap = _persistence.findByPrimaryKey(newUrlSiteMap.getPrimaryKey());

		Assert.assertEquals(existingUrlSiteMap.getUrlSiteMapId(),
			newUrlSiteMap.getUrlSiteMapId());
		Assert.assertEquals(existingUrlSiteMap.getSiteMapId(),
			newUrlSiteMap.getSiteMapId());
		Assert.assertEquals(existingUrlSiteMap.getGroup(),
			newUrlSiteMap.getGroup());
		Assert.assertEquals(existingUrlSiteMap.getFriendlyUrl(),
			newUrlSiteMap.getFriendlyUrl());
		Assert.assertEquals(existingUrlSiteMap.getUrl(), newUrlSiteMap.getUrl());
		Assert.assertEquals(existingUrlSiteMap.getWeight(),
			newUrlSiteMap.getWeight());
	}

	@Test
	public void testCountBySiteMapId() throws Exception {
		_persistence.countBySiteMapId(RandomTestUtil.nextLong());

		_persistence.countBySiteMapId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		UrlSiteMap newUrlSiteMap = addUrlSiteMap();

		UrlSiteMap existingUrlSiteMap = _persistence.findByPrimaryKey(newUrlSiteMap.getPrimaryKey());

		Assert.assertEquals(existingUrlSiteMap, newUrlSiteMap);
	}

	@Test(expected = NoSuchUrlSiteMapException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<UrlSiteMap> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("StressTool_UrlSiteMap",
			"urlSiteMapId", true, "siteMapId", true, "group", true,
			"friendlyUrl", true, "url", true, "weight", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		UrlSiteMap newUrlSiteMap = addUrlSiteMap();

		UrlSiteMap existingUrlSiteMap = _persistence.fetchByPrimaryKey(newUrlSiteMap.getPrimaryKey());

		Assert.assertEquals(existingUrlSiteMap, newUrlSiteMap);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UrlSiteMap missingUrlSiteMap = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingUrlSiteMap);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		UrlSiteMap newUrlSiteMap1 = addUrlSiteMap();
		UrlSiteMap newUrlSiteMap2 = addUrlSiteMap();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUrlSiteMap1.getPrimaryKey());
		primaryKeys.add(newUrlSiteMap2.getPrimaryKey());

		Map<Serializable, UrlSiteMap> urlSiteMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, urlSiteMaps.size());
		Assert.assertEquals(newUrlSiteMap1,
			urlSiteMaps.get(newUrlSiteMap1.getPrimaryKey()));
		Assert.assertEquals(newUrlSiteMap2,
			urlSiteMaps.get(newUrlSiteMap2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, UrlSiteMap> urlSiteMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(urlSiteMaps.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		UrlSiteMap newUrlSiteMap = addUrlSiteMap();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUrlSiteMap.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, UrlSiteMap> urlSiteMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, urlSiteMaps.size());
		Assert.assertEquals(newUrlSiteMap,
			urlSiteMaps.get(newUrlSiteMap.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, UrlSiteMap> urlSiteMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(urlSiteMaps.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		UrlSiteMap newUrlSiteMap = addUrlSiteMap();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUrlSiteMap.getPrimaryKey());

		Map<Serializable, UrlSiteMap> urlSiteMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, urlSiteMaps.size());
		Assert.assertEquals(newUrlSiteMap,
			urlSiteMaps.get(newUrlSiteMap.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = UrlSiteMapLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<UrlSiteMap>() {
				@Override
				public void performAction(UrlSiteMap urlSiteMap) {
					Assert.assertNotNull(urlSiteMap);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		UrlSiteMap newUrlSiteMap = addUrlSiteMap();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UrlSiteMap.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("urlSiteMapId",
				newUrlSiteMap.getUrlSiteMapId()));

		List<UrlSiteMap> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		UrlSiteMap existingUrlSiteMap = result.get(0);

		Assert.assertEquals(existingUrlSiteMap, newUrlSiteMap);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UrlSiteMap.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("urlSiteMapId",
				RandomTestUtil.nextLong()));

		List<UrlSiteMap> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		UrlSiteMap newUrlSiteMap = addUrlSiteMap();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UrlSiteMap.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"urlSiteMapId"));

		Object newUrlSiteMapId = newUrlSiteMap.getUrlSiteMapId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("urlSiteMapId",
				new Object[] { newUrlSiteMapId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUrlSiteMapId = result.get(0);

		Assert.assertEquals(existingUrlSiteMapId, newUrlSiteMapId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UrlSiteMap.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"urlSiteMapId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("urlSiteMapId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected UrlSiteMap addUrlSiteMap() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UrlSiteMap urlSiteMap = _persistence.create(pk);

		urlSiteMap.setSiteMapId(RandomTestUtil.nextLong());

		urlSiteMap.setGroup(RandomTestUtil.randomString());

		urlSiteMap.setFriendlyUrl(RandomTestUtil.randomString());

		urlSiteMap.setUrl(RandomTestUtil.randomString());

		urlSiteMap.setWeight(RandomTestUtil.nextInt());

		_urlSiteMaps.add(_persistence.update(urlSiteMap));

		return urlSiteMap;
	}

	private List<UrlSiteMap> _urlSiteMaps = new ArrayList<UrlSiteMap>();
	private UrlSiteMapPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}