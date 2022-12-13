
package projet.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AjouterUtilisateurController implements Initializable {
    
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
    private TextField txtCin;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom; 
    @FXML
    private TextField txtMdp;
    @FXML
            
            
            
    void Ajouter(ActionEvent event) {
        String CIN,nom,prenom,email,mdp,role;
        CIN=txtCin.getText();
        nom=txtNom.getText();
        prenom=txtPrenom.getText();
        email=txtEmail.getText();
        mdp=txtMdp.getText();
        role="Resp";
        if(validerForm(email)){
            try{
                pst=con.prepareStatement("insert into user(CIN,nom,prenom,Email,role,password) values(?,?,?,?,?,?)");
                pst.setString(1, CIN);
                pst.setString(2, nom);
                pst.setString(3, prenom);
                pst.setString(4, email);
                pst.setString(5, role);
                pst.setString(6, mdp);
                pst.executeUpdate();


                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");

                alert.setHeaderText("Ajout d'un utilisateur ");
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
            
        }else{
            System.out.println(" Form Invalide ");
        }
    }

    @FXML
    void Effacer(ActionEvent event) {
        resetTextField();
    }

    @FXML
    void Modifier(ActionEvent event) {
        String CIN,nom,prenom,email,mdp;
        CIN=txtCin.getText();
        nom=txtNom.getText();
        prenom=txtPrenom.getText();
        email=txtEmail.getText();
        mdp=txtMdp.getText();
        if(validerForm(email)){
            try{
                pst=con.prepareStatement("update user set nom = ?,prenom = ? ,Email = ?,password = ? where CIN = ? ");
                pst.setString(5, CIN);
                pst.setString(1, nom);
                pst.setString(2, prenom);
                pst.setString(3, email);
                pst.setString(4, mdp);
                pst.executeUpdate();


                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modification");

                //alert.setHeaderText("Ajout d'un utilisateur ");
                alert.setContentText("MODIFICATION AVEC SUCCES !");

                alert.showAndWait();
                
                resetTextField();
                Retourner(event);

            }catch(SQLException e){
                System.out.println("Error de MISE A JOUR ");
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE,null,e);
            }catch(Exception e){
                System.out.println(" Autre Erreur de UPDATE! "+e.getMessage());
                e.printStackTrace();
            }
            
        }else{
            System.out.println(" Form Invalide ");
        }
    }

    @FXML
    void Retournerr(ActionEvent event) throws IOException{
        System.out.println("Gestion des Utilisateurs !");
        Stage logp = new Stage();
        globalPane.getScene().getWindow().hide();

    }

    @FXML
    void Supprimer(ActionEvent event) {
        if(txtCin.getText().length()!=8){
            labelError.setText("CIN invalide !");
        }else{
            labelError.setText("");
            String CIN=txtCin.getText();
            try
            {
                pst = con.prepareStatement("delete from user where CIN = ? ");
                pst.setString(1, CIN);
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Suppresion de l'Utilisateur");

                //alert.setHeaderText("Student Registation");
                alert.setContentText("Un utilisateur a été supprimé");

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
    
    
    
    boolean validerForm(String email){
        if(vide()){
            labelError.setText("(*) Tous les champs sont obligatoires !");
            return false;
        }else if(!valideEmail(email)){
            labelError.setText("L'adresse email est invalide !");
            return false;
        }else if( txtMdp.getText().length()<3){
            labelError.setText("Mot de passe doit etre au moins 3 caractères !");
            return false;
        }else if( txtCin.getText().length()!=8){
            labelError.setText("CIN doit etre 8 caractères !");
            return false;
        }else{
            labelError.setText("");
            return true;
        }
        
    }
    
    boolean valideEmail(String email){
        String regx = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE); 
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    boolean vide(){
        if(txtCin.getText().equals("") ||txtNom.getText().equals("")||txtPrenom.getText().equals("") ||txtEmail.getText().equals("") ||txtMdp.getText().equals("")   ){
            return true;
        }else{
            return false;
        }
        
    }

    void resetTextField(){
        txtCin.setText("");
        txtNom.setText("");
        txtPrenom.setText("");
        txtEmail.setText("");
        txtMdp.setText("");
        labelError.setText("");
    }
    
    void setTextField(String cin,String nom,String prenom,String email,String mdp) {
        txtCin.setText(cin);
        txtNom.setText(nom);
        txtPrenom.setText(prenom);
        txtEmail.setText(email);
        txtMdp.setText(mdp);
    }
    
    @FXML
    void Retourner(ActionEvent event){
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("../View/GestionUtilisateur.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestionUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        GestionUtilisateurController gestionUser = loader.getController();
        gestionUser.table();
        globalPane.getScene().getWindow().hide();
        /*Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        stage.setResizable(false);*/
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
