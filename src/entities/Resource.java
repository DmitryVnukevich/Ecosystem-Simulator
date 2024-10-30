package entities;

public class Resource {
    private String resourceName;
    private int quantity;

    public Resource(String resourceName, int quantity) {
        this.resourceName = resourceName;
        this.quantity = quantity;
    }

    public String getResourceName() {
        return resourceName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity(int amount) {
        quantity = Math.max(quantity - amount, 0);
    }

    @Override
    public String toString() {
        return "Ресурс: " + resourceName + ", количество: " + quantity;
    }
}
