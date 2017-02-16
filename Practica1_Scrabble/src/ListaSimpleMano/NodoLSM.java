/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaSimpleMano;

/**
 *
 * @author ana_j
 */
public class NodoLSM {

    private NodoLSM siguiente;
    private String fichaM;

    public NodoLSM(String fichaM, NodoLSM siguiente) {
        this.siguiente = siguiente;
        this.fichaM = fichaM;
    }

    public void setSiguiente(NodoLSM siguiente) {
        this.siguiente = siguiente;
    }

    public void setFichaM(String fichaM) {
        this.fichaM = fichaM;
    }

    public NodoLSM getSiguiente() {
        return siguiente;
    }

    public String getFichaM() {
        return fichaM;
    }
}
