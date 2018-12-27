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
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author micheal
 */
public class SearchController implements Initializable {

    @FXML
    private JFXButton btn_login;
    @FXML
    private ImageView img_back;

   public static int room_Id = 0;
   public static String guestId = " ";
    Connection con;
    @FXML
    private JFXTextField tf_search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Connector.getConnection();
    }

    @FXML
    private void login(ActionEvent event) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT  `RoomID` FROM `guest` WHERE id = ?");
            ps.setString(1, tf_search.getText());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                room_Id = rs.getInt("RoomID");
            }
            guestId = tf_search.getText();
        } catch (SQLException ex) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("Enter Valid ID ");
            al.setHeaderText("Invalid ID");
            al.show();
        }
        if (room_Id == 0) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setContentText("Enter Valid ID ");
            al.setHeaderText("Invalid ID");
            al.show();
        } else {
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

    @FXML
    private void back(MouseEvent event) {
        Pane p = new Pane();
        try {
            p = FXMLLoader.load(getClass().getResource("../View/guest.fxml"));
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
