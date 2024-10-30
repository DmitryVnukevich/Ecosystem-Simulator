package entities;

import java.util.List;

public class Animal extends Organism {
    public Animal(String name) {
        super(name);
    }

    @Override
    public void consume(List<Resource> resources) {
        System.out.println(name + " потребляет растения и воду");
        // реализовать логику поиска и потребления растений
    }

    public String toString() {
        return "Животное: " + name;
    }
}
