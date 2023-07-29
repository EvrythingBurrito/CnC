
package client;
import java.util.*;

import ConsoleMenu.*;

/* AREA CLASS
 *
 *      An area is the main gameplay map level in which the party traverses between landmarks
 *      
 *      Based on the area settings, the party may need to draw encounter cards to move within an area (only used for moving large distances)
 * 
 *      Towns and cities are areas, but the encounters are usually voluntary and non combat-based
 */

public class Area {

    public String name;
    public String description;
    private List<Landmark> discoveredLandmarks;
    private List<Landmark> allLandmarks;
    Landmark curLandmark;
    List<Encounter> encounterDeck;

    // constructors //////////////////////////////////////////////////////////////////////

    public Area() {
        this.name = new String();
        this.curLandmark = new Landmark();
        this.discoveredLandmarks = new ArrayList<Landmark>();
        this.encounterDeck = new ArrayList<Encounter>();
    }

    public Area(Landmark initialLandmark, List<Encounter> initialEncounterDeck) {
        this.name = new String();
        this.curLandmark = initialLandmark;
        this.discoveredLandmarks = new ArrayList<Landmark>();
        this.discoveredLandmarks.add(curLandmark);
        this.encounterDeck = initialEncounterDeck;
    }

    // Main menu //////////////////////////////////////////////////////////////////////

    // utility - Modify Landmarks //////////////////////////////////////////////////////////////////////

    public void modifyLandmarks() {
        Menu menu = new Menu();
		menu.setTitle("~~Available Landmarks~~");
		for (int i = 0; i < allLandmarks.size(); i++) {
			String name = new String(allLandmarks.get(i).name);
			menu.addItem(new MenuItem(name, this, "modifyLandmark", allLandmarks.get(i)));
		}
		menu.addItem(new MenuItem("new Landmark", this, "createLandmark", null));
    }

    public void modifyLandmark(Landmark aLandmark) {
		Menu menu = new Menu();
		menu.setTitle("Modify: " + aLandmark.name);
		menu.addItem(new MenuItem("Edit Landmark Name", aLandmark, "modifyName", null));
		// menu.addItem(new MenuItem("Edit Landmarks", anLandmark, "modifyLandmarks"));
	}

	public void createLandmark() {
		Landmark anLandmark = new Landmark();
		System.out.println("Landmark name:");
		anLandmark.name = ConsoleUtils.getStringResponse();
		System.out.println("Landmark Description:");
		anLandmark.description = ConsoleUtils.getStringResponse();
		allLandmarks.add(anLandmark);
	}

    // utility - Modify self //////////////////////////////////////////////////////////////////////

    public void modifyName() {
        System.out.println("new area name:");
		this.name = ConsoleUtils.getStringResponse();
    }

    // utility - Run //////////////////////////////////////////////////////////////////////

    // generate travel landmark, run landmark encounter
    public void runGeneratedLandmark() {
        // take a random encounter from the area encounter deck
        Collections.shuffle(encounterDeck);
        Encounter thisEncounter = encounterDeck.get(0);
        curLandmark = new Landmark(thisEncounter.getName() + "_landmark", thisEncounter);
        discoveredLandmarks.add(curLandmark);
        // run landmark encounter
        try {
            curLandmark.runCurrentEncounter();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return;
    }

}