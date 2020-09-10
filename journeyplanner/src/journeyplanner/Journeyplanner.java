/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package journeyplanner;


//Imports for JavaFX and IOException handling
import java.io.IOException;
import javafx.application.Application;                                  
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Niroshan
 */
public class Journeyplanner extends Application {
     
     
     @Override
     
    public void start(Stage stage) throws Exception {
        //Linking the XML sheet containing the markup
        Parent root = FXMLLoader.load(getClass().getResource("JourneyPlannerDesign.fxml"));
        //Definition of the stage and scene
        Scene scene = new Scene(root);    
        stage.setScene(scene);
        stage.show();
        //Setting the Title of the window
        stage.setTitle("Journey Planner");
      
    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
             //XMLDocumentController.getIn();
        launch(args);

    
    }
    
}
