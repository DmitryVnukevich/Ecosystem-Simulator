import entities.*;

public class Main {
    public static void main(String[] args) {
        Ecosystem ecosystem = new Ecosystem();

        ecosystem.addOrganism(new Plant("Трава"));
        ecosystem.addOrganism(new Plant("Трава"));
        ecosystem.addOrganism(new Plant("Куст"));
        ecosystem.addOrganism(new Plant("Куст"));

        ecosystem.addOrganism(new Animal("Заяц", "Травоядный"));
        ecosystem.addOrganism(new Animal("Заяц", "Травоядный"));
        ecosystem.addOrganism(new Animal("Волк", "Хищник"));

        ecosystem.addResource(new Resource("Солнечный свет", 100));
        ecosystem.addResource(new Resource("Вода", 100));

        for (int i = 1; i < 4; i++) {
            ecosystem.updateCycle(i);
        }
    }
}