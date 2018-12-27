/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connector;
import Model.GuestModel;
import Model.RoomModel;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author micheal
 */
public class EndController implements Initializable {

      @FXML
    private Button end_btn;

    @FXML
    private Label lb_roomcost;

    @FXML
    private Label lb_foodcost;

    @FXML
    private Label lb_tripcost;

    @FXML
    private Label lb_totalcost;

    /**
     * Initializes the controller class.
     */
    RoomModel rm = new RoomModel();
    GuestModel gm = new GuestModel();
    Connection con =null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Connector.getConnection();
        lb_roomcost.setText((ReservationController.roomcost)+"");
        lb_foodcost.setText(FoodController.foodcost+"");
        lb_tripcost.setText(TripController.tripcost+"");
        rm.setTotal_cost((ReservationController.roomcost)+(FoodController.foodcost)+(TripController.tripcost));
        lb_totalcost.setText(rm.getTotal_cost()+"");
          try {
              PreparedStatement ps = con.prepareStatement("update room SET FoodCost =?, TripCost=? ,Payment=?,available=?  where RoomID =?");
              ps.setInt(1, FoodController.foodcost);
              ps.setInt(2, TripController.tripcost);
              ps.setInt(3,rm.getTotal_cost());
              ps.setInt(4, 0);
              ps.setInt(5, ReservationController.roomid);
              ps.execute();
          } catch (SQLException ex) {
              Logger.getLogger(EndController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }  
  

    @FXML
    private void end(ActionEvent event) {   
         
              
              
              System.exit(0);
         
    }
    
}
