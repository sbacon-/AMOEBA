import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {
	public static String 
			type = "GIZMO 2020", 
			area = "", 
			issue = "", 
			desc = "",
			scope = "",
			unitqty = "",
			unit = "",
			disclaimers = "",
			total = "";
	public static Robot r;
	public static Categories cat;
	public static int delayMS = 50;

	public static ArrayList<LineItem> bid = new ArrayList<LineItem>();
	public static void main(String[] args){
		
		
		cat = new Categories();
		
		bootRobot();
		
		splash("Welcome to GIZMO: \n"
				+ "Please Click The Pivot Title and Return Here.\n"
				+ "Press the button to Continue");
		
		readSheet();
		
		for(LineItem li : bid) {
			System.out.println(li.cat+"\t"+li.desc+"\t"+li.quantity+"\t"+li.cost);
		}
		
		splash("The sheet has been read, please now prepare to write to PASKR\n"+
		"GO TO PASKR AND CLICK OK WHEN YOU ARE A BLANK BIDDING WORKSHEET TEMPLATE");
		
		//PLEASE ADD THE FOLLOWING CATEGORIES
		ArrayList<String> nonDefaults = new ArrayList<String>();
		for(LineItem li : bid) {
			if(!nonDefaults.contains(li.cat)&&!cat.defaults.contains(li.cat)) {
				nonDefaults.add(li.cat);
			}
		}
		System.out.println(nonDefaults.size());
		StringBuilder sb = new StringBuilder();
		sb.append("PLEASE ADD THE FOLLOWING CATEGORIES:\n");
		for(String s : nonDefaults) {
			sb.append(s+"\n");
		}
		splash(sb.toString());
		
		splash("READY TO WRITE");
		
		while(bid.size()!=0) {
			writeBid();
		}
	}
	
	private static void bootRobot() {
		try {
			r = new Robot();
			r.setAutoDelay(delayMS);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static String getClipboard() {
		try {
			return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		} catch (HeadlessException | UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
			return "";
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
	public void click(int x,int y,int dx,int dy) {
		r.mouseMove(x, y);
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseMove(dx, dy);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	public static void click(int x,int y) {
		r.mouseMove(x, y);
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}public void click() {
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	public static void tab() {
		p(KeyEvent.VK_TAB);
	}
	public static void enter() {
		p(KeyEvent.VK_ENTER);
	}
	public static void down() {
		p(KeyEvent.VK_DOWN);
	}
	public static void returnLeft() {
		for(int i = 0; i<=10; i++) {
			p(KeyEvent.VK_LEFT);
		}
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
	public static void copy() {
		p(KeyEvent.VK_CONTROL,KeyEvent.VK_C);
	}
	public void paste() {
		p(KeyEvent.VK_CONTROL,KeyEvent.VK_V);
	}
	public static void paste(String s) {
		StringSelection str = new StringSelection(s);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		p(KeyEvent.VK_CONTROL,KeyEvent.VK_V);
	}
	public static void splash(String text) {
		JOptionPane.showConfirmDialog(null,text, type + " - " + area ,JOptionPane.DEFAULT_OPTION);
		
	}
	
	public static String determineCategory(){
		String broad = (String)JOptionPane.showInputDialog(null,
				"Please Select a category for:\n"+
						type +" - " + area + " - "+ issue +" \n"+
						desc,type,JOptionPane.QUESTION_MESSAGE,null,
						cat.bible.toArray(),cat.bible.get(0));
		
		ArrayList<String> book = new ArrayList<String>();
		for(String s : cat.all) {
			if(s.substring(0, 2).equalsIgnoreCase(broad.substring(0,2)))book.add(s);
		}			
		
		String specific = (String)JOptionPane.showInputDialog(null,
						"Please Select a category for:\n"+
								type +" - " + area + " - "+ issue +" \n"+
								desc,type,JOptionPane.QUESTION_MESSAGE,null,
								book.toArray(),cat.bible.get(0));
			
		return specific;
		
	}
	public static String readCell() {
		copy();
		tab();
		return getClipboard();
		
	}
	
	public static void readLine() {
		LineItem line = null;
		
		String catLI="", descLI="";
		float quantityLI=0, costLI=0;
		
		type = readCell();
		area = readCell();
		issue = readCell();
		desc = readCell();
		scope = readCell();
		unitqty = readCell();
		unit = readCell();
		disclaimers = readCell();
		total = readCell();
		
		String descVerbose = desc;
		for(int i=45;i<desc.length();i+=45) {
			descVerbose = descVerbose.substring(0,i)+"\n"+descVerbose.substring(i);
		}
		
		
		if(confirm(
				"Is this item on the bid?\n"
				+descVerbose)){
			catLI = determineCategory();
			if(confirm("Would you like to edit text for \n"+descVerbose)) {
				desc = JOptionPane.showInputDialog(null,"",desc);
			}
			descLI = desc;
			
			if(confirm("Split item quantity?\n"+unitqty+" "+unit)) {
				quantityLI = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter QTY","1"));
			}else{
				quantityLI = 1;
			};
			
			total = total.replaceAll(",", "");
			costLI = Float.parseFloat(total.substring(1))/quantityLI;
			if(confirm("CONFIRM LINE ITEM \n"+catLI+"\n"+descLI+"\n"+quantityLI+"\n"+costLI)){
				line = new LineItem(catLI,descLI,quantityLI,costLI);
				bid.add(line);
				
			}else{
				returnLeft();
				return;
			};
		}
		returnLeft();
		down();
		
	}
	
	public static boolean confirm(String s) {
		int onBid = JOptionPane.showConfirmDialog(null,
				s,
				"GIZMO 2020",
				JOptionPane.YES_NO_OPTION);
		return onBid==JOptionPane.YES_OPTION;
	}
	
	
	public static void readSheet() {
		r.delay(200);
		down();
		down();
		down();
		
		while(!type.contains("Grand Total")){
			System.out.println(type);
			readLine();
		};
	}
	
	public static void writeBid() {
		int loadTime = 3000;
		ArrayList<Integer> bidIndexToWrite = new ArrayList<Integer>();
		for(LineItem li : bid) {
			if(li.cat.equals(bid.get(0).cat));
			bidIndexToWrite.add(bid.indexOf(li));
		}
		r.delay(loadTime);
		click(702,300); //Click Cost Code
		r.delay(loadTime);
		paste(bid.get(0).cat);
		r.delay(loadTime);
		enter();
		r.delay(loadTime);
		click(462,311); // EXPAND ITEM
		for(Integer index : bidIndexToWrite) {
			LineItem li = bid.get(index);
			splash("NEW LINE & DESCRIPTION\n"+li.cat);
			paste(li.desc);
			r.delay(loadTime);
			tab();
			r.delay(loadTime);
			tab();
			r.delay(loadTime);
			paste(String.valueOf(li.quantity));
			tab();
			r.delay(loadTime*2);
			paste(String.valueOf(li.cost==0?0.01:li.cost));
			tab();			
			splash("Wrote "+ li.cat+"\t"+li.desc+"\t"+li.quantity+"\t"+li.cost);
		}
		int deltaIndex = 0;
		for(Integer index : bidIndexToWrite) {
			bid.remove(index-deltaIndex++);
		}
	}
}
