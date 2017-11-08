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

import io.gatling.liferay.model.UrlRecord;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing UrlRecord in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UrlRecord
 * @generated
 */
@ProviderType
public class UrlRecordCacheModel implements CacheModel<UrlRecord>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UrlRecordCacheModel)) {
			return false;
		}

		UrlRecordCacheModel urlRecordCacheModel = (UrlRecordCacheModel)obj;

		if (urlRecordId == urlRecordCacheModel.urlRecordId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, urlRecordId);
	}

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
		}
		else {
			urlRecordImpl.setUrl(url);
		}

		if (type == null) {
			urlRecordImpl.setType(StringPool.BLANK);
		}
		else {
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
		}
		else {
			objectOutput.writeUTF(url);
		}

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeInt(order);

		objectOutput.writeInt(pauseTime);
	}

	public long urlRecordId;
	public long recordId;
	public String url;
	public String type;
	public int order;
	public int pauseTime;
}