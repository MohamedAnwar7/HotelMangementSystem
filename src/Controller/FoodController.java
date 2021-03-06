
package Controller;

import Model.RoomModel;
import com.jfoenix.controls.JFXCheckBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
 * @author micheal
 */
public class FoodController implements Initializable {

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
    public static int foodcost=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    //breakfast30
    //launch50
    //dinner50
   
    @FXML
    private void next(MouseEvent event) {
        
        Pane p = new Pane();
        RoomModel rm = new RoomModel();

        if(ch_break_fast.isSelected()){
            rm.setFood_cost(30+rm.getFood_cost());
        }
        if(ch_lunch.isSelected()){
            rm.setFood_cost(50+rm.getFood_cost());
        }
        if(ch_dinner.isSelected()){
            rm.setFood_cost(50+rm.getFood_cost());
        }
        foodcost = rm.getFood_cost();
            try {
                p = FXMLLoader.load(getClass().getResource("../View/Trip.fxml"));
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
                p = FXMLLoader.load(getClass().getResource("../View/Reservation.fxml"));
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
