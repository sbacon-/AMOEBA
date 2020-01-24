package dev.chancho.qc;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
		setTitle("QuickCoordinates");
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
	public class Board extends JPanel{
		private static final long serialVersionUID = 216511658890793116L;
		public mListener m = new mListener();
		public Board() {
			setPreferredSize(new Dimension(1920,1080));
			addMouseListener(m);
			addKeyListener(m);
			setFocusable(true);
			requestFocus();
			setOpaque(true);
			setBackground(new Color(0,0,0,0));
			
		}
	}
	public class mListener implements MouseListener, KeyListener{
		public Robot r;
		public boolean run = true; 
		public mListener() {
			try {
				r = new Robot();
				r.setAutoDelay(60);
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			if(run) {
				run=!run;
				System.out.println("click(r,"+
						e.getXOnScreen()+","+
						e.getYOnScreen()+");");
				atab(r);
				click(r);
				atab(r);
				
				run=!run;
			}
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE)run=!run;
		}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {
			if(run) {
				run=!run;
				System.out.println("p(r,"+
						e.getKeyCode()+");");
				atab(r);
				p(r,e.getKeyCode());
				atab(r);
				
				run=!run;
			}
		}
		public void p(Robot r, int k) {
			r.keyPress(k);
			r.keyRelease(k);
		}
		public void p(Robot r, int k, int k2) {
			r.keyPress(k);
			r.keyPress(k2);
			r.keyRelease(k);
			r.keyRelease(k2);
		}		
		public void click(Robot r,int x,int y,int dx,int dy) {
			r.mouseMove(x, y);
			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			r.mouseMove(dx, dy);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
		public void click(Robot r,int x,int y) {
			r.mouseMove(x, y);
			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}public void click(Robot r) {
			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
		public void tab(Robot r) {
			p(r,KeyEvent.VK_TAB);
		}
		public void enter(Robot r) {
			p(r,KeyEvent.VK_ENTER);
		}
		public void atab(Robot r) {
			r.keyPress(KeyEvent.VK_ALT);
			tab(r);
			r.keyRelease(KeyEvent.VK_ALT);
		}
		public void copy(Robot r) {
			p(r,KeyEvent.VK_CONTROL,KeyEvent.VK_C);
		}
		public void paste(Robot r) {
			p(r,KeyEvent.VK_CONTROL,KeyEvent.VK_V);
		}
		
	}
}
