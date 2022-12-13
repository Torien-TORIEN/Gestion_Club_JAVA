
package projet.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AjouterSalleController implements Initializable {

     @FXML
    private ComboBox<String> CombLocal;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnEffacer;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnSupprimer;
    @FXML
    private AnchorPane globalPane;
    @FXML
    private Label labelError;
    @FXML
    private TextArea txtDes;
    @FXML
    private TextField txtNum;
    
    
    @FXML
    void Ajouter(ActionEvent event) {
        String local=CombLocal.getItems().get(0);
        String num=txtNum.getText();
        String des=txtDes.getText();
        if(!vide()){
            connect();
            try{
                pst=con.prepareStatement("insert into salle(Numero,local,description) values(?,?,?)");
                pst.setString(1, num);
                pst.setString(2, local);
                pst.setString(3, des);
                pst.executeUpdate();


                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("GESTION DES SALLES");

                alert.setHeaderText("Ajout d'une Salle ");
                alert.setContentText("AJOUT AVEC SUCCES !");

                alert.showAndWait();
                
                System.out.println("Ajout");
                resetTextField();
                Retourner(event);

            }catch(SQLException e){
                System.out.println("Error Ajout");
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE,null,e);
            }catch(Exception e){
                System.out.println(" Autre Erreur ! "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    void Effacer(ActionEvent event) {
        resetTextField();
    }

    @FXML
    void Modifier(ActionEvent event) {
        String local=CombLocal.getItems().get(1);
        String num=txtNum.getText();
        String des=txtDes.getText();
        if(!vide()){
            connect();
            try{
                pst=con.prepareStatement("update salle set local=?, description=? where Numero=?");
                pst.setString(3, num);
                pst.setString(1, local);
                pst.setString(2, des);
                pst.executeUpdate();


                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("GESTION DES SALLES");

                //alert.setHeaderText("Ajout d'une Salle ");
                alert.setContentText("MISE A JOUR AVEC SUCCES !");

                alert.showAndWait();
                resetTextField();
                Retourner(event);

            }catch(SQLException e){
                System.out.println("Error Ajout");
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE,null,e);
            }catch(Exception e){
                System.out.println(" Autre Erreur ! "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    void Retourner(ActionEvent event) {
        System.out.println("Gestion des Salles !");
        Stage logp = new Stage();
        globalPane.getScene().getWindow().hide();
    }

    @FXML
    void Supprimer(ActionEvent event) {
        if(txtNum.getText().length()<3){
            labelError.setText("Numero de la salle  invalide !");
        }else{
            labelError.setText("");
            String num=txtNum.getText();
            try
            {
                pst = con.prepareStatement("delete from salle where Numero = ? ");
                pst.setString(1, num);
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Suppresion de la salle");

                //alert.setHeaderText("Student Registation");
                alert.setContentText("Avec Succes");

                alert.showAndWait();
                //-------------------Initialiser --------------
                resetTextField();
                Retourner(event);

            }
            catch (SQLException ex){
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    
    
    boolean vide(){
        try{
            if(CombLocal.getValue().equals(null)||txtNum.getText().equals("") ||txtDes.getText().length()<10){
                labelError.setText("Champs invalides !");
                return true;
            }else{
                labelError.setText("");
                return false;
            }
        }catch(Exception e){
            System.out.println("Execption :"+e.getMessage());
            labelError.setText("Veuillez choisir la localisation !");
            return true;
        }
    }
    
    
    void setTextField(String num,int local,String des) {
        txtNum.setText(num);
        CombLocal.getItems().get(local);
        txtDes.setText(des);
    }
    
    void resetTextField() {
        txtNum.setText("");
        CombLocal.getItems().get(0);
        txtDes.setText("");
        labelError.setText("");
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CombLocal.setItems( FXCollections.observableArrayList("Annexe","Principal"));
        connect();
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
    
}
