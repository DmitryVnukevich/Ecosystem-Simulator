package entities;

public class Animal extends Organism{
    public Animal(String name){
        super(name);
    }

    public String toString(){
        return "Животное: " + name;
    }
}
