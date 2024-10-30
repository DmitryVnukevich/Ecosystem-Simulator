package entities;

import java.util.List;

abstract class Organism {
    protected String name;

    public Organism(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void consume(List<Resource> resources);
}
