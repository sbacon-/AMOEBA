package dev.chancho.amoeba.utilities;

import dev.chancho.amoeba.Board;

import java.awt.*;

public class Sketch {

    public void render(Graphics g, Board b){
        g.setColor(Color.BLACK);
        g.drawRect(10,10,10,10);
    }
}
