package chatClientMain;

import java.io.*;

import chat.model.*;
import chatClient.view.*;
import chatClient.controller.*;

public class ChatClientMain {
	public static final String serverFile = "IP.txt";
	
	/**
	 * This is the purpose of the program
	 * @param args
	 */
	public static void main(String []args)
	{
		ChatClientGUI chatClientUI = new ChatClientGUI();
		chatClientUI.init();
		ChatClientConnection clientConnection = new ChatClientConnection();
		clientConnection.ReadServerFile(serverFile);
		clientConnection.createConnection();
		
		//add observers
		chatClientUI.addObserverChat(clientConnection);
		clientConnection.addObserverChat(chatClientUI);
		
		chatClientUI.setVisible(true);
	}
}
