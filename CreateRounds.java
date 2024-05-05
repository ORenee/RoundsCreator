import java.util.ArrayList;
import java.util.List;


public class CreateRounds {

    private static List<Round> rounds;
    private static Style currentStyle;

    // specifies how many couples can be on the floor at once
    private static final int MAX_ON_FLOOR = 3;

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

        rounds = new ArrayList<>();

        //---- CREATE SMOOTH ROUNDS ----

        currentStyle = Style.RHYTHM;
        rounds.add(new Round(MAX_ON_FLOOR, currentStyle));

        // get all the couples dancing rhythm
        List<Couple> rhythmCouples = couples;
        rhythmCouples.removeIf(x -> (!x.dancesRhythm()));
        //rhythmCouples = rhythmCouples.subList(0, 4);

        for (Couple couple : rhythmCouples) {
            addCouple(couple, findRoundNumber(couple));
        }

        //---- CREATE SMOOTH ROUNDS ----

        currentStyle = Style.SMOOTH;
        rounds.add(new Round(MAX_ON_FLOOR, currentStyle));

        // get all the couples dancing rhythm
        List<Couple> smoothCouples = couples;
        smoothCouples.removeIf(x -> (!x.dancesSmooth()));
        //smoothCouples = smoothCouples.subList(0, 4);

        for (Couple couple : smoothCouples) {
            addCouple(couple, findRoundNumber(couple));
        }

    }

    //TODO: someone can be added to a round before one they are already in (so they dance back to back)
    public static void addCouple(Couple couple, int roundNumber) {

        // check if these dancers were in the previous round
        if (!CAN_DANCE_CONSECUTIVE_ROUNDS && roundNumber > 0) {
            if (rounds.get(roundNumber - 1).includesDancers(couple, true)) {
                // at least one of these dancers were in previous round. add them to the next round
                addToNextRound(couple, roundNumber);
                return;
            }
        }

        // check if these dancers are already in this round for these dances
        if (rounds.get(roundNumber).includesDancers(couple, false)) {
            // at least one of these dancers are already in this round. add them to the next round

            addToNextRound(couple, roundNumber);
            return;
        }

        if (!rounds.get(roundNumber).addCouple(couple, currentStyle)) {
            addToNextRound(couple, roundNumber);
            return;
        }
    }

    private static void addToNextRound(Couple couple, int roundNumber) {
        if (rounds.size() <= roundNumber + 1) {
            rounds.add(new Round(MAX_ON_FLOOR, currentStyle));
        }

        addCouple(couple, roundNumber + 1);
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

}

