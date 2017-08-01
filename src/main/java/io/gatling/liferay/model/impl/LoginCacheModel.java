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

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import io.gatling.liferay.model.Login;

/**
 * The cache model class for representing Login in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Login
 * @generated
 */
public class LoginCacheModel implements CacheModel<Login>, Externalizable {
    public long userId;
    public String name;
    public String data;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{userId=");
        sb.append(userId);
        sb.append(", name=");
        sb.append(name);
        sb.append(", data=");
        sb.append(data);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Login toEntityModel() {
        LoginImpl loginImpl = new LoginImpl();

        loginImpl.setUserId(userId);

        if (name == null) {
            loginImpl.setName(StringPool.BLANK);
        } else {
            loginImpl.setName(name);
        }

        if (data == null) {
            loginImpl.setData(StringPool.BLANK);
        } else {
            loginImpl.setData(data);
        }

        loginImpl.resetOriginalValues();

        return loginImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        userId = objectInput.readLong();
        name = objectInput.readUTF();
        data = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(userId);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        if (data == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(data);
        }
    }
}
