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
    @FXML
    private Label caixaAlbum;
    @FXML
    private Label caixaArtista;
    @FXML
    private Label labelAdicionar;
    @FXML
    private Pane backgroundPane;

    private Musicas musica;


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
    }
    public void getMusicImage(){
        int indiceSelecionado = choiceBoxSelect.getSelectionModel().getSelectedIndex();
        if(!choiceBoxSelect.getItems().isEmpty() && indiceSelecionado>=0){
            List<Musicas> musicas = dbManager.getMusicas();
            Musicas musicaSelecionada = musicas.get(indiceSelecionado);

                if (musicaSelecionada != null) {
                    String caminhoDaImagem = musicaSelecionada.getCaminhoImagem();              
                    exibirImagem(caminhoDaImagem,musicaSelecionada);
                    System.out.println(musicaSelecionada.getId());
                }  
        }
    }
    
   
    private void exibirImagem(String caminhoDaImagem,Musicas musicaSelecionada) {
        // Limpar o conteúdo do painel
        pane.getChildren().clear();
        
        if (caminhoDaImagem != null && !caminhoDaImagem.isEmpty()) {
            backgroundPane.setStyle("-fx-background-color:transparent;");
            File file = new File(caminhoDaImagem);
            if (file.exists()) { 
                Image image = new Image("file:" + caminhoDaImagem);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(180);
                imageView.setFitHeight(180);
                pane.getChildren().add(imageView);
                imageView.layoutXProperty().bind(pane.widthProperty().subtract(imageView.fitWidthProperty()).divide(2));
                imageView.layoutYProperty().bind(pane.heightProperty().subtract(imageView.fitHeightProperty()).divide(2));
                int value = musicaSelecionada.getId();
               switch (value) {

                case 0:
                    backgroundPane.setStyle("-fx-background-color: linear-gradient(to bottom,#e4e4de,rgb(86, 207, 195)); -fx-background-radius:25px;");
                    break;
                case 1:
                    backgroundPane.setStyle("-fx-background-color: linear-gradient(to bottom,#d34d13,rgb(179, 126, 118)); -fx-background-radius:25px;");
                    break;
                case 2:
                    backgroundPane.setStyle("-fx-background-color: linear-gradient(to bottom,#453873,#6D33A6); -fx-background-radius:25px;");
                    break;
                case 10:
                    backgroundPane.setStyle("-fx-background-color: linear-gradient(to bottom,#F2F2F2,#F2E422); -fx-background-radius:25px;");
                    break;
                case 12:
                    backgroundPane.setStyle("-fx-background-color: linear-gradient(to bottom,#D0F2E6,#1A088C); -fx-background-radius:25px;");    
                    break;
                case 9:
                    backgroundPane.setStyle("-fx-background-color: linear-gradient(to bottom,#d34d13,rgb(179, 126, 118)); -fx-background-radius:25px;");
                    break;
                default:
                    backgroundPane.setStyle("-fx-background-color: linear-gradient(to bottom,#474746,rgb(34, 33, 32)); -fx-background-radius:25px;");
                    break;
               }
            } 
        } else {
            // Adicionar a imagem padrão para músicas sem imagem
            Image imagemPadrao = new Image("file:C:/Users/cami/Documents/vscode/spotifyDatabaseApp/src/images/imagem.png");
            ImageView imageView = new ImageView(imagemPadrao);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            pane.getChildren().add(imageView);
            imageView.layoutXProperty().bind(pane.widthProperty().subtract(imageView.fitWidthProperty()).divide(2));
            imageView.layoutYProperty().bind(pane.heightProperty().subtract(imageView.fitHeightProperty()).divide(2));
        }
    }

    public void inicializarMusica(Musicas musica) {
        this.musica=musica;
        caixaArtista.setText("Artista: "+musica.getArtista());
        caixaAlbum.setText("Album: "+musica.getAlbum());
    }



    //metodo para direcionar a música selecionado para o metodo de detalhamento
    @FXML
    public void abrirTelaDetalhada(ActionEvent event) throws IOException { 
        int indiceSelecionado = choiceBoxSelect.getSelectionModel().getSelectedIndex();
        
        if(!choiceBoxSelect.getItems().isEmpty() && indiceSelecionado>=0){
            List<Musicas> musicas = dbManager.getMusicas();
            Musicas musicaSelecionada = musicas.get(indiceSelecionado);

            if (musicaSelecionada !=null) {
                getMusicImage();
                inicializarMusica(musicaSelecionada);
            }
        }
    }
    public void voltar(ActionEvent event) throws IOException{
        controllerPrincipal.abrirTelaSelect(event);
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
       




