import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.*;

/**
 * Write a description of class Parasite here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Parasite extends Carcass
{
    double consumptionRate = 1;
    Boolean attached = false;
    //double energy = 10;
        
    public Parasite () {
        trophicLvl = 6;
        GreenfootImage image = new GreenfootImage(size+10, size+10); // Creates an empty transparent image with the given size+10
        image.setColor(Color.BLACK);        // Sets the color black
        image.drawRect(0, 0, size+10, size+10);   // Draws oval with the given size+10 on top of transparent image 
        image.setColor(Color.WHITE);        // Sets the color red
        image.fillRect(0, 0, size, size);   // Fills oval with the current color
        this.setImage(image);                  // Sets this as an actor image
        lifeforms.add(this); // Adds this algae to the list containing all objects under the class type of AbstOrganism
        prey = new ArrayList <AbstOrganism> ();//list of all that the types of organism can feed on
        predators = new ArrayList <AbstOrganism> ();//list of all the types of organsims that the organism can be eaten by   
        age = 0; // An int that increments each time act runs to store the age
        energy = 10; 

        stats = new int [] {300, 1, 120, 2, 200, 2, 1};

    }

    public void act() 
    {
        
        
        lifespan = stats [0]; //max limit an organism can be in the world 
        speed = stats [1]; //rate of movement of the organism 
        range = stats [2]; // Int storing detection range
        num_split = stats [3]; //number of offspring an organism can produce
        split_energy = stats [4]; //set energy needed to perform a split
        mutation_rate = stats [5]; // An int which determines how many random gene stats can be changed
        consumptionRate = stats [6];
        
        if (world == null) {

            world = (MyWorld) getWorld (); // Store the reference to the current world

        }
        
        Carnivore host2 = (Carnivore) getOneIntersectingObject(Carnivore.class);
        Herbivore host1 = (Herbivore) getOneIntersectingObject(Herbivore.class);
        
        if(host1 != null && host1.energy >= 0){
            attached = true;
            setLocation(host1.getX(), host1.getY());
            host1.energy-=0.001;
            eat();
        }
         else{
            attached = false;
        }
         
        if(host2 != null && host2.energy >= 0 ){
            attached = true;
            setLocation(host2.getX(), host2.getY());
            host2.energy-=0.001;
            eat();
        }
        else{
            attached = false;
        }
       
        //age ();     // Increases age by 1 every time the act method executes
        grow ();    // Grow depending on energy they have
        shift();    // Randomly moves around
        split();  // Reproduces when reaches the certain stage
    }
    
 

    public void grow() {
        size = (int) (0.02 * energy + 5); 
        GreenfootImage image = new GreenfootImage(size, size); 
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, size+10, size+10);
        image.setColor(Color.WHITE); 
        image.fillRect(0, 0, size, size);
        this.setImage(image);
    }


    public void eat(){
        if(energy < split_energy){
        energy += consumptionRate;
    }
    }

    public void split(){
        angle_split = 360 / num_split;
  
        if (energy >= split_energy && age < lifespan && attached == false) {
            
            for (int i = 0; i < num_split; i ++) {
                Parasite temp_Splitted = new Parasite();
                Mutation.mutate(temp_Splitted);
                world.addObject(temp_Splitted, getX(), getY());
                temp_Splitted.turn ((180 - angle_split * i) + (Greenfoot.getRandomNumber(angle_split) - angle_split / 2));
                temp_Splitted.move( getRandomNumber(10, 100) );
            }
            die();
        }
    }
    
    public void age() {
        //age += 1; 
        if (age >= lifespan && energy != 0) { 
            die();               
        }
    }
    
    public void shift(){
        move(1);
        turn(40);
    }
    
      public void die() {
        lifeforms.remove(this);
        world.removeObject(this);
    }
}
