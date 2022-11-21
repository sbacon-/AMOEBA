package dev.chancho.tom;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/************************************************************************
 * -----------------------------------------
 * AMOEBA Means Optimized Entry By Automata
 * -----------------------------------------
 * This is a program meant to automate key presses by Robot assignment
 * Originally created for Data Entry from Excel to WebForms
 * The shortcut keys are defined below the main class
 * This code is open source and available for anyone to use
 * Written Jan. 2019 || Last Revision Nov. 2022 
 * 
 *  - tom@chancho.dev - 
 * 
 * ***********************************************************************/

public class Tom{
	public static Robot r;
	public static void main(String[] args) throws AWTException, InterruptedException {
		r = new Robot();
		r.setAutoDelay(600);
		int load = 4000;
		atab();
		/*
		r.delay(load*2);
		r.mouseWheel(5);
		*/
		
	}
	
	//Press a key
	//p(KeyEvent.VK_BACK_SPACE);
	public static void p(int k) {
		r.keyPress(k);
		r.keyRelease(k);
	}

	//Press two keys
	//p(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
	public static void p(int k, int k2) {
		r.keyPress(k);
		r.keyPress(k2);
		
		r.keyRelease(k);
		r.keyRelease(k2);
	}
	
	//Mouse Click at current position
	public static void click() {
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	//Mouse Click at given coordinates
	//click(70,426);
	public static void click(int x,int y) {
		r.mouseMove(x, y);
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	//Mouse Click at given coordinates and Drag at second given coordinates
	//click(650,433,588,430);
	public static void click(int x,int y,int dx,int dy) {
		r.mouseMove(x, y);
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseMove(dx, dy);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}	
	
	//Macro for copy pasting data from Excel
	public static void proto() {
		atab();
		copy();
		tab();
		atab();
		paste();
		tab();
	}
	
	//SHORTCUTS
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
	
	//Set Clipboard to given string and paste the output
	public static void output(String s) {
		r.delay(500);
		StringSelection selection = new StringSelection(s);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		paste();
	}
	
}
