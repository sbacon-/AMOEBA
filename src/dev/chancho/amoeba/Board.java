package dev.chancho.amoeba;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JPanel;

public class Board extends JPanel implements Runnable {
    public int WIDTH = 1366;
    public int HEIGHT = 768;
    public int DELAY = 60;
    public int ticks = 0;
    public String id;

    public KListener kAdapter = new KListener();

    public Board(String id){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.decode("#ffffff"));
        addKeyListener(kAdapter);
        addMouseListener(kAdapter);
        addMouseWheelListener(kAdapter);
        setFocusable(true);
        requestFocus();

        this.id= id;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
    
}
