package controller;

import model.basic.*;
import model.manager.*;
import model.master.*;

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

public class UserEditController {

    @FXML
    private TextField phoneTxt;

    @FXML
    private TextField oldPwdTxt;

    @FXML
    private Button cancelBtn;

    @FXML
    private Label sorryLbl;

    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField firstnameTxt;

    @FXML
    private TextField lastnameTxt;

    @FXML
    private TextField newPwdTxt1;

    @FXML
    private TextField newPwdTxt2;

    @FXML
    private Button confirmBtn;

    @FXML
    private TextField emailTxt;

  // ************************** Model Variables *******************************

    ModelTXT model;
    Stage thisStage;
    Stage homeStage;

    User loginUser;

    // ************************** Initialization and Wireup *********************

    public void setModel(ModelTXT model){
      this.model = model;
    }
    public void setStage(Stage thisStage){
      this.thisStage = thisStage;
    }
    public void setHomeStage(Stage homeStage){
      this.homeStage = homeStage;
    }
    public void populate(User loginUser){
      this.loginUser = loginUser;
      usernameTxt.setText(loginUser.getUsername());
      firstnameTxt.setText(loginUser.getName()[0]);
      lastnameTxt.setText(loginUser.getName()[1]);
      emailTxt.setText(loginUser.getEmail());
      phoneTxt.setText(loginUser.getPhone());

      sorryLbl.setVisible(false);
    }

    // ************************** Other Events ************************************

    @FXML
    void confirmClick(ActionEvent event) {
      String pwd = loginUser.getPwd();
      if (pwd == oldPwdTxt.getText()){
        // change User Info
        String newPwd = newPwdTxt1.getText();
        String firstname = firstnameTxt.getText();
        String lastname = lastnameTxt.getText();
        String email = emailTxt.getText();
        String phone = phoneTxt.getText();
        // close window
        thisStage.hide();
      } else {
        sorryLbl.setVisible(true);
      }
    }

    @FXML
    void cancelClick(ActionEvent event) {
      thisStage.hide();
    }

}
