import javax.swing.*;


import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class ArtPanel extends JPanel{
	private ArtLeft left;
	private ArtRight right;
	private final Random rnd = new Random();
	private final int N = 24;
	private ArrayList<Integer> order = new ArrayList<Integer>(N);
	private ArrayList<Integer> inOrder = new ArrayList<Integer>(N);
	private boolean isRunning = true;
	private boolean isInOrder;
	
	public ArtPanel(boolean isInOrder){
		this.isInOrder = isInOrder;
		setLayout(new BorderLayout());
		setOrder();
		
		left = new ArtLeft();
		right = new ArtRight(order);
		
		add(left,BorderLayout.WEST);
		add(right,BorderLayout.EAST);
	}
	
	public class ArtLeft extends JPanel{
		private JLabel[] type = new JLabel[7];
		private final String[] TYPES = {"Artist:","Title:","Date:","Period:","Material:","Commisioned By:","Location:"};
		private JTextField[] inputs = new JTextField[7];
		private JLabel[] answers = new JLabel[7];
		private String[][] answer = new String[100][7];
		
		private final InputStream is = getClass().getResourceAsStream("resources/answers.txt");
		//private final File ANSWERS = FileUtils.toFile(getClass().getResource("resources/answers.txt"));
		private Scanner s;
		private boolean shown = false;
		
		public ArtLeft(){
			setLayout(new GridLayout(9,3));
			try{
				s = new Scanner(is);
				int count = 0;
				while(s.hasNextLine()){
					for(int x = 0;x<7;x++){
						if(s.hasNextLine()){
							answer[count][x] = s.nextLine();
						}
					}count++;
				}
				for(int x = 0;x<7;x++){
					type[x] = new JLabel(TYPES[x]);
					add(type[x]);
					inputs[x] = new JTextField("");
					add(inputs[x]);
					answers[x] = new JLabel("",JLabel.CENTER);
					add(answers[x]);
				}
			}catch(Exception e){
				System.out.println("File not found");
			}
			
			JButton previous = new JButton("Previous Image");
			previous.addActionListener(new PreviousImageListener());
			add(previous);
			JButton next = new JButton("Next Image");
			next.addActionListener(new NextImageListener());
			add(next);
			JButton showAnswers = new JButton("Toggle Answers");
			showAnswers.addActionListener(new ShowAnswerListener());
			add(showAnswers);
		}
		
		private class ShowAnswerListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!shown){
					for(int x = 0;x<7;x++){
						answers[x].setText(answer[order.get(right.getImageNumber())][x]);
					}
					shown = true;
				}else{
					for(int x = 0;x<7;x++){
						answers[x].setText("");
					}shown = false;
				}
			}
			
		}
		
		private class PreviousImageListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				right.previous();
				NextImageListener a = new NextImageListener();
				a.actionPerformed(new ActionEvent(new Object(),0,""));
			}
			
		}
		
		private class NextImageListener implements ActionListener{
			
			public void actionPerformed(ActionEvent e){
				for(int x=0;x<7;x++){
					answers[x].setText("");
					inputs[x].setText("");
				}
				shown = false;
				try{
					right.nextImage();
				}catch(Exception arg){
					isRunning = false;
				}
				answers[0].setText("refresh");
				answers[0].setText("");
			}
		}
	}
	
	private void setOrder() {
		ArrayList<Integer> inOrder = new ArrayList<Integer>(N);
		for (int i = 0; i < N; i++) {
	        inOrder.add(i);
	    }
		if(!isInOrder){
			Collections.shuffle(inOrder, rnd);
			order.addAll(inOrder.subList(0, N));
		}else{
			order = inOrder;
		}
	}
	
	public boolean isRunning(){
		return isRunning;
	}
}
