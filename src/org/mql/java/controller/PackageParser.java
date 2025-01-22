package org.mql.java.controller;

import java.io.File;
import java.util.List;
import java.util.Vector;

public class PackageParser {

	
	
	public PackageParser() {
		
	}

	 public static List<String> scan(String projectName, String packageName) {
		 	List<String> classes=new Vector<String>();
		 	String projectPath = "C:\\Users\\Chaimae\\eclipse-workspace\\" + projectName;
	        String classPath = projectPath + "\\bin";
	        String packagePath = packageName.replace(".", "\\");
	        String fullPath = classPath + "\\" + packagePath;
	        
	        File dir = new File(fullPath);

	        if (!dir.exists() || !dir.isDirectory()) {
	            System.out.println("Le package " + packageName + " n'existe pas ou est introuvable.");
	        }

	        File[] content = dir.listFiles();
	        if (content == null || content.length == 0) {
	            System.out.println("Aucune classe trouvée dans le package " + packageName);
	        }
	        for (File file : content) {
	            if (file.isFile() && file.getName().endsWith(".class")) {
	                String className = file.getName().replace(".class", "");
	                classes.add(packageName+"."+className);
	            }
	        }
	        return classes;
	    }
}
