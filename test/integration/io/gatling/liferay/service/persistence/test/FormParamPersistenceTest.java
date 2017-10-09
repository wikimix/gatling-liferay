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

import io.gatling.liferay.exception.NoSuchFormParamException;
import io.gatling.liferay.model.FormParam;
import io.gatling.liferay.service.FormParamLocalServiceUtil;
import io.gatling.liferay.service.persistence.FormParamPersistence;
import io.gatling.liferay.service.persistence.FormParamUtil;

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
public class FormParamPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = FormParamUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<FormParam> iterator = _formParams.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FormParam formParam = _persistence.create(pk);

		Assert.assertNotNull(formParam);

		Assert.assertEquals(formParam.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		FormParam newFormParam = addFormParam();

		_persistence.remove(newFormParam);

		FormParam existingFormParam = _persistence.fetchByPrimaryKey(newFormParam.getPrimaryKey());

		Assert.assertNull(existingFormParam);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addFormParam();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FormParam newFormParam = _persistence.create(pk);

		newFormParam.setUrlRecordId(RandomTestUtil.nextLong());

		newFormParam.setData(RandomTestUtil.randomString());

		_formParams.add(_persistence.update(newFormParam));

		FormParam existingFormParam = _persistence.findByPrimaryKey(newFormParam.getPrimaryKey());

		Assert.assertEquals(existingFormParam.getFormParamId(),
			newFormParam.getFormParamId());
		Assert.assertEquals(existingFormParam.getUrlRecordId(),
			newFormParam.getUrlRecordId());
		Assert.assertEquals(existingFormParam.getData(), newFormParam.getData());
	}

	@Test
	public void testCountByUrlRecordId() throws Exception {
		_persistence.countByUrlRecordId(RandomTestUtil.nextLong());

		_persistence.countByUrlRecordId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		FormParam newFormParam = addFormParam();

		FormParam existingFormParam = _persistence.findByPrimaryKey(newFormParam.getPrimaryKey());

		Assert.assertEquals(existingFormParam, newFormParam);
	}

	@Test(expected = NoSuchFormParamException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<FormParam> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("StressTool_FormParam",
			"formParamId", true, "urlRecordId", true, "data", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		FormParam newFormParam = addFormParam();

		FormParam existingFormParam = _persistence.fetchByPrimaryKey(newFormParam.getPrimaryKey());

		Assert.assertEquals(existingFormParam, newFormParam);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FormParam missingFormParam = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingFormParam);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		FormParam newFormParam1 = addFormParam();
		FormParam newFormParam2 = addFormParam();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFormParam1.getPrimaryKey());
		primaryKeys.add(newFormParam2.getPrimaryKey());

		Map<Serializable, FormParam> formParams = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, formParams.size());
		Assert.assertEquals(newFormParam1,
			formParams.get(newFormParam1.getPrimaryKey()));
		Assert.assertEquals(newFormParam2,
			formParams.get(newFormParam2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, FormParam> formParams = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(formParams.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		FormParam newFormParam = addFormParam();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFormParam.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, FormParam> formParams = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, formParams.size());
		Assert.assertEquals(newFormParam,
			formParams.get(newFormParam.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, FormParam> formParams = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(formParams.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		FormParam newFormParam = addFormParam();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFormParam.getPrimaryKey());

		Map<Serializable, FormParam> formParams = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, formParams.size());
		Assert.assertEquals(newFormParam,
			formParams.get(newFormParam.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = FormParamLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<FormParam>() {
				@Override
				public void performAction(FormParam formParam) {
					Assert.assertNotNull(formParam);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		FormParam newFormParam = addFormParam();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FormParam.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("formParamId",
				newFormParam.getFormParamId()));

		List<FormParam> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		FormParam existingFormParam = result.get(0);

		Assert.assertEquals(existingFormParam, newFormParam);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FormParam.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("formParamId",
				RandomTestUtil.nextLong()));

		List<FormParam> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		FormParam newFormParam = addFormParam();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FormParam.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("formParamId"));

		Object newFormParamId = newFormParam.getFormParamId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("formParamId",
				new Object[] { newFormParamId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFormParamId = result.get(0);

		Assert.assertEquals(existingFormParamId, newFormParamId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FormParam.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("formParamId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("formParamId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		FormParam newFormParam = addFormParam();

		_persistence.clearCache();

		FormParam existingFormParam = _persistence.findByPrimaryKey(newFormParam.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(existingFormParam.getUrlRecordId()),
			ReflectionTestUtil.<Long>invoke(existingFormParam,
				"getOriginalUrlRecordId", new Class<?>[0]));
	}

	protected FormParam addFormParam() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FormParam formParam = _persistence.create(pk);

		formParam.setUrlRecordId(RandomTestUtil.nextLong());

		formParam.setData(RandomTestUtil.randomString());

		_formParams.add(_persistence.update(formParam));

		return formParam;
	}

	private List<FormParam> _formParams = new ArrayList<FormParam>();
	private FormParamPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}