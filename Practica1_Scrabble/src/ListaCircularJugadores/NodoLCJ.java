/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaCircularJugadores;

import ListaSimplePalabras.NodoLSP;

/**
 *
 * @author ana_j
 */
public class NodoLCJ {

    private NodoLCJ siguiente;
    private String usuario;

    public NodoLCJ(String usuario, NodoLCJ siguiente) {
        this.siguiente = siguiente;
        this.usuario = usuario;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public NodoLCJ getSiguiente() {
        return siguiente;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSiguiente(NodoLCJ siguiente) {
        this.siguiente = siguiente;
    }
}
