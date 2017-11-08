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

package io.gatling.liferay.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import io.gatling.liferay.model.Scenario;
import io.gatling.liferay.model.ScenarioModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Scenario service. Represents a row in the &quot;StressTool_Scenario&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ScenarioModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ScenarioImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScenarioImpl
 * @see Scenario
 * @see ScenarioModel
 * @generated
 */
@ProviderType
public class ScenarioModelImpl extends BaseModelImpl<Scenario>
	implements ScenarioModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a scenario model instance should use the {@link Scenario} interface instead.
	 */
	public static final String TABLE_NAME = "StressTool_Scenario";
	public static final Object[][] TABLE_COLUMNS = {
			{ "scenario_id", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "url_site", Types.VARCHAR },
			{ "group_id", Types.BIGINT },
			{ "simulation_id", Types.BIGINT },
			{ "numberOfUsers", Types.BIGINT },
			{ "duration", Types.BIGINT },
			{ "injection", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("scenario_id", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("url_site", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("group_id", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("simulation_id", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("numberOfUsers", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("duration", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("injection", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table StressTool_Scenario (scenario_id LONG not null primary key,name VARCHAR(75) null,url_site VARCHAR(75) null,group_id LONG,simulation_id LONG,numberOfUsers LONG,duration LONG,injection VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table StressTool_Scenario";
	public static final String ORDER_BY_JPQL = " ORDER BY scenario.scenario_id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY StressTool_Scenario.scenario_id ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.io.gatling.liferay.model.Scenario"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.io.gatling.liferay.model.Scenario"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.io.gatling.liferay.model.Scenario"),
			true);
	public static final long NAME_COLUMN_BITMASK = 1L;
	public static final long SIMULATION_ID_COLUMN_BITMASK = 2L;
	public static final long SCENARIO_ID_COLUMN_BITMASK = 4L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.io.gatling.liferay.model.Scenario"));

	public ScenarioModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _scenario_id;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setScenario_id(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _scenario_id;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Scenario.class;
	}

	@Override
	public String getModelClassName() {
		return Scenario.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("scenario_id", getScenario_id());
		attributes.put("name", getName());
		attributes.put("url_site", getUrl_site());
		attributes.put("group_id", getGroup_id());
		attributes.put("simulation_id", getSimulation_id());
		attributes.put("numberOfUsers", getNumberOfUsers());
		attributes.put("duration", getDuration());
		attributes.put("injection", getInjection());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long scenario_id = (Long)attributes.get("scenario_id");

		if (scenario_id != null) {
			setScenario_id(scenario_id);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String url_site = (String)attributes.get("url_site");

		if (url_site != null) {
			setUrl_site(url_site);
		}

		Long group_id = (Long)attributes.get("group_id");

		if (group_id != null) {
			setGroup_id(group_id);
		}

		Long simulation_id = (Long)attributes.get("simulation_id");

		if (simulation_id != null) {
			setSimulation_id(simulation_id);
		}

		Long numberOfUsers = (Long)attributes.get("numberOfUsers");

		if (numberOfUsers != null) {
			setNumberOfUsers(numberOfUsers);
		}

		Long duration = (Long)attributes.get("duration");

		if (duration != null) {
			setDuration(duration);
		}

		String injection = (String)attributes.get("injection");

		if (injection != null) {
			setInjection(injection);
		}
	}

	@Override
	public long getScenario_id() {
		return _scenario_id;
	}

	@Override
	public void setScenario_id(long scenario_id) {
		_scenario_id = scenario_id;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@Override
	public String getUrl_site() {
		if (_url_site == null) {
			return StringPool.BLANK;
		}
		else {
			return _url_site;
		}
	}

	@Override
	public void setUrl_site(String url_site) {
		_url_site = url_site;
	}

	@Override
	public long getGroup_id() {
		return _group_id;
	}

	@Override
	public void setGroup_id(long group_id) {
		_group_id = group_id;
	}

	@Override
	public long getSimulation_id() {
		return _simulation_id;
	}

	@Override
	public void setSimulation_id(long simulation_id) {
		_columnBitmask |= SIMULATION_ID_COLUMN_BITMASK;

		if (!_setOriginalSimulation_id) {
			_setOriginalSimulation_id = true;

			_originalSimulation_id = _simulation_id;
		}

		_simulation_id = simulation_id;
	}

	public long getOriginalSimulation_id() {
		return _originalSimulation_id;
	}

	@Override
	public long getNumberOfUsers() {
		return _numberOfUsers;
	}

	@Override
	public void setNumberOfUsers(long numberOfUsers) {
		_numberOfUsers = numberOfUsers;
	}

	@Override
	public long getDuration() {
		return _duration;
	}

	@Override
	public void setDuration(long duration) {
		_duration = duration;
	}

	@Override
	public String getInjection() {
		if (_injection == null) {
			return StringPool.BLANK;
		}
		else {
			return _injection;
		}
	}

	@Override
	public void setInjection(String injection) {
		_injection = injection;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Scenario.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Scenario toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Scenario)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ScenarioImpl scenarioImpl = new ScenarioImpl();

		scenarioImpl.setScenario_id(getScenario_id());
		scenarioImpl.setName(getName());
		scenarioImpl.setUrl_site(getUrl_site());
		scenarioImpl.setGroup_id(getGroup_id());
		scenarioImpl.setSimulation_id(getSimulation_id());
		scenarioImpl.setNumberOfUsers(getNumberOfUsers());
		scenarioImpl.setDuration(getDuration());
		scenarioImpl.setInjection(getInjection());

		scenarioImpl.resetOriginalValues();

		return scenarioImpl;
	}

	@Override
	public int compareTo(Scenario scenario) {
		long primaryKey = scenario.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Scenario)) {
			return false;
		}

		Scenario scenario = (Scenario)obj;

		long primaryKey = scenario.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		ScenarioModelImpl scenarioModelImpl = this;

		scenarioModelImpl._originalName = scenarioModelImpl._name;

		scenarioModelImpl._originalSimulation_id = scenarioModelImpl._simulation_id;

		scenarioModelImpl._setOriginalSimulation_id = false;

		scenarioModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Scenario> toCacheModel() {
		ScenarioCacheModel scenarioCacheModel = new ScenarioCacheModel();

		scenarioCacheModel.scenario_id = getScenario_id();

		scenarioCacheModel.name = getName();

		String name = scenarioCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			scenarioCacheModel.name = null;
		}

		scenarioCacheModel.url_site = getUrl_site();

		String url_site = scenarioCacheModel.url_site;

		if ((url_site != null) && (url_site.length() == 0)) {
			scenarioCacheModel.url_site = null;
		}

		scenarioCacheModel.group_id = getGroup_id();

		scenarioCacheModel.simulation_id = getSimulation_id();

		scenarioCacheModel.numberOfUsers = getNumberOfUsers();

		scenarioCacheModel.duration = getDuration();

		scenarioCacheModel.injection = getInjection();

		String injection = scenarioCacheModel.injection;

		if ((injection != null) && (injection.length() == 0)) {
			scenarioCacheModel.injection = null;
		}

		return scenarioCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{scenario_id=");
		sb.append(getScenario_id());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", url_site=");
		sb.append(getUrl_site());
		sb.append(", group_id=");
		sb.append(getGroup_id());
		sb.append(", simulation_id=");
		sb.append(getSimulation_id());
		sb.append(", numberOfUsers=");
		sb.append(getNumberOfUsers());
		sb.append(", duration=");
		sb.append(getDuration());
		sb.append(", injection=");
		sb.append(getInjection());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("io.gatling.liferay.model.Scenario");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>scenario_id</column-name><column-value><![CDATA[");
		sb.append(getScenario_id());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url_site</column-name><column-value><![CDATA[");
		sb.append(getUrl_site());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>group_id</column-name><column-value><![CDATA[");
		sb.append(getGroup_id());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>simulation_id</column-name><column-value><![CDATA[");
		sb.append(getSimulation_id());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>numberOfUsers</column-name><column-value><![CDATA[");
		sb.append(getNumberOfUsers());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>duration</column-name><column-value><![CDATA[");
		sb.append(getDuration());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>injection</column-name><column-value><![CDATA[");
		sb.append(getInjection());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Scenario.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Scenario.class
		};
	private long _scenario_id;
	private String _name;
	private String _originalName;
	private String _url_site;
	private long _group_id;
	private long _simulation_id;
	private long _originalSimulation_id;
	private boolean _setOriginalSimulation_id;
	private long _numberOfUsers;
	private long _duration;
	private String _injection;
	private long _columnBitmask;
	private Scenario _escapedModel;
}