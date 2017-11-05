import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/** Carcass
 * A carcass left behind which appears once an organism dies of their age reaching their lifespan, the carcass slowly decompses
 * 
 * @author Hawke, Mudaser, Parmeet, Shusil, Tim  
 * @version 17/10/2017

 */

public class Carcass extends AbstOrganism

{

    private double leftEnergy;

    public Carcass (double energy) {
        leftEnergy = energy; //energy of the living organism is now
    }

    public Carcass () {
        leftEnergy = energy; //energy of the living organism is now
    }

    public void act() 
    {
        //creat new world if world is not created yet
        if(world == null){

            world = (MyWorld) getWorld();

        }    
        eat();
        grow();
        age();
    }

    public void eat(){
        //not in use
    }

    public List<AbstOrganism> givesOffList() {
        return null;//not in use
    }
       public List < AbstOrganism > givesOffList1() {
         List < AbstOrganism > list = getObjectsInRange(range, AbstOrganism.class); // List of organisms around 
        return list;
        }
        
         public List < AbstOrganism > givesOffList2() {
         List < AbstOrganism > list = getObjectsInRange(range, AbstOrganism.class); // List of organisms around 
        return list;
        }

    public void grow(){

        size = (int) (0.02 * leftEnergy + 5); //Change in size

        GreenfootImage image = new GreenfootImage(size+5, size+5); 

        image.setColor(Color.BLACK);

        image.drawOval(0, 0, size, size);

        image.setColor(Color.YELLOW);

        image.fillOval(0, 0, size, size);

        this.setImage(image);

    }

    public void split(){
        //not in use
    }

    public void age(){
        leftEnergy-=1; //energy reduces as the mass rotts 
        //if not energy is left remove body 
        if(leftEnergy <= 0){
            die();
        }
    }

    protected void fights(int _energy){    // Basically the calculation of attack and defense 

     }

    protected List givesOffListPredator(){ // List of organisms around
        return null;
    }

    public void die(){

        world.removeObject(this);

    }

    public void shift(){
        //not in use
    }

}   