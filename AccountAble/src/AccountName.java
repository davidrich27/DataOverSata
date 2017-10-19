import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class AccountName {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountName window = new AccountName();
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
	public AccountName() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("AccountAble");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./src/logo.jpg"));
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 850, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel Wpanel = new JPanel();
		frame.getContentPane().add(Wpanel, BorderLayout.WEST);
		Wpanel.setLayout(new BoxLayout(Wpanel, BoxLayout.Y_AXIS));

		JPanel AccName = new JPanel();
		Wpanel.add(AccName);
		JLabel AccNameLabel = new JLabel("Account Name");
		AccName.add(AccNameLabel);
		JTextField name = new JTextField(10);
		AccName.add(name);

		JPanel RecTransPanel = new JPanel();
		Wpanel.add(RecTransPanel);
		JLabel TransLabel = new JLabel("Recent Transactions");
		RecTransPanel.add(TransLabel);
		JTextField TransText = new JTextField(20);
		RecTransPanel.add(TransText);

		JButton AddTransB = new JButton("Add Transaction");
		RecTransPanel.add(AddTransB);
		JButton ViewHistB = new JButton("View History");
		RecTransPanel.add(ViewHistB);

		JPanel Epanel = new JPanel();
		frame.getContentPane().add(Epanel, BorderLayout.WEST);
		Epanel.setLayout(new BoxLayout(Epanel, BoxLayout.Y_AXIS));

		JPanel ComUsersPanel = new JPanel();
		Epanel.add(ComUsersPanel);
		JLabel ComUsersLabel = new JLabel("Recent User");
		ComUsersPanel.add(ComUsersLabel);

		JPanel UserTextPanel = new JPanel();
		Epanel.add(UserTextPanel);
		JButton EDTransB = new JButton("Edit/Delete Transaction");
		UserTextPanel.add(EDTransB);

		JPanel Spanel = new JPanel();
		Spanel.setLayout(new BorderLayout());
		Spanel.add(new JLabel("Developed By: Data Over Sata"), BorderLayout.SOUTH);

		frame.getContentPane().add(Wpanel, BorderLayout.WEST);
		frame.getContentPane().add(Spanel, BorderLayout.SOUTH);
		frame.getContentPane().add(Epanel, BorderLayout.EAST);

	}

}
