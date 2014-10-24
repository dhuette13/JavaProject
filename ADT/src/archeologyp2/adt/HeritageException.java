package archeologyp2.adt;

/**
 * THE HERITAGE EXCEPTION CLASS
 * 
 * This method creates a customized exception
 * if the user tries to dig in a coordinate
 * they'd previously marked as heritage. 
 * 
 * @author Daniel
 * @author Celine
 *
 */

public class HeritageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * For the public HeritageException
	 */
	public HeritageException(){
		super();
	}
	
	/**
	 * For the public HeritageException
	 * 
	 * This method showcases inheritance by
	 * printing out a message.
	 * 
	 * @param message
	 */
	public HeritageException(String message){
		super(message);
	}

}
