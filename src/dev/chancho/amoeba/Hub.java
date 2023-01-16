package dev.chancho.amoeba;
import java.awt.*;
import javax.swing.JFrame;

public class Hub extends JFrame{
    public Hub(){
        String name = "Amoeba";
        String version = "23.1.10";
        initUI(name+"-"+version);
    }
    private void initUI(String id) {
        setUndecorated(true);
        Board b = new Board(id,this);
        add(b);
        setResizable(false);
        pack();
        setTitle(id);
        setLocationRelativeTo(null);
        setBackground(new Color(0,0,0, 0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        System.out.println("ARGS: "+args.length);
        EventQueue.invokeLater(()->{
            Hub jFrame = new Hub();
            jFrame.setVisible(true);
        });
    }
}
