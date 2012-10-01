package chat.model;
import java.net.*;

public class ChatUser {
	private InetAddress address;
	private String username;
	
	public ChatUser()
	{
	}
	
	public ChatUser(InetAddress theAddress, String theUsername )
	{
		address = theAddress;
		username = theUsername;
	}
	
	public InetAddress getAddress()
	{
		return address;
	}
	
	public String getUsername()
	{
		return username;
	}
}
