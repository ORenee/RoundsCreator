import java.util.*;

public class Couple {
    private String lead;
    private String follow;
    private List<Dance> dances;

    Couple(String lead, String follow, List<Dance> dances) {
        this.lead = lead;
        this.follow = follow;
        this.dances = dances;
    };

    public String getLead(){
        return lead;
    }

    public String getFollow(){
        return follow;
    }

    public List<Dance> getDances(){
        return dances;
    }

    public List<String> getDancers(){
        List<String> dancers = new ArrayList<>();
        dancers.add(lead);
        dancers.add(follow);
        return dancers;
    }

    public boolean dancesRhythm(){
        for(Dance dance : dances){
            if(dance.isRhythm()){
                return true;
            }
        }
        return false;
    }

    public boolean dancesSmooth(){
        for(Dance dance : dances){
            if(dance.isSmooth()){
                return true;
            }
        }
        return false;
    }

    public boolean dancesStandard(){
        for(Dance dance : dances){
            if(dance.isStandard()){
                return true;
            }
        }
        return false;
    }

    public void print(){
        System.out.println("Lead: " + lead + " Follow: " + follow);
        System.out.print("Dances: " );
        for (Dance dance : dances) {
            System.out.print(dance.getName() + " ");
        }
        System.out.println();
    }
};