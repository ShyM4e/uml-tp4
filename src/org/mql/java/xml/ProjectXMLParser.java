package org.mql.java.xml;

import java.util.ArrayList;
import java.util.List;
import org.mql.java.format.ClassFormat;
import org.mql.java.format.FieldFormat;
import org.mql.java.format.MethodFormat;
import org.mql.java.format.PackageFormat;
import org.mql.java.format.ProjectFormat;
import org.mql.java.format.RelationshipFormat;

public class ProjectXMLParser {

    public ProjectXMLParser() {
    }

    public ProjectFormat parse(String source) {
        XMLNode root = new XMLNode(source); 
        String projectName = root.attribute("name");
        List<PackageFormat> packages = new ArrayList<>();

        for (XMLNode packageNode : root.children()) { 
            packages.add(parsePackage(packageNode));
        }

        return new ProjectFormat(projectName, packages);
    }

    
    private PackageFormat parsePackage(XMLNode packageNode) {
        String packageName = packageNode.attribute("name");
        List<ClassFormat> classes = new ArrayList<>();

        for (XMLNode classNode : packageNode.children()) { 
            classes.add(parseClass(classNode));
        }

        return new PackageFormat(packageName, classes);
    }

    
    private ClassFormat parseClass(XMLNode classNode) {
        String className = classNode.attribute("name");
        List<String> modifiers = parseModifiers(classNode.attribute("modifiers"));
        List<FieldFormat> fields = new ArrayList<>();
        List<MethodFormat> methods = new ArrayList<>();
        List<RelationshipFormat> relationships = new ArrayList<>();

        for (XMLNode child : classNode.children()) {
            switch (child.getName()) {
                case "field":
                    fields.add(parseField(child));
                    break;
                case "method":
                    methods.add(parseMethod(child));
                    break;
                case "relationship":
                    relationships.add(parseRelationship(child));
                    break;
            }
        }

        return new ClassFormat(className, modifiers, fields, methods, relationships);
    }

    
    private FieldFormat parseField(XMLNode fieldNode) {
        String fieldName = fieldNode.attribute("name");
        String fieldType = fieldNode.attribute("type");
        List<String> modifiers = parseModifiers(fieldNode.attribute("modifiers"));
        return new FieldFormat(fieldName, fieldType, modifiers);
    }

    
    private MethodFormat parseMethod(XMLNode methodNode) {
        String methodName = methodNode.attribute("name");
        String returnType = methodNode.attribute("returnType");
        List<String> modifiers = parseModifiers(methodNode.attribute("modifiers"));
        List<String> paramTypes = new ArrayList<>();

        for (XMLNode paramNode : methodNode.children()) {
            paramTypes.add(paramNode.attribute("type"));
        }

        return new MethodFormat(methodName, returnType, modifiers, paramTypes);
    }

    
    private RelationshipFormat parseRelationship(XMLNode relationshipNode) {
        String fromClass = relationshipNode.attribute("from");
        String toClass = relationshipNode.attribute("to");
        String relationshipType = relationshipNode.attribute("type");
        return new RelationshipFormat(fromClass, toClass, relationshipType);
    }

    
    private List<String> parseModifiers(String modifiers) {
        List<String> list = new ArrayList<>();
        if (modifiers != null && !modifiers.isEmpty()) {
            String[] parts = modifiers.split(" ");
            for (String modifier : parts) {
                list.add(modifier);
            }
        }
        return list;
    }
}
