package org.mql.java.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ClassParser {
	public String src;
	
	public ClassParser(String src) {
		this.src=src;
	}
	public List<String[]> getClassInfo() {
			
		List<String[]> info = new ArrayList<>();
	    try {
	        Class<?> cls = Class.forName(src);
	        info.add(new String[]{cls.getSimpleName()});
	        info.add(new String[]{Modifier.toString(cls.getModifiers())});
	        info.add(new String[]{cls.getSuperclass() != null ? cls.getSuperclass().getSimpleName() : "Aucune"});
	        Class<?>[] interfaces = cls.getInterfaces();
	        StringBuilder interfacesList = new StringBuilder();
	        for (Class<?> iface : interfaces) {
	            interfacesList.append(iface.getSimpleName()).append(" ");
	        }
	        info.add(new String[]{"Interfaces", interfacesList.toString()});
	    } catch (ClassNotFoundException e) {}
	    return info;
		 
	}
	
	public List<String[]> getClassFields() {
		 List<String[]> fields = new ArrayList<>();
	        try {
	            Class<?> cls = Class.forName(src);
	            for (Field field : cls.getDeclaredFields()) {
	                fields.add(new String[]{field.getName(),
	                		Modifier.toString(field.getModifiers()), 
	                		field.getType().getSimpleName()});
	            }
	        } catch (ClassNotFoundException e) {}
	        return fields;
	}
	
	public List<String[]> getClassConstructors() {
		List<String[]> constructors = new ArrayList<>();
        try {
            Class<?> cls = Class.forName(src);
            for (Constructor<?> constructor : cls.getConstructors()) {
                StringBuilder params = new StringBuilder();
                for (Class<?> param : constructor.getParameterTypes()) {
                    params.append(param.getSimpleName()).append(" ");
                }
                constructors.add(new String[]{constructor.getName(), Modifier.toString(constructor.getModifiers()), params.toString()});
            }
        } catch (ClassNotFoundException e) {}
        return constructors; 
   }
	
	public List<String[]> getClassMethods() {
		 List<String[]> methods = new ArrayList<>();
	        try {
	            Class<?> cls = Class.forName(src);
	            for (Method method : cls.getDeclaredMethods()) {
	                StringBuilder params = new StringBuilder();
	                for (Class<?> param : method.getParameterTypes()) {
	                    params.append(param.getSimpleName()).append(" ");
	                }
	                methods.add(new String[]{method.getName(), Modifier.toString(method.getModifiers()), params.toString(), method.getReturnType().getSimpleName()});
	            }
	        } catch (ClassNotFoundException e) {}
	        return methods;
	 }
	
	public List<Integer> getCount(){
		List<Integer> count=new Vector<Integer>();
		try {
            Class<?> cls = Class.forName(src);
            
            Field[] fields=cls.getDeclaredFields();
            count.add(fields.length);
            
            Constructor<?> [] c=cls.getConstructors();
            count.add(c.length);
            
            Method[] methods=cls.getDeclaredMethods();
            count.add(methods.length);
            
        } catch (Exception e) {}
		
        return count;
	}
}
