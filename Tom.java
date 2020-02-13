package dev.chancho.tom;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Tom {
	public static Robot r;
	public static void main(String[] args) throws AWTException, InterruptedException {
		
		
		//*** UNICELL BLOCK ***//
		HashSet<String> models = new HashSet<String>();
		HashSet<String> pileX = new HashSet<String>();
		ArrayList<String> images = new ArrayList<String>();
		String[] tag = {"_Silo.jpg","_Swatch.jpg","_AltCorner.jpg","_AltBack.jpg","_Pile.jpg","_Style1.jpg"};
		String dir = "D://Mohawk";
		File path = new File(dir);
		for(String f : path.list()){
			String fNew = f.substring(0, f.indexOf("_"));
			models.add(fNew);
			images.add(f);
		}
		for(String m: models) {
			for(int t=0;t<5;t++) {
				if(images.indexOf(m+tag[t])==-1 && images.indexOf(m+tag[t+1])!=-1) {
					pileX.add(m);
				}
			}
		}
		//////////////////////////
				
		r = new Robot();
		r.setAutoDelay(600);
		int load = 4000;
		atab();
		/*
		attab();
		atab();
		*/
		for(String m: models) {

			click(289,1023);
			r.delay(load*2);
			click(103,214);
			r.delay(load*2);
			p(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
			p(KeyEvent.VK_BACK_SPACE);
			output(m);
			click(70,426);
			r.delay(load*2);
		
			
			
			click(380,200);
			click(337,304);
			click(937,212);
			r.delay(load*2);
			

			click(650,433,588,430);
			copy();
			try {
				if(images.contains(Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor)+".jpg"))continue;
			} catch (HeadlessException | UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}

			
			
			//SILO
			click(914,383);
			click(870,685);
			click(1039,583);
			output(m+tag[0]);
			enter();
			click(990,633);
			r.delay(load);
			click(955,730);
			

			//SWATCH
			if(images.indexOf(m+tag[1])==-1)continue;
			click(913,911);
			click(870,685);
			click(1039,583);
			output(m+tag[1]);
			enter();
			click(994,624);
			r.delay(load);
			click(963,728);
			
			

			//CORNER
			if(images.indexOf(m+tag[2])==-1)continue;
			click(914,781);
			click(863,685);
			click(1023,584);
			output(m+tag[2]);
			enter();
			click(1014,638);
			r.delay(load);
			click(955,731);
			
			

			//BACK
			if(images.indexOf(m+tag[3])==-1)continue;
			click(913,741);
			click(870,685);
			click(1039,583);
			output(m+tag[3]);
			enter();
			click(1006,626);
			r.delay(load);
			click(971,728);
			r.mouseWheel(5);
			
			

			//PILE
			if(images.indexOf(m+tag[4])==-1 && !pileX.contains(m))continue;
			if(!pileX.contains(m)) {
				click(915,598);
				click(870,685);
				click(1039,583);
				output(m+tag[4]);
				enter();
				click(995,632);
				r.delay(load);
				click(955,730);
				
				//SYTLE
				
				if(images.indexOf(m+tag[5])==-1)continue;
				click(914,701);
			}
			
			if(pileX.contains(m)) click(912,641);
			click(870,685);
			click(1039,583);
			output(m+tag[5]);
			enter();
			click(992,630);
			r.delay(load);
			click(961,724);			
			
			r.delay(load*3);
		}
		
	}
	
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
	
	public static void output(String s) {
		r.delay(500);
		StringSelection selection = new StringSelection(s);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		paste();
	}
}
