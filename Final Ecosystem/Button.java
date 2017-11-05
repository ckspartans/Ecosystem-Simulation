import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;

/** Button

 * Can be clicked on to spawn organisms.

 * 

 * @author Hawke, Mudaser, Parmeet, Shusil, Tim  
 * @version 17/10/2017

 */

public class Button extends AbstUI

{

    /**

     * Act - do whatever the Button wants to do. This method is called whenever

     * the 'Act' or 'Run' button gets pressed in the environment.

     */

    public Button(int _trophic_Level){

        mouseY = 0;

        mouseX = 0;

        MyWorld world;

        mouseDown = false;

        select = 0;

        this.trophic_Level = _trophic_Level;

    }

    public void act(){//if mouse clicked
        
        if (world == null) {
        
            world = (MyWorld) getWorld (); // Store the reference to the current world
            
        }
        
        MouseInfo mouse = Greenfoot.getMouseInfo();        
        if (Greenfoot.mousePressed(this)){//when mouse clicks on button
            mouseDown = true;
            getWorld().showText("Selected", 900,240);
            if (trophic_Level == 0) {
                getWorld().addObject(new Algae(),Greenfoot.getRandomNumber(500),Greenfoot.getRandomNumber(500));//add an algae every time button is clicked
            } else if (trophic_Level == 1) {
                getWorld().addObject(new Herbivore(),Greenfoot.getRandomNumber(500),Greenfoot.getRandomNumber(500));//add an algae every time button is clicked
            } else if (trophic_Level == 2) {
                getWorld().addObject(new Scavenger(),Greenfoot.getRandomNumber(500),Greenfoot.getRandomNumber(500));//add an algae every time button is clicked
            } else if (trophic_Level == 3) {
                getWorld().addObject(new Carnivore(),Greenfoot.getRandomNumber(500),Greenfoot.getRandomNumber(500));//add an algae every time button is clicked
            }
            else if (trophic_Level == 4) {
                if (world != null) {
                    
                    if(world.auto == false){
                        world.auto = true;
                    }
                    else if(world.auto == true){
                        world.auto = false;
                    }

                }
            }

        }

    }

}