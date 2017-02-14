/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import javax.swing.tree.DefaultMutableTreeNode;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author ana_j
 */
public class XMLParser extends DefaultHandler{
public DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //ACA SE CAPTURA EL TAG DE ABERTURA
        System.out.println("<<" + qName);
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(qName);
        root.add(node);
        root = node;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //ACA SE MUESTRA EL TAG DE CIERRE
        System.out.println(qName + ">>");
        root = (DefaultMutableTreeNode)root.getParent();
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        //EN ESTE DE ACA SE MUESTRA EL CONTENIDO
        //SI ES UN NODO CON HIJOS ENTONCES SU CONTENIDO ESTARA EN BLANCO
        //SI ES UN NODO SIN HIJOS SI TENDRA CONTENIDO
        String contenido = new String(ch, start, length);
        //Se compara si el string esta vacio, o si tiene contenido
        if (!contenido.trim().isEmpty()) {
            System.out.println(new String(ch, start, length));
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(contenido);
            root.add(node);
        }
    }
/*    public void XMLParser(String path) {
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean bfname = false;
                boolean blname = false;
                boolean bnname = false;
                boolean bsalary = false;

                public void startElement(String uri, String localName, String qName,
                        Attributes attributes) throws SAXException {

                    System.out.println("Start Element :" + qName);

                    if (qName.equalsIgnoreCase("FIRSTNAME")) {
                        bfname = true;
                    }

                    if (qName.equalsIgnoreCase("LASTNAME")) {
                        blname = true;
                    }

                    if (qName.equalsIgnoreCase("NICKNAME")) {
                        bnname = true;
                    }

                    if (qName.equalsIgnoreCase("SALARY")) {
                        bsalary = true;
                    }

                }

                public void endElement(String uri, String localName,
                        String qName) throws SAXException {

                    System.out.println("End Element :" + qName);

                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (bfname) {
                        System.out.println("First Name : " + new String(ch, start, length));
                        bfname = false;
                    }

                    if (blname) {
                        System.out.println("Last Name : " + new String(ch, start, length));
                        blname = false;
                    }

                    if (bnname) {
                        System.out.println("Nick Name : " + new String(ch, start, length));
                        bnname = false;
                    }

                    if (bsalary) {
                        System.out.println("Salary : " + new String(ch, start, length));
                        bsalary = false;
                    }

                }

            };

            saxParser.parse(path, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
}
