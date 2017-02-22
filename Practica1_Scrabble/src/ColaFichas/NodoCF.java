/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColaFichas;

import MatrizTablero.Ficha;

/**
 *
 * @author ana_j
 */
public class NodoCF {

    private NodoCF siguiente;
    private Ficha ficha;

    public NodoCF(Ficha ficha, NodoCF siguiente) {
        this.siguiente = siguiente;
        this.ficha = ficha;
    }

    public void setSiguiente(NodoCF siguiente) {
        this.siguiente = siguiente;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public NodoCF getSiguiente() {
        return siguiente;
    }

    public Ficha getFicha() {
        return ficha;
    }
}
