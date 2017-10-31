import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * 
 * The main world of the program
 * 
 * @author Hawke, Mudaser, Parmeet, Shusil, Tim  
 * @version 17/10/2017
 */
public class MyWorld extends World
{

    int foodEaten = 0;
    StartScreen start;
    Button button;
    public static int timer = 0;
    
    /** MyWorld
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        
        start = new StartScreen(this);
        
        Greenfoot.setWorld(start);
        
        


        makeButton(0);//draw Algae button

        makeButton(1); // Herbivore button

        makeButton(2); // Scavenger button

        makeButton(3);// Carnivore button
        
    }
    
    
    
    
      public void act() 
    {
        
        //creat new world if world is not created yet
      
        display(); // Draws the various texts on the screen
        timer ++; // Increments the shared timer, used in some act methods to improve program efficiency
       
    }
    
    int algtotal;
    
     public void display(){
         
         // Display the organism counts + foodEaten on the left
         
         int algTotal =  getObjects(Algae.class).size(); // Total Algae in the screen
          showText("Algae: "+ algTotal, 100,150);
          
         int herbTotal = getObjects(Herbivore.class).size();   // Total herbivores in the screen
            showText("Herbivores: "+herbTotal, 100,200);

         int carnTotal = getObjects(Carnivore.class).size();   // Total herbivores in the screen
            showText("Carnivores: "+carnTotal, 100,250);
            
         int scavTotal = getObjects(Scavenger.class).size();   // Total herbivores in the screen
            showText("Scavengers: "+ scavTotal, 100,300);
            
         int carcTotal = getObjects(Carcass.class).size();   // Total herbivores in the screen
            showText("Carcasses: "+carcTotal, 100,350);
            
            showText("foodEaten: "+foodEaten, 100,400);     // Total food they ate
            
        // The UI Text
            
        showText("MENU",750,50);

        showText("Algae",750,100);

        showText("Herbivore",750,200);

        showText("Scavenger",750,300);

        showText("Carnivore",750,400);
    }
 

    private void makeButton(int _trophic_Level)//create algae button

    {

        button = new Button(_trophic_Level);

        addObject(button, 750, 140 + 100 * _trophic_Level); 

    }

    
    
    
}