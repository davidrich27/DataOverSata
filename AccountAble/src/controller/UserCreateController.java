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

public class UserCreateController {

    @FXML
    private TextField phoneTxt;

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

    @FXML
    private CheckBox adminChkbx;


    // ************************** Model Variables *******************************

    ModelTXT model;
    Stage thisStage;

    Stage homeStage;
    AdminController homeCtrl;

    String mode;
    User selectedUser;

    // ************************** Initialization and Wireup *********************

    public void setModel(ModelTXT model){
      this.model = model;
    }
    public void setStage(Stage thisStage){
      this.thisStage = thisStage;
      System.out.println("Starting User creator...");
    }
    public void setHome(Stage homeStage, AdminController homeCtrl){
      this.homeStage = homeStage;
      this.homeCtrl = homeCtrl;
    }
    public void setMode(String mode){
      this.mode = mode;
    }
    public void setupCreate(){
      setMode("create");
      titleLbl.setText("Create User");
      warningLbl.setVisible(false);
    }
    public void setupEdit(User selectedUser){
      setMode("edit");
      this.selectedUser = selectedUser;
      if (selectedUser != null){
        titleLbl.setText("Edit User");
        warningLbl.setVisible(false);

        usernameTxt.setText(selectedUser.getUsername());
        firstnameTxt.setText(selectedUser.getFirstName());
        lastnameTxt.setText(selectedUser.getLastName());
        phoneTxt.setText(selectedUser.getPhone());
        emailTxt.setText(selectedUser.getEmail());
      } else {
        // Hide this window and/or open error window
      }
    }
    public void setupExam(User selected){
      
    }

    // ************************** Other Events ************************************

    @FXML
    void confirmClick(ActionEvent event) {
      if (mode.equals("create")){
        confirmCreate();
      } else if (mode.equals("edit")){
        confirmEdit();
      }
      // close window


    }
    void confirmCreate(){
      // create new user
      String username = usernameTxt.getText();
      String pwd = newPwdTxt1.getText();
      String confirmPwd = newPwdTxt2.getText();
      String firstname = firstnameTxt.getText();
      String lastname = lastnameTxt.getText();
      String email = emailTxt.getText();
      String phone = phoneTxt.getText();
      Boolean admin = adminChkbx.isSelected();
      String checker = checkCreate(username, pwd, confirmPwd, firstname, lastname, email, phone);
      if(checker.equals("true")){
        model.addNewUser(username, pwd, firstname, lastname, email, phone, admin);
        thisStage.hide();
        homeCtrl.refresh();
      }
      else{
        warningLbl.setText("Error: Improper input in " + checker + " field.");
        warningLbl.setVisible(true);
      }

    }

    public String checkCreate(String username, String pwd, String confirmPwd, String firstname, String lastname, String email, String phone){
      int finSay = 0;
      if(username.matches("^.*[^a-zA-Z0-9].*$") || username.length() == 0){
        return "username";
      }
      if(pwd.contains(" ") || pwd.length() == 0){
        return "password";
      }
      if(!confirmPwd.equals(pwd) || confirmPwd.length() == 0){
        return "confirm password";
      }
      if(firstname.matches("^.*[^a-zA-Z].*$") || firstname.length() == 0){
        return "first name";
      }
      if(lastname.matches("^.*[^a-zA-Z].*$") || lastname.length() == 0){
        return "last name";
      }
      if(!email.contains("@") || email.contains(" ")){
        return "email";
      }
      if(phone.length() != 10 || phone.matches("^.*[^0-9].*$")){
        return "phone";
      }
      return "true";
    }


    void confirmEdit(){
      int id = selectedUser.getID();
      String username = usernameTxt.getText();
      String pwd = newPwdTxt1.getText();
      if (pwd.equals("")){
        pwd = selectedUser.getPwd();
      }
      String firstname = firstnameTxt.getText();
      String lastname = lastnameTxt.getText();
      String email = emailTxt.getText();
      String phone = phoneTxt.getText();
      Boolean admin = adminChkbx.isSelected();
      model.editUser(id, username, pwd, firstname, lastname, email, phone, admin);
      thisStage.hide();
      homeCtrl.refresh();
    }

    @FXML
    void cancelClick(ActionEvent event) {
      thisStage.hide();
      clear();
    }

    void clear(){
      usernameTxt.setText("");
      newPwdTxt1.setText("");
      firstnameTxt.setText("");
      lastnameTxt.setText("");
      emailTxt.setText("");
      phoneTxt.setText("");
      adminChkbx.setSelected(false);
    }

}
