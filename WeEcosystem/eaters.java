import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class eaters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class eaters extends Actor
{
    /**
     * Act - do whatever the eaters wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        
        shimee();
       getImage().scale(30, 30);
        // Add your action code here.
    }    
     public int getRandomNumber(int start,int end)
{
       int normal = Greenfoot.getRandomNumber(end-start+1);
       return normal+start;
}
public void shimee(){
    
    
       
      move(Greenfoot.getRandomNumber(5)+5);
        turn(Greenfoot.getRandomNumber(3)+30);
      
    
    
}

}
