package Controller;

import java.util.List;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import Clientpackage.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import Clientpackage.Client;

public class TelaSelectController {
    
    @FXML
    private ChoiceBox<?> choiceBoxSelect;
    
    @FXML
    void fazerSelect(ActionEvent event) {
        listResult(result);
    }
    public static void listResult (List<Client>result){
        System.out.println(result.size());
        for (Object o : result){
            System.out.println(o);
        }
    }
    ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"database.dbo");
    Client client = new Client(null,null,null);
    @SuppressWarnings("rawtypes")
    ObjectSet result = db.queryByExample(client);    

}
