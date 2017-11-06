
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtUsername"
	private TextField txtUsername; // Value injected by FXMLLoader

	@FXML // fx:id="txtPassword"
	private PasswordField txtPassword; // Value injected by FXMLLoader

	@FXML
	void checkLogin(ActionEvent event) {

		String user = "CSAdmin";
		String pw = "CSCI323";
		String checkUser, checkPw;

		checkUser = txtUsername.getText().toString();
		checkPw = txtPassword.getText().toString();
		if (checkUser.equals(user) && checkPw.equals(pw)) {
			new ViewInitial();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Login Error");
			alert.setHeaderText("Incorrect Username/Password Combination");
			alert.setContentText("Please try again");

			alert.showAndWait();
		}
	}

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {
		assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'LoginView.fxml'.";
		assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'LoginView.fxml'.";

	}
}
