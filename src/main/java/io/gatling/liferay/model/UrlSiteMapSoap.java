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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UrlSiteMapSoap implements Serializable {
    private long _urlSiteMapId;
    private long _siteMapId;
    private String _group;
    private String _friendlyUrl;
    private String _url;
    private int _weight;

    public UrlSiteMapSoap() {
    }

    public static UrlSiteMapSoap toSoapModel(UrlSiteMap model) {
        UrlSiteMapSoap soapModel = new UrlSiteMapSoap();

        soapModel.setUrlSiteMapId(model.getUrlSiteMapId());
        soapModel.setSiteMapId(model.getSiteMapId());
        soapModel.setGroup(model.getGroup());
        soapModel.setFriendlyUrl(model.getFriendlyUrl());
        soapModel.setUrl(model.getUrl());
        soapModel.setWeight(model.getWeight());

        return soapModel;
    }

    public static UrlSiteMapSoap[] toSoapModels(UrlSiteMap[] models) {
        UrlSiteMapSoap[] soapModels = new UrlSiteMapSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static UrlSiteMapSoap[][] toSoapModels(UrlSiteMap[][] models) {
        UrlSiteMapSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new UrlSiteMapSoap[models.length][models[0].length];
        } else {
            soapModels = new UrlSiteMapSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static UrlSiteMapSoap[] toSoapModels(List<UrlSiteMap> models) {
        List<UrlSiteMapSoap> soapModels = new ArrayList<UrlSiteMapSoap>(models.size());

        for (UrlSiteMap model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new UrlSiteMapSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _urlSiteMapId;
    }

    public void setPrimaryKey(long pk) {
        setUrlSiteMapId(pk);
    }

    public long getUrlSiteMapId() {
        return _urlSiteMapId;
    }

    public void setUrlSiteMapId(long urlSiteMapId) {
        _urlSiteMapId = urlSiteMapId;
    }

    public long getSiteMapId() {
        return _siteMapId;
    }

    public void setSiteMapId(long siteMapId) {
        _siteMapId = siteMapId;
    }

    public String getGroup() {
        return _group;
    }

    public void setGroup(String group) {
        _group = group;
    }

    public String getFriendlyUrl() {
        return _friendlyUrl;
    }

    public void setFriendlyUrl(String friendlyUrl) {
        _friendlyUrl = friendlyUrl;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }

    public int getWeight() {
        return _weight;
    }

    public void setWeight(int weight) {
        _weight = weight;
    }
}
