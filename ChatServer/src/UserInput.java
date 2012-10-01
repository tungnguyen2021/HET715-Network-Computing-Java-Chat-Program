
import javax.swing.*;

public class UserInput {
	private JTextArea textArea;
	
	public UserInput(JTextArea theTextArea)
	{
		textArea = theTextArea;
	}
	
	public String readln()
	{
		return textArea.getText();
	}	
}
