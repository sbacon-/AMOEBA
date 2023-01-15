package dev.chancho.amoeba.ui;

import java.awt.*;

public class UIButton {
    /*
    Basic template for creating buttons
    The render details are stored within Rectangle 'rect'
    Passing the mouse position (from Board origin) to determineHover() will update the button state
     */
    public boolean hover = false, click = false;
    public Rectangle rect;
    public String text;
    public STATE state;
    public int x,y,width,height;

    public UIButton(String text, Rectangle rect){
        this.rect = rect;
        this.x = rect.x;
        this.y = rect.y;
        this.width = rect.width;
        this.height = rect.height;
        this.text = text;
        this.state = STATE.DEFAULT;
    }

    public void determineHover(Point mouse, boolean mouseDown, boolean mouseClicked){
        if(mouse==null){
            return;
        }
        hover = mouse.x > rect.x && mouse.x<rect.x+rect.width &&
                mouse.y > rect.y && mouse.y<rect.y+rect.height;
        if(hover){
            state = STATE.HOVER;
            if(mouseDown)state = STATE.CLICKED;
            if(mouseClicked)click=true;
        }
        else state = STATE.DEFAULT;
    }

    public enum STATE{
        DEFAULT, HOVER, CLICKED
    }
}
