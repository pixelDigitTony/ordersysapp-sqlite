package com.example.ordersysapp.Model;

public class Request {
    private String requestId;
    private String requestNumber;
    private String requestdateTime;
    private String requestStatus;
    private String requestTotal;

    public Request(String requestId, String requestNumber, String requestdateTime, String requestStatus,  String requestTotal) {
        this.requestId = requestId;
        this.requestNumber = requestNumber;
        this.requestdateTime = requestdateTime;
        this.requestStatus = requestStatus;
        this.requestTotal = requestTotal;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestNumber() {return requestNumber; }

    public void setrequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getRequestdateTime() {
        return requestdateTime;
    }

    public void setRequestdateTime(String requestdateTime) {
        this.requestdateTime = requestdateTime;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestTotal() {
        return requestTotal;
    }

    public void setRequestTotal(String requestTotal) {
        this.requestTotal = requestTotal;
    }

}
