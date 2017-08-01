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

import io.gatling.liferay.model.UrlRecord;

/**
 * The cache model class for representing UrlRecord in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UrlRecord
 * @generated
 */
public class UrlRecordCacheModel implements CacheModel<UrlRecord>,
    Externalizable {
    public long urlRecordId;
    public long recordId;
    public String url;
    public String type;
    public int order;
    public int pauseTime;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{urlRecordId=");
        sb.append(urlRecordId);
        sb.append(", recordId=");
        sb.append(recordId);
        sb.append(", url=");
        sb.append(url);
        sb.append(", type=");
        sb.append(type);
        sb.append(", order=");
        sb.append(order);
        sb.append(", pauseTime=");
        sb.append(pauseTime);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public UrlRecord toEntityModel() {
        UrlRecordImpl urlRecordImpl = new UrlRecordImpl();

        urlRecordImpl.setUrlRecordId(urlRecordId);
        urlRecordImpl.setRecordId(recordId);

        if (url == null) {
            urlRecordImpl.setUrl(StringPool.BLANK);
        } else {
            urlRecordImpl.setUrl(url);
        }

        if (type == null) {
            urlRecordImpl.setType(StringPool.BLANK);
        } else {
            urlRecordImpl.setType(type);
        }

        urlRecordImpl.setOrder(order);
        urlRecordImpl.setPauseTime(pauseTime);

        urlRecordImpl.resetOriginalValues();

        return urlRecordImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        urlRecordId = objectInput.readLong();
        recordId = objectInput.readLong();
        url = objectInput.readUTF();
        type = objectInput.readUTF();
        order = objectInput.readInt();
        pauseTime = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(urlRecordId);
        objectOutput.writeLong(recordId);

        if (url == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(url);
        }

        if (type == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(type);
        }

        objectOutput.writeInt(order);
        objectOutput.writeInt(pauseTime);
    }
}
