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
import javafx.scene.control.TextField;


public class CreateAccount extends Application{
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{

		BorderPane border = new BorderPane();

		HBox header = addHeader();
		border.setTop(header);
		HBox footer = addFooter();
		border.setBottom(footer);
		VBox left = addLabels();
		border.setLeft(left);

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


	public VBox addLabels(){
		VBox vbox = new VBox();
		Text name = new Text("Account Name: ");
		name.setTranslateY(50);
		TextField nameField = new TextField("Enter Account Name");
		nameField.setTranslateY(25);
		nameField.setTranslateX(150);

		Text balance = new Text("Account Balance:          $");
		balance.setTranslateY(150);
		TextField balanceField = new TextField("Enter Account Balance");
		balanceField.setTranslateY(125);
		balanceField.setTranslateX(150);

		Text description = new Text("Account Description: ");
		description.setTranslateY(250);
		TextField descField = new TextField("Enter Account Description");
		descField.setTranslateY(225);
		descField.setTranslateX(150);
		Text info = new Text("Please Fill Out All Fields");
		Button add = new Button("Add Account");
		add.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				if(nameField.getText().equals("") || nameField.getText().equals("Enter Account Name") || balanceField.getText().equals("")
				|| balanceField.getText().equals("Enter Account Balance") || descField.getText().equals("") || descField.getText().equals("Enter Account Description")){
					info.setText("Not All Fields are Filled out");
				}else{
					info.setText("Added");
				}
			}
		});
		add.setTranslateY(350);
		info.setTranslateY(300);
		vbox.getChildren().addAll(name, nameField, balance, balanceField, description, descField, info, add);
		return vbox;
	}
}
