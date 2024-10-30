package entities;

public class Resource {
    private String resourceName;

    public Resource(String resourceName){
        this.resourceName = resourceName;
    }

    public String getResourceName(){
        return resourceName;
    }

    @Override
    public String toString(){
        return "Ресурс: " + resourceName;
    }
}
