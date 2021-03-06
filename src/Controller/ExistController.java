/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author micheal
 */
public class ExistController implements Initializable {

    
    @FXML
    private AnchorPane main_pane;
    @FXML
    private JFXButton edit_food;
    @FXML
    private JFXButton edit_trip;
    @FXML
    private JFXButton edit_duration;
    @FXML
    private JFXButton btn_done;
    @FXML
    private Label lb_roomnum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    lb_roomnum.setText(SearchController.room_Id+"");

    }    

    @FXML
    private void food(ActionEvent event) {
         Pane p = new Pane();
        try {
            p = FXMLLoader.load(getClass().getResource("../View/foodEdit.fxml"));
            Scene scene = new Scene(p);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("File Not Found");
        }
    }

    @FXML
    private void trip(ActionEvent event) {
         Pane p = new Pane();
        try {
            p = FXMLLoader.load(getClass().getResource("../View/tripEdit.fxml"));
            Scene scene = new Scene(p);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("File Not Found");
        }
    }

    @FXML
    private void duration(ActionEvent event) {
          Pane p = new Pane();
        try {
            p = FXMLLoader.load(getClass().getResource("../View/EditDuration.fxml"));
            Scene scene = new Scene(p);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("File Not Found");
        }
    }

    @FXML
    private void done(ActionEvent event) {
        System.exit(0);
    }
    
}
