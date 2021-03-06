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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the Login service. Represents a row in the &quot;StressTool_Login&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link io.gatling.liferay.model.impl.LoginModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link io.gatling.liferay.model.impl.LoginImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Login
 * @see io.gatling.liferay.model.impl.LoginImpl
 * @see io.gatling.liferay.model.impl.LoginModelImpl
 * @generated
 */
public interface LoginModel extends BaseModel<Login> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a login model instance should use the {@link Login} interface instead.
     */

    /**
     * Returns the primary key of this login.
     *
     * @return the primary key of this login
     */
    public long getPrimaryKey();

    /**
     * Sets the primary key of this login.
     *
     * @param primaryKey the primary key of this login
     */
    public void setPrimaryKey(long primaryKey);

    /**
     * Returns the user ID of this login.
     *
     * @return the user ID of this login
     */
    public long getUserId();

    /**
     * Sets the user ID of this login.
     *
     * @param userId the user ID of this login
     */
    public void setUserId(long userId);

    /**
     * Returns the user uuid of this login.
     *
     * @return the user uuid of this login
     * @throws SystemException if a system exception occurred
     */
    public String getUserUuid() throws SystemException;

    /**
     * Sets the user uuid of this login.
     *
     * @param userUuid the user uuid of this login
     */
    public void setUserUuid(String userUuid);

    /**
     * Returns the name of this login.
     *
     * @return the name of this login
     */
    @AutoEscape
    public String getName();

    /**
     * Sets the name of this login.
     *
     * @param name the name of this login
     */
    public void setName(String name);

    /**
     * Returns the data of this login.
     *
     * @return the data of this login
     */
    @AutoEscape
    public String getData();

    /**
     * Sets the data of this login.
     *
     * @param data the data of this login
     */
    public void setData(String data);

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
    public int compareTo(Login login);

    @Override
    public int hashCode();

    @Override
    public CacheModel<Login> toCacheModel();

    @Override
    public Login toEscapedModel();

    @Override
    public Login toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
