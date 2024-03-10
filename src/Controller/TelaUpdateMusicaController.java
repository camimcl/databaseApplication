package Controller;

import java.io.File;
import java.io.IOException;
import Model.Db4oManager;
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

public class TelaUpdateMusicaController {
    @FXML
    private TextField artistaContainer;
    @FXML
    private TextField nameContainer;
    @FXML
    private TextField albumContainer;
    @FXML 
    private Musicas musicaSelecionada;
    @FXML
    private Button botaoSelecionarImagem;
    @FXML
    private Pane pane;
    String caminhoDaImagem;

    ControllerPrincipal controllerPrincipal = new ControllerPrincipal();

    //recebe o cliente escolhido
    public void receberMusica(Musicas musicaSelecionada){
        this.musicaSelecionada =musicaSelecionada;
    }
    @FXML
    private void exibirImagem(String caminhoDaImagem) {
        Image imagem = new Image("file:" + caminhoDaImagem);
        ImageView imageView = new ImageView(imagem);
        // Define o tamanho da ImageView conforme necessário
        imageView.setFitWidth(150); // Defina o tamanho conforme necessário
        imageView.setFitHeight(130); // Defina o tamanho conforme necessário
        // Adiciona a ImageView a um Pane na sua cena, por exemplo:
        pane.getChildren().add(imageView);
    }

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

    //pegar as novas informacoes e passa pro banco
    @FXML
    void fazerAtualizacao(ActionEvent event) throws IOException {
        String novoNome = nameContainer.getText();
        String novoArtista = artistaContainer.getText();
        String novoAlbum = albumContainer.getText();

        
        
        Musicas musicaAtualizada = new Musicas(novoNome, novoAlbum, novoArtista);
        musicaAtualizada.setCaminhoImagem(caminhoDaImagem);
        
        int id = musicaSelecionada.getId();

        Db4oManager dbManager = Db4oManager.getInstance();
        dbManager.updateMusica(id, musicaAtualizada); 

        controllerPrincipal.abrirTelaSelect(event);
    }

}

