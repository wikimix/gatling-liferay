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

import io.gatling.liferay.model.Process;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Process in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Process
 * @generated
 */
@ProviderType
public class ProcessCacheModel implements CacheModel<Process>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessCacheModel)) {
			return false;
		}

		ProcessCacheModel processCacheModel = (ProcessCacheModel)obj;

		if (process_id == processCacheModel.process_id) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, process_id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{process_id=");
		sb.append(process_id);
		sb.append(", name=");
		sb.append(name);
		sb.append(", type=");
		sb.append(type);
		sb.append(", feederId=");
		sb.append(feederId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Process toEntityModel() {
		ProcessImpl processImpl = new ProcessImpl();

		processImpl.setProcess_id(process_id);

		if (name == null) {
			processImpl.setName(StringPool.BLANK);
		}
		else {
			processImpl.setName(name);
		}

		if (type == null) {
			processImpl.setType(StringPool.BLANK);
		}
		else {
			processImpl.setType(type);
		}

		processImpl.setFeederId(feederId);

		processImpl.resetOriginalValues();

		return processImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		process_id = objectInput.readLong();
		name = objectInput.readUTF();
		type = objectInput.readUTF();

		feederId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(process_id);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeLong(feederId);
	}

	public long process_id;
	public String name;
	public String type;
	public long feederId;
}