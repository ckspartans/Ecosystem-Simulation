import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;

/** Carnivore
 * A Carnivore tries to track down and eat any herbivores in range.
 * 
 * @author Hawke, Mudaser, Parmeet, Shusil, Tim  
 * @version 12/10/2017
 */
public class Carnivore extends AbstOrganism
{
    /**
     * Act - do whatever the Algae wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    //Construct taget 
    private Herbivore target = null; // when it is null, player has no target
    private boolean hungry = false;
    private int killCount = 0;
    private int tempTimer = 0;

    public Carnivore() {
        trophicLvl = 3;
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
        energy = 100; 
        stats = new int [] {300, 2, 120, 2, 200, 2}; //traits for Carnivore 
        Mutation.mutate(this);
    }

    public Carnivore (int [] newStats) {
        trophicLvl = 3;
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
        energy = 500; //Starts with zero energy
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

        if (target == null && hungry == true) { // when player has no target, using keyboard controller
            AI.checkPrey(this); // after each movement, check whether the food is in sight
        } else if (target != null && hungry == true) { // else player moves automatically to the target 
            AI.hunt(this); // Attacks preys
        }

        Herbivore herbivore = (Herbivore) getOneIntersectingObject(Herbivore.class);//ensure that the preditor is tocuhing prey 

        if (herbivore != null) {

            world.foodEaten ++;          // Increases foodeaten, a variable in My World
            killCount ++;
            energy+= (herbivore.energy)/4; //energy gained after eating

            AbstOrganism.lifeforms.remove(herbivore);
            world.removeObject(herbivore);      // Removes Algae object

            target = null; // clear the target after removing the apple

        }

        age ();     // Increases age by 1 every time the act method executes

        eat ();     // Increases energy by 1 every time the act method executes, adds 5 each time they eat other organism

        grow ();    // Grow depending on energy they have
        shift();    // Randomly moves around
        //split();    // Reproduces when reaches the certain stage

        hunger();
    }

    protected void fights(int _energy){    // Basically the calculation of attack and defense 
        energy += _energy;
        if(energy < 0){
            die();
        }

    }

    public List<Herbivore> givesOffList() {

        List<Herbivore> list = getObjectsInRange(range, Herbivore.class); // List of organisms around 
        return list;

    }
    public void hunger(){
        if(killCount >= 5){
            hungry = false;
        }
        else{
            hungry = true;
        }
        if(hungry == false){
            tempTimer ++;
            if(tempTimer >= 500){
                tempTimer = 0;
                killCount = 0;
            }
        }
        if(energy <= 0){
            die();
        }
    }

    public void eat() {
        // THIS IS HANDLED WITH HUNT
    }

    public void grow() {
        // Modify the size+10 of the image based on  the current energy
        size = (int) (0.02 * energy + 5); //Change in size+10
        GreenfootImage image = new GreenfootImage(size+10, size+10); 
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, size+10, size+10);
        image.setColor(Color.BLACK);        // Sets the color green
        image.fillRect(0, 0, size+10, size+10);
        this.setImage(image);
    }

    public void split(){
        angle_split = 360 / num_split;              // In which angle would the child go 
        // Check to see if there if enough energy (size?) to split
        if (energy >= split_energy && age < lifespan) {
            // If yes, then call the constructor for two new ones and kill the parent
            // energy -= split_energy; // Subtract the used up energy needed to split.

            // A for loop running once for each num_Split (child to be made)

            for (int i = 0; i < num_split; i ++) {

                //range = getRandomNumber(500, 700); in case range does not mutate 

                Carnivore temp_Splitted = new Carnivore(stats);//this is where the mutation for new radius is 

                Mutation.mutate(temp_Splitted);

                world.addObject(temp_Splitted, getX(), getY());

                temp_Splitted.turn ((180 - angle_split * i) + (Greenfoot.getRandomNumber(angle_split) - angle_split / 2));

            }
            die();
        }

    }

    protected List givesOffListPredator(){ // List of organisms around
        return null;
    }

    public void age() {

        age += 1; //Increase age by 1
        if (age >= lifespan) {    // If it is less than or equal to its lifespan
            world.addObject(new Carcass(energy), getX(), getY());
            die();                // dies
        }

    }

    public void die() {

        // Remove this object from its lists
        lifeforms.remove(this);
        // Remove from the world
        world.removeObject(this);

    }

    public void shift(){
        move(speed); //adjust speed ranging form 2-8 
        //randomize turning left and right
        if(Greenfoot.getRandomNumber(100)<10){
            turn(Greenfoot.getRandomNumber(90)-45);
        }
        //if at the edge turn away 
        if(age<lifespan && isAtEdge()==true){

            turn(180);

        }

    }

    public int getRandomNumber(int start, int end)
    {
        int normal = Greenfoot.getRandomNumber(end-start+1);
        return normal+start;
    }

}