import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     *  
     */

    // IntroScreen intro;

    public Omnivore subject1;
    int foodEaten = 0;

    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(500, 500, 1); //600,400
        //intro = new IntroScreen(this);
        // Greenfoot.setWorld(intro);
        setPaintOrder (Omnivore.class);

        subject1 = new Omnivore(20); // initiate with the radius value (>0), if the radius is too big it can see the whole map, if too small it must be very close to see
        addObject(subject1, 300, 200);

        // Resets the lifeforms array list
        AbstOrganism.lifeforms = new ArrayList ();

    }

    public void act () {

    }
   
}