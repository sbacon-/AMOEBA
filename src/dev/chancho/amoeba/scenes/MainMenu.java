package dev.chancho.amoeba.scenes;

import dev.chancho.amoeba.Board;
import dev.chancho.amoeba.utilities.Sketch;

import java.awt.*;

public class MainMenu implements Scene{
    Board b;
    Sketch s;

    public MainMenu(Board b) {
        this.b = b;
        this.s = b.sketch;
    }

    @Override
    public void render(Graphics g) {
        g.setFont(s.pcs.deriveFont(72.0f));
        s.fontMetrics = g.getFontMetrics();
        //int titleWidth = s.fontMetrics.stringWidth(b.id);
        int titleHeight = s.fontMetrics.getHeight();
        g.setColor(s.textColor);
        g.drawString(b.id,32,32+titleHeight);

    }
}
