import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;

/** Herbivore
 * A Herbivore tries to track down and eat any algae in range.
 * 
 * @author Hawke, Mudaser, Parmeet, Shusil, Tim  

 * @version 12/10/2017

 */

public class Herbivore extends AbstOrganism 

{

    /**

     * Act - do whatever the Algae wants to do. This method is called whenever

     * the 'Act' or 'Run' button gets pressed in the environment.

     */

    // Base Constructor
    // protected Algae target = null; // when it is null, player has no target
    //private boolean hungry = false;
    private int killCount = 0;
    private int tempTimer = 0;

    public Herbivore () {
        trophicLvl = 2;
        GreenfootImage image = new GreenfootImage(size+10, size+10); // Creates an empty transparent image with the given size+10
        image.setColor(Color.BLACK);        // Sets the color black
        image.drawRect(0, 0, size+10, size+10);   // Draws oval with the given size+10 on top of transparent image 
        image.setColor(Color.GREEN);        // Sets the color red
        image.fillRect(0, 0, size+10, size+10);   // Fills oval with the current color
        this.setImage(image);                  // Sets this as an actor image
        lifeforms.add(this); // Adds this algae to the list containing all objects under the class type of AbstOrganism
        prey = new ArrayList <AbstOrganism> ();//list of all that the types of organism can feed on
        predators = new ArrayList <AbstOrganism> ();//list of all the types of organsims that the organism can be eaten by   
        age = 0; // An int that increments each time act runs to store the age
        energy = 50; //Starts with zero energy

        stats = new int [] {300, 1, 120, 2, 200, 2}; //traits for Herbivore 

        this.range = range; // Range equals random number that is set during reproduction, how far the detection radius is for the organism
        Mutation.mutate(this);

        attack = 3;
        defense = 1;    // Defending armour level 2
    }

    public Herbivore (int [] newStats) {
        trophicLvl = 2;
        GreenfootImage image = new GreenfootImage(size+10, size+10); // Creates an empty transparent image with the given size+10
        image.setColor(Color.BLACK);        // Sets the color black
        image.drawRect(0, 0, size+10, size+10);   // Draws oval with the given size+10 on top of transparent image 
        image.setColor(Color.GREEN);        // Sets the color red
        image.fillRect(0, 0, size+10, size+10);   // Fills oval with the current color
        this.setImage(image);                  // Sets this as an actor image
        lifeforms.add(this); // Adds this algae to the list containing all objects under the class type of AbstOrganism
        prey = new ArrayList <AbstOrganism> ();//list of all that the types of organism can feed on
        predators = new ArrayList <AbstOrganism> ();//list of all the types of organsims that the organism can be eaten by   
        age = 0; // An int that increments each time act runs to store the age
        energy = 50; //Starts with zero energy
        stats = newStats; //traits for Herbivore 
        this.range = range; // Range equals random number that is set during reproduction, how far the detection radius is for the organism
    }

    public void act() 
    {

        lifespan = stats [0]; //max limit an organism can be in the world 
        speed = stats [1]; //rate of movement of the organism 
        range = stats [2]; // Int storing detection range
        num_split = stats [3]; //number of offspring an organism can produce
        split_energy = stats [4]; //set energy needed to perform a split
        mutation_rate = stats [5]; // An int which determines how many random gene stats can be changed

        AI.checkPrey(this);
        AI.checkPredator(this);
        // If the world reference is not stored:
        if (world == null) {

            world = (MyWorld) getWorld (); // Store the reference to the current world

        }   

        // if (target == null && hungry == true) { // when player has no target, using keyboard controller
        //    AI.checkPrey(this); // after each movement, check whether the food is in sight
        //   AI.checkPredator(this);
        // } else if (target != null && hungry == true) { // else player moves automatically to the target 
        // AI.hunt(this); // Attacks preys
        //  AI.flee(this);
        //    }

        Algae algae = (Algae) getOneIntersectingObject(Algae.class);
        if (algae != null) {
            ((MyWorld) getWorld()).foodEaten ++;          // Increases foodeaten, a variable in My World
            energy += (algae.energy)/2; //energy gained after eating
            ((MyWorld) getWorld()).removeObject(algae);      // Removes Algae object
            //  target = null; // clear the target after removing the apple

        }

        age ();     // Increases age by 1 every time the act method executes
        eat ();     // Increases energy by 1 every time the act method executes, adds 5 each time they eat other organism
        grow ();    // Grow depending on energy they have
        shift();    // Randomly moves around
        split();    // Reproduces when reaches the certain stage
        hunger();    
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

    public List<Algae> givesOffList() { //List of preys around
        List<Algae> list = getObjectsInRange(range, Algae.class); // List of Algae around 
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

    protected List givesOffListPredator(){ // List of predator around
        List<Carnivore> list = getObjectsInRange(range, Carnivore.class); // List of Carnvivores around 
        return list;
    }

    public void eat() {

    }

    protected void fights(int _energy){
        energy += _energy;
        if(energy < 0){
            die();
        }

    }

    public void grow() {
        // Modify the size+10 of the image based on  the current energy
        size = (int) (0.02 * energy + 5); //Change in size

        GreenfootImage image = new GreenfootImage(size+5, size+5); 

        image.setColor(Color.BLACK);

        image.drawRect(0, 0, size, size);

        image.setColor(Color.GREEN);

        image.fillRect(0, 0, size, size);

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
                Herbivore temp_Splitted = new Herbivore(stats);//this is where the mutation for new range is 
                Mutation.mutate(temp_Splitted);
                world.addObject(temp_Splitted, getX(), getY());
                temp_Splitted.turn ((180 - angle_split * i) + (Greenfoot.getRandomNumber(angle_split) - angle_split / 2));

            }

            die();

        }

    }

    public void age() {

        age += 1; //Increase age by 1
        if (age >= lifespan && energy != 0) {    // If it is less than or equal to its lifespan
            world.addObject(new Carcass(energy), getX(), getY());
            die();                // dies
        }

    }

    public void die() {
        // Remove this object from its lists
        lifeforms.remove(this);
        // Remove from the world
        world.removeObject(this);
        return;

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

}