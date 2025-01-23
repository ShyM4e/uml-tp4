package org.mql.java.xml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.mql.java.format.ClassFormat;
import org.mql.java.format.FieldFormat;
import org.mql.java.format.MethodFormat;
import org.mql.java.format.PackageFormat;
import org.mql.java.format.ProjectFormat;
import org.mql.java.format.RelationshipFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLTransformer {

    public static void transformToXML(ProjectFormat project, String outputPath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("project");
            rootElement.setAttribute("name", project.getName());
            doc.appendChild(rootElement);

            for (PackageFormat pkg : project.getPackages()) {
                Element packageElement = createPackageElement(doc, pkg);
                rootElement.appendChild(packageElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(outputPath));

            transformer.transform(source, result);

            System.out.println("XML file saved successfully at: " + outputPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Element createPackageElement(Document doc, PackageFormat pkg) {
        Element packageElement = doc.createElement("package");
        packageElement.setAttribute("name", pkg.getName());

        // Add classes
        for (ClassFormat cls : pkg.getClasses()) {
            Element classElement = createClassElement(doc, cls);
            packageElement.appendChild(classElement);
        }

        return packageElement;
    }

    private static Element createClassElement(Document doc, ClassFormat cls) {
        Element classElement = doc.createElement("class");
        classElement.setAttribute("name", cls.getName());
        classElement.setAttribute("modifiers", String.join(" ", cls.getModifiers()));

        // Add fields
        for (FieldFormat field : cls.getFields()) {
            Element fieldElement = createFieldElement(doc, field);
            classElement.appendChild(fieldElement);
        }

        // Add methods
        for (MethodFormat method : cls.getMethods()) {
            Element methodElement = createMethodElement(doc, method);
            classElement.appendChild(methodElement);
        }

        // Add relationships
        for (RelationshipFormat relationship : cls.getRelationships()) {
            Element relationshipElement = createRelationshipElement(doc, relationship);
            classElement.appendChild(relationshipElement);
        }

        return classElement;
    }

    private static Element createFieldElement(Document doc, FieldFormat field) {
        Element fieldElement = doc.createElement("field");
        fieldElement.setAttribute("name", field.getName());
        fieldElement.setAttribute("type", field.getType());
        fieldElement.setAttribute("modifiers", String.join(" ", field.getModifiers()));
        return fieldElement;
    }

    private static Element createMethodElement(Document doc, MethodFormat method) {
        Element methodElement = doc.createElement("method");
        methodElement.setAttribute("name", method.getName());
        methodElement.setAttribute("returnType", method.getReturnType());
        methodElement.setAttribute("modifiers", String.join(" ", method.getModifiers()));

        // Add parameters
        for (String param : method.getParamType()) {
            Element paramElement = doc.createElement("parameter");
            paramElement.setAttribute("type", param);
            methodElement.appendChild(paramElement);
        }

        return methodElement;
    }

    private static Element createRelationshipElement(Document doc, RelationshipFormat relationship) {
        Element relationshipElement = doc.createElement("relationship");
        relationshipElement.setAttribute("type", relationship.getRelationshipType());
        relationshipElement.setAttribute("from", relationship.getFromClass());
        relationshipElement.setAttribute("to", relationship.getToClass());
        return relationshipElement;
    }
}