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

import io.gatling.liferay.model.Scenario;

/**
 * The cache model class for representing Scenario in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Scenario
 * @generated
 */
public class ScenarioCacheModel implements CacheModel<Scenario>, Externalizable {
    public long scenario_id;
    public String name;
    public String url_site;
    public long group_id;
    public long simulation_id;
    public long numberOfUsers;
    public long duration;
    public String injection;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{scenario_id=");
        sb.append(scenario_id);
        sb.append(", name=");
        sb.append(name);
        sb.append(", url_site=");
        sb.append(url_site);
        sb.append(", group_id=");
        sb.append(group_id);
        sb.append(", simulation_id=");
        sb.append(simulation_id);
        sb.append(", numberOfUsers=");
        sb.append(numberOfUsers);
        sb.append(", duration=");
        sb.append(duration);
        sb.append(", injection=");
        sb.append(injection);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Scenario toEntityModel() {
        ScenarioImpl scenarioImpl = new ScenarioImpl();

        scenarioImpl.setScenario_id(scenario_id);

        if (name == null) {
            scenarioImpl.setName(StringPool.BLANK);
        } else {
            scenarioImpl.setName(name);
        }

        if (url_site == null) {
            scenarioImpl.setUrl_site(StringPool.BLANK);
        } else {
            scenarioImpl.setUrl_site(url_site);
        }

        scenarioImpl.setGroup_id(group_id);
        scenarioImpl.setSimulation_id(simulation_id);
        scenarioImpl.setNumberOfUsers(numberOfUsers);
        scenarioImpl.setDuration(duration);

        if (injection == null) {
            scenarioImpl.setInjection(StringPool.BLANK);
        } else {
            scenarioImpl.setInjection(injection);
        }

        scenarioImpl.resetOriginalValues();

        return scenarioImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        scenario_id = objectInput.readLong();
        name = objectInput.readUTF();
        url_site = objectInput.readUTF();
        group_id = objectInput.readLong();
        simulation_id = objectInput.readLong();
        numberOfUsers = objectInput.readLong();
        duration = objectInput.readLong();
        injection = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(scenario_id);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        if (url_site == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(url_site);
        }

        objectOutput.writeLong(group_id);
        objectOutput.writeLong(simulation_id);
        objectOutput.writeLong(numberOfUsers);
        objectOutput.writeLong(duration);

        if (injection == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(injection);
        }
    }
}
