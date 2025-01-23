package org.mql.java.examples;


import org.mql.java.controller.ProjectParser;
import org.mql.java.format.ProjectFormat;
import org.mql.java.xmi.XMITransformer;
import org.mql.java.xml.ProjectXMLParser;
import org.mql.java.xml.XMLTransformer;

public class Examples {

	public Examples() {
		exp03();
	}
	
	void exp01() {
		String projectName = "tp4-uml";
		
        ProjectParser projectParser = new ProjectParser(projectName);
        ProjectFormat project = projectParser.parseProject();
        
        System.out.println(project);
        

	}
	
	void exp02() {
		String projectName = "tp4-uml";
		
        ProjectParser projectParser = new ProjectParser(projectName);
        ProjectFormat project = projectParser.parseProject();
        
        System.out.println(project);
        
        XMLTransformer.transformToXML(project, "resources/projects.xml");
	}
	
	void exp03() {
		String projectName = "tp4-uml";
		
        ProjectParser projectParser = new ProjectParser(projectName);
        ProjectFormat project = projectParser.parseProject();
        
        System.out.println(project);
        
        XMLTransformer.transformToXML(project, "resources/projects.xml");
        
        XMITransformer.transformToXMI(project, "resources/projects.xmi");
	}
	
	void exp04() {
		 ProjectXMLParser parser = new ProjectXMLParser();
	     String xmlSource = "resources/projects.xml";
	     ProjectFormat project = parser.parse(xmlSource);

	     System.out.println(project);
	}
            
	public static void main(String[] args) {
		new Examples();
	}

}
