package entities;

public class Plant extends Organism {
    public Plant(String name){
        super(name);
    }

    @Override
    public String toString(){
        return "Растение: " + name;
    }
}
