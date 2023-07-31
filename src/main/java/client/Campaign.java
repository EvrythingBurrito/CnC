package client;
import java.io.*;
import java.util.*;
import ConsoleMenu.*;
import javax.json.*;

public class Campaign {

	public String name;
	public String description;

	private List<Region> discoveredRegions;
	private List<Region> allRegions;
	private Region curRegion;

	//private List<List<Encounter>> encounterDeckList;

	// constructors //////////////////////////////////////////////////////////////////////

	public Campaign() {
		name = new String();
		description = new String("new game");
		discoveredRegions = new ArrayList<Region>();
		allRegions = new ArrayList<Region>();
		curRegion = new Region();
	}

    public Campaign(String aName) {
        name = new String(aName);
		description = new String("new game");
		discoveredRegions = new ArrayList<Region>();
		allRegions = new ArrayList<Region>();
		curRegion = new Region();
    }

	// Main menu //////////////////////////////////////////////////////////////////////

    public void campaignMenu() {
		Menu menu = new Menu();
		menu.setTitle(name + "\n" + "~--  " + description + "  --~");
		menu.addItem(new MenuItem("Edit Regions" + "", this, "modifyRegions", null));
		menu.addItem(new MenuItem("Run Region" + "", this, "run", curRegion));
		menu.execute();
	}

	// utility - Modify Regions //////////////////////////////////////////////////////////////////////

	public void modifyRegions() {
		Menu menu = new Menu();
		menu.setTitle("~~ Edit Regions in Campaign \"" + this.name + "\" ~~");
		// option to create new region
		menu.addItem(new MenuItem(" . . . . <New Region>", this, "createRegion", null));
		// list all discovered regions to edit
		allRegions.forEach(region -> {menu.addItem(new MenuItem(region.name, this, "modifyRegion", region));});
		menu.execute();
	}

	public void modifyRegion(Region aRegion) {
		Menu menu = new Menu();
		menu.setTitle("Modify: " + aRegion.name);
		menu.addItem(new MenuItem("Edit Areas", aRegion, "modifyAreas", null));
		menu.addItem(new MenuItem("Edit name/description", aRegion, "modifySelf", null));
		menu.execute();
	}

	public void createRegion() {
		Region aRegion = new Region();
		System.out.println("Region name:");
		aRegion.name = ConsoleUtils.getStringResponse();
		System.out.println("Region Description:");
		aRegion.description = ConsoleUtils.getStringResponse();
		allRegions.add(aRegion);
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

	// utility - save campaign to json //////////////////////////////////////////////////////////////////////
	public void saveToDirectory(String aDirectory) {
		// save campaign to json
		JsonObjectBuilder gameJsonBuilder = Json.createObjectBuilder();
		gameJsonBuilder.add("name", this.name);
		gameJsonBuilder.add("description", this.description);
		// . . . . . . . add anything else necessary for campaign
		// add regions, if any
		if (allRegions.size() > 0) {
			allRegions.forEach(region -> {gameJsonBuilder.add(region.name, region.getBuilder());});
		}
		// add player characters
		JsonObject gameJson = gameJsonBuilder.build();
		 try {
			OutputStream fos = new FileOutputStream(aDirectory + "/" + this.name + ".json");
			JsonWriter jsonWriter = Json.createWriter(fos);
			jsonWriter.writeObject(gameJson);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
};