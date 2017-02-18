/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatrizTablero;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author ana_j
 */
public class Ficha {

    public static final Integer TAM_FICHA = 40;

    String letra;
    MiJLabel labelFicha;

    Ficha(String letra, int posx, int posy) {
        this.letra = letra;
        this.labelFicha = new MiJLabel(letra);
        this.labelFicha.setBounds(posx * TAM_FICHA, posy * TAM_FICHA, TAM_FICHA, TAM_FICHA);
        this.labelFicha.setFont(new Font("Tahoma", 1, 10)); // NOI18N
        this.labelFicha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Border outsideBorder = new LineBorder(Color.gray);
        labelFicha.setBorder(outsideBorder);
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public MiJLabel getLabelFicha() {
        return labelFicha;
    }

    public void setLabelFicha(MiJLabel labelFicha) {
        this.labelFicha = labelFicha;
    }
}
