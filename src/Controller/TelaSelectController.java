package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import Clientpackage.Client;
import Model.Db4oManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;


public class TelaSelectController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBoxSelect;
    ControllerPrincipal controllerPrincipal = new ControllerPrincipal();
    Db4oManager dbManager = new Db4oManager("database.dbo"); //pega o indice do cliente escolhido no choicebox para mandar a informacao para a proxima tela 
    
    @Override
    public void initialize(URL location, ResourceBundle resources) { // inicializando a choicebox com os nomes dos clientes ja registrados 
        List<Client> clientes = dbManager.verTodosOsClientes();
        for (Client client : clientes) {
            choiceBoxSelect.getItems().addAll(client.getName());
        }
    }
    public void abrirTelaDetalhada(ActionEvent event) throws IOException { 
        int indiceSelecionado = choiceBoxSelect.getSelectionModel().getSelectedIndex();
        if(!choiceBoxSelect.getItems().isEmpty() && indiceSelecionado>=0){
        List<Client> clientes = dbManager.verTodosOsClientes();
        Client clienteSelecionado = clientes.get(indiceSelecionado);
        if (clienteSelecionado !=null) {
            controllerPrincipal.abrirTelaDetalhada(event, clienteSelecionado);//aciona o metodo do controller principal para trocar de tela
        }
    }
    }
    @FXML
    void fazerDelete(ActionEvent event) {

    }
    TelaUpdateClienteController telaUpdateClienteController = new TelaUpdateClienteController();
    @FXML
    void fazerUpdate(ActionEvent event) throws IOException {
        int indiceSelecionado = choiceBoxSelect.getSelectionModel().getSelectedIndex();
        if (indiceSelecionado >-1) {
            telaUpdateClienteController.receberCliente(indiceSelecionado);
            controllerPrincipal.switchScene(event, "../view/TelaUpdateCliente.fxml");
        }
       
}
}


