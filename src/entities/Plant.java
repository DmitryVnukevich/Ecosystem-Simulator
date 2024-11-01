package entities;

import java.util.logging.Logger;

public class Plant extends Organism {
    private int waterConsumption;
    private int sunlightConsumption;

    public Plant(String name, int waterConsumption, int sunlightConsumption) {
        super(name);
        this.waterConsumption = waterConsumption;
        this.sunlightConsumption = sunlightConsumption;
    }

    @Override
    public void consume(Ecosystem ecosystem) {
        Logger logger = Ecosystem.getLogger();
        Resource waterResource = findResource("Вода", ecosystem);
        if (waterResource != null && waterResource.getQuantity() >= waterConsumption) {
            waterResource.decreaseQuantity(waterConsumption);
            System.out.printf(name + " потребил %d единиц воды.", waterConsumption);
            logger.info(name + " потребил " + waterConsumption + " единиц воды.");
        } else {
            System.out.println(name + " не хватает воды.");
            logger.info(name + " не хватает воды.");
        }

        Resource sunlightResource = findResource("Солнечный свет", ecosystem);
        if (sunlightResource != null && sunlightResource.getQuantity() >= sunlightConsumption) {
            sunlightResource.decreaseQuantity(sunlightConsumption);
            System.out.printf("\n" + name + " потребил %d единицы солнечного света.", sunlightConsumption);
            logger.info(name + " потребил " + sunlightConsumption + " единицы солнечного света.");
        } else {
            System.out.println(name + " не хватает солнечного света.");
            logger.info(name + " не хватает солнечного света.");
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
        Plant offspring = new Plant(name, waterConsumption, sunlightConsumption);
        System.out.println(name + " размножается.");
        return offspring;
    }

    @Override
    public String toString() {
        return "Растение: " + name;
    }
}
