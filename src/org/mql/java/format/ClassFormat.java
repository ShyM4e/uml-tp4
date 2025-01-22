package org.mql.java.format;

import java.util.List;
import java.util.Vector;



public class ClassFormat {
	String name;
	List<String> modifiers= new Vector<String>();
	private List<FieldFormat> fields;
	private List<MethodFormat> methods;
	
	public ClassFormat(String name, List<String> modifiers, List<FieldFormat> fields, List<MethodFormat> methods) {
		super();
		this.name = name;
		this.modifiers = modifiers;
		this.fields = fields;
		this.methods = methods;
	}

	public ClassFormat() {
		// TODO Auto-generated constructor stub
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

		return sb.toString();	
	}

}
