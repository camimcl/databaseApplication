package Controller;

import java.io.IOException;

import com.db4o.Db4o;

import Clientpackage.Client;
import Model.Db4oManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TelaDetalhadaController {

    @FXML
    private Label caixaEmail;
    @FXML
    private Label caixaGenero;

    @FXML
    private Label caixaNome;
    private Client cliente;
    private Db4oManager dbManager;
    @FXML
    void fazerDelete(ActionEvent event) {
        
    }
    @FXML
    void fazerUpdate(ActionEvent event) throws IOException {
        ControllerPrincipal controllerPrincipal = new ControllerPrincipal();
        controllerPrincipal.abrirTelaDetalhada(event, cliente, dbManager);
    }

  
    public void inicializarCliente(Client cliente,Db4oManager dbManager) {
        this.cliente=cliente;
        this.dbManager = dbManager;
        caixaNome.setText(cliente.getName());
        caixaEmail.setText(cliente.getEmail());
        caixaGenero.setText(cliente.getGender());
       
    }

}   
