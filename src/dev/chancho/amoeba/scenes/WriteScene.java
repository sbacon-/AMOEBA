package dev.chancho.amoeba.scenes;

import dev.chancho.amoeba.Board;
import dev.chancho.amoeba.ui.UIButton;
import dev.chancho.amoeba.utilities.Sketch;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class WriteScene implements Scene{
    Board b;
    Sketch s;
    Mode mode = Mode.NONE;

    int num_buttons = 10;
    UIButton[] buttons;
    UIButton[] main_buttons = new UIButton[num_buttons];
    UIButton[] no_button = new UIButton[0];


    String[] mode_string = {"Macro Recorder","Keystroke Mode","Click Mode","Click & Drag","MouseWheel Mode"};
    String[] hint_string = {
            "Choose a mode","Press any key to Record, click to return",
            "Click to Record, press any key to return",
            "Click and Drag to Record, press any key to return",
            "Scroll to Record, press any key to commit & return"
    };
    String dynamic_string = "";
    int mode_num = 0;
    int wheel_int = 0;

    public WriteScene(Board board) {
        this.b = board;
        this.s = b.sketch;
        String[] button_text = {"Keystroke","Click","Click & Drag","MouseWheel",
                                "Variable","IF Stmt", "For Loop", "Inf Loop",
                                "Save", "Exit"};
        int button_offset = 32,
                button_height = 100, button_width = 400,
                button_x = button_offset, button_y = b.watchdog.getResolution().height/2 - button_height*2 - button_offset*2;
        for(int i=0; i<(num_buttons-2)/2; i++){
            main_buttons[i] = new UIButton(button_text[i],new Rectangle(
                    button_x,button_y,button_width,button_height
            ));
            main_buttons[i+4] = new UIButton(button_text[i+4],new Rectangle(
                    b.watchdog.getResolution().width - button_width - button_x ,button_y,button_width,button_height
            ));
            button_y += button_offset + button_height;
        }
        main_buttons[8] = new UIButton(button_text[8],new Rectangle(
                b.watchdog.getResolution().width/2 - button_offset/2 - button_width, b.watchdog.getResolution().height - button_height - button_offset, button_width,button_height));
        main_buttons[9] = new UIButton(button_text[9],new Rectangle(
                b.watchdog.getResolution().width/2 + button_offset/2, b.watchdog.getResolution().height - button_height - button_offset, button_width,button_height));

        buttons = main_buttons;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(b.sketch.textColor);
        s.setFont(g,s.pcs,32.0f);
        int padding = 32, height = s.fontMetrics.getHeight();
        g.drawString(dynamic_string,b.watchdog.getResolution().width/2 - s.fontMetrics.stringWidth(dynamic_string)/2,b.watchdog.getResolution().height/2-s.fontMetrics.getMaxAscent());
        g.drawString(mode_string[mode_num],b.watchdog.getResolution().width/2 - s.fontMetrics.stringWidth(mode_string[mode_num])/2,height+padding);
        s.setFont(g,s.pcs,28.0f);
        g.drawString(hint_string[mode_num],b.watchdog.getResolution().width/2 - s.fontMetrics.stringWidth(hint_string[mode_num])/2,height*2+padding*2);
    }

    @Override
    public void tick() {
        for(UIButton button : buttons){
            if(button.click){
                if(button.text.equals("Keystroke")){
                    setMode(1);
                }
                if(button.text.equals("Click")){
                    setMode(2);
                }
                if(button.text.equals("Click & Drag")){
                    setMode(3);
                }
                if(button.text.equals("MouseWheel")){
                    setMode(4);
                }
                if(button.text.equals("Back")){
                    setMode(0);
                }
                if(button.text.equals("Exit"))
                    b.setActiveScene(1);
                if(button.text.equals("Save"))
                    b.setActiveScene(1);
            }
        }
    }
    void setMode(int m){
        mode_num = m;
        switch(m){
            case 0:
                mode = Mode.NONE;
                buttons = main_buttons;
                break;
            case 1:
                mode = Mode.KEY;
                buttons = no_button;
                break;
            case 2:
                mode = Mode.CLICK;
                buttons = no_button;
                break;
            case 3:
                mode = Mode.DRAG;
                buttons = no_button;
                break;
            case 4:
                wheel_int = 0;
                mode = Mode.WHEEL;
                buttons = no_button;
                break;
        }
    }


    @Override
    public UIButton[] getButtons() {
        return buttons;
    }

    @Override
    public void onSceneActive() {
        b.jupiter.createTempFile();
    }


    public enum Mode{
        NONE, KEY, CLICK, DRAG, WHEEL
    }

    public void key_down(KeyEvent k){
        if(mode == Mode.KEY) {
            dynamic_string = String.format("k_down %d\n", k.getKeyCode());
            b.jupiter.write(dynamic_string);
        }
        if(mode == Mode.CLICK || mode == Mode.DRAG || mode == Mode.WHEEL) {
            if(mode == Mode.WHEEL)
                b.jupiter.write(dynamic_string);
            setMode(0);
        }
    }
    public void key_up(KeyEvent k){
        if(mode == Mode.KEY) {
            dynamic_string = String.format("k_up %d\n", k.getKeyCode());
            b.jupiter.write(dynamic_string);
        }
    }
    public void mouse_press(MouseEvent e) {
        if(mode == Mode.CLICK || mode == Mode.DRAG){
            dynamic_string = String.format("m_move %d %d\n",e.getXOnScreen(),e.getYOnScreen());
            b.jupiter.write(dynamic_string);
            b.jupiter.write("m_down\n");
            if(mode == Mode.CLICK)
                b.jupiter.write("m_up\n");
        }
        if(mode == Mode.KEY)
            setMode(0);
    }
    public void mouse_release(MouseEvent e) {
        if(mode == Mode.DRAG){
            dynamic_string = String.format("m_move %d %d\n",e.getXOnScreen(),e.getYOnScreen());
            b.jupiter.write(dynamic_string);
            b.jupiter.write("m_up\n");
        }
        if(mode == Mode.KEY)
            setMode(0);
    }
    public void wheel(MouseWheelEvent e) {
        if(mode == Mode.WHEEL){
            wheel_int+=e.getUnitsToScroll();
            dynamic_string = "m_wheel "+wheel_int+"\n";
        }

    }
}