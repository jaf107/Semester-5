package SE_506.design_patterns.creational.builder.mealplanner;

public interface Item {
    public String name();
    public Packing packing();
    public float price();
}
