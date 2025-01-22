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
	
	public ClassParser(String src) {
		this.src=src;
	}
	public List<String[]> getClassInfo() {
        List<String[]> info = new ArrayList<>();
        try {
            Class<?> cls = Class.forName(src);
            info.add(new String[]{cls.getSimpleName()});
            info.add(new String[]{Modifier.toString(cls.getModifiers())});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }

    public List<String[]> getClassFields() {
        List<String[]> fields = new ArrayList<>();
        try {
            Class<?> cls = Class.forName(src);
            for (Field field : cls.getDeclaredFields()) {
                fields.add(new String[]{field.getName(), Modifier.toString(field.getModifiers()), field.getType().getSimpleName()});
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return methods;
    }

    public List<String[]> getClassAnnotations() {
        List<String[]> annotations = new ArrayList<>();
        try {
            Class<?> cls = Class.forName(src);
            for (Annotation annotation : cls.getAnnotations()) {
                annotations.add(new String[]{annotation.annotationType().getSimpleName()});
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return annotations;
    }

    public List<String[]> getClassEnumerations() {
        List<String[]> enums = new ArrayList<>();
        try {
            Class<?> cls = Class.forName(src);
            if (cls.isEnum()) {
                for (Object enumConstant : cls.getEnumConstants()) {
                    enums.add(new String[]{enumConstant.toString()});
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return enums;
    }

   
}
