/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatrizTablero;

import Interfaz.JFTablero;
import static Interfaz.JFTablero.matriz;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author ana_j
 */
public class MiJLabel extends JLabel {

    protected int lblYPt = 0;
    protected int lblXPt = 0;
    public int posx;
    public int posy;
    public int posxact;
    public int posyact;
    public boolean movida;
    public boolean sepuedemover;

    public MiJLabel(String text, int posx, int posy) {
        super(text);
        this.posx = posx;
        this.posy = posy;
        this.movida = false;
        this.sepuedemover = true;
        setListeners();
    }

    private void setListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                lblMousePressed(evt);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                lblMouseDragged(evt);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                lblMouseReleased(evt);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent evt) {
                lblMouseExited(evt);
            }
        });
    }

    public void setLabelBounds(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
        this.setBounds(Ficha.TAM_FICHA + 35 * posx, Ficha.TAM_FICHA * posy, Ficha.TAM_FICHA, Ficha.TAM_FICHA);
    }

    public void lblMousePressed(MouseEvent evt) {
        lblYPt = evt.getY();
        lblXPt = evt.getX();
    }

    public void lblMouseExited(MouseEvent evt) {
    }

    public void lblMouseReleased(MouseEvent evt) {
        if (sepuedemover) {
            Component parent = evt.getComponent().getParent();
            Point mouse = parent.getMousePosition();
            Integer posicionx = (mouse.x - lblXPt) / Ficha.TAM_FICHA;
            Integer posiciony = (mouse.y - lblYPt) / Ficha.TAM_FICHA;
            try {
                if (mouse.x > JFTablero.matriz.x * Ficha.TAM_FICHA || mouse.y > JFTablero.matriz.y * Ficha.TAM_FICHA) {
                    this.setBounds(Ficha.TAM_FICHA + 35 * posx, Ficha.TAM_FICHA * posy, Ficha.TAM_FICHA, Ficha.TAM_FICHA);
                    this.movida = false;
                } else if (JFTablero.matriz.get(posicionx, posiciony).getFicha() != null) {
                    this.setBounds(Ficha.TAM_FICHA + 35 * posx, Ficha.TAM_FICHA * posy, Ficha.TAM_FICHA, Ficha.TAM_FICHA);
                    this.movida = false;
                } else {
                    System.out.println(posicionx + "--" + posiciony);
                    this.posxact = posicionx;
                    this.posyact = posiciony;
                    this.movida = true;

                    this.setBounds(posicionx * Ficha.TAM_FICHA, posiciony * Ficha.TAM_FICHA, Ficha.TAM_FICHA, Ficha.TAM_FICHA);
                }
            } catch (Exception e) {
                this.setBounds(Ficha.TAM_FICHA + 35 * posx, Ficha.TAM_FICHA * posy, Ficha.TAM_FICHA, Ficha.TAM_FICHA);
                this.movida = false;
            }
        }
    }

    public void lblMouseDragged(MouseEvent evt) {
        if (sepuedemover) {
            Component parent = evt.getComponent().getParent();
            Point mouse = parent.getMousePosition();
            try {
                Integer posicionx = (mouse.x - lblXPt) / Ficha.TAM_FICHA;
                Integer posiciony = (mouse.y - lblYPt) / Ficha.TAM_FICHA;
                //System.out.println(posiciony);

                this.setBounds(mouse.x - lblXPt, mouse.y - lblYPt, Ficha.TAM_FICHA, Ficha.TAM_FICHA);

            } catch (Exception e) {
            }
        }
    }
}
