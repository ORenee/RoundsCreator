import java.util.ArrayList;
import java.util.List;

public class Round {
    List<Couple> dance1Dancers;
    List<Couple> dance2Dancers;
    List<Couple> dance3Dancers;

    Style style;

    Dance dance1;
    Dance dance2;
    Dance dance3;

    int maxOnfloor;

    Round(int maxOnfloor, Style style){
        this.maxOnfloor = maxOnfloor;
        dance1Dancers = new ArrayList<>();
        dance2Dancers = new ArrayList<>();
        dance3Dancers = new ArrayList<>();

        this.style = style;
        if(style == Style.RHYTHM){
            dance1 = Dance.CHACHA;
            dance2 = Dance.RUMBA;
            dance3 = Dance.SWING;
        }
        else if(style == Style.SMOOTH){
            dance1 = Dance.WALTZ;
            dance2 = Dance.TANGO;
            dance3 = Dance.FOXTROT;
        }
    }

    public void print(){
        System.out.println(dance1.getName() + ": ");
        for(Couple couple : dance1Dancers){
            System.out.println(couple.getLead() + " leading " + couple.getFollow());
        }
        System.out.println(dance2.getName() + ": ");
        for(Couple couple : dance2Dancers){
            System.out.println(couple.getLead() + " leading " + couple.getFollow());
        }
        System.out.println(dance3.getName() + ":");
        for(Couple couple : dance3Dancers){
            System.out.println(couple.getLead() + " leading " + couple.getFollow());
        }
        System.out.println();
    }

    public boolean isFullForDances(List<Dance> dances){

        // check if any dance they are doing is full
        if(dances.contains(dance1) && dance1Dancers.size() == maxOnfloor){
            return true;
        }

        if(dances.contains(dance2) && dance2Dancers.size() == maxOnfloor){
            return true;
        }

        if(dances.contains(dance3) && dance3Dancers.size() == maxOnfloor){
            return true;
        }

        return false;
    }

    public boolean addCouple(Couple couple, Style style){

        if(!style.equals(this.style)){
            return false;
        }

        // check if dances are full. If full, return false. adding failed
        if(isFullForDances(couple.getDances())){
            return false;
        }

        // add couple to dances they are in
        if(couple.getDances().contains(dance1)){
            dance1Dancers.add(couple);
        }
        if(couple.getDances().contains(dance2)){
            dance2Dancers.add(couple);
        }
        if(couple.getDances().contains(dance3)){
            dance3Dancers.add(couple);
        }

        return true; // success
    }

    /**
     * Check if dancers are in this round
     * @param couple the couple we are checking
     * @param inAnyDance if true, check if lead or follow dance in any dance in the round
     *                    if false, check only the dances that these two are dancing
     * @return true if they dance in this round
     */
    public boolean includesDancers(Couple couple, boolean inAnyDance){
        if(inAnyDance || couple.getDances().contains(dance1)){
            for(Couple dancers : dance1Dancers){
                // attempt to remove the new dancers from the ones in this round.
                // If something changes, then removeAll returns true
                // so if there is overlap, this if will return true
                if(dancers.getDancers().removeAll(couple.getDancers())){
                    return true;
                }
            }
        }
        if(inAnyDance || couple.getDances().contains(dance2)){
            for(Couple dancers : dance2Dancers){
                // attempt to remove the new dancers from the ones in this round.
                // If something changes, then removeAll returns true
                // so if there is overlap, this if will return true
                if(dancers.getDancers().removeAll(couple.getDancers())){
                    return true;
                }
            }
        }
        if(inAnyDance || couple.getDances().contains(dance3)){
            for(Couple dancers : dance3Dancers){
                // attempt to remove the new dancers from the ones in this round.
                // If something changes, then removeAll returns true
                // so if there is overlap, this if will return true
                if(dancers.getDancers().removeAll(couple.getDancers())){
                    return true;
                }
            }
        }

        // no overlap
        return false;

    }


    public String[][] printString(int startingHeatNumber){
        int width = 2 + 2 * maxOnfloor;
        String[][] toPrint = new String[3][width];
        toPrint[0][0] = "" + startingHeatNumber;
        toPrint[1][0] = "" + (startingHeatNumber + 1);
        toPrint[2][0] = "" + (startingHeatNumber + 2);
        toPrint[0][1] = dance1.getName();
        toPrint[1][1] = dance2.getName();
        toPrint[2][1] = dance3.getName();

        for(int i = 0; i < dance1Dancers.size(); i++){
            toPrint[0][i * 2 + 2] = dance1Dancers.get(i).getLead();
            toPrint[0][i * 2 + 3] = dance1Dancers.get(i).getFollow();
        }

        for(int i = 0; i < dance2Dancers.size(); i++){
            toPrint[1][i * 2 + 2] = dance2Dancers.get(i).getLead();
            toPrint[1][i * 2 + 3] = dance2Dancers.get(i).getFollow();
        }

        for(int i = 0; i < dance3Dancers.size(); i++){
            toPrint[2][i * 2 + 2] = dance3Dancers.get(i).getLead();
            toPrint[2][i * 2 + 3] = dance3Dancers.get(i).getFollow();
        }

        return toPrint;

    }

}