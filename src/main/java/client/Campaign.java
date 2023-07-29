package client;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
		curRegion = new Region();
	}

    public Campaign(String aName) {
        name = new String(aName);
		description = new String("new game");
		discoveredRegions = new ArrayList<Region>();
    }

	// Main menu //////////////////////////////////////////////////////////////////////

    public void campaignMenu() {
		Menu menu = new Menu();
		menu.setTitle(name + "\n" + "~--  " + description + "  --~");
		menu.addItem(new MenuItem("Edit Regions" + "", this, "modifyRegions", null));
		menu.addItem(new MenuItem("Run Region" + "", this, "run", curRegion));
		menu.execute();
	}

//	public void modifyEncounterDecks() {
//
//	} 

	// utility - Modify Regions //////////////////////////////////////////////////////////////////////

	public void modifyRegions() {
		Menu menu = new Menu();
		menu.setTitle("~~Available Regions~~");
		for (int i = 0; i < discoveredRegions.size(); i++) {
			String name = new String(discoveredRegions.get(i).name);
			menu.addItem(new MenuItem(name, this, "modifyRegion", discoveredRegions.get(i)));
		}
		menu.addItem(new MenuItem("new Region", this, "createRegion", null));
	}

	public void modifyRegion(Region aRegion) {
		Menu menu = new Menu();
		menu.setTitle("Modify: " + aRegion.name);
		menu.addItem(new MenuItem("Edit Region Name", aRegion, "setName", null));
		menu.addItem(new MenuItem("Edit Areas", aRegion, "modifyAreas", null));
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

	public void setCampaignDescription(String aDescription) {
		this.description = aDescription;
	}

	// utility - save game to json //////////////////////////////////////////////////////////////////////

	public void saveToDirectory(String aDirectory) {
		// make a campaign directory, if one doesn't already exist
		String campaignPathName = new String(aDirectory + "/" + this.name);
		Path campaignPath = Paths.get(campaignPathName);
		if (!Files.exists(campaignPath))
        	new File(campaignPathName).mkdirs();
		// save all regions to their own jsons
		// save all characters to their own jsons
		JsonObjectBuilder gameJsonBuilder = Json.createObjectBuilder();
		gameJsonBuilder.add("name", this.name);
		gameJsonBuilder.add("description", this.description);
		// . . . . . . . add anything else necessary for campaign
		JsonObject gameJson = gameJsonBuilder.build();
		 try {
			OutputStream fos = new FileOutputStream(campaignPathName + "/" + this.name + ".json");
			JsonWriter jsonWriter = Json.createWriter(fos);
			jsonWriter.writeObject(gameJson);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
};