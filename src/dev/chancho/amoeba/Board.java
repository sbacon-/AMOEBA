package dev.chancho.amoeba;

import dev.chancho.amoeba.scenes.MainMenu;
import dev.chancho.amoeba.scenes.Scene;
import dev.chancho.amoeba.scenes.Splash;
import dev.chancho.amoeba.utilities.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

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
    public Scene[] scenes;
    public int activeScene;

    public Board(String id, JFrame hub){
        this.hub = hub;
        this.id= id;
        watchdog.updateMonitor(0,this);

        setBackground(Color.decode("#000000"));
        addKeyListener(kAdapter);
        addMouseListener(kAdapter);
        addMouseWheelListener(kAdapter);
        init();
    }

    private void init(){
        sketch = new Sketch();
        jupiter = new Jupiter();
        scenes = new Scene[2];
        scenes[0] = new Splash(this);
        scenes[1] = new MainMenu(this);
        activeScene = 0;
        aria.play("hello");
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

    public Scene getActiveScene(){
        return scenes[activeScene];
    }
}
