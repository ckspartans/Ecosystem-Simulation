import java.util.*;

/**
 * This class handles the AI abilities that the organisms have
 * 
 * @author Hawke, Mudaser, Parmeet, Shusil, Tim  
 * @version 17/10/2017
 */
public class AI  
{
    // instance variables - replace the example below with your own

 

   static AbstOrganism target = null;
   static AbstOrganism runAway = null;
   
    public static void checkPrey(AbstOrganism Org2){
        
       
  
       // organism.getWorld().showText("Im dead",300,300);
        if(Org2.givesOffList().isEmpty() || Org2 ==null){ // If no preys
           
            return; // Gets out of the function
            
        }
        
         else {
            //if(target != (Algae) list.get(0)){
            target = (AbstOrganism) Org2.givesOffList().get(0); // always set the first one in the list as the target
           
             if(target!=null){ // If there are preys in range
                 hunt( Org2); //Kills
                 //flee1(organism, Org2);
                }
        }
          
          
        }
          
          
    public static void checkPredator(AbstOrganism Org2){ // Predator checking
  
     // organism.getWorld().showText("Im dead",300,300);
        if(Org2.givesOffListPredator().isEmpty() || Org2 ==null){ //If no predator
           
            return; // Gets out of the function
            
        }
        
         else {
            //if(target != (Algae) list.get(0)){
            runAway = (AbstOrganism) Org2.givesOffListPredator().get(0); // always set the first one in the list as the target
      
             if(runAway!=null){ // If no predators
                 flee(Org2); // Runsaway
                 //flee1(organism, Org2);
                }
        }
          
          
          
        }
        
        public static void flee(AbstOrganism Org2){ //Runsaway
            
            Org2.turnTowards(runAway.getX(),runAway.getY());         // Turns away the food
            Org2.turn(180); // Turns 180
            Org2.move(Org2.speed); 
            
            //Org2 = null;

               
            
        }
        

    
    
    public static void hunt(AbstOrganism Org2 ){ // Hunting
      //System.out.println("Im fine");
        Org2.turnTowards(target.getX(), target.getY());         // Turns toward the food

            target = null;

            Org2.move(Org2.speed);          //Moves with the speed assigned before
        
        
    }
    
    
    
    
    
}
