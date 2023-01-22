package dev.chancho.amoeba.scenes;

import dev.chancho.amoeba.Board;
import dev.chancho.amoeba.ui.UIButton;
import dev.chancho.amoeba.utilities.Sketch;

import java.awt.*;

public class Splash implements Scene {
    Board b;
    Sketch s;
    UIButton[] UIButtons;

    Sketch.Sprite left, right;

    String studio = "Chancho.dev";
    int fade_opacity = -300, speed = 2, stringWidth, startHeight, delta;
    Dimension resolution;

    public Splash(Board b){
        this.b=b;
        this.s=b.sketch;
        resolution = b.watchdog.getResolution();
        startHeight = resolution.height;
        left = s.getSprite("splashLeft");
        right = s.getSprite("splashRight");
        UIButtons = new UIButton[0];
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(s.textColor);
        g.drawString(studio,resolution.width/2 - stringWidth/2, resolution.height/2 - delta);
        g.setColor(new Color(255,255,255,(delta<300)?50-delta/6:0));
        g.fillOval( //Shadow
                resolution.width/2 - (stringWidth - (delta*2))/2,
                resolution.height/2 - s.fontMetrics.getHeight()/8,
                stringWidth - (delta*2),
                s.fontMetrics.getHeight()/2
        );
        if(delta == 0) {
            g.drawImage(left.getFrame(), resolution.width / 2 - s.fontMetrics.stringWidth(studio) / 2 - left.getFrame().getWidth(), resolution.height / 2 - left.getFrame().getHeight() / 2, null);
            g.drawImage(right.getFrame(), resolution.width / 2 + s.fontMetrics.stringWidth(studio) / 2 + right.getFrame().getWidth() - right.getFrame().getWidth(), resolution.height / 2 - right.getFrame().getHeight() / 2, null);
        }
//        g.setColor(new Color(0,0,0, Math.max(fade_opacity,0)));
//        g.fillRect(0,0,resolution.width,resolution.height);

    }

    @Override
    public void tick() {
        delta = Math.max(startHeight - (b.ticks * speed), 0);
        if(s.fontMetrics != null) stringWidth = s.fontMetrics.stringWidth(studio);
        if(delta == 0) {
            left.play();
            right.play();
        }
        if(left.currentFrame == left.frameCount-1 && fade_opacity<255)
            fade_opacity++;
        if(fade_opacity>=255)
            b.setActiveScene(1);
    }

    @Override
    public UIButton[] getButtons() {
        return UIButtons;
    }

    @Override
    public void onSceneActive() {

    }
}