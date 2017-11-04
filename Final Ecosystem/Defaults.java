/**
 * Handles the smaller vectors Calc.
 * 
 * @author (Shusil) 
 * @version (a version number or a date)
 */
public class Defaults  
{
    
    // Actor 1 is the current location 
    // Actor 2 is a target
       public static double calcDistance(AbstOrganism actor1, AbstOrganism actor2) {    // This just calculates the distance between two actors
           
           // Actor 1 is Parent Organism
           // Actor 2 is target
           
    return Math.hypot(actor1.getX() - actor2.getX(), actor1.getY() - actor2.getY());    // Does the pythagorus theorem
    // and then returns the hypotenuse
    
}

// There is not much in this class because we dont need more than this

}
