import entities.*;

public class Main {
    public static void main(String[] args) {
        Ecosystem ecosystem = new Ecosystem();

        ecosystem.addOrganism(new Plant("Трава"));
        ecosystem.addOrganism(new Plant("Трава 1"));
        ecosystem.addOrganism(new Plant("Трава 2"));
        ecosystem.addOrganism(new Plant("Куст"));
        ecosystem.addOrganism(new Plant("Куст 1"));
        ecosystem.addOrganism(new Plant("Куст 2"));

        ecosystem.addOrganism(new Animal("Заяц", "Rabbit" , "Травоядный"));
        ecosystem.addOrganism(new Animal("Заяц 1", "Rabbit" , "Травоядный"));
        ecosystem.addOrganism(new Animal("Заяц 2", "Rabbit" , "Травоядный"));
        ecosystem.addOrganism(new Animal("Волк", "Wolf" , "Хищник"));

        ecosystem.addResource(new Resource("Солнечный свет", 100));
        ecosystem.addResource(new Resource("Вода", 100));

        for (int i = 1; i < 4; i++) {
            ecosystem.updateCycle(i);
        }
    }
}