package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Clientpackage.Client;
import Model.Db4oManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class TelaUpdateClienteController implements Initializable {
    ControllerPrincipal controllerPrincipal = new ControllerPrincipal();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll("FEMININO","MASCULINO","OUTRO");
    }
    @FXML
    private ChoiceBox<String> choiceBox;
    
    @FXML
    private TextField emailContainer;
    
    @FXML
    private TextField nameContainer;
    
    @FXML 
    public int receberCliente(int clienteSelecionado){
        return clienteSelecionado;
    }
    @FXML
    void fazerAtualizacao(ActionEvent event) throws IOException {
        Db4oManager dbManager = new Db4oManager("database.dbo");
        String novoNome = nameContainer.getText();
        String novoEmail = emailContainer.getText();
        String novoGenero = choiceBox.getValue();

        Client clienteAtualizado = new Client(novoNome, novoEmail, novoGenero);
            dbManager.updateCliente(clienteAtualizado,receberCliente(0));
            dbManager.fecharConexao();
            controllerPrincipal.switchScene(event,"../view/TelaSelect.fxml");

    }

}


