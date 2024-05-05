//import java.util.List;
//
//public class SmoothRound extends Round {
//
//    // dance1 = waltz
//    // dance2 = tango
//    // dance3 = foxtrot
//
//    SmoothRound(int maxOnfloor){
//        super(maxOnfloor);
//    }
//
//    public void print(){
//        System.out.println("Waltz:");
//        for(Couple couple : dance1Dancers){
//            System.out.println(couple.getLead() + " leading " + couple.getFollow());
//        }
//        System.out.println("Tango:");
//        for(Couple couple : dance2Dancers){
//            System.out.println(couple.getLead() + " leading " + couple.getFollow());
//        }
//        System.out.println("Foxtrot:");
//        for(Couple couple : dance3Dancers){
//            System.out.println(couple.getLead() + " leading " + couple.getFollow());
//        }
//        System.out.println();
//    }
//
//    public boolean isFullForDances(List<Dance> dances){
//
//        // check if any dance they are doing is full. If so, adding them will fail
//        if(dances.contains(Dance.WALTZ) && dance1Dancers.size() == maxOnfloor){
//            return true;
//        }
//
//        if(dances.contains(Dance.TANGO) && dance2Dancers.size() == maxOnfloor){
//            return true;
//        }
//
//        if(dances.contains(Dance.FOXTROT) && dance3Dancers.size() == maxOnfloor){
//            return true;
//        }
//
//        return false;
//    }
//
//    public boolean addCouple(Couple couple){
//        // track which dances this couple is doing
//        boolean inChaCha = couple.getDances().contains(Dance.WALTZ);
//        boolean inRumba = couple.getDances().contains(Dance.TANGO);
//        boolean inSwing = couple.getDances().contains(Dance.FOXTROT);
//
//        if(isFullForDances(couple.getDances())){
//            return false;
//        }
//
//        // add couple to dances they are in
//        if(inChaCha){
//            dance1Dancers.add(couple);
//        }
//        if(inRumba){
//            dance2Dancers.add(couple);
//        }
//        if(inSwing){
//            dance3Dancers.add(couple);
//        }
//
//        return true; // success
//    }
//
//    /**
//     * Check if dancers are in this round
//     * @param couple the couple we are checking
//     * @param inAnyDance if true, check if lead or follow dance in any dance in the round
//     *                    if false, check only the dances that these two are dancing
//     * @return true if they dance in this round
//     */
//    public boolean includesDancers(Couple couple, boolean inAnyDance){
//        if(inAnyDance || couple.getDances().contains(Dance.WALTZ)){
//            for(Couple dancers : dance1Dancers){
//                // attempt to remove the new dancers from the ones in this round.
//                // If something changes, then removeAll returns true
//                // so if there is overlap, this if will return true
//                if(dancers.getDancers().removeAll(couple.getDancers())){
//                    return true;
//                }
//            }
//        }
//        if(inAnyDance || couple.getDances().contains(Dance.TANGO)){
//            for(Couple dancers : dance2Dancers){
//                // attempt to remove the new dancers from the ones in this round.
//                // If something changes, then removeAll returns true
//                // so if there is overlap, this if will return true
//                if(dancers.getDancers().removeAll(couple.getDancers())){
//                    return true;
//                }
//            }
//        }
//        if(inAnyDance || couple.getDances().contains(Dance.FOXTROT)){
//            for(Couple dancers : dance3Dancers){
//                // attempt to remove the new dancers from the ones in this round.
//                // If something changes, then removeAll returns true
//                // so if there is overlap, this if will return true
//                if(dancers.getDancers().removeAll(couple.getDancers())){
//                    return true;
//                }
//            }
//        }
//
//        // no overlap
//        return false;
//
//    }
//
//};