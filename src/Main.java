import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	private JTextField txtPassword;
	private JTextField txtPasswordLength;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 484, 361);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JCheckBox chckbxNumbers = new JCheckBox("Numbers");
		chckbxNumbers.setFont(new Font("Tahoma", Font.BOLD, 16));
		chckbxNumbers.setBounds(83, 248, 172, 33);
		panel.add(chckbxNumbers);
		
		JCheckBox chckbxLetters = new JCheckBox("Letters");
		chckbxLetters.setFont(new Font("Tahoma", Font.BOLD, 16));
		chckbxLetters.setBounds(83, 200, 172, 33);
		panel.add(chckbxLetters);
		
		JCheckBox chckbxSpecialChar = new JCheckBox("Special Characters");
		chckbxSpecialChar.setFont(new Font("Tahoma", Font.BOLD, 16));
		chckbxSpecialChar.setBounds(257, 200, 191, 33);
		panel.add(chckbxSpecialChar);
		
		JCheckBox chckbxCapitals = new JCheckBox("Capitals");
		chckbxCapitals.setFont(new Font("Tahoma", Font.BOLD, 16));
		chckbxCapitals.setBounds(257, 248, 172, 33);
		panel.add(chckbxCapitals);
		
		JButton btnCopy = new JButton("Copy");
		btnCopy.setBounds(335, 100, 67, 33);
		panel.add(btnCopy);
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = txtPassword.getText();
				
				Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
				StringSelection strPassword = new StringSelection(password);
				clip.setContents(strPassword, strPassword);
				JOptionPane.showMessageDialog(null ,"Text is copied!");
			}
		});
		
		txtPassword = new JTextField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtPassword.setBounds(128, 100, 196, 33);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password Generator");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setBounds(128, 31, 235, 33);
		panel.add(lblNewLabel);
		
		txtPasswordLength = new JTextField();
		txtPasswordLength.setHorizontalAlignment(SwingConstants.CENTER);
		txtPasswordLength.setBounds(171, 166, 43, 27);
		panel.add(txtPasswordLength);
		txtPasswordLength.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password Length");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(228, 166, 135, 27);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Generate Password");
		btnNewButton_1.setBounds(171, 300, 172, 34);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int length = Integer.valueOf(txtPasswordLength.getText());
				String password = genPassword(length);
				txtPassword.setText(password);
			}
			
			public String genPassword(int length) {
				String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				String lowerCase = "abcdefghijklmnopqrstuvwxyz";
				String number = "0123456789";
				String specialChar = "!@#$%^&*()_+{}";
				
				if (!chckbxLetters.isSelected() && !chckbxNumbers.isSelected() && !chckbxSpecialChar.isSelected()) {
					return new String("");
				}
				if (!chckbxLetters.isSelected()) {
					upperCase = "";
					lowerCase = "";
				}
				if (!chckbxCapitals.isSelected()) {
					upperCase = "";
				}
				if (!chckbxNumbers.isSelected()) {
					number = "";
				}
				if (!chckbxSpecialChar.isSelected()) {
					specialChar = "";
				}
				
				String combination = upperCase + lowerCase + number + specialChar;
				Random rand = new Random();
				StringBuilder sb = new StringBuilder();
				
				for (int i = 0; i < length; i++) {
					sb.append(combination.charAt(rand.nextInt(combination.length())));
				}
				
				return new String(sb);
			}
		});
		
	}
	
	
}
