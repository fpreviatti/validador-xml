package com.xml;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Implementation {

	public static void main(String[] args) {

		try {
			
			// validate the XML and schema
			Source xmlfile = validateXML("chat-schema.xsd");
			
			// read the XML File
			File file = new File("chat.xml");
			readElement(file,"Mensagem");
			
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		} 

	}

	public static Source validateXML(String xmlSchemaFilePath) throws SAXException, IOException {

		Source schemaFile = new StreamSource(new File(xmlSchemaFilePath));

		Source xmlFile = new StreamSource(new File("chat.xml"));
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		Schema schema = schemaFactory.newSchema(schemaFile);
		Validator validator = schema.newValidator();

		validator.validate(xmlFile);
		System.out.println(xmlFile.getSystemId() + " is valid");
		return xmlFile;

	}

	public static void readElement(File file, String tagElement) throws ParserConfigurationException, SAXException, IOException {
		// an instance of factory that gives a document builder
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// an instance of builder to parse the specified xml file
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		NodeList nodeList = doc.getElementsByTagName("Mensagem");

		for (int itr = 0; itr < nodeList.getLength(); itr++) {
			Node node = nodeList.item(itr);
			System.out.println("\nNode Name :" + node.getNodeName());
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				System.out.println("Student id: " + eElement.getElementsByTagName("Data").item(0).getTextContent());
				System.out.println("First Name: " + eElement.getElementsByTagName("Hora").item(0).getTextContent());
				System.out.println("Last Name: " + eElement.getElementsByTagName("Corpo").item(0).getTextContent());
			}

		}
	}
}
