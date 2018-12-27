package Controller;

import Model.Connector;
import Model.GuestModel;
import Model.RoomModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ReservationController implements Initializable {

    @FXML
    private JFXTextField tf_first;
    @FXML
    private JFXTextField tf_last;
    @FXML
    private JFXTextField tf_id;
    @FXML
    private JFXRadioButton rd_male;
    @FXML
    private JFXRadioButton rd_female;
    @FXML
    private JFXTextField tf_address;
    @FXML
    private JFXComboBox<String> cb_status;
    @FXML
    private JFXTextField tf_number_adult;
    @FXML
    private JFXTextField tf_number_children;
    @FXML
    private JFXTextField tf_email;
    @FXML
    private JFXTextField tf_phone;
    @FXML
    private JFXComboBox<String> cb_room_type;
    @FXML
    private JFXComboBox<Integer> cb_room_number;
    @FXML
    private JFXDatePicker enter_date;
    @FXML
    private ImageView img_back;
    @FXML
    private ImageView img_next;
    @FXML
    private JFXTextField tf_country;
    @FXML
    private JFXTextField tf_duration;
    Connection con=null;
    public static int roomcost =0;
    public static int roomid;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Connector.getConnection();
        tf_number_children.setVisible(true);
        ToggleGroup tg = new ToggleGroup();
        rd_male.setToggleGroup(tg);
        rd_female.setToggleGroup(tg);
        cb_status.getItems().add("Single");
        cb_status.getItems().add("Married");
        cb_room_type.getItems().addAll("single", "double", "trible");
        
        
    }

    @FXML
    private void room_available(MouseEvent event) {// show available rooms_id in combobox
        
        RoomModel RM = new RoomModel();
         RM.setRoom_type(" ");
            cb_room_number.getItems().clear();

        if(!(cb_room_type.getSelectionModel().isEmpty())){
            
           
      
            try {
                 con = Connector.getConnection();
                PreparedStatement ps = con.prepareStatement("select RoomID from room where  available= 1 AND room_type='"+cb_room_type.getValue()+"';");
                ResultSet rs = ps.executeQuery();
         
                while(rs.next()){
                    RM.setRoom_id(rs.getInt("RoomID"));
                    
                    cb_room_number.getItems().add(RM.getRoom_id());
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
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
        }
    }
    private void insert(){
        try {
            RoomModel rm = new RoomModel();
            GuestModel gm = new GuestModel();
            gm.setName(tf_first.getText()+tf_last.getText());
            gm.setId(tf_id.getText());
            
            if(rd_male.isSelected()){
                gm.setGender(rd_male.getText());
            }else{
                gm.setGender(rd_female.getText());
            }
            gm.setMail(tf_email.getText());
            gm.setPhone(tf_phone.getText());
            gm.setCountry(tf_country.getText());
            rm.setRoom_type(cb_room_type.getValue());
            gm.setStatus(cb_status.getValue());
            gm.setNum_child(Integer.parseInt(tf_number_children.getText()));
            gm.setRoom_id(cb_room_number.getValue());
            gm.setEnter_date(Date.valueOf(enter_date.getValue()));
            //
            gm.setDuration(Integer.parseInt(tf_duration.getText()));
            rm.setAvailable(0);
            rm.setRoom_id(cb_room_number.getValue());
            
            
            
            
            
           PreparedStatement ps =con.prepareStatement("INSERT INTO `guest`(`id`, `FullName`, `Phone`, `Gender`, `Country`, `Status`, `NumberChildren`, `RoomID`, `EnterDate`, `duration`, `Email`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,gm.getId());
            ps.setString(2, gm.getName());
            ps.setString(3, gm.getPhone());
            ps.setString(4, gm.getGender());
            ps.setString(5, gm.getCountry());
            ps.setString(6, gm.getStatus());
            ps.setInt(7, gm.getNum_child());
            ps.setInt(8, gm.getRoom_id());
            ps.setDate(9, gm.getEnter_date());
            ps.setInt(10, gm.getDuration());
            ps.setString(11,gm.getMail());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void next(MouseEvent event) {//store data in the model and move to the next frame 
       roomid=cb_room_number.getValue();
       // RoomModel rm = new RoomModel();
         insert();
          Pane p = new Pane();
            try {
                PreparedStatement ps = con.prepareStatement("select room_cost from room_cost where room_type='"+cb_room_type.getValue()+"'");
                ResultSet rs =ps.executeQuery();
                while(rs.next()){
                roomcost=rs.getInt("room_cost");
                }
                p = FXMLLoader.load(getClass().getResource("../View/food.fxml"));
                Scene scene = new Scene(p);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void status_action(ActionEvent event) {
        if (cb_status.getValue() == "Single") {
            tf_number_children.setVisible(false);
        } else {
            tf_number_children.setVisible(true);
        }
    }

    private boolean isNull(TextField tf) {
        return tf.getText().trim().isEmpty();
    }

}
