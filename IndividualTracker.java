import java.util.*;
import java.util.stream.Collectors;

public class IndividualTracker {
    private static Map<String, List<Integer>> individuals = new HashMap<>();

    /**
     * Track the number of times and person dances and save the rounds they are in.
     * Used for sorting the couples list later
     * @param couple
     * @param index of the couple in the list
     */
    public static void trackCouple(Couple couple, int index){
        String lead = couple.getLead();
        String follow = couple.getFollow();


        if(individuals.containsKey(lead)){
            List<Integer> temp = new ArrayList<>(individuals.get(lead));
            temp.add(index);
            individuals.put(lead, temp);
        } else {
            individuals.put(lead, List.of(index));
        }

        if(individuals.containsKey(follow)){
            List<Integer> temp = new ArrayList<>(individuals.get(follow));
            temp.add(index);
            individuals.put(follow, temp);
        } else {
            individuals.put(follow, List.of(index));
        }
    }

    public static List<Couple> sortCouplesByOccurances(List<Couple> couples){

        List<String>[] bucket = new List[individuals.size() + 1];
        for (String key : individuals.keySet()) {
            int frequency = individuals.get(key).size();
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        // create a new list. Needs to be a linked hash set so there are no duplicates added
        LinkedHashSet<Couple> result = new LinkedHashSet<>();
        // loop through the buckets
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) { // if the bucket exists
                // loop through the people in the bucket
                for(String person : bucket[i]){
                    // add each couple that this person is in to the results list
                    for(Integer index : individuals.get(person)) {
                        result.add(couples.get(index));
                    }

                }
            }
        }
        return new ArrayList<>(result);

    }


    // for testing purposes only
    public static void print(){

        for (String key : individuals.keySet()) {
            System.out.println(key + " - " + individuals.get(key));
        }

    }

}
