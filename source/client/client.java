package source.client;
import java.util.*;
import java.util.stream.*;
import java.io.*;
import source.javax.json.*;
import source.ConsoleMenu.*;

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

class GM_client {

    final static String campaignDir = new String("./gamedata/campaigns");
    static Campaign curGame;

    public static void main() { new GM_client().mainMenu(); }

    public void mainMenu() {
        Menu menu = new Menu();
		menu.setTitle("~~~----- CAVES AND COCKATRICES ----~~~\n");
		//menu.addItem(new MenuItem("CREATE NEW CAMPAIGN", this, "create_campaign"));
		menu.addItem(new MenuItem("LOAD CAMPAIGN", this, "loadGameMenu"));
        //menu.addItem(new MenuItem("SAVE CAMPAIGN", this, "saveGame"));
		menu.execute();
    }

    public void loadGameMenu() {

        Menu menu = new Menu();
		menu.setTitle("~~ Load Game ~~\n");
		menu.addItem(new MenuItem("CREATE NEW CAMPAIGN", this, "create_campaign"));
        // read "name" attributes in all json files in campaign directory
        Set<String> jsonNames = listJSONInDirectory(campaignDir);
        Iterator<String> it = jsonNames.iterator();
        while (it.hasNext()) {
            try {
                InputStream fis = new FileInputStream(it.next());
                JsonReader jsonReader = Json.createReader(fis);
                JsonObject gameJson = jsonReader.readObject();
                String gameName = new String(gameJson.getString("name"));
                menu.addItem(new MenuItem(gameName, this, "printName(" + gameName + ")"));
                fis.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        menu.addItem(new MenuItem(it.next(), this, "loadGame"));
		menu.execute();
    }

    public void printName(String name) {
        System.out.println(name);
    }

    public Set<String> listJSONInDirectory(String dir) {
    return Stream.of(new File(dir).listFiles())
      .filter(file -> !file.isDirectory())
      .filter(file -> file.getName().matches("*.json"))
      .map(File::getName)
      .collect(Collectors.toSet());
}
}