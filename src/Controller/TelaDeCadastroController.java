package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import musicaspackage.Musicas;
import java.io.File;
import java.io.IOException;


import Model.Db4oManager;

public class TelaDeCadastroController{
    
    @FXML
    private TextField albumContainer;
    @FXML
    private Pane pane;
    @FXML
    private TextField nameContainer;
    @FXML
    private Button botaoSelecionarImagem;
    @FXML
    private TextField artistaContainer;

    ControllerPrincipal controllerPrincipal = new ControllerPrincipal();

    private Db4oManager dbManager = Db4oManager.getInstance();
    String caminhoDaImagem ;
  
     @FXML
    private void selecionarImagem() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione uma imagem");
        Stage stage = (Stage) botaoSelecionarImagem.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            caminhoDaImagem = file.getAbsolutePath();
            exibirImagem(caminhoDaImagem);
        }
    }


    private void exibirImagem(String caminhoDaImagem) {
        Image imagem = new Image("file:" + caminhoDaImagem);
        ImageView imageView = new ImageView(imagem);
        // Define o tamanho da ImageView conforme necessário
        imageView.setFitWidth(150); // Defina o tamanho conforme necessário
        imageView.setFitHeight(130); // Defina o tamanho conforme necessário
        // Adiciona a ImageView a um Pane na sua cena, por exemplo:
        pane.getChildren().add(imageView);
    }

    //pegar informacoes do novo cliente e mandar pro banco 
    public void fazerCadastro(ActionEvent event) throws IOException{
        String nome = nameContainer.getText();
        String album = albumContainer.getText();
        String artista = artistaContainer.getText();
        
        Musicas musica = new Musicas(nome,album,artista);
      
        musica.setCaminhoImagem(caminhoDaImagem);
        dbManager.inserirMusica(musica);
        
        controllerPrincipal.abrirTelaSelect(event);
    }
        
}
