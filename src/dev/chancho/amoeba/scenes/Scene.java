package dev.chancho.amoeba.scenes;

import dev.chancho.amoeba.ui.UIButton;
import java.awt.*;
public interface Scene {
    void render(Graphics g);
    void tick();
    UIButton[] getButtons();

    void onSceneActive();
}