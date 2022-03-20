package SE_506.Assignments.Print;

import java.util.Queue;

public class PrintJob {

    /*Devoid of SOP as PrintJob does two jobs*/

    Queue<PrintRequest> printRequests;
//    PrioritySetting prioritySetting;

    public PrintJob(Queue<PrintRequest> printRequests){
        this.printRequests = printRequests;
//        this.prioritySetting = prioritySetting;
    }

    // Methods
    void pullJob(){
        System.out.println("Printing Job");
        printRequests.remove();
    }



    /*
    void changePriority(){

    }
    This mode is transferred to Priority Setting class following ISP
    */
}
