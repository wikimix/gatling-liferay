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

import io.gatling.liferay.exception.NoSuchSimulationException;
import io.gatling.liferay.model.Simulation;
import io.gatling.liferay.service.SimulationLocalServiceUtil;
import io.gatling.liferay.service.persistence.SimulationPersistence;
import io.gatling.liferay.service.persistence.SimulationUtil;

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
public class SimulationPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = SimulationUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Simulation> iterator = _simulations.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Simulation simulation = _persistence.create(pk);

		Assert.assertNotNull(simulation);

		Assert.assertEquals(simulation.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Simulation newSimulation = addSimulation();

		_persistence.remove(newSimulation);

		Simulation existingSimulation = _persistence.fetchByPrimaryKey(newSimulation.getPrimaryKey());

		Assert.assertNull(existingSimulation);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addSimulation();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Simulation newSimulation = _persistence.create(pk);

		newSimulation.setName(RandomTestUtil.randomString());

		newSimulation.setFeederContent(RandomTestUtil.randomString());

		newSimulation.setIsFeederAFile(RandomTestUtil.randomBoolean());

		_simulations.add(_persistence.update(newSimulation));

		Simulation existingSimulation = _persistence.findByPrimaryKey(newSimulation.getPrimaryKey());

		Assert.assertEquals(existingSimulation.getSimulation_id(),
			newSimulation.getSimulation_id());
		Assert.assertEquals(existingSimulation.getName(),
			newSimulation.getName());
		Assert.assertEquals(existingSimulation.getFeederContent(),
			newSimulation.getFeederContent());
		Assert.assertEquals(existingSimulation.getIsFeederAFile(),
			newSimulation.getIsFeederAFile());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Simulation newSimulation = addSimulation();

		Simulation existingSimulation = _persistence.findByPrimaryKey(newSimulation.getPrimaryKey());

		Assert.assertEquals(existingSimulation, newSimulation);
	}

	@Test(expected = NoSuchSimulationException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<Simulation> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("StressTool_Simulation",
			"simulation_id", true, "name", true, "feederContent", true,
			"isFeederAFile", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Simulation newSimulation = addSimulation();

		Simulation existingSimulation = _persistence.fetchByPrimaryKey(newSimulation.getPrimaryKey());

		Assert.assertEquals(existingSimulation, newSimulation);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Simulation missingSimulation = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingSimulation);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		Simulation newSimulation1 = addSimulation();
		Simulation newSimulation2 = addSimulation();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSimulation1.getPrimaryKey());
		primaryKeys.add(newSimulation2.getPrimaryKey());

		Map<Serializable, Simulation> simulations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, simulations.size());
		Assert.assertEquals(newSimulation1,
			simulations.get(newSimulation1.getPrimaryKey()));
		Assert.assertEquals(newSimulation2,
			simulations.get(newSimulation2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Simulation> simulations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(simulations.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		Simulation newSimulation = addSimulation();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSimulation.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Simulation> simulations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, simulations.size());
		Assert.assertEquals(newSimulation,
			simulations.get(newSimulation.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Simulation> simulations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(simulations.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		Simulation newSimulation = addSimulation();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSimulation.getPrimaryKey());

		Map<Serializable, Simulation> simulations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, simulations.size());
		Assert.assertEquals(newSimulation,
			simulations.get(newSimulation.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = SimulationLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Simulation>() {
				@Override
				public void performAction(Simulation simulation) {
					Assert.assertNotNull(simulation);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Simulation newSimulation = addSimulation();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Simulation.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("simulation_id",
				newSimulation.getSimulation_id()));

		List<Simulation> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Simulation existingSimulation = result.get(0);

		Assert.assertEquals(existingSimulation, newSimulation);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Simulation.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("simulation_id",
				RandomTestUtil.nextLong()));

		List<Simulation> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Simulation newSimulation = addSimulation();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Simulation.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"simulation_id"));

		Object newSimulation_id = newSimulation.getSimulation_id();

		dynamicQuery.add(RestrictionsFactoryUtil.in("simulation_id",
				new Object[] { newSimulation_id }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingSimulation_id = result.get(0);

		Assert.assertEquals(existingSimulation_id, newSimulation_id);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Simulation.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"simulation_id"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("simulation_id",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected Simulation addSimulation() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Simulation simulation = _persistence.create(pk);

		simulation.setName(RandomTestUtil.randomString());

		simulation.setFeederContent(RandomTestUtil.randomString());

		simulation.setIsFeederAFile(RandomTestUtil.randomBoolean());

		_simulations.add(_persistence.update(simulation));

		return simulation;
	}

	private List<Simulation> _simulations = new ArrayList<Simulation>();
	private SimulationPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}