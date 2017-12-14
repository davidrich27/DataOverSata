package controller;

import model.basic.*;
import model.manager.*;
import model.master.*;
import model.security.*;

import java.util.*;
import java.nio.charset.*;
import java.nio.file.*;

import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import javafx.event.*;
import javafx.scene.paint.Color;

public class SchemeController {

    @FXML
    private ColorPicker fontPicker;

    @FXML
    private ColorPicker backgroundPicker;

    @FXML
    private ColorPicker menuPicker;

    @FXML
    private ColorPicker fontBgPicker;

    @FXML
    private Button cancelBtn;

    @FXML
    private Label titleLbl;

    @FXML
    private Button default1Btn;

    @FXML
    private Button confirmBtn;

    @FXML
    private Label warningLbl;

    @FXML
    private ColorPicker buttonPicker;

    @FXML
    private Button default4Btn;

    @FXML
    private Button default2Btn;

    @FXML
    private Button default3Btn;

    // ************************** Model Variables *******************************

    ModelTXT model;
    Stage thisStage;

    Stage homeStage;
    AdminController homeCtrl;

    String stylePath = "styles/current_style.css";

    String[] defaultColor1 = {"#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF"};
    String[] defaultColor2 = {"#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF"};
    String[] defaultColor3 = {"#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF"};
    String[] defaultColor4 = {"#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF"};

    //*********************** Initialization and Wireup **************************

    public void initialize() {
      warningLbl.setText("");
    }

    public void setDataModel(ModelTXT model){
      this.model = model;
    }
    public ModelTXT getDataModel(){
      return model;
    }
    public void setHome(Stage homeStage, AdminController homeCtrl){
      this.homeStage = homeStage;
      this.homeCtrl = homeCtrl;
    }
    public void setStage(Stage thisStage){
      this.thisStage = thisStage;
    }

    public String toRGBstr (Color color) {

      return "rgb(" + Math.floor(color.getRed()*255) + "," + Math.floor(color.getGreen()*255) + "," + Math.floor(color.getBlue()*255) + ")";
    }

    //************************* Button Events *********************************************

    @FXML
    void confirmClick(ActionEvent event) {
      // Find and Replace old colors with new colors
      Color newBgColor = backgroundPicker.getValue();
      Color newFontColor = fontPicker.getValue();
      Color newMenuColor = menuPicker.getValue();
      Color newBtnColor = buttonPicker.getValue();
      Color newFontBgColor = fontBgPicker.getValue();

      try {
        Path readPath = Paths.get("styles/template_style.css");
        Path writePath = Paths.get("styles/current_style.css");

        Charset charset = StandardCharsets.UTF_8;

        String content = new String(Files.readAllBytes(readPath), charset);
        content = content.replace("$background-color$", toRGBstr(newBgColor));
        content = content.replace("$text-color$", toRGBstr(newFontColor));
        content = content.replace("$main-color$", toRGBstr(newMenuColor));
        content = content.replace("$button-color$", toRGBstr(newBtnColor));
        content = content.replace("$text-bg-color$", toRGBstr(newFontBgColor));
        Files.write(writePath, content.getBytes(charset));
      } catch(IOException e) {
        e.printStackTrace();
      }
      // Update stylesheets
      thisStage.getScene().getStylesheets().clear();
      thisStage.getScene().getStylesheets().add(stylePath);
      homeStage.getScene().getStylesheets().clear();
      homeStage.getScene().getStylesheets().add(stylePath);
    }

    @FXML
    void cancelClick(ActionEvent event) {
      thisStage.hide();
    }

    @FXML
    void default1Click(ActionEvent event) {
      backgroundPicker.setValue(Color.web("#424958"));
      fontPicker.setValue(Color.web("#FFFF00"));
      menuPicker.setValue(Color.web("#0000FF"));
      buttonPicker.setValue(Color.web("#455A64"));
      fontBgPicker.setValue(Color.web("#000000"));
    }

    @FXML
    void default2Click(ActionEvent event) {
      backgroundPicker.setValue(Color.web("#20303D"));
      fontPicker.setValue(Color.web("#000000"));
      menuPicker.setValue(Color.web("#DDD9D6"));
      buttonPicker.setValue(Color.web("#6C707B"));
      fontBgPicker.setValue(Color.web("#000000"));
    }

    @FXML
    void default3Click(ActionEvent event) {
      backgroundPicker.setValue(Color.web("#5E001D"));
      fontPicker.setValue(Color.web("#DDD9D6"));
      menuPicker.setValue(Color.web("#8c8e90"));
      buttonPicker.setValue(Color.web("#8c8e90"));
      fontBgPicker.setValue(Color.web("#fbfbf5"));
    }

    @FXML
    void default4Click(ActionEvent event) {
      backgroundPicker.setValue(Color.web("#1c2240"));
      fontPicker.setValue(Color.web("#9399a9"));
      menuPicker.setValue(Color.web("#232b40"));
      buttonPicker.setValue(Color.web("#5f6473"));
      fontBgPicker.setValue(Color.web("#939989"));
    }

}
