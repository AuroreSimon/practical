package io.practical.p0007;

import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

public class P0007 {

	private static int SIZE = 100000;
	private static String SRC = new StringBuilder().append(System.getProperty("user.dir"))
			.append(FileSystems.getDefault().getSeparator()).append("src")
			.append(FileSystems.getDefault().getSeparator()).append("test")
			.append(FileSystems.getDefault().getSeparator()).append("resources")
			.append(FileSystems.getDefault().getSeparator()).toString();
	private static Path XML_WITHOUT_NAMESPACE = Paths.get(SRC + "nation.xml");
	private static Path XML_WITH_NAMESPACE = Paths.get(SRC + "slide3.xml");

//	public static void main(String[] args) throws Exception {
//		System.out.println("!!!! DOM !!!!");
//		readDom(XML_WITHOUT_NAMESPACE);
//		System.out.println("!!!! SAX !!!!");
//		readSax(XML_WITHOUT_NAMESPACE);
//		System.out.println("!!!! STAX !!!!");
//		readStax(XML_WITHOUT_NAMESPACE);
//	}
//
	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void readSaxFileWithoutNamespace() throws Exception {
		for (int i = 0; i < SIZE; i++) {
			readSax(XML_WITHOUT_NAMESPACE);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void readSaxFileWithNamespace() throws Exception {
		for (int i = 0; i < SIZE; i++) {
			readSax(XML_WITH_NAMESPACE);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void readDomFileWithoutNamespace() throws Exception {
		for (int i = 0; i < SIZE; i++) {
			readDom(XML_WITHOUT_NAMESPACE);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void readDomFileWithNamespace() throws Exception {
		for (int i = 0; i < SIZE; i++) {
			readDom(XML_WITH_NAMESPACE);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void readStaxFileWithoutNamespace() throws Exception {
		for (int i = 0; i < SIZE; i++) {
			readStax(XML_WITHOUT_NAMESPACE);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void readStaxFileWithNamespace() throws Exception {
		for (int i = 0; i < SIZE; i++) {
			readStax(XML_WITH_NAMESPACE);
		}
	}

	public static void readSax(Path xmlFilePath) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.newSAXParser().parse(xmlFilePath.toFile(), new XmlHandler());
	}

	public static void readStax(Path xmlFilePath) throws Exception {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		FileReader fileReader = new FileReader(xmlFilePath.toFile());
		XMLStreamReader xMLStreamReader = factory.createXMLStreamReader(fileReader);
		String space = "";
		while (xMLStreamReader.hasNext()) {

			switch (xMLStreamReader.next()) {
			case XMLEvent.START_ELEMENT:
//				System.out.println(space + "- " + xMLStreamReader.getName());
				space += " ";
				break;
			case XMLEvent.END_ELEMENT:
				space = space.substring(1);
				break;
//			case XMLEvent.CHARACTERS:
//				String chaine = xMLStreamReader.getText();
//				if (!xMLStreamReader.isWhiteSpace()) {
//					System.out.println("\t->\"" + chaine + "\"");
//				}
//				break;
			default:
				break;
			}
		}
	}

	public static void readDom(Path xmlFilePath) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document = factory.newDocumentBuilder().parse(xmlFilePath.toFile());
		DocumentTraversal traversal = (DocumentTraversal) document;
		TreeWalker walker = traversal.createTreeWalker(document.getDocumentElement(), NodeFilter.SHOW_ALL, null, true);
		walker.getRoot();
		traverseLevel(walker, "");
	}

	private static final void traverseLevel(TreeWalker walker, String space) {
		Node noeud = walker.getCurrentNode();

		if (noeud instanceof Element) {
//			System.out.println(space + "- " + ((Element) noeud).getTagName());
			for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
				traverseLevel(walker, space + "  ");
			}
		}

		walker.setCurrentNode(noeud);
	}
}
