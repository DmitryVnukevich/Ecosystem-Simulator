package entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Animal extends Organism {
    private String targetDiet;
    private List<String> prey;
    private int hungerLevel = 3;

    public Animal(String name, String targetDiet) {
        super(name);
        this.targetDiet = targetDiet;
        this.prey = new ArrayList<>();
        if (targetDiet.equals("Хищник")) {
            prey.add("Animal");
        } else if (targetDiet.equals("Травоядный")) {
            prey.add("Plant");
        }
    }

    @Override
    public void consume(Ecosystem ecosystem) {
        Organism food = findFood(ecosystem);
        if (food != null) {
            hungerLevel = 3;
            System.out.println(name + " съел " + food.getName());
        } else {
            hungerLevel--;
            if (hungerLevel <= 0) {
                System.out.println(name + " умер от голода.");
                ecosystem.getOrganisms().remove(this);
            } else {
                System.out.println(name + " не нашёл пищи и голодает.");
            }
        }
    }

    //сделать так, чтобы хищники не поедали сами себя и не поедали себе подобных
    private Organism findFood(Ecosystem ecosystem) {
        Iterator<Organism> iterator = ecosystem.getOrganisms().iterator();
        while (iterator.hasNext()) {
            Organism organism = iterator.next();
            if ((prey.contains("Plant") && organism instanceof Plant) || (prey.contains("Animal") && organism instanceof Animal)) {
                iterator.remove();
                return organism;
            }
        }
        return null;
    }

    public Organism reproduce() {
        if (hungerLevel > 1) {
            System.out.println(name + " размножается.");
            return new Animal(name + " Jr.", targetDiet);
        }
        return null;
    }

    public String toString() {
        return "Животное: " + name + ", Диета: " + targetDiet;
    }
}
