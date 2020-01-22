import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Frame extends JFrame{
	private static final long serialVersionUID = -6915346476300185739L;
	public Frame() {
		initUI();
	}
	private void initUI() {
		setUndecorated(true);
		add(new Board());
		setResizable(false);
		pack();
		setTitle("ClickCoordinates");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(1.0f,1.0f,1.0f,0.5f));
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			Frame f = new Frame();
			f.setVisible(true);
		});
	}

}
