package org.example;

import org.example.parsers.AbstractParser;
import org.example.parsers.DOMParser;
import org.example.parsers.SAXParser;
import org.example.parsers.StAXParser;

public class Main {

    public static void main(String[] args) {
        AbstractParser saxParser = new SAXParser();
        AbstractParser domParser = new DOMParser();
        AbstractParser staxParser = new StAXParser();
    }

}