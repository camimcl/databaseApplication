package Controller;

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
    private Client cliente;

    @FXML
    void fazerDelete(ActionEvent event) {

    }
    @FXML
    void fazerUpdate(ActionEvent event) {

    }
    private void initialize() {
        if (cliente != null) {
            inicializarCliente(cliente);
        }
    }
    
    public void inicializarCliente(Client cliente) {
        this.cliente=cliente;
        caixaNome.setText(cliente.getName());
        caixaEmail.setText(cliente.getEmail());
        System.out.println("oi");
    }

}   
