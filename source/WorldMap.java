
package source;
import java.util.*;

class WorldMap {

    List<Region> discoveredRegions;
    Region currentRegion;

    public WorldMap(Region initialRegion) {
        discoveredRegions = new ArrayList<Region>();
        discoveredRegions.add(initialRegion);
    }

    public String getRegionName() {
        return currentRegion.getName();
    }

    public void queryRegionHop() {
        // menu to browse known info about discovered regions
    }

}