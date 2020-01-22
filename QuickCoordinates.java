import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class QuickCoordinates extends JFrame{
	private static final long serialVersionUID = -6915346476300185739L;
	public QuickCoordinates() {
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
			QuickCoordinates f = new QuickCoordinates();
			f.setVisible(true);
		});
	}
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
	public class mListener implements MouseListener{
		
		boolean run = false; 
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!run) {
			System.out.println("click(r,"+e.getXOnScreen()+","+e.getYOnScreen()+");");
			run=true;
			try {
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_ALT);
				r.keyPress(KeyEvent.VK_SPACE);
				r.keyPress(KeyEvent.VK_N);
				r.keyRelease(KeyEvent.VK_N);
				r.keyRelease(KeyEvent.VK_ALT);
				r.keyRelease(KeyEvent.VK_SPACE);
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			run=false;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
