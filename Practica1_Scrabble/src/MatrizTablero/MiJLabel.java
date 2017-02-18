/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatrizTablero;

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

    public MiJLabel(String text) {
        super(text);
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
    }

    public void lblMousePressed(MouseEvent evt) {
        lblYPt = evt.getY();
        lblXPt = evt.getX();
    }

    public void lblMouseReleased(MouseEvent evt) {
        Component parent = evt.getComponent().getParent();
        Point mouse = parent.getMousePosition();
        try {
            Integer posicionx = (mouse.x - lblXPt) / Ficha.TAM_FICHA;
            Integer posiciony = (mouse.y - lblYPt) / Ficha.TAM_FICHA;
            System.out.println(posiciony);

            this.setBounds(posicionx * Ficha.TAM_FICHA, posiciony * Ficha.TAM_FICHA, Ficha.TAM_FICHA, Ficha.TAM_FICHA);

        } catch (Exception e) {
        }
    }

    public void lblMouseDragged(MouseEvent evt) {
        Component parent = evt.getComponent().getParent();
        Point mouse = parent.getMousePosition();
        try {
            Integer posicionx = (mouse.x - lblXPt) / Ficha.TAM_FICHA;
            Integer posiciony = (mouse.y - lblYPt) / Ficha.TAM_FICHA;
            System.out.println(posiciony);

            this.setBounds(mouse.x - lblXPt, mouse.y - lblYPt, Ficha.TAM_FICHA, Ficha.TAM_FICHA);

        } catch (Exception e) {
        }
    }
}
