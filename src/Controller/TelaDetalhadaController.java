package Controller;

import java.io.IOException;
import Clientpackage.Client;
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
    @FXML
    private Client cliente;
    
    ControllerPrincipal controllerPrincipal = new ControllerPrincipal();
    //voltar para tela de selecao
    public void voltar(ActionEvent event) throws IOException{
        controllerPrincipal.abrirTelaSelect(event);
    }
    //incializa informacoes referente a selecao
    public void inicializarCliente(Client cliente) {
        this.cliente=cliente;
        caixaNome.setText(cliente.getName());
        caixaEmail.setText(cliente.getEmail());
        caixaGenero.setText(cliente.getGender());
        

    
    }

}   
