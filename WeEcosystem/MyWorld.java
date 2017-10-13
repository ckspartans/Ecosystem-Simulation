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
    
    public TestOrganism subject1;
    int foodEaten = 0;
    
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1920, 1080, 1, false); //600,400
        //intro = new IntroScreen(this);
       // Greenfoot.setWorld(intro);
        setPaintOrder (TestOrganism.class);
        
        subject1 = new TestOrganism(120); // initiate with the radius value (>0)
        addObject(subject1, 300, 200);
        
        
        
        // Resets the lifeforms array list
        AbstOrganism.lifeforms = new ArrayList ();
        
        
    }
    
    public void act () {
        
        if (Greenfoot.isKeyDown("space")) {
        
            removeObject(subject1);
        
        }
        
        showText("Food Eaten: " + foodEaten, 100, 20);
        // Check if the lifeforms list contains at least one item
        if (AbstOrganism.lifeforms != null) {
            
            showText("Lifeforms: " + AbstOrganism.lifeforms.size(), 500, 100);
            
        }
        
    }
    
}