import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;




public class OrderQuestionFrame extends JFrame {
	private boolean isInOrder;
	
	public OrderQuestionFrame(){
		super("What Order Would You Prefer?");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);
		JButton inOrder = new JButton("In Order");
		inOrder.addActionListener(new InOrderListener());
		panel.add(inOrder,BorderLayout.WEST);
		JButton randomOrder = new JButton("Random Order");
		randomOrder.addActionListener(new RandomOrderListener());
		panel.add(randomOrder,BorderLayout.EAST);
		
	}
	
	private class InOrderListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			isInOrder = true;
			closeWindow();
		}
		
	}
	
	private class RandomOrderListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			isInOrder = false;
			closeWindow();
		}
		
	}
	
	private void closeWindow(){
		dispose();
	}
	
	public boolean isInOrder(){
		return isInOrder;
	}
}
