package org.mql.java.format;

import java.util.List;


public class ClassFormat {
	private String name;
	private List<String> modifiers;
	private List<FieldFormat> fields;
	private List<MethodFormat> methods;
	private List<RelationshipFormat> relationships;
	

	
	public ClassFormat(String name, List<String> modifiers, List<FieldFormat> fields, List<MethodFormat> methods,
			List<RelationshipFormat> relationships) {
	
		this.name = name;
		this.modifiers = modifiers;
		this.fields = fields;
		this.methods = methods;
		this.relationships = relationships;
	}

	


	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public List<String> getModifiers() {
		return modifiers;
	}




	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}




	public List<FieldFormat> getFields() {
		return fields;
	}




	public void setFields(List<FieldFormat> fields) {
		this.fields = fields;
	}




	public List<MethodFormat> getMethods() {
		return methods;
	}




	public void setMethods(List<MethodFormat> methods) {
		this.methods = methods;
	}




	public List<RelationshipFormat> getRelationships() {
		return relationships;
	}




	public void setRelationships(List<RelationshipFormat> relationships) {
		this.relationships = relationships;
	}




	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		String formattedModifiers = String.join(" ", modifiers);
		sb.append("|__ " + formattedModifiers + " " + name + "\n");

		if (!fields.isEmpty()) {
			sb.append("\t|__ Fields:\n");
			for (FieldFormat field : fields) {
				sb.append("\t    ").append(field).append("\n");
			}
		}
		if (!methods.isEmpty()) {
			sb.append("\t|__ Methods:\n");
			for (MethodFormat method : methods) {
				sb.append("\t    ").append(method).append("\n");
			}
		}
		if (!relationships.isEmpty()) {
			sb.append("\t|__ Relationships:\n");
			for (RelationshipFormat relationship : relationships) {
				sb.append("\t    ").append(relationship).append("\n");
			}
		}
		return sb.toString();	
	}

}
