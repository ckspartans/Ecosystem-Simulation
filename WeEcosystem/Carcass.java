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



    MyWorld world; //reference to the myworld class

    double leftEnergy;

    double value;

    public Carcass (double energy) {

        leftEnergy = energy;

    }
     public Carcass () {

        leftEnergy = energy;

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

        leftEnergy-=1;

    }


    
     public List<AbstOrganism> givesOffList() {
         
         
 
    
    
    return null;
}

    

    public void grow(){

    

        size = (int) (0.02 * leftEnergy + 5); //Change in size

        GreenfootImage image = new GreenfootImage(size+5, size+5); 

        image.setColor(Color.BLACK);

        image.drawOval(0, 0, size, size);

        image.setColor(Color.BLUE);        // Sets the color green

        image.fillOval(0, 0, size, size);

        this.setImage(image);

        

    }



    public void split(){}

    


    public void age(){



        if(leftEnergy <= 0){



            die();

        }



    }

 protected List givesOffListPredator(){ // List of organisms around

        return null;
    }

    public void die(){

        world.removeObject(this);

    }



    public void shift(){}




}   