package CSE_504.KNN.code;
import java.awt.*;
import java.awt.image.BufferedImage;
import static java.lang.Math.abs;
import static java.lang.Math.min;

public class ImageComparator {
    BufferedImage train;
    BufferedImage test;
    Integer minHeight;
    Integer minWidth;

    private Double KNN_val;

    public ImageComparator() {
    }

    public ImageComparator(BufferedImage train, BufferedImage test) {
        this.train = train;
        this.test = test;
        this.KNN_val = 0.0;
        calculateMinDimension();
//        setMinHeight(1);
//        setMinWidth(1);
    }

    public void setTrain(BufferedImage train) {
        this.train = train;
    }

    public void setTest(BufferedImage test) {
        this.test = test;
    }

    public void setMinHeight(Integer minHeight) {
        this.minHeight = minHeight;
    }

    public void setMinWidth(Integer minWidth) {
        this.minWidth = minWidth;
    }

    private void calculateMinDimension() {
        this.minHeight = min(train.getHeight(), test.getHeight());
        this.minWidth = min(train.getWidth(),test.getWidth());
    }


    public void calculateKNN(){

        double knn = 0.0;
        for (int i = 0; i < minWidth; i++) {
            for (int j = 0; j < minHeight; j++) {
                int train_pixel = train.getRGB(i, j);
                int test_pixel = test.getRGB(i, j);

                Color trainColor = new Color(train_pixel, true);
                Color testColor = new Color(test_pixel, true);

//                System.out.print("\nRed : ");
                knn += square_of_difference(trainColor.getRed(), testColor.getRed());

//                System.out.print("Green : ");
                knn += square_of_difference(trainColor.getGreen(), testColor.getGreen());

//                System.out.print("Blue : ");
                knn += square_of_difference(trainColor.getBlue(), testColor.getBlue());
            }
        }
        KNN_val = Math.sqrt(knn);
    }

    private double square_of_difference(int first, int second) {
        int d = abs(first - second);
//        System.out.println(first + " - " + second);
        return d*d;
    }

    public Double getKNN_val() {
        return KNN_val;
    }
}
