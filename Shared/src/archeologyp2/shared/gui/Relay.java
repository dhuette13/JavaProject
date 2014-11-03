/*
 * Source code for Relay in event handling systems
 * Author: Dr. Eric Becker
 * Date put into the code: October 18, 2014
 * 
 */

package archeologyp2.shared.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.EventListenerList;


/**
 * RELAY FOR SHARED GUI
 * Add the event registration and notification code to a class.
 * 
 * @author Dr. Becker
 *
 */
public class Relay {
	private JFrame frame;
	
	// Create the listener list
	protected EventListenerList listenerList = new EventListenerList();

	// This methods allows classes to register for MyEvents
	public void addMyEventListener(CompletionEventListener listener) {

		listenerList.add(CompletionEventListener.class, listener);
	}

	// This methods allows classes to unregister for MyEvents
	public void removeMyEventListener(CompletionEventListener listener) {

		listenerList.remove(CompletionEventListener.class, listener);
	}

	// This private class is used to fire MyEvents
	public void fireMyEvent(CompletionEvent event) {
		try{
			Object[] listeners = listenerList.getListenerList();
			// Each listener occupies two elements - the first is the listener class
			// and the second is the listener instance
			for (int i=0; i<listeners.length; i+=2) {
				if (listeners[i] == CompletionEventListener.class) {
					((CompletionEventListener)listeners[i+1]).myEventOccurred(event);
				}
			}
		}
		catch(NullPointerException n){
			JOptionPane.showMessageDialog(frame,
				    "Uh oh! Looks like you typed in something wrong. Please try again.",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
}