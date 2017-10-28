package io.practical.p0007;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlHandler extends DefaultHandler {
//	private String tagCourant = "";
	private String space = "";

	@Override
	public void startElement(String nameSpace, String localName, String qName, Attributes attr) throws SAXException {
//		tagCourant = qName;
//		System.out.println(space + "- " + qName);
		space += " ";
	}

	@Override
	public void endElement(String nameSpace, String localName, String qName) throws SAXException {
//		tagCourant = "";
		space = space.substring(1);
	}

//	@Override
//	public void characters(char[] caracteres, int debut, int longueur) throws SAXException {
//		String donnees = new String(caracteres, debut, longueur);
//
//		if (!tagCourant.equals("")) {
//			if (!Character.isISOControl(caracteres[debut])) {
//				System.out.println(space + "Valeur = *" + donnees + "*");
//			}
//		}
//	}
}