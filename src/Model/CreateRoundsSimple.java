package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CreateRoundsSimple {

    private static List<Round> rounds;
    private static Style currentStyle;
    private static List<Dance> dancesInStyle;

    public static final int MAX_ON_FLOOR = 3;

    public static List<Round> createRounds(List<Couple> couples) {

        rounds = new ArrayList<>();

        //---- CREATE RHYTHM ROUNDS ----

        currentStyle = Style.RHYTHM; // set the style
        dancesInStyle = Arrays.asList(Dance.CHACHA, Dance.RUMBA, Dance.SWING, Dance.BOLERO, Dance.MAMBO); // list possible dances
        rounds.add(new Round(currentStyle)); // add the current style to the round

        // get all the couples dancing rhythm
        List<Couple> rhythmCouples = new ArrayList<>(couples);
        rhythmCouples.removeIf(x -> (!x.dancesRhythm()));

        // add couples that dance a lot first
        rhythmCouples = IndividualTracker.sortCouplesByOccurances(rhythmCouples);

        int roundNum = 0;
        // for every couple
        for (Couple couple : rhythmCouples) {
            // check if we can add the couple. If we can't add it, go to the next round
            while(!canAddToRound(couple, roundNum)){ // can't add to this round
                roundNum++; // increment round number
                if(rounds.size() <= roundNum){ // if we've tried every existing round,
                    rounds.add(new Round(currentStyle)); // add a new round
                }
            }
            // we can add the couple because we passed the while loop
            rounds.get(roundNum).addCouple(couple, currentStyle, 1); // add the couple to the rounds
            roundNum = 0;
        }

        //---- CREATE SMOOTH ROUNDS ----

        currentStyle = Style.SMOOTH;
        dancesInStyle = Arrays.asList(Dance.WALTZ, Dance.TANGO, Dance.FOXTROT, Dance.VIENNESE);
        rounds.add(new Round(currentStyle));

        // get all the couples dancing rhythm
        List<Couple> smoothCouples = new ArrayList<>(couples);
        smoothCouples.removeIf(x -> (!x.dancesSmooth()));

        smoothCouples = IndividualTracker.sortCouplesByOccurances(smoothCouples);

        int smoothBeginsAt = rounds.size() - 1;
        roundNum = smoothBeginsAt;
        for (Couple couple : smoothCouples) {
            while(!canAddToRound(couple, roundNum)){
                roundNum++;
                if(rounds.size() <= roundNum){
                    rounds.add(new Round(currentStyle));
                }
            }
            rounds.get(roundNum).addCouple(couple, currentStyle, 1);
            roundNum = smoothBeginsAt;
        }


        // remove heats that are empty
        for(Round round : rounds){
            round.removeEmptyDances();
        }

        return rounds;
    }

    private static boolean canAddToRound(Couple couple, int roundNum){
        RoundInfo curRound = rounds.get(roundNum).roundInfo;
        List<Dance> coupleDances = couple.getDances();

        // if the current round is empty, we can add
        if(curRound.dancers.isEmpty()){
            return true;
        }
        // if the current round already includes this lead, cannot add
        if(curRound.dancers.contains(couple.getLead())){
            return false;
        }
        // if the current round already includes this follow, cannot add
        if(curRound.dancers.contains(couple.getFollow())){
            return false;
        }

        if(coupleDances.contains(dancesInStyle.get(0)) && // if the couple is dancing the first dance,
                curRound.dance1Num >= MAX_ON_FLOOR){ // and the first dance is not full
            return false;
        }
        if(coupleDances.contains(dancesInStyle.get(1)) && // if the couple is dancing the second dance,
                curRound.dance2Num >= MAX_ON_FLOOR){ // and the second dance is not full
            return false;
        }
        if(coupleDances.contains(dancesInStyle.get(2)) && curRound.dance3Num >= MAX_ON_FLOOR){
            return false;
        }
        if(coupleDances.contains(dancesInStyle.get(3)) && curRound.dance4Num >= MAX_ON_FLOOR){
            return false;
        }
        // some styles do not have 5 styles, so check if there are
        if(dancesInStyle.size() > 4
                && coupleDances.contains(dancesInStyle.get(4))
                && curRound.dance5Num >= MAX_ON_FLOOR){
            return false;
        }
        // everything is fine, so we can add this couple
        return true;
    }

}
