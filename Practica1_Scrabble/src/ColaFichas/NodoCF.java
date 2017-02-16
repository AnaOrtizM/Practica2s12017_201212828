/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColaFichas;

/**
 *
 * @author ana_j
 */
public class NodoCF {

    private NodoCF siguiente;
    private String ficha;

    public NodoCF(String ficha, NodoCF siguiente) {
        this.siguiente = siguiente;
        this.ficha = ficha;
    }

    public void setSiguiente(NodoCF siguiente) {
        this.siguiente = siguiente;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    public NodoCF getSiguiente() {
        return siguiente;
    }

    public String getFicha() {
        return ficha;
    }
}
