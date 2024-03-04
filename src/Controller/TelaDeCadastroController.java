package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Clientpackage.Client;
import Model.Db4oManager;

public class TelaDeCadastroController implements Initializable {
    
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
    ControllerPrincipal controllerPrincipal = new ControllerPrincipal();
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll("FEMININO","MASCULINO","OUTRO");
    }
    Db4oManager dbManager = new Db4oManager("database.dbo");

    public void fazerCadastro(ActionEvent event) throws IOException{
        String name = nameContainer.getText();
        String email = emailContainer.getText();
        String gender = choiceBox.getValue();
        
        Client client = new Client(name,email,gender);
      
        dbManager.inserirCliente(client);
        dbManager.fecharConexao();
        
        controllerPrincipal.switchScene(event, "../view/TelaSelect.fxml");
    }
        
        // para verificar se ta registrando
        // Db4oManager dbManager = new Db4oManager("database.dbo");        
        // List<Client> clientes = dbManager.verTodosOsClientes();
        // for (Client client : clientes) {
        //     System.out.println("nome: "+client.getName()+"\n"+"Email: "+client.getEmail()+"\n"+"Gender:"+client.getGender());
        // }
    

}
