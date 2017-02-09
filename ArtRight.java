import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class ArtRight extends JPanel {
	
	private ImageIcon[] images;
	private int imageNumber;
	private JLabel image;
	private ArrayList<Integer> order;
	private final int NUMPICS = 24;
	
	public ArtRight(ArrayList<Integer> order){
		this.order = order;
		imageNumber = 0;
		images = new ImageIcon[200];
		String path = "resources/images/1.jpg";
		
		for(int x = 0;x<NUMPICS;x++){
			path = path.substring(0,17)+(x+1)+".jpg";
			try{
				java.net.URL url = getClass().getResource(path);
				images[x] = new ImageIcon(url);
			}catch(Exception e){
				System.out.println("didn't work");
			}
		}
		
		image = new JLabel(images[order.get(0)]);
		add(image);
	}
	
	public int getImageNumber(){
		return imageNumber;
	}
	
	public void nextImage() throws Exception{
		if(imageNumber==NUMPICS-1){
			throw new Exception("No more images!!");
		}if(imageNumber==-2){
			imageNumber = 0;
			throw new Exception("Can't go any further back!!");
		}
		imageNumber++;
		remove(image);
		image = new JLabel("", images[order.get(imageNumber)],JLabel.CENTER);
		add(image,BorderLayout.CENTER);
	}
	
	public void previous(){
		imageNumber--;
		imageNumber--;
	}
}
