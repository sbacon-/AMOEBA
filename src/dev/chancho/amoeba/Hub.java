package dev.chancho.amoeba;
import java.awt.*;
import javax.swing.JFrame;

public class Hub extends JFrame{
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice gd = ge.getDefaultScreenDevice();
    public boolean transparency_supported;
    public String workingDirectory;
    public Hub(){
        String name = "Amoeba";
        String version = "23.1.10";
        transparency_supported = gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT);
        init(name+"-"+version);
    }
    private void init(String id) {
        //OBTAIN WORKING DIRECTORY FOR STORING FILES
        String os = System.getProperty("os.name").toUpperCase();
        if(os.contains("WIN"))
            workingDirectory = System.getenv("AppData");
        else
            workingDirectory = System.getProperty("user.home");

        //USER INTERFACE
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        Board b = new Board(id,this);
        add(b);
        setResizable(false);
        pack();
        setTitle(id);
        setLocationRelativeTo(null);
        if(transparency_supported){
            setOpacity(0.5f);
            setBackground(new Color(0,0,0,0));
        }
        else{
            setBackground(Color.BLACK);
        }
        System.out.printf("Transparency: %s\n",transparency_supported);
    }
    public static void main(String[] args){
        System.out.println("ARGS: "+args.length);
        EventQueue.invokeLater(()->{
            Hub jFrame = new Hub();
            jFrame.setVisible(true);
        });
    }
}
