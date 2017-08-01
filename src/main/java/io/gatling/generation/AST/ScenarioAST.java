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
package io.gatling.generation.AST;

import java.util.ArrayList;
import java.util.List;

import io.gatling.generation.AST.process.ProcessAST;
import io.gatling.generation.service.SourceCodeIdentifierServices;

/**
 * A ScenarioAST represents the structure of scenario
 * in a Gatling Simulation.
 * This class is used to generate the source code of a scenario.
 */
public class ScenarioAST {
	
	private String scenarioName;
	private long users;
	private long rampUp;
	private String injection;
	private List<ProcessAST> processes;

	public ScenarioAST(String scenarioName, long users, String injection, long rampUp, List<ProcessAST> processList) {
		this.scenarioName = SourceCodeIdentifierServices.createVariableName("", scenarioName);
		this.users = users;
		this.injection = injection;
		this.rampUp = rampUp;
		this.processes = processList;
	}

	public ScenarioAST(String scenarioName, long users, String injection, long rampUp) {
		this(scenarioName, users, injection, rampUp, new ArrayList<ProcessAST>());
	}
	
	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public long getUsers() {
		return users;
	}

	public void setUsers(long users) {
		this.users = users;
	}

	public long getRampUp() {
		return rampUp;
	}

	public void setRamUp(long rampup) {
		this.rampUp = rampup;
	}

	public List<ProcessAST> getProcesses() {
		return processes;
	}

	public void setProcesses(List<ProcessAST> processes) {
		this.processes = processes;
	}

	public String getInjection() {
		return injection;
	}

	public void setInjection(String injection) {
		this.injection = injection;
	}
	
	/**
	 * Generates a scala injection line (rampOver or atOnce) 
	 * 
	 * @return the injection code
	 */
	public String getInjectionCode() {
		StringBuilder sb = new StringBuilder();
		if ("at Once".equals(injection)) {
			sb.append("atOnceUsers(")
				.append(users)
				.append(")");
		}
		else {
			sb.append("rampUsers(")
				.append(users)
				.append(") over(")
				.append(rampUp)
				.append(" seconds)");
		}
		return sb.toString();
	}
	
	/**
	 * Computes all the scala code related to the scenario.
	 * 
	 * @return the scala code that will be included in the simulation file.
	 */
	public String printCode(){
		StringBuilder sb = new StringBuilder();
		final String spaceIndent = "      ";
		int i =0;
		for (ProcessAST process : processes) {
			sb.append(process.printCode());
			if(i+1 != processes.size()) {
				if (process.getPause()!=0) {
					sb.append(",\n").append(spaceIndent);
					sb.append("pause(").append(process.getPause()).append(")");
				}
				sb.append(",\n").append(spaceIndent);
			}
			i++;
		}
		return sb.toString();
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Scenario name: ");
		sb.append(this.scenarioName);
		sb.append("Processes: \n[");
		for(ProcessAST process : processes) {
			sb.append(process.toString());
			sb.append(",\t");
		}
		sb.append("\n]");
		sb.append("Rampup: ").append(rampUp);
		return sb.toString();
	}
	
}
