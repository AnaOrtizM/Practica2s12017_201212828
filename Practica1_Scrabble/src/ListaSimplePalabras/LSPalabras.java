/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaSimplePalabras;

import Utils.Graficador;

/**
 *
 * @author ana_j
 */
public class LSPalabras {

    private NodoLSP inicio;
    private NodoLSP fin;
    
    Graficador g = new Graficador();

    public LSPalabras() {
        inicio = fin = null;
    }

    public boolean estaVacia() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }

    public void insertarFinal(String palabra) {

        NodoLSP actual;

        if (estaVacia()) {
            actual = new NodoLSP(palabra, null);
            inicio = actual;
            fin = actual;
        } else {
            actual = new NodoLSP(palabra, null);
            fin.setSiguiente(actual);
            fin = actual;
        }
    }

    public void Mostrar() {
        NodoLSP temp;
        if (estaVacia()) {
            System.out.println("Lista de Palabras Vacia");
        } else {
            temp = inicio;
            while (temp != null) {
                System.out.println(temp.getPalabra());
                temp = temp.getSiguiente();
            }
        }
    }

    public void Graficar() {
        String grafo;
        NodoLSP temp;
        grafo = "digraph g {  node [shape = record,height=.1];  { \n";
        if (estaVacia()) {
            grafo += "\"ListaVacia\" [label = \"Lista Vacia\"]";
        } else {
            temp = inicio;
            int i = 0;
            while (temp != null) {
                grafo += "\"" + i + "\" [label = \"" + temp.getPalabra() + "\"];\n";
                if (i > 0) {
                    grafo += "\"" + (i - 1) + "\" -> \"" + i + "\" ;\n";
                }
                //System.out.println(temp.getPalabra().toString());
                temp = temp.getSiguiente();
                i = i + 1;
            }
        }
        grafo += "}  labelloc=\"t\"; label=\" LISTA SIMPLE PALABRAS\";}";
        System.out.println(grafo);
        
        g.graficar("LSPalabras", grafo);
    }

}
