package io.practical.p0008;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlErrorHandler implements ErrorHandler {

	@Override
	public void warning(SAXParseException exception) throws SAXException {
		throw new SAXException("Warning : " + exception.getMessage());
	}

	@Override
	public void error(SAXParseException exception) throws SAXException {
		throw new SAXException("Error : " + exception.getMessage());
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException {
		throw new SAXException("Fatal error : " + exception.getMessage());
	}

}
