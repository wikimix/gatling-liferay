/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.excilys.liferay.gatling.model;

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the LinkUsecaseRequest service. Represents a row in the &quot;StressTool_LinkUsecaseRequest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.excilys.liferay.gatling.model.impl.LinkUsecaseRequestModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.excilys.liferay.gatling.model.impl.LinkUsecaseRequestImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LinkUsecaseRequest
 * @see com.excilys.liferay.gatling.model.impl.LinkUsecaseRequestImpl
 * @see com.excilys.liferay.gatling.model.impl.LinkUsecaseRequestModelImpl
 * @generated
 */
public interface LinkUsecaseRequestModel extends BaseModel<LinkUsecaseRequest> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a link usecase request model instance should use the {@link LinkUsecaseRequest} interface instead.
	 */

	/**
	 * Returns the primary key of this link usecase request.
	 *
	 * @return the primary key of this link usecase request
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this link usecase request.
	 *
	 * @param primaryKey the primary key of this link usecase request
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the link usecase request ID of this link usecase request.
	 *
	 * @return the link usecase request ID of this link usecase request
	 */
	public long getLinkUsecaseRequestId();

	/**
	 * Sets the link usecase request ID of this link usecase request.
	 *
	 * @param linkUsecaseRequestId the link usecase request ID of this link usecase request
	 */
	public void setLinkUsecaseRequestId(long linkUsecaseRequestId);

	/**
	 * Returns the request_id of this link usecase request.
	 *
	 * @return the request_id of this link usecase request
	 */
	public long getRequest_id();

	/**
	 * Sets the request_id of this link usecase request.
	 *
	 * @param request_id the request_id of this link usecase request
	 */
	public void setRequest_id(long request_id);

	/**
	 * Returns the record ID of this link usecase request.
	 *
	 * @return the record ID of this link usecase request
	 */
	public long getRecordId();

	/**
	 * Sets the record ID of this link usecase request.
	 *
	 * @param recordId the record ID of this link usecase request
	 */
	public void setRecordId(long recordId);

	/**
	 * Returns the weight of this link usecase request.
	 *
	 * @return the weight of this link usecase request
	 */
	public double getWeight();

	/**
	 * Sets the weight of this link usecase request.
	 *
	 * @param weight the weight of this link usecase request
	 */
	public void setWeight(double weight);

	/**
	 * Returns the sample of this link usecase request.
	 *
	 * @return the sample of this link usecase request
	 */
	public boolean getSample();

	/**
	 * Returns <code>true</code> if this link usecase request is sample.
	 *
	 * @return <code>true</code> if this link usecase request is sample; <code>false</code> otherwise
	 */
	public boolean isSample();

	/**
	 * Sets whether this link usecase request is sample.
	 *
	 * @param sample the sample of this link usecase request
	 */
	public void setSample(boolean sample);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(LinkUsecaseRequest linkUsecaseRequest);

	@Override
	public int hashCode();

	@Override
	public CacheModel<LinkUsecaseRequest> toCacheModel();

	@Override
	public LinkUsecaseRequest toEscapedModel();

	@Override
	public LinkUsecaseRequest toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}