
package client;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import ConsoleMenu.ConsoleUtils;

public class Landmark {
    public String name;
    public String description;
    // encounter can change or even not exist
    Encounter currentEncounter;

    // constructors //////////////////////////////////////////////////////////////////////

    public Landmark() {
        this.name = new String();
        this.description = new String();
    }

    public Landmark(String aName) {
        this.name = aName;
    }

    public Landmark(String aName, Encounter anEncounter) {
        this.name = aName;
        currentEncounter = anEncounter;
    }

    // Main menu //////////////////////////////////////////////////////////////////////

    // utility //////////////////////////////////////////////////////////////////////

    public void runCurrentEncounter() throws Exception {
        if (currentEncounter != null) {
            // play encounter (combat, npc, whatever)
            // update landmark data to results of encounter
        } else {
            throw new Exception("Landmark has no active encounters");
        }
    }

    // utility - modify self //////////////////////////////////////////////////////////////////////
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

    // utility - create landmark jsonbuilder //////////////////////////////////////////////////////////////////////
    public JsonObjectBuilder getBuilder() {
        // create json object builder for area
		JsonObjectBuilder gameJsonBuilder = Json.createObjectBuilder();
		gameJsonBuilder.add("name", this.name);
		gameJsonBuilder.add("description", this.description);
		return gameJsonBuilder;
	}
}