package org.mql.java.format;

import java.util.List;
import java.util.Vector;

public class FieldFormat {
	String name;
	String type;
	List<String> modifiers= new Vector<String>();
	
	public FieldFormat() {
		
	}

	public FieldFormat(String name, String type, List<String> modifiers) {
		super();
		this.name = name;
		this.type = type;
		this.modifiers = modifiers;
	}

	@Override
	public String toString() {
		return modifiers + " " + type + " " + name ;
	}
	
	

}
