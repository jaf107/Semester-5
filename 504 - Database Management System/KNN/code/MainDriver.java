package CSE_504.KNN.code;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainDriver {
    public static void main(String[] args) throws IOException {

        int noOfSamples = 8;

        ArrayList<BufferedImage> dataset = new ArrayList<>();
        String samplePath = "src/CSE_504/KNN/dataset/all_sample/s";

        for (int i = 1; i <= noOfSamples; i++) {
            String tempSamplePath = samplePath + i + ".jpg";

            File tempFile = new File(tempSamplePath);
            BufferedImage tempImage = ImageIO.read(tempFile);
            dataset.add(tempImage);
//            System.out.println(tempSamplePath);
        }

        String testPath = "src/CSE_504/KNN/dataset/test/test_image_3.jpg";
//        testPath = "src/CSE_504/KNN/dataset/all_sample/s3.jpg";
        File testFile = new File(testPath);
        BufferedImage testImage = ImageIO.read(testFile);

        KNN_Trainer knn_trainer_Tool = new KNN_Trainer(testImage,dataset);
        knn_trainer_Tool.showValuesOfKnn();
        System.out.println(knn_trainer_Tool.get_cluster(3));

    }
}
