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
        String srcPath = source + File.separator + "src";
        File src = new File(srcPath);

        if (!src.exists() || !src.isDirectory()) {
            System.out.println("The source directory does not exist or is not accessible: " + srcPath);
            return;
        }

        File[] content = src.listFiles();
        if (content != null) {
            for (File file : content) {
                if (file.isDirectory()) {
                    String packageName = file.getPath()
                            .substring(src.getPath().length() + 1) 
                            .replace(File.separator, ".");
                    addPackageAndScanSubPackages(file, packageName); 
                }
            }
        }
    }

    public void addPackageAndScanSubPackages(File directory, String packageName) {
        foundPackages.add(packageName);

        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            System.out.println("Directory does not exist or is not accessible: " + directory.getPath());
            return;
        }

        File[] content = directory.listFiles();
        if (content == null || content.length == 0) {
            return;
        }

        for (File file : content) {
            if (file.isDirectory()) {
                addPackageAndScanSubPackages(file, packageName + "." + file.getName());
            }
        }
    }

    public static void main(String[] args) {
        String projectName = "C:\\Users\\Chaimae\\eclipse-workspace\\p05-functional-programming";

        PackageExplorer explorer = new PackageExplorer(projectName);
        List<String> packages = explorer.getFoundPackages();

        System.out.println("Packages found:");
        for (String pkg : packages) {
            System.out.println(pkg);
            System.out.println(PackageParser.scan(projectName, pkg));
        }
    }
}
