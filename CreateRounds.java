import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class CreateRounds {

    private static List<Round> rounds;
    private static Style currentStyle;

    // specifies how many couples can be on the floor at once
    public static final int MAX_ON_FLOOR = 4;
    // specifies the minimum number of couples on the floor. Must be less than or equal to max
    public static int SOFT_MAX = 3;
    //!! if min and max are the same number, not dancing consecutive rounds is not guaranteed !!

    public static final boolean BALANCED_HEATS = false;

    // check for dancing back-to-back rounds
    private static final boolean CAN_DANCE_CONSECUTIVE_ROUNDS = false;

    public static List<Round> getRounds(boolean redoRounds){
        // only run algo again if it hasn't been run before or the user wants to redo it
        if(redoRounds || rounds == null){
            createRounds();
        }
        return rounds;
    }

    // -- PRINT RESULTS --
    public static void printRounds(boolean redoRounds){
        if(redoRounds || rounds == null){
            createRounds();
        }

        try {
            CSVFileWriter.createOutput(rounds);
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i = 1; i <= rounds.size(); ++i) {

            if(rounds.get(i - 1).requireBreakBefore){
                System.out.println("Required Break\n");
            }
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
        if(BALANCED_HEATS){
            // if balanced, soft max is always what user inputed + 1
            // because average will be one less than what you'd think
            SOFT_MAX = SOFT_MAX + 1;
        }

        // read couples from csv
        List<Couple> couples = FileReader.readCouplesFromCSV("partners.csv");

        rounds = new ArrayList<>();

        //---- CREATE SMOOTH ROUNDS ----

        currentStyle = Style.RHYTHM;
        rounds.add(new Round(currentStyle));

        // get all the couples dancing rhythm
        List<Couple> rhythmCouples = couples;
        rhythmCouples.removeIf(x -> (!x.dancesRhythm()));

        rhythmCouples = IndividualTracker.sortCouplesByOccurances(rhythmCouples);

        for (Couple couple : rhythmCouples) {
            if(BALANCED_HEATS){
                // prioritizes balancing the heats. May result in less than optimal number of couples on the floor
                // add to the round with the least amount of couples in it
                Round min = rounds.stream().filter(x -> x.style == Style.RHYTHM)
                        .min(Comparator.comparingDouble(Round::getLeastOnFloor)).orElse(null); // will never be null
                addCouple(couple, rounds.indexOf(min), 0);
            } else{
                // prioritizing number of couples on the floor. May result in heats with only one couple
                // fills rounds sequentially
                addCouple(couple, findRoundNumber(couple), 0);
            }
        }


        //---- CREATE SMOOTH ROUNDS ----

        currentStyle = Style.SMOOTH;
        rounds.add(new Round(currentStyle));

        // get all the couples dancing rhythm
        List<Couple> smoothCouples = couples;
        smoothCouples.removeIf(x -> (!x.dancesSmooth()));

        smoothCouples = IndividualTracker.sortCouplesByOccurances(smoothCouples);

        for (Couple couple : smoothCouples) {
            if(BALANCED_HEATS){
                // prioritizes balancing the heats. May result in less than optimal number of couples on the floor
                // add to the round with the least amount of couples in it
                Round min = rounds.stream().filter(x -> x.style == Style.RHYTHM)
                        .min(Comparator.comparingDouble(Round::getLeastOnFloor)).orElse(null); // will never be null
                addCouple(couple, rounds.indexOf(min), 0);
            } else{
                // prioritizing number of couples on the floor. May result in heats with only one couple
                // fills rounds sequentially
                addCouple(couple, findRoundNumber(couple), 0);
            }
        }


        //--- Remove single couples and heats with no dancers ----

        // start at the end because any rounds with a single couple will be added to the previous round
        for (int i = rounds.size(); i > 0; --i) {
            Round current = rounds.get(i - 1);
            // TODO: create indicator that a break is required if a couple is moved to a previous round
            //  or if an empty round is deleted and it is not at the end (should never happen at the end but oh well)

            if(current.isEmpty()){
                rounds.remove(current);
            }

            current.removeEmptyDances();

            // if a round has dances where there is 1 person on the floor,
            // add this round to the previous r
            if(current.getLeastOnFloor() == 1){
                // the first round should almost never have this problem.
                // if it does, that means only one couple is dancing this
                // style so leave it as it is
                if(i > 1 && rounds.get(i-2).style == current.style){
                    // add this round to previous round
                    Round result = rounds.get(i - 2).addToRoundWhereOnlyOneCouple(current);
                    rounds.get(i - 2).requireBreakBefore = true;
                    if(result != null){
                        rounds.set(rounds.indexOf(current), result);
                    } else { // result is null so the current round is now empty
                        rounds.remove(current);
                    }
                }
            }

        }

    }


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
            if (rounds.get(i).style.equals(currentStyle) && !rounds.get(i).isFullForDances(couple.getDances(), false)) {
                roundNumber = i;
                break;
            }
        }
        return roundNumber;
    }


}

