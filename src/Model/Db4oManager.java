package Model;
import java.util.ArrayList;
import java.util.List;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import Clientpackage.Client;

public class Db4oManager {
    private ObjectContainer db;

@SuppressWarnings("deprecation")
public Db4oManager(String dbCaminho){
    db = Db4o.openFile(dbCaminho);
} 
public void fecharConexao(){
    db.close();
}
public void inserirCliente(Client client){
    db.store(client);
}
public void updateCliente(Client clientAtualizado, int clientSelecionado){
        List<Client> clientes = verTodosOsClientes();
        Client clienteAntigo = clientes.get(clientSelecionado);   
        clienteAntigo = new Client(clientAtualizado.getName(), clientAtualizado.getEmail(), clientAtualizado.getGender());
        clienteAntigo.setName(clientAtualizado.getName());
        clienteAntigo.setEmail(clientAtualizado.getEmail());
        clienteAntigo.setGender(clientAtualizado.getGender());
        db.store(clienteAntigo);
        if(clientAtualizado.getGender()!=null){
            clienteAntigo.setGender(clientAtualizado.getGender());
        }
    }
    

public void deleteCliente(Client client){}

public List<Client>verTodosOsClientes(){
    List<Client>clientes = new ArrayList<>();
    ObjectSet<Client> result = db.queryByExample(Client.class);
    while (result.hasNext()){
        clientes.add(result.next());
    }
    return clientes;
}
}

