package com.excilys.liferay.gatling.model.AST.feeder;

import java.util.ArrayList;
import java.util.List;


/* TODO: Use a list of siteMapData instead of a raw content */ 
public class SiteMapFeederFileAST extends FeederFileAST{

private static final String TYPE = "SiteMap";
	
	private String content;
	
	public SiteMapFeederFileAST(String name, String content) {
		this.name = name;
		this.type = TYPE;
		this.content = content;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public List<FeederFileAST> flatWithSubsequentFeeders() {
		List<FeederFileAST> feeders = new ArrayList<>(1);
		feeders.add(this);
		return feeders;
	}
	
}