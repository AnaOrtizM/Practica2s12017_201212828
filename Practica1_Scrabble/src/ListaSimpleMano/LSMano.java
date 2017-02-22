/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaSimpleMano;

import Utils.Graficador;
import MatrizTablero.Ficha;
import Interfaz.JFTablero;
import javax.swing.JPanel;

/**
 *
 * @author ana_j
 */
public class LSMano {

    private NodoLSM inicio;
    private NodoLSM fin;
    public int tam = 0;

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

    public void insertarFinal(Ficha fichaM) {
        NodoLSM actual;
        tam++;

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

    public void Buscar(Ficha fichaM) {
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

    public Ficha Extraer(int posicion) {
        NodoLSM temp;
        tam--;
        Ficha actual = null;
        temp = inicio;

        if (posicion == 0) {
            actual = inicio.getFichaM();
            inicio = inicio.getSiguiente();
        } else {
            bucle1:
            for (int i = 0; i < 7; i++) {
                if (i == posicion - 1) {
                    actual = temp.getSiguiente().getFichaM();
                    if (temp.getSiguiente() == this.fin) {
                        this.fin = temp;
                    }
                    temp.setSiguiente(temp.getSiguiente().getSiguiente());
                    break bucle1;
                }
                temp = temp.getSiguiente();
            }
        }
        if (inicio == null) {
            fin = null;
        }
        temp = temp.getSiguiente();
        while (temp != null) {
            if (!temp.getFichaM().getLabelFicha().movida) {
                temp.getFichaM().getLabelFicha().setLabelBounds(temp.getFichaM().getLabelFicha().posx - 1, temp.getFichaM().getLabelFicha().posy);
            } else {
                temp.getFichaM().getLabelFicha().posx -= 1;
            }
            temp = temp.getSiguiente();
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

    public void pintarFichasTablero(JPanel panel) {
        NodoLSM temp;
        if (estaVacia()) {
            System.out.println("Lista de Mano Vacia");
        } else {
            temp = inicio;
            while (temp != null) {
                panel.add(temp.getFichaM().getLabelFicha());

                temp = temp.getSiguiente();
            }
        }
    }

    public void ValidarTiro() {
        NodoLSM temp = inicio;
        while (temp != null) {
            Ficha f = temp.getFichaM();
            if (f.getLabelFicha().movida) {
                int posxact = f.getLabelFicha().posxact;
                int posyact = f.getLabelFicha().posyact;
                if (JFTablero.matriz.get(posxact, posyact).getFicha() == null) {    //VALIDACION SI LA POSICION DONDE SE SOLTO ESTA VACIA                
                    this.Extraer(f.getLabelFicha().posx - 1);
                    f.getLabelFicha().sepuedemover = false;
                    JFTablero.matriz.get(posxact, posyact).setFicha(f);
                } else {
                    f.getLabelFicha().setLabelBounds(f.getLabelFicha().posx, f.getLabelFicha().posy);
                }
            }
            temp = temp.getSiguiente();
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
                grafo += "\"" + i + "\" [label = \"" + temp.getFichaM().getLetra() + "\"];\n";
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
