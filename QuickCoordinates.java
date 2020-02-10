package dev.chancho.qc;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
		setBackground(new Color(1.0f,1.0f,1.0f,0.2f));
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
		@Override

		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.setFont(g.getFont().deriveFont(61f));
			int y = 100, x=30;
			g.drawString("Q - PROTO",x,y);y+=100;
			g.drawString("A - ATAB",x,y);y+=100;
			g.drawString("S - TAB",x,y);y+=100;
			g.drawString("D - COPY",x,y);y+=100;
			g.drawString("F - PASTE",x,y);y+=100;
			g.drawString("E - ENTER",x,y);y+=100;	
			g.drawString("ESC - NOTE",x,y);y+=100;
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
				System.out.println("click("+
						e.getXOnScreen()+","+
						e.getYOnScreen()+");");
				atab();
				click();
				atab();
				
				run=!run;
			}
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
				System.out.print("//NOTE:");
				System.out.println(JOptionPane.showInputDialog("//NOTE:"));
			}
			if(e.getKeyCode()==KeyEvent.VK_Q) {
				System.out.println("proto();");
				atab();
				attab();
				atab();
				proto();
				attab();
			}
			if(e.getKeyCode()==KeyEvent.VK_A) {
				System.out.println("atab();");
				attab();
				atab();
				
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				System.out.println("tab();");
				atab();
				tab();
				atab();
			}
			if(e.getKeyCode()==KeyEvent.VK_D) {
				System.out.println("copy();");
				atab();
				copy();
				atab();
			}
			if(e.getKeyCode()==KeyEvent.VK_F) {
				System.out.println("paste();");
				atab();
				paste();
				atab();
			}
			if(e.getKeyCode()==KeyEvent.VK_E) {
				System.out.println("enter();");
				atab();
				enter();
				atab();
			}
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
		public void keyReleased(KeyEvent e) {}
		public void p(int k) {
			r.keyPress(k);
			r.keyRelease(k);
		}
		public void p(int k, int k2) {
			r.keyPress(k);
			r.keyPress(k2);
			r.keyRelease(k);
			r.keyRelease(k2);
		}		
		public void click(int x,int y,int dx,int dy) {
			r.mouseMove(x, y);
			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			r.mouseMove(dx, dy);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
		public void click(int x,int y) {
			r.mouseMove(x, y);
			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}public void click() {
			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
		public void tab() {
			p(KeyEvent.VK_TAB);
		}
		public void enter() {
			p(KeyEvent.VK_ENTER);
		}
		public void atab() {
			r.keyPress(KeyEvent.VK_ALT);
			tab();
			r.delay(120);
			r.keyRelease(KeyEvent.VK_ALT);
		}
		public void attab() {
			r.keyPress(KeyEvent.VK_ALT);
			tab();
			r.delay(120);
			tab();
			r.delay(120);
			r.keyRelease(KeyEvent.VK_ALT);
		}
		public void copy() {
			p(KeyEvent.VK_CONTROL,KeyEvent.VK_C);
		}
		public void paste() {
			p(KeyEvent.VK_CONTROL,KeyEvent.VK_V);
		}
		public void proto() {
			atab();
			copy();
			tab();
			atab();
			paste();
			tab();
		}
		
	}
}
