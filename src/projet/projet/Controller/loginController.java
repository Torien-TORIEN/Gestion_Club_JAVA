
package projet.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class loginController implements Initializable{
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label labelError;
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
        
        if(email.equals("")|| mdp.equals("")){
            labelError.setText("(*) Tous les champs sont obligatoires !");
        }else{
            connect();
            labelError.setText("");
            
            try{
                pst=con.prepareStatement("select * from User where email=? and password=?");
                pst.setString(1, email);
                pst.setString(2, mdp);
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()){
                    
                    String role=rs.getString("role");
                    System.out.println(role);
                    if(role.equals("Admin")){
                        
                        System.out.println("Succes Login Admin");    
                        Stage logp = new Stage();
                        anchorPane.getScene().getWindow().hide();
                        Parent rootA;
                        rootA = FXMLLoader.load(getClass().getResource("../View/Admin.fxml"));
                        Scene scene = new Scene(rootA);
                        logp.setScene(scene);
                        logp.show();
                        logp.setResizable(false);   
                    }else{
                        System.out.println("Succes Login Responsable");    
                        Stage logp = new Stage();
                        anchorPane.getScene().getWindow().hide();
                        Parent rootA;
                        rootA = FXMLLoader.load(getClass().getResource("../View/Responsable.fxml"));
                        Scene scene = new Scene(rootA);
                        logp.setScene(scene);
                        logp.show();
                        logp.setResizable(false);
                    }
                    
                }else{
                    labelError.setText("Aucun compte est trouvé !!");
                }
                
            }catch(SQLException e){
                System.out.println("Error SQL "+e.getMessage());
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE,null,e);
            }catch(Exception e){
                System.out.println(" Autre Erreur ! "+e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    
    
    
    
    
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    public void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/gestion_des_clubs","root","");
            System.out.println("La connexion est bien établie !");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connect();
    } 
}
