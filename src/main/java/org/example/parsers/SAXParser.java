package org.example.parsers;

import org.example.model.OldCard;
import org.example.model.Type;
import org.example.model.Valuable;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class SAXParser extends AbstractParser {
    @Override
    public List<OldCard> parse(String xmlFilePath) {
        List<OldCard> oldCards = new ArrayList<>();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                OldCard oldCard = null;
                final StringBuilder content = new StringBuilder();

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if (qName.equals("OldCard")) {
                        oldCard = new OldCard();
                    }

                    content.setLength(0);
                }

                @Override
                public void endElement(String uri, String localName, String qName) {
                    if (oldCard != null) {
                        switch (qName) {
                            case "Thema" -> oldCard.setThema(content.toString());
                            case "Type" -> oldCard.setType(Type.valueOf(content.toString()));
                            case "Sent" -> oldCard.setSent(Boolean.parseBoolean(content.toString()));
                            case "Country" -> oldCard.setCountry(content.toString());
                            case "Year" -> oldCard.setYear(Integer.parseInt(content.toString()));
                            case "Author" -> oldCard.setAuthor(content.toString());
                            case "Valuable" -> oldCard.setValuable(Valuable.valueOf(content.toString()));
                            case "OldCard" -> oldCards.add(oldCard);
                        }
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) {
                    content.append(ch, start, length);
                }
            };

            saxParser.parse(xmlFilePath, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return oldCards;
    }
}
