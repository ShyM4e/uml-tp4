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
            info.add(new String[]{cls.getSuperclass() != null ? cls.getSuperclass().getSimpleName() : "Aucune"});
            Class<?>[] interfaces = cls.getInterfaces();
            StringBuilder interfacesList = new StringBuilder();
            for (Class<?> iface : interfaces) {
                interfacesList.append(iface.getSimpleName()).append(" ");
            }
            info.add(new String[]{"Interfaces", interfacesList.toString()});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }

    // Extraction des champs de la classe
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

    // Extraction des constructeurs de la classe
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

    // Extraction des méthodes de la classe
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

    // Extraction des annotations de la classe
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

    // Extraction des énumérations de la classe (si applicable)
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

    // Extraction de l'ensemble des données (champs, méthodes, constructeurs, annotations, énumérations)
    public List<List<String[]>> getAllClassData() {
        List<List<String[]>> allData = new ArrayList<>();
        allData.add(getClassInfo());
        allData.add(getClassFields());
        allData.add(getClassConstructors());
        allData.add(getClassMethods());
        allData.add(getClassAnnotations());
        allData.add(getClassEnumerations());
        return allData;
    }
}
