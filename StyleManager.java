public class StyleManager {

    private static StyleManager styleManager = null;

    private int numRhythm;
    private int numStandard;
    private int numLatin;
    private int numSmooth;

    private StyleManager(){
        numRhythm = 3;
        numStandard = 5;
        numLatin = 5;
        numSmooth = 3;
    };

    // implementation of singleton
    public static StyleManager getInstance(){
        if(styleManager == null){
            styleManager = new StyleManager();
        }

        return styleManager;
    }

    /**
     * Get the number of dances in a particular style
     * @param style
     * @return
     */
    public int numberOfDances(Style style){
        switch (style){
            case RHYTHM:
                return numRhythm;
            case STANDARD:
                return numStandard;
            case LATIN:
                return numLatin;
            case SMOOTH:
                return numSmooth;
            default:
                System.err.println("Unknown style selected");
                return 0;
        }
    }

    /**
     * Set the number of dances in each style depending on what dances are present at this showcase
     * @pre all ints must be at least 3 and no greater than 5
     *
     * @param rhythm
     * @param standard
     * @param latin
     * @param smooth
     */
    public void setNumberofDances(int rhythm, int standard, int latin, int smooth){
        numRhythm = rhythm;
        numStandard = standard;
        numLatin = latin;
        numSmooth = smooth;
    }
}
