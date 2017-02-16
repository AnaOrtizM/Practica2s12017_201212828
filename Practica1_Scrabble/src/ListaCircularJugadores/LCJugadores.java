/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaCircularJugadores;

import Utils.Graficador;

/**
 *
 * @author ana_j
 */
public class LCJugadores {

    private NodoLCJ inicio;
    private NodoLCJ fin;

    Graficador g = new Graficador();

    public LCJugadores() {
        inicio = fin = null;
    }

    public boolean estaVacia() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }

    public void Insertar(String usuario) {

        NodoLCJ actual;

        if (estaVacia()) {
            actual = new NodoLCJ(usuario, null);
            inicio = actual;
            fin = actual;
            fin.setSiguiente(inicio);
        } else {
            actual = new NodoLCJ(usuario, null);
            fin.setSiguiente(actual);
            actual.setSiguiente(inicio);
            fin = actual;
        }
    }

    public void Mostrar() {
        NodoLCJ temp;
        if (estaVacia()) {
            System.out.println("Lista de Jugadores Vacia");
        } else {
            temp = inicio;
            do {
                System.out.println(temp.getUsuario());
                temp = temp.getSiguiente();
            } while (temp != inicio);
        }
    }

    public void Graficar() {
        String grafo;
        NodoLCJ temp;
        grafo = "digraph g {  node [shape = oval,height=.1];  { \n";
        if (estaVacia()) {
            grafo += "\"ListaVacia\" [label = \"Lista Vacia\"]";
        } else {
            temp = inicio;
            int i = 1;
            do {
                grafo += "\"" + i + "\" [label = \"" + temp.getUsuario() + "\"];\n";
                temp = temp.getSiguiente();
                if (temp == inicio) {
                    grafo += "\"" + i + "\" -> \"" + 1 + "\" ;\n";
                } else {
                    grafo += "\"" + i + "\" -> \"" + (i + 1) + "\" ;\n";
                }
                System.out.println(temp.getUsuario());
                i = i + 1;
            } while (temp != inicio);
        }
        grafo += "}  labelloc=\"t\"; label=\" LISTA CIRCULAR JUGADORES\";}";
        System.out.println(grafo);

        g.graficar("LCJugadores", grafo);
    }
}
