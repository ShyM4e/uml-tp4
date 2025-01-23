package org.mql.java.format;

import java.util.List;

public class PackageFormat {
	
	 	private String name;
	   	private List<ClassFormat> classes;

	    public PackageFormat(String name, List<ClassFormat> classes) {
	        this.name = name;
	        this.classes = classes;
	    }

	    public String getName() {
	        return name;
	    }

	    public List<ClassFormat> getClasses() {
	        return classes;
	    }

	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("Package: ").append(name).append("\n");
	        for (ClassFormat cls : classes) {
	            sb.append(cls).append("\n");
	        }
	        return sb.toString();
	    }

}
