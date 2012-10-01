package chat.model;

//interface to implement Observer pattern
//This is the observerable, who is be observed by observers
//Observerable objects will notify observers when data of observerable objects change
public interface ObserverableChat {
	public void notifyObserverChat_SendingMessage();
	public void notifyObserverChat_ReceivingMessage();
	
	public void addObserverChat(ObserverChat observer);
}
