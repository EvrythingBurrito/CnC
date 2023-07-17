
package source;
import java.util.*;

class Encounter {

    String encounterName;
    List<Encounter> encounterDeck;

    public Encounter(String aName) {
        encounterName = aName;
    }

    public String getName() {
        return encounterName;
    }

}