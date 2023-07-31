
package client;
import java.util.*;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import ConsoleMenu.ConsoleUtils;

class Encounter {

    String name;
    String description;
    List<Encounter> encounterDeck;

    public Encounter(String aName) {
        name = aName;
    }

    public String getName() {
        return name;
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

    // utility - create encounter jsonbuilder //////////////////////////////////////////////////////////////////////
    public JsonObjectBuilder getBuilder() {
        // create json object builder for area
		JsonObjectBuilder gameJsonBuilder = Json.createObjectBuilder();
		gameJsonBuilder.add("name", this.name);
		gameJsonBuilder.add("description", this.description);
		return gameJsonBuilder;
	}
}