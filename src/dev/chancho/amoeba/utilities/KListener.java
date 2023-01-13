package dev.chancho.amoeba.utilities;

import java.awt.*;
import java.awt.event.*;

public class KListener implements KeyListener, MouseListener, MouseMotionListener,MouseWheelListener{
    public Point mousePosition=null;
    public boolean mouseDown = false,
            mouseClicked = false,
            esc = false;


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("MOUSE CLICKED: "+e.getX()+","+e.getY());
        System.out.println("ON SCREEN: "+e.getXOnScreen()+","+e.getYOnScreen());
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition = e.getPoint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
        mouseClicked = true;
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
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
    public void keyTyped(KeyEvent e) {
    }

}