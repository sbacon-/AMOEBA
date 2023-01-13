package dev.chancho.amoeba.scenes;

import dev.chancho.amoeba.Board;
import dev.chancho.amoeba.utilities.Sketch;
import dev.chancho.amoeba.ui.UIButton;

import java.awt.*;

public class MainMenu implements Scene{
    Board b;
    Sketch s;

    UIButton[] UIButtons;

    int titlePadding = 32;

    public MainMenu(Board b) {
        this.b = b;
        this.s = b.sketch;
        UIButtons = new UIButton[4];
        String[] buttonText = {"Read","Write","Options","Exit"};
        int buttonWidth = 400, buttonHeight = 64, buttonPadding = 32, buttonY = b.watchdog.getResolution().height - buttonHeight;
        for(int i=buttonText.length-1; i>=0; i--){
            buttonY -= buttonHeight + buttonPadding;
            UIButtons[i] = new UIButton(buttonText[i],new Rectangle(buttonPadding, buttonY, buttonWidth, buttonHeight));
        }
    }

    @Override
    public void render(Graphics g) {
        s.setFont(g,s.vt323,72.0f);
        //int titleWidth = s.fontMetrics.stringWidth(b.id);
        int titleHeight = s.fontMetrics.getHeight();
        g.setColor(s.textColor);
        g.drawString(b.id,titlePadding,titlePadding+titleHeight);
        g.drawRect(0,0, 32, 32);
    }

    @Override
    public void tick() {

    }

    public UIButton[] getButtons(){
        return UIButtons;
    }
}
