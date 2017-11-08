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

import io.gatling.liferay.model.ProcessScenarioLink;
import io.gatling.liferay.model.ProcessScenarioLinkModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the ProcessScenarioLink service. Represents a row in the &quot;StressTool_ProcessScenarioLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ProcessScenarioLinkModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProcessScenarioLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcessScenarioLinkImpl
 * @see ProcessScenarioLink
 * @see ProcessScenarioLinkModel
 * @generated
 */
@ProviderType
public class ProcessScenarioLinkModelImpl extends BaseModelImpl<ProcessScenarioLink>
	implements ProcessScenarioLinkModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a process scenario link model instance should use the {@link ProcessScenarioLink} interface instead.
	 */
	public static final String TABLE_NAME = "StressTool_ProcessScenarioLink";
	public static final Object[][] TABLE_COLUMNS = {
			{ "psl_id", Types.BIGINT },
			{ "process_id", Types.BIGINT },
			{ "scenario_id", Types.BIGINT },
			{ "order_", Types.INTEGER },
			{ "pause", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("psl_id", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("process_id", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("scenario_id", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("order_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("pause", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table StressTool_ProcessScenarioLink (psl_id LONG not null primary key,process_id LONG,scenario_id LONG,order_ INTEGER,pause INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table StressTool_ProcessScenarioLink";
	public static final String ORDER_BY_JPQL = " ORDER BY processScenarioLink.order ASC";
	public static final String ORDER_BY_SQL = " ORDER BY StressTool_ProcessScenarioLink.order_ ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.io.gatling.liferay.model.ProcessScenarioLink"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.io.gatling.liferay.model.ProcessScenarioLink"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.io.gatling.liferay.model.ProcessScenarioLink"),
			true);
	public static final long ORDER_COLUMN_BITMASK = 1L;
	public static final long PROCESS_ID_COLUMN_BITMASK = 2L;
	public static final long SCENARIO_ID_COLUMN_BITMASK = 4L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.io.gatling.liferay.model.ProcessScenarioLink"));

	public ProcessScenarioLinkModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _psl_id;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPsl_id(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _psl_id;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ProcessScenarioLink.class;
	}

	@Override
	public String getModelClassName() {
		return ProcessScenarioLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("psl_id", getPsl_id());
		attributes.put("process_id", getProcess_id());
		attributes.put("scenario_id", getScenario_id());
		attributes.put("order", getOrder());
		attributes.put("pause", getPause());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long psl_id = (Long)attributes.get("psl_id");

		if (psl_id != null) {
			setPsl_id(psl_id);
		}

		Long process_id = (Long)attributes.get("process_id");

		if (process_id != null) {
			setProcess_id(process_id);
		}

		Long scenario_id = (Long)attributes.get("scenario_id");

		if (scenario_id != null) {
			setScenario_id(scenario_id);
		}

		Integer order = (Integer)attributes.get("order");

		if (order != null) {
			setOrder(order);
		}

		Integer pause = (Integer)attributes.get("pause");

		if (pause != null) {
			setPause(pause);
		}
	}

	@Override
	public long getPsl_id() {
		return _psl_id;
	}

	@Override
	public void setPsl_id(long psl_id) {
		_psl_id = psl_id;
	}

	@Override
	public long getProcess_id() {
		return _process_id;
	}

	@Override
	public void setProcess_id(long process_id) {
		_columnBitmask |= PROCESS_ID_COLUMN_BITMASK;

		if (!_setOriginalProcess_id) {
			_setOriginalProcess_id = true;

			_originalProcess_id = _process_id;
		}

		_process_id = process_id;
	}

	public long getOriginalProcess_id() {
		return _originalProcess_id;
	}

	@Override
	public long getScenario_id() {
		return _scenario_id;
	}

	@Override
	public void setScenario_id(long scenario_id) {
		_columnBitmask |= SCENARIO_ID_COLUMN_BITMASK;

		if (!_setOriginalScenario_id) {
			_setOriginalScenario_id = true;

			_originalScenario_id = _scenario_id;
		}

		_scenario_id = scenario_id;
	}

	public long getOriginalScenario_id() {
		return _originalScenario_id;
	}

	@Override
	public int getOrder() {
		return _order;
	}

	@Override
	public void setOrder(int order) {
		_columnBitmask = -1L;

		if (!_setOriginalOrder) {
			_setOriginalOrder = true;

			_originalOrder = _order;
		}

		_order = order;
	}

	public int getOriginalOrder() {
		return _originalOrder;
	}

	@Override
	public int getPause() {
		return _pause;
	}

	@Override
	public void setPause(int pause) {
		_pause = pause;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			ProcessScenarioLink.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ProcessScenarioLink toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ProcessScenarioLink)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ProcessScenarioLinkImpl processScenarioLinkImpl = new ProcessScenarioLinkImpl();

		processScenarioLinkImpl.setPsl_id(getPsl_id());
		processScenarioLinkImpl.setProcess_id(getProcess_id());
		processScenarioLinkImpl.setScenario_id(getScenario_id());
		processScenarioLinkImpl.setOrder(getOrder());
		processScenarioLinkImpl.setPause(getPause());

		processScenarioLinkImpl.resetOriginalValues();

		return processScenarioLinkImpl;
	}

	@Override
	public int compareTo(ProcessScenarioLink processScenarioLink) {
		int value = 0;

		if (getOrder() < processScenarioLink.getOrder()) {
			value = -1;
		}
		else if (getOrder() > processScenarioLink.getOrder()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessScenarioLink)) {
			return false;
		}

		ProcessScenarioLink processScenarioLink = (ProcessScenarioLink)obj;

		long primaryKey = processScenarioLink.getPrimaryKey();

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
		ProcessScenarioLinkModelImpl processScenarioLinkModelImpl = this;

		processScenarioLinkModelImpl._originalProcess_id = processScenarioLinkModelImpl._process_id;

		processScenarioLinkModelImpl._setOriginalProcess_id = false;

		processScenarioLinkModelImpl._originalScenario_id = processScenarioLinkModelImpl._scenario_id;

		processScenarioLinkModelImpl._setOriginalScenario_id = false;

		processScenarioLinkModelImpl._originalOrder = processScenarioLinkModelImpl._order;

		processScenarioLinkModelImpl._setOriginalOrder = false;

		processScenarioLinkModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ProcessScenarioLink> toCacheModel() {
		ProcessScenarioLinkCacheModel processScenarioLinkCacheModel = new ProcessScenarioLinkCacheModel();

		processScenarioLinkCacheModel.psl_id = getPsl_id();

		processScenarioLinkCacheModel.process_id = getProcess_id();

		processScenarioLinkCacheModel.scenario_id = getScenario_id();

		processScenarioLinkCacheModel.order = getOrder();

		processScenarioLinkCacheModel.pause = getPause();

		return processScenarioLinkCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{psl_id=");
		sb.append(getPsl_id());
		sb.append(", process_id=");
		sb.append(getProcess_id());
		sb.append(", scenario_id=");
		sb.append(getScenario_id());
		sb.append(", order=");
		sb.append(getOrder());
		sb.append(", pause=");
		sb.append(getPause());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("io.gatling.liferay.model.ProcessScenarioLink");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>psl_id</column-name><column-value><![CDATA[");
		sb.append(getPsl_id());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>process_id</column-name><column-value><![CDATA[");
		sb.append(getProcess_id());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scenario_id</column-name><column-value><![CDATA[");
		sb.append(getScenario_id());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>order</column-name><column-value><![CDATA[");
		sb.append(getOrder());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>pause</column-name><column-value><![CDATA[");
		sb.append(getPause());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = ProcessScenarioLink.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ProcessScenarioLink.class
		};
	private long _psl_id;
	private long _process_id;
	private long _originalProcess_id;
	private boolean _setOriginalProcess_id;
	private long _scenario_id;
	private long _originalScenario_id;
	private boolean _setOriginalScenario_id;
	private int _order;
	private int _originalOrder;
	private boolean _setOriginalOrder;
	private int _pause;
	private long _columnBitmask;
	private ProcessScenarioLink _escapedModel;
}