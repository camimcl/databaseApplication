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
    @FXML 
    private Client clienteSelecionado;

    ControllerPrincipal controllerPrincipal = new ControllerPrincipal();

    @Override
    public void initialize(URL location, ResourceBundle resources) {//Inicializar choicebox
        choiceBox.getItems().addAll("FEMININO","MASCULINO","OUTRO");
    }
    public void receberCliente(Client clienteSelecionado){
        this.clienteSelecionado =clienteSelecionado;
    }
    @FXML
    void fazerAtualizacao(ActionEvent event) throws IOException {
        String novoNome = nameContainer.getText();
        String novoEmail = emailContainer.getText();
        String novoGenero = choiceBox.getValue();
        
        Client clienteAtualizado = new Client(novoNome, novoEmail, novoGenero);

        Db4oManager dbManager = Db4oManager.getInstance();

        int id = clienteSelecionado.getId();

        dbManager.updateCliente(id, clienteAtualizado); 

        controllerPrincipal.abrirTelaSelect(event);
    }

}


