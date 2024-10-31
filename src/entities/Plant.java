package entities;

import java.util.*;

public class Plant extends Organism {
    private static Map<String, Integer> speciesCounters = new HashMap<>();
    public Plant(String name) {
        super(name);
    }

    @Override
    public void consume(Ecosystem ecosystem) {
        Resource waterResource = findResource("Вода", ecosystem);
        if (waterResource != null && waterResource.getQuantity() >= 5) {
            waterResource.decreaseQuantity(5);
            System.out.println(name + " потребил 5 единиц воды.");
        } else {
            System.out.println(name + " не хватает воды.");
        }

        Resource sunlightResource = findResource("Солнечный свет", ecosystem);
        if (sunlightResource != null && sunlightResource.getQuantity() >= 3) {
            sunlightResource.decreaseQuantity(3);
            System.out.println(name + " потребил 3 единицы солнечного света.");
        } else {
            System.out.println(name + " не хватает солнечного света.");
        }
    }

    private Resource findResource(String resourceName, Ecosystem ecosystem) {
        for (Resource resource : ecosystem.getResources()) {
            if (resource.getResourceName().equals(resourceName)) {
                return resource;
            }
        }
        return null;
    }

    @Override
    public Organism reproduce() {
        String baseName = name.split(" ")[0];

        int count = speciesCounters.getOrDefault(baseName, 0) + 1;
        speciesCounters.put(baseName, count);

        String newName = baseName + " " + count;
        System.out.println(name + " размножается и создаёт " + newName);

        return new Plant(newName);
    }

    @Override
    public String toString() {
        return "Растение: " + name;
    }
}
