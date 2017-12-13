package controller;

import model.basic.*;
import model.manager.*;
import model.master.*;
import model.security.*;

import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.math.BigInteger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import javafx.event.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;

public class ChangeInfoController {

      @FXML
      private TextField phoneTxt;

      @FXML
      private TextField oldPwdTxt;

      @FXML
      private Button cancelBtn;

      @FXML
      private TextField usernameTxt;

      @FXML
      private TextField firstnameTxt;

      @FXML
      private TextField lastnameTxt;

      @FXML
      private Label titleLbl;

      @FXML
      private TextField newPwdTxt1;

      @FXML
      private TextField newPwdTxt2;

      @FXML
      private Button confirmBtn;

      @FXML
      private TextField emailTxt;

      @FXML
      private Label warningLbl;

  // ************************** Model Variables *******************************

    ModelTXT model;
    Stage thisStage;

    Stage homeStage;
    AdminController homeCtrl;

    User loginUser;

    // ************************** Initialization and Wireup *********************

    public void setModel(ModelTXT model){
      this.model = model;
    }
    public void setStage(Stage thisStage){
      this.thisStage = thisStage;
    }
    public void setHome(Stage homeStage, AdminController homeCtrl){
      this.homeStage = homeStage;
      this.homeCtrl = homeCtrl;
    }
    public void setupEdit(User loginUser){
      this.loginUser = loginUser;
      usernameTxt.setText(loginUser.getUsername());
      firstnameTxt.setText(loginUser.getName()[0]);
      lastnameTxt.setText(loginUser.getName()[1]);
      emailTxt.setText(loginUser.getEmail());
      phoneTxt.setText(loginUser.getPhone());

      warningLbl.setVisible(false);
    }

    // ************************** Other Events ************************************

    @FXML
    void confirmClick(ActionEvent event)
    throws NoSuchAlgorithmException, InvalidKeySpecException, PasswordHasher.InvalidHashException, PasswordHasher.CannotPerformOperationException {
      String oldPwd = oldPwdTxt.getText();
      if (!loginUser.testPwd(oldPwd)){
        warningLbl.setVisible(true);
        warningLbl.setText("Sorry, that is the wrong password.");
      } else if (!newPwdTxt1.getText().equals(newPwdTxt2.getText())){
        warningLbl.setVisible(true);
        warningLbl.setText("Sorry, your new password fields do not match.");
      } else {
        // change User Info
        int id = loginUser.getID();
        String username = loginUser.getUsername();             // don't change username
        String newPwd = loginUser.getPwd();
        if (!newPwdTxt1.getText().trim().isEmpty()){           // don't change pwd if left blank
          newPwd = newPwdTxt1.getText();
        }
        String firstname = firstnameTxt.getText();
        String lastname = lastnameTxt.getText();
        String email = emailTxt.getText();
        String phone = phoneTxt.getText();
        boolean admin = loginUser.getAdmin();
        model.editUser(id, username, newPwd, firstname, lastname, email, phone, admin);
        // close window
        thisStage.hide();
        warningLbl.setVisible(false);
        homeCtrl.refresh();
      }
    }

    @FXML
    void cancelClick(ActionEvent event) {
      thisStage.hide();
    }

}
