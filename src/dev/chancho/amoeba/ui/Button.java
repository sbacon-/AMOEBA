package dev.chancho.amoeba.ui;

import java.awt.*;

public class Button {
    /*
    Basic template for creating buttons
    The render details are stored within Rectangle 'rect'
    Passing the mouse position (from Board origin) to determineHover() will indicate the mouse is over the button
     */
    public boolean hover = false, click = false;
    Rectangle rect;
    String text;
    int id;

    public Button(Rectangle rect, String text, int id){
        this.rect = rect;
        this.text = text;
        this.id = id;
    }

    public boolean determineHover(Point mouse){
        if(mouse==null) return false;
        hover = mouse.x > rect.x && mouse.x<rect.x+rect.width &&
                mouse.y > rect.y && mouse.y<rect.y+rect.height;
        return hover;
    }
}
