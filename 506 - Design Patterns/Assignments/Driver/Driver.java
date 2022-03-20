package SE_506.Assignments.Driver;

import SE_506.Assignments.Mode.TonerSaveMode;

public class Driver {
    public static void main(String[] args) {
        TonerSaveMode tonerSaveMode = new TonerSaveMode("high");
        tonerSaveMode.alterColorIntersity();
        System.out.println(tonerSaveMode.getColorIntensity());
    }
}
