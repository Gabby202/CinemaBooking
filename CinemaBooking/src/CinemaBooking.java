import javax.swing.*;
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

		JPanel beginBookingPanel = new JPanel(); //this is to take up the a row of the grid in mainContentPanel
		beginBookingPanel.setBackground(new Color(107, 106, 104));

		
		JPanel blankPanel1 = new JPanel(); //this is to take up the a row of the grid in mainContentPanel
		blankPanel1.setBackground(new Color(107, 106, 104));

		JPanel blankPanel2 = new JPanel(); //this is to take up the a row of the grid in mainContentPanel
		blankPanel2.setBackground(new Color(107, 106, 104));
		
		JPanel blankPanel3 = new JPanel(); //this is to take up the a row of the grid in mainContentPanel
		blankPanel3.setBackground(new Color(107, 106, 104));


		// Labels ===================================================
		JLabel title = new JLabel("Welcome To Movies@Blanchardstown");
		title.setFont(new Font("Candara", Font.BOLD, 32));
		title.setForeground(Color.WHITE);
		
		JLabel leftColTitle = new JLabel("Navigation");
		leftColTitle.setFont(new Font("Candara", Font.ITALIC, 24));
		leftColTitle.setForeground(Color.WHITE);
		
		JLabel beginBookingLabel = new JLabel("Click here to begin booking!");
		beginBookingLabel.setFont(new Font("Candara", Font.BOLD, 24));
		beginBookingLabel.setForeground(Color.WHITE);
		
		// Add Components ============================================
		containerPanel.add(leftColNav, BorderLayout.WEST); 
		containerPanel.add(mainContentPanel);
		
		titlePanel.add(title);
		leftColNav.add(leftColTitle);
		
		beginBookingPanel.add(beginBookingLabel);
		
		mainContentPanel.add(titlePanel);
		mainContentPanel.add(blankPanel1);
		mainContentPanel.add(blankPanel2);
		mainContentPanel.add(blankPanel3);
		mainContentPanel.add(beginBookingPanel);

		
		c.add(containerPanel);
		setSize(1024, 720);
		setVisible(true);
		setTitle("Cinema Booking");
		
	}//end constructor
	
	
	public void actionPerformed(ActionEvent e){
		
	}

	public static void main(String[] args) {
		new CinemaBooking();
	}//end main

}//end class
