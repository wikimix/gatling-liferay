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

import io.gatling.liferay.exception.NoSuchRecordException;
import io.gatling.liferay.model.Record;
import io.gatling.liferay.service.RecordLocalServiceUtil;
import io.gatling.liferay.service.persistence.RecordPersistence;
import io.gatling.liferay.service.persistence.RecordUtil;

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
public class RecordPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = RecordUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Record> iterator = _records.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Record record = _persistence.create(pk);

		Assert.assertNotNull(record);

		Assert.assertEquals(record.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Record newRecord = addRecord();

		_persistence.remove(newRecord);

		Record existingRecord = _persistence.fetchByPrimaryKey(newRecord.getPrimaryKey());

		Assert.assertNull(existingRecord);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRecord();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Record newRecord = _persistence.create(pk);

		newRecord.setPortletId(RandomTestUtil.randomString());

		newRecord.setVersionPortlet(RandomTestUtil.randomString());

		newRecord.setName(RandomTestUtil.randomString());

		_records.add(_persistence.update(newRecord));

		Record existingRecord = _persistence.findByPrimaryKey(newRecord.getPrimaryKey());

		Assert.assertEquals(existingRecord.getRecordId(),
			newRecord.getRecordId());
		Assert.assertEquals(existingRecord.getPortletId(),
			newRecord.getPortletId());
		Assert.assertEquals(existingRecord.getVersionPortlet(),
			newRecord.getVersionPortlet());
		Assert.assertEquals(existingRecord.getName(), newRecord.getName());
	}

	@Test
	public void testCountByPortletId() throws Exception {
		_persistence.countByPortletId(StringPool.BLANK);

		_persistence.countByPortletId(StringPool.NULL);

		_persistence.countByPortletId((String)null);
	}

	@Test
	public void testCountByName() throws Exception {
		_persistence.countByName(StringPool.BLANK);

		_persistence.countByName(StringPool.NULL);

		_persistence.countByName((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Record newRecord = addRecord();

		Record existingRecord = _persistence.findByPrimaryKey(newRecord.getPrimaryKey());

		Assert.assertEquals(existingRecord, newRecord);
	}

	@Test(expected = NoSuchRecordException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<Record> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("StressTool_Record",
			"recordId", true, "portletId", true, "versionPortlet", true,
			"name", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Record newRecord = addRecord();

		Record existingRecord = _persistence.fetchByPrimaryKey(newRecord.getPrimaryKey());

		Assert.assertEquals(existingRecord, newRecord);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Record missingRecord = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRecord);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		Record newRecord1 = addRecord();
		Record newRecord2 = addRecord();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRecord1.getPrimaryKey());
		primaryKeys.add(newRecord2.getPrimaryKey());

		Map<Serializable, Record> records = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, records.size());
		Assert.assertEquals(newRecord1, records.get(newRecord1.getPrimaryKey()));
		Assert.assertEquals(newRecord2, records.get(newRecord2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Record> records = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(records.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		Record newRecord = addRecord();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRecord.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Record> records = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, records.size());
		Assert.assertEquals(newRecord, records.get(newRecord.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Record> records = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(records.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		Record newRecord = addRecord();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRecord.getPrimaryKey());

		Map<Serializable, Record> records = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, records.size());
		Assert.assertEquals(newRecord, records.get(newRecord.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RecordLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Record>() {
				@Override
				public void performAction(Record record) {
					Assert.assertNotNull(record);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Record newRecord = addRecord();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Record.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("recordId",
				newRecord.getRecordId()));

		List<Record> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Record existingRecord = result.get(0);

		Assert.assertEquals(existingRecord, newRecord);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Record.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("recordId",
				RandomTestUtil.nextLong()));

		List<Record> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Record newRecord = addRecord();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Record.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("recordId"));

		Object newRecordId = newRecord.getRecordId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("recordId",
				new Object[] { newRecordId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRecordId = result.get(0);

		Assert.assertEquals(existingRecordId, newRecordId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Record.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("recordId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("recordId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Record newRecord = addRecord();

		_persistence.clearCache();

		Record existingRecord = _persistence.findByPrimaryKey(newRecord.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingRecord.getName(),
				ReflectionTestUtil.invoke(existingRecord, "getOriginalName",
					new Class<?>[0])));
	}

	protected Record addRecord() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Record record = _persistence.create(pk);

		record.setPortletId(RandomTestUtil.randomString());

		record.setVersionPortlet(RandomTestUtil.randomString());

		record.setName(RandomTestUtil.randomString());

		_records.add(_persistence.update(record));

		return record;
	}

	private List<Record> _records = new ArrayList<Record>();
	private RecordPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}