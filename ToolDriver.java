import java.awt.Toolkit;
import java.awt.event.WindowEvent;


public class ToolDriver {
	
	public static void main(String[] args){
		//new ArtLeft();
		OrderQuestionFrame order = new OrderQuestionFrame();
		order.setVisible(true);
		order.pack();
		order.setSize(300,100);
		while(order.isActive()){};
		
		
		ToolFrame frame = new ToolFrame("Test Yourself!", order.isInOrder());
		frame.setVisible(true);
		frame.pack();
		frame.setSize(1000,500);
	}
}
