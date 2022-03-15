package DesignPatterns.Assignments.Mode;

public class BoosterMode extends PrintMode {

    // Methods

    AlterColorIntensity alterColorIntensityTool;


    public BoosterMode(AlterColorIntensity alterColorIntensityTool) {
        this.alterColorIntensityTool = alterColorIntensityTool;
    }

    void boost() {
        alterColorIntensityTool.boost();
    }
}
