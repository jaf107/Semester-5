package CSE_504.KNN.code;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class KNN_Trainer {
    BufferedImage test;
    ArrayList<BufferedImage> dataset;

    ArrayList<Double> valuesOfKNN;
    ArrayList<Boolean> selected;

    public KNN_Trainer(BufferedImage test, ArrayList<BufferedImage> dataset) {
        this.test = test;
        this.dataset = dataset;
        this.valuesOfKNN = new ArrayList<>();
        this.selected = new ArrayList<>();
        calculateValuesOfKNN();
    }

    private void calculateValuesOfKNN() {
        ImageComparator tool;
//        tool.setTest(test);
        for (BufferedImage train:dataset) {
            tool = new ImageComparator(train, test);
            tool.calculateKNN();
            valuesOfKNN.add(tool.getKNN_val());
            selected.add(false);
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

    public String get_cluster(int k) {
//        int k = 3;
        ArrayList<Double> sample = valuesOfKNN;

        ArrayList<Integer> snow = new ArrayList<>();
        ArrayList<Integer> green = new ArrayList<>();

        int bestIndex;
        for (int i = 0; i < k; i++) {
            bestIndex = getBestIndex(sample);
            if(bestIndex < 5){
                green.add(bestIndex);
            }else if(bestIndex < 9){
                snow.add(bestIndex);
            }
            sample.remove(bestIndex-1);
        }
        if(snow.size() > green.size()){
            return "Snow";
        }else{
            return "Green";
        }
    }

    private int getBestIndex(ArrayList<Double> sample) {
        int index = Integer.MAX_VALUE;
        double val = Double.MAX_VALUE;
//        int i = 0;
        for (int i = 0; i < sample.size(); i++) {
            if(sample.get(i) < val){
                index = i;
                val = Math.min(val, sample.get(i));
            }
        }
        selected.set(index, true);
        return index+1;
    }

    void showValuesOfKnn(){
        for (int i = 0; i < valuesOfKNN.size(); i++) {
            System.out.println("Value -> "+ (i+1) + " : " + valuesOfKNN.get(i) + " " + selected.get(i));
        }
    }

}
