package chat.model;

//this class defines the observerableChatServer which is used to see if new client connects to the server
//or leaves the servers
public interface ObserverableChatServer {
	public void addObserverChatServer(ObserverChatServer observer);
	public void notifyObserverChatServer_ClientConnects();
	public void notifyObserverChatServer_ClientLeaves();	
}
