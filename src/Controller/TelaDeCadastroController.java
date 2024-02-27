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
import java.util.ResourceBundle;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import Clientpackage.Client;

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
    private String[]Gender ={"FEMININO","MASCULINO","OUTRO"};
    @FXML
    private DatePicker dateOfBirthContainer;

    @FXML
    private TextField emailContainer;

    @FXML
    private ChoiceBox<?> genderContainer;

    @FXML
    private TextField nameContainer;

    @FXML
    private PasswordField passwordContainer;

    @FXML
    private Button registerButton;
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll(Gender);
    }
    
    @FXML
    Client client= new Client();
    ObjectContainer db;
    @SuppressWarnings("deprecation")
    public void fazerCadastro(ActionEvent event) {
        db = Db4o.openFile("database.dbo");
        String name = nameContainer.getText();
        // String password = passwordContainer.getText();
        String email = emailContainer.getText();
        client.setEmail(email);
        client.setName(name);

        db.store(client);
        db.close();
        ObjectSet<Client>lista;
        db= Db4o.openFile("database.dbo");
        lista= db.query(Client.class);
        while (lista.hasNext()) {
            client = lista.next();
            System.out.println("################");
            System.out.println(client.getName());
            System.out.println(client.getEmail());
        }
        
    }
}
