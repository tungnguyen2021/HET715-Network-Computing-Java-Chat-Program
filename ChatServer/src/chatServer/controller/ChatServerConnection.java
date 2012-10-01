package chatServer.controller;

import java.io.*;
import java.net.*;
import java.util.*;

import chat.model.*;
import chatServer.view.*;

public class ChatServerConnection implements ObserverChat, ObserverableChat, ObserverableChatServer, Runnable
{
	private Socket socket = null;
	private ChatListener chatListener;
	private ChatSender chatSender;
	private ChatUser chatUser;
	private ArrayList <ObserverChatServer> observerChatServerList;
	private String message;
	
	public ChatServerConnection(Socket theSocket)
	{
		//get the socket that connects to the client
		socket = theSocket;
		chatUser = new ChatUser();
		observerChatServerList = new ArrayList <ObserverChatServer>();
	}
	
	public void run()
	{
		//create input and output stream to the client
		try
		{
//			observerList= new ArrayList <ObserverChat>();
			chatListener = new ChatListener(socket.getInputStream());
			chatSender = new ChatSender(socket.getOutputStream());
			
			//add the current chatServerConnection to the observer of the ChatListener
			//so when the chatListener receives a message, it will then notify the ChatServerConnection
			//by doing that, we then can send it back to the controller which manages all the connection
			// and from there, the message will be send to all clients.
			chatListener.addObserverChat(this);
			
			//create listening thread to get incoming messages
			Thread listenThread = new Thread(chatListener);
			listenThread.start();
		}
		catch(Exception ex)
		{
			System.out.println("\nError when allocating memory for variables!\n");
			System.exit(1);
		}
	}
		
	//close the connection
	public void closeConnection()
	{
		try
		{
			System.out.println("\nClosing connection...");
			socket.close();
		}
		catch(IOException ioEx)
		{
			System.out.println("Unable to disconnect!");
			System.exit(1);
		}
	}
	
	public void sendMessage(String message)
	{
		chatSender.sendMessage(message);
	}
	
	//Observer patterns
	public void notifyObserverChat_ReceivingMessage(){
		for(ObserverChatServer observer:observerChatServerList)
		{
			observer.updateReceiveMessage(message, chatUser);
		}
	}

	public void notifyObserverChat_SendingMessage(){
	}
	
	//add observers to the listener so whenever a new message comes, all the observer will be notified.
	//when sending message, the server triggers the sending so don't need to notify anyone.
	public void addObserverChat(ObserverChat observer){
		chatListener.addObserverChat(observer);
	}
	
	public void updateMessage(String theMessage){
		//System.out.println("Update message");
		//chatSender.sendMessage(message);
	}
	
	public void updateResponse(String theMessage){
		message = theMessage;
		notifyObserverChat_ReceivingMessage();
	}
	
	public void addObserverChatServer(ObserverChatServer observer)
	{
		observerChatServerList.add(observer);
	}
	
	public void notifyObserverChatServer_ClientConnects()
	{
		for (ObserverChatServer observer:observerChatServerList)
		{
			observer.updateChatUserList_add(chatUser);
		}
	}
	
	public void notifyObserverChatServer_ClientLeaves()
	{
		for (ObserverChatServer observer:observerChatServerList)
		{
			observer.updateChatUserList_remove(chatUser);
		}
	}
}