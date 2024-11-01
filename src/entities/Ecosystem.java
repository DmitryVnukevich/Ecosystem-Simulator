package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Ecosystem {
    private int sunlightRecovery;
    private int waterRecovery;
    private List<Organism> organisms;
    private List<Resource> resources;
    private Map<String, Integer> organismCounters = new HashMap<>();
    private static final Logger logger = Logger.getLogger(Ecosystem.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("ecosystem.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Ecosystem() {
        this.organisms = new ArrayList<>();
        this.resources = new ArrayList<>();
    }

    public static Logger getLogger() {
        return logger;
    }

    public List<Organism> getOrganisms() {
        return organisms;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void addResources(int initialWater, int initialSunlight, int waterRecovery, int sunlightRecovery) {
        this.resources.add(new Resource("Вода", initialWater));
        this.resources.add(new Resource("Солнечный свет", initialSunlight));
        this.waterRecovery = waterRecovery;
        this.sunlightRecovery = sunlightRecovery;
    }

    public void addPlant(String name, int waterConsumption, int sunlightConsumption) {
        Plant plant = new Plant(name, waterConsumption, sunlightConsumption);
        organisms.add(addOrganism(plant));
        logger.info("Добавлено растение: " + plant.getName() + ", потребление воды: " + waterConsumption + ", солнечного света: " + sunlightConsumption);
    }

    public void addAnimal(String name, boolean isHerbivore) {
        Animal animal = new Animal(name, isHerbivore ? "Травоядное" : "Хищник");
        organisms.add(addOrganism(animal));
        logger.info("Добавлено животное: " + animal.getName() + ", тип: " + (isHerbivore ? "Травоядное" : "Хищник"));
    }

    public Organism addOrganism(Organism organism) {
        String baseName = organism.getName().split(" ")[0];
        int count = organismCounters.getOrDefault(baseName, 0) + 1;
        organismCounters.put(baseName, count);

        organism.setName(baseName + " " + count);
        return organism;
    }

    public void showConditions() {
        System.out.println("Организмы в экосистеме:");
        for (Organism organism : organisms) {
            System.out.println(organism);
        }
        System.out.println("Ресурсы в экосистеме:");
        for (Resource resource : resources) {
            System.out.println(resource);
        }
    }

    public boolean isReadyForSimulation() {
        return !resources.isEmpty() && !organisms.isEmpty();
    }

    public void run(int numCycles) {
        for (int i = 1; i <= numCycles; i++) {
            System.out.printf("""
                    \n**************************************************
                    Начинается новый цикл (%d) обновления экосистемы...
                    **************************************************\n""", i);
            logger.info("Начинается новый цикл (" + i + ") обновления экосистемы...");
            updateCycle();
        }
    }

    public void updateCycle() {
        List<Organism> currentOrganisms = new ArrayList<>(organisms);
        for (Organism organism : currentOrganisms) {
            organism.consume(this);
        }

        replenishResources();
        reproduceOrganism();

        displayStatus();
        logEcosystemState();
    }

    private void replenishResources() {
        System.out.println("Ресурсы восстанавливаются...");
        for (Resource resource : resources) {
            if (resource.getResourceName().equals("Вода")) {
                resource.increaseQuantity(waterRecovery);
            } else if (resource.getResourceName().equals("Солнечный свет")) {
                resource.increaseQuantity(sunlightRecovery);
            }
        }
    }

    private void reproduceOrganism() {
        List<Organism> organismsSnapshot = new ArrayList<>(organisms);

        for (Organism organism : organismsSnapshot) {
            Organism offspring = organism.reproduce();
            if (offspring != null) {
                addOrganism(offspring);
                System.out.println(offspring.getName() + " был добавлен в экосистему.");
            }
        }
    }

    public void displayStatus() {
        System.out.println("Состояние экосистемы: ");
        for (Organism organism : organisms) {
            System.out.println(organism);
        }
        for (Resource resource : resources) {
            System.out.println(resource);
        }

    }

    public void logEcosystemState() {
        logger.info("Состояние экосистемы: ");
        for (Organism organism : organisms) {
            logger.info("Организм: " + organism.getName() + ", тип: " + organism.getClass().getSimpleName());
        }
        for (Resource resource : resources) {
            logger.info("Ресурсы: " + resource.getResourceName() + " = " + resource.getQuantity() + ", " + resource.getResourceName() + " = " + resource.getQuantity());
        }
    }
}
