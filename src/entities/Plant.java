package entities;


public class Plant extends Organism {
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
        Plant offspring = new Plant(name);
        System.out.println(name + " размножается.");
        return offspring;
    }

    @Override
    public String toString() {
        return "Растение: " + name;
    }
}
