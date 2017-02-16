/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColaFichas;

import Utils.Graficador;

/**
 *
 * @author ana_j
 */
public class CFichas {

    private NodoCF inicio;
    private NodoCF fin;

    Graficador g = new Graficador();

    public CFichas() {
        inicio = fin = null;
    }

    public boolean estaVacia() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }

    public void Push(String ficha) {

        NodoCF actual;

        if (estaVacia()) {
            actual = new NodoCF(ficha, null);
            inicio = actual;
            fin = actual;
        } else {
            actual = new NodoCF(ficha, null);
            fin.setSiguiente(actual);
            fin = actual;
        }
    }

    public void Mostrar() {
        NodoCF temp;
        if (estaVacia()) {
            System.out.println("Cola de Fichas Vacia");
        } else {
            temp = inicio;
            while (temp != null) {
                System.out.println(temp.getFicha());
                temp = temp.getSiguiente();
            }
        }
    }

    public void Graficar() {
        String grafo;
        NodoCF temp;
        grafo = "digraph g {  node [shape = circle,height=.1];  { \n";
        if (estaVacia()) {
            grafo += "\"ColaVacia\" [label = \"Cola Vacia\"]";
        } else {
            temp = inicio;
            int i = 0;
            while (temp != null) {
                grafo += "\"" + i + "\" [label = \"" + temp.getFicha() + "\"];\n";
                if (i > 0) {
                    grafo += "\"" + (i - 1) + "\" -> \"" + i + "\" ;\n";
                }
                temp = temp.getSiguiente();
                i = i + 1;
            }
        }
        grafo += "}  labelloc=\"t\"; label=\" COLA FICHAS\";}";
        System.out.println(grafo);

        g.graficar("CFichas", grafo);
    }
    
    public void Fichas(){
        String ficha[] = new String[25];
        ficha[0] = "A";
        ficha[1] = "B";
        ficha[2] = "C";
        ficha[3] = "D";
        ficha[4] = "E";
        ficha[5] = "F";
        ficha[6] = "G";
        ficha[7] = "H";
        ficha[8] = "I";
        ficha[9] = "J";
        ficha[10] = "L";
        ficha[11] = "M";
        ficha[12] = "N";
        ficha[13] = "Ñ";
        ficha[14] = "O";
        ficha[15] = "P";
        ficha[16] = "Q";
        ficha[17] = "R";
        ficha[18] = "S";
        ficha[19] = "T";
        ficha[20] = "U";
        ficha[21] = "V";
        ficha[22] = "X";
        ficha[23] = "Y";
        ficha[24] = "Z";                
    }
}
