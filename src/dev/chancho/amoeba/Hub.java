package dev.chancho.amoeba;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Hub extends JFrame{
    public Hub(){
        String name = "Amoeba";
        String version = "23.1.10";
        initUI(name+"-"+version);
    }
    private void initUI(String id) {
        setUndecorated(true);
        setResizable(false);
        Board b = new Board(id,this);
        add(b);
        pack();
        setTitle(id);
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
