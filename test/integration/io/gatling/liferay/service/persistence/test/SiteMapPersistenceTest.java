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

import io.gatling.liferay.exception.NoSuchSiteMapException;
import io.gatling.liferay.model.SiteMap;
import io.gatling.liferay.service.SiteMapLocalServiceUtil;
import io.gatling.liferay.service.persistence.SiteMapPersistence;
import io.gatling.liferay.service.persistence.SiteMapUtil;

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
public class SiteMapPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = SiteMapUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<SiteMap> iterator = _siteMaps.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SiteMap siteMap = _persistence.create(pk);

		Assert.assertNotNull(siteMap);

		Assert.assertEquals(siteMap.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		SiteMap newSiteMap = addSiteMap();

		_persistence.remove(newSiteMap);

		SiteMap existingSiteMap = _persistence.fetchByPrimaryKey(newSiteMap.getPrimaryKey());

		Assert.assertNull(existingSiteMap);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addSiteMap();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SiteMap newSiteMap = _persistence.create(pk);

		newSiteMap.setName(RandomTestUtil.randomString());

		_siteMaps.add(_persistence.update(newSiteMap));

		SiteMap existingSiteMap = _persistence.findByPrimaryKey(newSiteMap.getPrimaryKey());

		Assert.assertEquals(existingSiteMap.getSiteMapId(),
			newSiteMap.getSiteMapId());
		Assert.assertEquals(existingSiteMap.getName(), newSiteMap.getName());
	}

	@Test
	public void testCountByName() throws Exception {
		_persistence.countByName(StringPool.BLANK);

		_persistence.countByName(StringPool.NULL);

		_persistence.countByName((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		SiteMap newSiteMap = addSiteMap();

		SiteMap existingSiteMap = _persistence.findByPrimaryKey(newSiteMap.getPrimaryKey());

		Assert.assertEquals(existingSiteMap, newSiteMap);
	}

	@Test(expected = NoSuchSiteMapException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<SiteMap> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("StressTool_SiteMap",
			"siteMapId", true, "name", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		SiteMap newSiteMap = addSiteMap();

		SiteMap existingSiteMap = _persistence.fetchByPrimaryKey(newSiteMap.getPrimaryKey());

		Assert.assertEquals(existingSiteMap, newSiteMap);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SiteMap missingSiteMap = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingSiteMap);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		SiteMap newSiteMap1 = addSiteMap();
		SiteMap newSiteMap2 = addSiteMap();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSiteMap1.getPrimaryKey());
		primaryKeys.add(newSiteMap2.getPrimaryKey());

		Map<Serializable, SiteMap> siteMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, siteMaps.size());
		Assert.assertEquals(newSiteMap1,
			siteMaps.get(newSiteMap1.getPrimaryKey()));
		Assert.assertEquals(newSiteMap2,
			siteMaps.get(newSiteMap2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, SiteMap> siteMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(siteMaps.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		SiteMap newSiteMap = addSiteMap();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSiteMap.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, SiteMap> siteMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, siteMaps.size());
		Assert.assertEquals(newSiteMap, siteMaps.get(newSiteMap.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, SiteMap> siteMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(siteMaps.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		SiteMap newSiteMap = addSiteMap();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSiteMap.getPrimaryKey());

		Map<Serializable, SiteMap> siteMaps = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, siteMaps.size());
		Assert.assertEquals(newSiteMap, siteMaps.get(newSiteMap.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = SiteMapLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<SiteMap>() {
				@Override
				public void performAction(SiteMap siteMap) {
					Assert.assertNotNull(siteMap);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		SiteMap newSiteMap = addSiteMap();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SiteMap.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("siteMapId",
				newSiteMap.getSiteMapId()));

		List<SiteMap> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		SiteMap existingSiteMap = result.get(0);

		Assert.assertEquals(existingSiteMap, newSiteMap);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SiteMap.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("siteMapId",
				RandomTestUtil.nextLong()));

		List<SiteMap> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		SiteMap newSiteMap = addSiteMap();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SiteMap.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("siteMapId"));

		Object newSiteMapId = newSiteMap.getSiteMapId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("siteMapId",
				new Object[] { newSiteMapId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingSiteMapId = result.get(0);

		Assert.assertEquals(existingSiteMapId, newSiteMapId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SiteMap.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("siteMapId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("siteMapId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		SiteMap newSiteMap = addSiteMap();

		_persistence.clearCache();

		SiteMap existingSiteMap = _persistence.findByPrimaryKey(newSiteMap.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingSiteMap.getName(),
				ReflectionTestUtil.invoke(existingSiteMap, "getOriginalName",
					new Class<?>[0])));
	}

	protected SiteMap addSiteMap() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SiteMap siteMap = _persistence.create(pk);

		siteMap.setName(RandomTestUtil.randomString());

		_siteMaps.add(_persistence.update(siteMap));

		return siteMap;
	}

	private List<SiteMap> _siteMaps = new ArrayList<SiteMap>();
	private SiteMapPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}