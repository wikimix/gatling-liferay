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

import io.gatling.liferay.model.ProcessScenarioLink;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProcessScenarioLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProcessScenarioLink
 * @generated
 */
@ProviderType
public class ProcessScenarioLinkCacheModel implements CacheModel<ProcessScenarioLink>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProcessScenarioLinkCacheModel)) {
			return false;
		}

		ProcessScenarioLinkCacheModel processScenarioLinkCacheModel = (ProcessScenarioLinkCacheModel)obj;

		if (psl_id == processScenarioLinkCacheModel.psl_id) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, psl_id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{psl_id=");
		sb.append(psl_id);
		sb.append(", process_id=");
		sb.append(process_id);
		sb.append(", scenario_id=");
		sb.append(scenario_id);
		sb.append(", order=");
		sb.append(order);
		sb.append(", pause=");
		sb.append(pause);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProcessScenarioLink toEntityModel() {
		ProcessScenarioLinkImpl processScenarioLinkImpl = new ProcessScenarioLinkImpl();

		processScenarioLinkImpl.setPsl_id(psl_id);
		processScenarioLinkImpl.setProcess_id(process_id);
		processScenarioLinkImpl.setScenario_id(scenario_id);
		processScenarioLinkImpl.setOrder(order);
		processScenarioLinkImpl.setPause(pause);

		processScenarioLinkImpl.resetOriginalValues();

		return processScenarioLinkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		psl_id = objectInput.readLong();

		process_id = objectInput.readLong();

		scenario_id = objectInput.readLong();

		order = objectInput.readInt();

		pause = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(psl_id);

		objectOutput.writeLong(process_id);

		objectOutput.writeLong(scenario_id);

		objectOutput.writeInt(order);

		objectOutput.writeInt(pause);
	}

	public long psl_id;
	public long process_id;
	public long scenario_id;
	public int order;
	public int pause;
}