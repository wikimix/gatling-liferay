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

import io.gatling.liferay.exception.NoSuchProcessException;
import io.gatling.liferay.model.Process;
import io.gatling.liferay.service.ProcessLocalServiceUtil;
import io.gatling.liferay.service.persistence.ProcessPersistence;
import io.gatling.liferay.service.persistence.ProcessUtil;

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
public class ProcessPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = ProcessUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Process> iterator = _processes.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Process process = _persistence.create(pk);

		Assert.assertNotNull(process);

		Assert.assertEquals(process.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Process newProcess = addProcess();

		_persistence.remove(newProcess);

		Process existingProcess = _persistence.fetchByPrimaryKey(newProcess.getPrimaryKey());

		Assert.assertNull(existingProcess);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addProcess();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Process newProcess = _persistence.create(pk);

		newProcess.setName(RandomTestUtil.randomString());

		newProcess.setType(RandomTestUtil.randomString());

		newProcess.setFeederId(RandomTestUtil.nextLong());

		_processes.add(_persistence.update(newProcess));

		Process existingProcess = _persistence.findByPrimaryKey(newProcess.getPrimaryKey());

		Assert.assertEquals(existingProcess.getProcess_id(),
			newProcess.getProcess_id());
		Assert.assertEquals(existingProcess.getName(), newProcess.getName());
		Assert.assertEquals(existingProcess.getType(), newProcess.getType());
		Assert.assertEquals(existingProcess.getFeederId(),
			newProcess.getFeederId());
	}

	@Test
	public void testCountByName() throws Exception {
		_persistence.countByName(StringPool.BLANK);

		_persistence.countByName(StringPool.NULL);

		_persistence.countByName((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Process newProcess = addProcess();

		Process existingProcess = _persistence.findByPrimaryKey(newProcess.getPrimaryKey());

		Assert.assertEquals(existingProcess, newProcess);
	}

	@Test(expected = NoSuchProcessException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<Process> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("StressTool_Process",
			"process_id", true, "name", true, "type", true, "feederId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Process newProcess = addProcess();

		Process existingProcess = _persistence.fetchByPrimaryKey(newProcess.getPrimaryKey());

		Assert.assertEquals(existingProcess, newProcess);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Process missingProcess = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingProcess);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		Process newProcess1 = addProcess();
		Process newProcess2 = addProcess();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProcess1.getPrimaryKey());
		primaryKeys.add(newProcess2.getPrimaryKey());

		Map<Serializable, Process> processes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, processes.size());
		Assert.assertEquals(newProcess1,
			processes.get(newProcess1.getPrimaryKey()));
		Assert.assertEquals(newProcess2,
			processes.get(newProcess2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Process> processes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(processes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		Process newProcess = addProcess();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProcess.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Process> processes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, processes.size());
		Assert.assertEquals(newProcess,
			processes.get(newProcess.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Process> processes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(processes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		Process newProcess = addProcess();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProcess.getPrimaryKey());

		Map<Serializable, Process> processes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, processes.size());
		Assert.assertEquals(newProcess,
			processes.get(newProcess.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ProcessLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Process>() {
				@Override
				public void performAction(Process process) {
					Assert.assertNotNull(process);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Process newProcess = addProcess();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Process.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("process_id",
				newProcess.getProcess_id()));

		List<Process> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Process existingProcess = result.get(0);

		Assert.assertEquals(existingProcess, newProcess);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Process.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("process_id",
				RandomTestUtil.nextLong()));

		List<Process> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Process newProcess = addProcess();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Process.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("process_id"));

		Object newProcess_id = newProcess.getProcess_id();

		dynamicQuery.add(RestrictionsFactoryUtil.in("process_id",
				new Object[] { newProcess_id }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProcess_id = result.get(0);

		Assert.assertEquals(existingProcess_id, newProcess_id);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Process.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("process_id"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("process_id",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Process newProcess = addProcess();

		_persistence.clearCache();

		Process existingProcess = _persistence.findByPrimaryKey(newProcess.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingProcess.getName(),
				ReflectionTestUtil.invoke(existingProcess, "getOriginalName",
					new Class<?>[0])));
	}

	protected Process addProcess() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Process process = _persistence.create(pk);

		process.setName(RandomTestUtil.randomString());

		process.setType(RandomTestUtil.randomString());

		process.setFeederId(RandomTestUtil.nextLong());

		_processes.add(_persistence.update(process));

		return process;
	}

	private List<Process> _processes = new ArrayList<Process>();
	private ProcessPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}