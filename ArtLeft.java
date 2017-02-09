import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArtLeft extends JPanel{
	
	public ArtLeft(){
		File file = new File("src/resources/images/1.jpg");
		String path = file.getAbsolutePath();
		System.out.println(file.toURI());
		java.net.URI a = file.toURI();
		try{
			java.net.URL b = a.toURL();
			System.out.println(b);
			ImageIcon c = new ImageIcon(b);
			System.out.println(c.getIconHeight());
		}catch(Exception e){
			System.out.println("didn't work");
		}
		
		
		java.net.URL imgURL = getClass().getResource(path);
		System.out.println(imgURL);
		
		
	}
}
