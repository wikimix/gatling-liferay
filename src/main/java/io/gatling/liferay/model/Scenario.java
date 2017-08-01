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
package io.gatling.liferay.model;

import com.liferay.portal.kernel.model.PersistedModel;

/**
 * The extended model interface for the Scenario service. Represents a row in the &quot;StressTool_Scenario&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ScenarioModel
 * @see io.gatling.liferay.model.impl.ScenarioImpl
 * @see io.gatling.liferay.model.impl.ScenarioModelImpl
 * @generated
 */
public interface Scenario extends ScenarioModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link io.gatling.liferay.model.impl.ScenarioImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public boolean isComplete();
}
