//class which takes exceptions thrown by given methods
public class DictionaryException extends Exception {
	public DictionaryException(String message){
		//print message specified within given methods
		super(message);
	}

}