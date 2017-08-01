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
package io.gatling.liferay.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Serializable;

/**
 * The base model interface for the Process service. Represents a row in the &quot;StressTool_Process&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link io.gatling.liferay.model.impl.ProcessModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link io.gatling.liferay.model.impl.ProcessImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Process
 * @see io.gatling.liferay.model.impl.ProcessImpl
 * @see io.gatling.liferay.model.impl.ProcessModelImpl
 * @generated
 */
public interface ProcessModel extends BaseModel<Process> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a process model instance should use the {@link Process} interface instead.
     */

    /**
     * Returns the primary key of this process.
     *
     * @return the primary key of this process
     */
    public long getPrimaryKey();

    /**
     * Sets the primary key of this process.
     *
     * @param primaryKey the primary key of this process
     */
    public void setPrimaryKey(long primaryKey);

    /**
     * Returns the process_id of this process.
     *
     * @return the process_id of this process
     */
    public long getProcess_id();

    /**
     * Sets the process_id of this process.
     *
     * @param process_id the process_id of this process
     */
    public void setProcess_id(long process_id);

    /**
     * Returns the name of this process.
     *
     * @return the name of this process
     */
    @AutoEscape
    public String getName();

    /**
     * Sets the name of this process.
     *
     * @param name the name of this process
     */
    public void setName(String name);

    /**
     * Returns the type of this process.
     *
     * @return the type of this process
     */
    @AutoEscape
    public String getType();

    /**
     * Sets the type of this process.
     *
     * @param type the type of this process
     */
    public void setType(String type);

    /**
     * Returns the feeder ID of this process.
     *
     * @return the feeder ID of this process
     */
    public Long getFeederId();

    /**
     * Sets the feeder ID of this process.
     *
     * @param feederId the feeder ID of this process
     */
    public void setFeederId(Long feederId);

    @Override
    public boolean isNew();

    @Override
    public void setNew(boolean n);

    @Override
    public boolean isCachedModel();

    @Override
    public void setCachedModel(boolean cachedModel);

    @Override
    public boolean isEscapedModel();

    @Override
    public Serializable getPrimaryKeyObj();

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj);

    @Override
    public ExpandoBridge getExpandoBridge();

    @Override
    public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

    @Override
    public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext);

    @Override
    public Object clone();

    @Override
    public int compareTo(Process process);

    @Override
    public int hashCode();

    @Override
    public CacheModel<Process> toCacheModel();

    @Override
    public Process toEscapedModel();

    @Override
    public Process toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
