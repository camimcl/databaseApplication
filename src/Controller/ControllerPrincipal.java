package Controller;

import java.io.IOException;

import Clientpackage.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerPrincipal {

    public void abrirTelaDetalhada(ActionEvent event,Client clienteSelecionado) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TelaDetalhada.fxml"));
        Parent root = loader.load();
        
        TelaDetalhadaController controller = loader.getController();
        controller.inicializarCliente(clienteSelecionado);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void abrirTelaUpdate(ActionEvent event, Client clienteSelecionado) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TelaUpdateCliente.fxml"));
        Parent root = loader.load();

        TelaUpdateClienteController controller = loader.getController();
        controller.receberCliente(clienteSelecionado);
    
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void abrirTelaCadastro(ActionEvent event) throws IOException{ 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TelaDeCadastro.fxml"));
        Parent root = loader.load();


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    

    public void abrirTelaSelect(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TelaSelect.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
