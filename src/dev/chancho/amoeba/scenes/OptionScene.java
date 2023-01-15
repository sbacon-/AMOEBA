package dev.chancho.amoeba.scenes;

import dev.chancho.amoeba.Board;
import dev.chancho.amoeba.ui.UIButton;

import java.awt.*;

public class OptionScene implements Scene{
    Board b;
    public OptionScene(Board board) {
        this.b = board;
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void tick() {

    }

    @Override
    public UIButton[] getButtons() {
        return new UIButton[0];
    }

    @Override
    public void onSceneActive() {

    }
}
