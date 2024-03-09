package Controller;

import java.io.IOException;

import Clientpackage.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//classe para controlar fluxo de telas
public class ControllerPrincipal {

    //levar para tela de detalhamento + chama o controller da classe pra passar info do cliente 
    public void abrirTelaDetalhada(ActionEvent event,Client clienteSelecionado) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TelaDetalhada.fxml"));

        Parent root = loader.load();
        
         //acesso ao cotroller da tela p passar informacao
        TelaDetalhadaController controller = loader.getController();
        controller.inicializarCliente(clienteSelecionado);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        //adicao de estilizacao para scene
        String css =this.getClass().getResource("../style/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    //levar para tela de atualizacao + chama o controller da classe pra passar info do cliente 
    public void abrirTelaUpdate(ActionEvent event, Client clienteSelecionado) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TelaUpdateCliente.fxml"));
        
        Parent root = loader.load();

        //acesso ao cotroller da tela p passar informacao
        TelaUpdateClienteController controller = loader.getController();
        controller.receberCliente(clienteSelecionado);
    
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        //adicao de estilizacao para scene
        String css =this.getClass().getResource("../style/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    //levar para tela de cadastro
    public void abrirTelaCadastro(ActionEvent event) throws IOException{ 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TelaDeCadastro.fxml"));

        Parent root = loader.load();


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        //adicao de estilizacao para scene
        String css =this.getClass().getResource("../style/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    
    //levar para tela de selecao
    public void abrirTelaSelect(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TelaSelect.fxml"));

        Parent root = loader.load();
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        //adicao de estilizacao para scene
        String css =this.getClass().getResource("../style/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
