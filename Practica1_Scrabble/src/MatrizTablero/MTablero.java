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
    public int x;
    public int y;

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

    public void graficarMatriz() {
        String grafo;
        NodoMT tempX = this.raiz;
        NodoMT tempY = this.raiz;

        grafo = "digraph G {\n"
                + "rankdir = TB;\n"
                + "rank = min;\n"
                + "node[style=filled,shape=box, label=\"Inicio\", rankdir=UD];\n";

        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                grafo += "\"" + i + "," + j + "\"[label=\"" + tempX.getContenido() + "\", style=filled];\n";
                tempX = tempX.derecha;
            }
            tempY = tempY.abajo;
            tempX = tempY;
        }

        for (int j = 0; j < y; j++) {
            for (int i = 0; i < (x - 1); i++) {
                grafo += "\"" + i + "," + j + "\" -> \"" + (i + 1) + "," + j + "\"[constraint=false];\n";
                grafo += "\"" + (i + 1) + "," + j + "\" -> \"" + i + "," + j + "\"[constraint=false];\n";
                grafo += "{rank=same;\"" + i + "," + j + "\" \"" + (i + 1) + "," + j + "\"}\n";
                grafo += "{rank=same;\"" + (i + 1) + "," + j + "\" \"" + i + "," + j + "\"}\n";
            }
        }

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < (x - 1); j++) {
                grafo += "\"" + i + "," + j + "\" -> \"" + i + "," + (j + 1) + "\"[rankdir=UD];\n";
                grafo += "\"" + i + "," + (j + 1) + "\" -> \"" + i + "," + j + "\"[rankdir=UD];\n";
            }
        }

        grafo += "labelloc=\"t\"; label=\" MATRIZ ORTOGONAL TABLERO\";}";
        System.out.println(grafo);

        g.graficar("MTablero", grafo);
    }

    public void graficarMatrizSinUnParDeLineas() {

        String grafo;
        NodoMT tempX;
        NodoMT tempY;

        grafo = "digraph G {\n"
                + "graph [splines=ortho, nodesep=0.5];\n"
                + "style=filled;\n"
                + "color=lightgrey;\n"
                + "node[style=filled,shape=box];\n"
                + "ranksep=.75; ";

        grafo += "\"" + this.raiz.getPosicionString() + "\" [label=\"" + this.raiz.getContenido() + "\"];";

        /*grafo += "subgraph cluster0{ node[shape = record];\n"
                + "rankdir=\"TB\";\n"
                + "node[group=a];";*/
        tempX = this.raiz;
        tempY = this.raiz;

        for (int i = 1; i < y; i++) {
            tempY = tempY.abajo;

            grafo += "\"" + "0" + "," + (i - 1) + "\" -> \"" + "0" + "," + i + "\" ;\n";
            grafo += "\"" + "0" + "," + i + "\" -> \"" + "0" + "," + (i - 1) + "\" ;\n";

            grafo += "\"" + tempY.getPosicionString() + "\" [style=filled,label=\"" + tempY.getContenido() + "\",shape=box];";
        }

        /* grafo += "edge[style=invis];\n"
                + "}";*/
        /**
         * ***
         */
        /* grafo += "{ rank=same;\n"
                + "\"0,0\";";*/
        for (int i = 1; i < x; i++) {
            tempX = tempX.derecha;

            grafo += "\"" + (i - 1) + "," + "0" + "\" -> \"" + i + "," + "0" + "\" ;\n";
            grafo += "\"" + i + "," + "0" + "\" -> \"" + (i - 1) + "," + "0" + "\" ;\n";

            grafo += "\"" + tempX.getPosicionString() + "\" [style=filled,label=\"" + tempX.getContenido() + "\",shape=box];";
        }

        // grafo += "}";
        /**
         * ****
         */
        /**
         * *****************
         */
        tempX = this.raiz;

        for (int j = 1; j < x; j++) {
            tempX = tempX.derecha;
            tempY = tempX;

            /*      grafo += "{\n"
                    + " node [shape = record];\n"
                    + " rankdir=TB;\n"
                    + " node[group=a];";*/
            for (int i = 1; i < y; i++) {
                tempY = tempY.abajo;

                grafo += "\"" + j + "," + (i - 1) + "\" -> \"" + j + "," + i + "\" ;\n";
                grafo += "\"" + j + "," + i + "\" -> \"" + j + "," + (i - 1) + "\" ;\n";

                grafo += "\"" + (j - 1) + "," + i + "\" -> \"" + j + "," + i + "\" ;\n";
                grafo += "\"" + j + "," + i + "\" -> \"" + (j - 1) + "," + i + "\" ;\n";

                grafo += "\"" + tempY.getPosicionString() + "\" [style=filled,label=\"" + tempY.getContenido() + "\",shape=box];";
            }
            /*   grafo += "edge[style=invis];\n"
                    + "}";*/

        }
        /**
         * *****************
         */

        grafo += "}  labelloc=\"t\"; label=\" MATRIZ ORTOGONAL TABLERO\";}";
        System.out.println(grafo);

        g.graficar("MTablero", grafo);
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
            String c = null;
            while (temp != null) {
                grafo += "\"" + i + j + c + "\" [label = \"" + x + y + temp.getContenido() + "\"];\n";
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
                grafo += "\"" + i + j + "\" [label = \"" + x + "," + y + "\"];\n";
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
