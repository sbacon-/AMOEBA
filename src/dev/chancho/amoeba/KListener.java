package dev.chancho.amoeba;

import java.awt.*;
import java.awt.event.*;

public class KListener implements KeyListener, MouseListener, MouseWheelListener{
    Point mousePosition = null;
    boolean mouseClicked = false,
            esc = false;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("MOUSE CLICKED: "+e.getX()+","+e.getY());
        System.out.println("ON SCREEN: "+e.getXOnScreen()+","+e.getYOnScreen());
        System.out.println("");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed: "+e.getKeyCode());
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
            esc = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
            esc = false;
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}