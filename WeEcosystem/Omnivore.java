import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class omnivore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Omnivore extends AbstOrganism
{
    // some constants of directio used for rectMove()
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;

    protected int speed = 5;
    protected int spX = 0;
    protected int spY = 0;
    protected int radius = 120; // radius, default using 1
    protected Algae target = null; // when it is null, player has no target

    public Omnivore(int radius) { //the bigger the radius the farther it can see
        lifeforms = new ArrayList <AbstOrganism> ();//list of all the organsims in the game
        lifeforms.add(this);
        prey = new ArrayList <AbstOrganism> ();//list of all that the types of organism can feed on
        predators = new ArrayList <AbstOrganism> ();//list of all the types of organsims that the organism can be eaten by 

        trophic_lvl = 1; // The trophic level of the organism, its place in the food web / chain
        age = 0; // An int that increments each time act runs to store the age
        energy = 0;
        hasMutated = false;

        stats = new int [] {300, 1, 1, 2, 200, 2}; //traits for omnivore 

        if (radius <= 0) { // exception check
            System.out.println("Invalid initialization !");
            System.out.println("Radius cannot be set smaller than 1 !");
        } else {
            this.radius = radius;
        }
    }

    /**
     * Act - do whatever the omnivore wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

        lifespan = stats [0]; //max limit an organism can be in the world 
        speed = stats [1]; //rate of movement of the organism 
        range = stats [2]; // Int storing detection range
        num_split = stats [3]; //number of offspring an organism can produce
        split_energy = stats [4]; //set energy needed to perform a split
        mutation_rate = stats [5]; // An int which determines how many random gene stats can be changed

        // Add your action code here.
        if (target == null) { // when player has no target, using keyboard controller
            checkKeys();
            checkAlgae(); // after each movement, check whether the food is in sight
        } else { // else player moves automatically to the target 
            hunt();
        }
        
        Algae algae = (Algae) getOneIntersectingObject(Algae.class);
        if (algae != null) {

            ((MyWorld) getWorld()).foodEaten ++;
            ((MyWorld) getWorld()).removeObject(algae);
            energy+=5; //energy gained after eating 
            target = null; // clear the target after removing the apple

        }
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

        age (); //commented out beacuse it is unstable 
        eat ();
        grow (); 
      // hunt();
       shift();
        split ();
        

    }    

    int changeNum = 0;
    protected void checkAlgae() {
        List list = getObjectsInRange(radius, Algae.class); // getting all apples in range
        if (list.isEmpty()) 
            return;
        else {
           
            target = (Algae) list.get(changeNum); // always set the first one in the list as the target
           changeNum+=1;
            // notice that it may not be the closet one
            // you can foreach the list and compare the distance of each apples to choose the closet one
        }
    }
      public void hunt(){
        turnTowards(target.getX(), target.getY());
        
        
        
        move(5);
    }
    

    protected void moveTowardsTarget() {
        // each time move "speed" pixels
        
        
        int stepsLeft = speed;
        int tx = target.getX(); // target's x
        int ty = target.getY(); // target's y
        
        //turnTowards(tx,ty);
        
        
        int dx = tx - getX();  // difference in x
        int dy = ty - getY(); // difference in y
      
        while(stepsLeft > 0) { // move when there are steps left to go
            // determine the direction to move
            if (dx > 0) { // right
                if (dx >= stepsLeft) { // when difference in x is larger than steps you can move
                    rectMove(stepsLeft, RIGHT);
                    stepsLeft = 0; // there is no steps left for you to move than
                } else {
                    rectMove(dx, RIGHT);
                    stepsLeft -= dx;
                }
            } else if (dx < 0) { // left
                if (dx <= -stepsLeft) {
                    rectMove(stepsLeft, LEFT);
                    stepsLeft = 0;
                } else {
                    rectMove(-dx, LEFT);
                    stepsLeft -= -dx;
                }
            } else if (dy > 0) { // down
                if (dy >= stepsLeft) {
                    rectMove(stepsLeft, DOWN);
                    stepsLeft = 0;
                } else {
                    rectMove(dy, DOWN);
                    stepsLeft -= dy;
                }
            } else if (dy < 0) { // up
                if (dy <= stepsLeft) {
                    rectMove(stepsLeft, UP);
                    stepsLeft = 0;
                } else {
                    rectMove(-dy, UP);
                    stepsLeft -= -dy;
                }
            }
            // refresh differences in position
            dx = tx - getX();
            dy = ty - getY();
            
        }
        
    }

    protected void rectMove(int step, int direction) {
        // move in 90 degrees
        switch (direction) {
            case LEFT: setLocation(getX() - step, getY()); break;
            case RIGHT: setLocation(getX() + step, getY()); break;
            case UP: setLocation(getX(), getY() - step); break;
            case DOWN: setLocation(getX(), getY() + step); break;
        }
    }

    protected void checkKeys () {

        if (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) {

            spY = -speed;

        } else if (Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) {

            spY = speed;

        } else {

            spY = 0;

        }

        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {

            spX = -speed;

        } else if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {

            spX = speed;

        } else {

            spX = 0;

        }

        setLocation(getX () + spX, getY () + spY);
    }

    public void shift(){

        move(speed); //adjust speed ranging form 2-8 

        //randomize turning left and right
        if(Greenfoot.getRandomNumber(100)<10){

            turn(Greenfoot.getRandomNumber(90)-45);
        }
        
        //if at the edge turn away 
        if(isAtEdge()==true){
            turn(180);
        }
        
      

    }

    public void eat() {
        // Increases the energy amount.
        energy += 1;

    }

    public void grow() { // Modify the size of the image based on  the current energy
        //increase size of body
        size = (int) (0.02 * energy + 5);
        getImage().scale(size+30, size+30);

    }

    public void split(){

        angle_split = 360 / num_split;

        // Check to see if there if enough energy (size?) to split
        if (energy >= split_energy && age < lifespan) {

            // If yes, then call the constructor for two new ones and kill the parent
            //energy -= split_energy; // Subtract the used up energy needed to split.

            // A for loop running once for each num_Split (child to be made)
            for (int i = 0; i < num_split; i ++) {

                Omnivore temp_Splited = new Omnivore(range);//this is where the mutation for new radius is 

                //lifeforms.add(new Algae());
                world.addObject(temp_Splited, getX(), getY());

                temp_Splited.turn ((180 - angle_split * i) + (Greenfoot.getRandomNumber(angle_split) - angle_split / 2));
                temp_Splited.move(7);

            }
            

            die();

        }

        //say ("split not implemented");

    }

   
    public void age() {

        // Increases age
        age +=.1;
        // Checks to see if past lifespan
        if (age >= lifespan) {    

            die();
        }

        //say ("age not implemented");
    }

    public void die() { //this is causing the program to pause 

        // Remove this object from its lists
       lifeforms.remove(this);
        // Remove from the world
       world.removeObject(this);
        //say ("die not implemented");

    }

    public void mutate() {
        //say ("Mutate not implemented");
        for (int i = 0; i < stats [5]; i ++) {

            int mutating = (int) Greenfoot.getRandomNumber (stats.length - 1);

            if (mutating == 0) { // lifespan

                stats [mutating] = (int) getRandomNumber(500, 1000);

            } else if (mutating == 1) { // speed

                stats [mutating] = (int) getRandomNumber(2, 8);

            } else if (mutating == 2) { // range

                stats [mutating] = (int) getRandomNumber(40, 300);

            } else if (mutating == 3) { // num_split

                stats [mutating] = (int) getRandomNumber(2, 2);

            } else if (mutating == 4) { // split_energy

                stats [mutating] = (int) getRandomNumber(400, 700);

            } else if ( mutating == 5) { // mutation_rate

                stats [mutating] = (int) getRandomNumber(2, 6);

            }     
        }
    }

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