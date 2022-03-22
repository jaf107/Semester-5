package CSE_504.KNN.code;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class KNN_Trainer {
    BufferedImage test;
    ArrayList<BufferedImage> dataset;

    ArrayList<Double> valuesOfKNN;

    public KNN_Trainer(BufferedImage test, ArrayList<BufferedImage> dataset) {
        this.test = test;
        this.dataset = dataset;
        this.valuesOfKNN = new ArrayList<>();
        calculateValuesOfKNN();
    }

    private void calculateValuesOfKNN() {
        ImageComparator tool;
//        tool.setTest(test);
        for (BufferedImage train:dataset) {
            tool = new ImageComparator(train, test);
            tool.calculateKNN();
            valuesOfKNN.add(tool.getKNN_val());
        }
    }

    @Override
    public String toString() {
        return "KNN_Trainer{" +
                "test=" + test + "\n" +
                ", dataset=" + dataset + "\n" +
                ", valuesOfKNN=" + valuesOfKNN + "\n" +
                '}';
    }

    public void get_cluster() {
        int bestIndex = getBestIndex();

        if(bestIndex < 5){
            System.out.println("Green");
        }else if(bestIndex < 9){
            System.out.println("Snow");
        }
    }

    private int getBestIndex() {
        int index = Integer.MAX_VALUE;
        double val = Double.MAX_VALUE;
        for (int i = 0; i < valuesOfKNN.size(); i++) {
            if(valuesOfKNN.get(i) < val){
                index = i;
                val = Math.min(val, valuesOfKNN.get(i));
            }
        }
        return index+1;
    }
}
