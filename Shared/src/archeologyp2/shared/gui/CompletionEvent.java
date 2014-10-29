/*
 * Source code for Completion Event in Event System 
 * 
 * Author: Dr. Eric Becker
 * Date put into the code: October 18, 2014
 * 
 */

package archeologyp2.shared.gui;

import java.util.EventObject;

/**
 * COMPLETIONEVENT FOR SHARED GUI
 * This class holds an event that is triggered when a 
 * dialog box completes it's computation.
 * 
 * @author Dr. Becker
 */
public class CompletionEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	
	public CompletionEvent(Object source) {
		super(source);
	}
}
