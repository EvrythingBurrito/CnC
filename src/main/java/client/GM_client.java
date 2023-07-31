package client;
import java.util.*;
import java.util.stream.*;
import java.io.*;
import javax.json.*;
import ConsoleMenu.*;
import com.google.gson.Gson;

// class player_client extends Thread {
//     public void run() {
//         try {
//             // Displaying the thread that is running
//             System.out.println( "Thread " + Thread.currentThread().getState() + " is running");
//         }
//         catch (Exception e) {
//             // Throwing an exception
//             System.out.println("Exception is caught");
//         }
//     }
// }

//        // start player threads
//        int n = 4; // Number of players
//        for (int i = 0; i < n; i++) {
//            player_client player = new player_client();
//            player.start();
//        }

public class GM_client {

    // run directory is /CnC/run
    final static String campaignDir = new String("./gamedata/campaigns");
    Campaign curCampaign;

    public static void main(String[] args) { new GM_client().mainMenu(); }

    public void mainMenu() {
        Menu menu = new Menu();
		menu.setTitle("\n\n\n\n~~~----- CAVES AND COCKATRICES ----~~~\n");
		menu.addItem(new MenuItem("CREATE NEW CAMPAIGN", this, "createCampaign", null));
		menu.addItem(new MenuItem("LOAD CAMPAIGN", this, "loadGameMenu", null));
        menu.addItem(new MenuItem("SAVE CAMPAIGN", this, "saveGameMenu", null));
        menu.addItem(new MenuItem("EDIT CAMPAIGN", this, "modifyCampaign", null));
		menu.execute();
    }

    // Utility - create game ///////////////////////////////////////////////////////////////////////////////////////////////

    public void createCampaign() {
        Campaign aCampaign = new Campaign();
        System.out.println("\n\nCampaign name:");
		aCampaign.name = ConsoleUtils.getStringResponse();
		System.out.println("Campaign Description:");
		aCampaign.description = ConsoleUtils.getStringResponse();
        curCampaign = aCampaign;
    }

    // Utility - load game ///////////////////////////////////////////////////////////////////////////////////////////////

    public void loadGameMenu() {
        Menu menu = new Menu();
		menu.setTitle("\n\n~~ Load Game ~~\n");
        // read "name" attributes in all json files in campaign directory
        List<JsonObject> campaignJsons = getJSONInDirectory(campaignDir);
        campaignJsons.forEach(json -> {menu.addItem(new MenuItem(json.getString("name"), this, "loadGame", json.toString()));});
		menu.execute();
    }

    public void loadGame(String jsonString) {
        curCampaign = new Campaign();
        Gson gson = new Gson();
        curCampaign = gson.fromJson(jsonString, curCampaign.getClass());
    }

    // Utility - save game ///////////////////////////////////////////////////////////////////////////////////////////////

    public void saveGameMenu() {
		System.out.println("\n\n~~ Save Current Game : " + curCampaign.name + " ~~\n");
        boolean doSaveGame = true;
        boolean newGameSave = true;
        List<JsonObject> campaignJsons = getJSONInDirectory(campaignDir);
        // check if overwriting old campaign save file
        for (int i = 0; i < campaignJsons.size(); i++) {
            if (campaignJsons.get(i).getString("name") == curCampaign.name) {
                System.out.println(curCampaign.name + " already exists. . .\n\nOverwrite gamesave?\n");
                newGameSave = false;
                if (!ConsoleUtils.requestConfirmation())
                    doSaveGame = false;
            }
        }
        // verify if campaign is new
        if (newGameSave) {
            System.out.println("save new campaign " + curCampaign.name + " ?");
            if (!ConsoleUtils.requestConfirmation())
                    doSaveGame = false;
        }
        // save game or exit
        if (doSaveGame) {
            curCampaign.saveToDirectory(campaignDir);
            System.out.println("\ngame saved");
        } else {
            System.out.println("\ngame save cancelled");
        }
        ConsoleUtils.pauseExecution();
    }

    // Utility - modify game ///////////////////////////////////////////////////////////////////////////////////////////////

    public void modifyCampaign() {
        Menu menu = new Menu();
		menu.setTitle("\n\n~~ Modify Campaign: \"" + curCampaign.name + "\" ~~\n");
        menu.addItem(new MenuItem("edit campaign regions", curCampaign, "modifyRegions", null));
        menu.addItem(new MenuItem("edit campaign name/description", curCampaign, "modifySelf", null));
        menu.execute();
    }

    // Utility - Misc ///////////////////////////////////////////////////////////////////////////////////////////////

    public List<JsonObject> getJSONInDirectory(String dir) {
        List<JsonObject> jsonList = new ArrayList<JsonObject>();
        // FIXME: adding non-json files to campaign directory will cause issues
        Set<String> jsonNames = listFileInDirectory(dir);
        Iterator<String> it = jsonNames.iterator();
        while (it.hasNext()) {
            try {
                String curFile = new String(dir + "/" + it.next());
                InputStream fis = new FileInputStream(curFile);
                JsonReader jsonReader = Json.createReader(fis);
                jsonList.add(jsonReader.readObject());
                fis.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return jsonList;
    }

    public Set<String> listFileInDirectory(String dir) {
    return Stream.of(new File(dir).listFiles())
      .filter(file -> !file.isDirectory())
      .map(File::getName)
      .collect(Collectors.toSet());
    }
    
}