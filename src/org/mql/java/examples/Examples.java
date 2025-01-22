package org.mql.java.examples;

import java.util.List;
import java.util.Vector;

import org.mql.java.controller.ClassParser;
import org.mql.java.controller.PackageExplorer;
import org.mql.java.controller.PackageParser;
import org.mql.java.format.ClassFormat;
import org.mql.java.format.FieldFormat;
import org.mql.java.format.MethodFormat;

public class Examples {

	public Examples() {
		exp01();
	}
	
	void exp01() {
		String projectName = "tp4-uml"; 

        PackageExplorer explorer = new PackageExplorer(projectName);
        List<String> packages = explorer.getFoundPackages();
        System.out.println(packages);
        System.out.println("Project: " +projectName);
        for (String pkg : packages) {
        	System.out.println("********"+pkg+"********");
            List<String> classes=PackageParser.scan(projectName, pkg);
            System.out.println(classes);
            for (String cls : classes) {
            	ClassParser cp= new ClassParser(cls);
            	 
                List<String[]> infos = cp.getClassInfo();
                String className = infos.get(0)[0];
                String classModifiers = infos.get(1)[0];

               
                List<FieldFormat> fieldFormats = new Vector<>();
                List<MethodFormat> methodFormats = new Vector<>();
                
                
                List<String[]> fields = cp.getClassFields();
                for (String[] field : fields) {
                    String fieldName = field[0];
                    String fieldModifiers = field[1];
                    String fieldType = field[2];

                    List<String> modifiersList = List.of(fieldModifiers.split(" "));
                    fieldFormats.add(new FieldFormat(fieldName, fieldType, modifiersList));
                }

                
                List<String[]> methods = cp.getClassMethods();
                for (String[] method : methods) {
                    String methodName = method[0];
                    String methodModifiers = method[1];
                    String returnType = method[3];

                    List<String> modifiersList = List.of(methodModifiers.split(" "));
                    List<String> paramTypes = List.of(method[2].trim().split(" "));

                    methodFormats.add(new MethodFormat(methodName, returnType, modifiersList, paramTypes));
                }

                
                List<String> classModifiersList = List.of(classModifiers.split(" "));
                ClassFormat classFormat = new ClassFormat(className, classModifiersList, fieldFormats, methodFormats);

                
                System.out.println(classFormat);
            }
        }
	}
            
	public static void main(String[] args) {
		new Examples();
	}

}
