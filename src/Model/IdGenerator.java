package Model;

import java.util.ArrayList;
import java.util.List;

//TODO: need to load current database ids into list on instantiation
public class IdGenerator {

    private static IdGenerator idGenerator = null;
    private List<Integer> usedInts;

    private IdGenerator(){
        usedInts = new ArrayList<>();
    }

    public static IdGenerator getInstance(){
        if(idGenerator == null){
            idGenerator = new IdGenerator();
        }
        return idGenerator;
    }

    public int getUniqueId(){
        int id = (int)(Math.random() * 10000);
        if(usedInts.contains(id)){
            getUniqueId();
        }
        usedInts.add(id);
        return id;
    }
}
