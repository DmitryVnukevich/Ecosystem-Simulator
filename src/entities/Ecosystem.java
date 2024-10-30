package entities;

import java.util.ArrayList;
import java.util.List;

public class Ecosystem {
    private List<Organism> organisms;
    private List<Resource> resources;

    public Ecosystem() {
        this.organisms = new ArrayList<>();
        this.resources = new ArrayList<>();
    }

    public void addOrganism(Organism organism) {
        organisms.add(organism);
    }

    public void addResource(Resource resource) {
        resources.add(resource);
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
