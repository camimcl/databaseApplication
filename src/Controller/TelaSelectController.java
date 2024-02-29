package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Clientpackage.Client;
import Model.Db4oManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class TelaSelectController implements Initializable {
   
    @FXML
    private ChoiceBox<String> choiceBoxSelect;

    
    @FXML
     public void switchToTelaDetalhada(ActionEvent event) throws IOException{
     Parent root = FXMLLoader.load(getClass().getResource("../view/TelaDetalhada.fxml"));
     Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Db4oManager dbManager = new Db4oManager("database.dbo");
        List<Client> clientes = dbManager.verTodosOsClientes();
        for (Client client : clientes) {
            choiceBoxSelect.getItems().addAll(client.getName());
        }
    }
    public void abrirTelaDetalhada(ActionEvent event) throws IOException{
        Db4oManager dbManager = new Db4oManager("database.dbo");
        List<Client> clientes = dbManager.verTodosOsClientes();
        int indiceSelecionado = choiceBoxSelect.getSelectionModel().getSelectedIndex();
        Client clienteSelecionado = clientes.get(indiceSelecionado);
        if (clienteSelecionado !=null) {
            switchToTelaDetalhada(event);
        }
    }
  

}
