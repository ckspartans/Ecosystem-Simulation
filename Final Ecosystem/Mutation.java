import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.lang.*;

/** Mutation
 * Mutation class is a static class which handles the mutations of all the organisms.
 *  * The code picks a random stat to mutate multiple times (based off of the organism's mutation_rate int), and then sets it to a random number with the range having the origanl state 
 * @author Hawke, Mudaser, Parmeet, Shusil, Tim  
 * @version 17/10/2017
 */
public class Mutation extends Actor
{
    /**
     * Act - do whatever the Mutation wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void act() 
    {

        // Add your action code here.

    }    

    /* PUT ON HOLD

    public static void split (AbstOrganism organism) {

    int angle_split = 360 / organism.num_split;

    // Check to see if there if enough energy (size?) to split

    if (organism.energy >= organism.split_energy && organism.age < organism.lifespan) {

    // If yes, then call the constructor for two new ones and kill the parent

    organism.energy -= organism.split_energy; // Subtract the used up energy needed to split.

    // A for loop running once for each num_Split (child to be made)
    for (int i = 0; i < organism.num_split; i ++) {
    //ClassDemo demo = new ClassDemo();
    //Class <?> organism_class = organism.getClass();
    //Constructor organism_construct = organism_class.getDeclaredConstructor(organism_class);
    //Algae temp_splitted = new Algae();
    //organism.getConstructors(); //.cast(temp_splitted);
    //Algae temp_splitted = AbstOrganism.class.asSubclass(AbstOrganism.class).getConstructor(ARRAY.class).newInstance(organism.stats);
    //temp_splitted = organism.getDeclaredConstructor(organism_class.class);

    //organism_Class temp_splitted = new organism_class ();

    //Algae temp_Splitted = new Algae ();
    mutate (temp_splitted);
    organism.world.addObject(temp_splitted, organism.getX(), organism.getY());      
    temp_splitted.turn ((180 - angle_split * i) + (Greenfoot.getRandomNumber(angle_split) - angle_split / 2));
    temp_splitted.move(20);
    }

    organism.die();

    }
    }
     */
    public static void mutate (AbstOrganism organism) {
        int [] stats = organism.stats;
        for (int i = 0; i < stats [5]; i ++) { // Run for as many times as the mutation_rate stat's value (-1)

            int mutating = (int) Greenfoot.getRandomNumber (stats.length); // Chooses which stat to mutate randomly
            // Please see AbstOrganism's getRandomNumber method to better understand the code below. It takes a start and ending value of the range of the random number you want to be generated and calls on Greenfoot's method in a slightly different way. 
            if (organism instanceof Algae) { // If an algae, mutate within this bounds, and so on for other class types.

                if (mutating == 0) { // lifespan

                    if (stats [mutating] - 30 >= 1) {

                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 30, stats [mutating] + 30); // This line sets the lifespan (index 0 of stats) to a random number between the unmutated stats minus 30, and the stat plus 30, the rest of the lines are similar to this, except modified different indexes and/or by different amounts

                    } else {

                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 30);

                    }

                } else if (mutating == 1) { // speed
                    // ALGAE has no speed

                } else if (mutating == 2) { // range

                    // ALGAE does not use range

                } else if (mutating == 3) { // num_split

                    if (stats [mutating] - 2 >= 1) {

                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 2, stats [mutating] + 2); 

                    } else {

                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 2);

                    }

                } else if (mutating == 4) { // split_energy
                    if (stats [mutating] - 20 >= 1) {
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 20, stats [mutating] + 20); 
                    } else {
                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 20);
                    }

                } else if (mutating == 5) { // mutation_rate

                    if (stats [mutating] - 1 >= 1 && stats [mutating] + 1 <= stats.length) { // If the least and greatest possible values of this mutation will not be less than the minimum (1) or more than the maximum (stats.length)

                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 1, stats [mutating] + 1); 

                    } else if (stats [mutating] + 1 <= stats.length) { // Checks if this mutation will "never" be more than the maximum

                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 1); // This means it would've been possible to have the value be less than the minimum, so instead of a random minimum, the maximum is used. Similar to the next else statement, but for the maximum

                    } else {

                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 1, stats.length);

                    }

                }     
            } else if (organism instanceof Herbivore) {
                if (mutating == 0) { // lifespan
                    if (stats [mutating] - 30 >= 1) {
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 30, stats [mutating] + 30); 
                    } else {
                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 30);
                    }

                } else if (mutating == 1) { // speed

                    if (stats [mutating] - 1 >= 0) {
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 1, stats [mutating] + 1); 
                    } else {
                        stats [mutating] = (int) organism.getRandomNumber(0 , stats [mutating] + 1);
                    }

                } else if (mutating == 2) { // range

                    if (stats [mutating] - 20 >= 0) {

                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 20, stats [mutating] + 20); 

                    } else {

                        stats [mutating] = (int) organism.getRandomNumber(0 , stats [mutating] + 20);

                    }

                } else if (mutating == 3) { // num_split
                    if (stats [mutating] - 2 >= 1) {
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 2, stats [mutating] + 2); 
                    } else {
                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 2);
                    }

                } else if (mutating == 4) { // split_energy

                    if (stats [mutating] - 20 >= 1) {

                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 20, stats [mutating] + 20); 

                    } else {

                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 20);

                    }

                } else if (mutating == 5) { // mutation_rate
                    if (stats [mutating] - 1 >= 1 && stats [mutating] + 1 <= stats.length) { // If the least and greatest possible values of this mutation will not be less than the minimum (1) or more than the maximum (stats.length)
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 1, stats [mutating] + 1); 
                    } else if (stats [mutating] + 1 <= stats.length) { // Checks if this mutation will "never" be more than the maximum
                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 1); // This means it would've been possible to have the value be less than the minimum, so instead of a random minimum, the maximum is used. Similar to the next else statement, but for the maximum
                    } else {
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 1, stats.length);
                    }

                }

            } else if (organism instanceof Carnivore) {

                if (mutating == 0) { // lifespan

                    if (stats [mutating] - 30 >= 1) {
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 30, stats [mutating] + 30); 
                    } else {
                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 30);
                    }

                } else if (mutating == 1) { // speed

                    if (stats [mutating] - 2 >= 0) {

                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 2, stats [mutating] + 2); 

                    } else {

                        stats [mutating] = (int) organism.getRandomNumber(0 , stats [mutating] + 2);

                    }

                } else if (mutating == 2) { // range
                    if (stats [mutating] - 20 >= 0) {
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 20, stats [mutating] + 20); 
                    } else {
                        stats [mutating] = (int) organism.getRandomNumber(0 , stats [mutating] + 20);
                    }

                } else if (mutating == 3) { // num_split

                    if (stats [mutating] - 2 >= 1) {

                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 2, stats [mutating] + 2); 

                    } else {

                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 2);

                    }

                } else if (mutating == 4) { // split_energy

                    if (stats [mutating] - 20 >= 1) {

                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 20, stats [mutating] + 20); 

                    } else {

                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 20);

                    }

                } else if (mutating == 5) { // mutation_rate
                    if (stats [mutating] - 1 >= 1 && stats [mutating] + 1 <= stats.length) { // If the least and greatest possible values of this mutation will not be less than the minimum (1) or more than the maximum (stats.length)
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 1, stats [mutating] + 1); 
                    } else if (stats [mutating] + 1 <= stats.length) { // Checks if this mutation will "never" be more than the maximum
                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 1); // This means it would've been possible to have the value be less than the minimum, so instead of a random minimum, the maximum is used. Similar to the next else statement, but for the maximum
                    } else {
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 1, stats.length);
                    }

                }     

            }else if (organism instanceof Scavenger) {

                if (mutating == 0) { // lifespan

                    if (stats [mutating] - 30 >= 1) {
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 30, stats [mutating] + 30); 
                    } else {
                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 30);
                    }

                } else if (mutating == 1) { // speed

                    if (stats [mutating] - 2 >= 0) {
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 2, stats [mutating] + 2); 
                    } else {
                        stats [mutating] = (int) organism.getRandomNumber(0 , stats [mutating] + 2);
                    }

                } else if (mutating == 2) { // range

                    if (stats [mutating] - 20 >= 0) {
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 20, stats [mutating] + 20); 
                    } else {
                        stats [mutating] = (int) organism.getRandomNumber(0 , stats [mutating] + 20);
                    }

                } else if (mutating == 3) { // num_split

                    if (stats [mutating] - 2 >= 1) {
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 2, stats [mutating] + 2); 
                    } else {
                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 2);
                    }

                } else if (mutating == 4) { // split_energy

                    if (stats [mutating] - 20 >= 1) {

                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 20, stats [mutating] + 20); 

                    } else {

                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 20);

                    }

                } else if (mutating == 5) { // mutation_rate
                    if (stats [mutating] - 1 >= 1 && stats [mutating] + 1 <= stats.length) { // If the least and greatest possible values of this mutation will not be less than the minimum (1) or more than the maximum (stats.length)
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 1, stats [mutating] + 1); 
                    } else if (stats [mutating] + 1 <= stats.length) { // Checks if this mutation will "never" be more than the maximum
                        stats [mutating] = (int) organism.getRandomNumber(1 , stats [mutating] + 1); // This means it would've been possible to have the value be less than the minimum, so instead of a random minimum, the maximum is used. Similar to the next else statement, but for the maximum
                    } else {
                        stats [mutating] = (int) organism.getRandomNumber(stats [mutating] - 1, stats.length);
                    }

                }     

            }

        }

    }

}