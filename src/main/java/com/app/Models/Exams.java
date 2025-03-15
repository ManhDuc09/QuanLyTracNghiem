package com.app.Models;

import java.util.ArrayList;

public class Exams {
    private String testCode;
    private String exOrder;
    private String exCode;
    private ArrayList<Integer> quesIDs;
    public Exams(String testCode, String exOrder, String exCode, ArrayList<Integer> quesIDs) {
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

    public ArrayList<Integer> getQuesIDs() {
        return quesIDs;
    }

    public void setQuesIDs(ArrayList<Integer> quesIDs) {
        this.quesIDs = quesIDs;
    }
}
