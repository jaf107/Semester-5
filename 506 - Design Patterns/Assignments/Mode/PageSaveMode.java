package SE_506.Assignments.Mode;

public class PageSaveMode extends PrintMode{

    /*
    * This class is created separately following SOP principle*/

    double defaultSize;
    double defaultOrientation;

    public PageSaveMode() {
        defaultSize = 1.0;
        defaultOrientation = 0.0;
    }

    public PageSaveMode(double defaultSize, double defaultOrientation) {
        this.defaultSize = defaultSize;
        this.defaultOrientation = defaultOrientation;
    }
// Methods

    void adjustPageSize(double newPageSize){
        System.out.println("Adjusting page size");
        pageSize = newPageSize;
    }

    void adjustOrientation(double newOrientation){
        System.out.println("Adjusting page orientation");
        orientation = newOrientation;
    }

    void renderPreview(){
        System.out.println("Rendering preview of updated document");
    }


    void savePage() {
        adjustPageSize(defaultSize);
        adjustOrientation(defaultOrientation);
        renderPreview();
    }

}
