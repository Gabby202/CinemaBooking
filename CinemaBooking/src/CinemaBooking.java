import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


/**
 * This class houses the gui and most of the functions
 * @author Gabby
 *
 */
public class CinemaBooking extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	JPanel mainContentPanel = new JPanel();
	JPanel cardPanel1 = buildCardPanel(1);
	JPanel cardPanel2 = buildCardPanel(2);
	JPanel cardPanel3 = buildCardPanel(3);
	JPanel cardPanel4 = buildCardPanel(4);

	JPanel buttonsPanel;
	JButton nextButton;
	JButton backButton;

	// movie list method arrays made global to give mouse listener methods scope
	JPanel moviePanel[], spacePanel[], descriptionPanel[], seat[];
	JLabel movieLabel[], movieDescription[];
	Icon movieImage[];
	boolean[] pressed;
	

	/**
	 * Constructor for program
	 */
	public CinemaBooking() {

		Container c = getContentPane();

		// Panels ===================================================
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BorderLayout()); // container Panel to
														// allow positioning of
														// leftColNav to the
														// left of frame

		mainContentPanel = new JPanel(); // panel that will house main content
											// of pages
		mainContentPanel.setLayout(new CardLayout());

		JPanel leftColNav = new JPanel(); // panel at the left of the frame for
											// navigation
		leftColNav.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		leftColNav.setPreferredSize(new Dimension(256, 720));
		leftColNav.setBackground(new Color(107, 106, 104));

		JPanel titlePanel = new JPanel(); // panel at top of frame for welcome
											// message
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
		mainContentPanel.add(cardPanel4);

		containerPanel.add(titlePanel, BorderLayout.NORTH);
		containerPanel.add(leftColNav, BorderLayout.WEST);
		containerPanel.add(mainContentPanel);

		c.add(containerPanel);
		setSize(1024, 720);
		setVisible(true);
		setTitle("Cinema Booking");

	}// end constructor


	public void actionPerformed(ActionEvent e) {
		CardLayout card = (CardLayout) mainContentPanel.getLayout(); // Get the
																		// card
																		// layout
																		// from
																		// the
																		// main
																		// panel
		card.next(mainContentPanel);// Move to the next card in the card layout

	}

	// this is the first Card that is in the mainContentPanel
	public JPanel buildCardPanel(int panelNumber) {

		JPanel panel = null;
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		panel.setBackground(new Color(107, 106, 104));

		JLabel card1Label = new JLabel();
		card1Label.setText("Card " + panelNumber);
		card1Label.setFont(new Font("Candara", Font.ITALIC, 24));
		card1Label.setForeground(Color.WHITE);
		card1Label.setHorizontalAlignment(JLabel.CENTER);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 2));

		backButton = createButton("Back");
		backButton.addActionListener(this);
		nextButton = createButton("Next");
		nextButton.addActionListener(this);

		// changes method depending on which panel
		if (panelNumber == 1) {
			panel.add(movieList());
			card1Label.setText("Select Your Movie");
		} else if (panelNumber == 3) {
			panel.add(seatMap());
			card1Label.setText("Select Your Seat");
		} else if (panelNumber == 4) {
			panel.add(paymentInfo());
			card1Label.setText("Payment Information");
		}

		buttonsPanel.add(backButton);
		buttonsPanel.add(nextButton);

		panel.add(card1Label, BorderLayout.NORTH);

		panel.add(buttonsPanel, BorderLayout.SOUTH);

		// Returns the completed panel
		return panel;

	}
	public JPanel paymentInfo() {
		JPanel paymentInfo = new JPanel();
		paymentInfo.setLayout(new GridLayout(1,1)); //makes the mainContent panel fill all the space
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new GridLayout(6, 1));
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setHorizontalAlignment(JLabel.LEFT);
		JTextField nameField = new JTextField(16);
		JLabel surnameLabel = new JLabel("Surname: ");
		JTextField surNameField = new JTextField(16);
		JTextField add1 = new JTextField(16);
		JTextField add2 = new JTextField(16);
		JTextField cardNo = new JTextField(16);
		JTextField exp = new JTextField(16);

		String stringLabels[] = {"Name", "Surname", "Address", "Address 2 ", "CardNo", "Cvv"};
		JLabel labels[] = new JLabel[6];
		
		JPanel[] panels = new JPanel[6];
		JPanel[] spacePanel = new JPanel[6];
		JPanel[] labelPanel = new JPanel[6];
		JPanel[] fieldPanel = new JPanel[6];
		JPanel[] innerFieldPanels = new JPanel[12];
		JTextField[] fields = new JTextField[12];
		
		fields[0] = new JTextField();
		fields[1] = new JTextField();
		fields[2] = new JTextField();
		fields[3] = new JTextField();
		fields[4] = new JTextField();
		fields[5] = new JTextField();

		fields[0].add(nameField);
		fields[1].add(surNameField);
		fields[2].add(add1);
		fields[3].add(add2);
		fields[4].add(cardNo);
		fields[5].add(exp);
		
		for (int i = 0; i < panels.length; i++ ) {
			panels[i] = new JPanel();
			spacePanel[i] = new JPanel();
			labelPanel[i] = new JPanel();
			fieldPanel[i] = new JPanel();
			panels[i].setLayout(new BorderLayout());
			innerFieldPanels[i] = new JPanel();
			
			labels[i] = new JLabel();
			labels[i].setText(stringLabels[i]);
			
			formatPanel(panels[i]);
			formatPanel(spacePanel[i]);
			formatPanel(labelPanel[i]);
			formatPanel(fieldPanel[i]);
			formatPanel(innerFieldPanels[i]);
			
			if(i > 0 && i < 5) {
				labelPanel[i].setLayout(new GridLayout(2,1));
				fieldPanel[i].setLayout(new GridLayout(2, 1));
				labelPanel[i].setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 20));
				labelPanel[i].setPreferredSize(new Dimension(20, 0));
			}
			
			
			labelPanel[i].setPreferredSize(new Dimension(20, 0));

		
			
		//	spacePanel[i].setPreferredSize(new Dimension(68, 0));
			fieldPanel[i].setPreferredSize(new Dimension(550, 0));
			
			fieldPanel[i].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 200)); //moves components
			
		//	panels[i].add(spacePanel[i], BorderLayout.WEST);
			panels[i].add(labelPanel[i], BorderLayout.CENTER);
			panels[i].add(fieldPanel[i], BorderLayout.EAST);
			mainContent.add(panels[i]);
			
			labels[i].setText(stringLabels[i]);
			labels[i].setForeground(Color.WHITE);
			
			
	
		}
		labelPanel[1].add(labels[0]);
		labelPanel[1].add(labels[1]);
		labelPanel[2].add(labels[2]);
		labelPanel[2].add(labels[3]);
		labelPanel[3].add(labels[4]);
		labelPanel[3].add(labels[5]);
		
		fieldPanel[1].add(innerFieldPanels[0]);
		fieldPanel[1].add(innerFieldPanels[1]);
		fieldPanel[2].add(innerFieldPanels[2]);
		fieldPanel[2].add(innerFieldPanels[3]);
		fieldPanel[3].add(innerFieldPanels[4]);
		fieldPanel[3].add(innerFieldPanels[5]);
		
		innerFieldPanels[0].add(nameField);
		innerFieldPanels[1].add(surNameField);
		innerFieldPanels[2].add(add1);
		innerFieldPanels[3].add(add2);
		innerFieldPanels[4].add(cardNo);
		innerFieldPanels[5].add(exp);
		
		for(int x = 0; x < 6; x++) {
			innerFieldPanels[x].setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 100));
		}

		
		labelPanel[0].setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		fieldPanel[0].setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 400));
		
		paymentInfo.add(mainContent);
		mainContent.setBackground(new Color(107, 106, 104));
		return paymentInfo;
	}

	public JPanel formatPanel(JPanel panel) {
		
		
		panel.setBackground(new Color(107,106,104));
		panel.setForeground(Color.WHITE);
		//panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		return panel;
	}

/**
 * This creates the list of movies for selection
 * @return JPanel panels to be used in cards
 */
	// method for showing list of movies
	public JPanel movieList() {

		JPanel movieList = new JPanel();
		movieList.setLayout(new GridLayout(4, 1));
		moviePanel = new JPanel[4];
		movieLabel = new JLabel[4];
		movieImage = new ImageIcon[4];
		spacePanel = new JPanel[4];
		descriptionPanel = new JPanel[4];
		movieDescription = new JLabel[4];
		
		movieList.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		movieImage[0] = new ImageIcon("images/the_hobbit1.jpg");
		movieImage[1] = new ImageIcon("images/captain_america1.jpg");
		movieImage[2] = new ImageIcon("images/hunger_games1.jpg");
		movieImage[3] = new ImageIcon("images/star_wars1.jpg");

		movieDescription[0] = new JLabel("The Hobbit: The Desolation Of Smaug");
		movieDescription[1] = new JLabel("Captain America: Civil War");
		movieDescription[2] = new JLabel("The Hunger Games: Mockingjay");
		movieDescription[3] = new JLabel("Star Wars: The Force Awakens");

		for (int i = 0; i < 4; i++) {
			moviePanel[i] = new JPanel();
			movieLabel[i] = new JLabel(movieImage[i]);
			spacePanel[i] = new JPanel();
			descriptionPanel[i] = new JPanel();

			movieLabel[i].setHorizontalAlignment(JLabel.LEFT);
			moviePanel[i].setLayout(new BorderLayout());
			spacePanel[i].setPreferredSize(new Dimension(68, 0));
			descriptionPanel[i].setPreferredSize(new Dimension(538, 0));
			descriptionPanel[i].setLayout(new BorderLayout());
			descriptionPanel[i].setBackground(new Color(107, 106, 104));
			movieDescription[i].setForeground(Color.WHITE);
			movieDescription[i].setFont(new Font("Candara", Font.BOLD, 24));

			movieList.add(moviePanel[i]);
			spacePanel[i].setBackground(new Color(107, 106, 104));
			moviePanel[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			moviePanel[i].setBackground(new Color(107, 106, 104));
			moviePanel[i].addMouseListener(this);
			descriptionPanel[i].add(movieDescription[i], BorderLayout.WEST);
			movieDescription[i].setHorizontalAlignment(JLabel.LEFT);

			moviePanel[i].add(spacePanel[i], BorderLayout.WEST);
			moviePanel[i].add(movieLabel[i], BorderLayout.CENTER);
			moviePanel[i].add(descriptionPanel[i], BorderLayout.EAST);

		}

		return movieList;
	}
	
/**
 * This creates the seat map for the users to select from
 * @return JPanel panel for card layout
 */
	// this is the page to show the time selection list
	public JPanel seatMap() {
		JPanel seatMap = new JPanel();
		seatMap.setLayout(new BorderLayout());
		seat = new JPanel[100];
		

		JPanel[] paddingPanel = new JPanel[4];
		JPanel container = new JPanel(); // the panel with seats, padded from
											// edges
		for (int i = 0; i < 4; i++) {
			paddingPanel[i] = new JPanel();
			// paddingPanel[i].setBorder(BorderFactory.createLineBorder(Color.GRAY,
			// 1));
			paddingPanel[i].setBackground(new Color(97, 96, 94));
		}

		container.setLayout(new GridLayout(10, 10));

		// create seats

		for (int s = 0; s < 100; s++) {
			seat[s] = new JPanel();

			// hides seats that are supposed to be walking paths
			if (s >= 70 && s <= 79) {
				seat[s].setBackground(new Color(97, 96, 94));
			}

			else if (s >= 20 && s <= 29) {
				seat[s].setBackground(new Color(97, 96, 94));
			}

			else if (s == 22 || s == 32 || s == 42 || s == 52 || s == 62) {
				seat[s].setBackground(new Color(97, 96, 94));
			}

			else if (s == 27 || s == 37 || s == 47 || s == 57 || s == 67) {
				seat[s].setBackground(new Color(97, 96, 94));
			} else {
				seat[s].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				seat[s].setBackground(new Color(117, 116, 114));
				seat[s].addMouseListener(this);
			}
			
			

			container.add(seat[s]);
		}

		paddingPanel[0].setPreferredSize(new Dimension(80, 720));
		paddingPanel[1].setPreferredSize(new Dimension(1024, 80));
		paddingPanel[2].setPreferredSize(new Dimension(80, 720));
		paddingPanel[3].setPreferredSize(new Dimension(1024, 80));

		seatMap.add(paddingPanel[0], BorderLayout.WEST);
		seatMap.add(paddingPanel[1], BorderLayout.NORTH);
		seatMap.add(paddingPanel[2], BorderLayout.EAST);
		seatMap.add(paddingPanel[3], BorderLayout.SOUTH);
		seatMap.add(container, BorderLayout.CENTER);
		return seatMap;
	}

	/**
	 * 
	 * @param text to choose which card to use
	 * @return JPanel to display card
	 */
	// this just creates a nicer looking button
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
	
	
/**
 * 
 * @param text to choose which card to be displayed
 * @param text to pass text into jbutton
 * @return JPanel panel
 */
	public static JPanel createBlankPanel(String text) {
		JPanel panel = new JPanel(); // this is to take up the a row of the grid
										// in mainContentPanel
		;
		return panel;
	}

	/**
	 * main method
	 * @param args main method
	 */
	public static void main(String[] args) {
		new CinemaBooking();
	}// end main

	// Mouse Listeners
/**
 * mouseClicked event to selecrt seats and movies 
 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// change background color of selected panel in movie list
		if (e.getSource() == moviePanel[0]) {
			moviePanel[0].setBackground(new Color(148, 146, 143));
			spacePanel[0].setBackground(new Color(148, 146, 143));
			descriptionPanel[0].setBackground(new Color(148, 146, 143));

		} else {
			moviePanel[0].setBackground(new Color(107, 106, 104));
			spacePanel[0].setBackground(new Color(107, 106, 104));
			descriptionPanel[0].setBackground(new Color(107, 106, 104));

		}

		if (e.getSource() == moviePanel[1]) {
			moviePanel[1].setBackground(new Color(148, 146, 143));
			spacePanel[1].setBackground(new Color(148, 146, 143));
			descriptionPanel[1].setBackground(new Color(148, 146, 143));

		} else {
			moviePanel[1].setBackground(new Color(107, 106, 104));
			spacePanel[1].setBackground(new Color(107, 106, 104));
			descriptionPanel[1].setBackground(new Color(107, 106, 104));
		}

		if (e.getSource() == moviePanel[2]) {
			moviePanel[2].setBackground(new Color(148, 146, 143));
			spacePanel[2].setBackground(new Color(148, 146, 143));
			descriptionPanel[2].setBackground(new Color(148, 146, 143));

		} else {
			moviePanel[2].setBackground(new Color(107, 106, 104));
			spacePanel[2].setBackground(new Color(107, 106, 104));
			descriptionPanel[2].setBackground(new Color(107, 106, 104));

		}

		if (e.getSource() == moviePanel[3]) {
			moviePanel[3].setBackground(new Color(148, 146, 143));
			spacePanel[3].setBackground(new Color(148, 146, 143));
			descriptionPanel[3].setBackground(new Color(148, 146, 143));

		} else {
			moviePanel[3].setBackground(new Color(107, 106, 104));
			spacePanel[3].setBackground(new Color(107, 106, 104));
			descriptionPanel[3].setBackground(new Color(107, 106, 104));
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		for (int i = 0; i < 100; i++) {
			if (e.getSource() == seat[i]) {
			
				
				//	seat[i].setBackground(new Color(137, 136, 134));
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		pressed = new boolean[100];
	/*	for (int i = 0; i < 100; i++) {
			if (e.getSource() == seat[i]) {
				seat[i].setBackground(new Color(117, 116, 114));
			} 
			
		}*/

	}

	/**
	 * mouse pressed event for choosing movie and seat
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		pressed = new boolean[100];
		Arrays.fill(pressed, false);
		
		for (int i = 0; i < 100; i++) {
			if (e.getSource() == seat[i]) {
				pressed[i] = true;
				System.out.println("pressed at index " + i + " " + pressed[i]);
				
			}
			
			if(pressed[i] == true) {
				seat[i].setBackground(new Color(137, 136, 134));				
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}// end class
