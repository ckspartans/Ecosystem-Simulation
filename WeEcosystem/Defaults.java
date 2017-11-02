/**
 * Write a description of class Defaults here.
 * 
 * @author (Shusil) 
 * @version (a version number or a date)
 */
public class Defaults  
{
    
    // Actor 1 is the current location 
    // Actor 2 is a target
       public static double calcDistance(AbstOrganism actor1, AbstOrganism actor2) {
    return Math.hypot(actor1.getX() - actor2.getX(), actor1.getY() - actor2.getY());
}



}
