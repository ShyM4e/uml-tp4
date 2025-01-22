package org.mql.java.format;

import java.util.List;
import java.util.Vector;

public class MethodFormat {
	String name;
	String returnType;
	List<String> modifiers= new Vector<String>();
	List<String> paramType= new Vector<String>();
	
	public MethodFormat(String name, String returnType, List<String> modifiers, List<String> paramType) {
		super();
		this.name = name;
		this.returnType = returnType;
		this.modifiers = modifiers;
		this.paramType = paramType;
	}
	
	@Override
	public String toString() {
		return modifiers + " " + returnType + " " + name +"(" +paramType+")";
	}
	

}
