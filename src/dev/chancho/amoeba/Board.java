package dev.chancho.amoeba;

import dev.chancho.amoeba.scenes.*;
import dev.chancho.amoeba.ui.UIButton;
import dev.chancho.amoeba.utilities.*;

import javax.swing.JPanel;
import java.awt.*;

public class Board extends JPanel implements Runnable {
    public Hub hub;
    public String id;

    public KListener kAdapter = new KListener(this);
    public Watchdog watchdog = new Watchdog();
    public Aria aria = new Aria();

    public Thread timer;
    public int DELAY = 60, ticks = 0;
    public Sketch sketch;
    public Jupiter jupiter;
    public Scene[] scenes;
    private int activeScene;

    public Board(String id, Hub hub){
        this.hub = hub;
        this.id= id;
        watchdog.updateMonitor(0,this);
        setPreferredSize(watchdog.getResolution());
        if(hub.transparency_supported) {
            setOpaque(false);
        }else{
            setBackground(Color.BLACK);
        }
        addKeyListener(kAdapter);
        addMouseListener(kAdapter);
        addMouseMotionListener(kAdapter);
        addMouseWheelListener(kAdapter);
        setFocusable(true);
        requestFocus();
        init();
    }

    private void init(){
        sketch = new Sketch();
        jupiter = new Jupiter(this);
        scenes = new Scene[5];
        scenes[0] = new Splash(this);
        scenes[1] = new MainMenu(this);
        scenes[2] = new ReadScene(this);
        scenes[3] = new WriteScene(this);
        scenes[4] = new OptionScene(this);
        setActiveScene(0);
        //aria.play("hello");
    }

    private void tick(){
        ticks++;
        for(UIButton button : getActiveScene().getButtons()){
            button.click = false;
            button.determineHover(kAdapter.mousePosition, kAdapter.mouseDown, kAdapter.mouseClicked);
        }
        getActiveScene().tick();
        kAdapter.mouseClicked = false;
        /*
        if(kAdapter.esc) {
            kAdapter.esc = false;
            watchdog.updateMonitor(watchdog.selectedMonitor+1,this);
        }
         */
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
    public void setActiveScene(int scene){
        this.activeScene = scene;
        getActiveScene().onSceneActive();
    }
}
