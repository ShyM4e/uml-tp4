package org.mql.java.controller;

import java.util.ArrayList;
import java.util.List;
import org.mql.java.format.ClassFormat;
import org.mql.java.format.FieldFormat;
import org.mql.java.format.MethodFormat;
import org.mql.java.format.PackageFormat;
import org.mql.java.format.ProjectFormat;
import org.mql.java.format.RelationshipFormat;

public class ProjectParser {

    private String projectName;

    public ProjectParser(String projectName) {
        this.projectName = projectName;
    }

    public ProjectFormat parseProject() {
        List<PackageFormat> packages = new ArrayList<>();

        PackageExplorer explorer = new PackageExplorer(projectName);
        List<String> packageNames = explorer.getFoundPackages();

        for (String pkg : packageNames) {
            List<ClassFormat> classes = new ArrayList<>();
            List<String> classNames = PackageParser.scan(projectName, pkg);
            if(!classNames.isEmpty()) {
	            for (String cls : classNames) {
	                ClassParser cp = new ClassParser(cls,projectName+"\\bin");
	                List<String[]> infos = cp.getClassInfo();
	                String className = infos.get(0)[0];
	                String classModifiers = infos.get(1)[0];
	
	                List<FieldFormat> fields = new ArrayList<>();
	                List<MethodFormat> methods = new ArrayList<>();
	                List<RelationshipFormat> relationships = new ArrayList<>();
	
	              
	                for (String[] field : cp.getClassFields()) {
	                    fields.add(new FieldFormat(field[0], field[2], List.of(field[1].split(" "))));
	                }
	
	              
	                for (String[] method : cp.getClassMethods()) {
	                    methods.add(new MethodFormat(method[0], method[3], List.of(method[1].split(" ")), List.of(method[2].trim().split(" "))));
	                }
	
	          
	                for (String[] relationship : cp.extractRelationships()) {
	                    relationships.add(new RelationshipFormat(className, relationship[1], relationship[0]));
	                }
	
	            
	                ClassFormat classFormat = new ClassFormat(className, List.of(classModifiers.split(" ")), fields, methods, relationships);
	                classes.add(classFormat);
            }

       
            packages.add(new PackageFormat(pkg, classes));
            }
        }

       
        return new ProjectFormat(projectName, packages);
    }
}