package SE_506.design_patterns.creational.builder.mealplanner;

public abstract class Burger implements Item{

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();

}
