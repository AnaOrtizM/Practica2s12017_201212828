/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaSimpleMano;

import Utils.Graficador;

/**
 *
 * @author ana_j
 */
public class LSMano {

    private NodoLSM inicio;
    private NodoLSM fin;

    Graficador g = new Graficador();

    public LSMano() {
        inicio = fin = null;
    }

    public boolean estaVacia() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }

    public void insertarFinal(String fichaM) {
        NodoLSM actual;

        if (estaVacia()) {
            actual = new NodoLSM(fichaM, null);
            inicio = actual;
            fin = actual;
        } else {
            actual = new NodoLSM(fichaM, null);
            fin.setSiguiente(actual);
            fin = actual;
        }
    }

    public void Buscar(String fichaM) {
        NodoLSM temp;
        if (estaVacia()) {
            System.out.println("Lista de Mano Vacia");
        } else {
            temp = inicio;
            while (temp != null) {
                if (fichaM.equals(temp.getFichaM())) {
                    System.out.println(temp.getFichaM());
                    break;
                }

                temp = temp.getSiguiente();
            }
        }
    }

    public String Extraer(int posicion) {
        NodoLSM temp;
        String actual = null;
        temp = inicio;

        if (posicion == 0) {
            actual = inicio.getFichaM();
            inicio = inicio.getSiguiente();
        } else {
            for (int i = 0; i < 7; i++) {
                if (i == posicion - 1) {
                    actual = temp.getSiguiente().getFichaM();
                    temp.setSiguiente(temp.getSiguiente().getSiguiente());
                }
                temp = temp.getSiguiente();
            }
        }
        if(inicio == null){
            fin = null;
        }
        return actual;
    }

    /*    public void Eliminar(String fichaM) {
        NodoLSM temp;
        NodoLSM anterior;

        temp = inicio;
        anterior = null;

        while (temp != null) {
            if (fichaM.equals(temp.getFichaM())) {
                if (temp == inicio) {
                    inicio = inicio.getSiguiente();
                } else {
                    anterior.setSiguiente(temp);
                }
            }
            System.out.println(temp.getFichaM());
            anterior = temp;
            temp = temp.getSiguiente();
        }
    }*/
    public void Mostrar() {
        NodoLSM temp;
        if (estaVacia()) {
            System.out.println("Lista de Mano Vacia");
        } else {
            temp = inicio;
            while (temp != null) {
                System.out.println(temp.getFichaM());
                temp = temp.getSiguiente();
            }
        }
    }

    public void Graficar() {
        String grafo;
        NodoLSM temp;
        grafo = "digraph g {  node [shape = doublecircle,height=.1];  { \n";
        if (estaVacia()) {
            grafo += "\"ListaVacia\" [label = \"Lista Vacia\"]";
        } else {
            temp = inicio;
            int i = 0;
            while (temp != null) {
                grafo += "\"" + i + "\" [label = \"" + temp.getFichaM() + "\"];\n";
                if (i > 0) {
                    grafo += "\"" + (i - 1) + "\" -> \"" + i + "\" ;\n";
                }
                temp = temp.getSiguiente();
                i = i + 1;
            }
        }
        grafo += "}  labelloc=\"t\"; label=\" LISTA SIMPLE MANO\";}";
        System.out.println(grafo);

        g.graficar("LSMano", grafo);
    }
}
