package archeologyp2.shared.gui;

import javax.swing.event.EventListenerList;


//Add the event registration and notification code to a class.
public class Relay {
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
	void fireMyEvent(CompletionEvent evt) {

		Object[] listeners = listenerList.getListenerList();
		// Each listener occupies two elements - the first is the listener class
		// and the second is the listener instance
		for (int i=0; i<listeners.length; i+=2) {
			if (listeners[i] == CompletionEventListener.class) {
				((CompletionEventListener)listeners[i+1]).myEventOccurred(evt);
			}
		}
	}
}