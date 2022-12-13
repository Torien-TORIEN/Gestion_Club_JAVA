
package projet.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import projet.Model.Utilisateur;


public class GestionUtilisateurController implements Initializable {
    
    
    @FXML
    private TableView<Utilisateur> table;
    @FXML
    private TableColumn<Utilisateur,String> CinCln;
    @FXML
    private TableColumn<Utilisateur,String> EmailCln;
    @FXML
    private TableColumn<Utilisateur,String> NomCln;
    @FXML
    private TableColumn<Utilisateur,String> PrenomCln;
    @FXML
    private TableColumn<Utilisateur,String> RoleCln;
    
   
    @FXML
    private BorderPane GlobalPane;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnGestClub;
    @FXML
    private Button btnGestEvent;
    @FXML
    private Button btnGestMat;
    @FXML
    private Button btnGestSal;
    @FXML
    private Button btnGestUser;
    
      @FXML
    void refresh(MouseEvent event) {
        table();
    }
    
    @FXML
    void Ajouter(ActionEvent event) throws IOException {
        System.out.println("Ajouter formulaire !");
        Stage logp = new Stage();
        //GlobalPane.getScene().getWindow().hide();
        Parent rootA;
        rootA = FXMLLoader.load(getClass().getResource("../View/ajouterUtilisateur.fxml"));
        Scene scene = new Scene(rootA);
        logp.setScene(scene);
        logp.initStyle(StageStyle.UTILITY);
        logp.show();
        
        logp.setResizable(false);
    }       
            
            
            
            
            
    
    

    @FXML
    void gestionClub(ActionEvent event) throws IOException {
        System.out.println("Gestion des Clubs !");
        Stage logp = new Stage();
        GlobalPane.getScene().getWindow().hide();
        Parent rootA;
        rootA = FXMLLoader.load(getClass().getResource("../View/GestionClub.fxml"));
        Scene scene = new Scene(rootA);
        logp.setScene(scene);
        logp.show();
        logp.setResizable(false);
    }

    @FXML
    void gestionEvenement(ActionEvent event) throws IOException {
        System.out.println("Gestion des Evenements !");
        Stage logp = new Stage();
        GlobalPane.getScene().getWindow().hide();
        Parent rootA;
        rootA = FXMLLoader.load(getClass().getResource("../View/Admin.fxml"));
        Scene scene = new Scene(rootA);
        logp.setScene(scene);
        logp.show();
        logp.setResizable(false);
    }

    @FXML
    void gestionMateriel(ActionEvent event) throws IOException {
        System.out.println("Gestion des Materiels !");
        Stage logp = new Stage();
        GlobalPane.getScene().getWindow().hide();
        Parent rootA;
        rootA = FXMLLoader.load(getClass().getResource("../View/GestionMateriel.fxml"));
        Scene scene = new Scene(rootA);
        logp.setScene(scene);
        logp.show();
        logp.setResizable(false);

    }

    @FXML
    void gestionSalle(ActionEvent event) throws IOException {
        System.out.println("Gestion des Salles !");
        Stage logp = new Stage();
        GlobalPane.getScene().getWindow().hide();
        Parent rootA;
        rootA = FXMLLoader.load(getClass().getResource("../View/GestionSalle.fxml"));
        Scene scene = new Scene(rootA);
        logp.setScene(scene);
        logp.show();
        logp.setResizable(false);
    }

    @FXML
    void gestionUtilisateur(ActionEvent event) throws IOException {
        System.out.println("Gestion des Utilisateurs !");
        Stage logp = new Stage();
        GlobalPane.getScene().getWindow().hide();
        Parent rootA;
        rootA = FXMLLoader.load(getClass().getResource("../View/GestionUtilisateur.fxml"));
        Scene scene = new Scene(rootA);
        logp.setScene(scene);
        logp.show();
        logp.setResizable(false);

    }
    
    
    
    
    //----------------------------------------------
    public void table(){
        connect();
        ObservableList<Utilisateur> utilisateurs = FXCollections.observableArrayList();
        try{
           pst = con.prepareStatement("select * from user");  
           ResultSet rs = pst.executeQuery();
            {
            while (rs.next()){
                Utilisateur st = new Utilisateur();
                st.setCIN(rs.getInt("CIN"));
                st.setNom(rs.getString("nom"));
                st.setPrenom(rs.getString("prenom"));
                st.setRole(rs.getString("role"));
                st.setEmail(rs.getString("Email"));
                st.setMdp(rs.getString("password"));
                //System.out.println("email : "+st.getEmail());
                utilisateurs.add(st);
            }
            }
                //refreshTable();
                table.setItems(utilisateurs);
                
                CinCln.setCellValueFactory(new PropertyValueFactory<>("CIN"));
                NomCln.setCellValueFactory(new PropertyValueFactory<>("nom"));
                PrenomCln.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                RoleCln.setCellValueFactory(new PropertyValueFactory<>("role"));
                EmailCln.setCellValueFactory(new PropertyValueFactory<>("Email"));
 
       }catch (SQLException ex){
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
        //--------------------------------------- mettre dans le formulaire ---------------------------//
        table.setRowFactory( tv -> {
            TableRow<Utilisateur> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->{
                if (event.getClickCount() == 1 && (!myRow.isEmpty())){
                    myIndex =  table.getSelectionModel().getSelectedIndex();
                    //System.out.println(myIndex);
                    
                    int CIN = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getCIN()));
                    String nom=table.getItems().get(myIndex).getNom();
                    String prenom=table.getItems().get(myIndex).getPrenom();
                    String email=table.getItems().get(myIndex).getEmail();
                    String mdp=table.getItems().get(myIndex).getMdp();
                    Afficher(CIN, nom,prenom,email,mdp);
                }
            });
            return myRow;
        });
    }
    
//-----------------------------------------------------
    private void refreshTable() {
        try {
            utilisateursList.clear();
            pst = con.prepareStatement("select * from user");
             ResultSet resultSet = pst.executeQuery();
            
            while (resultSet.next()){
                utilisateursList.add(new  Utilisateur(
                        resultSet.getInt("CIN"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("role"),
                        resultSet.getString("Email"),
                        resultSet.getString("password")));
                table.setItems(utilisateursList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
	

    
    
    
    

    
    void Afficher(int cin,String nom,String prenom,String email,String mdp){
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("../View/ajouterUtilisateur.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestionUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }

        AjouterUtilisateurController addStudentController = loader.getController();
        addStudentController.setTextField(String.valueOf(cin),nom,prenom,email,mdp);
        
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        stage.setResizable(false);
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connect();
        table();
    }
    
    Connection con;
    PreparedStatement pst;
    ObservableList<Utilisateur> utilisateursList = FXCollections.observableArrayList();
    int myIndex;
    int id;
    public void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/gestion_des_clubs","root","");
            System.out.println(this.getClass().getSimpleName()+"La connexion est bien établie !");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
