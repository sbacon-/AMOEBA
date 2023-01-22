package dev.chancho.amoeba.scenes;

import dev.chancho.amoeba.Board;
import dev.chancho.amoeba.ui.UIButton;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class ReadScene implements Scene{
    Board b;
    Robot robot;
    BufferedReader bufferedReader;
    InputStreamReader inputStreamReader;
    String[] files;
    UIButton[] buttons;

    public ReadScene(Board board) {
        this.b = board;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(Graphics2D g) {

    }

    @Override
    public void tick() {
        for(UIButton button: buttons){
            if(button.click){
                if(button.text.equals("CANCEL"))
                    b.setActiveScene(1);
                else{
                    try {
                        b.jupiter.setReadTarget(b.jupiter.working_directory+button.text);
                        inputStreamReader = new InputStreamReader(b.jupiter.getInputStream());
                        bufferedReader = new BufferedReader(inputStreamReader);
                        String line = bufferedReader.readLine();
                        robot.setAutoDelay(50);
                        while(line != null){
                            System.out.println(line);
                            parseLine(line);
                            line = bufferedReader.readLine();
                        }
                    } catch (IOException e) {
                        System.out.printf("Cannot Open File for Reading: %s\n",button.text);
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }

    @Override
    public UIButton[] getButtons() {
        return buttons;
    }

    @Override
    public void onSceneActive() {
        b.jupiter.deleteTempFile();
        files = b.jupiter.getMacroList();
        buttons = new UIButton[files.length+1];
        int padding = 32, x = padding, y = padding ,width=400, height=100;
        for(int i = 0; i< files.length; i++){
            buttons[i] = new UIButton(files[i],new Rectangle(
                    x,y,width,height
            ));
            y+=height+padding;
            if(i%5 == 0){
                x+=width + padding;
                y=padding;
            }
        }
        buttons[files.length] = new UIButton("CANCEL",new Rectangle(
                x,y,width,height
        ));
    }

    void parseLine(String s){
        String func = s.substring(s.indexOf('(')+1, s.indexOf(')'));
        int num = 0, num2 = 0;
        if(func.contains(",")) {
            num = Integer.parseInt(func.substring(0,func.indexOf(',')));
            num2 = Integer.parseInt(func.substring(func.indexOf(',')+1));
        }else{
            num = Integer.parseInt(func);
        }
        if(s.startsWith("k_down"))
            robot.keyPress(num);
        if(s.startsWith("k_up"))
            robot.keyRelease(num);
        if(s.startsWith("m_move"))
            robot.mouseMove(num,num2);
        if(s.startsWith("m_down"))
            robot.mousePress(InputEvent.getMaskForButton(num));
        if(s.startsWith("m_up"))
            robot.mouseRelease(InputEvent.getMaskForButton(num));
        if(s.startsWith("delay"))
            robot.delay(num);
    }
}
