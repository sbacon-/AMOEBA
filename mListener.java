import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
