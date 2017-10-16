

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * 
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
     //arraly organism
       int foodEaten = 0;
     
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
    }
    
    
    
    
      public void act() 
    {
        
        //creat new world if world is not created yet
      
      display();
       
    }
    
    int algtotal;
    
     public void display(){
          int algtotal =  getObjects(Algae.class).size(); // Total Algae in the screen
          showText("Algae: "+algtotal, 100,150);
          
          int Herbtotal = getObjects(Herbivore.class).size();   // Total herbivores in the screen
            showText("Herbivore: "+Herbtotal, 100,200);

            
            showText("foodEaten: "+foodEaten, 100,250);     // Total food they ate
    }
    
    
    /*
    public void addOrg(AbstOrganism input){
        
        sho
        
        
        
    }
    */
   //addPlants(abstorganism input)
    //organism.add(input);
}