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
    
    ControllerPrincipal controllerPrincipal = new ControllerPrincipal();
    
    public void voltar(ActionEvent event) throws IOException{
        controllerPrincipal.switchScene(event,"../view/TelaSelect.fxml");
    }
   

    public void inicializarCliente(Client cliente) {
        this.cliente=cliente;
        caixaNome.setText(cliente.getName());
        caixaEmail.setText(cliente.getEmail());
        caixaGenero.setText(cliente.getGender());
       
    }

}   
