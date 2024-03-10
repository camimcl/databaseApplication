package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import musicaspackage.Musicas;

public class TelaDetalhadaController {

    @FXML
    private Label caixaNome;
    @FXML
    private Label caixaAlbum;
    @FXML
    private Label caixaArtista;

    private Musicas musica;
    
    ControllerPrincipal controllerPrincipal = new ControllerPrincipal();
    //voltar para tela de selecao
    public void voltar(ActionEvent event) throws IOException{
        controllerPrincipal.abrirTelaSelect(event);
    }
    //incializa informacoes referente a selecao
    public void inicializarMusica(Musicas musica) {
        this.musica=musica;
        caixaNome.setText(musica.getName());
        caixaArtista.setText(musica.getArtista());
        caixaAlbum.setText(musica.getAlbum());
       
    }

}   
