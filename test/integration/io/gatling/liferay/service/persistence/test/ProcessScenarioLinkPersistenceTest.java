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
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import io.gatling.liferay.exception.NoSuchProcessScenarioLinkException;
import io.gatling.liferay.model.ProcessScenarioLink;
import io.gatling.liferay.service.ProcessScenarioLinkLocalServiceUtil;
import io.gatling.liferay.service.persistence.ProcessScenarioLinkPersistence;
import io.gatling.liferay.service.persistence.ProcessScenarioLinkUtil;

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
public class ProcessScenarioLinkPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = ProcessScenarioLinkUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ProcessScenarioLink> iterator = _processScenarioLinks.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ProcessScenarioLink processScenarioLink = _persistence.create(pk);

		Assert.assertNotNull(processScenarioLink);

		Assert.assertEquals(processScenarioLink.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ProcessScenarioLink newProcessScenarioLink = addProcessScenarioLink();

		_persistence.remove(newProcessScenarioLink);

		ProcessScenarioLink existingProcessScenarioLink = _persistence.fetchByPrimaryKey(newProcessScenarioLink.getPrimaryKey());

		Assert.assertNull(existingProcessScenarioLink);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addProcessScenarioLink();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ProcessScenarioLink newProcessScenarioLink = _persistence.create(pk);

		newProcessScenarioLink.setProcess_id(RandomTestUtil.nextLong());

		newProcessScenarioLink.setScenario_id(RandomTestUtil.nextLong());

		newProcessScenarioLink.setOrder(RandomTestUtil.nextInt());

		newProcessScenarioLink.setPause(RandomTestUtil.nextInt());

		_processScenarioLinks.add(_persistence.update(newProcessScenarioLink));

		ProcessScenarioLink existingProcessScenarioLink = _persistence.findByPrimaryKey(newProcessScenarioLink.getPrimaryKey());

		Assert.assertEquals(existingProcessScenarioLink.getPsl_id(),
			newProcessScenarioLink.getPsl_id());
		Assert.assertEquals(existingProcessScenarioLink.getProcess_id(),
			newProcessScenarioLink.getProcess_id());
		Assert.assertEquals(existingProcessScenarioLink.getScenario_id(),
			newProcessScenarioLink.getScenario_id());
		Assert.assertEquals(existingProcessScenarioLink.getOrder(),
			newProcessScenarioLink.getOrder());
		Assert.assertEquals(existingProcessScenarioLink.getPause(),
			newProcessScenarioLink.getPause());
	}

	@Test
	public void testCountByprocessId() throws Exception {
		_persistence.countByprocessId(RandomTestUtil.nextLong());

		_persistence.countByprocessId(0L);
	}

	@Test
	public void testCountByscenarioId() throws Exception {
		_persistence.countByscenarioId(RandomTestUtil.nextLong());

		_persistence.countByscenarioId(0L);
	}

	@Test
	public void testCountByPause() throws Exception {
		_persistence.countByPause(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByPause(0L, 0L, 0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ProcessScenarioLink newProcessScenarioLink = addProcessScenarioLink();

		ProcessScenarioLink existingProcessScenarioLink = _persistence.findByPrimaryKey(newProcessScenarioLink.getPrimaryKey());

		Assert.assertEquals(existingProcessScenarioLink, newProcessScenarioLink);
	}

	@Test(expected = NoSuchProcessScenarioLinkException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ProcessScenarioLink> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("StressTool_ProcessScenarioLink",
			"psl_id", true, "process_id", true, "scenario_id", true, "order",
			true, "pause", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ProcessScenarioLink newProcessScenarioLink = addProcessScenarioLink();

		ProcessScenarioLink existingProcessScenarioLink = _persistence.fetchByPrimaryKey(newProcessScenarioLink.getPrimaryKey());

		Assert.assertEquals(existingProcessScenarioLink, newProcessScenarioLink);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		ProcessScenarioLink missingProcessScenarioLink = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingProcessScenarioLink);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ProcessScenarioLink newProcessScenarioLink1 = addProcessScenarioLink();
		ProcessScenarioLink newProcessScenarioLink2 = addProcessScenarioLink();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProcessScenarioLink1.getPrimaryKey());
		primaryKeys.add(newProcessScenarioLink2.getPrimaryKey());

		Map<Serializable, ProcessScenarioLink> processScenarioLinks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, processScenarioLinks.size());
		Assert.assertEquals(newProcessScenarioLink1,
			processScenarioLinks.get(newProcessScenarioLink1.getPrimaryKey()));
		Assert.assertEquals(newProcessScenarioLink2,
			processScenarioLinks.get(newProcessScenarioLink2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ProcessScenarioLink> processScenarioLinks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(processScenarioLinks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ProcessScenarioLink newProcessScenarioLink = addProcessScenarioLink();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProcessScenarioLink.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ProcessScenarioLink> processScenarioLinks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, processScenarioLinks.size());
		Assert.assertEquals(newProcessScenarioLink,
			processScenarioLinks.get(newProcessScenarioLink.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ProcessScenarioLink> processScenarioLinks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(processScenarioLinks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ProcessScenarioLink newProcessScenarioLink = addProcessScenarioLink();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProcessScenarioLink.getPrimaryKey());

		Map<Serializable, ProcessScenarioLink> processScenarioLinks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, processScenarioLinks.size());
		Assert.assertEquals(newProcessScenarioLink,
			processScenarioLinks.get(newProcessScenarioLink.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ProcessScenarioLinkLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ProcessScenarioLink>() {
				@Override
				public void performAction(
					ProcessScenarioLink processScenarioLink) {
					Assert.assertNotNull(processScenarioLink);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ProcessScenarioLink newProcessScenarioLink = addProcessScenarioLink();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProcessScenarioLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("psl_id",
				newProcessScenarioLink.getPsl_id()));

		List<ProcessScenarioLink> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ProcessScenarioLink existingProcessScenarioLink = result.get(0);

		Assert.assertEquals(existingProcessScenarioLink, newProcessScenarioLink);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProcessScenarioLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("psl_id",
				RandomTestUtil.nextLong()));

		List<ProcessScenarioLink> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ProcessScenarioLink newProcessScenarioLink = addProcessScenarioLink();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProcessScenarioLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("psl_id"));

		Object newPsl_id = newProcessScenarioLink.getPsl_id();

		dynamicQuery.add(RestrictionsFactoryUtil.in("psl_id",
				new Object[] { newPsl_id }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPsl_id = result.get(0);

		Assert.assertEquals(existingPsl_id, newPsl_id);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProcessScenarioLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("psl_id"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("psl_id",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		ProcessScenarioLink newProcessScenarioLink = addProcessScenarioLink();

		_persistence.clearCache();

		ProcessScenarioLink existingProcessScenarioLink = _persistence.findByPrimaryKey(newProcessScenarioLink.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingProcessScenarioLink.getProcess_id()),
			ReflectionTestUtil.<Long>invoke(existingProcessScenarioLink,
				"getOriginalProcess_id", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingProcessScenarioLink.getScenario_id()),
			ReflectionTestUtil.<Long>invoke(existingProcessScenarioLink,
				"getOriginalScenario_id", new Class<?>[0]));
		Assert.assertEquals(Integer.valueOf(
				existingProcessScenarioLink.getOrder()),
			ReflectionTestUtil.<Integer>invoke(existingProcessScenarioLink,
				"getOriginalOrder", new Class<?>[0]));
	}

	protected ProcessScenarioLink addProcessScenarioLink()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		ProcessScenarioLink processScenarioLink = _persistence.create(pk);

		processScenarioLink.setProcess_id(RandomTestUtil.nextLong());

		processScenarioLink.setScenario_id(RandomTestUtil.nextLong());

		processScenarioLink.setOrder(RandomTestUtil.nextInt());

		processScenarioLink.setPause(RandomTestUtil.nextInt());

		_processScenarioLinks.add(_persistence.update(processScenarioLink));

		return processScenarioLink;
	}

	private List<ProcessScenarioLink> _processScenarioLinks = new ArrayList<ProcessScenarioLink>();
	private ProcessScenarioLinkPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}