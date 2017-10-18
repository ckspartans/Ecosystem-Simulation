import java.util.*;

/**
 * Write a description of class AI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AI  
{
    // instance variables - replace the example below with your own
    private int x;
    
    

    /**
     * Constructor for objects of class AI
     */
    public AI()
    {
        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
    
    static AbstOrganism target = null;
    
    public static void checkPrey(AbstOrganism Org2){
  
       // organism.getWorld().showText("Im dead",300,300);
        if(Org2.givesOffList().isEmpty() || Org2 ==null){
           
            return;
            
        }
        
         else {
            //if(target != (Algae) list.get(0)){
            target = (AbstOrganism) Org2.givesOffList().get(0); // always set the first one in the list as the target
             System.out.println("Im fine");
             if(target!=null){
                 hunt(Org2);
                 //flee1(Org2);
                }
        }
          
          
        }
          
         
        
        public static void flee1(AbstOrganism Org2){
            
             Org2.turnTowards(target.getX(), target.getY());         // Turns toward the food

            target = null;

            Org2.move(Org2.speed);    
            
        }
        
        
        public static void flee(AbstOrganism organism, AbstOrganism Org2){
  
       // organism.getWorld().showText("Im dead",300,300);
        if(Org2.givesOffList().isEmpty() || organism ==null || Org2 ==null){
          
            return;
            
        }
        
         else {
            //if(target != (Algae) list.get(0)){
            target = (AbstOrganism) Org2.givesOffList().get(0); // always set the first one in the list as the target
             System.out.println("Im fine");
             if(target!=null){
                Org2.turnTowards(-target.getX(), -target.getY());
                }
        }
          
          
        }
          
        
        
    
    
    public static void hunt(AbstOrganism Org2 ){
      //System.out.println("Im fine");
        Org2.turnTowards(target.getX(), target.getY());         // Turns toward the food

            target = null;

            Org2.move(Org2.speed);     
        
        
    }
    
    
    
    
    
}
