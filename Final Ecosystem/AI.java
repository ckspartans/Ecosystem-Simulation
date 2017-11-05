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
    
      public static void doHerbivore(Herbivore he) {    // To decide whether to go for food or not(Checks how far away is predator)
        List<Carnivore> predators = he.givesOffListPredator();  // List of predators
        List<Algae> preys = he.givesOffList();                  // List of Preys
        if (!predators.isEmpty()) {                             // When they are not empty 
            if (!preys.isEmpty()) {
                List<Actor> actors = new ArrayList<Actor>();
                actors.addAll(preys);                           // Adds up all preys in that array list
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

    public static void checkPrey(AbstOrganism attacker){
        AbstOrganism target = null;         // This is the variable that stores target 

        if(!attacker.givesOffList().isEmpty()){   // Checks the organisms if any within that radius

            target = (AbstOrganism) getTheCloset(attacker, attacker.givesOffList() ); // always set the first one in the list as the target

            // target = (AbstOrganism) getTheCloset(attacker, attacker.givesOffList() );
            if(target!=null && attacker.hungry == true){ // If there are preys in range

                if(Defaults.calcDistance(attacker, target)<10){ // Attack only gets called when their distance is less than 10

                    attack(attacker, target);     // Calls the function

                }

                hunt(attacker, target); //Kills


                // This is basically for user to see the predator's sight
                activateBoundary(attacker, attacker.range);     // If the predator sees prey, then its border gets activated

            }else{

                //   attacker.world.removeObject(attacker.mySight);      // Removes from world

            }

        }
    }
    
    public static void checkOmnivorePrey(Omnivore attacker){
        
        AbstOrganism target = null;         // This is the variable that stores target
        Algae targetB = null; // This is the closest algae
        Herbivore targetC = null; // This is the closest herbivore
        
        if (attacker.hungry == true) {
            if(!attacker.givesOffListA().isEmpty() && !attacker.givesOffListH().isEmpty()){   // Checks the organisms if any within that radius
    
                targetB = getTheClosestAlgae(attacker, attacker.givesOffListA()); // always set the first one in the list as the target
                targetC = getTheClosestHerbivore(attacker, attacker.givesOffListH());
                if (targetB != null && targetC != null) {
                    
                    target = (AbstOrganism) closestOfTwo(attacker, targetB, targetC);
                    
                }
                // target = (AbstOrganism) getTheCloset(attacker, attacker.givesOffList() );
                if(target!=null){ // If there are preys in range
    
                    if(Defaults.calcDistance(attacker, target)<10){ // Attack only gets called when their distance is less than 10
    
                        attack(attacker, target);     // Calls the function
    
                    }
    
                    hunt(attacker, target); //Kills
    
    
                    // This is basically for user to see the predator's sight
                    activateBoundary(attacker, attacker.range);     // If the predator sees prey, then its border gets activated
    
                }else{
    
                    //   attacker.world.removeObject(attacker.mySight);      // Removes from world
    
                }
    
            } else if (!attacker.givesOffListA().isEmpty()) { // If some algae are found in range
                
                target = (AbstOrganism) getTheClosestAlgae(attacker, attacker.givesOffListA()); // always set the first one in the list as the target
                
                if(target!=null){ // If there are preys in range
    
                    if(Defaults.calcDistance(attacker, target)<10){ // Attack only gets called when their distance is less than 10
    
                        attack(attacker, target);     // Calls the function
    
                    }
    
                    hunt(attacker, target); //Kills
    
    
                    // This is basically for user to see the predator's sight
                    activateBoundary(attacker, attacker.range);     // If the predator sees prey, then its border gets activated
    
                }else{
        
                }
                
            } else if (!attacker.givesOffListH().isEmpty()) { // If some herbivores can be found in range
                
                target = (AbstOrganism) getTheClosestHerbivore(attacker, attacker.givesOffListH()); // always set the first one in the list as the target
                
                if(target!=null){ // If there are preys in range
    
                    if(Defaults.calcDistance(attacker, target)<10){ // Attack only gets called when their distance is less than 10
    
                        attack(attacker, target);     // Calls the function
    
                    }
    
                    hunt(attacker, target); //Kills
    
    
                    // This is basically for user to see the predator's sight
                    activateBoundary(attacker, attacker.range);     // If the predator sees prey, then its border gets activated
    
                }else{
        
                }
                
            }
        }   
    }   

    public static void activateBoundary(AbstOrganism attacker, int range){
        if(attacker != null ){          // If Organism that the sight class is attaching is not null
            attacker.world.removeObject(attacker.mySight);      // Removes from world
            attacker.mySight = new Sight(attacker, range);      // Reference to the sight actor class  
            attacker.world.addObject(attacker.mySight, attacker.getX(), attacker.getY());
        }

    }

    public static void deleteBoundary(AbstOrganism attacker ,Sight mySight){    // This deletes the mySight actor
        // Not sure if I need this 
        attacker.world.removeObject(mySight);      // Removes from world

    }

    public static void checkPredator(AbstOrganism attacker){ // Predator checking
        AbstOrganism runAway = null;        // Sets it to null, this saves the list of predators around

        if(attacker.givesOffListPredator().isEmpty() || attacker ==null){ //If no predator

            return; // Gets out of the function

        }

        else {

            runAway = (AbstOrganism) attacker.givesOffListPredator().get(0); // always set the first one in the list as the target

            if(runAway!=null){ // If no predators

                flee(attacker, runAway); // Runsaway

            }
        }

    }

    public static void flee(AbstOrganism attacker, AbstOrganism runAway){ // This method lets preys to run away whenever they see Predators

        attacker.turnTowards(runAway.getX(),runAway.getY());         // Turns away the predator       

        attacker.turn(180); // Turns 180
        attacker.move(attacker.speed);
         //attacker.move(2);
    }

    public static void hunt(AbstOrganism attacker, AbstOrganism target){ // Hunting
        if(target.energy > 0){
            attacker.turnTowards(target.getX(), target.getY());         // Turns toward the food

            target = null;

            attacker.move(attacker.speed);          //Moves with the speed assigned before
          //  attacker.move(5);
        }

    }

    public static void attack(AbstOrganism attacker, AbstOrganism target){      // This is meant for making them fight each other
        int addEnergy=0;           // Energy that will be added based on whos fighting and against who
        //attacker.world.showText("Eneg : "+ addEnergy, 100, 450);        // Just for testing
        if(attacker.trophicLvl == 5 || attacker.trophicLvl == 3 || attacker.trophicLvl == 2){     // If it is Omnivore
            addEnergy = (attacker.attack - target.defense); // It basically gives you the positive value
            attacker.fights(addEnergy, attacker); // then that positive value(addEnergy) will be added to organism's energy

        }

        if(target.trophicLvl == 2 ){       // If it is Herbivore

            
            addEnergy = (target.defense - attacker.attack); // It gives you negative 
            target.fights(addEnergy, attacker); // Negative value when added to energy subtracts the orgs. energy

        }
        if(target.trophicLvl == 1){         // If it is Algae
            target.move(10);     // Just to make it look like its defending 
            target.turn(40);       // Just to make it look like its defending 
            addEnergy = (target.defense - attacker.attack); // It gives you negative 
            target.fights(addEnergy, attacker);    // Negative value when added to energy subtracts the orgs. energy

        }
        // When Omnivore attacks Carnivore, then we will do (Att.this - Att.other)
        // This will add up to their energy 
        // When energy goes negative, they die
    }

    public static Actor getTheCloset(Actor actor, List<Actor> actors) {     // Gets the closest one
        Actor closestActor = null;      // Its null at first 
        int distance = 100;             // Minimum distance that will be compared is 100
        for (Actor a : actors) {        // Goes through every actors
            int d = (int) Defaults.calcDistance((AbstOrganism)actor, (AbstOrganism)a);  // It calculates the distance between predators and preys
            if (d <= distance) {        // If their distance is shorter than 100 then it will go for it
                closestActor = a;       // Sets the target to that 'a' actor
                distance = d;           // Sets the value of distance as a d
            }

        }
        return closestActor;            // returns the closest actor
    }
    
    public static Algae getTheClosestAlgae (Omnivore actor, List<Algae> actors) {  // // Gets the closest one
        Algae closestActor = null;       // Its null at first 
        int distance = 100; // Minimum distance that will be compared is 100
        for (Algae a : actors) {// Goes through every actors
            int d = (int) Defaults.calcDistance((AbstOrganism)actor, (AbstOrganism)a); // It calculates the distance between predators and preys
            if (d <= distance) {// If their distance is shorter than 100 then it will go for it
                closestActor = a; // Sets the target to that 'a' actor
                distance = d;// Sets the value of distance as a d
            }

        }
        return closestActor; // returns the closest actor
    }
    
    public static Herbivore getTheClosestHerbivore (Omnivore actor, List<Herbivore> actors) { // Gets the closest one
        Herbivore closestActor = null;
       int distance = 100; // Minimum distance that will be compared is 100
        for (Herbivore a : actors) {// Goes through every actors
            int d = (int) Defaults.calcDistance((AbstOrganism)actor, (AbstOrganism)a); // It calculates the distance between predators and preys
            if (d <= distance) {// If their distance is shorter than 100 then it will go for it
                closestActor = a; // Sets the target to that 'a' actor
                distance = d;// Sets the value of distance as a d
            }

        }
        return closestActor; // returns the closest actor
    }
    
    public static Actor closestOfTwo (Actor actorA, Actor actorB, Actor actorC) { // Takes in the central actor (actorA), actorB and actorC, checks which of B or C is closer and returns that one
        Actor closestActor = null;      // It is set null at first
        int distance = 100;             // Minimum distance is 100
        int distAB = (int) Defaults.calcDistance((AbstOrganism)actorA, (AbstOrganism)actorB);   // Calculates distance between Target A and B
        int distAC = (int) Defaults.calcDistance((AbstOrganism)actorA, (AbstOrganism)actorC);   // Calculates distance between Target B and C
        if (distAB < distAC) {          // Is distance between AB short than AC
            closestActor = actorB;      // If it is then closest one is target B
            distance = distAB;
        } else {
            
            closestActor = actorC;      // Otherwise closest one is target B
            distance = distAC;
                
        }

        
        return closestActor;            // Returns closest
    }
}

