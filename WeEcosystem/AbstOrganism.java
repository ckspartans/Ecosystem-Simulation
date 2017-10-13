import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * 
 * This class sets the traits and behaviours that are common throughout the organisms  
 * @author Mudaser, Shusil, Tim, Parmeet, Hawke  
 * @version 10/3/2017
 * 
 */

public abstract class AbstOrganism extends Actor
{
    MyWorld world; //reference to the myworld class
    //GreenfootImage image; // Reference to the organism's image
    static public ArrayList <AbstOrganism> lifeforms = new ArrayList <AbstOrganism>();//list of all the organsims in the game
    public ArrayList <AbstOrganism> prey;//list of all that the types of organism can feed on
    public ArrayList <AbstOrganism> predators;//list of all the types of organsims that the organism can be eaten by 
    int age; //increases as the time passes until it reaches its limit
    int size; //radius of the organims
    int energy; //the amount of energy an organism posses, used to move gaind through eating
    int trophic_lvl; //Determine the order of the food 
    int angle_split;
    boolean hasMutated; // Whether or not they have mutated yet
    int deaths;
    
    // Mutatable Stats
    int[] stats = new int [6];
    
    int lifespan = stats [0]; //max limit an organism can be in the world 
    int speed = stats [1]; //rate of movement of the organism 
    int range = stats [2]; // Int storing detection range
    int num_split = stats [3]; //number of offspring an organism can produce
    int split_energy = stats [4]; //set energy needed to perform a split
    int mutation_rate = stats [5]; // An int which determines how many random gene stats can be changed
    
    
    
   public void act() 
    {
        
        //creat new world if world is not created yet
        if(world == null){
            
            world = (MyWorld) getWorld();
            
        } 
        
    }

    public abstract void eat(); //determins amount of energy one gains, % of the prey's enrgy, for algae this is a constant intake  

    public abstract void grow(); //is directly porportional to the energy of the organism, scaling is set by the siz 

    public abstract void split(); //preforms the splitting of the organism, creats the number of offspring, energy is split up, sopme energy is lost in the process

    public abstract void age(); //a counter that keeps increasing as a means of time 

    public abstract void die(); //removes the organism 

    public abstract void shift(); //a function the allows the organism to move around randomly 
    
    public abstract void shift(int action, AbstOrganism target); //a function the allows the organism to move based on detect command 

    public abstract int detect(int trophic_lvl); //Determines if there is an organism in a radius, and checks its trophic level, then follows if necessary or avoids, calls eat if needed 
    //detect retuns the action type which feeds into move 
    
    public void say(String s){
        
        System.out.println(s);
        
    }
       
    public abstract int getRandomNumber(int start,int end) ;
       
    

}