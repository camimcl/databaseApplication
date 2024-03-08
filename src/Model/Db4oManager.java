package Model;
import java.util.ArrayList;
import java.util.List;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import Clientpackage.Client;

public class Db4oManager {
    private int nextClientId;
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

    public void inserirCliente(Client client){
        List<Client> clientes = getClientes();
        
        int proximoId = 0;
        
        for (Client c : clientes) {
            if (c.getId() >= proximoId) {
                proximoId = c.getId() + 1;
            }
        }

        client.setId(proximoId);

        objectContainer.store(client);
    }
    public Client buscarClientePorId(int id) {
        Client client = null;

        List<Client> clientes = getClientes();

        for (Client cliente : clientes) {
            if (cliente.getId() == id) {
                client = cliente;
                break;
            }
        }

        return client;
    }

    public void updateCliente(int id,Client clienteAtualizado){
        Client clienteAntigo = buscarClientePorId(id);

        if (clienteAntigo != null) {
            if (clienteAtualizado.getName() != null && !clienteAtualizado.getName().isEmpty()) {
                clienteAntigo.setName(clienteAtualizado.getName());
            }
            if (clienteAtualizado.getEmail() != null && !clienteAtualizado.getEmail().isEmpty()) {
                clienteAntigo.setEmail(clienteAtualizado.getEmail());
            }
            if (clienteAtualizado.getGender() != null && !clienteAtualizado.getGender().isEmpty()) {
                clienteAntigo.setGender(clienteAtualizado.getGender());
            }
            objectContainer.store(clienteAntigo);
        } else {
            System.out.println("Cliente não encontrado para atualização.");
        }
    }
    public void deleteCliente(int id){
        Client clienteParaDeletar = buscarClientePorId(id);

        if (clienteParaDeletar != null) {
            objectContainer.delete(clienteParaDeletar);
        } else {
            System.out.println("Cliente não encontrado para deleção.");
        }
    }

    public List<Client> getClientes(){
        List<Client> clientes = new ArrayList<>();

        ObjectSet<Client> result = objectContainer.queryByExample(Client.class);

        while (result.hasNext()){
            clientes.add(result.next());
        }

        return clientes;
    }
}

