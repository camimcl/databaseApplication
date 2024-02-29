package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import Clientpackage.Client;
import Model.Db4oManager;

public class TelaDeCadastroController implements Initializable {
    
     public void switchToScene1(ActionEvent event) throws IOException{
     Parent root = FXMLLoader.load(getClass().getResource("../view/TelaDeMenu.fxml"));
     Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private DatePicker dateOfBirthContainer;

    @FXML
    private TextField emailContainer;

    @FXML
    private TextField nameContainer;

    @FXML
    private PasswordField passwordContainer;

    @FXML
    private Button registerButton;
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll("FEMININO","MASCULINO","OUTRO");
    }
    public void fazerCadastro(ActionEvent event){
        String name = nameContainer.getText();
        String email = emailContainer.getText();
        String gender = choiceBox.getValue();
        
        Client client = new Client(name,email);
        client.setGender(gender);
        
        
        Db4oManager dbManager = new Db4oManager("database.dbo");
        dbManager.inserirCliente(client);
        dbManager.fecharConexao();
    }
    public void mostrarClientes(ActionEvent event){     
        Db4oManager dbManager = new Db4oManager("database.dbo");        
        List<Client> clientes = dbManager.verTodosOsClientes();
        dbManager.fecharConexao();
        for (Client client : clientes) {
            System.out.println("nome: "+client.getName()+"\n"+"Email: "+client.getEmail()+"\n"+"Gender:"+client.getGender());
        }
    }
    
}
