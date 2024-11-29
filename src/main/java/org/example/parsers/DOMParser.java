package org.example.parsers;

import org.example.model.OldCard;
import org.example.model.Type;
import org.example.model.Valuable;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOMParser extends AbstractParser {
    @Override
    public List<OldCard> parse(String xmlFilePath) {
        List<OldCard> oldCards = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document document = factory.newDocumentBuilder().parse(new File(xmlFilePath));
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("OldCard");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    OldCard oldCard = new OldCard();

                    oldCard.setThema(element.getElementsByTagName("Thema").item(0).getTextContent());
                    oldCard.setType(Type.valueOf(element.getElementsByTagName("Type").item(0).getTextContent()));
                    oldCard.setSent(Boolean.parseBoolean(element.getElementsByTagName("Sent").item(0).getTextContent()));
                    oldCard.setCountry(element.getElementsByTagName("Country").item(0).getTextContent());
                    oldCard.setYear(Integer.parseInt(element.getElementsByTagName("Year").item(0).getTextContent()));
                    oldCard.setAuthor(element.getElementsByTagName("Author").item(0).getTextContent());
                    oldCard.setValuable(Valuable.valueOf(element.getElementsByTagName("Valuable").item(0).getTextContent()));

                    oldCards.add(oldCard);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return oldCards;
    }
}
