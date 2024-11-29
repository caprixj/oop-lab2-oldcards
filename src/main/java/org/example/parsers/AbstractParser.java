package org.example.parsers;

import org.example.model.OldCard;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.util.List;

public abstract class AbstractParser {

    public abstract List<OldCard> parse(String xmlFilePath);

    public boolean validate(String xmlFilePath, String xsdFilePath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = factory.newSchema(new File(xsdFilePath));
            Validator validator = schema.newValidator();

            validator.validate(new StreamSource(new File(xmlFilePath)));

            return true;

        } catch (Exception e) {
            System.out.println("validation error: " + e.getMessage());
            return false;
        }
    }

}
