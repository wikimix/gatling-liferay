create unique index IX_5FD9C16F on StressTool_FormParam (urlRecordId);

create unique index IX_9B96CBDB on StressTool_Login (name[$COLUMN_LENGTH:75$]);

create unique index IX_5F97ADA1 on StressTool_Process (name[$COLUMN_LENGTH:75$]);

create unique index IX_6B50D174 on StressTool_ProcessScenarioLink (process_id, scenario_id, order_);
create index IX_29262AAE on StressTool_ProcessScenarioLink (scenario_id);

create unique index IX_C2CCD189 on StressTool_Record (name[$COLUMN_LENGTH:75$]);
create index IX_531D7B9B on StressTool_Record (portletId[$COLUMN_LENGTH:75$]);

create unique index IX_43512908 on StressTool_Scenario (name[$COLUMN_LENGTH:75$]);
create index IX_4E9E331A on StressTool_Scenario (simulation_id);

create unique index IX_54E83DC7 on StressTool_SiteMap (name[$COLUMN_LENGTH:75$]);

create index IX_CB06F193 on StressTool_UrlRecord (recordId);

create index IX_50BDD081 on StressTool_UrlSiteMap (siteMapId);