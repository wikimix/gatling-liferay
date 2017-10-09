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
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import io.gatling.liferay.exception.NoSuchUrlRecordException;
import io.gatling.liferay.model.UrlRecord;
import io.gatling.liferay.service.UrlRecordLocalServiceUtil;
import io.gatling.liferay.service.persistence.UrlRecordPersistence;
import io.gatling.liferay.service.persistence.UrlRecordUtil;

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
public class UrlRecordPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = UrlRecordUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<UrlRecord> iterator = _urlRecords.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UrlRecord urlRecord = _persistence.create(pk);

		Assert.assertNotNull(urlRecord);

		Assert.assertEquals(urlRecord.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		UrlRecord newUrlRecord = addUrlRecord();

		_persistence.remove(newUrlRecord);

		UrlRecord existingUrlRecord = _persistence.fetchByPrimaryKey(newUrlRecord.getPrimaryKey());

		Assert.assertNull(existingUrlRecord);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addUrlRecord();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UrlRecord newUrlRecord = _persistence.create(pk);

		newUrlRecord.setRecordId(RandomTestUtil.nextLong());

		newUrlRecord.setUrl(RandomTestUtil.randomString());

		newUrlRecord.setType(RandomTestUtil.randomString());

		newUrlRecord.setOrder(RandomTestUtil.nextInt());

		newUrlRecord.setPauseTime(RandomTestUtil.nextInt());

		_urlRecords.add(_persistence.update(newUrlRecord));

		UrlRecord existingUrlRecord = _persistence.findByPrimaryKey(newUrlRecord.getPrimaryKey());

		Assert.assertEquals(existingUrlRecord.getUrlRecordId(),
			newUrlRecord.getUrlRecordId());
		Assert.assertEquals(existingUrlRecord.getRecordId(),
			newUrlRecord.getRecordId());
		Assert.assertEquals(existingUrlRecord.getUrl(), newUrlRecord.getUrl());
		Assert.assertEquals(existingUrlRecord.getType(), newUrlRecord.getType());
		Assert.assertEquals(existingUrlRecord.getOrder(),
			newUrlRecord.getOrder());
		Assert.assertEquals(existingUrlRecord.getPauseTime(),
			newUrlRecord.getPauseTime());
	}

	@Test
	public void testCountByRecordId() throws Exception {
		_persistence.countByRecordId(RandomTestUtil.nextLong());

		_persistence.countByRecordId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		UrlRecord newUrlRecord = addUrlRecord();

		UrlRecord existingUrlRecord = _persistence.findByPrimaryKey(newUrlRecord.getPrimaryKey());

		Assert.assertEquals(existingUrlRecord, newUrlRecord);
	}

	@Test(expected = NoSuchUrlRecordException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<UrlRecord> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("StressTool_UrlRecord",
			"urlRecordId", true, "recordId", true, "url", true, "type", true,
			"order", true, "pauseTime", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		UrlRecord newUrlRecord = addUrlRecord();

		UrlRecord existingUrlRecord = _persistence.fetchByPrimaryKey(newUrlRecord.getPrimaryKey());

		Assert.assertEquals(existingUrlRecord, newUrlRecord);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UrlRecord missingUrlRecord = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingUrlRecord);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		UrlRecord newUrlRecord1 = addUrlRecord();
		UrlRecord newUrlRecord2 = addUrlRecord();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUrlRecord1.getPrimaryKey());
		primaryKeys.add(newUrlRecord2.getPrimaryKey());

		Map<Serializable, UrlRecord> urlRecords = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, urlRecords.size());
		Assert.assertEquals(newUrlRecord1,
			urlRecords.get(newUrlRecord1.getPrimaryKey()));
		Assert.assertEquals(newUrlRecord2,
			urlRecords.get(newUrlRecord2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, UrlRecord> urlRecords = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(urlRecords.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		UrlRecord newUrlRecord = addUrlRecord();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUrlRecord.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, UrlRecord> urlRecords = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, urlRecords.size());
		Assert.assertEquals(newUrlRecord,
			urlRecords.get(newUrlRecord.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, UrlRecord> urlRecords = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(urlRecords.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		UrlRecord newUrlRecord = addUrlRecord();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUrlRecord.getPrimaryKey());

		Map<Serializable, UrlRecord> urlRecords = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, urlRecords.size());
		Assert.assertEquals(newUrlRecord,
			urlRecords.get(newUrlRecord.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = UrlRecordLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<UrlRecord>() {
				@Override
				public void performAction(UrlRecord urlRecord) {
					Assert.assertNotNull(urlRecord);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		UrlRecord newUrlRecord = addUrlRecord();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UrlRecord.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("urlRecordId",
				newUrlRecord.getUrlRecordId()));

		List<UrlRecord> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		UrlRecord existingUrlRecord = result.get(0);

		Assert.assertEquals(existingUrlRecord, newUrlRecord);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UrlRecord.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("urlRecordId",
				RandomTestUtil.nextLong()));

		List<UrlRecord> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		UrlRecord newUrlRecord = addUrlRecord();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UrlRecord.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("urlRecordId"));

		Object newUrlRecordId = newUrlRecord.getUrlRecordId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("urlRecordId",
				new Object[] { newUrlRecordId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUrlRecordId = result.get(0);

		Assert.assertEquals(existingUrlRecordId, newUrlRecordId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UrlRecord.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("urlRecordId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("urlRecordId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected UrlRecord addUrlRecord() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UrlRecord urlRecord = _persistence.create(pk);

		urlRecord.setRecordId(RandomTestUtil.nextLong());

		urlRecord.setUrl(RandomTestUtil.randomString());

		urlRecord.setType(RandomTestUtil.randomString());

		urlRecord.setOrder(RandomTestUtil.nextInt());

		urlRecord.setPauseTime(RandomTestUtil.nextInt());

		_urlRecords.add(_persistence.update(urlRecord));

		return urlRecord;
	}

	private List<UrlRecord> _urlRecords = new ArrayList<UrlRecord>();
	private UrlRecordPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}