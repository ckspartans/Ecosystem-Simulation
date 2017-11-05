import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;

/** Scavenger
 * Scavengers feed off of the dead bodies of other organisms.
 * 
 * @author Hawke, Mudaser, Parmeet, Shusil, Tim  
 * @version 17/10/2017

 */

public class Scavenger extends AbstOrganism

{



    /**
     * Act - do whatever the Algae wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    // Base Constructor

    protected Carcass target = null; // when it is null, player has no target

    public Scavenger() {
        trophicLvl = 4;
        GreenfootImage image = new GreenfootImage(size+10, size+10); // Creates an empty transparent image with the given size+10
        image.setColor(Color.BLACK);        // Sets the color black
        image.drawRect(0, 0, size+10, size+10);   // Draws oval with the given size+10 on top of transparent image 
        image.setColor(Color.YELLOW);        // Sets the color red
        image.fillRect(0, 0, size+10, size+10);   // Fills oval with the current color
        this.setImage(image);                  // Sets this as an actor image
        lifeforms.add(this); // Adds this algae to the list containing all objects under the class type of AbstOrganism
        prey = new ArrayList <AbstOrganism> ();//list of all that the types of organism can feed on
        predators = new ArrayList <AbstOrganism> ();//list of all the types of organsims that the organism can be eaten by   
        age = 0; // An int that increments each time act runs to store the age
        energy = 0; //Starts with zero energy
        stats = new int [] {300, 1, 120, 2, 200, 2}; //traits 
        Mutation.mutate(this);
    }

    public Scavenger (int [] newStats) {
        trophicLvl = 4;
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
        energy = 0; //Starts with zero energy

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

        checkCarcass();
        // If the world reference is not stored:
        if (world == null) {

            world = (MyWorld) getWorld (); // Store the reference to the current world

        }   

        if (target == null) { // when player has no target, using keyboard controller
            checkCarcass(); // after each movement, check whether the food is in sight
        } else { // else player moves automatically to the target 
            hunt(); // Attacks preys
        }

        Carcass carcass = (Carcass) getOneIntersectingObject(Carcass.class);
        if (carcass != null) {
            ((MyWorld) getWorld()).foodEaten ++;          // Increases foodeaten, a variable in My World
            energy+= (carcass.energy)/4; //energy gained after eating
            ((MyWorld) getWorld()).removeObject(carcass);      // Removes Algae object
            target = null; // clear the target after removing the apple

        }

        age ();     // Increases age by 1 every time the act method executes
        eat ();     // Increases energy by 1 every time the act method executes, adds 5 each time they eat other organism
        grow ();    // Grow depending on energy they have
        shift();    // Randomly moves around
        split();    // Reproduces when reaches the certain stage

    }

    protected void checkCarcass() { // Check if there is any algae within the given radius
        List<Carcass> list = getObjectsInRange(range, Carcass.class); // getting all apples in range
        if (list.isEmpty()){     // If there is no food, it gets out of function
            return;
        }

        else {
            //if(target != (Algae) list.get(0)){
            target = (Carcass) list.get(0); // always set the first one in the list as the target

        }
    }

    public List<AbstOrganism> givesOffList() {

        List<AbstOrganism> list = getObjectsInRange(range, AbstOrganism.class); // List of organisms around 

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

    protected void hunt(){
        // getWorld().showText("Im dead", 159,225);
        // double valueX = 0; 
        if(age < lifespan){ // If its not dying and the food it is going to eat is not going to die

            turnTowards(target.getX(), target.getY());         // Turns toward the food

            // valueX = target.getX();
            target = null;

            move(5);                                            // Moves in certain speed

            // getWorld().showText("Im dead", target.getX(),target.getY());
            //getWorld().showText("Im dead", 159,225);
        }

    }

    public void eat() {
        // Increases the energy amount.
        //energy += 1;

    }

    protected List givesOffListPredator(){ // List of organisms around
        return null;
    }

    public void grow() {

        // Modify the size+10 of the image based on  the current energy

       size = (int) (0.02 * energy + 5); //Change in size

        GreenfootImage image = new GreenfootImage(size+5, size+5); 

        image.setColor(Color.BLACK);

        image.drawRect(0, 0, size, size);

        image.setColor(Color.YELLOW);

        image.fillRect(0, 0, size, size);

        this.setImage(image);

    }

    public void split(){

        angle_split = 360 / num_split;              // In which angle would the child go 

        // Check to see if there if enough energy (size?) to split

        if (energy >= split_energy && age < lifespan) {

            // If yes, then call the constructor for two new ones and kill the parent
            // A for loop running once for each num_Split (child to be made)
            for (int i = 0; i < num_split; i ++) {
                Scavenger temp_Splitted = new Scavenger(stats);//this is where the mutation for new radius is 
                Mutation.mutate(temp_Splitted);
                world.addObject(temp_Splitted, getX(), getY());

                temp_Splitted.turn ((180 - angle_split * i) + (Greenfoot.getRandomNumber(angle_split) - angle_split / 2));

            }
            die();
        }

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

    protected void fights(int _energy){    // Basically the calculation of attack and defense 

    }
}