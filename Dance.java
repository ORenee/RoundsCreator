

public enum Dance {
    UNKNOWN(0),
    
    // rhythm is 0x
    CHACHA(1), 
    RUMBA(2), 
    SWING(3),
    BOLERO(4),
    MAMBO(5),

    // smooth is 1x
    WALTZ(11),
    TANGO(12),
    FOXTROT(13),
    VIENNESE(14),

    // standard is 2x
    SWALTZ(21),
    STANGO(22),
    SVIENNESE(23),
    SFOXTROT(24),
    SQUICKSTEP(25),

    // latin is 3x
    LCHACHA(31),
    SAMBA(32),
    LRUMBA(33),
    PASODOBLE(34),
    JIVE(35);
    
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
            case MAMBO:
                return "Mambo";
            case BOLERO:
                return "Bolero";
            case WALTZ:
                return "Waltz";
            case TANGO:
                return "Tango";
            case FOXTROT:
                return "Foxtrot";
            case VIENNESE:
                return "Viennese Waltz";
            case SWALTZ:
                return "Waltz"; 
            case SFOXTROT:
                return "Foxtrot";
            case SVIENNESE:
                return "Viennese Waltz";
            case STANGO:
                return "Tango";
            case SQUICKSTEP:     
                return "Quickstep";
            case LCHACHA:
                return "Cha Cha";
            case SAMBA:
                return "Samba";
            case LRUMBA:
                return "Rumba";
            case PASODOBLE:
                return "Paso Doble";
            case JIVE:
                return "Jive";
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

    public boolean isLatin(){
        return this.value >= 30 && this.value < 40;
    }

}; 