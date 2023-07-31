
package client;
import java.util.*;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import ConsoleMenu.*;

/* REGION CLASS
 *
 *      An region is the higher level gameplay map made up of large areas
 *      
 *      regions have settings that affect the types of descriptions, encounters, and flavor there is in areas
 * 
 *      Regions are usually divided by biomes, political borders, and story beats
 */

public class Region {

    public String name;
    public String description;

    private List<Area> discoveredAreas;
    private List<Area> allAreas;
    private Area curArea;

    // constructors //////////////////////////////////////////////////////////////////////

    public Region () {
        this.name = new String();
        this.description = new String();
        this.curArea = new Area();
        this.discoveredAreas = new ArrayList<Area>();
        this.allAreas = new ArrayList<Area>();
    }

    public Region (Area initialArea) {
        this.name = new String();
        this.description = new String();
        this.allAreas = new ArrayList<Area>();
        this.curArea = initialArea;
        this.discoveredAreas = new ArrayList<Area>();
        this.discoveredAreas.add(curArea);
    }

    // Main menu //////////////////////////////////////////////////////////////////////

    // utility - Modify Areas //////////////////////////////////////////////////////////////////////

    public void modifyAreas() {
        Menu menu = new Menu();
		menu.setTitle("~~Available Areas~~");
        // option to create new area
        menu.addItem(new MenuItem("new Area", this, "createArea", null));
		// list all discovered areas to edit
		allAreas.forEach(area -> {menu.addItem(new MenuItem(area.name, this, "modifyArea", area));});
        menu.execute();
    }

    public void modifyArea(Area anArea) {
		Menu menu = new Menu();
		menu.setTitle("Modify: " + anArea.name);
		menu.addItem(new MenuItem("Edit Landmarks", anArea, "modifyLandmarks", null));
        menu.addItem(new MenuItem("Edit Area name/description", anArea, "modifySelf", null));
        menu.execute();
	}

	public void createArea() {
		Area anArea = new Area();
		System.out.println("Area name:");
		anArea.name = ConsoleUtils.getStringResponse();
		System.out.println("Area Description:");
		anArea.description = ConsoleUtils.getStringResponse();
		allAreas.add(anArea);
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

    // utility - create region jsonbuilder //////////////////////////////////////////////////////////////////////
    public JsonObjectBuilder getBuilder() {
        // create json object builder for area
		JsonObjectBuilder gameJsonBuilder = Json.createObjectBuilder();
		gameJsonBuilder.add("name", this.name);
		gameJsonBuilder.add("description", this.description);
        if (allAreas.size() > 0) {
		    allAreas.forEach(area -> {gameJsonBuilder.add(area.name, area.getBuilder());});
        }
		return gameJsonBuilder;
	}

}