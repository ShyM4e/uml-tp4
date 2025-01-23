package org.mql.java.format;

import java.util.List;
import java.util.Vector;

public class MethodFormat {
	private String name;
	private String returnType;
	private List<String> modifiers= new Vector<String>();
	private List<String> paramType= new Vector<String>();
	
	public MethodFormat(String name, String returnType, List<String> modifiers, List<String> paramType) {
		super();
		this.name = name;
		this.returnType = returnType;
		this.modifiers = modifiers;
		this.paramType = paramType;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getReturnType() {
		return returnType;
	}


	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}


	public List<String> getModifiers() {
		return modifiers;
	}


	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}


	public List<String> getParamType() {
		return paramType;
	}


	public void setParamType(List<String> paramType) {
		this.paramType = paramType;
	}


	@Override
	public String toString() {
		return modifiers + " " + returnType + " " + name +"(" +paramType+")";
	}
	

}
