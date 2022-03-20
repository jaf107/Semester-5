package SE_506.design_patterns.creational.builder.mealplanner;

public class Coke extends ColdDrink{
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 20.0f;
    }
}
