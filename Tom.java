package dev.chancho.tom;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Tom {
	public static Robot r;
	public static void main(String[] args) throws AWTException, InterruptedException {
		r = new Robot();
		r.setAutoDelay(120);
		//int load = 4000;
		atab();
		attab();
		atab();
		for(int i=0; i<1; i++) {r.setAutoDelay(500);
		}}
	
	
	public static void p(int k) {
		r.keyPress(k);
		r.keyRelease(k);
	}
	public static void p(int k, int k2) {
		r.keyPress(k);
		r.keyPress(k2);
		r.keyRelease(k);
		r.keyRelease(k2);
	}
	
	
	public static void click(int x,int y,int dx,int dy) {
		r.mouseMove(x, y);
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseMove(dx, dy);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	public static void click(int x,int y) {
		r.mouseMove(x, y);
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}public static void click() {
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	public static void proto() {
		atab();
		copy();
		tab();
		atab();
		paste();
		tab();
	}
	
	public static void tab() {
		p(KeyEvent.VK_TAB);
	}
	public static void enter() {
		p(KeyEvent.VK_ENTER);
	}
	public static void atab() {
		r.keyPress(KeyEvent.VK_ALT);
		tab();
		r.keyRelease(KeyEvent.VK_ALT);
	}
	public static void attab() {
		r.keyPress(KeyEvent.VK_ALT);
		tab();
		r.delay(100);
		tab();
		r.keyRelease(KeyEvent.VK_ALT);
	}
	public static void copy() {
		//EXCEL
		//p(KeyEvent.VK_Z);
		//p(KeyEvent.VK_CONTROL,KeyEvent.VK_Z);
		
		p(KeyEvent.VK_CONTROL,KeyEvent.VK_C);
	}
	public static void paste() {
		p(KeyEvent.VK_CONTROL,KeyEvent.VK_V);
	}
}
