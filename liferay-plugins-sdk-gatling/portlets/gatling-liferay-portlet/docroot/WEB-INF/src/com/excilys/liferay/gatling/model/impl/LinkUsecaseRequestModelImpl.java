/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.excilys.liferay.gatling.model.impl;

import com.excilys.liferay.gatling.model.LinkUsecaseRequest;
import com.excilys.liferay.gatling.model.LinkUsecaseRequestModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the LinkUsecaseRequest service. Represents a row in the &quot;StressTool_LinkUsecaseRequest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.excilys.liferay.gatling.model.LinkUsecaseRequestModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LinkUsecaseRequestImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LinkUsecaseRequestImpl
 * @see com.excilys.liferay.gatling.model.LinkUsecaseRequest
 * @see com.excilys.liferay.gatling.model.LinkUsecaseRequestModel
 * @generated
 */
public class LinkUsecaseRequestModelImpl extends BaseModelImpl<LinkUsecaseRequest>
	implements LinkUsecaseRequestModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a link usecase request model instance should use the {@link com.excilys.liferay.gatling.model.LinkUsecaseRequest} interface instead.
	 */
	public static final String TABLE_NAME = "StressTool_LinkUsecaseRequest";
	public static final Object[][] TABLE_COLUMNS = {
			{ "linkUsecaseRequestId", Types.BIGINT },
			{ "request_id", Types.BIGINT },
			{ "recordId", Types.BIGINT },
			{ "weight", Types.DOUBLE },
			{ "sample", Types.BOOLEAN }
		};
	public static final String TABLE_SQL_CREATE = "create table StressTool_LinkUsecaseRequest (linkUsecaseRequestId LONG not null primary key,request_id LONG,recordId LONG,weight DOUBLE,sample BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table StressTool_LinkUsecaseRequest";
	public static final String ORDER_BY_JPQL = " ORDER BY linkUsecaseRequest.linkUsecaseRequestId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY StressTool_LinkUsecaseRequest.linkUsecaseRequestId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.excilys.liferay.gatling.model.LinkUsecaseRequest"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.excilys.liferay.gatling.model.LinkUsecaseRequest"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.excilys.liferay.gatling.model.LinkUsecaseRequest"));

	public LinkUsecaseRequestModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _linkUsecaseRequestId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLinkUsecaseRequestId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _linkUsecaseRequestId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LinkUsecaseRequest.class;
	}

	@Override
	public String getModelClassName() {
		return LinkUsecaseRequest.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("linkUsecaseRequestId", getLinkUsecaseRequestId());
		attributes.put("request_id", getRequest_id());
		attributes.put("recordId", getRecordId());
		attributes.put("weight", getWeight());
		attributes.put("sample", getSample());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long linkUsecaseRequestId = (Long)attributes.get("linkUsecaseRequestId");

		if (linkUsecaseRequestId != null) {
			setLinkUsecaseRequestId(linkUsecaseRequestId);
		}

		Long request_id = (Long)attributes.get("request_id");

		if (request_id != null) {
			setRequest_id(request_id);
		}

		Long recordId = (Long)attributes.get("recordId");

		if (recordId != null) {
			setRecordId(recordId);
		}

		Double weight = (Double)attributes.get("weight");

		if (weight != null) {
			setWeight(weight);
		}

		Boolean sample = (Boolean)attributes.get("sample");

		if (sample != null) {
			setSample(sample);
		}
	}

	@Override
	public long getLinkUsecaseRequestId() {
		return _linkUsecaseRequestId;
	}

	@Override
	public void setLinkUsecaseRequestId(long linkUsecaseRequestId) {
		_linkUsecaseRequestId = linkUsecaseRequestId;
	}

	@Override
	public long getRequest_id() {
		return _request_id;
	}

	@Override
	public void setRequest_id(long request_id) {
		_request_id = request_id;
	}

	@Override
	public long getRecordId() {
		return _recordId;
	}

	@Override
	public void setRecordId(long recordId) {
		_recordId = recordId;
	}

	@Override
	public double getWeight() {
		return _weight;
	}

	@Override
	public void setWeight(double weight) {
		_weight = weight;
	}

	@Override
	public boolean getSample() {
		return _sample;
	}

	@Override
	public boolean isSample() {
		return _sample;
	}

	@Override
	public void setSample(boolean sample) {
		_sample = sample;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			LinkUsecaseRequest.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LinkUsecaseRequest toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (LinkUsecaseRequest)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		LinkUsecaseRequestImpl linkUsecaseRequestImpl = new LinkUsecaseRequestImpl();

		linkUsecaseRequestImpl.setLinkUsecaseRequestId(getLinkUsecaseRequestId());
		linkUsecaseRequestImpl.setRequest_id(getRequest_id());
		linkUsecaseRequestImpl.setRecordId(getRecordId());
		linkUsecaseRequestImpl.setWeight(getWeight());
		linkUsecaseRequestImpl.setSample(getSample());

		linkUsecaseRequestImpl.resetOriginalValues();

		return linkUsecaseRequestImpl;
	}

	@Override
	public int compareTo(LinkUsecaseRequest linkUsecaseRequest) {
		long primaryKey = linkUsecaseRequest.getPrimaryKey();

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

		if (!(obj instanceof LinkUsecaseRequest)) {
			return false;
		}

		LinkUsecaseRequest linkUsecaseRequest = (LinkUsecaseRequest)obj;

		long primaryKey = linkUsecaseRequest.getPrimaryKey();

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
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<LinkUsecaseRequest> toCacheModel() {
		LinkUsecaseRequestCacheModel linkUsecaseRequestCacheModel = new LinkUsecaseRequestCacheModel();

		linkUsecaseRequestCacheModel.linkUsecaseRequestId = getLinkUsecaseRequestId();

		linkUsecaseRequestCacheModel.request_id = getRequest_id();

		linkUsecaseRequestCacheModel.recordId = getRecordId();

		linkUsecaseRequestCacheModel.weight = getWeight();

		linkUsecaseRequestCacheModel.sample = getSample();

		return linkUsecaseRequestCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{linkUsecaseRequestId=");
		sb.append(getLinkUsecaseRequestId());
		sb.append(", request_id=");
		sb.append(getRequest_id());
		sb.append(", recordId=");
		sb.append(getRecordId());
		sb.append(", weight=");
		sb.append(getWeight());
		sb.append(", sample=");
		sb.append(getSample());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.excilys.liferay.gatling.model.LinkUsecaseRequest");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>linkUsecaseRequestId</column-name><column-value><![CDATA[");
		sb.append(getLinkUsecaseRequestId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>request_id</column-name><column-value><![CDATA[");
		sb.append(getRequest_id());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recordId</column-name><column-value><![CDATA[");
		sb.append(getRecordId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>weight</column-name><column-value><![CDATA[");
		sb.append(getWeight());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sample</column-name><column-value><![CDATA[");
		sb.append(getSample());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = LinkUsecaseRequest.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			LinkUsecaseRequest.class
		};
	private long _linkUsecaseRequestId;
	private long _request_id;
	private long _recordId;
	private double _weight;
	private boolean _sample;
	private LinkUsecaseRequest _escapedModel;
}