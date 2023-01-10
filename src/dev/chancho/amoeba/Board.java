package dev.chancho.amoeba;
import dev.chancho.amoeba.utilities.*;

import java.awt.*;

import javax.swing.*;

public class Board extends JPanel implements Runnable {
    public JFrame hub;
    public String id;

    public KListener kAdapter = new KListener();
    public Watchdog watchdog = new Watchdog();
    public Aria aria = new Aria();

    public Thread timer;
    public int DELAY = 60, ticks = 0;
    public Sketch sketch;
    public Jupiter jupiter;

    public Board(String id, JFrame hub){
        this.hub = hub;
        this.id= id;
        watchdog.updateMonitor(0,this);

        setBackground(Color.decode("#000000"));
        addKeyListener(kAdapter);
        addMouseListener(kAdapter);
        addMouseWheelListener(kAdapter);
        setFocusable(true);
        requestFocus();

        init();
    }

    private void init(){
        sketch = new Sketch();
        jupiter = new Jupiter();
        aria.play("splash");
    }

    private void tick(){
        /*
        if(kAdapter.esc) {
            kAdapter.esc = false;
            watchdog.updateMonitor(watchdog.selectedMonitor+1,this);
        }
         */
        ticks++;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        sketch.render(g,this);
    }

    @Override
    public void addNotify(){
        super.addNotify();
        timer = new Thread(this);
        timer.start();
    }

    @Override
    public void run() {
        long current,delta,sleep;
        current = System.currentTimeMillis();
        //noinspection InfiniteLoopStatement
        while(true){
            tick();
            repaint();
            delta = System.currentTimeMillis()-current;
            sleep = DELAY-delta;
            if(sleep<0)sleep=2;
            try{
                //noinspection BusyWait
                Thread.sleep(sleep);
            }catch(InterruptedException e){
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                System.out.println(msg);
            }
        }
    }
}
