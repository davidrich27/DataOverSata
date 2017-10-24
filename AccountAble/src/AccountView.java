//Imports are listed in full to show what's being used
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class AccountView extends Application {

    //JavaFX applicatoin uses the main method.
    public static void main(String[] args) {
        launch(args);
    }
    
    //starting point for the application and user interface
    @Override
    public void start(Stage primaryStage) {
        
        //The primaryStage is the top-level container
        primaryStage.setTitle("AccountAble");
  
        //The BorderPane has the same areas laid out as the
        //BorderLayout layout manager
        BorderPane componentLayout = new BorderPane();
        componentLayout.setPadding(new Insets(20,20,20,20));
        
        //The FlowPane is a container that uses a flow layout
        final FlowPane choicePane = new FlowPane();
        choicePane.setHgap(10);
        Label choiceLbl = new Label("Choose Account");
        Label accntName = new Label("Account name");
        
        //The choicebox is populated from an observableArrayList
        ChoiceBox accntBox = new ChoiceBox(FXCollections.observableArrayList("P_Camp", "R_Supplies", 
        "Break Room", "BBQ", "Summer Camp"));
        
        //Add the label and choicebox to the flowpane
        choicePane.getChildren().add(choiceLbl);
        choicePane.getChildren().add(accntBox);
        choicePane.getChildren().add(accntName);
        
        //put the flowpane in the top area of the BorderPane
        componentLayout.setTop(choicePane);
        
        final FlowPane listPane = new FlowPane();
        listPane.setHgap(100);
        
        componentLayout.setCenter(listPane);
        
        
        //Button for the bottom of screen
        Button subButton = new Button("Submit");
        componentLayout.setLeft(subButton);
        
        Button tranButton = new Button("Add Transaction");
        componentLayout.setRight(tranButton);
        
        Label DevBy = new Label("Developed by Data Over Sata");
        componentLayout.setBottom(DevBy);
        
        //Add the BorderPane to the Scene
        Scene appScene = new Scene(componentLayout,500,500);
        
        //Add the Scene to the Stage
        primaryStage.setScene(appScene);
        primaryStage.show();
    }
}
