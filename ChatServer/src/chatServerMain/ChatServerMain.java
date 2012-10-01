package chatServerMain;

import java.io.*;

import chat.model.*;
import chatServer.view.*;
import chatServer.controller.*;

public class ChatServerMain {
	
	public static void main(String []args)
	{
		ChatServerController server = new ChatServerController();		
		server.start();
	}
}