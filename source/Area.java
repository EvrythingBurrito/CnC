
package source;
import java.util.*;

class Area {

    String areaName;
    List<Landmark> discoveredLandmarks;
    Landmark currentLandmark;
    List<Encounter> encounterDeck;

    public Area(String aName, Landmark initialLandmark) {
        areaName = aName;
        discoveredLandmarks = new ArrayList<Landmark>();
        currentLandmark = initialLandmark;
        encounterDeck = new ArrayList<Encounter>();
    }

    // print all known landmark names
    public ArrayList<String> getDiscoveredLandmarkNames() {
        ArrayList<String> namesList = new ArrayList<String>();
        discoveredLandmarks.forEach((lm) -> namesList.add(lm.getName()));
        return namesList;
    }

    // show all known landmarks, print selected landmark description 
    public void queryLandmarkHop() throws Exception {
        ConsoleListMenu areaConsole = new ConsoleListMenu(getDiscoveredLandmarkNames());
        String userSelection = new String(areaConsole.selectFromOptions());
        discoveredLandmarks.forEach((lm) -> {
            if (userSelection == lm.getName()) { 
                System.out.println(lm.getDescription()); 
            }
        });
    }

    // generate travel landmark, run landmark encounter
    public void runGeneratedLandmark() {
        // take a random encounter from the area encounter deck
        Collections.shuffle(encounterDeck);
        Encounter thisEncounter = encounterDeck.get(0);
        currentLandmark = new Landmark(thisEncounter.getName() + "_landmark", thisEncounter);
        discoveredLandmarks.add(currentLandmark);
        // run landmark encounter
        try {
            currentLandmark.runCurrentEncounter();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return;
    }

}