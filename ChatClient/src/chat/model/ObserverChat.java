package chat.model;

//interface to implement Observer pattern
//This is the observer, who will observe to the change of observerable objects
//Observerable objects will notify observer when data of observerable objects change
public interface ObserverChat{
	public void updateResponse(String message);
	public void updateMessage(String message);
}
