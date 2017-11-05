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
    public boolean auto = true;
    public static int timer = 0;
    int algTotal;
    int herbTotal;
    int carnTotal;
    int scavTotal;
    /** MyWorld
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    

        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 

        start = new StartScreen(this);

        setPaintOrder (Button.class, Parasite.class, Carnivore.class, Scavenger.class, Herbivore.class, Algae.class);
        Greenfoot.setWorld(start);
        
        makeButton(0);//draw Algae button
        makeButton(1); // Herbivore button
        makeButton(2); // Scavenger button
        makeButton(3);// Carnivore button
        makeButton(4);
    }

    public void act() 
    {

        //creat new world if world is not created yet

        display(); // Draws the various texts on the screen
        timer ++; // Increments the shared timer, used in some act methods to improve program efficiency

        if(auto == true){
            if(algTotal <= 10){
                for (int i = 0; i < 30; i ++) {
                    addObject(new Algae(),Greenfoot.getRandomNumber(600),Greenfoot.getRandomNumber(600));
                }
            }
            if(herbTotal <= 1){
                for (int i = 0; i < 8; i ++) {
                    addObject(new Herbivore(),Greenfoot.getRandomNumber(600),Greenfoot.getRandomNumber(600));
                }
            }
            if(carnTotal <= 2){
                for (int i = 0; i < 4; i ++) {
                    addObject(new Carnivore(),Greenfoot.getRandomNumber(600),Greenfoot.getRandomNumber(600));
                }
                for (int i = 0; i < 3; i ++) {
                    addObject(new Omnivore(),Greenfoot.getRandomNumber(600),Greenfoot.getRandomNumber(600));
                }
            }
            if(scavTotal <= 0){
                for (int i = 0; i < 3; i ++) {
                    addObject(new Scavenger(),Greenfoot.getRandomNumber(600),Greenfoot.getRandomNumber(600));
                }
                for (int i = 0; i < 6; i ++) {
                    addObject(new Parasite(),Greenfoot.getRandomNumber(600),Greenfoot.getRandomNumber(600));
                }
            }
        }
    }

    public void display(){

        // Display the organism counts + foodEaten on the left

        algTotal =  getObjects(Algae.class).size(); // Total Algae in the screen
        showText("Algae: "+ algTotal, 100,150);

        herbTotal = getObjects(Herbivore.class).size();   // Total herbivores in the screen
        showText("Herbivores: "+herbTotal, 100,200);

        carnTotal = getObjects(Carnivore.class).size();   // Total herbivores in the screen
        showText("Carnivores: "+carnTotal, 100,250);

        scavTotal = getObjects(Scavenger.class).size();   // Total herbivores in the screen
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
        showText("Auto: " + auto,750,500);
        
    }

    private void makeButton(int _trophic_Level)//create algae button
    {
        button = new Button(_trophic_Level);
        addObject(button, 750, 140 + 100 * _trophic_Level); 
    }


}