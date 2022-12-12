
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class GestionSalleController implements Initializable {
   
    @FXML
    private AnchorPane GlobalPane;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
