package entities;

import java.util.*;

public class Animal extends Organism {
    private String species;
    private String targetDiet;
    private List<String> prey;
    private static Map<String, Integer> speciesCounters = new HashMap<>();
    private int hungerLevel = 3;

    public Animal(String name, String species ,String targetDiet) {
        super(name);
        this.targetDiet = targetDiet;
        this.species = species;
        this.prey = new ArrayList<>();
        if (targetDiet.equals("Хищник")) {
            prey.add("Animal");
        } else if (targetDiet.equals("Травоядный")) {
            prey.add("Plant");
        }
    }

    private String getSpecies(){
        return species;
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

    private Organism findFood(Ecosystem ecosystem) {
        Iterator<Organism> iterator = ecosystem.getOrganisms().iterator();
        while (iterator.hasNext()) {
            Organism organism = iterator.next();
            if (organism != this &&
                    ((prey.contains("Plant") && organism instanceof Plant) ||
                            (prey.contains("Animal") && organism instanceof Animal &&
                                    !((Animal) organism).getSpecies().equals(this.species)))) {
                iterator.remove();
                return organism;
            }
        }
        return null;
    }

    public Organism reproduce() {
        if (hungerLevel > 2) {
            String baseName = name.split(" ")[0];

            int count = speciesCounters.getOrDefault(baseName, 0) + 1;
            speciesCounters.put(baseName, count);

            String newName = baseName + " " + count;
            System.out.println(name + " размножается и создаёт " + newName);

            return new Animal(newName, species, targetDiet);
        }
        return null;
    }

    public String toString() {
        return "Животное: " + name + ", Диета: " + targetDiet;
    }
}
