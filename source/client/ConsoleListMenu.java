
package source.client;
import java.util.*;

class ConsoleListMenu {

    List<String> optionsList;
    Boolean option_found;
    String selection;

    public ConsoleListMenu(ArrayList<String> aList) {
        optionsList = new ArrayList<String>();
    }

    public String selectFromOptions() throws Exception {
        option_found = false;
        // print all options
        optionsList.forEach((option) -> System.out.println(option));
        // receive user selection
        optionsList.forEach((option) -> {
            if (System.console().readLine() == option) {
                System.out.println(option);
                option_found = true;
                selection = option;
            }
        });
        if (option_found == true) {
            return selection;
        }
        else {
            throw new Exception("user input does not match available options");
        }
    }

}