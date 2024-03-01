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

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField emailContainer;

    @FXML
    private TextField nameContainer;
    
    private Db4oManager dbManager;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll("FEMININO","MASCULINO","OUTRO");
    }
    
    @FXML
    void fazerAtualizacao(ActionEvent event,int clienteSelecionado) throws IOException {
        String novoNome = nameContainer.getText();
        String novoEmail = emailContainer.getText();
        String novoGenero = choiceBox.getValue();

        Client clienteAtualizado = new Client(novoNome, novoEmail, novoGenero);

        if (clienteAtualizado!= null){
            ControllerPrincipal controllerPrincipal = new ControllerPrincipal();
            dbManager.updateCliente(clienteAtualizado,clienteSelecionado);
            controllerPrincipal.switchScene(event,"../view/TelaSelect.fxml");
        }
    }

}

