/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatrizTablero;

import Utils.Graficador;
import javax.swing.JPanel;

/**
 *
 * @author ana_j
 */
public class MTablero {

    private NodoMT raiz;
    private int x;
    private int y;
    
    Graficador g = new Graficador();

    public MTablero(int tamx, int tamy) {
        this.x = tamx;
        this.y = tamy;
        this.raiz = new NodoMT(0, 0);
        NodoMT auxx;
        NodoMT auxy = null;
        for (int j = 0; j < y; j++) {
            if (auxy == null) {
                auxy = raiz;
            } else {
                auxy.abajo = new NodoMT(0, j);
                auxy.abajo.arriba = auxy;
                auxy = auxy.abajo;
            }
            auxx = auxy;
            for (int i = 1; i < x; i++) {
                auxx.derecha = new NodoMT(i, j);
                auxx.derecha.izquierda = auxx;
                auxx = auxx.derecha;
                NodoMT aux = buscarNodo(i, j - 1);
                if (aux != null) {
                    aux.abajo = auxx;
                    auxx.arriba = aux;
                }
            }
        };
    }

    public NodoMT buscarNodo(int posx, int posy) {
        NodoMT aux = raiz;
        if (posx > x || posy > y || posx < 0 || posy < 0) {
            return null;
        }
        for (int j = 0; j < posy; j++) {
            aux = aux.abajo;
        }
        for (int i = 0; i < posx; i++) {
            aux = aux.derecha;
        }
        return aux;
    }

    public void imprimirMatrizx() {
        NodoMT auxx = raiz;
        NodoMT auxy = raiz;
        for (int j = 0; j < this.y; j++) {
            System.out.print(" | ");
            for (int i = 0; i < this.x; i++) {
                System.out.print(auxx.getFicha().letra + " | ");
                auxx = auxx.derecha;
            }
            System.out.print("\n");
            auxy = auxy.abajo;
            auxx = auxy;
        }
    }

    public void imprimirMatrizy() {
        NodoMT auxx = raiz;
        NodoMT auxy = raiz;
        for (int i = 0; i < this.x; i++) {
            System.out.print(" | ");
            for (int j = 0; j < this.y; j++) {
                System.out.print(auxy.getFicha().letra + " | ");
                auxy = auxy.abajo;
            }
            System.out.print("\n");
            auxx = auxx.derecha;
            auxy = auxx;
        }
    }

    public void insertar(int posx, int posy, String letra) {
        NodoMT aux = raiz;
        if (posx > x || posy > y || posx < 0 || posy < 0) {
            System.out.println("Indices fuera de rango.");
            return;
        }
        for (int j = 0; j < posy; j++) {
            aux = aux.abajo;
        }
        for (int i = 0; i < posx; i++) {
            aux = aux.derecha;
        }
        aux.getFicha().setLetra(letra);
    }

    public NodoMT get(int posx, int posy) {
        NodoMT aux = raiz;
        if (posx > x || posy > y || posx < 0 || posy < 0) {
            System.out.println("Indices fuera de rango.");
            return null;
        }
        for (int j = 0; j < posy; j++) {
            aux = aux.abajo;
        }
        for (int i = 0; i < posx; i++) {
            aux = aux.derecha;
        }
        return aux;
    }

    public void pintarTablero(JPanel panel) {
        for (int i = 0; i < this.x; i++) {
            for (int j = 0; j < this.y; j++) {
                NodoMT nodo = this.get(i, j);
                panel.add(nodo.lbl);
            }
        }
    }

    public void Graficar() {
        String grafo;
        NodoMT temp;
        grafo = "digraph g {  node [shape = box,height=.1];  { \n";
        if (raiz.abajo == null) {
            grafo += "\"MatrizVacia\" [label = \"Matriz Vacia\"]";
        } else {
            temp = raiz.abajo;
            int i = 0;
            int j = 0;
            while (temp != null) {
                grafo += "\"" + i + j + "\" [label = \"" + x + y + "\"];\n";
                if (temp.abajo != null) {
                    grafo += "\"" + i + "\" -> \"" + (i + 1) + "\" ;\n";
                }
                temp = temp.abajo;
                i++;
            }
        }

        if (raiz.derecha == null) {
            grafo += "\"MatrizVacia\" [label = \"Matriz Vacia\"]";
        } else {
            temp = raiz.derecha;
            int i = 0;
            int j = 0;
            while (temp != null) {
                grafo += "\"" + i + j + "\" [label = \"" + x + y + "\"];\n";
                if (temp.derecha != null) {
                    grafo += "\"" + j + "\" -> \"" + (j + 1) + "\" ;\n";
                }
                temp = temp.derecha;
                j++;
            }
        }
        grafo += "}  labelloc=\"t\"; label=\" MATRIZ ORTOGONAL TABLERO\";}";
        System.out.println(grafo);

        g.graficar("MTablero", grafo);
    }
}
