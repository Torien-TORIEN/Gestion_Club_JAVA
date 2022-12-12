
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


public class ResponsableController implements Initializable {
    
    @FXML
    private BorderPane GlobalPane;

    @FXML
    private Button btnDeconnecter;

    @FXML
    void SeDeconnecter(ActionEvent event) throws IOException {
        System.out.println("Se deconnecter !");
        Stage logp = new Stage();
        GlobalPane.getScene().getWindow().hide();
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
