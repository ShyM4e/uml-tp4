package org.mql.java.controller;

import java.io.File;
import java.util.List;
import java.util.Vector;

public class PackageExplorer {
	
	private List<String> foundPackages = new Vector<>();

    public PackageExplorer(String src) {
        scan(src);
    }

    public List<String> getFoundPackages() {
        return foundPackages; 
    }

    public void scan(String source) {
        String path = "C:\\Users\\Chaimae\\eclipse-workspace\\" + source + "\\src";
        File src = new File(path);

        if (!src.exists()) {
            System.out.println("Le fichier source n'existe pas");
            return;
        }

        File[] content = src.listFiles();
        if (content != null) {
            for (File file : content) {
                if (file.isDirectory()) {
                    String packageName = file.getPath()
                            .replace(path + File.separator, "")
                            .replace(File.separator, ".");
                    scanSubPackages(file, packageName);
                }
            }
        }
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

    public static void main(String[] args) {
    	String projectName = "p05-functional-programming"; 

        PackageExplorer explorer = new PackageExplorer(projectName);
        List<String> packages = explorer.getFoundPackages();

        System.out.println("Packages trouvés :");
        for (String pkg : packages) {
            PackageParser.scan(projectName, pkg);
        }
    }
}
