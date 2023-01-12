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

    int fade_opacity = -300;

    public Splash(Board b){
        this.b=b;
        this.s=b.sketch;
        left = s.getSprite("splashLeft");
        right = s.getSprite("splashRight");
        UIButtons = new UIButton[0];
    }

    @Override
    public void render(Graphics g) {
        String studio = "Chancho.dev";
        Dimension resolution = b.watchdog.getResolution();
        int stringWidth = s.fontMetrics.stringWidth(studio);
        int speed = 2;
        int startHeight = resolution.height;
        int delta = Math.max(startHeight - (b.ticks * speed), 0);
        g.setColor(s.textColor);
        g.drawString(studio,resolution.width/2 - stringWidth/2, resolution.height/2 - delta);
        g.setColor(new Color(255,255,255,(delta<300)?50-delta/6:0));
        g.fillOval( //Shadow
                resolution.width/2 - stringWidth/2,
                resolution.height/2 - s.fontMetrics.getHeight()/8,
                stringWidth,
                s.fontMetrics.getHeight()/2
        );
        if(delta == 0) {
            g.drawImage(left.getFrame(), resolution.width / 2 - s.fontMetrics.stringWidth(studio) / 2 - left.getFrame().getWidth(), resolution.height / 2 - left.getFrame().getHeight() / 2, null);
            g.drawImage(right.getFrame(), resolution.width / 2 + s.fontMetrics.stringWidth(studio) / 2 + right.getFrame().getWidth() - right.getFrame().getWidth(), resolution.height / 2 - right.getFrame().getHeight() / 2, null);
            left.play();
            right.play();
        }
        if(left.currentFrame == left.frameCount-1 && fade_opacity<255){
           fade_opacity++;
        }
        g.setColor(new Color(0,0,0, Math.max(fade_opacity,0)));
        g.fillRect(0,0,resolution.width,resolution.height);

        if(fade_opacity>=255){
            b.activeScene = 1;
        }
    }

    @Override
    public UIButton[] getButtons() {
        return UIButtons;
    }
}