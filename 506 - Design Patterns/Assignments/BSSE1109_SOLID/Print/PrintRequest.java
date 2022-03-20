package DesignPatterns.Assignments.Print;

import DesignPatterns.Assignments.Mode.PrintMode;

public class PrintRequest {
    Document document;
    PrintMode printMode;
    /*
    * Follows Liskov Substitution Principle as child object can be set in parent object*/

    public PrintRequest(Document document, PrintMode printMode) {
        this.document = document;
        this.printMode = printMode;
    }

    public void setPrintMode(PrintMode printMode) {
        this.printMode = printMode;
    }
}
