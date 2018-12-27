/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connector;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author micheal
 */

public class EditDurationController implements Initializable {

    @FXML
    private JFXButton btn_submit;
    @FXML
    private JFXButton btn_Cancel;
    Connection con ;
    @FXML
    private Label lb_arrive;
    @FXML
    private JFXTextField tf_duration;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con = Connector.getConnection();
            PreparedStatement ps = con.prepareStatement("select EnterDate from guest where id=?");
            ps.setString(1, SearchController.guestId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lb_arrive.setText(rs.getString("EnterDate"));
                break;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EditDurationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void submit(ActionEvent event) {
        
        try {
            PreparedStatement ps = con.prepareStatement("update guest set duration=? where id=?");
            ps.setInt(1, Integer.parseInt(tf_duration.getText()));
            ps.setString(2, SearchController.guestId);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EditDurationController.class.getName()).log(Level.SEVERE, null, ex);
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
            System.out.println("File Not Found");
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
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
            System.out.println("File Not Found");
        }
    }
    
}
