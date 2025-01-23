package org.mql.java.format;

import java.util.List;

public class ProjectFormat {
	
	private String name;
    private List<PackageFormat> packages;

    public ProjectFormat(String name, List<PackageFormat> packages) {
        this.name = name;
        this.packages = packages;
    }

    public String getName() {
        return name;
    }

    public List<PackageFormat> getPackages() {
        return packages;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Project: ").append(name).append("\n");
        for (PackageFormat pkg : packages) {
            sb.append(pkg).append("\n");
        }
        return sb.toString();
    }

}
