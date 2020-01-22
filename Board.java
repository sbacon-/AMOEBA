import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Board extends JPanel implements Runnable{
	private static final long serialVersionUID = 216511658890793116L;
	public Thread timer;
	public mListener m = new mListener();
	public Board() {
		setPreferredSize(new Dimension(5000,2000));
		addMouseListener(m);
		setFocusable(true);
		requestFocus();
		setOpaque(true);
		setBackground(new Color(0,0,0,0));
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
