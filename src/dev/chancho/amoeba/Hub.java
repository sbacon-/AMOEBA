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
        add(new Board(id));
        setResizable(false);
        pack();
        setTitle(id);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        EventQueue.invokeLater(()->{
            Hub f = new Hub();
            f.setVisible(true);
        });
    }
    
}
