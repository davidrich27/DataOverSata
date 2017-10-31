import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;

import javafx.fxml.FXML;

public class ControllerLogin {
	@FXML
	private Label lblStatus;
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPassword;

	public void Login(ActionEvent event) {
		if (txtUsername.getText().equals("user")&& txtPassword.getText().equals("pass")) {
			lblStatus.setText("Login Success");
		}else {
			lblStatus.setText("Login Failed");
		}
	}
}
