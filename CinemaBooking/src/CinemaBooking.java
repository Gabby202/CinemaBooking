import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

public class CinemaBooking extends JFrame implements ActionListener{
	
	public CinemaBooking(){
		
		Container c = getContentPane();
		
		
		// Panels ===================================================
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BorderLayout()); //container Panel to allow positioning of leftColNav to the left of frame
		
		JPanel mainContentPanel = new JPanel(); //panel that will house main content of pages
		mainContentPanel.setLayout(new GridLayout(5, 1));
		mainContentPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		mainContentPanel.setBackground(new Color(107, 106, 104));
		
		JPanel leftColNav = new JPanel(); //panel at the left of the frame for navigation
		leftColNav.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		leftColNav.setPreferredSize(new Dimension(256, 720));
		leftColNav.setBackground(new Color(107, 106, 104));
		
		JPanel titlePanel = new JPanel(); //panel at top of frame for welcome message
		titlePanel.setBackground(new Color(107, 106, 104));
		titlePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JPanel beginBookingPanel = new JPanel(); 
		JPanel nextBackButtons = new JPanel();
		nextBackButtons.setLayout(new GridLayout(1, 2));

		beginBookingPanel.setBackground(new Color(107, 106, 104));
		beginBookingPanel.setLayout(new GridLayout(2, 1));

		

		// Labels ===================================================
		JLabel title = new JLabel("Welcome To Movies@Blanchardstown");
		title.setFont(new Font("Candara", Font.BOLD, 32));
		title.setForeground(Color.WHITE);
		
		JLabel leftColTitle = new JLabel("Navigation");
		leftColTitle.setFont(new Font("Candara", Font.ITALIC, 24));
		leftColTitle.setForeground(Color.WHITE);
		
		JLabel beginBookingLabel = new JLabel("Click Next to begin booking!");
		beginBookingLabel.setFont(new Font("Candara", Font.PLAIN, 24));
		beginBookingLabel.setForeground(Color.WHITE);
		beginBookingLabel.setHorizontalAlignment(JLabel.CENTER);
	
		
		
		// Add Components ============================================
	
		
		titlePanel.add(title);
		leftColNav.add(leftColTitle);
		nextBackButtons.add(createButton("Back"));
		nextBackButtons.add(createButton("Next"));

		
		beginBookingPanel.add(beginBookingLabel);
		beginBookingPanel.add(nextBackButtons);
		
		mainContentPanel.add(createBlankPanel(""));
		mainContentPanel.add(createBlankPanel(""));
		mainContentPanel.add(createBlankPanel(""));
		mainContentPanel.add(createBlankPanel(""));
		mainContentPanel.add(beginBookingPanel);
		
		containerPanel.add(titlePanel, BorderLayout.NORTH); 
		containerPanel.add(leftColNav, BorderLayout.WEST); 
		containerPanel.add(mainContentPanel);
		
		c.add(containerPanel);
		setSize(1024, 720);
		setVisible(true);
		setTitle("Cinema Booking");
		
	}//end constructor
	
	
	public void actionPerformed(ActionEvent e){
		
	}
	
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
