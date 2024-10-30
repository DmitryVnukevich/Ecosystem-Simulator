package entities;

import java.util.List;

public class Plant extends Organism {
    public Plant(String name) {
        super(name);
    }

    @Override
    public void consume(List<Resource> resources) {
        System.out.println(name + " потребляет солнечный свет и воду.");
        // реализаия логики уменьшения ресурсов и изменение состояния растения
    }

    @Override
    public String toString() {
        return "Растение: " + name;
    }
}
