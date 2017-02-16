/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import ListaSimplePalabras.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author ana_j
 */
public class XMLParser extends DefaultHandler {

    boolean bpalabra = false;
    LSPalabras lsp = JFMenuPrincipal.lsp;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //ACA SE CAPTURA EL TAG DE ABERTURA
        System.out.println("<<" + qName);

        if (qName.equalsIgnoreCase("palabra")) {
            bpalabra = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //ACA SE MUESTRA EL TAG DE CIERRE
        System.out.println(qName + ">>");
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        //EN ESTE DE ACA SE MUESTRA EL CONTENIDO
        //SI ES UN NODO CON HIJOS ENTONCES SU CONTENIDO ESTARA EN BLANCO
        //SI ES UN NODO SIN HIJOS SI TENDRA CONTENIDO
        String contenido = new String(ch, start, length);

        if (bpalabra) {
            System.out.println("--- " + new String(ch, start, length));
            //********* insertar en la estructura *********//           
            lsp.insertarFinal(new String(ch, start, length));
            bpalabra = false;
        }
       // lsp.Mostrar();
    }
}
