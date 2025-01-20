package org.mql.java.controller;

import java.io.File;

public class PackageParser {

	
		
	public PackageParser() {
		// TODO Auto-generated constructor stub
	}

	 public static void scan(String projectName, String packageName) {
		 	String projectPath = "C:\\Users\\Chaimae\\eclipse-workspace\\" + projectName;
	        String classPath = projectPath + "\\bin";
	        String packagePath = packageName.replace(".", "\\");
	        String fullPath = classPath + "\\" + packagePath;

	        File dir = new File(fullPath);

	        if (!dir.exists() || !dir.isDirectory()) {
	            System.out.println("Le package " + packageName + " n'existe pas ou est introuvable.");
	            return;
	        }

	        File[] content = dir.listFiles();
	        if (content == null || content.length == 0) {
	            System.out.println("Aucune classe trouvée dans le package " + packageName);
	            return;
	        }

	        System.out.println("Classes trouvées dans le package : " + packageName);
	        for (File file : content) {
	            if (file.isFile() && file.getName().endsWith(".class")) {
	                String className = file.getName().replace(".class", "");
	                System.out.println("- " + className);
	            }
	        }
	    }
}
