package viruses;

import java.awt.*;
//import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class VirusFrame extends JFrame implements KeyListener, MouseListener {
	
	/**A list of key code characters denoting the debug escape sequence to stop a running Viruses method
	 * 
	 */
	private static int[] escapeSeq = {46, 46, 46, 46, 46};
	
	/**Variable tracking the current position in typing a debug sequence
	 * 
	 */
	private static int seqLoc = 0;
	
	/**Variable tracking when the escape sequence is fully input
	 * 
	 */
	private static boolean escapeCompleted = false;
	
	/**Variable tracking if the mouse is over this window
	 * 
	 */
	private boolean mouseOver = true;
	
	
	/**Constructs a window with no buttons and no borders automatically above all other windows of color c the size of sqSize
	 * by sqSize pixels. The window records escape sequence input and tracks if the mouse is over it at all times.
	 * 
	 * @param c The color of the window
	 * @param sqSize The side length (in pixels) of the window in the x and y directions
	 */
	public VirusFrame(Color c, int sqSize) {
		setLayout(null);
		addKeyListener(this);
		addMouseListener(this);
		getContentPane().setBackground(c);
		setAlwaysOnTop(true);
		setUndecorated(true);
		setDefaultCloseOperation(0);
		setSize(sqSize, sqSize);
		setVisible(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	/**Constructs a window with no buttons and no borders automatically above all other windows of color c the size of xSize
	 * by ySize pixels. The window records escape sequence input and tracks if the mouse is over it at all times.
	 * 
	 * @param c The color of the window
	 * @param xSize The side length in the x direction (in pixels) of the window
	 * @param ySize The side length in the y direction (in pixels) of the window
	 */
	public VirusFrame(Color c, int xSize, int ySize) {
		setLayout(null);
		addKeyListener(this);
		addMouseListener(this);
		getContentPane().setBackground(c);;
		setAlwaysOnTop(true);
		setUndecorated(true);
		setDefaultCloseOperation(0);
		setSize(xSize, ySize);
		setVisible(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	
	/**Returns the mouseOver variable for this frame
	 * 
	 * @return The mouseOver attribute
	 */
	public boolean getMouseOver() {
		return mouseOver;
	}
	
	/**Returns the escapeCompleted variable for this class
	 * 
	 * @return the escapeCompleted static attribute
	 */
	public static boolean getEscapeCompleted() {
		return escapeCompleted;
	}
	
	/**Randomizes the color of this VirusFrame object. The 10 possible colors to be chosen are: black, blue, cyan, green,
	 * magenta, orange, pink, red, yellow, and white. These colors are specified in the Color class.
	 * 
	 */
	public void randomizeColor() {
		int colorNum = (int)(Math.random()*10);
		switch(colorNum) {
		case 0:
			getContentPane().setBackground(Color.black);
			break;
		case 1:
			getContentPane().setBackground(Color.blue);
			break;
		case 2:
			getContentPane().setBackground(Color.cyan);
			break;
		case 3:
			getContentPane().setBackground(Color.green);
			break;
		case 4:
			getContentPane().setBackground(Color.magenta);
			break;
		case 5:
			getContentPane().setBackground(Color.orange);
			break;
		case 6:
			getContentPane().setBackground(Color.pink);
			break;
		case 7:
			getContentPane().setBackground(Color.red);
			break;
		case 8:
			getContentPane().setBackground(Color.yellow);
			break;
		case 9:
			getContentPane().setBackground(Color.white);
			break;
		}
	}
	
	/**Deletes this VirusFrame object by setting visible to false and disposing of the memory for the object
	 * 
	 */
	public void deleteThis() {
		setVisible(false);
		dispose();
	}
	
	
	
	/**Unused method implemented from KeyListener
	 * 
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	/**Called on the VirusFrame object that is focused when a key on the keyboard is pressed
	 * 
	 * @param e The KeyEvent that occurred
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		//print the keyCode of the event for debug purposes
		System.out.println(e.getKeyCode());
		
		//conditional to check if the key pressed is the next in the escape sequence
		if(e.getKeyCode()==escapeSeq[seqLoc]) {
			
			//move onto the next key in the sequence
			seqLoc++;
			
		} else {
			
			//reset sequence progress
			seqLoc = 0;
			
		} //end conditional
		
		//conditional to check if sequence is complete
		if(seqLoc==escapeSeq.length) {
			
			//set escapeCompleted to true
			escapeCompleted = true;
			
		} //end conditional
	} //end method
	
	/**Unused method implemented from KeyListener
	 * 
	 */
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	
	/**Unused method implemented from MouseListener
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	/**Unused method implemented from MouseListener
	 * 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
	}
	
	/**Unused method implemented from MouseListener
	 * 
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	/**Called on the VirusFrame object that the mouse has just entered the bounds of
	 * 
	 * @param e The MouseEvent that occured
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		
		//print debug info
		System.out.println("mouse enter");
		
		//set mouseOver to true
		mouseOver = true;
		
	} //end method
	
	/**Called on the VirusFrame object that the mouse has just exited the bounds of
	 * 
	 * @param e The MouseEvent that occurred
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		
		//print debug info
		System.out.println("mouse exit");
		
		//set mouseOver to false
		mouseOver = false;
		
	} //end method
	
}
