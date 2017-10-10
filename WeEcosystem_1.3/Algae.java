import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * The producer class, the lowest trophic level of 1, Algae.
 * @author Mudaser, Shusil, Tim, Parmeet, Hawke  
 * @version 10/10/2017
 */
public class Algae extends AbstOrganism 
{
    
     private int size = 1; // Starting size of an organism
     
    /**
     * Act - do whatever the Algae wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    // Base Constructor
    public Algae () {
        
        GreenfootImage image = new GreenfootImage(size, size); // Creates an empty transparent image with the given size
        image.setColor(Color.BLUE);        // Sets the color green
        image.drawOval(0, 0, size, size);   // Draws oval with the given size on top of transparent image 
        image.fillOval(0, 0, size, size);   // Fills oval with the current color
        this.setImage(image);                  // Sets this as an actor image
        lifeforms = new ArrayList <AbstOrganism> ();//list of all the organsims in the game
        lifeforms.add(this);
        prey = new ArrayList <AbstOrganism> ();//list of all that the types of organism can feed on
        predators = new ArrayList <AbstOrganism> ();//list of all the types of organsims that the organism can be eaten by   
        trophic_lvl = 0; // The trophic level of the organism, its place in the food web / chain
        age = 0; // An int that increments each time act runs to store the age
        energy = 0; //Starts with zero energy
        hasMutated = false;
        deaths = 0;
        
        stats = new int [] {300, 1, 1, 2, 200, 2}; //traits for algae 
        
        
    
    }
    
    public void act() 
    {
       
        lifespan = stats [0]; //max limit an organism can be in the world 
        speed = stats [1]; //rate of movement of the organism 
        range = stats [2]; // Int storing detection range
        num_split = stats [3]; //number of offspring an organism can produce
        split_energy = stats [4]; //set energy needed to perform a split
        mutation_rate = stats [5]; // An int which determines how many random gene stats can be changed
        // If the world reference is not stored:
        if (world == null) {
        
            world = (MyWorld) getWorld (); // Store the reference to the current world
        
        }   
        // If the image reference is not stored:
        
        //preforms a mutation once in the organisms lifetime 
        if (hasMutated == false) {
            mutate ();
            hasMutated = true;
        }
        
        age ();
        eat ();
        grow ();
        split ();
      shift();   
        
    }
    
    public void eat() {
        
        // Increases the energy amount.
        energy += 2;
        world.showText(""+energy, 150,100);
        world.showText(""+deaths, 150,150);
    }
    
    public void grow() {
         // Modify the size of the image based on  the current energy
        size = (int) (0.02 * energy + 5); //Change in size
        GreenfootImage image = new GreenfootImage(size, size); 
        image.setColor(Color.BLUE);
        image.drawOval(0, 0, size, size);
        image.fillOval(0, 0, size, size);
        this.setImage(image);
       
    }
    
    public void split(){
        angle_split = 360 / num_split;
        // Check to see if there if enough energy (size?) to split
        if (energy >= split_energy) {
            // If yes, then call the constructor for two new ones and kill the parent
            energy -= split_energy; // Subtract the used up energy needed to split.
            
            // A for loop running once for each num_Split (child to be made)
            for (int i = 0; i < num_split; i ++) {
            
                Algae temp_Splited = new Algae ();
                //lifeforms.add(new Algae());
                world.addObject(temp_Splited, getX(), getY());      
                temp_Splited.turn ((180 - angle_split * i) + (Greenfoot.getRandomNumber(angle_split) - angle_split / 2));
                temp_Splited.move(7);
            
            }
            
            die();
            
        }
    
    }
    
    public void age() {
      
         
        age +=1;
        
        if (age >= lifespan) {    
              die();
        }
    
    }
    
    public void die() {
        
        // Checks to see if past lifespan
      
        
        // Remove this object from its lists
        lifeforms.remove(this);
        // Remove from the world
        world.removeObject(this);
        return;
    
    }
    
    
    public void mutate() {
   
        //say ("Mutate not implemented");
        for (int i = 0; i < stats [5]; i ++) {
        
            int mutating = (int) Greenfoot.getRandomNumber (stats.length - 1);
            
            if (mutating == 0) { // lifespan
            
                stats [mutating] = (int) getRandomNumber(100, 500);
            
            } else if (mutating == 1) { // speed
            
                stats [mutating] = (int) getRandomNumber(1, 3);
            
            } else if (mutating == 2) { // range
            
                stats [mutating] = (int) getRandomNumber(1, 5);
            
            } else if (mutating == 3) { // num_split
            
                stats [mutating] = (int) getRandomNumber(2, 4);
            
            } else if (mutating == 4) { // split_energy
            
                stats [mutating] = (int) getRandomNumber(200, 400);
            
            } else if ( mutating == 5) { // mutation_rate
            
                stats [mutating] = (int) getRandomNumber(2, 6);
            
            }     
        }
    }
    
    public void shift() {
        int turn_direction = 0;

        move(speed);
        if(turn_direction == 0){
            turn(10);
        }
        else{
            turn(-10);
        }
                
        energy--;
    } //a function the allows the organism to move around randomly 
    
    public void shift(int action, AbstOrganism target) {
    
        
    
    } //a function the allows the organism to move based on detect command 
    
    public int detect (int trophic_lvl) {
    
        return 0;
    
    }
    
    public int getRandomNumber(int start, int end)
    {
       int normal = Greenfoot.getRandomNumber(end-start+1);
       return normal+start;
    }
    
}