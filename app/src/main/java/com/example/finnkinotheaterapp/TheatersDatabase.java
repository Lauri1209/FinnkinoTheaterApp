package com.example.finnkinotheaterapp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class TheatersDatabase {

    // Singleton
    private static TheatersDatabase instance = null;
    public static TheatersDatabase getInstance(){
        if (instance == null) {
            instance = new TheatersDatabase();
        }
        return instance;
    }

    ArrayList<Theater> theaters;

    public void XMLreader() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String url = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document document = builder.parse(url);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getDocumentElement().getElementsByTagName("TheatreArea");

            theaters = new ArrayList<Theater>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String elementID = element.getElementsByTagName("ID").item(0).getTextContent();
                    String elementText = element.getElementsByTagName("Name").item(0).getTextContent();

                    if (elementText.contains(":")) {
                        theaters.add(new Theater(elementID, elementText));
                    }
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Theater> returnTheaters() {return theaters;}
}
