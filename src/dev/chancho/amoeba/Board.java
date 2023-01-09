package dev.chancho.amoeba;
import java.awt.*;

import javax.swing.*;

public class Board extends JPanel implements Runnable {

    public String id;
    public KListener kAdapter = new KListener();
    public JFrame hub;

    //Monitor
    GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice[] devices = g.getScreenDevices();
    Rectangle[] monitors = new Rectangle[devices.length];
    int selectedMonitor = 0;

    public Thread timer;
    public int DELAY = 60, ticks = 0;
    public Sketch sketch;

    public Board(String id, JFrame hub){
        this.hub = hub;
        this.id= id;

        //Obtain monitors as list of Rectangles and update to monitor 0
        for(int i=0; i<devices.length; i++) {
            monitors[i] = new Rectangle();
            monitors[i].setBounds(devices[i].getDefaultConfiguration().getBounds());
        }
        updateMonitor(selectedMonitor);

        setBackground(Color.decode("#ffffff"));
        addKeyListener(kAdapter);
        addMouseListener(kAdapter);
        addMouseWheelListener(kAdapter);
        setFocusable(true);
        requestFocus();

        init();
    }

    private void init(){
        sketch = new Sketch();
    };

    private void tick(){
        if(kAdapter.esc) {
            updateMonitor((selectedMonitor + 1) % devices.length);
            kAdapter.esc = false;
        }
    };

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
        while(true){
            tick();
            repaint();
            delta = System.currentTimeMillis()-current;
            sleep = DELAY-delta;
            if(sleep<0)sleep=2;
            try{
                Thread.sleep(sleep);
            }catch(InterruptedException e){
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                System.out.println(msg);
            }
        }
    }

    public void updateMonitor(int selectedMonitor){
        this.selectedMonitor = selectedMonitor;
        setPreferredSize(monitors[selectedMonitor].getSize());
        hub.pack();
        hub.setLocation(monitors[selectedMonitor].getLocation());
    }
}
