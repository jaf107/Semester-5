package DesignPatterns.Assignments.Mode;

public class TonerSaveMode extends PrintMode{
    /*
        This Method deals with altering Color Intensity

    */

     String tonerSavingLevel;
     AlterColorIntensity alterColorIntensityTool; // Serves SOP as this class only alters color Intensity


    public TonerSaveMode(String tonerSavingLevel) {
        this.tonerSavingLevel = tonerSavingLevel;
        alterColorIntensityTool = new AlterColorIntensity(tonerSavingLevel);
    }

    // Methods


    public String getTonerSavingLevel() {
        return tonerSavingLevel;
    }

    public void setTonerSavingLevel(String tonerSavingLevel) {
        this.tonerSavingLevel = tonerSavingLevel;
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

    void saveToner() {
        alterColorIntensityTool.alterColorIntensity();
    }
}
