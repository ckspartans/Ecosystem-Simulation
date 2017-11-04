import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;

/** AbstUI

 * Abstract User Interface class, super class of buttons

 * 

 * @author Hawke, Mudaser, Parmeet, Shusil, Tim  
 * @version 17/10/2017

 */

public abstract class AbstUI extends Actor

{

    /**

     * Act - do whatever the AbstUI wants to do. This method is called whenever

     * the 'Act' or 'Run' button gets pressed in the environment.

     */

    public int mouseY;

    public int mouseX;

    MyWorld world;

    public boolean mouseDown;

    public int select;
    
    public int trophic_Level; // The type of organism spawned once clicked on.

}