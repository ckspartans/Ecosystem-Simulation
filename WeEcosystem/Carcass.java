import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Carcass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Carcass extends AbstOrganism
{

    MyWorld world; //reference to the myworld class
    double leftEnergy;
    double value;
    public Carcass (double energy) {
        leftEnergy = energy;
    }

    public void act() 
    {
        //creat new world if world is not created yet
        if(world == null){

            world = (MyWorld) getWorld();

        }    
        eat();
        grow();
        age();
    }

    public void eat(){
        leftEnergy-=1;
    }

    public void grow(){
    
        size = (int) (0.02 * leftEnergy + 5); //Change in size
        GreenfootImage image = new GreenfootImage(size, size); 
        image.setColor(Color.BLACK);
        image.drawOval(0, 0, size, size);
        image.setColor(Color.BLUE);        // Sets the color green
        image.fillOval(0, 0, size, size);
        this.setImage(image);
        
    }

    public void split(){}

    public void age(){

        if(leftEnergy <= 0){

            die();
        }

    }

 public List<Herbivore> givesOffList() {
  return null;
}


    public void die(){
        world.removeObject(this);
    }

    public void shift(){}

    public void shift(int action, AbstOrganism target){}

    public int detect(int trophic_lvl){return 0;}

    protected void say(String s){

        System.out.println(s);

    }

    public int getRandomNumber(int start,int end) {
        int normal = Greenfoot.getRandomNumber(end-start+1);
        return normal+start;

    }

}   