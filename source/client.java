package source;
import java.io.*;
import javax.json.*;

class player_client extends Thread {
    public void run() {
        try {
            // Displaying the thread that is running
            System.out.println( "Thread " + Thread.currentThread().getState() + " is running");
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}

class GM_client {
    enum state {
        PLAY,
        SAVE,
        LOAD,
        EXIT
    } 

    final static String gamesave = new String("../gamesaves/gamesave.json");
    static File gamesave_file = new File(gamesave);
    static game_environment curGame;

    public static void run_session(state initialState) {
        // select game_environment
        try {
            // read JSON to gameJson
            InputStream fis = new FileInputStream(gamesave_file);
            JsonReader jsonReader = Json.createReader(fis);
            JsonObject gameJson = jsonReader.readObject();
            jsonReader.close();
            try {
                fis.close();
            } catch (Exception e) {
                System.out.println("error closing file");
            }
                // use gameJson to initialize curGame
            curGame = new game_environment();
        } catch (FileNotFoundException e) {
            System.out.println("No JSON found!");
            return;
        }
        // start player threads
        int n = 4; // Number of players
        for (int i = 0; i < n; i++) {
            player_client player = new player_client();
            player.start();
        }

        // state shift
        state curState = initialState;
        while (true) {
            if (curState == state.SAVE) {
                // backup game_environment data to JSON gamesave
            }
            else if (curState == state.LOAD) {
                // overwrite game_environment to JSON gamesave
            }
            else if (curState == state.EXIT) {
                // powerdown
                return;
            }
            else {
                // run game
                curGame.run();
            }
        }
    }
}