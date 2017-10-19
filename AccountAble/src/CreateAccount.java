import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.JPanel;

public class CreateAccount {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount window = new CreateAccount();
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
	public CreateAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("AccountAble");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./src/chrome2.png"));
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 850, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

    JPanel addPanel = new JPanel();
    frame.getContentPane().add(addPanel, BorderLayout.WEST);
    addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));

    //Create account name label and textfield
    JPanel namePan = new JPanel();
    addPanel.add(namePan);
    JLabel nameLabel = new JLabel("Account Name");
    namePan.add(nameLabel);
    JTextField name = new JTextField(10);
    namePan.add(name);
    //addPanel.add(Box.createVerticalStrut(20));

    //Create account balance label and textfield
    JPanel balancePan = new JPanel();
    addPanel.add(balancePan);
    JLabel balanceLabel = new JLabel("Account Balance");
    balancePan.add(balanceLabel);
    JTextField balance = new JTextField(10);
    balancePan.add(balance);
  //  addPanel.add(Box.createVerticalStrut(20));

    //Create account description label and textfield
    JPanel descPan = new JPanel();
    addPanel.add(descPan);
    JLabel descLabel = new JLabel("Account Description");
    descPan.add(descLabel);
    JTextField desc = new JTextField(10);
    descPan.add(desc);

    //Create add button
    JPanel buttonPanel = new JPanel();
    addPanel.add(buttonPanel);
    JButton addButton = new JButton("Add");
    buttonPanel.add(addButton);

    JOptionPane messagePane = new JOptionPane();
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (desc.getText().equals("") || balance.getText().equals("") || name.getText().equals("")) {
          messagePane.showMessageDialog(frame, "Error: All Fields must be filled out");
        }else{
          messagePane.showMessageDialog(frame, "Account Created!");
        }

      }
    });





    JPanel loginPanel = new JPanel(new BorderLayout(5, 5));
    JButton loginBtn = new JButton("Logout");
    loginPanel.add(loginBtn, BorderLayout.NORTH);
    loginPanel.add(new JLabel("Not You?"), BorderLayout.SOUTH);


    //Create Developed By footer
		JLabel lblNewLabel = new JLabel("Developed By: Data Over Sata");
		frame.getContentPane().add(lblNewLabel, BorderLayout.SOUTH);


	}

}
