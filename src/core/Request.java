package core;

/**
 * Created by home on 11/04/2016.
 */
public class Request{


    private int request_ID;
    private String startDate;
    private String endDate;
    private String status;
    private int emp_ID2;

    public Request(){

    }

    public Request(int  request_id, String startDate, String endDate, String status, int emp_ID){
        this.request_ID =  request_id;
        this.endDate=endDate;
        this.startDate=startDate;
        this.emp_ID2 = emp_ID ;
        this.status = status;

    }

    public int getRequest_ID(){
        return request_ID;
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
    public String toString() {
        return request_ID +" From: " + startDate + " To: " + endDate ;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public int getEmp() {
        return emp_ID2;
    }


    public void setStatus(String status) {
        this.status = status;
    }
}