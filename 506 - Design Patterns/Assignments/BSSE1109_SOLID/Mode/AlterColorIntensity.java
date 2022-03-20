package DesignPatterns.Assignments.Mode;

//import static DesignPatterns.Assignments.Mode.BoosterMode.maxColorIntensity;

public class AlterColorIntensity extends TonerSaveMode{

    /* Following OpenClose Principle*/
    final int maxColorIntensity = 100;

    public AlterColorIntensity(String tonerSavingLevel) {
        super(tonerSavingLevel);
    }

    void alterColorIntensity(){
        double tempColorIntensity = this.colorIntensity;
        if(this.tonerSavingLevel == "high" || this.tonerSavingLevel == "HIGH" ) {
            System.out.println("Color intensity High, reducing toner level");
            tempColorIntensity--;
            this.colorIntensity = tempColorIntensity;
        } else if(this.tonerSavingLevel == "medium" || this.tonerSavingLevel == "MEDIUM"
                || this.tonerSavingLevel == "low" || this.tonerSavingLevel == "LOW") {
            System.out.println("Color intensity Medium or Low, reducing toner level");
            tempColorIntensity--;
            this.colorIntensity = tempColorIntensity;
        }
    }

    void boost(){
        colorIntensity = maxColorIntensity;
    }



}
