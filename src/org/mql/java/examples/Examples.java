package org.mql.java.examples;


import java.util.List;

import javax.swing.SwingUtilities;

import org.mql.java.controller.ProjectParser;
import org.mql.java.format.ProjectFormat;
import org.mql.java.ui.MainFrame;
import org.mql.java.xmi.XMITransformer;
import org.mql.java.xml.ProjectXMLParser;
import org.mql.java.xml.XMLTransformer;

public class Examples {

	public Examples() {
		exp04();
	}
	
	void exp01() {
		String projectName =  "C:\\Users\\Chaimae\\eclipse-workspace\\model-for-testing";
		
        ProjectParser projectParser = new ProjectParser(projectName);
        ProjectFormat project = projectParser.parseProject();
        
        System.out.println(project);
        

	}
	
	void exp02() {
		String projectName = "C:\\Users\\Chaimae\\eclipse-workspace\\model-for-testing";
		
        ProjectParser projectParser = new ProjectParser(projectName);
        ProjectFormat project = projectParser.parseProject();
        
        System.out.println(project);
        
        XMLTransformer.transformToXML(project, "resources/projects.xml");
	}
	
	void exp03() {
		String projectName = "C:\\Users\\Chaimae\\eclipse-workspace\\model-for-testing";
		
        ProjectParser projectParser = new ProjectParser(projectName);
        ProjectFormat project = projectParser.parseProject();
        
        System.out.println(project);
        
        XMLTransformer.transformToXML(project, "resources/projects.xml");
        
        XMITransformer.transformToXMI(project, "resources/projects.xmi");
	}
	
	void exp04() {
		 ProjectXMLParser parser = new ProjectXMLParser();
	     String xmlSource = "resources/projects.xml";
	     List<ProjectFormat> projects = (List<ProjectFormat>) parser.parse(xmlSource);
	     
	     for (ProjectFormat project : projects) {
	    	    System.out.println(project);
	    	    
	    	}
	}
    
	void exp05() {
		 SwingUtilities.invokeLater(MainFrame::new);
	}
	
	public static void main(String[] args) {
		new Examples();
	}

}
