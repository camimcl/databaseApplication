package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

    @FXML 
    private Label registerTitle;

    @FXML
    private Label labelErrorMessage;
    
    ControllerPrincipal controllerPrincipal = new ControllerPrincipal();

    private Db4oManager dbManager = Db4oManager.getInstance();
  
    //inicializar opcoes da choicebox
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll("Feminino","Masculino","Outro");
    }

    //pegar informacoes do novo cliente e mandar pro banco 
    public void fazerCadastro(ActionEvent event) throws IOException{
        String name = nameContainer.getText();
        String email = emailContainer.getText();
        String gender = choiceBox.getValue();
        if(name!=null && email!=null && gender != null){
            Client client = new Client(name,email,gender);
      
            dbManager.inserirCliente(client);
        
            controllerPrincipal.abrirTelaSelect(event);
        }
            else{ 
                labelErrorMessage.setText("Para continuar, preencha os campos!");
                
            }
    }
        
}
