import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/** Mutation
 * Mutation class is a static class which handles the mutations of all the organisms.
 * 
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
    
    /** PUT ON HOLD FOR THIS FIRST ITERATION!
     
    public static void split (AbstOrganism organism) {
    
        int angle_split = 360 / organism.num_split;

        // Check to see if there if enough energy (size?) to split

        if (organism.energy >= organism.split_energy && organism.age < organism.lifespan) {

            // If yes, then call the constructor for two new ones and kill the parent

            organism.energy -= organism.split_energy; // Subtract the used up energy needed to split.



            // A for loop running once for each num_Split (child to be made)

            for (int i = 0; i < organism.num_split; i ++) {
                
                Class <?> organism_class = organism.getClass();
                
                
                
                organism_Class temp_splitted = new Algae ();
                
                
                //organism_class temp_Splitted = new organism_class ();
                
                mutate (temp_splitted);

                organism.world.addObject(temp_splitted, organism.getX(), organism.getY());      

                temp_splitted.turn ((180 - angle_split * i) + (Greenfoot.getRandomNumber(angle_split) - angle_split / 2));

                temp_splitted.move(20);

            }



            organism.die();



        }
        
    }
    
    */ //PUT ON HOLD FOR THIS FIRST ITERATION!
    
    public static void mutate (AbstOrganism organism) {
        
        int [] stats = organism.stats;
    
        for (int i = 0; i < stats [5]; i ++) { // Run for as many times as the mutation_rate stat's value (-1)



            int mutating = (int) Greenfoot.getRandomNumber (stats.length); // Chooses which stat to mutate randomly
            
            // Please see AbstOrganism's getRandomNumber method to better understand the code below. It takes a start and ending value of the range of the random number you want to be generated and calls on Greenfoot's method in a slightly different way. 
            if (organism instanceof Algae) { // If an algae, mutate within this bounds, and so on for other class types.
    
                if (mutating == 0) { // lifespan
    
    
    
                    stats [mutating] = (int) organism.getRandomNumber(700, 1000); // This line sets the lifespan (index 0 of stats) to a random number between 700 and 1000, the rest of the lines are similar to this, except modified different indexes and/or by different amounts
    
    
    
                } else if (mutating == 1) { // speed
    
    
    
                    stats [mutating] = (int) organism.getRandomNumber(1, 3); // Sets the speed (index 1 of stats) to a random number between 1 and 3
    
    
    
                } else if (mutating == 2) { // range
    
    
    
                    stats [mutating] = (int) organism.getRandomNumber(1, 5); // Sets the range (index 2 of stats) to a random number between 1 and 5
    
    
    
                } else if (mutating == 3) { // num_split
    
    
    
                    stats [mutating] = (int) organism.getRandomNumber(2, 4); // Sets the num_split (index 3 of stats) to a random number between 2 and 4
    
    
    
                } else if (mutating == 4) { // split_energy
    
    
    
                    stats [mutating] = (int) organism.getRandomNumber(800, 1500); // Sets the split_energy (index 4 of stats) to a random number between 800 and 1500
    
    
    
                } else if ( mutating == 5) { // mutation_rate
    
    
    
                    stats [mutating] = (int) organism.getRandomNumber(2, 4); // Sets the mutation_rate (index 5 of stats) to a random number between 2 and 4
    
    
    
                }     
                
            } else if (organism instanceof Herbivore) {
            
                if (mutating == 0) { // lifespan



                    stats [mutating] = (int) organism.getRandomNumber(500, 1000);



                } else if (mutating == 1) { // speed
    
    
    
                    stats [mutating] = (int) organism.getRandomNumber(2, 8);
    
    
    
                } else if (mutating == 2) { // range
    
    
    
                    stats [mutating] = (int) organism.getRandomNumber(40, 300);
    
    
    
                } else if (mutating == 3) { // num_split
    
    
    
                    stats [mutating] = (int) organism.getRandomNumber(2, 2);
    
    
    
                } else if (mutating == 4) { // split_energy
    
    
    
                    stats [mutating] = (int) organism.getRandomNumber(600, 800);
    
    
    
                } else if (mutating == 5) { // mutation_rate
    
    
    
                    stats [mutating] = (int) organism.getRandomNumber(2, 6);
    
    
    
                }

            
            } else if (organism instanceof Carnivore) {
            
                if (mutating == 0) { // lifespan



                stats [mutating] = (int) organism.getRandomNumber(1600, 2500);



            } else if (mutating == 1) { // speed



                stats [mutating] = (int) organism.getRandomNumber(2, 10);



            } else if (mutating == 2) { // range



                stats [mutating] = (int) organism.getRandomNumber(120, 300);



            } else if (mutating == 3) { // num_split



                stats [mutating] = (int) organism.getRandomNumber(2, 2);



            } else if (mutating == 4) { // split_energy



                stats [mutating] = (int) organism.getRandomNumber(600, 800);



            } else if (mutating == 5) { // mutation_rate



                stats [mutating] = (int) organism.getRandomNumber(2, 6);



            }     
            
            }else if (organism instanceof Scavenger) {
            
                if (mutating == 0) { // lifespan



                stats [mutating] = (int) organism.getRandomNumber(1600, 2500);



            } else if (mutating == 1) { // speed



                stats [mutating] = (int) organism.getRandomNumber(5, 10);



            } else if (mutating == 2) { // range



                stats [mutating] = (int) organism.getRandomNumber(120, 300);



            } else if (mutating == 3) { // num_split



                stats [mutating] = (int) organism.getRandomNumber(2, 2);



            } else if (mutating == 4) { // split_energy



                stats [mutating] = (int) organism.getRandomNumber(600, 800);



            } else if (mutating == 5) { // mutation_rate



                stats [mutating] = (int) organism.getRandomNumber(2, 6);



            }     
            
            }

        }
    
    }
    
}