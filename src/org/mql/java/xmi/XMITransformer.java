package org.mql.java.xmi;

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
import org.mql.java.format.PackageFormat;
import org.mql.java.format.ProjectFormat;
import org.mql.java.format.RelationshipFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMITransformer {

	public static void transformToXMI(ProjectFormat project, String xmiOutputPath) {
	    try {
	        System.out.println("Generating XMI file at: " + xmiOutputPath);

	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.newDocument();

	        // Create the root XMI element
	        Element xmiRoot = doc.createElement("xmi:XMI");
            xmiRoot.setAttribute("xmi:version", "2.5");
            xmiRoot.setAttribute("xmlns:xmi", "http://www.omg.org/spec/XMI/20131001");
            xmiRoot.setAttribute("xmlns:uml", "http://www.eclipse.org/uml2/5.0.0/UML");
            xmiRoot.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            xmiRoot.setAttribute(
                "xsi:schemaLocation",
                "http://www.omg.org/spec/XMI/20131001 resources/schemas/XMI.xsd " +
                "http://www.eclipse.org/uml2/5.0.0/UML resources/schemas/UML.xsd"
            );
            doc.appendChild(xmiRoot);

	        // Create UML Model element
	        Element umlModel = doc.createElement("uml:Model");
	        umlModel.setAttribute("xmi:id", "model1");
	        umlModel.setAttribute("name", project.getName());
	        xmiRoot.appendChild(umlModel);

	        // Add packages to the model
	        for (PackageFormat pkg : project.getPackages()) {
	            Element umlPackage = createPackageElement(doc, pkg);
	            umlModel.appendChild(umlPackage);
	        }

	        // Write the XMI file
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File(xmiOutputPath));

	        transformer.transform(source, result);
	        System.out.println("XMI file saved successfully at: " + xmiOutputPath);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


    private static Element createPackageElement(Document doc, PackageFormat pkg) {
        Element umlPackage = doc.createElement("packagedElement");
        umlPackage.setAttribute("xmi:type", "uml:Package");
        umlPackage.setAttribute("xmi:id", "pkg_" + pkg.getName());
        umlPackage.setAttribute("name", pkg.getName());

        // Add classes to the package
        for (ClassFormat cls : pkg.getClasses()) {
            Element umlClass = createClassElement(doc, cls);
            umlPackage.appendChild(umlClass);
        }

        return umlPackage;
    }

    private static Element createClassElement(Document doc, ClassFormat cls) {
        Element umlClass = doc.createElement("packagedElement");
        umlClass.setAttribute("xmi:type", "uml:Class");
        umlClass.setAttribute("xmi:id", "cls_" + cls.getName());
        umlClass.setAttribute("name", cls.getName());

        // Add fields to the class
        for (FieldFormat field : cls.getFields()) {
            Element umlField = doc.createElement("ownedAttribute");
            umlField.setAttribute("xmi:type", "uml:Property");
            umlField.setAttribute("xmi:id", "field_" + field.getName());
            umlField.setAttribute("name", field.getName());
            umlField.setAttribute("type", field.getType());
            umlClass.appendChild(umlField);
        }

        // Add relationships to the class
        for (RelationshipFormat relationship : cls.getRelationships()) {
            Element umlRelationship = doc.createElement("packagedElement");
            umlRelationship.setAttribute("xmi:type", "uml:Association");
            umlRelationship.setAttribute("xmi:id", "rel_" + relationship.getFromClass() + "_" + relationship.getToClass());
            umlRelationship.setAttribute("memberEnd", relationship.getFromClass() + " " + relationship.getToClass());
            umlClass.appendChild(umlRelationship);
        }

        return umlClass;
    }
}