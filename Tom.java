package dev.chancho.tom;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

public class Tom {
	public static void main(String[] args) throws AWTException, InterruptedException {
		Robot r = new Robot();
		r.setAutoDelay(120);
		int load = 4000;
		atab(r);
		attab(r);
		atab(r);
		/*
		for(int i=0; i<1; i++) {
			JOptionPane.showMessageDialog(null, "CONTINUE???");
			click(r,990,287);
			proto(r);
			proto(r);
			proto(r);
			click(r,570,521);
			proto(r);
			proto(r);
			proto(r);
			proto(r);
			p(r,KeyEvent.VK_SPACE);
			tab(r);
			tab(r);
			p(r,KeyEvent.VK_SPACE);
			tab(r);
			proto(r);
			proto(r);	
			tab(r);
			//JOptionPane.showMessageDialog(null, "CONTINUE???");
			r.delay(100);
			enter(r);
			r.delay(load);
			///TAB 2
			proto(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			proto(r);
			proto(r);
			proto(r);
			proto(r);

			p(r,KeyEvent.VK_SPACE);
			r.keyPress(KeyEvent.VK_SHIFT);
			for(int t=0;t<6;t++) {
				tab(r);
			}
			r.keyRelease(KeyEvent.VK_SHIFT);
			proto(r);
			proto(r);
			proto(r);
			proto(r);
			proto(r);
			proto(r);
			tab(r);
			tab(r);
			//JOptionPane.showMessageDialog(null, "CONTINUE???");
			r.delay(100);
			enter(r);
			r.delay(load);
			//TAB 3
			proto(r);
			proto(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			proto(r);
			proto(r);
			proto(r);
			proto(r);
			proto(r);
			p(r,KeyEvent.VK_SPACE);
			r.delay(load/2);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			proto(r);
			proto(r);
			proto(r);
			proto(r);
			proto(r);
			proto(r);
			click(r,1195,1030);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			tab(r);
			//JOptionPane.showMessageDialog(null, "CONTINUE???");
			r.delay(100);
			enter(r);
			r.delay(load);
			//TAB 4

			r.keyPress(KeyEvent.VK_SHIFT);
			for(int t=0;t<7;t++) {
				tab(r);
			}
			r.keyRelease(KeyEvent.VK_SHIFT);
			tab(r);
			p(r,KeyEvent.VK_SPACE);
			tab(r);
			p(r,KeyEvent.VK_SPACE);
			tab(r);
			proto(r);
			p(r,KeyEvent.VK_SPACE);
			tab(r);
			p(r,KeyEvent.VK_SPACE);
			r.delay(load/4);
			p(r,KeyEvent.VK_W);
			p(r,KeyEvent.VK_A);
			p(r,KeyEvent.VK_R);
			p(r,KeyEvent.VK_ENTER);
			click(r,1362,719);
			tab(r);
			tab(r);
			tab(r);
			//JOptionPane.showMessageDialog(null, "CONTINUE???");
			r.delay(100);
			enter(r);
			r.delay(load);
			//TAB 5
		}
		*/
	}
	
	
	public static void p(Robot r, int k) {
		r.keyPress(k);
		r.keyRelease(k);
	}
	public static void p(Robot r, int k, int k2) {
		r.keyPress(k);
		r.keyPress(k2);
		r.keyRelease(k);
		r.keyRelease(k2);
	}
	
	
	public static void click(Robot r,int x,int y,int dx,int dy) {
		r.mouseMove(x, y);
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseMove(dx, dy);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	public static void click(Robot r,int x,int y) {
		r.mouseMove(x, y);
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}public static void click(Robot r) {
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	public static void proto(Robot r) {
		atab(r);
		copy(r);
		tab(r);
		atab(r);
		paste(r);
		tab(r);
	}
	
	public static void tab(Robot r) {
		p(r,KeyEvent.VK_TAB);
	}
	public static void enter(Robot r) {
		p(r,KeyEvent.VK_ENTER);
	}
	public static void atab(Robot r) {
		r.keyPress(KeyEvent.VK_ALT);
		tab(r);
		r.keyRelease(KeyEvent.VK_ALT);
	}
	public static void attab(Robot r) {
		r.keyPress(KeyEvent.VK_ALT);
		tab(r);
		r.delay(100);
		tab(r);
		r.keyRelease(KeyEvent.VK_ALT);
	}
	public static void copy(Robot r) {
		//EXCEL
		//p(r,KeyEvent.VK_Z);
		//p(r,KeyEvent.VK_CONTROL,KeyEvent.VK_Z);
		
		p(r,KeyEvent.VK_CONTROL,KeyEvent.VK_C);
	}
	public static void paste(Robot r) {
		p(r,KeyEvent.VK_CONTROL,KeyEvent.VK_V);
	}
}

