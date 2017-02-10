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

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CinemaBooking extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	JPanel mainContentPanel = new JPanel();
	JPanel cardPanel1 = buildCardPanel(1);
	JPanel cardPanel2 = buildCardPanel(2);
	JPanel cardPanel3 = buildCardPanel(3);

	JPanel buttonsPanel;
	JButton nextButton;
	JButton backButton;

	// movie list method arrays made global to give mouse listener methods scope
	JPanel moviePanel[], spacePanel[], descriptionPanel[];
	JLabel movieLabel[], movieDescription[];
	Icon movieImage[];


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
		}

		buttonsPanel.add(backButton);
		buttonsPanel.add(nextButton);

		panel.add(card1Label, BorderLayout.NORTH);

		panel.add(buttonsPanel, BorderLayout.SOUTH);

		// Returns the completed panel
		return panel;

	}

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
		
		movieImage[0] = new ImageIcon("src/images/the_hobbit1.jpg");
		movieImage[1] = new ImageIcon("src/images/captain_america1.jpg");
		movieImage[2] = new ImageIcon("src/images/hunger_games1.jpg");
		movieImage[3] = new ImageIcon("src/images/star_wars1.jpg");
		
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
			moviePanel[i]. add(descriptionPanel[i], BorderLayout.EAST);
			
		}
		
		return movieList;
	}

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

	public static JPanel createBlankPanel(String text) {
		JPanel panel = new JPanel(); // this is to take up the a row of the grid
										// in mainContentPanel
		;
		return panel;
	}

	public static void main(String[] args) {
		new CinemaBooking();
	}// end main

	// Mouse Listeners

	@Override
	public void mouseClicked(MouseEvent e) {
		// change background color of selected panel in movie list
		if (e.getSource() == moviePanel[0]) {
			moviePanel[0].setBackground(new Color(148, 146, 143));
			spacePanel[0].setBackground(new Color(148, 146, 143));
			descriptionPanel[0].setBackground(new Color(148, 146, 143));


		}else{
			moviePanel[0].setBackground(new Color(107, 106, 104));
			spacePanel[0].setBackground(new Color(107, 106, 104));
			descriptionPanel[0].setBackground(new Color(107, 106, 104));

		}
		
		if(e.getSource() == moviePanel[1]){
			moviePanel[1].setBackground(new Color(148, 146, 143));
			spacePanel[1].setBackground(new Color(148, 146, 143));
			descriptionPanel[1].setBackground(new Color(148, 146, 143));

		}else{
			moviePanel[1].setBackground(new Color(107, 106, 104));
			spacePanel[1].setBackground(new Color(107, 106, 104));
			descriptionPanel[1].setBackground(new Color(107, 106, 104));
		}
		
		if (e.getSource() == moviePanel[2]) {
			moviePanel[2].setBackground(new Color(148, 146, 143));
			spacePanel[2].setBackground(new Color(148, 146, 143));
			descriptionPanel[2].setBackground(new Color(148, 146, 143));

		}else{
			moviePanel[2].setBackground(new Color(107, 106, 104));
			spacePanel[2].setBackground(new Color(107, 106, 104));
			descriptionPanel[2].setBackground(new Color(107, 106, 104));

		}
		
		if(e.getSource() == moviePanel[3]){
			moviePanel[3].setBackground(new Color(148, 146, 143));
			spacePanel[3].setBackground(new Color(148, 146, 143));
			descriptionPanel[3].setBackground(new Color(148, 146, 143));

		}else{
			moviePanel[3].setBackground(new Color(107, 106, 104));
			spacePanel[3].setBackground(new Color(107, 106, 104));
			descriptionPanel[3].setBackground(new Color(107, 106, 104));
		}
		
		
			
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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
