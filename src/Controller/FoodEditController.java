/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connector;
import com.jfoenix.controls.JFXCheckBox;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MUHAMMED ANWAR
 */
public class FoodEditController implements Initializable {

    @FXML
    private JFXCheckBox ch_break_fast;
    @FXML
    private JFXCheckBox ch_lunch;
    @FXML
    private JFXCheckBox ch_dinner;
    @FXML
    private ImageView img_next;
    @FXML
    private ImageView img_back;

    Connection con;
    private int foodCost = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Connector.getConnection();
    }

    @FXML
    private void next(MouseEvent event) {
        if (ch_break_fast.isSelected()) {
            foodCost += 50;
        }
        if (ch_lunch.isSelected()) {
            foodCost += 50;
        }
        if (ch_dinner.isSelected()) {
            foodCost += 50;
        }

        if (foodCost != 0) {
            try {
                PreparedStatement ps = con.prepareStatement("update room set FoodCost=? where RoomID=?");
                ps.setInt(1, foodCost);
                ps.setInt(2, SearchController.room_Id);
                ps.execute();
            } catch (SQLException ex) {
                Logger.getLogger(FoodEditController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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

}
