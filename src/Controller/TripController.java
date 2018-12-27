/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.RoomModel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author micheal
 */
public class TripController implements Initializable {
    double x,y;
    
    @FXML
    private FontAwesomeIconView icon_close;

    @FXML
    private CheckBox cb_mountain;//100cost

    @FXML
    private CheckBox cb_sea;//150cost

    @FXML
    private CheckBox cb_desert;//100cost

    @FXML
    private CheckBox cb_diving;//200

    @FXML
    private Button img_next;

    @FXML
    private Button img_back;
    public static int tripcost =0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getSceneX()-x);
        stage.setY(event.getSceneY()-y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x =event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    private void next(MouseEvent event) {
        Pane p = new Pane();
        RoomModel rm = new RoomModel();
        if(cb_mountain.isSelected()){
            rm.setTrip_cost(100);
        }
        if(cb_sea.isSelected()){
            rm.setTrip_cost(150+rm.getTrip_cost());
        }
        if(cb_desert.isSelected()){
            rm.setTrip_cost(100+rm.getTrip_cost());
        }
        if(cb_diving.isSelected()){
            rm.setTrip_cost(200+rm.getTrip_cost());
        }
        tripcost=rm.getTrip_cost();
            try {
                p = FXMLLoader.load(getClass().getResource("../View/end.fxml"));
                Scene scene = new Scene(p);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void back(MouseEvent event) {
        Pane p = new Pane();
            try {
                p = FXMLLoader.load(getClass().getResource("../View/food.fxml"));
                Scene scene = new Scene(p);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }
    
    
    
   
    
    
}
