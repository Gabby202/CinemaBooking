import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

public class CinemaBooking extends JFrame implements ActionListener{
	
	JPanel mainContentPanel = new JPanel();
	JPanel cardPanel1 = buildCardPanel1();
	JPanel cardPanel2 = buildCardPanel2();
	JPanel cardPanel3 = buildCardPanel3();

	JPanel buttonsPanel;
	JButton nextButton;
	JButton backButton;
	
	
	public CinemaBooking(){
		
		Container c = getContentPane();
		
		
		
		
		// Panels ===================================================
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BorderLayout()); //container Panel to allow positioning of leftColNav to the left of frame
		
		mainContentPanel = new JPanel(); //panel that will house main content of pages
		mainContentPanel.setLayout(new CardLayout());

		
		JPanel leftColNav = new JPanel(); //panel at the left of the frame for navigation
		leftColNav.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		leftColNav.setPreferredSize(new Dimension(256, 720));
		leftColNav.setBackground(new Color(107, 106, 104));
		
		JPanel titlePanel = new JPanel(); //panel at top of frame for welcome message
		titlePanel.setBackground(new Color(107, 106, 104));
		titlePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		
	
	
		

		// Labels ===================================================
		JLabel title = new JLabel("Welcome To Movies@Blanchardstown");
		title.setFont(new Font("Candara", Font.BOLD, 32));
		title.setForeground(Color.WHITE);
		
		JLabel leftColTitle = new JLabel("Navigation");
		leftColTitle.setFont(new Font("Candara", Font.ITALIC, 24));
		leftColTitle.setForeground(Color.WHITE);
		
		
		
		// Add Components ============================================
	
		
		titlePanel.add(title);
		leftColNav.add(leftColTitle);

		
		// cards to mainContentPane
		mainContentPanel.add(cardPanel1);
		mainContentPanel.add(cardPanel2);
		mainContentPanel.add(cardPanel3);

		
		containerPanel.add(titlePanel, BorderLayout.NORTH); 
		containerPanel.add(leftColNav, BorderLayout.WEST); 
		containerPanel.add(mainContentPanel);
		
		c.add(containerPanel);
		setSize(1024, 720);
		setVisible(true);
		setTitle("Cinema Booking");
		
	}//end constructor
	
	
	public void actionPerformed(ActionEvent e){
		//Get the card layout from the main panel
		CardLayout card = (CardLayout)mainContentPanel.getLayout();
		
		//Move to the next card in the card layout
		card.next(mainContentPanel);
	}
	
	//this is the first Card that is in the mainContentPanel
	public JPanel buildCardPanel1() {
		
		JPanel panel = null;
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		panel.setBackground(new Color(107, 106, 104));
		
		JLabel card1Label = new JLabel();
		card1Label.setText("Card 1");
		card1Label.setFont(new Font("Candara", Font.ITALIC, 24));
		card1Label.setForeground(Color.WHITE);
		card1Label.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 2));
		
		backButton = createButton("Back");
		backButton.addActionListener(this);
		nextButton = createButton("Next");
		nextButton.addActionListener(this);

		
		buttonsPanel.add(backButton);
		buttonsPanel.add(nextButton);
		
		panel.add(card1Label, BorderLayout.NORTH);

		panel.add(buttonsPanel, BorderLayout.SOUTH);

		//Returns the completed panel
		return panel;
		
	}
	
	//second card
	public JPanel buildCardPanel2() {
		
		JPanel panel = null;
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		panel.setBackground(new Color(107, 106, 104));
		
		JLabel card1Label = new JLabel();
		card1Label.setText("Card 2");
		card1Label.setFont(new Font("Candara", Font.ITALIC, 24));
		card1Label.setForeground(Color.WHITE);
		card1Label.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 2));
		
		backButton = createButton("Back");
		backButton.addActionListener(this);
		nextButton = createButton("Next");
		nextButton.addActionListener(this);

		
		buttonsPanel.add(backButton);
		buttonsPanel.add(nextButton);
		
		panel.add(card1Label, BorderLayout.NORTH);

		panel.add(buttonsPanel, BorderLayout.SOUTH);
		
		//Returns the completed panel
		return panel;
		
	}
	
	public JPanel buildCardPanel3() {
		
		JPanel panel = null;
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		panel.setBackground(new Color(107, 106, 104));
		
		JLabel card1Label = new JLabel();
		card1Label.setText("Card 3");
		card1Label.setFont(new Font("Candara", Font.ITALIC, 24));
		card1Label.setForeground(Color.WHITE);
		card1Label.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 2));
		
		backButton = createButton("Back");
		backButton.addActionListener(this);
		nextButton = createButton("Next");
		nextButton.addActionListener(this);

		
		buttonsPanel.add(backButton);
		buttonsPanel.add(nextButton);
		
		panel.add(card1Label, BorderLayout.NORTH);

		panel.add(buttonsPanel, BorderLayout.SOUTH);
		
		//Returns the completed panel
		return panel;
		
	}
	
	//this just creates a nicer looking button
	public static JButton createButton(String text) {
		  JButton button = new JButton(text);
		  button.setForeground(Color.WHITE);
		  button.setBackground(new Color(107, 106, 104));
		  Border line = new LineBorder(Color.GRAY);
		  Border margin = new EmptyBorder(5, 15, 5, 15);
		  Border compound = new CompoundBorder(line, margin);
		  button.setBorder(compound);
		  return button;
	}
	

	
	public static JPanel createBlankPanel(String text){
		JPanel panel = new JPanel(); //this is to take up the a row of the grid in mainContentPanel
		panel.setBackground(new Color(107, 106, 104));
		return panel;	
	}
	

	public static void main(String[] args) {
		new CinemaBooking();
	}//end main

}//end class
