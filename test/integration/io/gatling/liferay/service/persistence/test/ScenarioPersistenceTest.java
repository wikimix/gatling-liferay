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

import io.gatling.liferay.exception.NoSuchScenarioException;
import io.gatling.liferay.model.Scenario;
import io.gatling.liferay.service.ScenarioLocalServiceUtil;
import io.gatling.liferay.service.persistence.ScenarioPersistence;
import io.gatling.liferay.service.persistence.ScenarioUtil;

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
public class ScenarioPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = ScenarioUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Scenario> iterator = _scenarios.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Scenario scenario = _persistence.create(pk);

		Assert.assertNotNull(scenario);

		Assert.assertEquals(scenario.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Scenario newScenario = addScenario();

		_persistence.remove(newScenario);

		Scenario existingScenario = _persistence.fetchByPrimaryKey(newScenario.getPrimaryKey());

		Assert.assertNull(existingScenario);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addScenario();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Scenario newScenario = _persistence.create(pk);

		newScenario.setName(RandomTestUtil.randomString());

		newScenario.setUrl_site(RandomTestUtil.randomString());

		newScenario.setGroup_id(RandomTestUtil.nextLong());

		newScenario.setSimulation_id(RandomTestUtil.nextLong());

		newScenario.setNumberOfUsers(RandomTestUtil.nextLong());

		newScenario.setDuration(RandomTestUtil.nextLong());

		newScenario.setInjection(RandomTestUtil.randomString());

		_scenarios.add(_persistence.update(newScenario));

		Scenario existingScenario = _persistence.findByPrimaryKey(newScenario.getPrimaryKey());

		Assert.assertEquals(existingScenario.getScenario_id(),
			newScenario.getScenario_id());
		Assert.assertEquals(existingScenario.getName(), newScenario.getName());
		Assert.assertEquals(existingScenario.getUrl_site(),
			newScenario.getUrl_site());
		Assert.assertEquals(existingScenario.getGroup_id(),
			newScenario.getGroup_id());
		Assert.assertEquals(existingScenario.getSimulation_id(),
			newScenario.getSimulation_id());
		Assert.assertEquals(existingScenario.getNumberOfUsers(),
			newScenario.getNumberOfUsers());
		Assert.assertEquals(existingScenario.getDuration(),
			newScenario.getDuration());
		Assert.assertEquals(existingScenario.getInjection(),
			newScenario.getInjection());
	}

	@Test
	public void testCountBySimulationId() throws Exception {
		_persistence.countBySimulationId(RandomTestUtil.nextLong());

		_persistence.countBySimulationId(0L);
	}

	@Test
	public void testCountByName() throws Exception {
		_persistence.countByName(StringPool.BLANK);

		_persistence.countByName(StringPool.NULL);

		_persistence.countByName((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Scenario newScenario = addScenario();

		Scenario existingScenario = _persistence.findByPrimaryKey(newScenario.getPrimaryKey());

		Assert.assertEquals(existingScenario, newScenario);
	}

	@Test(expected = NoSuchScenarioException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<Scenario> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("StressTool_Scenario",
			"scenario_id", true, "name", true, "url_site", true, "group_id",
			true, "simulation_id", true, "numberOfUsers", true, "duration",
			true, "injection", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Scenario newScenario = addScenario();

		Scenario existingScenario = _persistence.fetchByPrimaryKey(newScenario.getPrimaryKey());

		Assert.assertEquals(existingScenario, newScenario);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Scenario missingScenario = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingScenario);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		Scenario newScenario1 = addScenario();
		Scenario newScenario2 = addScenario();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newScenario1.getPrimaryKey());
		primaryKeys.add(newScenario2.getPrimaryKey());

		Map<Serializable, Scenario> scenarios = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, scenarios.size());
		Assert.assertEquals(newScenario1,
			scenarios.get(newScenario1.getPrimaryKey()));
		Assert.assertEquals(newScenario2,
			scenarios.get(newScenario2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Scenario> scenarios = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(scenarios.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		Scenario newScenario = addScenario();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newScenario.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Scenario> scenarios = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, scenarios.size());
		Assert.assertEquals(newScenario,
			scenarios.get(newScenario.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Scenario> scenarios = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(scenarios.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		Scenario newScenario = addScenario();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newScenario.getPrimaryKey());

		Map<Serializable, Scenario> scenarios = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, scenarios.size());
		Assert.assertEquals(newScenario,
			scenarios.get(newScenario.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ScenarioLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Scenario>() {
				@Override
				public void performAction(Scenario scenario) {
					Assert.assertNotNull(scenario);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Scenario newScenario = addScenario();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Scenario.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("scenario_id",
				newScenario.getScenario_id()));

		List<Scenario> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Scenario existingScenario = result.get(0);

		Assert.assertEquals(existingScenario, newScenario);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Scenario.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("scenario_id",
				RandomTestUtil.nextLong()));

		List<Scenario> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Scenario newScenario = addScenario();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Scenario.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("scenario_id"));

		Object newScenario_id = newScenario.getScenario_id();

		dynamicQuery.add(RestrictionsFactoryUtil.in("scenario_id",
				new Object[] { newScenario_id }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingScenario_id = result.get(0);

		Assert.assertEquals(existingScenario_id, newScenario_id);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Scenario.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("scenario_id"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("scenario_id",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Scenario newScenario = addScenario();

		_persistence.clearCache();

		Scenario existingScenario = _persistence.findByPrimaryKey(newScenario.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingScenario.getName(),
				ReflectionTestUtil.invoke(existingScenario, "getOriginalName",
					new Class<?>[0])));
	}

	protected Scenario addScenario() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Scenario scenario = _persistence.create(pk);

		scenario.setName(RandomTestUtil.randomString());

		scenario.setUrl_site(RandomTestUtil.randomString());

		scenario.setGroup_id(RandomTestUtil.nextLong());

		scenario.setSimulation_id(RandomTestUtil.nextLong());

		scenario.setNumberOfUsers(RandomTestUtil.nextLong());

		scenario.setDuration(RandomTestUtil.nextLong());

		scenario.setInjection(RandomTestUtil.randomString());

		_scenarios.add(_persistence.update(scenario));

		return scenario;
	}

	private List<Scenario> _scenarios = new ArrayList<Scenario>();
	private ScenarioPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}