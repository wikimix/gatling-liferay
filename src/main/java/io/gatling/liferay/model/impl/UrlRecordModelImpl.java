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

import io.gatling.liferay.model.UrlRecord;
import io.gatling.liferay.model.UrlRecordModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the UrlRecord service. Represents a row in the &quot;StressTool_UrlRecord&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link UrlRecordModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UrlRecordImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UrlRecordImpl
 * @see UrlRecord
 * @see UrlRecordModel
 * @generated
 */
@ProviderType
public class UrlRecordModelImpl extends BaseModelImpl<UrlRecord>
	implements UrlRecordModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a url record model instance should use the {@link UrlRecord} interface instead.
	 */
	public static final String TABLE_NAME = "StressTool_UrlRecord";
	public static final Object[][] TABLE_COLUMNS = {
			{ "urlRecordId", Types.BIGINT },
			{ "recordId", Types.BIGINT },
			{ "url", Types.VARCHAR },
			{ "type_", Types.VARCHAR },
			{ "order_", Types.INTEGER },
			{ "pauseTime", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("urlRecordId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("recordId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("url", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("order_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("pauseTime", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table StressTool_UrlRecord (urlRecordId LONG not null primary key,recordId LONG,url VARCHAR(75) null,type_ VARCHAR(75) null,order_ INTEGER,pauseTime INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table StressTool_UrlRecord";
	public static final String ORDER_BY_JPQL = " ORDER BY urlRecord.urlRecordId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY StressTool_UrlRecord.urlRecordId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.io.gatling.liferay.model.UrlRecord"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.io.gatling.liferay.model.UrlRecord"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.io.gatling.liferay.model.UrlRecord"),
			true);
	public static final long RECORDID_COLUMN_BITMASK = 1L;
	public static final long URLRECORDID_COLUMN_BITMASK = 2L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.io.gatling.liferay.model.UrlRecord"));

	public UrlRecordModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _urlRecordId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUrlRecordId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _urlRecordId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UrlRecord.class;
	}

	@Override
	public String getModelClassName() {
		return UrlRecord.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("urlRecordId", getUrlRecordId());
		attributes.put("recordId", getRecordId());
		attributes.put("url", getUrl());
		attributes.put("type", getType());
		attributes.put("order", getOrder());
		attributes.put("pauseTime", getPauseTime());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long urlRecordId = (Long)attributes.get("urlRecordId");

		if (urlRecordId != null) {
			setUrlRecordId(urlRecordId);
		}

		Long recordId = (Long)attributes.get("recordId");

		if (recordId != null) {
			setRecordId(recordId);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer order = (Integer)attributes.get("order");

		if (order != null) {
			setOrder(order);
		}

		Integer pauseTime = (Integer)attributes.get("pauseTime");

		if (pauseTime != null) {
			setPauseTime(pauseTime);
		}
	}

	@Override
	public long getUrlRecordId() {
		return _urlRecordId;
	}

	@Override
	public void setUrlRecordId(long urlRecordId) {
		_urlRecordId = urlRecordId;
	}

	@Override
	public long getRecordId() {
		return _recordId;
	}

	@Override
	public void setRecordId(long recordId) {
		_columnBitmask |= RECORDID_COLUMN_BITMASK;

		if (!_setOriginalRecordId) {
			_setOriginalRecordId = true;

			_originalRecordId = _recordId;
		}

		_recordId = recordId;
	}

	public long getOriginalRecordId() {
		return _originalRecordId;
	}

	@Override
	public String getUrl() {
		if (_url == null) {
			return StringPool.BLANK;
		}
		else {
			return _url;
		}
	}

	@Override
	public void setUrl(String url) {
		_url = url;
	}

	@Override
	public String getType() {
		if (_type == null) {
			return StringPool.BLANK;
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		_type = type;
	}

	@Override
	public int getOrder() {
		return _order;
	}

	@Override
	public void setOrder(int order) {
		_order = order;
	}

	@Override
	public int getPauseTime() {
		return _pauseTime;
	}

	@Override
	public void setPauseTime(int pauseTime) {
		_pauseTime = pauseTime;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			UrlRecord.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UrlRecord toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (UrlRecord)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		UrlRecordImpl urlRecordImpl = new UrlRecordImpl();

		urlRecordImpl.setUrlRecordId(getUrlRecordId());
		urlRecordImpl.setRecordId(getRecordId());
		urlRecordImpl.setUrl(getUrl());
		urlRecordImpl.setType(getType());
		urlRecordImpl.setOrder(getOrder());
		urlRecordImpl.setPauseTime(getPauseTime());

		urlRecordImpl.resetOriginalValues();

		return urlRecordImpl;
	}

	@Override
	public int compareTo(UrlRecord urlRecord) {
		long primaryKey = urlRecord.getPrimaryKey();

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

		if (!(obj instanceof UrlRecord)) {
			return false;
		}

		UrlRecord urlRecord = (UrlRecord)obj;

		long primaryKey = urlRecord.getPrimaryKey();

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
		UrlRecordModelImpl urlRecordModelImpl = this;

		urlRecordModelImpl._originalRecordId = urlRecordModelImpl._recordId;

		urlRecordModelImpl._setOriginalRecordId = false;

		urlRecordModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<UrlRecord> toCacheModel() {
		UrlRecordCacheModel urlRecordCacheModel = new UrlRecordCacheModel();

		urlRecordCacheModel.urlRecordId = getUrlRecordId();

		urlRecordCacheModel.recordId = getRecordId();

		urlRecordCacheModel.url = getUrl();

		String url = urlRecordCacheModel.url;

		if ((url != null) && (url.length() == 0)) {
			urlRecordCacheModel.url = null;
		}

		urlRecordCacheModel.type = getType();

		String type = urlRecordCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			urlRecordCacheModel.type = null;
		}

		urlRecordCacheModel.order = getOrder();

		urlRecordCacheModel.pauseTime = getPauseTime();

		return urlRecordCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{urlRecordId=");
		sb.append(getUrlRecordId());
		sb.append(", recordId=");
		sb.append(getRecordId());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", order=");
		sb.append(getOrder());
		sb.append(", pauseTime=");
		sb.append(getPauseTime());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("io.gatling.liferay.model.UrlRecord");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>urlRecordId</column-name><column-value><![CDATA[");
		sb.append(getUrlRecordId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recordId</column-name><column-value><![CDATA[");
		sb.append(getRecordId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>order</column-name><column-value><![CDATA[");
		sb.append(getOrder());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>pauseTime</column-name><column-value><![CDATA[");
		sb.append(getPauseTime());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = UrlRecord.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			UrlRecord.class
		};
	private long _urlRecordId;
	private long _recordId;
	private long _originalRecordId;
	private boolean _setOriginalRecordId;
	private String _url;
	private String _type;
	private int _order;
	private int _pauseTime;
	private long _columnBitmask;
	private UrlRecord _escapedModel;
}