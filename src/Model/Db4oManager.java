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
public void updateCliente(Client clientAtualizado){
    Client clienteAntigo = (Client)db.queryByExample(new Client(clientAtualizado.getName(), clientAtualizado.getEmail())).next();
    clienteAntigo.setName(clientAtualizado.getName());
    clienteAntigo.setEmail(clientAtualizado.getEmail());
    db.store(clienteAntigo);
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

