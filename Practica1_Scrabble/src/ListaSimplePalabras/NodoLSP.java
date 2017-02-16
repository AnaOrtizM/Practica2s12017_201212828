/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaSimplePalabras;

/**
 *
 * @author ana_j
 */
public class NodoLSP {

    private NodoLSP siguiente;
    private String palabra;

    public NodoLSP(String palabra, NodoLSP siguiente) {
        this.siguiente = siguiente;
        this.palabra = palabra;
    }

    public void setSiguiente(NodoLSP siguiente) {
        this.siguiente = siguiente;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public NodoLSP getSiguiente() {
        return siguiente;
    }

    public String getPalabra() {
        return palabra;
    }
}
