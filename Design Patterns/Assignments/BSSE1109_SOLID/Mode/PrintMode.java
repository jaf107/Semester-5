package DesignPatterns.Assignments.Mode;

public abstract class PrintMode { // Can't have instances

    /*
       This class deals with the printing mode and there are three classes that extends from it
*/
    String nameOfMode = "";

    int numberOfPages;
    double pageSize;
    double orientation;
    double colorIntensity;
    double costPerPage;

    // Methods

    /*
    abstract void saveToner(); // Transferred to TonerSaveMode
    abstract void savePage(); // Transferred to PageSaveMode
    abstract void boost();  // Transferred to BoostMode

    This transfer of abstract methods signifies the SINGLE RESPONSIBILITY Principle (SOP) &
    Interface Segregation Principle (ISP)

*/



    // CONSTRUCTOR
//    public PrintMode(int numberOfPages, double pageSize, double orientation, double colorIntensity, double costPerPage) {
//        this.numberOfPages = numberOfPages;
//        this.pageSize = pageSize;
//        this.orientation = orientation;
//        this.colorIntensity = colorIntensity;
//        this.costPerPage = costPerPage;
//    }

    // GETTER & SETTER
    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public double getPageSize() {
        return pageSize;
    }

    public void setPageSize(double pageSize) {
        this.pageSize = pageSize;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public double getColorIntensity() {
        return colorIntensity;
    }

    public void setColorIntensity(double colorIntensity) {
        this.colorIntensity = colorIntensity;
    }

    public double getCostPerPage() {
        return costPerPage;
    }

    public void setCostPerPage(double costPerPage) {
        this.costPerPage = costPerPage;
    }
}
