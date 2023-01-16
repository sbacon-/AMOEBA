package dev.chancho.amoeba.scenes;

import dev.chancho.amoeba.Board;
import dev.chancho.amoeba.ui.UIButton;

import java.awt.*;

public class WriteScene implements Scene{
    Board b;
    int num_buttons = 10;
    Mode mode = Mode.NONE;
    UIButton[] main_buttons = new UIButton[num_buttons];
    UIButton[] buttons;
    public WriteScene(Board board) {
        this.b = board;
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

    }

    @Override
    public void tick() {
        for(UIButton button : buttons){
            if(button.click){
                if(button.text.equals("Keystroke"))
                    mode = Mode.KEY;
                if(button.text.equals("Click"))
                    mode = Mode.CLICK;
                if(button.text.equals("Click & Drag"))
                    mode = Mode.CLICKDRAG;
                if(button.text.equals("MouseWheel"))
                    mode = Mode.WHEEL;
                if(button.text.equals("Back"))
                    mode = Mode.NONE;
            }
        }
    }


    @Override
    public UIButton[] getButtons() {
        return buttons;
    }

    @Override
    public void onSceneActive() {


    }

    public enum Mode{
        NONE, KEY, CLICK, CLICKDRAG, WHEEL
    }
}
