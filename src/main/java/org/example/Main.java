package org.example;

import net.sf.saxon.TransformerFactoryImpl;
import org.example.parsers.AbstractParser;
import org.example.parsers.DOMParser;
import org.example.parsers.SAXParser;
import org.example.parsers.StAXParser;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class Main {

    private static final String xmlFilePath = "src/main/resources/example.xml";
    private static final String xsdFilePath = "src/main/resources/OldCard.xsd";

    public static void main(String[] args) throws Exception {
        applyValidation(new DOMParser(), "\n--- SAX ---");
        applyValidation(new SAXParser(), "\n--- DOM ---");
        applyValidation(new StAXParser(), "\n--- StAX ---");

        group();
    }

    private static void applyValidation(AbstractParser parser, String consoleMessage) {
        if (parser.validate(xmlFilePath, xsdFilePath)) {
            System.out.println("\nXML file validation is successful!");
            System.out.println(consoleMessage);
            System.out.println(parser.parse(xmlFilePath));
        } else {
            System.out.println("\nXML file validation failed!");
        }
    }

    private static void group() throws Exception {
        TransformerFactory factory = new TransformerFactoryImpl();
        Source xslt = new StreamSource(new File("src/main/resources/grouping.xsl"));

        Transformer transformer = factory.newTransformer(xslt);
        Source xml = new StreamSource(new File("src/main/resources/example.xml"));

        transformer.transform(xml, new StreamResult(new File("src/main/output/output.xml")));
    }

}