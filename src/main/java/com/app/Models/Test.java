package com.app.Models;

import java.sql.Date;

public class Test {
    private int testID;
    private String testCode;
    private String testTitle;
    private int testTime;
    private int tpID;
    private int numEasy;
    private int numMedium;
    private int numDiff;
    private int testLimit;
    private Date testDate;
    private boolean testStatus;

    // Constructor
    public Test(int testID, String testCode, String testTitle, int testTime, int tpID,
                int numEasy, int numMedium, int numDiff, int testLimit, Date testDate, boolean testStatus) {
        this.testID = testID;
        this.testCode = testCode;
        this.testTitle = testTitle;
        this.testTime = testTime;
        this.tpID = tpID;
        this.numEasy = numEasy;
        this.numMedium = numMedium;
        this.numDiff = numDiff;
        this.testLimit = testLimit;
        this.testDate = testDate;
        this.testStatus = testStatus;
    }

    // Getters và Setters
    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public int getTestTime() {
        return testTime;
    }

    public void setTestTime(int testTime) {
        this.testTime = testTime;
    }

    public int getTpID() {
        return tpID;
    }

    public void setTpID(int tpID) {
        this.tpID = tpID;
    }

    public int getNumEasy() {
        return numEasy;
    }

    public void setNumEasy(int numEasy) {
        this.numEasy = numEasy;
    }

    public int getNumMedium() {
        return numMedium;
    }

    public void setNumMedium(int numMedium) {
        this.numMedium = numMedium;
    }

    public int getNumDiff() {
        return numDiff;
    }

    public void setNumDiff(int numDiff) {
        this.numDiff = numDiff;
    }

    public int getTestLimit() {
        return testLimit;
    }

    public void setTestLimit(int testLimit) {
        this.testLimit = testLimit;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public boolean getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(boolean testStatus) {
        this.testStatus = testStatus;
    }
}
