
package source;

class Landmark {
    String name;
    String description;
    Encounter currentEncounter;

    public Landmark(String aName, Encounter anEncounter) {
        name = aName;
        currentEncounter = anEncounter;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

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