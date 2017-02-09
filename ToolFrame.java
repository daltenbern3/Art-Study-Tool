import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class ToolFrame extends JFrame {
	
	private ArtPanel x;
	
	public ToolFrame(String name,boolean isInOrder){
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		x = new ArtPanel(isInOrder);
		add(x);
	}
	
	public boolean isRunning(){
		return x.isRunning();
	}
}
