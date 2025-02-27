package com.app.Models;

public class Questions {
    private int qID;
    private String qContent;
    private String qPicture;
    private int topicID;
    private String qLevel;
    private boolean qStatus;
    public Questions(int qID, String qContent, String qPicture, int topicID, String qLevel, boolean qStatus)
    {
        this.qID = qID;
        this.qContent = qContent;
        this.topicID = topicID;
        this.qLevel = qLevel;
        this.qPicture = qPicture;
        this.qStatus = qStatus;
    }

    public int getqID(){
        return qID;
    }
    public void setqID(int qID){
        this.qID = qID;
    }

    public String getqContent(){
        return qContent;
    }
    public void setqContent(String qContent){
        this.qContent = qContent;
    }

    public String getqPicture(){
        return qPicture;
    }
    public void setqPicture(String qPicture){
        this.qPicture = qPicture;
    }

    public int getTopicID(){
        return topicID;
    }
    public void setTopicID(int topicID){
        this.topicID = topicID;
    }

    public String getqLevel(){
        return qLevel;
    }
    public void setqLevel(String qLevel){
        this.qLevel = qLevel;
    }

    public boolean getqStatus(){
        return qStatus;
    }
    public void setqStatus(boolean qStatus){
        this.qStatus = qStatus;
    }
}
