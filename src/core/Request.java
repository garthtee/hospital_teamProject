package core;

/**
 * Created by home on 11/04/2016.
 */
public class Request {


    private int request_ID;
    private String startDate;
    private String endDate;
    private String status;
    private int emp_ID2;

    public Request(){

    }

    public Request(String startDate, String endDate, int emp_ID){
        this.endDate=endDate;
        this.startDate=startDate;



    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
