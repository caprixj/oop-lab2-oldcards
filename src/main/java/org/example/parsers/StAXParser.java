package org.example.parsers;

import org.example.model.OldCard;
import org.example.model.Type;
import org.example.model.Valuable;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class StAXParser extends AbstractParser {
    @Override
    public List<OldCard> parse(String xmlFilePath) {
        List<OldCard> oldCards = new ArrayList<>();
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(xmlFilePath));

            OldCard oldCard = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    String elementName = event.asStartElement().getName().getLocalPart();

                    if (elementName.equals("OldCard")) {
                        oldCard = new OldCard();
                    } else if (oldCard != null) {
                        switch (elementName) {
                            case "Thema" -> oldCard.setThema(eventReader.nextEvent().asCharacters().getData());
                            case "Type" ->
                                oldCard.setType(Type.valueOf(eventReader.nextEvent().asCharacters().getData()));
                            case "Sent" ->
                                oldCard.setSent(Boolean.parseBoolean(eventReader.nextEvent().asCharacters().getData()));
                            case "Country" -> oldCard.setCountry(eventReader.nextEvent().asCharacters().getData());
                            case "Year" ->
                                oldCard.setYear(Integer.parseInt(eventReader.nextEvent().asCharacters().getData()));
                            case "Author" -> oldCard.setAuthor(eventReader.nextEvent().asCharacters().getData());
                            case "Valuable" ->
                                oldCard.setValuable(Valuable.valueOf(eventReader.nextEvent().asCharacters().getData()));
                        }
                    }
                }

                if (event.isEndElement() && event.asEndElement().getName().getLocalPart().equals("OldCard")) {
                    if (oldCard != null) {
                        oldCards.add(oldCard);
                        oldCard = null;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return oldCards;
    }
}
