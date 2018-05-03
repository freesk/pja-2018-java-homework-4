package mypackage;
import javax.swing.JTextArea;

public class Screen {
	
	JTextArea textArea;
	
	public Screen(JTextArea textArea) {
		this.textArea = textArea;
	}
	// log into the window 
	synchronized public void log(String text) {	
		textArea.append(text + "\n");
	}
}
