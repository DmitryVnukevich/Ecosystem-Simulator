package entities;

import java.util.*;
import java.util.logging.Logger;

public class Animal extends Organism {
    private String targetDiet;
    //private static Logger logger;
    private List<String> prey;
    private int hungerLevel = 3;

    public Animal(String name, String targetDiet) {
        super(name);
        this.targetDiet = targetDiet;
        this.prey = new ArrayList<>();
        if (targetDiet.equals("Хищник")) {
            prey.add("Animal");
        } else if (targetDiet.equals("Травоядное")) {
            prey.add("Plant");
        }
    }

    @Override
    public void consume(Ecosystem ecosystem) {
        Logger logger = Ecosystem.getLogger();

        Organism food = findFood(ecosystem);
        if (food != null) {
            hungerLevel = 3;
            System.out.println(name + " съел " + food.getName());
            logger.info(name + " съел " + food.getName());
        } else {
            hungerLevel--;
            if (hungerLevel <= 0) {
                System.out.println(name + " умер от голода.");
                logger.info(name + " умер от голода.");
                ecosystem.getOrganisms().remove(this);
            } else {
                System.out.println(name + " не нашёл пищи и голодает.");
                logger.info(name + " не нашёл пищи и голодает.");
            }
        }
    }

    private Organism findFood(Ecosystem ecosystem) {
        Iterator<Organism> iterator = ecosystem.getOrganisms().iterator();
        String currentSpecies = this.name.split(" ")[0];

        while (iterator.hasNext()) {
            Organism organism = iterator.next();
            String foodSpecies = organism.getName().split(" ")[0];

            if (!currentSpecies.equals(foodSpecies) &&
                    ((prey.contains("Plant") && organism instanceof Plant) ||
                            (prey.contains("Animal") && organism instanceof Animal))) {
                iterator.remove();
                return organism;
            }
        }
        return null;
    }

    @Override
    public Organism reproduce() {
        Logger logger = Ecosystem.getLogger();
        if (hungerLevel > 2) {
            Animal offspring = new Animal(name, targetDiet);
            System.out.println(name + " размножается.");
            logger.info(name + " не нашёл пищи и голодает.");
            return offspring;
        }
        return null;
    }

    public String toString() {
        return "Животное: " + name + ", Диета: " + targetDiet;
    }
}
