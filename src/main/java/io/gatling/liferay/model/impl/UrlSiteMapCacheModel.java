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

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import io.gatling.liferay.model.UrlSiteMap;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing UrlSiteMap in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UrlSiteMap
 * @generated
 */
@ProviderType
public class UrlSiteMapCacheModel implements CacheModel<UrlSiteMap>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UrlSiteMapCacheModel)) {
			return false;
		}

		UrlSiteMapCacheModel urlSiteMapCacheModel = (UrlSiteMapCacheModel)obj;

		if (urlSiteMapId == urlSiteMapCacheModel.urlSiteMapId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, urlSiteMapId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{urlSiteMapId=");
		sb.append(urlSiteMapId);
		sb.append(", siteMapId=");
		sb.append(siteMapId);
		sb.append(", group=");
		sb.append(group);
		sb.append(", friendlyUrl=");
		sb.append(friendlyUrl);
		sb.append(", url=");
		sb.append(url);
		sb.append(", weight=");
		sb.append(weight);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UrlSiteMap toEntityModel() {
		UrlSiteMapImpl urlSiteMapImpl = new UrlSiteMapImpl();

		urlSiteMapImpl.setUrlSiteMapId(urlSiteMapId);
		urlSiteMapImpl.setSiteMapId(siteMapId);

		if (group == null) {
			urlSiteMapImpl.setGroup(StringPool.BLANK);
		}
		else {
			urlSiteMapImpl.setGroup(group);
		}

		if (friendlyUrl == null) {
			urlSiteMapImpl.setFriendlyUrl(StringPool.BLANK);
		}
		else {
			urlSiteMapImpl.setFriendlyUrl(friendlyUrl);
		}

		if (url == null) {
			urlSiteMapImpl.setUrl(StringPool.BLANK);
		}
		else {
			urlSiteMapImpl.setUrl(url);
		}

		urlSiteMapImpl.setWeight(weight);

		urlSiteMapImpl.resetOriginalValues();

		return urlSiteMapImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		urlSiteMapId = objectInput.readLong();

		siteMapId = objectInput.readLong();
		group = objectInput.readUTF();
		friendlyUrl = objectInput.readUTF();
		url = objectInput.readUTF();

		weight = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(urlSiteMapId);

		objectOutput.writeLong(siteMapId);

		if (group == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(group);
		}

		if (friendlyUrl == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(friendlyUrl);
		}

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}

		objectOutput.writeInt(weight);
	}

	public long urlSiteMapId;
	public long siteMapId;
	public String group;
	public String friendlyUrl;
	public String url;
	public int weight;
}