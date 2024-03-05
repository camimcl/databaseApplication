package Model;
import java.util.ArrayList;
import java.util.List;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import Clientpackage.Client;

public class Db4oManager {
    private int nextClientId;
    private ObjectContainer db;

@SuppressWarnings("deprecation")
public Db4oManager(String dbCaminho){
    db = Db4o.openFile(dbCaminho);
} 
public void fecharConexao(){
    db.close();
}
public void inserirCliente(Client client){
    List<Client> clientes = verTodosOsClientes();
    int proximoId = 0;
    for (Client c : clientes) {
        if (c.getId() >= proximoId) {
            proximoId = c.getId() + 1;
        }
    }
    client.setId(proximoId);
    db.store(client);
}
public Client buscarClientePorId(int id) {
    List<Client> clientes = verTodosOsClientes();
    for (Client cliente : clientes) {
        if (cliente.getId() == id) {
            return cliente;
        }
    }
    System.out.println("Cliente não encontrado.");
    return null;
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
        db.store(clienteAntigo);
        db.close();
    } else {
        System.out.println("Cliente não encontrado para atualização.");
    }
}
public void deleteCliente(int id){
    Client clienteParaDeletar = buscarClientePorId(id);
    if (clienteParaDeletar != null) {
        db.delete(clienteParaDeletar);
    } else {
        System.out.println("Cliente não encontrado para deleção.");
    }
}

public List<Client>verTodosOsClientes(){
    List<Client>clientes = new ArrayList<>();
    ObjectSet<Client> result = db.queryByExample(Client.class);
    while (result.hasNext()){
        clientes.add(result.next());
    }
    return clientes;
}
}

