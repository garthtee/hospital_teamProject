package core;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by home on 11/04/2016.
 */
public class Request {


    private int request_ID;
    private Calendar startDate;
    private Calendar endDate;
    private String status;
    private int emp_ID2;

    public Request(){

    }

    public Request(int request_ID, Calendar startDate, Calendar endDate, int emp_ID){
        this.endDate=endDate;
        this.startDate=startDate;
        this.request_ID=request_ID;
        status="waiting";
        this.emp_ID2=emp_ID;
    }

    public int getEmp_ID2() {
        return emp_ID2;
    }

    public void setEmp_ID2(int emp_ID2) {
        this.emp_ID2 = emp_ID2;
    }

    public int getRequest_ID() {

        return request_ID;
    }

    public void setRequest_ID(int request_ID) {
        this.request_ID = request_ID;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }


    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return request_ID + "   " + startDate + "   "+ endDate+"   "+emp_ID2;
    }
}
