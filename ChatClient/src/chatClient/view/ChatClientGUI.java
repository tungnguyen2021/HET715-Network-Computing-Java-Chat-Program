package chatClient.view;

import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

//import user created packages
import chat.model.*;

/**
 * @author CTB
 *
 */
/**
 * @author CTB
 *
 */
public class ChatClientGUI extends JFrame implements ActionListener, ObserverChat, ObserverableChat{
	Container container;
	JButton sendButton, clearButton, quitButton;
    private JTextArea messageArea; // for the email message body
    private JTextArea responseArea; // for the server response etc.
    private ArrayList <ObserverChat> observerList;
    private String message;
    
	//constructor
	public ChatClientGUI(){	
	}
	
	//@init the UI
	public void init()
	{
		//init the observerList
		observerList = new ArrayList<ObserverChat>();
		
		initUI();		
	}
		
	/**
	 * testing
	 */
	public void clearAll()
	{
		messageArea.setText("");
		responseArea.setText("");		
	}
	
    public void initUI()
    {
    	setSize(600, 600);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	InetAddress localHost = null;
    	try
    	{
        	localHost = InetAddress.getLocalHost();
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	this.setTitle("HET715 GROUP N3 - Chat Client Version 1.0 - " +  localHost.getHostAddress() );
    	//set up the elements in the GUI
    	sendButton  = new JButton("  Send Text   ");
    	clearButton = new JButton("Clear Screen");
    	//quitButton = new JButton("Quit");

    	messageArea = new JTextArea(10,30);
    	responseArea = new JTextArea(15,30);
    	responseArea.setEditable(false);
    	
    	//create the layout of the client GUI
    	container = getContentPane();
    	container.setLayout( new FlowLayout() );

    	Box hBox_Response = Box.createHorizontalBox();
    	Box hBox_Message = Box.createHorizontalBox();
    	JLabel response_Label = new JLabel("Response area");
    	JLabel message_Label = new JLabel("Message area");
    	
    	Box vBox = Box.createVerticalBox();
    	
    	//response area
    	hBox_Response.add(new JScrollPane(responseArea) );
    	hBox_Response.add(clearButton);
    	//hBox_Response.add(quitButton);
    	
    	//message area
    	hBox_Message.add( new JScrollPane(messageArea) );    	
    	hBox_Message.add(sendButton);
    	
    	//body
    	vBox.add(response_Label);
    	vBox.add(hBox_Response);
        vBox.add(Box.createVerticalStrut(10));
    	vBox.add(message_Label);    	
    	vBox.add(hBox_Message);
    	
    	container.add(vBox);
    	
    	//action listeners
		clearButton.addActionListener(this);
		sendButton.addActionListener(this);
		//quitButton.addActionListener(this);
		
		this.setVisible( true );
    }
    
    /**
     * This method is invoked when the send button is pressed
     * @param e button pressed event
     */
    public void actionPerformed(ActionEvent e)
    {
    	System.out.println("button clicked");
    	if (e.getSource() == sendButton )
    	{
        	System.out.println("send button");
    		message = messageArea.getText();
    		notifyObserverChat_SendingMessage();
    	}
        else if (e.getSource() == clearButton)
        {
//        	System.out.println("clear button");
        	clearAll();
        }        
/*        else 
        	if (e.getSource() == quitButton)
        {
        	message = ApplicationConstant.QUIT_STRING;
        	notifyObserverChat_SendingMessage();
        }
*//*
    	else if (e.getSource() == helpButton)
    	{
        	System.out.println("helpEmail button");    		
    		JFrame help = new displayHelp();
                help.setVisible(true);
    	}    		
*/
    }
    
    //update the response area
    //this method is called by the receiving thread.
	public void updateResponse(String message)
	{
		System.out.println("Textarea receives a message: " + message);
		responseArea.append("\n" + message);
	}
	
	public void updateMessage(String message)
	{
	}   
	
	public void addObserverChat(ObserverChat observer)
	{
		observerList.add(observer);
	}
	
	
	//the GUI only need to notify observers when it wants to send messages.
	// it doesn't need to tell observers when it receives message
	public void notifyObserverChat_SendingMessage()
	{
		for (ObserverChat observer:observerList)
		{
			observer.updateMessage(message);
		}
	}
	
	//don't need to implement
	//the GUI only need to notify observers when it wants to send messages.
	// it doesn't need to tell observers when it receives message
	public void notifyObserverChat_ReceivingMessage()
	{
	}
	
}
