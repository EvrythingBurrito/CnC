

public class Defender extends GameClass {
    
    // combat
    // movelist - 
    // moves the character to the desired square
    public int longjump(int distance, int height) {
        // send jump request (inheritance from move function) using jump inherited function - exception if cannot accomplish jump
        // calculate damage and staminacost based on height delta and distance delta 
        // send stamina cost request using spend_stamina function - exception if not enough stamina
        // send damage output request using do_dmg function
    } 
}
