package org.mql.java.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PackageExplorer {
	
	private List<String> foundPackages = new ArrayList<>();
	
	public PackageExplorer(String src) {
		scan(src);
	}

	public void scan(String source) {
		
		String path= "C:\\Users\\Chaimae\\eclipse-workspace\\" +source+ "\\src";
		
		File src=new File(path);
		
		if(!src.exists()) {
			System.out.println("Le fichier source n\'existe pas");
		}
		
		File content[]=src.listFiles();
		
		for (File file : content) {
            if (file.isDirectory()) {
                String packageName = file.getPath().replace(path + File.separator, "")
                        .replace(File.separator, ".");
                scanSubPackages(file, packageName);
            }
        }
        printFinalPackages();
		
	}
	public void scanSubPackages(File directory, String packageName) {
        File[] content = directory.listFiles();

        if (content == null || content.length == 0) {
            return;
        }

        boolean hasSubPackage = false;

        for (File file : content) {
            if (file.isDirectory()) {
                hasSubPackage = true;
                scanSubPackages(file, packageName + "." + file.getName());
            }
        }

        if (!hasSubPackage) {
            foundPackages.add(packageName);
        }
    }
	
	public void printFinalPackages() {
        System.out.println("Liste des packages trouvés :");
        for (String packageName : foundPackages) {
            System.out.println(packageName);
        }
    }
	
	public static void main(String[] args) {
		String projectName="p03-reflection-and-anptation";
		new PackageExplorer(projectName);
	}
}
