import java.awt.EventQueue;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class CreateAccount extends Application{
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		//primaryStage.setTitle("Title");
	//	Button button = new Button();
	//	button.setText("Click");

	//	BorderPane layout = new BorderPane();
	//	HBox hbox = addHBox();
	//	layout.setTop(hbox);
		//VBox left = new VBox();
		//layout.setLeft(left);
		//layout.setLeft(addVBox());
		//left.getChildren().add(button);
		//layout.getChildren().add(button);
		/*addVBox();

		Scene scene = new Scene(layout, 800, 800);
		primaryStage.setScene(scene);
		primaryStage.show();*/
		BorderPane border = new BorderPane();

		HBox header = addHeader();
		border.setTop(header);
		HBox footer = addFooter();
		border.setBottom(footer);

		Scene scene = new Scene(border, 800, 800);
		primaryStage.setScene(scene);
		primaryStage.show();


	}
	public HBox addHeader(){
		HBox hbox = new HBox();
		Button b1 = new Button();
		b1.setText("Logout");
		b1.setTranslateX(592);
		Button b2 = new Button();
		b2.setText("Go Back");
		b2.setTranslateX(590);

		Text title = new Text("AccountAble");
		title.setTranslateX(350);
		hbox.getChildren().addAll(title,b2,b1);


		return hbox;

	}

	public HBox addFooter(){
		HBox hbox = new HBox();
		Text title = new Text("Developed by Data Over Sata");
		title.setTranslateX(325);
		hbox.getChildren().add(title);
		return hbox;
	}


	/*public VBox addVBox(){
		VBox vbox = new VBox();
	}*/
}



	/*public static void main(String[] args) {
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
	/*public CreateAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
/*	private void initialize() {
		frame = new JFrame("AccountAble");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./src/logo.jpg"));
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 850, 650);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

}*/
