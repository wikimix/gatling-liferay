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

import com.liferay.portal.kernel.exception.SystemException;

import io.gatling.liferay.model.ProcessScenarioLink;
import io.gatling.liferay.service.ProcessScenarioLinkLocalServiceUtil;

/**
 * The extended model base implementation for the ProcessScenarioLink service. Represents a row in the &quot;StressTool_ProcessScenarioLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProcessScenarioLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcessScenarioLinkImpl
 * @see io.gatling.liferay.model.ProcessScenarioLink
 * @generated
 */
public abstract class ProcessScenarioLinkBaseImpl
    extends ProcessScenarioLinkModelImpl implements ProcessScenarioLink {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a process scenario link model instance should use the {@link ProcessScenarioLink} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProcessScenarioLinkLocalServiceUtil.addProcessScenarioLink(this);
        } else {
            ProcessScenarioLinkLocalServiceUtil.updateProcessScenarioLink(this);
        }
    }
}
