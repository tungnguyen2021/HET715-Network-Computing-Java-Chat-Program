package chatClient.controller;

import java.io.*;
import java.net.*;
import java.util.*;

import chat.model.*;
import chatClient.view.*;

public class ChatClientConnection implements ObserverChat, ObserverableChat
{
	private String serverFile;
	private ArrayList <InetAddress> serverList;
	private InetAddress activeServer;
	private Socket socket = null;
	private ChatListener chatListener;
	private ChatSender chatSender;
//	private ArrayList <ObserverChat> observerList;
	
	public ChatClientConnection()
	{
		try
		{
//			observerList= new ArrayList <ObserverChat>();
			serverList = new ArrayList<InetAddress>();		
		}
		catch(Exception ex)
		{
			System.out.println("\nError when allocating memory for variables!\n");
			System.exit(1);
		}
	}
	
	//read all the servers from serverFile
	//suppose that all servers listen on the same port
	public void ReadServerFile(String theServerFile)
	{
		serverFile = theServerFile;
		try
		{
			//ref: http://www.roseindia.net/java/beginners/java-read-file-line-by-line.shtml
			FileReader fr = new FileReader(serverFile);
			BufferedReader br = new BufferedReader( fr);
			String line;
			
			while( (line = br.readLine()) != null)
			{
				InetAddress add = InetAddress.getByName(line);
				serverList.add(add);				
			}
			
			//close the input stream
			fr.close();
		}
		catch(Exception ex)
		{
			System.out.println("Error when reading the host file");
			System.exit(1);
		}
	}
	
	//try to connect to one of the server
	public boolean createConnection()
	{
		for(InetAddress host: serverList)
		{			
			if(connectToServer(host))
			{
				//the first server that client can connect become the active server for the client
				activeServer = host;
				return true;
			}
		}
		
		return false;
	}
	
	//create a connection to a server
	private boolean connectToServer(InetAddress host)
	{
		try
		{
			socket = new Socket(host,ApplicationConstant.SERVER_PORT);
			chatListener = new ChatListener(socket.getInputStream());
			chatSender = new ChatSender(socket.getOutputStream());
			
			//starts the listener thread
			Thread t = new Thread(chatListener);
			t.start();
			
			//t.st();
			//Timer tm = new Timer(500,createConnection);
			return true;
		}
		catch(IOException ioEx)
		{
			//can't connect to the server
			ioEx.printStackTrace();
		}
		
		return false;
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
	
	public void notifyObserverChat_ReceivingMessage(){
	}

	public void notifyObserverChat_SendingMessage(){
	}
	
	//add observers to the listener so whenever a new message comes, all the observer will be notified.
	public void addObserverChat(ObserverChat observer){
		chatListener.addObserverChat(observer);
	}
	
	public void updateMessage(String message){
		System.out.println("Update message");
		chatSender.sendMessage(message);
	}
	
	public void updateResponse(String message){
	}
}