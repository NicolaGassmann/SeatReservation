package sample;

public class Category {
    private int categoryId;
    private String name;
    private double price;

    public Category(int categoryId, String name, double price){
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
    }

    public int getCategoryId() { return categoryId; }

    public String getName() { return name; }

    public double getPrice() { return price; }
}
