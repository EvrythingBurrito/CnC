
package client;

public class Landmark {
    public String name;
    public String description;
    // encounter will change or even not exist
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

    public void setDescription() {
        // menu to update landmark description
    }

    public void runCurrentEncounter() throws Exception {
        if (currentEncounter != null) {
            // play encounter (combat, npc, whatever)
            // update landmark data to results of encounter
        } else {
            throw new Exception("Landmark has no active encounters");
        }
    }
}