import java.util.*;

class Heat {
    private Dance dance;
    private List<Couple> couples;

    Heat(Dance dance) {
        this.dance = dance;
        this.couples = new ArrayList<>();
    }

    Heat(Dance dance, List<Couple> couples) {
        this.dance = dance;
        this.couples = couples;
    }

    public Dance getDance(){
        return dance;
    }

    public List<Couple> getCouples(){
        return couples;
    }

    public void addCouple(Couple couple){
        couples.add(couple);
    }

    public void addCouples(List<Couple> couples){
        this.couples.addAll(couples);
    }
};