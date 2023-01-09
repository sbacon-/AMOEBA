package dev.chancho.amoeba;

import java.awt.*;

public class Watchdog {
    /*
    This is a class for handling the dimensions and number of displays
    updateMonitor() is used to change the window size of the board passed to it
    */
    public GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
    public GraphicsDevice[] devices = g.getScreenDevices();
    public Rectangle[] monitors = new Rectangle[devices.length];
    public int selectedMonitor = 0;

    public Watchdog() {
        //Maintain monitors as list of Rectangles
        for(int i = 0;i<devices.length;i++) {
            monitors[i] = new Rectangle();
            monitors[i].setBounds(devices[i].getDefaultConfiguration().getBounds());
        }
    }

    //Update the size of the window to fit the monitor
    public void updateMonitor(int selectedMonitor, Board board){
        selectedMonitor %= devices.length;
        this.selectedMonitor = selectedMonitor;
        board.setPreferredSize(monitors[selectedMonitor].getSize());
        board.hub.pack();
        board.hub.setLocation(monitors[selectedMonitor].getLocation());
    }
}
