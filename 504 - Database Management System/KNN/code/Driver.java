package CSE_504.KNN.code;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        String trainImagePath = "src/CSE_504/KNN/dataset/all_sample/s2.jpg";
        File trainFile = new File(trainImagePath);
        BufferedImage trainImage = ImageIO.read(trainFile);

        String testImagePath = "src/CSE_504/KNN/dataset/test/test_image_1.jpg";
        File testFile = new File(testImagePath);
        BufferedImage testImage = ImageIO.read(testFile);

        ImageComparator tool = new ImageComparator(trainImage,testImage);
        tool.calculateKNN();

        System.out.println("\n"+tool.getKNN_val());

    }
}
