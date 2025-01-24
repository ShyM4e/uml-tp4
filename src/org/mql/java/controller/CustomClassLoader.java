package org.mql.java.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader {
    private String classPath;

    public CustomClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
           
            String path = classPath + File.separator + name.replace(".", File.separator) + ".class";
            FileInputStream fis = new FileInputStream(path);
            byte[] classData = new byte[fis.available()];
            fis.read(classData);
            fis.close();

            return defineClass(name, classData, 0, classData.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Class " + name + " not found in " + classPath, e);
        }
    }
}