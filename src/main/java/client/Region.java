
package client;
import java.util.*;

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
        curArea = new Area();
        discoveredAreas = new ArrayList<Area>();
    }

    public Region (Area initialArea) {
        this.name = new String();
        this.description = new String();
        curArea = initialArea;
        discoveredAreas = new ArrayList<Area>();
        discoveredAreas.add(curArea);
    }

    // Main menu //////////////////////////////////////////////////////////////////////

    // utility - Modify Areas //////////////////////////////////////////////////////////////////////

    public void modifyAreas() {
        Menu menu = new Menu();
		menu.setTitle("~~Available Areas~~");
		for (int i = 0; i < discoveredAreas.size(); i++) {
			String name = new String(discoveredAreas.get(i).name);
			menu.addItem(new MenuItem(name, this, "modifyArea", discoveredAreas.get(i)));
		}
		menu.addItem(new MenuItem("new Area", this, "createArea", null));
    }

    public void modifyArea(Area anArea) {
		Menu menu = new Menu();
		menu.setTitle("Modify: " + anArea.name);
		menu.addItem(new MenuItem("Edit Area Name", anArea, "setName", null));
		menu.addItem(new MenuItem("Edit Areas", anArea, "modifyLandmarks", null));
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

    public void modifyName() {
        System.out.println("new region name:");
		this.name = ConsoleUtils.getStringResponse();
    }

}