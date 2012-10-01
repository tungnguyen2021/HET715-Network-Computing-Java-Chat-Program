
package chat.model;
import java.io.*;
import java.util.*;

public class ChatListener implements Runnable, ObserverableChat  {
	private BufferedReader input;
	private boolean stop;
	private ArrayList <ObserverChat> observerList;
	private String message;
	
	//initialize the listening thread
	public ChatListener(InputStream inStream)
	{
		stop = false;
		observerList = new ArrayList<ObserverChat>();
		
		try
		{
			input = new BufferedReader(new InputStreamReader(inStream) );
		}
		catch(Exception ex)
		{
			//there is an error here
			System.err.println("Error when create chat listening thread: " + ex.toString());
		}
	}
	
	public void setStop(boolean value)
	{
		stop = value;
	}
	
	public boolean isStopped()
	{
		return stop == true;
	}	
	
	public void run()
	{
		try
		{
			while ( !isStopped() )
			{
				message = input.readLine();
				if (message.contains(ApplicationConstant.QUIT_STRING))
				{
					message = "Connection is closed";
					setStop(true);
				}
				//notify the observer
				notifyObserverChat_ReceivingMessage();
			}			
		}
		catch(Exception ex)
		{
			System.err.println("Error when reading input: " + ex.toString());
		}
	}
	
	//notify the observer that we have new message, please update the message log
	public void notifyObserverChat_ReceivingMessage()
	{
		for (ObserverChat observer: observerList)
		{
			System.out.println("Chat client receives a message " + message);
			observer.updateResponse(message);
		}
	}
	
	public void notifyObserverChat_SendingMessage()
	{
	}
	
	//add observer to observerList
	public void addObserverChat(ObserverChat observer)
	{
		observerList.add(observer);
	}
}
