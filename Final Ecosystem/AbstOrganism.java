import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;


/** AbstOrganism

 * 

 * This class sets the traits and behaviours that are common throughout the organisms  

 * @author Hawke, Mudaser, Parmeet, Shusil, Tim  

 * @version 12/10/2017

 * 

 */


public abstract class AbstOrganism extends Actor

{

    MyWorld world; //reference to the myworld class
    
    static public ArrayList <AbstOrganism> lifeforms = new ArrayList <AbstOrganism>();//list of all the organsims in the game

    protected ArrayList <AbstOrganism> prey;//list of all that the types of organism can feed on

    protected ArrayList <AbstOrganism> predators;//list of all the types of organsims that the organism can be eaten by 

    protected double age; //increases as the time passes until it reaches its limit

    protected int size = 5; //radius of the organims

    protected int energy; //the amount of energy an organism posses, used to move gaind through eating

    protected int angle_split; // The base angle between the offspring of an organim, for example, an algae splitting into 2 would have 180 degrees between them (360 / numsplit) before randomization

    protected int trophicLvl;
    
    protected Sight mySight;
    
    protected boolean hungry = false;
    
    // Mutatable Stats

    protected int[] stats = new int [6]; // An int array storing the mutatable stats of the organism

    protected int lifespan = stats [0]; //max limit an organism can be in the world 

    protected int speed = stats [1]; //rate of movement of the organism 

    protected int range = stats [2]; // Int storing detection range

    protected int num_split = stats [3]; //number of offspring an organism can produce

    protected int split_energy = stats [4]; //set energy needed to perform a split

    protected int mutation_rate = stats [5]; // An int which determines how many random gene stats can be changed

    protected int attack;

    protected int defense;

    public void act(){
    }
    
    protected abstract List givesOffList(); // List of organisms around
    
     protected abstract List givesOffList1(); // List of organisms around
     
      protected abstract List givesOffList2(); // List of organisms around
      
      
    protected abstract List givesOffListPredator(); // List of organisms around

    protected abstract void eat(); //determins amount of energy one gains, % of the prey's enrgy, for algae this is a constant intake  

    protected abstract void grow(); //is directly porportional to the energy of the organism, scaling is set by the siz 

    protected abstract void split(); //preforms the splitting of the organism, creats the number of offspring, energy is split up, sopme energy is lost in the process
    
    protected abstract void fights(int _energy);
    
    protected abstract void age(); //a counter that keeps increasing as a means of time 

    protected abstract void die(); //removes the organism 

    protected abstract void shift(); //a function the allows the organism to move around randomly 
    
    protected void say(String s){

        System.out.println(s);

    }

    public int getRandomNumber(int start, int end) {

        int normal = Greenfoot.getRandomNumber(end-start+1);

        return normal+start;


    }



}	