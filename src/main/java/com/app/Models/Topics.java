package com.app.Models;

public class Topics {
    private int tpID;
    private String tpTitle;
    private int tpParent;
    private boolean tpStatus;

    public Topics(int tpID, String tpTitle, int tpParent, boolean tpStatus){
        this.tpID = tpID;
        this.tpTitle = tpTitle;
        this.tpParent = tpParent;
        this.tpStatus = tpStatus;
    }

    public int getTpID(){
        return tpID;
    }
    public void setTpTitle(String tpTitle){
        this.tpTitle = tpTitle;
    }

    public int getTpParent(){
        return tpParent;
    }
    public void setTpParent(int tpParent){
        this.tpParent = tpParent;
    }

    public boolean getTpStatus(){
        return tpStatus;
    }
    public void setTpStatus(boolean tpStatus){
        this.tpStatus = tpStatus;
    }
}
