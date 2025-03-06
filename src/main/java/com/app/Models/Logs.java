package com.app.Models;

import java.util.Date;

public class Logs {
    private int logID;
    private String logContent;
    private int logUserID;
    private int ExID;
    private Date logDate;

    public Logs(int logID, String logContent, int logUserID, int ExID, Date logDate) {
        this.logID = logID;
        this.logContent = logContent;
        this.logUserID = logUserID;
        this.ExID = ExID;
        this.logDate = logDate;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public int getLogUserID() {
        return logUserID;
    }

    public void setLogUserID(int logUserID) {
        this.logUserID = logUserID;
    }

    public int getExID(){
        return ExID;
    }
    public void setExID(int ExID){
        this.ExID = ExID;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date LogDate) {
        this.logDate = logDate;
    }
}
