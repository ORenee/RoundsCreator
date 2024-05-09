import java.util.List;

public class Round {

    Style style;
    int numberOfDances;

    Heat dance1;
    Heat dance2;
    Heat dance3;
    Heat dance4;
    Heat dance5;

    int maxOnfloor;
    int minOnFloor;

    Round(Style style){
        maxOnfloor = CreateRounds.MAX_ON_FLOOR;
        minOnFloor = CreateRounds.SOFT_MAX;

        this.style = style;
        numberOfDances = StyleManager.getInstance().numberOfDances(style);

        fillHeatsforRound();

    }

    private void fillHeatsforRound(){
        // for this style, create the heats
        // If any dances are not being used, set them to null
        switch(style){
            case RHYTHM:
                dance1 = new Heat(Dance.CHACHA);
                dance2 = new Heat(Dance.RUMBA);
                dance3 = new Heat(Dance.SWING);
                dance4 = numberOfDances > 3 ? new Heat(Dance.MAMBO) : null;
                dance5 = numberOfDances > 4 ? new Heat(Dance.SWING) : null;
                break;
            case STANDARD:
                dance1 = new Heat(Dance.SWALTZ);
                dance2 = new Heat(Dance.STANGO);
                // the dances cut from standard for beginners are in the middle of the round
                // therefore there has to be some adjusting depending on what dances are being done
                if(numberOfDances == 3){
                    dance3 = new Heat(Dance.SQUICKSTEP);
                    dance4 = null;
                    dance5 = null;
                } else if(numberOfDances == 4){
                    dance3 = new Heat(Dance.SFOXTROT);
                    dance4 = new Heat(Dance.SQUICKSTEP);
                    dance5 = null;
                } else{
                    dance3 = new Heat(Dance.SVIENNESE);
                    dance4 = new Heat(Dance.SFOXTROT);
                    dance5 = new Heat(Dance.SQUICKSTEP);
                }
                break;
            case LATIN:
                dance1 = new Heat(Dance.LCHACHA);
                dance2 = new Heat(Dance.SAMBA);
                dance3 = new Heat(Dance.LRUMBA);
                dance4 = new Heat(Dance.PASODOBLE);
                dance5 = new Heat(Dance.JIVE);
                break;
            case SMOOTH:
                dance1 = new Heat(Dance.WALTZ);
                dance2 = new Heat(Dance.TANGO);
                dance3 = new Heat(Dance.FOXTROT);
                dance4 = numberOfDances > 3 ? new Heat(Dance.VIENNESE) : null;
                dance5 = null;
                break;
        }
    }

    public void print(){
        // any dance can be null now because we should have removed empty dances
        if(dance1 != null) {
            System.out.println(dance1.getDance().getName() + ": ");
            for (Couple couple : dance1.getCouples()) {
                System.out.println(couple.getLead() + " leading " + couple.getFollow());
            }
        }
        if(dance2 != null) {
            System.out.println(dance2.getDance().getName() + ": ");
            for (Couple couple : dance2.getCouples()) {
                System.out.println(couple.getLead() + " leading " + couple.getFollow());
            }
        }
        if(dance3 != null) {
            System.out.println(dance3.getDance().getName() + ":");
            for (Couple couple : dance3.getCouples()) {
                System.out.println(couple.getLead() + " leading " + couple.getFollow());
            }
        }
        if(dance4 != null){
            System.out.println(dance4.getDance().getName() + ":");
            for(Couple couple : dance4.getCouples()){
                System.out.println(couple.getLead() + " leading " + couple.getFollow());
            }
        }
        if(dance5 != null){
            System.out.println(dance5.getDance().getName() + ":");
            for(Couple couple : dance5.getCouples()){
                System.out.println(couple.getLead() + " leading " + couple.getFollow());
            }
        }
        System.out.println();
    }

    public boolean isFullForDances(List<Dance> dances, boolean useHardMax){
        int maxToCompare = useHardMax ? maxOnfloor : minOnFloor;

        // check if any dance they are doing is full
        if(dances.contains(dance1.getDance()) && dance1.getCouples().size() >= maxToCompare){
            return true;
        }

        if(dances.contains(dance2.getDance()) && dance2.getCouples().size() >= maxToCompare){
            return true;
        }

        if(dances.contains(dance3.getDance()) && dance3.getCouples().size() >= maxToCompare){
            return true;
        }

        if(dance4 != null && dances.contains(dance4.getDance()) && dance4.getCouples().size() >= maxToCompare){
            return true;
        }

        if(dance5 != null && dances.contains(dance5.getDance()) && dance5.getCouples().size() >= maxToCompare){
            return true;
        }

        return false;
    }

    public boolean addCouple(Couple couple, Style style, int priority){

        if(!style.equals(this.style)){
            return false;
        }

        // check if dances are full at the soft max
        if(priority == 0){
            if(isFullForDances(couple.getDances(), false)){
                // at soft max, move to next round
                return false;
            }
        }
        // this couple needs to be here. See if this round is at hard max
        else if(priority == 1){
            if(isFullForDances(couple.getDances(), true)){
                // at hard max, need to add to next round
                return false;
            }
        }

        // add couple to dances they are in
        if(couple.getDances().contains(dance1.getDance())){
            dance1.addCouple(couple);
        }
        if(couple.getDances().contains(dance2.getDance())){
            dance2.addCouple(couple);
        }
        if(couple.getDances().contains(dance3.getDance())){
            dance3.addCouple(couple);
        }

        if(dance4 != null && couple.getDances().contains(dance4.getDance())){
            dance4.addCouple(couple);
        }
        if(dance5 != null && couple.getDances().contains(dance5.getDance())){
            dance5.addCouple(couple);
        }

        return true; // success
    }

    /**
     * Check if dancers are in this round
     * @param couple the couple we are checking
     * @param checkForPreviousRound
     *      if true, check every dance in case they are dancing with another partner
     *      if false, check just the dances these two are dancing
     * @return true if they dance in this round
     */
    public boolean includesDancers(Couple couple, boolean checkForPreviousRound){

        if(checkForPreviousRound || couple.getDances().contains(dance1.getDance())){
            for(Couple dancers : dance1.getCouples()){
                // attempt to remove the new dancers from the ones in this round.
                // If something changes, then removeAll returns true
                // so if there is overlap, this if will return true
                if(dancers.getDancers().removeAll(couple.getDancers())){
                    return true;
                }
            }
        }
        if(checkForPreviousRound || couple.getDances().contains(dance2.getDance())){
            for(Couple dancers : dance2.getCouples()){
                // attempt to remove the new dancers from the ones in this round.
                // If something changes, then removeAll returns true
                // so if there is overlap, this if will return true
                if(dancers.getDancers().removeAll(couple.getDancers())){
                    return true;
                }
            }
        }
        if(checkForPreviousRound || couple.getDances().contains(dance3.getDance())){
            for(Couple dancers : dance3.getCouples()){
                // attempt to remove the new dancers from the ones in this round.
                // If something changes, then removeAll returns true
                // so if there is overlap, this if will return true
                if(dancers.getDancers().removeAll(couple.getDancers())){
                    return true;
                }
            }
        }

        if(dance4 != null && (checkForPreviousRound || couple.getDances().contains(dance4.getDance()))){
            for(Couple dancers : dance4.getCouples()){
                // attempt to remove the new dancers from the ones in this round.
                // If something changes, then removeAll returns true
                // so if there is overlap, this if will return true
                if(dancers.getDancers().removeAll(couple.getDancers())){
                    return true;
                }
            }
        }
        if(dance5 != null && (checkForPreviousRound || couple.getDances().contains(dance5.getDance()))){
            for(Couple dancers : dance5.getCouples()){
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
        // width = 2 is the heat number and name of the dance +
        // 2 * maxOnFloor is the maximum number of individuals on the floor
        int width = 2 + 2 * maxOnfloor;
        // height is the number of dances. Since a heat could be empty,
        // we have to recalculate the number of dances that we have
        int height = 0;
        if(dance1 != null) { height += 1; }
        if(dance2 != null) { height += 1; }
        if(dance3 != null) { height += 1; }
        if(dance4 != null) { height += 1; }
        if(dance5 != null) { height += 1; }
        // create the string array. height is number of dances in this round
        String[][] toPrint = new String[height][width];

        // input the heat numbers
        for(int row = 0; row < height; ++row){
            toPrint[row][0] = "" + (startingHeatNumber + row); // if we start heats at #1, first heat number is 1 + 0
        }

        // input the dance names
        int position = 0;
        if(dance1 != null){
            toPrint[position][1] = dance1.getDance().getName();
            position += 1;
        }
        if(dance2 != null){
            toPrint[position][1] = dance2.getDance().getName();
            position += 1;
        }
        if(dance3 != null){
            toPrint[position][1] = dance3.getDance().getName();
            position += 1;
        }
        if(dance4 != null){
            toPrint[position][1] = dance4.getDance().getName();
            position += 1;
        }
        if(dance5 != null){
            toPrint[position][1] = dance5.getDance().getName();
            position += 1;
        }

        // input the names of dancers
        position = 0;
        if(dance1 != null){
            for(int i = 0; i < dance1.getCouples().size(); i++){
                toPrint[position][i * 2 + 2] = dance1.getCouples().get(i).getLead();
                toPrint[position][i * 2 + 3] = dance1.getCouples().get(i).getFollow();
            }
            position += 1;
        }
        if(dance2 != null){
            for(int i = 0; i < dance2.getCouples().size(); i++){
                toPrint[position][i * 2 + 2] = dance2.getCouples().get(i).getLead();
                toPrint[position][i * 2 + 3] = dance2.getCouples().get(i).getFollow();
            }
            position += 1;
        }
        if(dance3 != null){
            for(int i = 0; i < dance3.getCouples().size(); i++){
                toPrint[position][i * 2 + 2] = dance3.getCouples().get(i).getLead();
                toPrint[position][i * 2 + 3] = dance3.getCouples().get(i).getFollow();
            }
            position += 1;
        }
        if(dance4 != null){
            for(int i = 0; i < dance4.getCouples().size(); i++){
                toPrint[position][i * 2 + 2] = dance4.getCouples().get(i).getLead();
                toPrint[position][i * 2 + 3] = dance4.getCouples().get(i).getFollow();
            }
            position += 1;
        }

        if(dance5 != null){
            for(int i = 0; i < dance5.getCouples().size(); i++){
                toPrint[position][i * 2 + 2] = dance5.getCouples().get(i).getLead();
                toPrint[position][i * 2 + 3] = dance5.getCouples().get(i).getFollow();
            }
            position += 1;
        }

        return toPrint;

    }

    public int getLeastOnFloor(){

        int min = Math.min(Math.min(dance1.getCouples().size(), dance2.getCouples().size()), dance3.getCouples().size());

        if(dance4 != null) {
            min = Math.min(min, dance4.getCouples().size());
        }

        if(dance5 != null) {
            min = Math.min(min, dance5.getCouples().size());
        }

        return min;
    }

    /**
     * NewRound and this round MUST BE THE SAME STYLE WITH THE SAME NUMBER OF DANCES
     * @param newRound
     */
    public Round addToRoundWhereOnlyOneCouple(Round newRound){
        if(style != newRound.style){
            System.err.println("Trying to add a round with different types");
            return null;
        }
        if(dance1 == null) {
            if (newRound.dance1.getCouples().size() == 1 && this.dance1.getCouples().size() < maxOnfloor) {
                dance1.addCouples(newRound.dance1.getCouples());
                newRound.removeDance(dance1.getDance());
            }
        }
        if(dance2 != null) {
            if (newRound.dance2.getCouples().size() == 1 && this.dance2.getCouples().size() < maxOnfloor) {
                dance2.addCouples(newRound.dance2.getCouples());
                newRound.removeDance(dance2.getDance());
            }
        }
        if(dance3 != null) {
            if (newRound.dance3.getCouples().size() == 1 && this.dance3.getCouples().size() < maxOnfloor) {
                dance3.addCouples(newRound.dance3.getCouples());
                newRound.removeDance(dance3.getDance());
            }
        }
        if(dance4 != null){
            if(newRound.dance4.getCouples().size() == 1 && this.dance4.getCouples().size() < maxOnfloor){
                dance4.addCouples(newRound.dance4.getCouples());
                newRound.removeDance(dance4.getDance());
            }
        }
        if(dance5 != null){
            if(newRound.dance5.getCouples().size() == 1 && this.dance5.getCouples().size() < maxOnfloor){
                dance5.addCouples(newRound.dance5.getCouples());
                newRound.removeDance(dance5.getDance());
            }
        }
        return newRound;
    }

    public boolean isEmpty(){
        boolean dancesEmpty = dance1.getCouples().isEmpty()
                && dance2.getCouples().isEmpty()
                && dance3.getCouples().isEmpty();
        if(dance4 != null){
            dancesEmpty = dancesEmpty && dance4.getCouples().isEmpty();
        }
        if(dance5 != null){
            dancesEmpty = dancesEmpty && dance5.getCouples().isEmpty();
        }
        return dancesEmpty;
    }

    /**
     * Remove empty dances from the list.
     * DO NOT MANIPULATE THIS ROUND AFTER RUNNING THIS
     */
    public void removeEmptyDances(){
        if(dance1 != null && dance1.getCouples().isEmpty()) {
            dance1 = null;
        }
        if(dance2 != null && dance2.getCouples().isEmpty()) {
            dance2 = null;
        }
        if(dance3 != null && dance3.getCouples().isEmpty()) {
            dance3 = null;
        }
        if(dance4 != null && dance4.getCouples().isEmpty()) {
            dance4 = null;
        }
        if(dance5 != null && dance5.getCouples().isEmpty()) {
            dance5 = null;
        }
    }

    public void removeDance(Dance dance){
        if(dance1 != null && dance == dance1.getDance()) {
            dance1 = null;
        }
        if(dance2 != null && dance == dance2.getDance()) {
            dance2 = null;
        }
        if(dance3 != null && dance == dance3.getDance()) {
            dance3 = null;
        }
        if(dance4 != null && dance == dance4.getDance()) {
            dance4 = null;
        }
        if(dance5 != null && dance == dance5.getDance()) {
            dance5 = null;
        }
    }

}