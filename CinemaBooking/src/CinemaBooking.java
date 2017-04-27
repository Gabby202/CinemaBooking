import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * This class houses the gui and most of the functions
 * 
 * @author Gabby
 *
 */
@SuppressWarnings("serial")
public class CinemaBooking extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	DBConnection conn = new DBConnection();
	
	Vector<Movie> movies = conn.getMovies();

	JPanel mainContentPanel = new JPanel();
	JPanel cardPanel1 = buildCardPanel(1);
	JPanel cardPanel2 = buildCardPanel(2);
	JPanel cardPanel3 = buildCardPanel(3);
	JPanel cardPanel4 = buildCardPanel(4);
	JPanel cardPanel5 = buildCardPanel(5);

	JPanel buttonsPanel;
	JButton nextButton, backButton, submitPayment, submitSeats, clearSeats, navButtons[];
	JTextField nameField, surNameField, add1, add2, cardNo, cvv;

	// movie list method arrays made global to give mouse listener methods scope
	JPanel moviePanel[], spacePanel[], descriptionPanel[], seat[];
	JLabel movieLabel[], movieDescription[], movieChoice, timeChoice, seatChoice, totalPrice, paymentDetails[],
			testLabel;
	JComboBox<?> times[];
	String selectedMovie;
	Icon movieImage[];
	boolean[] pressed;
	boolean[] moviePressed;
	int timeSelected;
	Payment payment = new Payment();
	Seat seats = new Seat();

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
		leftColNav.setLayout(new GridLayout(12 ,1));

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
		leftColTitle.setHorizontalAlignment(JLabel.CENTER);
		leftColTitle.setVerticalAlignment(JLabel.NORTH);
		// Add Components ============================================
	
		
		titlePanel.add(title);
		leftColNav.add(leftColTitle);
		navButtons = new JButton[5];
		String[] navStrings = {"Home" , "Movies", "Seats", "Payment", "Receipt"};
		for(int i = 0; i < 5; i++) {
			navButtons[i] = new JButton(navStrings[i]);
			formatButton(navButtons[i]);
			navButtons[i].addActionListener(this);
			leftColNav.add(navButtons[i]);
		}
		
		// cards to mainContentPane
		mainContentPanel.add(cardPanel1, "home");
		mainContentPanel.add(cardPanel2, "movies");
		mainContentPanel.add(cardPanel3, "seats");
		mainContentPanel.add(cardPanel4, "payment");
		mainContentPanel.add(cardPanel5, "receipt");

		containerPanel.add(titlePanel, BorderLayout.NORTH);
		containerPanel.add(leftColNav, BorderLayout.WEST);
		containerPanel.add(mainContentPanel);

		c.add(containerPanel);
		setSize(1024, 720);
		setVisible(true);
		setTitle("Cinema Booking");

	}// end constructor

	/**
	 * action performed for navigation buttons
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == navButtons[0]) {
			CardLayout card = (CardLayout) mainContentPanel.getLayout();
			card.show(mainContentPanel, "home");
		} else if(e.getSource() == navButtons[1]) {
			CardLayout card = (CardLayout) mainContentPanel.getLayout();
			card.show(mainContentPanel, "movies");
		} else if(e.getSource() == navButtons[2]) {
			CardLayout card = (CardLayout) mainContentPanel.getLayout();
			card.show(mainContentPanel, "seats");
		} else if(e.getSource() == navButtons[3]) {
			CardLayout card = (CardLayout) mainContentPanel.getLayout();
			card.show(mainContentPanel, "payment");
		} else if(e.getSource() == navButtons[4]) {
			CardLayout card = (CardLayout) mainContentPanel.getLayout();
			card.show(mainContentPanel, "receipt");
		}
	}

	/**
	 * 
	 * @param panelNumber panel int 
	 * @return panel
	 */
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
		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) mainContentPanel.getLayout();
				card.previous(mainContentPanel);
			}

		});
		nextButton = createButton("Next");
		nextButton.addActionListener(new ActionListener() {

			/**
			 * action performed for seats
			 */
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) mainContentPanel.getLayout();
				card.next(mainContentPanel);
				
				for (int i = 0; i < 100; i++) {
					// hides seats that are supposed to be walking paths
					if (i >= 70 && i <= 79) {
						seat[i].setBackground(new Color(97, 96, 94));
					}

					else if (i >= 20 && i <= 29) {
						seat[i].setBackground(new Color(97, 96, 94));
					}

					else if (i == 22 || i == 32 || i == 42 || i == 52 || i == 62) {
						seat[i].setBackground(new Color(97, 96, 94));
					}

					else if (i == 27 || i == 37 || i == 47 || i == 57 || i == 67) {
						seat[i].setBackground(new Color(97, 96, 94));
					} else {
						seat[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
						for(int j = 0; j < movies.size(); j++){
							if(movies.elementAt(j).getTitle().equals(selectedMovie)){
								if(movies.elementAt(j).getSeat(timeSelected - 1, i) == 1){
									seat[i].setBackground(Color.red);
								}else{
									seat[i].setBackground(new Color(117, 116, 114));
								}
							}
						}
					}
				}
			}

		});

		// changes method depending on which panel
		if (panelNumber == 1) {
			panel.add(home());

			card1Label.setText("Home");
		} else if (panelNumber == 2) {
			panel.add(movieList());
			card1Label.setText("Select Your Movie");
		} else if (panelNumber == 3) {
			panel.add(seatMap());
			card1Label.setText("Select Your Seat");
		} else if (panelNumber == 4) {
			panel.add(paymentInfo());
			card1Label.setText("Payment Information");
		} else if (panelNumber == 5) {
			panel.add(receipt());
			card1Label.setText("Receipt");
		}

		buttonsPanel.add(backButton);
		buttonsPanel.add(nextButton);

		panel.add(card1Label, BorderLayout.NORTH);

		panel.add(buttonsPanel, BorderLayout.SOUTH);

		// Returns the completed panel
		return panel;

	}

	/**
	 * 
	 * @return panel
	 */
	public JPanel receipt() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(10, 1));
		formatPanel(panel);
		panel.add(new JLabel());
		paymentDetails = new JLabel[6];
		for (int i = 0; i < paymentDetails.length; i++) {

			paymentDetails[i] = new JLabel();
			formatLabel(paymentDetails[i]);
			paymentDetails[i].setHorizontalAlignment(JLabel.CENTER);
			panel.add(paymentDetails[i]);
		}

		return panel;

	}

	/**
	 * 
	 * @return panel
	 */
	public JPanel home() {

		JPanel panel = new JPanel();
		formatPanel(panel);
		panel.setLayout(new BorderLayout());
		JLabel title = new JLabel("Please Click The ''Next'' Button To Begin Booking!");
		title.setFont(new Font("Candara", Font.BOLD, 32));
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		panel.add(title, BorderLayout.CENTER);
		return panel;
	}

	/**
	 * 
	 * @return panel
	 */
	public JPanel paymentInfo() {
		JPanel paymentInfo = new JPanel();
		paymentInfo.setLayout(new GridLayout(1, 1)); // makes the mainContent
														// panel fill all the
														// space
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new GridLayout(6, 1));

		JLabel nameLabel = new JLabel("Name: ");

		formatLabel(nameLabel);
		nameField = new JTextField(16);
		surNameField = new JTextField(16);
		add1 = new JTextField(16);
		add2 = new JTextField(16);
		cardNo = new JTextField(16);
		cvv = new JTextField(16);

		String stringLabels[] = { "Name", "Surname", "Address", "Address 2 ", "CardNo", "Cvv" };
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
		fields[5].add(cvv);

		for (int i = 0; i < panels.length; i++) {
			fields[i] = new JTextField();
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

			if (i > 0 && i < 5) {
				labelPanel[i].setLayout(new GridLayout(2, 1));
				fieldPanel[i].setLayout(new GridLayout(2, 1));
				labelPanel[i].setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 20));
				labelPanel[i].setPreferredSize(new Dimension(20, 0));
			}

			labelPanel[i].setPreferredSize(new Dimension(20, 0));

			// spacePanel[i].setPreferredSize(new Dimension(68, 0));
			fieldPanel[i].setPreferredSize(new Dimension(550, 0));

			fieldPanel[i].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 200)); // moves
																					// components

			// panels[i].add(spacePanel[i], BorderLayout.WEST);
			panels[i].add(labelPanel[i], BorderLayout.CENTER);
			panels[i].add(fieldPanel[i], BorderLayout.EAST);
			mainContent.add(panels[i]);

			labels[i].setText(stringLabels[i]);
			labels[i].setForeground(Color.WHITE);

			formatLabel(labels[i]);

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
		submitPayment = new JButton("Submit Detials");
		formatButton(submitPayment);
		submitPayment.addActionListener(new ActionListener() {
			/**
			 * action performed for form 
			 */
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == submitPayment) {

					if (nameField.getText().length() < 1) {
						JOptionPane.showMessageDialog(null, "Enter Valid Name");

					} else {

						payment.setFirstName(nameField.getText());
						paymentDetails[0].setText(payment.getFirstName());

					}

					if (surNameField.getText().length() < 1) {
						JOptionPane.showMessageDialog(null, "Enter Valid Surname");

					} else {
						payment.setSurName(surNameField.getText());
						paymentDetails[1].setText(payment.getSurName());
					}

					if (add1.getText().length() < 1) {
						JOptionPane.showMessageDialog(null, "Enter Address");

					} else {
						payment.setAddress1(add1.getText());
						paymentDetails[2].setText(payment.getAddress1());
					}

					payment.setAddress2(add2.getText());
					paymentDetails[3].setText(payment.getAddress2());

					if (cardNo.getText().length() < 16 || cardNo.getText().length() > 16) {

						try {
							long l = Long.valueOf(cardNo.getText());
							System.out.println("long" + l);
							JOptionPane.showMessageDialog(null, "Enter Valid Card Number");

						} catch (Exception ex) {
							System.out.println("not number");
							JOptionPane.showMessageDialog(null, "Enter Valid Card Number");
						}

					} else {
						payment.setCardNo(Long.parseLong(cardNo.getText()));
						paymentDetails[4].setText("Card Number: " + Long.toString(payment.getCardNo()));
					}

					if (cvv.getText().length() > 3 || cvv.getText().length() < 3) {
						try {
							int i = Integer.valueOf(cvv.getText());
							System.out.println("integer" + i);
						} catch (Exception ex) {
							System.out.println("not number");
							JOptionPane.showMessageDialog(null, "not a valid CVV Number");
						}

					} else {

						payment.setCvv(Integer.parseInt(cvv.getText()));
						paymentDetails[5].setText("CVV: " + Integer.toString(payment.getCvv()));
					}
					
					for(int j = 0; j < movies.size(); j++){
						if(movies.elementAt(j).getTitle().equals(selectedMovie)){
							movies.elementAt(j).setSeat(timeSelected - 1, seats.getSeat(), 1);
						}
					}
					
					conn.setMovies(movies);

				}
			}
		});
		fieldPanel[4].add(submitPayment);

		innerFieldPanels[0].add(nameField);
		innerFieldPanels[1].add(surNameField);
		innerFieldPanels[2].add(add1);
		innerFieldPanels[3].add(add2);
		innerFieldPanels[4].add(cardNo);
		innerFieldPanels[5].add(cvv);

		for (int x = 0; x < 6; x++) {
			innerFieldPanels[x].setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 100));
		}

		labelPanel[0].setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		fieldPanel[0].setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 400));

		JLabel reviewLabel = new JLabel("Review: ");
		formatLabel(reviewLabel);
		labelPanel[5].add(reviewLabel);

		formatLabel(movieChoice);
		formatLabel(timeChoice);
		formatLabel(seatChoice);
		formatLabel(totalPrice);

		formatLabel(movieChoice);
		formatLabel(timeChoice);
		formatLabel(seatChoice);
		formatLabel(totalPrice);

		fieldPanel[5].setLayout(new GridLayout(4, 1));
		fieldPanel[5].add(movieChoice);
		fieldPanel[5].add(timeChoice);
		fieldPanel[5].add(seatChoice);
		fieldPanel[5].add(totalPrice);
		

		paymentInfo.add(mainContent);
		mainContent.setBackground(new Color(107, 106, 104));
		return paymentInfo;
	}

	/**
	 * 
	 * @param panel panel 
	 * @return panel
	 */
	public JPanel formatPanel(JPanel panel) {

		panel.setBackground(new Color(107, 106, 104));
		panel.setForeground(Color.WHITE);
		// panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		return panel;
	}

	public JLabel formatLabel(JLabel label) {

		label.setForeground(Color.WHITE);
		label.setFont(new Font("Candara", Font.BOLD, 16));

		return label;
	}

	/**
	 * This creates the list of movies for selection
	 * 
	 * @return JPanel panels to be used in cards
	 */
	// method for showing list of movies
	public JPanel movieList() {

		JPanel movieList = new JPanel();
		movieList.setLayout(new GridLayout(movies.size(), 1));
		moviePanel = new JPanel[movies.size()];
		movieLabel = new JLabel[movies.size()];
		movieImage = new ImageIcon[movies.size()];
		spacePanel = new JPanel[movies.size()];
		descriptionPanel = new JPanel[movies.size()];
		movieDescription = new JLabel[movies.size()];
		times = new JComboBox[movies.size()];

		movieList.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		for (int i = 0; i < movieImage.length; i++) {
			movieImage[i] = movies.elementAt(i).getImg();
		}

		for (int i = 0; i < movieDescription.length; i++) {
			movieDescription[i] = new JLabel(movies.elementAt(i).getTitle());
		}

		GridBagConstraints gbc = new GridBagConstraints();

		for (int i = 0; i < movies.size(); i++) {
			moviePanel[i] = new JPanel(new GridBagLayout());

			movieLabel[i] = new JLabel(movieImage[i]);
			spacePanel[i] = new JPanel();
			descriptionPanel[i] = new JPanel();

			movieLabel[i].setHorizontalAlignment(JLabel.LEFT);
			spacePanel[i].setPreferredSize(new Dimension(68, 0));
			descriptionPanel[i].setPreferredSize(new Dimension(538, 0));
			descriptionPanel[i].setLayout(new BorderLayout());
			descriptionPanel[i].setBackground(new Color(107, 106, 104));
			movieDescription[i].setForeground(Color.WHITE);
			movieDescription[i].setFont(new Font("Candara", Font.BOLD, 20));

			movieList.add(moviePanel[i]);
			spacePanel[i].setBackground(new Color(107, 106, 104));
			moviePanel[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			moviePanel[i].setBackground(new Color(107, 106, 104));
			moviePanel[i].addMouseListener(this);
			descriptionPanel[i].add(movieDescription[i], BorderLayout.WEST);
			movieDescription[i].setHorizontalAlignment(JLabel.LEFT);

			String timesList[] = new String[movies.elementAt(i).getTimes().length + 1];
			timesList[0] = "Select Times";
			for (int j = 1; j < movies.elementAt(i).getTimes().length + 1; j++) {
				timesList[j] = movies.elementAt(i).getTimes()[j - 1];
			}
			times[i] = new JComboBox<String>(timesList);
			times[i].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					for(int i = 0; i < times.length; i++){
						if(times[i].isEnabled()){
							timeSelected = times[i].getSelectedIndex();
							System.out.println(timeSelected);
						}
					}
				}	
			});
			times[i].setEnabled(false);

			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 1;
			moviePanel[i].add(spacePanel[i], gbc);
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.weightx = 1;
			moviePanel[i].add(movieLabel[i], gbc);
			gbc.gridx = 2;
			gbc.gridy = 0;
			gbc.weightx = 1;
			gbc.gridwidth = 2;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			moviePanel[i].add(descriptionPanel[i], gbc);
			gbc.gridx = 4;
			gbc.gridy = 0;
			gbc.weightx = 1;
			gbc.gridwidth = 1;
			gbc.fill = GridBagConstraints.NONE;
			moviePanel[i].add(times[i], gbc);

		}

		return movieList;
	}

	/**
	 * This creates the seat map for the users to select from
	 * 
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

		submitSeats = new JButton("Submit");
		clearSeats = new JButton("Clear");

		formatButton(submitSeats);
		formatButton(clearSeats);
		seatChoice = new JLabel("Seat: ");
		movieChoice = new JLabel("Movie: ");
		timeChoice = new JLabel("Time: ");
		totalPrice = new JLabel("Total :");
		pressed = new boolean[100];
		Arrays.fill(pressed, false);

		submitSeats.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == submitSeats) {
					
					System.out.println(seats.getSeat());
					seatChoice.setText("Seat Number: " + seats.getSeat());
					totalPrice.setText("Total: €9.45");
					movieChoice.setText("Movie: " + selectedMovie);
					
					for(int i = 0; i < movies.size(); i++){
						if(movies.elementAt(i).getTitle().equals(selectedMovie)){
							timeChoice.setText("Time: " + times[i].getSelectedItem());
						}
					}
				}
			}
		});

		clearSeats.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == clearSeats) {
					for (int i = 0; i < 100; i++) {
						// hides seats that are supposed to be walking paths
						if (i >= 70 && i <= 79) {
							seat[i].setBackground(new Color(97, 96, 94));
						}

						else if (i >= 20 && i <= 29) {
							seat[i].setBackground(new Color(97, 96, 94));
						}

						else if (i == 22 || i == 32 || i == 42 || i == 52 || i == 62) {
							seat[i].setBackground(new Color(97, 96, 94));
						}

						else if (i == 27 || i == 37 || i == 47 || i == 57 || i == 67) {
							seat[i].setBackground(new Color(97, 96, 94));
						} else {
							seat[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
							for(int j = 0; j < movies.size(); j++){
								if(movies.elementAt(j).getTitle().equals(selectedMovie)){
									if(movies.elementAt(j).getSeat(timeSelected - 1, i) == 1){
										seat[i].setBackground(Color.red);
									}else{
										seat[i].setBackground(new Color(117, 116, 114));
									}
								}
							}
						}
					}
				}
			}

		});
		paddingPanel[3].add(clearSeats);
		paddingPanel[3].add(submitSeats);

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
				for(int i = 0; i < movies.size(); i++){
					if(movies.elementAt(i).getTitle().equals(selectedMovie)){
						if(movies.elementAt(i).getSeat(timeSelected - 1, s) == 1){
							seat[s].setBackground(Color.red);
						}else{
							seat[s].setBackground(new Color(117, 116, 114));
						}
					}else{
						seat[s].setBackground(new Color(117, 116, 114));
					}
				}
				//seat[s].setBackground(new Color(117, 116, 114));
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
	 * @param text
	 *            to choose which card to use
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

	public JButton formatButton(JButton button) {
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
	 * @param text
	 *            to choose which card to be displayed
	 * @param text
	 *            to pass text into jbutton
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
	 * 
	 * @param args
	 *            main method
	 */
	public static void main(String[] args) {
		CinemaBooking frame = new CinemaBooking();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}// end main

	// Mouse Listeners -
	/**
	 * mouseClicked event to selecrt seats and movies
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		moviePressed = new boolean[movies.size()];
		
		// change background color of selected panel in movie list
		for(int i = 0; i < movies.size(); i++){
			if (e.getSource() == moviePanel[i]) {
				moviePanel[i].setBackground(new Color(148, 146, 143));
				spacePanel[i].setBackground(new Color(148, 146, 143));
				descriptionPanel[i].setBackground(new Color(148, 146, 143));
				// movieChoice.setText(movieLabel[2].getText());

				for (int j = 0; j < times.length; j++) {
					if (j == i) {
						times[j].setEnabled(true);
					} else {
						times[j].setSelectedIndex(0);
						times[j].setEnabled(false);
					}
				}

				Arrays.fill(moviePressed, false);
				moviePressed[i] = true;

				System.out.println("Movie " + i + " = " + moviePressed[i]);
				selectedMovie = movies.elementAt(i).getTitle();

			} else {
				moviePanel[i].setBackground(new Color(107, 106, 104));
				spacePanel[i].setBackground(new Color(107, 106, 104));
				descriptionPanel[i].setBackground(new Color(107, 106, 104));
				// movieChoice.setText("");

			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	/**
	 * mouse pressed event for choosing movie and seat
	 */
	@Override
	/**
	 * change colours of seats
	 */
	public void mousePressed(MouseEvent e) {
		
		pressed = new boolean[100];
		Arrays.fill(pressed, false);
		
		for (int i = 0; i < 100; i++) {
			// hides seats that are supposed to be walking paths
			if (i >= 70 && i <= 79) {
				seat[i].setBackground(new Color(97, 96, 94));
			}

			else if (i >= 20 && i <= 29) {
				seat[i].setBackground(new Color(97, 96, 94));
			}

			else if (i == 22 || i == 32 || i == 42 || i == 52 || i == 62) {
				seat[i].setBackground(new Color(97, 96, 94));
			}

			else if (i == 27 || i == 37 || i == 47 || i == 57 || i == 67) {
				seat[i].setBackground(new Color(97, 96, 94));
			} else {
				seat[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				for(int j = 0; j < movies.size(); j++){
					if(movies.elementAt(j).getTitle().equals(selectedMovie)){
						if(movies.elementAt(j).getSeat(timeSelected - 1, i) == 1){
							seat[i].setBackground(Color.red);
						}else{
							seat[i].setBackground(new Color(117, 116, 114));
						}
					}
				}
			}
		}
		
		for (int i = 0; i < 100; i++) {
			if (e.getSource() == seat[i]) {
				if(i != 0){
					for(int j = 0; j < movies.size(); j++){
						if(movies.elementAt(j).getTitle().equals(selectedMovie)){
							if(movies.elementAt(j).getSeat(timeSelected - 1, i) == 0){
								pressed[i] = true;
								System.out.println("pressed at index " + i + " " + pressed[i]);
							}
						}
					}
				}
			}

			if (pressed[i] == true) {
				seats.setSeat(i);
				seat[i].setBackground(new Color(137, 136, 134));
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}// end class
