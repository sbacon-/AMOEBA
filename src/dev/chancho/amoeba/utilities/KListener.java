package dev.chancho.amoeba.utilities;

import dev.chancho.amoeba.Board;
import dev.chancho.amoeba.scenes.WriteScene;

import java.awt.*;
import java.awt.event.*;

public class KListener implements KeyListener, MouseListener, MouseMotionListener,MouseWheelListener{
    public Board b;
    public Point mousePosition=null;
    public boolean mouseDown = false,
            mouseClicked = false,
            esc = false;

    public KListener(Board b){
        this.b = b;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
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
        if(b.getActiveScene().getClass() == WriteScene.class)
            ((WriteScene)b.getActiveScene()).mouse_press(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
        mouseClicked = true;
        if(b.getActiveScene().getClass() == WriteScene.class)
            ((WriteScene)b.getActiveScene()).mouse_release(e);
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(b.getActiveScene().getClass() == WriteScene.class)
            ((WriteScene)b.getActiveScene()).wheel(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(b.getActiveScene().getClass() == WriteScene.class)
            ((WriteScene)b.getActiveScene()).key_down(e);
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
            esc = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(b.getActiveScene().getClass() == WriteScene.class)
            ((WriteScene)b.getActiveScene()).key_up(e);
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
            esc = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}