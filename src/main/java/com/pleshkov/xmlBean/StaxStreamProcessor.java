package com.pleshkov.xmlBean;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

/**
 * @author pleshkov on 03.10.2018.
 */
public class StaxStreamProcessor implements AutoCloseable {
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

    private final XMLStreamReader reader;

    public StaxStreamProcessor(InputStream is) throws XMLStreamException {
        reader = FACTORY.createXMLStreamReader(is);
    }

    public XMLStreamReader getReader() {
        return reader;
    }

    public String getAttribute(String name) throws XMLStreamException {
        return reader.getAttributeValue(null, name);
    }
    public String getText() throws XMLStreamException {
        return reader.getElementText();
    }

    public boolean doUntil(int stopEvent, String value) throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == stopEvent && value.equalsIgnoreCase(reader.getLocalName())) {
                return true;
            }
        }
        return false;
    }

    public boolean startElement(String element, String parent) throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (parent != null && event == XMLEvent.END_ELEMENT &&
                    parent.equalsIgnoreCase(reader.getLocalName())) {
                return false;
            }
            if (event == XMLEvent.START_ELEMENT &&
                    element.equalsIgnoreCase(reader.getLocalName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void close() {
        if (reader != null) {
            try {
                reader.close();
            } catch (XMLStreamException e) { // empty
            }
        }
    }
}
