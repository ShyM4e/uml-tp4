package org.mql.java.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ClassParser {
	public String src;
	Class<?> cls;
	
	public ClassParser(String src, String classPath) {
        this.src = src;
        try {
            CustomClassLoader customClassLoader = new CustomClassLoader(classPath);
            cls = customClassLoader.loadClass(src);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + src);
            e.printStackTrace();
        }
    }
 
	public List<String[]> getClassInfo() {
        List<String[]> info = new ArrayList<>();
       
        info.add(new String[]{cls.getSimpleName()});
        info.add(new String[]{Modifier.toString(cls.getModifiers())});
       
        return info;
    }

    public List<String[]> getClassFields() {
        List<String[]> fields = new ArrayList<>();
       
           
        for (Field field : cls.getDeclaredFields()) {
             fields.add(new String[]{field.getName(), Modifier.toString(field.getModifiers()), field.getType().getSimpleName()});
         }
        return fields;
    }

    public List<String[]> getClassConstructors() {
        List<String[]> constructors = new ArrayList<>();
        
        for (Constructor<?> constructor : cls.getConstructors()) {
            StringBuilder params = new StringBuilder();
            for (Class<?> param : constructor.getParameterTypes()) {
                 params.append(param.getSimpleName()).append(" ");
             }
            constructors.add(new String[]{constructor.getName(), Modifier.toString(constructor.getModifiers()), params.toString()});
       }
       
        return constructors;
    }

    public List<String[]> getClassMethods() {
        List<String[]> methods = new ArrayList<>();
        for (Method method : cls.getDeclaredMethods()) {
          StringBuilder params = new StringBuilder();
          for (Class<?> param : method.getParameterTypes()) {
               params.append(param.getSimpleName()).append(" ");
             }
              methods.add(new String[]{method.getName(), Modifier.toString(method.getModifiers()), params.toString(), method.getReturnType().getSimpleName()});
          }
        
        return methods;
    }

    public List<String[]> getClassAnnotations() {
        List<String[]> annotations = new ArrayList<>();
       
       for (Annotation annotation : cls.getAnnotations()) {
           annotations.add(new String[]{annotation.annotationType().getSimpleName()});
        }
        
        return annotations;
    }

    public List<String[]> getClassEnumerations() {
        List<String[]> enums = new ArrayList<>();
       
        if (cls.isEnum()) {
        	for (Object enumConstant : cls.getEnumConstants()) {
                enums.add(new String[]{enumConstant.toString()});
            }
         }
       
        return enums;
    }
    
    public List<String[]> extractRelationships() {
        List<String[]> relationships = new Vector<>();
        Package classPackage = cls.getPackage();

        for (Field field : cls.getDeclaredFields()) {
            Class<?> fieldType = field.getType();
            Package fieldPackage = fieldType.getPackage();

            boolean isCollection = java.util.Collection.class.isAssignableFrom(fieldType) || 
                                   fieldType.isArray();

            if ((fieldPackage != null && !fieldPackage.getName().startsWith("java.") && 
                 !fieldPackage.getName().startsWith("javax.")) || isCollection) {
                relationships.add(new String[]{"Aggregation", fieldType.getSimpleName()});
            }
        }

        if (cls.getSuperclass() != null && !cls.getSuperclass().getSimpleName().equals("Object")) {
            relationships.add(new String[]{"Extension", cls.getSuperclass().getSimpleName()});
        }

        for (Class<?> iface : cls.getInterfaces()) {
            relationships.add(new String[]{"Implementation", iface.getSimpleName()});
        }

        for (Method method : cls.getDeclaredMethods()) {
            for (Class<?> paramType : method.getParameterTypes()) {
                Package paramPackage = paramType.getPackage();

                if (paramPackage != null && !paramPackage.getName().startsWith("java.") && 
                    !paramPackage.getName().startsWith("javax.") && 
                    !paramPackage.getName().equals(classPackage.getName())) {
                    relationships.add(new String[]{"Utilisation", paramType.getSimpleName()});
                }
            }
        }

        return relationships;
    }

   
}
