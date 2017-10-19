import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JPanel;

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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./src/chrome2.png"));
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 850, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.EAST);
		
		JPanel CPanel = new JPanel();
		frame.getContentPane().add(CPanel, BorderLayout.WEST);

		JButton btnLogOff = new JButton("Log Off");
		panel.add(btnLogOff, BorderLayout.NORTH);
		
		JButton btnAddTransaction = new JButton("Add Transaction");
		CPanel.add(btnAddTransaction, BorderLayout.CENTER);
		
		JButton btnDeleteTransaction = new JButton("Delete Transaction");
		CPanel.add(btnDeleteTransaction, BorderLayout.CENTER);
		
	

		JLabel lblNewLabel = new JLabel("Developed By: Data Over Sata");
		frame.getContentPane().add(lblNewLabel, BorderLayout.SOUTH);
		

	}

}
