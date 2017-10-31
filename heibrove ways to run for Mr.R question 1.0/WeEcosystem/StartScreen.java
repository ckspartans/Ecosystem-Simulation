import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/** StartScreen

 * The starting screen of the program, it provides the users with the list of authors, can be clicked on to move to the rest of the program.

 * 

 * @author Hawke, Mudaser, Parmeet, Shusil, Tim  
 * @version 17/10/2017

 */

public class StartScreen extends World

{

    int menuHeight=100;

    int menuWidth = 200;

    /**

     * Constructor for objects of class StartScreen.

     * 

     */
    
    MyWorld world;

    public StartScreen()

    {    

        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.

        super(800, 600, 1);

        showText("Welcome to the Simulator",400,100);

        showText("Click anywhere to begin",400,200);

        showText("Brought to you by weorganisms:",400,300);

        showText("Parmeet, Shusil, Mudaser, Hawke, Tim", 400,400);

    }
    
    public StartScreen(MyWorld w)

    {    

        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.

        super(800, 600, 1);
        
        world = w;

        showText("Welcome to the Simulator",400,100);

        showText("Click anywhere to begin",400,200);

        showText("Brought to you by weorganisms:",400,300);

        showText("Hawke, Mudaser, Parmeet, Shusil, Tim", 400,400);

    }

    public void act(){

       //Greenfoot.setWorld(new StartScreen()); 

       if (Greenfoot.mouseClicked(this)) {

        Greenfoot.setWorld(world); // Once clicked, the start screen changes to the MyWorld screen where the main program takes place.

      }
 

    }
    
}