package Model;

public enum Style{
    UNKNOWN(0),

    // in order of performance
    RHYTHM(1),
    STANDARD(2),
    LATIN(3),
    SMOOTH(4);
    

    private int value;

    private Style(int value) {
            this.value = value;
    }

}
