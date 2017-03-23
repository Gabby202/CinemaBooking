import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class LoginRegister extends JFrame {

	private boolean complete = false;
	private Container c;
	private JTextField userNameInput;
	private JPasswordField userPassInput;

	private GridBagConstraints gbc = new GridBagConstraints();

	public boolean run() {
		c = getContentPane();
		c.setLayout(new BorderLayout());
		JPanel loginPanel = LoginPanel();
		JPanel registerPanel = RegisterPanel();

		c.add(loginPanel, BorderLayout.NORTH);
		c.add(registerPanel, BorderLayout.SOUTH);

		setSize(500, 300);
		setVisible(true);

		return complete;
	}
	
	private JPanel LoginPanel(){
		JPanel panel = new JPanel(new GridBagLayout());
		
		JLabel userNameLabel = new JLabel("User name: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(userNameLabel, gbc);

		userNameInput = new JTextField(50);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(userNameInput, gbc);

		JLabel userPassLabel = new JLabel("Password: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(userPassLabel, gbc);

		userPassInput = new JPasswordField(25);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(userPassInput, gbc);

		JButton loginButton = new JButton("Login");
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(loginButton, gbc);
		
		return panel;
	}
	
	private JPanel RegisterPanel(){
		JPanel panel = new JPanel();
		
		return panel;
	}
}
