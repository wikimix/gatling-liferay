/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.sample.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author sana
 * @generated
 */
public class ScenarioSoap implements Serializable {
	public static ScenarioSoap toSoapModel(Scenario model) {
		ScenarioSoap soapModel = new ScenarioSoap();

		soapModel.setScenario_id(model.getScenario_id());
		soapModel.setName(model.getName());
		soapModel.setGroup_id(model.getGroup_id());
		soapModel.setSimulation_id(model.getSimulation_id());

		return soapModel;
	}

	public static ScenarioSoap[] toSoapModels(Scenario[] models) {
		ScenarioSoap[] soapModels = new ScenarioSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ScenarioSoap[][] toSoapModels(Scenario[][] models) {
		ScenarioSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ScenarioSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ScenarioSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ScenarioSoap[] toSoapModels(List<Scenario> models) {
		List<ScenarioSoap> soapModels = new ArrayList<ScenarioSoap>(models.size());

		for (Scenario model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ScenarioSoap[soapModels.size()]);
	}

	public ScenarioSoap() {
	}

	public long getPrimaryKey() {
		return _scenario_id;
	}

	public void setPrimaryKey(long pk) {
		setScenario_id(pk);
	}

	public long getScenario_id() {
		return _scenario_id;
	}

	public void setScenario_id(long scenario_id) {
		_scenario_id = scenario_id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getGroup_id() {
		return _group_id;
	}

	public void setGroup_id(long group_id) {
		_group_id = group_id;
	}

	public long getSimulation_id() {
		return _simulation_id;
	}

	public void setSimulation_id(long simulation_id) {
		_simulation_id = simulation_id;
	}

	private long _scenario_id;
	private String _name;
	private long _group_id;
	private long _simulation_id;
}