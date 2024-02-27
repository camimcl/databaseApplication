package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaDeMenuController {

    @FXML
    void fazerInsert(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("../view/TelaDeCadastro.fxml"));
     Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    @FXML
    void fazerSelect(ActionEvent event) {

    }

    @FXML
    void fazerUpdate(ActionEvent event) {

    }
    @FXML
    void fazerDelete(ActionEvent event) {

    }

}
