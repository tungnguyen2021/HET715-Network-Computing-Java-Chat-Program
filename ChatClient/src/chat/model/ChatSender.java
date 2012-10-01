package chat.model;

import java.io.*;

public class ChatSender implements ObserverChat{
	private PrintWriter output;
	
	//initialize the listening thread
	public ChatSender(OutputStream outStream)
	{
		try
		{
			output = new PrintWriter(outStream, true);
		}
		catch(Exception ex)
		{
			//there is an error here
			output.print("Error when create chat ClientSending output stream: " + ex.toString());
		}
	}
	
	public void sendMessage(String message)
	{
		try
		{
			output.println(message);
		}
		catch(Exception ex)
		{
			output.println("Error when sending message: " + ex.toString());
		}
	}
	
	public void updateMessage(String message)
	{
		sendMessage(message);
	}
	
	public void updateResponse(String message)
	{
	}
}