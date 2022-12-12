
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class AdminController implements Initializable {
    @FXML
    private BorderPane BordePane;
    @FXML
    private Button btnDeconnecter;
    @FXML
    private Button btnGestClub;
    @FXML
    private Button btnGestEv;
    @FXML
    private Button btnGestMat;
    @FXML
    private Button btnGestSal;
    @FXML
    private Button btnGestUser;



    @FXML
    void gestionEvenement(ActionEvent event) {
        
    }

    @FXML
    void gestionMateriel(ActionEvent event) throws IOException {
        System.out.println("Gestion des Evenements !");
        Stage logp = new Stage();
        BordePane.getScene().getWindow().hide();
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
        BordePane.getScene().getWindow().hide();
        Parent rootA;
        rootA = FXMLLoader.load(getClass().getResource("../View/GestionSalle.fxml"));
        Scene scene = new Scene(rootA);
        logp.setScene(scene);
        logp.show();
        logp.setResizable(false);
    }

    @FXML
    void gestionUtilisateur(ActionEvent event) throws IOException {
        System.out.println("Gestion des Utilisateur !");
        Stage logp = new Stage();
        BordePane.getScene().getWindow().hide();
        Parent rootA;
        rootA = FXMLLoader.load(getClass().getResource("../View/GestionUtilisateur.fxml"));
        Scene scene = new Scene(rootA);
        logp.setScene(scene);
        logp.show();
        logp.setResizable(false);
    }
    
    @FXML
    void gestionClub(ActionEvent event)throws IOException {
        System.out.println("Gestion des Clubs !");
        Stage logp = new Stage();
        BordePane.getScene().getWindow().hide();
        Parent rootA;
        rootA = FXMLLoader.load(getClass().getResource("../View/GestionClub.fxml"));
        Scene scene = new Scene(rootA);
        logp.setScene(scene);
        logp.show();
        logp.setResizable(false);
    }

    @FXML
    void SeDeconnecter(ActionEvent event) throws IOException {
        System.out.println("Se deconnecter !");
        Stage logp = new Stage();
        BordePane.getScene().getWindow().hide();
        Parent rootA;
        rootA = FXMLLoader.load(getClass().getResource("../View/login.fxml"));
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
