import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Tom {
	public static void main(String[] args) throws AWTException, InterruptedException {
		Robot r = new Robot();
		r.setAutoDelay(1500);
		//Thread.sleep(load);
		int load = 4000;
		for(int i=0; i<1; i++) {
			click(r,2714,430);
			Thread.sleep(load);
			click(r,3148,401);
			click(r,4489,693,4492,828);
			click(r,3394,674);
			p(r,KeyEvent.VK_N);
			enter(r);
			click(r,4530,650);
			click(r,3103,1003);
			Thread.sleep(load);
		}
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
