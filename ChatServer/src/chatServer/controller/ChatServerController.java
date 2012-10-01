package chatServer.controller;
import java.io.*;
import java.net.*;
import java.util.*;

import chat.model.*;

public class ChatServerController implements ObserverChatServer
{
	private ServerSocket serverSocket;
	private ArrayList <ChatUser> chatUserList;
	private ArrayList <ChatServerConnection> chatServerConnectionList;
	
	public String username = "";
		
	public ChatServerController()
	{
		try
		{
			chatUserList = new ArrayList<ChatUser>();
			serverSocket = new ServerSocket(ApplicationConstant.SERVER_PORT);
			chatServerConnectionList = new ArrayList<ChatServerConnection>();
		}
		catch (IOException ioEx)
		{
			System.out.println("\nUnable to set up port!");
			System.exit(1);
		}
		
		System.out.println("Server starts");
	}
	
	public void start()
	{
		try
		{
			do
			{
				//Wait for client...
				Socket client = serverSocket.accept();
	
				System.out.println("\nNew client accepted.\n");
	
				//Create a thread to handle communication with
				//this client and pass the constructor for this
				//thread a reference to the relevant socket...
				ChatServerConnection chatConnection = new ChatServerConnection(client);				
				chatServerConnectionList.add(chatConnection);
				
				//add the chatServerController as an observer for the connection,
				//so when the chatConnection receives a message, it will notify the controller.
				chatConnection.addObserverChatServer(this);
				Thread t = new Thread(chatConnection);
				t.start();					
			}while (true);
		}
		catch(Exception ex)
		{
			System.err.println("Error when accepting new connection");
			ex.printStackTrace();
		}
	}
	
	public void updateChatUserList_add(ChatUser theChatUser)
	{
		chatUserList.add(theChatUser);
	}
	
	public void updateChatUserList_remove(ChatUser theChatUser)
	{
		chatUserList.remove(theChatUser);
	}
	
	public void updateReceiveMessage(String message, ChatUser theChatSender)
	{
		broadcastMessage(message, theChatSender);
	}
	
	public void broadcastMessage(String message, ChatUser theChatSender)
	{
		for (ChatServerConnection connection:chatServerConnectionList)
		{
			connection.sendMessage(message);
		}
	}
}