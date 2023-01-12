package dev.chancho.amoeba.ui;

import java.awt.*;

public class UIButton {
    /*
    Basic template for creating buttons
    The render details are stored within Rectangle 'rect'
    Passing the mouse position (from Board origin) to determineHover() will indicate the mouse is over the button
     */
    public boolean hover = false, click = false;
    public Rectangle rect;
    String text;

    public UIButton(String text, Rectangle rect){
        this.rect = rect;
        this.text = text;
    }

    public boolean determineHover(Point mouse){
        if(mouse==null) return false;
        hover = mouse.x > rect.x && mouse.x<rect.x+rect.width &&
                mouse.y > rect.y && mouse.y<rect.y+rect.height;
        return hover;
    }
}
