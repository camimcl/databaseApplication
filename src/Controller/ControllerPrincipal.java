package Controller;

import java.io.IOException;

import Clientpackage.Client;
import Model.Db4oManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerPrincipal {

    public void abrirTelaDetalhada(ActionEvent event,Client clienteSelecionado,Db4oManager dbManager){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TelaDetalhada.fxml"));
            Parent root = loader.load();
            
            TelaDetalhadaController controller = loader.getController();
            controller.inicializarCliente(clienteSelecionado,dbManager);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void switchScene(ActionEvent event,String caminhoTela) throws IOException{
     Parent root = FXMLLoader.load(getClass().getResource(caminhoTela));
     Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
}
