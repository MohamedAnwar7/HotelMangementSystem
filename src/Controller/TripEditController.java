/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connector;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author MUHAMMED ANWAR
 */
public class TripEditController implements Initializable {

    @FXML
    private FontAwesomeIconView icon_close;
    @FXML
    private CheckBox cb_mountain;
    @FXML
    private CheckBox cb_sea;
    @FXML
    private CheckBox cb_desert;
    @FXML
    private CheckBox cb_diving;
    @FXML
    private Button img_next;
    @FXML
    private Button img_back;

    Connection con ;
    private int tripCost = 0 ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Connector.getConnection();
    }    

    @FXML
    private void exit(MouseEvent event) {
    }

    @FXML
    private void next(MouseEvent event) {
        if(cb_mountain.isSelected()){
            tripCost += 100;
        }
        if(cb_sea.isSelected()){
            tripCost += 100;
        }
        if(cb_desert.isSelected()){
            tripCost += 100;
        }
        if(cb_diving.isSelected()){
            tripCost += 100;
        }

        if (tripCost != 0) {
            try {
                PreparedStatement ps = con.prepareStatement("update room set TripCost=? where RoomID=?");
                ps.setInt(1, tripCost);
                ps.setInt(2, SearchController.room_Id);
                ps.execute();
            } catch (SQLException ex) {
                Logger.getLogger(FoodEditController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Pane p = new Pane();
            try {
                p = FXMLLoader.load(getClass().getResource("../View/Exist.fxml"));
                Scene scene = new Scene(p);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void back(MouseEvent event) {
        Pane p = new Pane();
            try {
                p = FXMLLoader.load(getClass().getResource("../View/Exist.fxml"));
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
    private void dragged(MouseEvent event) {
    }

    @FXML
    private void pressed(MouseEvent event) {
    }
    
}
