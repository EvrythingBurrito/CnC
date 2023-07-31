
package client;
import java.util.*;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

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
    public List<Encounter> encounterDeck;

    // constructors //////////////////////////////////////////////////////////////////////

    public Area() {
        this.name = new String();
        this.curLandmark = new Landmark();
        this.discoveredLandmarks = new ArrayList<Landmark>();
        this.allLandmarks = new ArrayList<Landmark>();
        this.encounterDeck = new ArrayList<Encounter>();
    }

    public Area(Landmark initialLandmark, List<Encounter> initialEncounterDeck) {
        this.name = new String();
        this.curLandmark = initialLandmark;
        this.discoveredLandmarks = new ArrayList<Landmark>();
        this.allLandmarks = new ArrayList<Landmark>();
        this.discoveredLandmarks.add(curLandmark);
        this.encounterDeck = initialEncounterDeck;
    }

    // Main menu //////////////////////////////////////////////////////////////////////

    // utility - Modify Landmarks //////////////////////////////////////////////////////////////////////

    public void modifyLandmarks() {
        Menu menu = new Menu();
		menu.setTitle("~~Available Landmarks~~");
        // option to create new area
        menu.addItem(new MenuItem("new Landmark", this, "createLandmark", null));
		// list all discovered areas to edit
		allLandmarks.forEach(landmark -> {menu.addItem(new MenuItem(landmark.name, this, "modifyLandmark", landmark));});
        menu.execute();
    }

    public void modifyLandmark(Landmark aLandmark) {
		Menu menu = new Menu();
		menu.setTitle("Modify: " + aLandmark.name);
		menu.addItem(new MenuItem("Edit name/description/encounter", aLandmark, "modifySelf", null));
        menu.execute();
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

    public void modifySelf() {
		System.out.println("New name: (default: \"" + this.name + "\")");
		String response = ConsoleUtils.getStringResponse();
		if (response != null) {
			this.name = response;
		}
		System.out.println("New description: (default: \"" + this.description + "\")");
		response = ConsoleUtils.getStringResponse();
		if (response != null) {
			this.description = response;
		}
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

    // utility - create area jsonbuilder //////////////////////////////////////////////////////////////////////
    public JsonObjectBuilder getBuilder() {
        // create json object builder for area
		JsonObjectBuilder gameJsonBuilder = Json.createObjectBuilder();
		gameJsonBuilder.add("name", this.name);
		gameJsonBuilder.add("description", this.description);
        if (allLandmarks.size() > 0) {
		    allLandmarks.forEach(landmark -> {gameJsonBuilder.add(landmark.name, landmark.getBuilder());});
        }
        if (encounterDeck.size() > 0) {
            encounterDeck.forEach(encounter -> {gameJsonBuilder.add(encounter.name, encounter.getBuilder());});
        }
		return gameJsonBuilder;
	}
}