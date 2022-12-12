
package projet.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class loginController implements Initializable{
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void UserLogin(ActionEvent event) throws IOException {
        String mdp=password.getText();
        String email=username.getText();
        System.out.println("Login : username="+email +" et password ="+mdp);
        if(email.equals("torien1227@gmail.com")&& mdp.equals("123")){
            System.out.println("Succes Login Admin");    
            Stage logp = new Stage();
            anchorPane.getScene().getWindow().hide();
            Parent rootA;
            rootA = FXMLLoader.load(getClass().getResource("../View/Admin.fxml"));
            Scene scene = new Scene(rootA);
            logp.setScene(scene);
            logp.show();
            logp.setResizable(false);
        

        }else if(email.equals("houssem@gmail.com")&& mdp.equals("123")){
            System.out.println("Succes Login Responsable");    
            Stage logp = new Stage();
            anchorPane.getScene().getWindow().hide();
            Parent rootA;
            rootA = FXMLLoader.load(getClass().getResource("../View/Responsable.fxml"));
            Scene scene = new Scene(rootA);
            logp.setScene(scene);
            logp.show();
            logp.setResizable(false);
        }else{
            System.out.println("Echec Login ");
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
}
