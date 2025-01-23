package org.mql.java.format;

import java.util.List;
import java.util.Vector;

public class FieldFormat {
	private String name;
	private String type;
	private List<String> modifiers= new Vector<String>();
	
	

	public FieldFormat(String name, String type, List<String> modifiers) {
		super();
		this.name = name;
		this.type = type;
		this.modifiers = modifiers;
	}
	
	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public List<String> getModifiers() {
		return modifiers;
	}



	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}



	@Override
	public String toString() {
		return modifiers + " " + type + " " + name ;
	}
	
	

}
