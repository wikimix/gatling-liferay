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

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import io.gatling.liferay.model.SiteMap;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SiteMap in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SiteMap
 * @generated
 */
@ProviderType
public class SiteMapCacheModel implements CacheModel<SiteMap>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SiteMapCacheModel)) {
			return false;
		}

		SiteMapCacheModel siteMapCacheModel = (SiteMapCacheModel)obj;

		if (siteMapId == siteMapCacheModel.siteMapId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, siteMapId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{siteMapId=");
		sb.append(siteMapId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SiteMap toEntityModel() {
		SiteMapImpl siteMapImpl = new SiteMapImpl();

		siteMapImpl.setSiteMapId(siteMapId);

		if (name == null) {
			siteMapImpl.setName(StringPool.BLANK);
		}
		else {
			siteMapImpl.setName(name);
		}

		siteMapImpl.resetOriginalValues();

		return siteMapImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		siteMapId = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(siteMapId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long siteMapId;
	public String name;
}