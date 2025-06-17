package viruses;

import java.awt.*;
import java.util.*;
//import javax.swing.*;

public class Viruses {
	
	public static void main(String[] args) throws InterruptedException, AWTException {
		
		
	}
	
	/**A method which prevents use of the computer without restarting by consistently creating new windows the size of the
	 * monitor whenever the mouse isn't over the highest window. Can be broken by a second monitor or monitor with greater
	 * size than 1920x1080
	 * 
	 * @throws InterruptedException inherits Thread.sleep(int milis) and its issues
	 */
	public static void noMoreComputer() throws InterruptedException {
		
		//create ArrayList to hold windows
		ArrayList<VirusFrame> h = new ArrayList<VirusFrame>();
		
		//create first window
		h.add(new VirusFrame(Color.black, 1920, 1080));
		
		//create iterative variable to track window #, as old windows are not removed when another is opened
		int i = 0;
		
		//loop until escape completed
		while(!VirusFrame.getEscapeCompleted()) {
			System.out.println(h.get(i).getMouseOver());
			//delay check for window focus, as the new window has to open first
			Thread.sleep(50);
			System.out.println(!h.get(i).getMouseOver());
			//conditional checking if the mouse is not over the last on top window
			if(!h.get(i).getMouseOver()) {

				//create a new frame to cover the now uncovered screen
				h.add(new VirusFrame(Color.black, 1920, 1080));
				
				//increment iterative variable so we operate on the new window
				i++;
				
			} //end conditional
		} //end while
		
		
		//enhanced for deleting all opened windows after escape is input
		for(VirusFrame v: h) {
			v.deleteThis();
		}
		
	} //end method
	
	/**Creates epileptic effect, do not run with epilepsy it WILL trigger
	 * 
	 * @param epilepsyAmount how much epilepsy you want (a good start is 25-50)
	 * @param blindedValue how much you want to be blinded (standard should be about 200)
	 */
	public static void epilepsy(int epilepsyAmount, int blindedValue) {
		
		//create list of windows to use for effect
		VirusFrame[] h = new VirusFrame[epilepsyAmount];
		
		//for loop to instantiate windows
		for(int i=0; i<h.length; i++) {
			
			//instantiate frame
			h[i] = new VirusFrame(Color.black, blindedValue);
			
			//randomize its color
			h[i].randomizeColor();
			
		} //end for
		
		
		//while loop to run until escape is completed
		while(!VirusFrame.getEscapeCompleted()) {
			
			//loop through list of frames
			for(int i=0; i<h.length; i++) {
				
				//randomize frames location on the monitor
				h[i].setLocation((int)(Math.random()*1920+1) - blindedValue, (int)(Math.random()*1080+1) - blindedValue);
				
				//extra code so that escape sequence activates instantly instead of waiting until current for completion
				if(!VirusFrame.getEscapeCompleted()) break;
				
			} //end for
		} //end while
		
		
		//enhanced for to close all windows
		for(VirusFrame v: h) {
			v.deleteThis();
		}
		
	} //end method
	
	/**A fun little screen saver effect created by making a list of windows appear with randomized colors, overlapping the main
	 * computer display (I don't know how screen savers actually help your screen though, so it doesn't do any of that)
	 * 
	 * @param windowSize The size of the windows that appear
	 * @param windowLife The duration a window will be left open in delay miliseconds before it is replaced
	 * @param duration The number of times windows will appear on screen in a new location
	 * @param delay The delay between windows appearing and disappearing in miliseconds
	 * @throws InterruptedException inherits Thread.sleep(int milis) and its issues
	 */
	public static void screenSaver(int windowSize, int windowLife, long duration, int delay) throws InterruptedException {
		VirusFrame[] h = new VirusFrame[windowLife]; //list of windows to open
		VirusFrame[] oldH = new VirusFrame[windowLife]; //reference list of windows opened
		long timer = 0; //timer tracking effect duration
		
		//loop for timer duration
		while(timer>=duration && !VirusFrame.getEscapeCompleted()) {
			
			//loop through list of frames to operate on each
			for(int i=0; i<h.length; i++) {
				
				//when we've got a full list start to loop the use of memory by reusing old refs
				if(timer>=h.length) {
					//delete old frames through their references
					oldH[i].deleteThis();
				}
				
				//replace old window/create new one
				h[i] = new VirusFrame(Color.black, windowSize);
				
				//randomize its position on the screen, but make sure not to have it off screen
				int randX = (int)(Math.random()*1920+1) - windowSize;
				int randY = (int)(Math.random()*1080+1) - windowSize;
				h[i].setLocation(randX, randY);
				
				//randomize window color, preventing color loop
				h[i].randomizeColor();
				
				//create object reference so we can reuse memory
				oldH[i] = h[i];
				
				
				//create some delay between each windows operation to ease effect and avoid error of operation time
				Thread.sleep(delay);
				
				//abort for loop when while condition is false, preventing timer from going over before the while can stop
				if(timer>=duration && !VirusFrame.getEscapeCompleted()) break;
				
				//increment the timer to eventually stop
				timer++;
				
			} //end for
		} //end while
		
		
		//after effect is done, remove windows in the same way they appear, ending the effect in the way it appeared
		for(int i=h.length-1; i>=0; i--) {
			
			//use same delay as before to make disappearing effect the same speed
			Thread.sleep(delay);
			
			//delete window from memory
			h[i].deleteThis();
			
		} //end for
	} //end method
	
	/**Prevents use of mouse while active. Loop ends on escape sequence, but sacrifices are made in preventing work arounds to
	 * create a more subtle effect, leading to a few other workarounds
	 * 
	 * @throws InterruptedException inherits Thread.sleep(int milis) and its issues
	 */
	public static void virus() throws InterruptedException {
		
		//create a window of size one pixel, matching the black border color of the mouse
		VirusFrame trackingWindow = new VirusFrame(Color.black, 1);
		
		while(!VirusFrame.getEscapeCompleted()) {
			
			//set window location to the mouse coordinates, ignoring subpixel values
			trackingWindow.setLocation((int) MouseInfo.getPointerInfo().getLocation().getX(),
									   (int) MouseInfo.getPointerInfo().getLocation().getY());
			
		} //end while
		
		//delete window when effect is over
		trackingWindow.deleteThis();
		
	} //end method
	
}
