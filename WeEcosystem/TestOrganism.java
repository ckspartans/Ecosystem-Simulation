import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
 
/**
 * Write a description of class TestOrganism here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestOrganism extends Actor{
   
    // some constants of directio used for rectMove()
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;
   
    protected int speed = 5;
    protected int spX = 0;
    protected int spY = 0;
    protected int radius = 1; // radius, default using 1
    protected Algae target = null; // when it is null, player has no target
   
    public TestOrganism(int radius) {
        if (radius <= 0) { // exception check
            System.out.println("Invalid initialization !");
            System.out.println("Radius cannot be set smaller than 1 !");
        } else {
            this.radius = radius;
        }
    }
   
    /**
     * Act - do whatever the TestOrganism wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if (target == null) { // when player has no target, using keyboard controller
            checkKeys();
            checkApples(); // after each movement, check whether there're apples in sight
        } else { // else player moves automatically to the target apple
            moveTowardsTarget();
        }
        // removeTouching (foodApple.class);
        // see wether interects the apple or not
        Algae algae = (Algae) getOneIntersectingObject(Algae.class);
        if (algae != null) {
           
            ((MyWorld) getWorld()).foodEaten ++;
            ((MyWorld) getWorld()).removeObject(algae);
            target = null; // clear the target after removing the apple
       
        }
    }
   
    protected void checkApples() {
        List list = getObjectsInRange(radius, Algae.class); // getting all apples in range
        if (list.isEmpty()) return;
        else {
            target = (Algae) list.get(0); // always set the first one in the list as the target
            // notice that it may not be the closet one
            // you can foreach the list and compare the distance of each apples to choose the closet one
        }
    }
   
    protected void moveTowardsTarget() {
        // each time move "speed" pixels
        int stepsLeft = speed;
        int tx = target.getX(); // target's x
        int ty = target.getY(); // target's y
        int dx = tx - getX();  // difference in x
        int dy = ty - getY(); // difference in y
        ///////////////////////////////////// just for debug,jiu shi da ying chu lai kan kan shu ju.
        System.out.println("dx = " + dx);
        System.out.println("dy = " + dy);
        /////////////////////////////////////
        while(stepsLeft > 0) { // move when there are steps left to go
            // determine the direction to move
            if (dx > 0) { // right
                if (dx >= stepsLeft) { // when difference in x is larger than steps you can move
                    rectMove(stepsLeft, RIGHT);
                    stepsLeft = 0; // there is no steps left for you to move than
                } else {
                    rectMove(dx, RIGHT);
                    stepsLeft -= dx;
                }
            } else if (dx < 0) { // left
                if (dx <= -stepsLeft) {
                    rectMove(stepsLeft, LEFT);
                    stepsLeft = 0;
                } else {
                    rectMove(-dx, LEFT);
                    stepsLeft -= -dx;
                }
            } else if (dy > 0) { // down
                if (dy >= stepsLeft) {
                    rectMove(stepsLeft, DOWN);
                    stepsLeft = 0;
                } else {
                    rectMove(dy, DOWN);
                    stepsLeft -= dy;
                }
            } else if (dy < 0) { // up
                if (dy <= stepsLeft) {
                    rectMove(stepsLeft, UP);
                    stepsLeft = 0;
                } else {
                    rectMove(-dy, UP);
                    stepsLeft -= -dy;
                }
            }
            // refresh differences in position
            dx = tx - getX();
            dy = ty - getY();
        }
    }
   
    protected void rectMove(int step, int direction) {
        // move in 90 degrees
        switch (direction) {
            case LEFT: setLocation(getX() - step, getY()); break;
            case RIGHT: setLocation(getX() + step, getY()); break;
            case UP: setLocation(getX(), getY() - step); break;
            case DOWN: setLocation(getX(), getY() + step); break;
        }
    }
   
    protected void checkKeys () {
   
        if (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) {
       
            spY = -speed;
       
        } else if (Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) {
       
            spY = speed;
       
        } else {
       
            spY = 0;
       
        }
       
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
       
            spX = -speed;
       
        } else if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
       
            spX = speed;
       
        } else {
       
            spX = 0;
       
        }
       
        setLocation(getX () + spX, getY () + spY);
       
    }
   
   
}
