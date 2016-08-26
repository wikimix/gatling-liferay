package com.excilys.liferay.gatling.model.AST.feeder;

import java.util.ArrayList;
import java.util.List;

public abstract class ResourceFileAST {
	
	/** The name of the resource File */
	protected String name;
	
	/** The type of the resource File */
	protected String type;
	
	/** The folder where the resource File is */
	protected String location;
	
	protected ResourceFileAST(String name, String type, String location) {
		this.name = name;
		this.type = type;
		this.location = location;
	}
	
	/**
	 * Computes the name of the resource file with his location
	 * @return the resource file name
	 */
	public String getLocatedName() {
		return location + getName();
	}
	
	/**
	 * Computes the name of the resource file
	 * @return the resource file name
	 */
	public String getName() {
		return type + name;
	}
	
	/**
	 * Computes the Resource file content.
	 * @return the content of the feeder file
	 */
	public abstract String getContent();
	
	/**
	 * Returns this resource file plus the resource files that this one depends on.
	 * The default behaviour is to return a list containing this resource file
	 * @return the referenced resource files
	 */
	public List<ResourceFileAST> flatWithSubsequentRessourceFile() {
		List<ResourceFileAST> resource = new ArrayList<>(1);
		resource.add(this);
		return resource;
	}
	
	@Override
	public String toString() {
		return this.getLocatedName();
	}
	
}
