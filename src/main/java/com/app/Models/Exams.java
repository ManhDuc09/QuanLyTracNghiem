package com.app.Models;

public class Exams {
    private String testCode;
    private String exOrder;
    private String exCode;
    private String quesIDs;
    public Exams(String testCode, String exOrder, String exCode, String quesIDs) {
        this.testCode = testCode;
        this.exOrder = exOrder;
        this.exCode = exCode;
        this.quesIDs = quesIDs;
    }

    // Getters và Setters
    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getExOrder() {
        return exOrder;
    }

    public void setExOrder(String exOrder) {
        this.exOrder = exOrder;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public String getQuesIDs() {
        return quesIDs;
    }

    public void setQuesIDs(String quesIDs) {
        this.quesIDs = quesIDs;
    }
}
