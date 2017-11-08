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

import io.gatling.liferay.model.FormParam;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing FormParam in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see FormParam
 * @generated
 */
@ProviderType
public class FormParamCacheModel implements CacheModel<FormParam>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FormParamCacheModel)) {
			return false;
		}

		FormParamCacheModel formParamCacheModel = (FormParamCacheModel)obj;

		if (formParamId == formParamCacheModel.formParamId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, formParamId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{formParamId=");
		sb.append(formParamId);
		sb.append(", urlRecordId=");
		sb.append(urlRecordId);
		sb.append(", data=");
		sb.append(data);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FormParam toEntityModel() {
		FormParamImpl formParamImpl = new FormParamImpl();

		formParamImpl.setFormParamId(formParamId);
		formParamImpl.setUrlRecordId(urlRecordId);

		if (data == null) {
			formParamImpl.setData(StringPool.BLANK);
		}
		else {
			formParamImpl.setData(data);
		}

		formParamImpl.resetOriginalValues();

		return formParamImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		formParamId = objectInput.readLong();

		urlRecordId = objectInput.readLong();
		data = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(formParamId);

		objectOutput.writeLong(urlRecordId);

		if (data == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(data);
		}
	}

	public long formParamId;
	public long urlRecordId;
	public String data;
}