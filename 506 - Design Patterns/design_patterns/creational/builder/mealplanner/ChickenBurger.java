package SE_506.design_patterns.creational.builder.mealplanner;

public class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 50.0f;
    }
}
