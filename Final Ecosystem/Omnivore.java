import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Omnivore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Omnivore extends Carnivore
{
    protected int radius = 120; // radius, default using 1
  //  protected Herbivore target = null; // when it is null, player has no target
  //  protected Algae target2 = null; // when it is null, player has no target2
    private int killCount = 0;
    private int tempTimer = 0;

    public Omnivore() {
        trophicLvl = 5;
        
        GreenfootImage image = new GreenfootImage(size+10, size+10); // Creates an empty transparent image with the given size+10

        image.setColor(Color.BLACK);        // Sets the color black

        image.drawOval(0, 0, size+10, size+10);   // Draws oval with the given size+10 on top of transparent image 

        image.setColor(Color.WHITE);        // Sets the color red

        image.fillOval(0, 0, size+10, size+10);   // Fills oval with the current color

        this.setImage(image);                  // Sets this as an actor image

        lifeforms.add(this); // Adds this algae to the list containing all objects under the class type of AbstOrganism

        prey = new ArrayList <AbstOrganism> ();//list of all that the types of organism can feed on

        predators = new ArrayList <AbstOrganism> ();//list of all the types of organsims that the organism can be eaten by   

        age = 0; // An int that increments each time act runs to store the age

        energy = 10; 
        
        int attack = 7;
        
        int defense = 0;

        stats = new int [] {300, 2, 120, 2, 200, 2}; //traits for Herbivore 

        Mutation.mutate(this);
    }

    public Omnivore (int [] newStats) {
        trophicLvl = 5;
        GreenfootImage image = new GreenfootImage(size+10, size+10); // Creates an empty transparent image with the given size+10
        image.setColor(Color.BLACK);        // Sets the color black
        image.drawRect(0, 0, size+10, size+10);   // Draws oval with the given size+10 on top of transparent image 
        image.setColor(Color.BLACK);        // Sets the color red
        image.fillRect(0, 0, size+10, size+10);   // Fills oval with the current color
        this.setImage(image);                  // Sets this as an actor image
        lifeforms.add(this); // Adds this algae to the list containing all objects under the class type of AbstOrganism
        prey = new ArrayList <AbstOrganism> ();//list of all that the types of organism can feed on
        predators = new ArrayList <AbstOrganism> ();//list of all the types of organsims that the organism can be eaten by   
        age = 0; // An int that increments each time act runs to store the age
        energy = 50; //Starts with zero energy
        stats = newStats; //traits for Herbivore 
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
        
       // if (target == null) { // when player has no target2, using keyboard controller
            AI.checkPrey(this); // after each movement, check whether the food is in sight
       // } else { // else player moves automatically to the target2 
          //  AI.hunt(this); // Attacks preys
       // }
        
     //   if (target2 == null) { // when player has no target2, using keyboard controller
            AI.checkPrey(this); // after each movement, check whether the food is in sight
       // } else { // else player moves automatically to the target2 
            //AI.hunt(this); // Attacks preys
        //}

        Algae algae = (Algae) getOneIntersectingObject(Algae.class);

        if (algae != null) {

            world.foodEaten ++;          // Increases foodeaten, a variable in My World

            energy += (algae.energy)/2; //energy gained after eating

            AbstOrganism.lifeforms.remove(algae);
            world.removeObject(algae);      // Removes Algae object

          //  target2 = null; // clear the target2 after removing the apple

        }

        Herbivore herbivore = (Herbivore) getOneIntersectingObject(Herbivore.class);

        if (herbivore != null) {

            world.foodEaten ++;          // Increases foodeaten, a variable in My World

            energy+= (herbivore.energy)/2; //energy gained after eating

            AbstOrganism.lifeforms.remove(herbivore);
            world.removeObject(herbivore);      // Removes Algae object

        //    target = null; // clear the target after removing the apple

        }

        age ();     // Increases age by 1 every time the act method executes
        eat ();     // Increases energy by 1 every time the act method executes, adds 5 each time they eat other organism
        grow ();    // Grow depending on energy they have
        shift();    // Randomly moves around
        split();    // Reproduces when reaches the certain stage
    }

    
       public List<Herbivore> givesOffList() {

        List<Herbivore> list = getObjectsInRange(range, Herbivore.class); // List of organisms around 
        return list;

    }
        
      
    
     public void age() {

        age += 1; //Increase age by 1
        if (age >= lifespan) {    // If it is less than or equal to its lifespan
            world.addObject(new Carcass(energy), getX(), getY());
            die();                // dies
        }

    }

    public void grow() {
        // Modify the size+10 of the image based on  the current energy
        size = (int) (0.02 * energy + 5); //Change in size+10
        GreenfootImage image = new GreenfootImage(size+10, size+10); 
        image.setColor(Color.BLACK);
        image.drawOval(0, 0, size+10, size+10);
        image.setColor(Color.BLACK);        // Sets the color green
        image.fillOval(0, 0, size+10, size+10);
        this.setImage(image);
    }

   
        
    public void split(){
        angle_split = 360 / num_split;              // In which angle would the child go 
        // Check to see if there if enough energy (size?) to split
        if (energy >= split_energy && age < lifespan) {
            for (int i = 0; i < num_split; i ++) {

                //range = getRandomNumber(500, 700); in case range does not mutate 

                Omnivore temp_Splitted = new Omnivore(stats);//this is where the mutation for new radius is 

                Mutation.mutate(temp_Splitted);

                world.addObject(temp_Splitted, getX(), getY());

                temp_Splitted.turn ((180 - angle_split * i) + (Greenfoot.getRandomNumber(angle_split) - angle_split / 2));

            }
            die();
        }

    }

}
