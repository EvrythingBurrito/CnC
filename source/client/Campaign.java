package source.client;
import java.util.*;
import source.ConsoleMenu.*;

class Campaign {

	public String name;
	public String description;

	private List<Region> discoveredRegions;
	private List<Region> allRegions;
	private Region curRegion;

	//private List<List<Encounter>> encounterDeckList;

    public Campaign(String aName) {
        name = new String(aName);
		description = new String("new game");
		discoveredRegions = new ArrayList<Region>();
    }

    public void campaignMenu() {
		Menu menu = new Menu();
		menu.setTitle(name + "\n" + "~--  " + description + "  --~");
		menu.addItem(new MenuItem("Edit Regions" + "", this, "modifyRegions"));
		menu.addItem(new MenuItem("Run Region" + "", this, "run(" + curRegion.name + ")"));
		menu.execute();
	}

//	public void modifyEncounterDecks() {
//
//	} 

	public void modifyRegions() {
		Menu menu = new Menu();
		menu.setTitle("~~Available Regions~~");
		for (int i = 0; i < discoveredRegions.size(); i++) {
			String name = new String(discoveredRegions.get(i).name);
			menu.addItem(new MenuItem(name, this, "modifyRegion(" + name + ")"));
		}
		menu.addItem(new MenuItem("new Region", this, "createRegion"));
	}

	public void modifyRegion(Region aRegion) {
		Menu menu = new Menu();
		menu.setTitle("Modify: " + aRegion.name);
		menu.addItem(new MenuItem("Edit Region Name", aRegion, "setName"));
		menu.addItem(new MenuItem("Edit Areas", aRegion, "modifyAreas"));
	}

	public void createRegion() {
		Region aRegion = new Region();
		System.out.println("Region name:");
		aRegion.name = System.console().readLine();
		System.out.println("Region Description:");
		aRegion.description = System.console().readLine();
		allRegions.add(aRegion);
	}

	public void setCampaignDescription(String aDescription) {
		description = aDescription;
	}
};