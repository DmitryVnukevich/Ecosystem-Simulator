package entities;

abstract class Organism {
    protected String name;

    public Organism(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void consume(Ecosystem ecosystem);

    public abstract Organism reproduce();

    public abstract String toString();
}
