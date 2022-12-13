
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projet.Model.MyException;

import projet.Model.Salle;
import projet.Model.Utilisateur;


public class GestionSalleController implements Initializable {
   
    @FXML
    private BorderPane GlobalPane;
    @FXML
    private Button btnGestClub;
    @FXML
    private Button btnGestEvent;
    @FXML
    private Button btnGestMat;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnGestSal;
    @FXML
    private Button btnGestUser;
    
    @FXML
    private TableView<Salle> table;  
    @FXML
    private TableColumn<Salle, String> DesCln;
    @FXML
    private TableColumn<Salle, String>  LocalCln;
    @FXML
    private TableColumn<Salle, String>  NumeroCln;

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
    void gestionSalle(ActionEvent event) {
        System.out.println("Gestion des Salles !");
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
    
    
    @FXML
    void refresh(MouseEvent event) throws MyException {
        table();
    }

    @FXML
    void Ajouter(ActionEvent event) throws IOException {
        System.out.println("Ajouter formulaire !");
        Stage logp = new Stage();
        //GlobalPane.getScene().getWindow().hide();
        Parent rootA;
        rootA = FXMLLoader.load(getClass().getResource("../View/ajoutSalle.fxml"));
        Scene scene = new Scene(rootA);
        logp.setScene(scene);
        logp.initStyle(StageStyle.UTILITY);
        logp.show();
        
        logp.setResizable(false);
    }
    
    
    
    
    //----------------------------------------------
    public void table() throws MyException{
        connect();
        ObservableList<Salle> salles = FXCollections.observableArrayList();
        try{
           pst = con.prepareStatement("select * from salle");  
           ResultSet rs = pst.executeQuery();
            {
            while (rs.next()){
                Salle st = new Salle();
                st.setNumSal(rs.getInt("Numero"));
                st.setLocal(rs.getString("local"));
                st.setDescriptionSal(rs.getString("description"));
                System.out.println("N° Salle  : "+st.getNumSal());
                System.out.println("Description  : "+st.getDescriptionSal());
                salles.add(st);
            }
            }
                //refreshTable();
                table.setItems(salles);
                
                NumeroCln.setCellValueFactory(new PropertyValueFactory<>("numSal"));
                LocalCln.setCellValueFactory(new PropertyValueFactory<>("local"));
                DesCln.setCellValueFactory(new PropertyValueFactory<>("descriptionSal"));
 
       }catch (SQLException ex){
           System.out.println("SQL Error : "+ex.getMessage());
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }catch(Exception e){
           System.out.println("Autre erreure : "+e.getMessage());
       }
        //--------------------------------------- mettre dans le formulaire ---------------------------//
        table.setRowFactory( tv -> {
            TableRow<Salle> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->{
                if (event.getClickCount() == 1 && (!myRow.isEmpty())){
                    myIndex =  table.getSelectionModel().getSelectedIndex();
                    //System.out.println(myIndex);
                    
                    int num = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getNumSal()));
                    String locale=table.getItems().get(myIndex).getLocal();
                    String des=table.getItems().get(myIndex).getDescriptionSal();
                    Afficher(num, 0,des);
                }
            });
            return myRow;
        });
    }
    
//-----------------------------------------------------
    
    void Afficher(int num,int local,String des){
        FXMLLoader loader = new FXMLLoader ();
        loader.setLocation(getClass().getResource("../View/ajoutSalle.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestionUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }

        AjouterSalleController addStudentController = loader.getController();
        addStudentController.setTextField(String.valueOf(num),local,des);
        
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
        try {
            table();
        } catch (MyException ex) {
            Logger.getLogger(GestionSalleController.class.getName()).log(Level.SEVERE, null, ex);
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
            System.out.println(this.getClass().getSimpleName()+" La connexion est bien établie !");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
