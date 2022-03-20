package SE_506.design_patterns.creational.factory;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape shape1 = shapeFactory.getShape("CIRCLE");
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        Shape shape3 = shapeFactory.getShape("SQUARE");
//        Shape shape4 = shapeFactory.getShape("RECTANGLE");

        shape1.draw();
        shape2.draw();
        shape3.draw();
//        shape4.draw();



    }
}
