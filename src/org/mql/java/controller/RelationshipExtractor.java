package org.mql.java.controller;

import java.lang.reflect.*;
import java.util.List;
import java.util.Vector;

public class RelationshipExtractor {
	
	public static List<String[]> extractRelationships(String className) {
        List<String[]> relationships = new Vector<>();
        try {
            Class<?> cls = Class.forName(className);
            Package classPackage = cls.getPackage();

            for (Field field : cls.getDeclaredFields()) {
                Class<?> fieldType = field.getType();
                Package fieldPackage = fieldType.getPackage();

                if (fieldPackage != null && classPackage != null && 
                    !fieldPackage.getName().equals(classPackage.getName()) && !fieldPackage.getName().startsWith("java.") 
                    && !fieldPackage.getName().startsWith("javax.")) {
                    relationships.add(new String[]{"Agrégation", cls.getSimpleName(), fieldType.getSimpleName()});
                }
            }

            if (cls.getSuperclass() != null && !cls.getSuperclass().getSimpleName().equals("Object") ) {
                relationships.add(new String[]{"Extension", cls.getSimpleName(), cls.getSuperclass().getSimpleName()});
            }

           
            for (Class<?> iface : cls.getInterfaces()) {
                relationships.add(new String[]{"Implémentation", cls.getSimpleName(), iface.getSimpleName()});
            }

            for (Method method : cls.getDeclaredMethods()) {
                for (Class<?> paramType : method.getParameterTypes()) {
                    Package paramPackage = paramType.getPackage();

                    if (paramPackage != null && classPackage != null && !paramPackage.getName().startsWith("java.")&& !paramPackage.getName().startsWith("javax.")
                        && !paramPackage.getName().equals(classPackage.getName())) {
                        relationships.add(new String[]{"Utilisation", cls.getSimpleName(), paramType.getSimpleName()});
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return relationships;
    }

  
    public static void main(String[] args) {
        String className = "org.mql.java.models.LinkedList";
        List<String[]> relationships = extractRelationships(className);
        for (String[] relationship : relationships) {
            System.out.println(relationship[0] + " : " + relationship[1] + " -> " + relationship[2]);
        }
    }
}
