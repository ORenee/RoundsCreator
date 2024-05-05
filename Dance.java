

public enum Dance {
    UNKNOWN(0),
    
    // rhythm is 0x
    CHACHA(1), 
    RUMBA(2), 
    SWING(3),

    // smooth is 1x
    WALTZ(11),
    TANGO(12),
    FOXTROT(13),

    // standard is 2x
    SWALTZ(21),
    STANGO(22),
    SFOXTROT(24),
    SQUICKSTEP(25);
    
    private int value;

    private Dance(int value) {
            this.value = value;
    }

    public String getName(){
        switch (this) {
            case CHACHA:
                return "Cha Cha";
            case RUMBA:
                return "Rumba";
            case SWING:
                return "Swing";
            case WALTZ:
                return "Waltz";
            case TANGO:
                return "Tango";
            case FOXTROT:
                return "Foxtrot";
            case SWALTZ:
                return "Waltz"; 
            case SFOXTROT:
                return "Foxtrot";
            case STANGO:
                return "Tango";
            case SQUICKSTEP:     
                return "Quickstep";          
            default:
                return "Unknown";
        }
    }

    public boolean isRhythm(){
        return this.value >= 1 && this.value < 10;
    }

    public boolean isSmooth(){
        return this.value >= 10 && this.value < 20;
    }

    public boolean isStandard(){
        return this.value >= 20 && this.value < 30;
    }

}; 