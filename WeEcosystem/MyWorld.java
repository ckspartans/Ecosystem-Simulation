import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * 
 * 
 * Write a description of class MyWorld here.
 * @author Hawke, Mudaser, Parmeet, Shusil, Tim   
 * @version 12/10/2017
 */
public class MyWorld extends World
{
     //arraly organism
     
     
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        // Resets the lifeforms array list
        AbstOrganism.lifeforms = new ArrayList ();
        
    }
    
    public void act () {
    
        showText("" + AbstOrganism.lifeforms.size(), 200, 200);
    
    }
    
    /*
    public void addOrg(AbstOrganism input){
        
        
        
        
        
    }
    */
   //addPlants(abstorganism input)
    //organism.add(input);
}
