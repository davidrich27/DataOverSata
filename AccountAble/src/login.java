import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class login extends JFrame {

	JButton blogin;
	JPanel loginpanel;
	JTextField txuser;
	JTextField pass;
	JButton newUSer;
	JLabel username;
	JLabel password;

	public login() { 
		super("AccountAble Login");
		setResizable(false);
		loginpanel = new JPanel();

		setSize(300, 300);
		setLocation(500, 280);
		username = new JLabel("User - ");
		username.setBounds(20, 28, 80, 20);
		loginpanel.add(username);

		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/chrome2.png"));
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Developed By: Data Over Sata");
		getContentPane().add(lblNewLabel, BorderLayout.SOUTH);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		txuser = new JTextField(15);

		txuser.setBounds(70, 30, 150, 20);
		loginpanel.add(txuser);
		password = new JLabel("Pass - ");
		password.setBounds(20, 63, 80, 20);
		loginpanel.add(password);
		pass = new JPasswordField(15);
		pass.setBounds(70, 65, 150, 20);
		loginpanel.add(pass);

		blogin = new JButton("Login");
		blogin.setBounds(110, 100, 80, 20);

		loginpanel.add(blogin);

		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File file = new File("userPass.txt");
					Scanner scan = new Scanner(file);
					;
					String line = null;
					FileWriter filewrite = new FileWriter(file, true);

					String usertxt = " ";
					String passtxt = " ";
					String puname = txuser.getText();
					String ppaswd = pass.getText();

					while (scan.hasNext()) {
						usertxt = scan.nextLine();
						passtxt = scan.nextLine();

						if (puname.equals(usertxt) && ppaswd.equals(passtxt)) {
							// Initial view Frame here
							InitialView postlogin = new InitialView();
							dispose();
						} else if (puname.equals("") && ppaswd.equals("")) {
							JOptionPane.showMessageDialog(null, "Please insert Username and Password");
						} else {

							JOptionPane.showMessageDialog(null, "Wrong Username / Password");
							txuser.setText("");
							pass.setText("");
							txuser.requestFocus();
						}

					}

				} catch (IOException d) {
					d.printStackTrace();
				}

			}
		});

		getContentPane().add(loginpanel);
		newUSer = new JButton("New User?");
		newUSer.setBounds(110, 135, 80, 20);
		loginpanel.add(newUSer);

		newUSer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewUser user = new NewUser();
				dispose();

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		Writer writer = null;
		File check = new File("userPass.txt");
		if (check.exists()) {

			// Checks if the file exists. will not add anything if the file does
			// exist.
		} else {
			try {
				File texting = new File("userPass.txt");
				writer = new BufferedWriter(new FileWriter(texting));
				writer.write("message");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}