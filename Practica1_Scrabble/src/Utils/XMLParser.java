/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Interfaz.JFMenuPrincipal;
import Interfaz.JFTablero;
import ListaSimplePalabras.*;
import MatrizTablero.MTablero;
import MatrizTablero.NodoMT;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author ana_j
 */
public class XMLParser extends DefaultHandler {

    boolean bpalabra = false;
    boolean bdimension = false;
    boolean bdobles = false;
    boolean btriples = false;
    boolean bcasilla = false;
    boolean bx = false;
    boolean by = false;

    int tipoX = 0;
    int tipoY = 0;
    public static int dimension;
    
    public LSPalabras lsp = JFMenuPrincipal.lsp;
    public MTablero mt = JFTablero.mt;

    public XMLParser() {
        super();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //ACA SE CAPTURA EL TAG DE ABERTURA
        System.out.println("<<" + qName);

        if (qName.equalsIgnoreCase("palabra")) {
            bpalabra = true;
        }

        if (qName.equalsIgnoreCase("dimension")) {
            bdimension = true;
        }

        if (qName.equalsIgnoreCase("dobles")) {
            bdobles = true;
        }

        if (qName.equalsIgnoreCase("triples")) {
            btriples = true;
        }

        if (qName.equalsIgnoreCase("x")) {
            bx = true;
        }

        if (qName.equalsIgnoreCase("y")) {
            by = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //ACA SE MUESTRA EL TAG DE CIERRE
        System.out.println(qName + ">>");

        if (qName.equalsIgnoreCase("dobles")) {
            bdobles = false;
        }

        if (qName.equalsIgnoreCase("triples")) {
            btriples = false;
        }
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

        if (bdimension) {
            System.out.println("--- " + new String(ch, start, length));
            String dim = new String(ch, start, length).trim();
            dimension = Integer.parseInt(dim);
            JFTablero.mt = new MTablero(dimension, dimension);
            bdimension = false;
        }

        if (bdobles) {
            if (bx) {
                System.out.println("--- " + new String(ch, start, length));
                String x = new String(ch, start, length).trim();
                tipoX = Integer.parseInt(x);
                bx = false;
            } else if (by) {
                System.out.println("--- " + new String(ch, start, length));
                String y = new String(ch, start, length).trim();
                tipoY = Integer.parseInt(y);
                NodoMT nodo = JFTablero.mt.get(tipoX-1, tipoY-1);
                nodo.setTipo(1);
                by = false;
            }
        }
        
        if (btriples) {
            if (bx) {
                System.out.println("--- " + new String(ch, start, length));
                String x = new String(ch, start, length).trim();
                tipoX = Integer.parseInt(x);
                bx = false;
            } else if (by) {
                System.out.println("--- " + new String(ch, start, length));
                String y = new String(ch, start, length).trim();
                tipoY = Integer.parseInt(y);
                NodoMT nodo = JFTablero.mt.get(tipoX-1, tipoY-1);
                nodo.setTipo(2);
                by = false;
            }
        }
    }
}
