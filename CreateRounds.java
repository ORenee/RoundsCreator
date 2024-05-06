import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CreateRounds {

    private static List<Round> rounds;
    private static Style currentStyle;

    // specifies how many couples can be on the floor at once
    public static final int MAX_ON_FLOOR = 4;
    // specifies the minimum number of couples on the floor. Must be less than max
    public static final int SOFT_MAX = 2;
    //!! if min and max are the same number, not dancing consecutive rounds is not guaranteed !!

    // check for dancing back-to-back rounds
    private static final boolean CAN_DANCE_CONSECUTIVE_ROUNDS = false;

    public static List<Round> getRounds(){
        createRounds();
        return rounds;
    }

    // -- PRINT RESULTS --
    public static void printRounds(){
        createRounds();

        try {
            CSVFileWriter.createOutput(rounds);
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i = 1; i <= rounds.size(); ++i) {
            System.out.println("Round " + i);
            rounds.get(i - 1).print();
        }

        int roundsize = rounds.size();

        double minutesForAll = roundsize * 5;
        int totalBreaks = roundsize / 2;
        double minutesPlusBreaks = minutesForAll + totalBreaks * 5;

        System.out.println("\nTotal minutes at 5 minutes per round: " + minutesForAll);
        System.out.println("Total minutes plus games/socials: " + minutesPlusBreaks);

    }

    public static void createRounds() {
        // read couples from csv
        List<Couple> couples = FileReader.readCouplesFromCSV("partners.csv");

        //TODO: couples need to have unique IDs so that this method can be calld for just
        // smooth or rhythm dances. That way they will be better organized within the types of dances
        // someone is doing
        couples = IndividualTracker.sortCouplesByOccurances(couples);

        for(Couple c : couples){
            c.print();
        }


        rounds = new ArrayList<>();

        //---- CREATE SMOOTH ROUNDS ----

        currentStyle = Style.RHYTHM;
        rounds.add(new Round(currentStyle));

        // get all the couples dancing rhythm
        List<Couple> rhythmCouples = couples;
        rhythmCouples.removeIf(x -> (!x.dancesRhythm()));
        //rhythmCouples = rhythmCouples.subList(0, 4);

        for (Couple couple : rhythmCouples) {
            addCouple(couple, findRoundNumber(couple), 0);
        }

        //---- CREATE SMOOTH ROUNDS ----

        currentStyle = Style.SMOOTH;
        rounds.add(new Round(currentStyle));

        // get all the couples dancing rhythm
        List<Couple> smoothCouples = couples;
        smoothCouples.removeIf(x -> (!x.dancesSmooth()));
        //smoothCouples = smoothCouples.subList(0, 4);

        for (Couple couple : smoothCouples) {
            addCouple(couple, findRoundNumber(couple), 0);
        }

    }

    //TODO: if someone is just doing the first dance in a round, they can dance in the next round.

    // priority is if these dancers need to be added to this round. 1 is high priority 0 is low
    public static void addCouple(Couple couple, int roundNumber, int priority) {

        // check if these dancers were in the previous round
        if (!CAN_DANCE_CONSECUTIVE_ROUNDS && roundNumber > 0) {
            if (rounds.get(roundNumber - 1).includesDancers(couple, true)) {
                // at least one of these dancers were in previous round. add them to the next round
                // priority always 1 because these dancers have restrictions of where they can go
                addToNextRound(couple, roundNumber, 1);
                return;
            }
        }

        // check if these dancers are already in this round for these dances
        if (rounds.get(roundNumber).includesDancers(couple, false)) {
            // at least one of these dancers are already in this round. add them to the next round

            addToNextRound(couple, roundNumber, priority);
            return;
        }

        // Attempt to add couple to this round
        if (!rounds.get(roundNumber).addCouple(couple, currentStyle, priority)) {
            // error occurred with adding. Add couple to next round
            addToNextRound(couple, roundNumber, priority);
            return;
        }
    }

    // priority is if these dancers need to be added to this round. 1 is high priority 0 is low
    private static void addToNextRound(Couple couple, int roundNumber, int priority) {
        if (rounds.size() <= roundNumber + 1) {
            rounds.add(new Round(currentStyle));
        }

        addCouple(couple, roundNumber + 1, priority);
    }

    private static int findRoundNumber(Couple couple) {
        // need to find the first round that is not full
        int roundNumber = 0;
        for (int i = 0; i < rounds.size(); ++i) {
            if (rounds.get(i).style.equals(currentStyle) && !rounds.get(i).isFullForDances(couple.getDances())) {
                roundNumber = i;
                break;
            }
        }
        return roundNumber;
    }

//    public List<Couple> frequencyBasedSort(List<Couple> couples, int k) {
//
//        // create hash on number times a dancer appears
//        List<Couple>[] bucket = new List[couples.size() + 1];
//        Map<Couple, Pair<Integer, Integer>> freqMap = new HashMap<>();
//        for (Couple n : couples) {
//            Pair<Integer, Integer> curCount = freqMap.get(n);
//            if(curCount != null){
//
//                freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
//            }
//
//        }
//        for (String key : freqMap.keySet()) {
//            int frequency = freqMap.get(key);
//            if (bucket[frequency] == null) {
//                bucket[frequency] = new ArrayList<>();
//            }
//            bucket[frequency].add(key);
//        }
//
//        //sort list
//        List<Couple> res = new ArrayList<>();
//        for (int i = bucket.length - 1; i >= 0; i--) {
//            if (bucket[i] != null) {
//                res.addAll(bucket[i]);
//            }
//        }
//        return res;
//    }
//
//    public List<Couple> frequencyBasedSorts(List<Couple> couples, int k) {
//
//        List<Couple>[] bucket = new List[couples.size() + 1];
//        Map<Couple, Integer> freqMap = new HashMap<>();
//        for (Couple n : couples) {
//            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
//        }
//        for (Couple key : freqMap.keySet()) {
//            int frequency = freqMap.get(key);
//            if (bucket[frequency] == null) {
//                bucket[frequency] = new ArrayList<>();
//            }
//            bucket[frequency].add(key);
//        }
//        List<Couple> res = new ArrayList<>();
//        for (int i = bucket.length - 1; i >= 0; i--) {
//            if (bucket[i] != null) {
//                res.addAll(bucket[i]);
//            }
//        }
//        return res;
//    }

}

