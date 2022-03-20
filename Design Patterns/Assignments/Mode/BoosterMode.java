package SE_506.Assignments.Mode;

public class BoosterMode extends PrintMode implements AlterColorIntensityInterface {



    public BoosterMode(String nameOfMode, int numberOfPages, double pageSize, double orientation, double colorIntensity, double costPerPage, AlterColorIntensity alterColorIntensityTool) {
        super(nameOfMode, numberOfPages, pageSize, orientation, colorIntensity, costPerPage);
        this.alterColorIntensityTool = alterColorIntensityTool;
    }

    // Methods

    AlterColorIntensity alterColorIntensityTool;


    public BoosterMode(AlterColorIntensity alterColorIntensityTool) {
        this.alterColorIntensityTool = alterColorIntensityTool;
    }

    void boost() {
        alterColorIntensityTool.boost();
    }

    @Override
    public void alterColorIntersity() {
        boost();
    }
}
