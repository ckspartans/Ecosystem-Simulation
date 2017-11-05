import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;

import java.util.ArrayList.*;

/** Algae

 * The producer class, Algae.

 * @author Hawke, Mudaser, Parmeet, Shusil, Tim  

 * @version 12/10/2017

 */

public class Algae extends AbstOrganism

{
    /**
     * Act - do whatever the Algae wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    // Base Constructor

    public Algae() {
        trophicLvl = 1;
        GreenfootImage image = new GreenfootImage(size, size); // Creates an empty transparent image with the given size
        image.setColor(Color.BLACK); // Sets the boarder color black
        image.drawOval(0, 0, size, size); // Draws oval with the given size on top of transparent image 
        image.setColor(Color.GREEN); // Sets the color green
        image.fillOval(0, 0, size, size); // Fills oval with the current color
        this.setImage(image); // Sets this as an actor image
        lifeforms.add(this); // Adds this algae to the list containing all objects under the class type of AbstOrganism
        prey = new ArrayList < AbstOrganism > (); //list of all that the types of organism can feed on
        predators = new ArrayList < AbstOrganism > (); //list of all the types of organsims that the organism can be eaten by   
        age = 0; // An int that increments each time act runs to store the age
        energy = 10; //Starts with this energy
        stats = new int[] {300,1,1,2,200,2};
        Mutation.mutate(this);
        attack = 0;

        defense = 1;

    }

    public Algae(int[] newStats) {
        trophicLvl = 1;
        GreenfootImage image = new GreenfootImage(size, size); // Creates an empty transparent image with the given size
        image.setColor(Color.BLACK); // Sets the boarder color black
        image.drawOval(0, 0, size, size); // Draws oval with the given size on top of transparent image 
        image.setColor(Color.GREEN); // Sets the color green
        image.fillOval(0, 0, size, size); // Fills oval with the current color
        this.setImage(image); // Sets this as an actor image
        lifeforms.add(this); // Adds this algae to the list containing all objects under the class type of AbstOrganism
        prey = new ArrayList < AbstOrganism > (); //list of all that the types of organism can feed on
        predators = new ArrayList < AbstOrganism > (); //list of all the types of organsims that the organism can be eaten by   
        age = 0; // An int that increments each time act runs to store the age
        energy = 10; //Starts with zero energy
        stats = newStats; //traits for algae 
    }

    public void act() {
        //Update values to the ones that are mutated 
        lifespan = stats[0]; //max limit an organism can be in the world 
        speed = stats[1]; //rate of movement of the organism 
        range = stats[2]; // Int storing detection range
        num_split = stats[3]; //number of offspring an organism can produce
        split_energy = stats[4]; //set energy needed to perform a split
        mutation_rate = stats[5]; // An int which determines how many random gene stats can be changed

        // If the world reference is not stored:
        if (world == null) {

            world = (MyWorld) getWorld(); // Store the reference to the current world

        }

        //reduces lag by only updating values a a spcific time
        if (world.timer % 2 == 0) {
            age(); // Increases age by 1 every time the act method executes
            eat(); // Increases energy by 1 every time the act method executes, adds 5 each time they eat other organism
            grow(); // Grow depending on energy they have
            split(); // Reproduces when reaches the certain stage
        }
    }

    public List < AbstOrganism > givesOffList() {

        List < AbstOrganism > list = getObjectsInRange(range, AbstOrganism.class); // List of organisms around 
        return list;
    }

     public List < AbstOrganism > givesOffList1() {
         List < AbstOrganism > list = getObjectsInRange(range, AbstOrganism.class); // List of organisms around 
        return list;
        }
        
         public List < AbstOrganism > givesOffList2() {
         List < AbstOrganism > list = getObjectsInRange(range, AbstOrganism.class); // List of organisms around 
        return list;
        }
    
    public void eat() {
        // Increases the energy amount by absorbing sunlight 
        energy += 1;
    }

    public void grow() {
        // Modify the size of the image based on  the current energy
        size = (int)(0.02 * energy + 5); //Change in size
        GreenfootImage image = new GreenfootImage(size, size);
        image.setColor(Color.BLACK);
        image.drawOval(0, 0, size, size);
        image.setColor(Color.GREEN); // Sets the color green
        image.fillOval(0, 0, size, size);
        this.setImage(image);
    }

    public void split() {
        // Check to see if there if enough energy (size?) to split
        if (energy >= split_energy && age < lifespan) {
            // If yes, then call then create num_split new organisms, and mutate them.
            int angle_split = 360 / num_split;
            // If yes, then call the constructor for two new ones and kill the parent
            energy -= split_energy; // Subtract the used up energy needed to split.
            // A for loop running once for each num_Split (child to be made)
            for (int i = 0; i < num_split; i++) {
                Algae temp_splitted = new Algae(stats);
                Mutation.mutate(temp_splitted); //run mutation 
                world.addObject(temp_splitted, getX(), getY()); //add to the last known location of the parent       
                temp_splitted.turn((180 - angle_split * i) + (Greenfoot.getRandomNumber(angle_split) - angle_split / 2)); //angle them equally 
                temp_splitted.move(20); //spread apart the new organisms 
            }
            die();
        }
    }
    
      protected void fights(int _energy){    // Basically the calculation of attack and defense 
        energy += _energy;
        if(energy < 0){
            die();
        }

    }

    public void age() {
        //increase age
        age += 0.6;
        if (age >= lifespan && energy >= 0) {
            //ceate dead body with the remaining energy, remove orgional body 
            world.addObject(new Carcass(energy), getX(), getY());
            die();
        }

    }

    public void die() {
        // Remove this object from its lists
        lifeforms.remove(this);
        // Remove from the world
        world.removeObject(this);
    }

    public void shift() {
        //does not move
    }

    protected List givesOffListPredator() { // List of organisms around
        return null; //not in use
    }

}