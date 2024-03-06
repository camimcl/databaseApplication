package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import Clientpackage.Client;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import Model.Db4oManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;


public class TelaSelectController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBoxSelect;
    @FXML
    private Label labelSelect;
    @FXML
    private Button selectButton;
      @FXML
    private Pane painelSelect;
    

    //criando as instancias necessárias
    ControllerPrincipal controllerPrincipal = new ControllerPrincipal();
    TelaUpdateClienteController telaUpdateClienteController = new TelaUpdateClienteController();
    Db4oManager dbManager = new Db4oManager("database.dbo"); 


    // inicializando a choicebox com os nomes dos clientes ja registrados 
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        List<Client> clientes = dbManager.verTodosOsClientes();
        for (Client client : clientes) {
            choiceBoxSelect.getItems().addAll(client.getName());
        }
    }


    //metodo para direcionar o cliente selecionado para o metodo de detalhamento
    @FXML
    public void abrirTelaDetalhada(ActionEvent event) throws IOException { 
        int indiceSelecionado = choiceBoxSelect.getSelectionModel().getSelectedIndex();
        if(!choiceBoxSelect.getItems().isEmpty() && indiceSelecionado>=0){
            List<Client> clientes = dbManager.verTodosOsClientes();
            Client clienteSelecionado = clientes.get(indiceSelecionado);

            if (clienteSelecionado !=null) {
                controllerPrincipal.abrirTelaDetalhada(event, clienteSelecionado);//aciona o metodo do controller principal para trocar de tela
            }
        }
    }


    //metodo para direcionar o cliente selecionado para o metodo de delete
    @FXML
    void fazerDelete(ActionEvent event) throws IOException {
        int indiceSelecionado = choiceBoxSelect.getSelectionModel().getSelectedIndex();
            if (indiceSelecionado >= 0) {
                List<Client> clientes = dbManager.verTodosOsClientes();
                Client clienteSelecionado = clientes.get(indiceSelecionado);
                if (clienteSelecionado != null) {
                    int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar o cliente selecionado?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opcao == JOptionPane.YES_OPTION) {
                        dbManager.deleteCliente(clienteSelecionado.getId());
                        JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");
                        choiceBoxSelect.getItems().remove(indiceSelecionado);
                        }
                    }
                    else {
                        // Se o usuário cancelar, não fazer nada
                        JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
                    }
                }
            }

    //metodo para direcionar para o cadastro de usuario
    @FXML
    void cadastrarUsuario(ActionEvent event) throws IOException{
        controllerPrincipal.abrirTelaCadastro(event);
    }        
    

    //metodo para direcionar o cliente selecionado para uma atualizacao de dados
    @FXML
    void fazerUpdate(ActionEvent event) throws IOException {
        int indiceSelecionado = choiceBoxSelect.getSelectionModel().getSelectedIndex();
        if (indiceSelecionado >= 0) {
            List<Client> clientes = dbManager.verTodosOsClientes();
            Client clienteSelecionado = clientes.get(indiceSelecionado);
            if (clienteSelecionado != null) {
                controllerPrincipal.abrirTelaUpdate(event, clienteSelecionado);
            }
        }
    }              
}                
       




