package com.timvanx.homework;


import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLTest1 {

    public static void main(String[] args) {
        String path = XMLTest1.class.getResource("").getFile();
        String xmlName = path + "bookstore.xml";
        System.out.println("路径为："+xmlName);

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();;
//em1
            DocumentBuilder docBuilder=dbf.newDocumentBuilder();
            Document doc = docBuilder.parse(xmlName);
            System.out.println(doc.getDocumentElement().getTagName());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}
