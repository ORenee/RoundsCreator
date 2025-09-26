package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoundInfo {
    List<String> dancers;

    int dance1Num;
    int dance2Num;
    int dance3Num;
    int dance4Num;
    int dance5Num;

    private final List<Dance> dances;

    private final Style style;

    RoundInfo(Style style){
        this.style = style;
        this.dancers = new ArrayList<>();
        if(style == Style.RHYTHM){
            this.dances = Arrays.asList(Dance.CHACHA, Dance.RUMBA, Dance.SWING, Dance.BOLERO, Dance.MAMBO);
        }
        else if(style == Style.SMOOTH){
            this.dances = Arrays.asList(Dance.WALTZ, Dance.TANGO, Dance.FOXTROT, Dance.VIENNESE);
        }
        else if(style == Style.LATIN){
            this.dances = Arrays.asList(Dance.LCHACHA, Dance.SAMBA, Dance.LRUMBA, Dance.PASODOBLE, Dance.JIVE);
        }
        else if(style == Style.STANDARD){
            this.dances = Arrays.asList(Dance.SWALTZ, Dance.STANGO, Dance.SVIENNESE, Dance.SFOXTROT, Dance.SQUICKSTEP);
        }
        else {
            this.dances = new ArrayList<>();
            // style not an actual style. Should never happen
        }
    }

    public void addCouple(Couple couple){
        dancers.addAll(couple.getDancers());
        List<Dance> coupleDances = couple.getDances();
        coupleDances.removeIf(x -> !x.isRhythm());

        if(coupleDances.contains(dances.get(0))){
            dance1Num++;
        }
        if(coupleDances.contains(dances.get(1))){
            dance2Num++;
        }
        if(coupleDances.contains(dances.get(2))){
            dance3Num++;
        }
        if(coupleDances.contains(dances.get(3))){
            dance4Num++;
        }
        if(dances.size() > 4 && coupleDances.contains(dances.get(4))){
            dance5Num++;
        }
    }
}
