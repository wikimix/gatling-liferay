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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import io.gatling.liferay.model.Process;
import io.gatling.liferay.model.ProcessModel;

/**
 * The base model implementation for the Process service. Represents a row in the &quot;StressTool_Process&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link io.gatling.liferay.model.ProcessModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProcessImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcessImpl
 * @see io.gatling.liferay.model.Process
 * @see io.gatling.liferay.model.ProcessModel
 * @generated
 */
public class ProcessModelImpl extends BaseModelImpl<Process>
    implements ProcessModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a process model instance should use the {@link io.gatling.liferay.model.Process} interface instead.
     */
    public static final String TABLE_NAME = "StressTool_Process";
    public static final Object[][] TABLE_COLUMNS = {
            { "process_id", Types.BIGINT },
            { "name", Types.VARCHAR },
            { "type_", Types.VARCHAR },
            { "feederId", Types.BIGINT }
        };
    public static final String TABLE_SQL_CREATE = "create table StressTool_Process (process_id LONG not null primary key,name VARCHAR(75) null,type_ VARCHAR(75) null,feederId LONG)";
    public static final String TABLE_SQL_DROP = "drop table StressTool_Process";
    public static final String ORDER_BY_JPQL = " ORDER BY process.process_id ASC";
    public static final String ORDER_BY_SQL = " ORDER BY StressTool_Process.process_id ASC";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.io.gatling.liferay.model.Process"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.io.gatling.liferay.model.Process"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.io.gatling.liferay.model.Process"),
            true);
    public static long NAME_COLUMN_BITMASK = 1L;
    public static long PROCESS_ID_COLUMN_BITMASK = 2L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
                "lock.expiration.time.io.gatling.liferay.model.Process"));
    private static ClassLoader _classLoader = Process.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            Process.class
        };
    private long _process_id;
    private String _name;
    private String _originalName;
    private String _type;
    private Long _feederId;
    private long _columnBitmask;
    private Process _escapedModel;

    public ProcessModelImpl() {
    }

    @Override
    public long getPrimaryKey() {
        return _process_id;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setProcess_id(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _process_id;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Class<?> getModelClass() {
        return Process.class;
    }

    @Override
    public String getModelClassName() {
        return Process.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("process_id", getProcess_id());
        attributes.put("name", getName());
        attributes.put("type", getType());
        attributes.put("feederId", getFeederId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long process_id = (Long) attributes.get("process_id");

        if (process_id != null) {
            setProcess_id(process_id);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String type = (String) attributes.get("type");

        if (type != null) {
            setType(type);
        }

        Long feederId = (Long) attributes.get("feederId");

        if (feederId != null) {
            setFeederId(feederId);
        }
    }

    @Override
    public long getProcess_id() {
        return _process_id;
    }

    @Override
    public void setProcess_id(long process_id) {
        _process_id = process_id;
    }

    @Override
    public String getName() {
        if (_name == null) {
            return StringPool.BLANK;
        } else {
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
    public String getType() {
        if (_type == null) {
            return StringPool.BLANK;
        } else {
            return _type;
        }
    }

    @Override
    public void setType(String type) {
        _type = type;
    }

    @Override
    public Long getFeederId() {
        return _feederId;
    }

    @Override
    public void setFeederId(Long feederId) {
        _feederId = feederId;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
            Process.class.getName(), getPrimaryKey());
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        ExpandoBridge expandoBridge = getExpandoBridge();

        expandoBridge.setAttributes(serviceContext);
    }

    @Override
    public Process toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (Process) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        ProcessImpl processImpl = new ProcessImpl();

        processImpl.setProcess_id(getProcess_id());
        processImpl.setName(getName());
        processImpl.setType(getType());
        processImpl.setFeederId(getFeederId());

        processImpl.resetOriginalValues();

        return processImpl;
    }

    @Override
    public int compareTo(Process process) {
        long primaryKey = process.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Process)) {
            return false;
        }

        Process process = (Process) obj;

        long primaryKey = process.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public void resetOriginalValues() {
        ProcessModelImpl processModelImpl = this;

        processModelImpl._originalName = processModelImpl._name;

        processModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<Process> toCacheModel() {
        ProcessCacheModel processCacheModel = new ProcessCacheModel();

        processCacheModel.process_id = getProcess_id();

        processCacheModel.name = getName();

        String name = processCacheModel.name;

        if ((name != null) && (name.length() == 0)) {
            processCacheModel.name = null;
        }

        processCacheModel.type = getType();

        String type = processCacheModel.type;

        if ((type != null) && (type.length() == 0)) {
            processCacheModel.type = null;
        }

        processCacheModel.feederId = getFeederId();

        return processCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{process_id=");
        sb.append(getProcess_id());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", type=");
        sb.append(getType());
        sb.append(", feederId=");
        sb.append(getFeederId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("io.gatling.liferay.model.Process");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>process_id</column-name><column-value><![CDATA[");
        sb.append(getProcess_id());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>type</column-name><column-value><![CDATA[");
        sb.append(getType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>feederId</column-name><column-value><![CDATA[");
        sb.append(getFeederId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
