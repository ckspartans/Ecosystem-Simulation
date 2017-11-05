import java.util.*;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class handles the AI abilities that the organisms have
 * 
 * @author Hawke, Mudaser, Parmeet, Shusil, Tim  
 * @version 17/10/2017
 */
public class AI  
{
    
      public static void doHerbivore(Herbivore he) {////
        List<Carnivore> predators = he.givesOffListPredator();
        List<Algae> preys = he.givesOffList();
        if (!predators.isEmpty()) {
            if (!preys.isEmpty()) {
                List<Actor> actors = new ArrayList<Actor>();
                actors.addAll(preys);
                Algae prey = (Algae) getTheCloset(he, actors);
                actors.clear();
                actors.addAll(predators);
                Carnivore predator = (Carnivore) getTheCloset(prey, actors);
                if (Defaults.calcDistance(he, prey) < Defaults.calcDistance(prey, predator)) {
                    checkPrey(he);
                } else {
                    checkPredator(he);
                }
            } else {
                checkPredator(he);
            }
        } else if (!preys.isEmpty()) {
            checkPrey(he);
        } else {
            he.shift();
        }
    }

    public static void checkPrey(AbstOrganism Org2){
        AbstOrganism target = null;         // This is the variable that stores target 

        if(!Org2.givesOffList().isEmpty()){   // Checks the organisms if any within that radius

            target = (AbstOrganism) getTheCloset(Org2, Org2.givesOffList() ); // always set the first one in the list as the target

            // target = (AbstOrganism) getTheCloset(Org2, Org2.givesOffList() );
            if(target!=null && Org2.hungry == true){ // If there are preys in range

                if(Defaults.calcDistance(Org2, target)<10){ // Attack only gets called when their distance is less than 10

                    attack(Org2, target);     // Calls the function

                }

                hunt(Org2, target); //Kills

                System.out.println("Range before: " + Org2.range);  // For testing range

                // This is basically for user to see the predator's sight
                activateBoundary(Org2, Org2.range);     // If the predator sees prey, then its border gets activated

            }else{

                //   Org2.world.removeObject(Org2.mySight);      // Removes from world

            }

        }
    }   

    public static void activateBoundary(AbstOrganism Org2, int range){
        if(Org2 != null ){          // If Organism that the sight class is attaching is not null
            Org2.world.removeObject(Org2.mySight);      // Removes from world
            Org2.mySight = new Sight(Org2, range);      // Reference to the sight actor class  
            Org2.world.addObject(Org2.mySight, Org2.getX(), Org2.getY());
        }

    }

    public static void deleteBoundary(AbstOrganism Org2 ,Sight mySight){    // This deletes the mySight actor
        // Not sure if I need this 
        Org2.world.removeObject(mySight);      // Removes from world

    }

    public static void checkPredator(AbstOrganism Org2){ // Predator checking
        AbstOrganism runAway = null;        // Sets it to null, this saves the list of predators around

        if(Org2.givesOffListPredator().isEmpty() || Org2 ==null){ //If no predator

            return; // Gets out of the function

        }

        else {

            runAway = (AbstOrganism) Org2.givesOffListPredator().get(0); // always set the first one in the list as the target

            if(runAway!=null){ // If no predators

                flee(Org2, runAway); // Runsaway

            }
        }

    }

    public static void flee(AbstOrganism Org2, AbstOrganism runAway){ // This method lets preys to run away whenever they see Predators

        Org2.turnTowards(runAway.getX(),runAway.getY());         // Turns away the predator       

        Org2.turn(180); // Turns 180
        Org2.move(Org2.speed);
         Org2.move(2);
    }

    public static void hunt(AbstOrganism Org2, AbstOrganism target){ // Hunting
        if(target.energy > 0){
            Org2.turnTowards(target.getX(), target.getY());         // Turns toward the food

            target = null;

            Org2.move(Org2.speed);          //Moves with the speed assigned before
          //  Org2.move(5);
        }

    }

    public static void attack(AbstOrganism Org2, AbstOrganism target){      // This is meant for making them fight each other
        int addEnergy=0;           // Energy that will be added based on whos fighting and against who
        Org2.world.showText("Eneg : "+ addEnergy, 100, 450);        // Just for testing
        if(Org2.trophicLvl == 3){     // If it is Carnivore
            addEnergy = (Org2.attack - target.defense); // It basically gives you the positive value
            Org2.fights(addEnergy); // then that positive value(addEnergy) will be added to organism's energy

        }
        if(Org2.trophicLvl == 2){       // If it is Herbivore

            addEnergy = (Org2.attack - target.defense);// It basically gives you the positive value
            Org2.fights(addEnergy); // then that positive value(addEnergy) will be added to organism's energy

        }
        if(target.trophicLvl == 2){       // If it is Herbivore

            addEnergy = (target.defense - Org2.attack); // It gives you negative 
            target.fights(addEnergy); // Negative value when added to energy subtracts the orgs. energy

        }
        if(target.trophicLvl == 1){         // If it is Algae
            target.move(10);     // Just to make it look like its defending 
            target.turn(40);       // Just to make it look like its defending 
            addEnergy = (target.defense - Org2.attack); // It gives you negative 
            target.fights(addEnergy);    // Negative value when added to energy subtracts the orgs. energy

        }
        // When Omnivore attacks Carnivore, then we will do (Att.this - Att.other)
        // This will add up to their energy 
        // When energy goes negative, they die
    }

    public static Actor getTheCloset(Actor actor, List<Actor> actors) {////
        Actor closetActor = null;
        int distance = 100;
        for (Actor a : actors) {
            int d = (int) Defaults.calcDistance((AbstOrganism)actor, (AbstOrganism)a);
            if (d <= distance) {
                closetActor = a;
                distance = d;
            }

        }
        return closetActor;
    }
}

