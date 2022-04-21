package com.xml;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class Implementation {

	public static void main(String[] args) {
				Source xmlfile = validateXML("chat-schema.xsd");
				
				//readXML(xmlfile);
				
	}

	public static Source validateXML(String xmlSchemaFilePath) {
		
		Source schemaFile = new StreamSource(new File(xmlSchemaFilePath));

		Source xmlFile = new StreamSource(new File("chat.xml"));
		SchemaFactory schemaFactory = SchemaFactory
		    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		try {
		  Schema schema = schemaFactory.newSchema(schemaFile);
		  Validator validator = schema.newValidator();
		  
		  validator.validate(xmlFile);
		  System.out.println(xmlFile.getSystemId() + " is valid");
		  return xmlFile;
		} catch (SAXException e) {
		  System.out.println(xmlFile.getSystemId() + " is NOT valid reason:" + e);
		} catch (IOException e) {
		  System.out.println(xmlFile.getSystemId() + " is NOT valid reason:" + e);
		}
		return null;
		
	}
	
	

}
