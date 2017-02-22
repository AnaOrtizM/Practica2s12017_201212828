/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatrizTablero;

//import static MatrizTablero.Ficha.TAM_FICHA;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author ana_j
 */
public class NodoMT {

    public NodoMT arriba;
    public NodoMT abajo;
    public NodoMT izquierda;
    public NodoMT derecha;
    private Ficha ficha;

    public int tipo;
    public int posicionX;
    public int posicionY;

    JLabel lbl = new JLabel();

    public NodoMT() {

    }

    public NodoMT(int posx, int posy) {
        this.posicionX = posx;
        this.posicionY = posy;
        this.lbl = new JLabel("");
        this.lbl.setBounds(posx * Ficha.TAM_FICHA, posy * Ficha.TAM_FICHA, Ficha.TAM_FICHA, Ficha.TAM_FICHA);
        this.lbl.setFont(new Font("Tahoma", 1, 10)); // NOI18N
        this.lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Border outsideBorder = new LineBorder(Color.gray);
        lbl.setBorder(outsideBorder);
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
        this.ficha.getLabelFicha().setBackground(Color.blue);
        this.ficha.getLabelFicha().setForeground(Color.white);
        this.ficha.getLabelFicha().setOpaque(true);
        this.ficha.getLabelFicha().repaint();
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
        if (tipo == 1) {
            this.lbl.setBackground(Color.red);
            this.lbl.setOpaque(true);
            this.lbl.repaint();
        } else if (tipo == 2) {
            this.lbl.setBackground(Color.blue);
            this.lbl.setOpaque(true);
            this.lbl.repaint();
        }
    }

    public String getContenido() {
        if (this.ficha == null) {
            return "" + this.posicionX + "," + this.posicionY;
        } else {
            return this.ficha.getLetra();
        }
    }
    
    public String getPosicionString(){
        return "" + this.posicionX + "," + this.posicionY; 
    }
}
