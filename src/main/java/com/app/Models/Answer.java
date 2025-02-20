package com.app.Models;

public class Answer {
    private int aId;
    private int qId;
    private String aContent;
    private String aPicture; // URL
    private boolean aStatus;
    private boolean isRight;

    // Constructor with all properties
    public Answer(int aId, int qId, String aContent, String aPicture, boolean aStatus, boolean isRight) {
        this.aId = aId;
        this.qId = qId;
        this.aContent = aContent;
        this.aPicture = aPicture;
        this.aStatus = aStatus;
        this.isRight = isRight;
    }

    // Getters and Setters
    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public String getaContent() {
        return aContent;
    }

    public void setaContent(String aContent) {
        this.aContent = aContent;
    }

    public String getaPicture() {
        return aPicture;
    }

    public void setaPicture(String aPicture) {
        this.aPicture = aPicture;
    }

    public boolean isaStatus() {
        return aStatus;
    }

    public void setaStatus(boolean aStatus) {
        this.aStatus = aStatus;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }
}
