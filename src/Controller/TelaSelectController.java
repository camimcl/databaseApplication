package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import musicaspackage.Musicas;
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
    @FXML
    private ImageView imageView;
    @FXML
    private Pane pane;

    //criando as instancias necessárias
    ControllerPrincipal controllerPrincipal = new ControllerPrincipal();
    
    TelaUpdateMusicaController telaUpdateMusicaController = new TelaUpdateMusicaController();

    Db4oManager dbManager = Db4oManager.getInstance();
    


    // inicializando a choicebox com os nomes dos musicas ja registrados 
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        List<Musicas> musicas = dbManager.getMusicas();
        for (Musicas musica : musicas) {
            choiceBoxSelect.getItems().addAll(musica.getName());
        }
        

        choiceBoxSelect.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Musicas musicaSelecionada = dbManager.buscarMusicaPorNome(newValue);
                if (musicaSelecionada != null) {
                    String caminhoDaImagem = musicaSelecionada.getCaminhoImagem();
                   
                    if (caminhoDaImagem != null && !caminhoDaImagem.isEmpty()) {
                        exibirImagem(caminhoDaImagem);
                    }
                }
            }

        });
    }
    
    
    private void exibirImagem(String caminhoDaImagem) {
        pane.getChildren().clear();
    if (caminhoDaImagem != null && !caminhoDaImagem.isEmpty()) {
        File file = new File(caminhoDaImagem);
        System.out.println("Arquivo existe: " + file.exists());
        if (file.exists()) { 
            Image imagem = new Image("file:" + caminhoDaImagem);
            ImageView imageView = new ImageView(imagem);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            pane.getChildren().add(imageView);
        } 
    }
    else{
        imageView.setImage(null);
    }
}
    
    //metodo para direcionar a música selecionado para o metodo de detalhamento
    @FXML
    public void abrirTelaDetalhada(ActionEvent event) throws IOException { 
        int indiceSelecionado = choiceBoxSelect.getSelectionModel().getSelectedIndex();
        
        if(!choiceBoxSelect.getItems().isEmpty() && indiceSelecionado>=0){
            List<Musicas> musicas = dbManager.getMusicas();
            Musicas musicaSelecionada = musicas.get(indiceSelecionado);

            if (musicaSelecionada !=null) {
                controllerPrincipal.abrirTelaDetalhada(event, musicaSelecionada);//aciona o metodo do controller principal para trocar de tela
            }
        }
    }


    //metodo para direcionar a música selecionado para o metodo de delete
    @FXML
    void fazerDelete(ActionEvent event) throws IOException {
        int indiceSelecionado = choiceBoxSelect.getSelectionModel().getSelectedIndex();
            if (indiceSelecionado >= 0) {
                List<Musicas> musicas = dbManager.getMusicas();
                Musicas musicaSelecionada = musicas.get(indiceSelecionado);
                if (musicaSelecionada != null) {
                    int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar a música selecionado?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opcao == JOptionPane.YES_OPTION) {
                        dbManager.deletarMusica(musicaSelecionada.getId());
                        JOptionPane.showMessageDialog(null, "Música deletado com sucesso!");
                        controllerPrincipal.abrirTelaSelect(event);
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
    

    //metodo para direcionar a música selecionado para uma atualizacao de dados
    @FXML
    void fazerUpdate(ActionEvent event) throws IOException {
        int indiceSelecionado = choiceBoxSelect.getSelectionModel().getSelectedIndex();
        if (indiceSelecionado >= 0) {
            List<Musicas> musicas = dbManager.getMusicas();
            Musicas musicaSelecionada = musicas.get(indiceSelecionado);
            if (musicaSelecionada != null) {
                controllerPrincipal.abrirTelaUpdate(event, musicaSelecionada);
            }
        }
    }              
}                
       




