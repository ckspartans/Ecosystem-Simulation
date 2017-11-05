import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sight extends Actor
{
    AbstOrganism o;     // Reference to Organism to get attached to 
    int size = 80;      // This is just for testing
    int range;          // That stores the range passed from AI

    public Sight(){

    }
  
    public Sight(AbstOrganism _o, int _range){
        range = _range; // Equals the range that was sent in from AI
        GreenfootImage image = new GreenfootImage(_range, _range); // Creates an empty transparent image with the given size
        image.drawOval(0, 0, _range, _range);   // Draws oval with the given size on top of transparent image 
        image.setColor(Color.YELLOW);        // Sets the color red
        image.fillOval(0, 0, _range, _range);   // Fills oval with the current color
        this.setImage(image);                  // Sets this as an actor image
        image.setTransparency(20);
        o = _o; // Lets say, Parent Organism

    }

    public void act() 
    { 
       // System.out.println("Range transferred: " + range);  // Just for testing

        if(o.getWorld() != null ){ // Only if there are organisms in the World to get attached

            setLocation(o.getX(), o.getY());  // Sets this actor's location to the Parent Organism

        }else{

            getWorld().removeObject(this);  // If there is no Parent Organism and some how this actor gets added, it will delete then

        }
        
         
       
    }    

}
