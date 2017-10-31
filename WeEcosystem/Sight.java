import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sight extends Actor
{
    AbstOrganism o;
    int size = 80;
    
    public Sight(AbstOrganism _o){
        
        GreenfootImage image = new GreenfootImage(size+10, size+10); // Creates an empty transparent image with the given size+10
       // image.setColor(Color.BLACK);        // Sets the color black
        image.drawOval(0, 0, size+10, size+10);   // Draws oval with the given size+10 on top of transparent image 
        image.setColor(Color.GREEN);        // Sets the color red
        image.fillOval(0, 0, size+10, size+10);   // Fills oval with the current color
        this.setImage(image);                  // Sets this as an actor image
        o = _o;//
        
    }
    
    public void act() 
    {
        
      
      setLocation(o.getX(), o.getY());
        
       
    }    
}
