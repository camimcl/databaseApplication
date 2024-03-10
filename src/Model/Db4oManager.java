package Model;
import java.util.ArrayList;
import java.util.List;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import musicaspackage.Musicas;

public class Db4oManager {
    
    private ObjectContainer objectContainer;
    private static String DATABASE_PATH = "database.dbo";
    
    // attributo do singleton
    private static Db4oManager db4oManager;
 

    // metodo do singleton
    public static Db4oManager getInstance() {
        if (db4oManager == null) {
            db4oManager = new Db4oManager();
        }

        return db4oManager;
    }

    // Construtor privado do singleton
    private Db4oManager() {
        openConnection();
    }

    @SuppressWarnings("deprecation")
    private void openConnection() {
        objectContainer = Db4o.openFile(DATABASE_PATH); 
    }

    public void inserirMusica(Musicas musica){
        List<Musicas> musicas = getMusicas();
        
        int proximoId = 0;
        
        for (Musicas c : musicas) {
            if (c.getId() >= proximoId) {
                proximoId = c.getId() + 1;
            }
        }

        musica.setId(proximoId);

        objectContainer.store(musica);
    }
    public Musicas buscarMusicaPorId(int id) {
        Musicas musica = null;

        List<Musicas> musicas = getMusicas();

        for (Musicas musicae : musicas) {
            if (musicae.getId() == id) {
                musica = musicae;
                break;
            }
        }

        return musica;
    }

    public void updateMusica(int id,Musicas musicaAtualizada){
        Musicas musicaAntiga = buscarMusicaPorId(id);

        if (musicaAntiga != null) {
            if (musicaAtualizada.getName() != null && !musicaAtualizada.getName().isEmpty()) {
                musicaAntiga.setName(musicaAtualizada.getName());
            }
            if (musicaAtualizada.getAlbum() != null && !musicaAtualizada.getAlbum().isEmpty()) {
                musicaAntiga.setAlbum(musicaAtualizada.getAlbum());
            }
            if (musicaAtualizada.getArtista() != null && !musicaAtualizada.getArtista().isEmpty()) {
                musicaAntiga.setArtista(musicaAtualizada.getArtista());
            }
            if(musicaAtualizada.getCaminhoImagem()!=null && !musicaAtualizada.getCaminhoImagem().isEmpty()){
                musicaAntiga.setCaminhoImagem(musicaAtualizada.getCaminhoImagem());
            }
            objectContainer.store(musicaAntiga);
        } else {
            System.out.println("musica não encontrado para atualização.");
        }
    }
    public void deletarMusica(int id){
        Musicas musicaParaDeletar = buscarMusicaPorId(id);

        if (musicaParaDeletar != null) {
            objectContainer.delete(musicaParaDeletar);
        } else {
            System.out.println("Musica não encontrado para deleção.");
        }
    }

    public List<Musicas> getMusicas(){
        List<Musicas> musicas = new ArrayList<>();

        ObjectSet<Musicas> result = objectContainer.queryByExample(Musicas.class);

        while (result.hasNext()){
            musicas.add(result.next());
        }

        return musicas;
    }
    public Musicas buscarMusicaPorNome(String nome) {
        ObjectSet<Musicas> result = objectContainer.query(new Predicate<Musicas>() {
            @Override
            public boolean match(Musicas musica) {
                return musica.getName().equals(nome);
            }
        });
    
        if (result.hasNext()) {
            return result.next(); 
        } else {
            return null; 
        }
    }
}

