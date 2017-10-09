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

import aQute.bnd.annotation.ProviderType;

import io.gatling.liferay.model.UrlRecord;
import io.gatling.liferay.service.UrlRecordLocalServiceUtil;

/**
 * The extended model base implementation for the UrlRecord service. Represents a row in the &quot;StressTool_UrlRecord&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UrlRecordImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UrlRecordImpl
 * @see UrlRecord
 * @generated
 */
@ProviderType
public abstract class UrlRecordBaseImpl extends UrlRecordModelImpl
	implements UrlRecord {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a url record model instance should use the {@link UrlRecord} interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			UrlRecordLocalServiceUtil.addUrlRecord(this);
		}
		else {
			UrlRecordLocalServiceUtil.updateUrlRecord(this);
		}
	}
}