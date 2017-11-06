package io.practical.p0008;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class P0008 {

	private static String SEPARATOR = FileSystems.getDefault().getSeparator();
	private static String SRC = new StringBuilder()
			.append(System.getProperty("user.dir")).append(SEPARATOR)
			.append("src").append(SEPARATOR)
			.append("test").append(SEPARATOR)
			.append("resources").append(SEPARATOR)
			.toString();
	private static String PATH_XSD = SRC + "xsd" + SEPARATOR;
	private static String PATH_XML = SRC + "xml" + SEPARATOR;
	private static Path xsdPath = Paths.get(PATH_XSD + "akomantoso30.xsd");
//	private static Path xsdPath = Paths.get(PATH_XSD + "xml.xsd");
	private static Path xmlPath = Paths.get(PATH_XML + "cl_Sesion56_2.xml");
//	private static Path xmlPath = Paths.get(PATH_XML + "eu_COM(2013)0619_EN-8.xml");
//	private static Path xmlPath = Paths.get(PATH_XML + "it_senato_ddl_2013.xml");
//	private static Path xmlPath = Paths.get(PATH_XML + "us_Title9-Chap3-eng.xml");
//	private static Path xmlPath = Paths.get(PATH_XML + "za_Judgement_2008-11-26.xml");

//	public static void main(String[] args) throws IOException {
//		validatorFirst();
//		validatorSeconde();
//		validatorThird();
//	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public static void validatorFirst() throws IOException {
		Source xmlFileSource = new StreamSource(xmlPath.toFile());
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

		try {
			Schema schema = schemaFactory.newSchema(xsdPath.toFile());
			Validator validator = schema.newValidator();
			validator.setErrorHandler(new XmlErrorHandler());
			validator.validate(xmlFileSource);

//			System.out.println("ValidatorFirst Schema is valid");
		} catch (SAXException e) {
//			System.out.println("ValidatorFirst Schema is NOT valid -> " + e);
		}

	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public static void validatorSeconde() throws IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		factory.setNamespaceAware(true);
		DocumentBuilder builder;

		try {
			factory.setSchema(schemaFactory.newSchema(xsdPath.toFile()));
			builder = factory.newDocumentBuilder();
			builder.setErrorHandler(new XmlErrorHandler());
			builder.parse(xmlPath.toFile());

//			System.out.println("ValidatorSeconde Schema is valid");
		} catch (ParserConfigurationException | SAXException e) {
//			System.out.println("ValidatorSeconde Schema is NOT valid -> " + e);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public static void validatorThird() throws IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		SAXParser parser = null;
		
		try {
			factory.setSchema(schemaFactory.newSchema(xsdPath.toFile()));
			parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			reader.setErrorHandler(new XmlErrorHandler());
			reader.parse(new InputSource(xmlPath.toFile().toString()));

//			System.out.println("ValidatorThird Schema is valid");
		} catch (SAXException | ParserConfigurationException e) {
//			System.out.println("ValidatorThird Schema is NOT valid -> " + e);
		}

	}

}
