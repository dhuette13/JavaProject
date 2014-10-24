/*
 * Source code for CompletionEventListener in Event System
 * 
 * Author: Dr. Eric Becker
 * Date put into the code: October 18, 2014
 * 
 */
package archeologyp2.shared.gui;

import java.util.EventListener;

/**
 * 
 * @author Dr. Becker
 *
 */
public interface CompletionEventListener extends EventListener {
	
	/**
	 * Triggered by the relay class to notify all listeners
	 * 
	 * @param evt
	 */
	public void myEventOccurred(CompletionEvent evt);
	
}
