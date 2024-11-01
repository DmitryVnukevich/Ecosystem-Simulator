package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ecosystem {
    private List<Organism> organisms;
    private List<Resource> resources;
    private Map<String, Integer> organismCounters = new HashMap<>();

    public Ecosystem() {
        this.organisms = new ArrayList<>();
        this.resources = new ArrayList<>();
    }

    public void addOrganism(Organism organism) {
        String baseName = organism.getName().split(" ")[0];
        int count = organismCounters.getOrDefault(baseName, 0) + 1;
        organismCounters.put(baseName, count);
        organism.setName(baseName + " " + count);
        organisms.add(organism);
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public List<Organism> getOrganisms() {
        return organisms;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void updateCycle(int counter) {
        System.out.printf("""
                \n**************************************************
                Начинается новый цикл (%d) обновления экосистемы...
                **************************************************\n""", counter);

        List<Organism> currentOrganisms = new ArrayList<>(organisms);
        for (Organism organism : currentOrganisms) {
            organism.consume(this);
        }

        replenishResources();
        reproduceOrganism();

        displayStatus();
    }

    private void replenishResources() {
        System.out.println("Ресурсы восстанавливаются...");
        for (Resource resource : resources) {
            resource.increaseQuantity(3);
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
}
