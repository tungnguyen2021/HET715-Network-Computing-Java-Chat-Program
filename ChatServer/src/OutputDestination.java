
import javax.swing.*;

public class OutputDestination {
	private JTextArea textArea;
	
	public OutputDestination(JTextArea theTextArea)
	{
		textArea = theTextArea;
	}
	
	public void print(String s)
	{
		textArea.append(s);
	}
	
	public void println(String s)
	{
		textArea.append("\n"+s);
	}
}
