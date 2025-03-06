package com.app.Models;

public class Users {
    private int UserID;
    private String UserName;
    private String UserEmail;
    private String UserPassword;
    private String UserFullName;
    private boolean isAdmin;
    public Users(int UserID,String UserName,String UserEmail,String UserPassword, String UserFullName,boolean isAdmin ){
        this.UserID = UserID;
        this.UserName = UserName;
        this.UserEmail = UserEmail;
        this.UserPassword = UserPassword;
        this.UserFullName = UserFullName;
        this.isAdmin = isAdmin;
    }
    public int getUserID(){
        return UserID;
    }
    public void setUserID(int UserID){
        this.UserID = UserID;
    }

    public String getUserName(){
        return UserName;
    }
    public void setUserName(String UserName){
        this.UserName = UserName;
    }

    public String getUserEmail(){
        return UserEmail;
    }
    public void setUserEmail(String UserEmail){
        this.UserEmail = UserEmail;
    }

    public String getUserPassword(){
        return UserPassword;
    }
    public void setUserPassword(String UserPassword){
        this.UserPassword = UserPassword;
    }

    public String getUserFullName(){
        return UserFullName;
    }
    public void setUserFullName(String UserFullName){
        this.UserFullName = UserFullName;
    }

    public boolean getIsAdmin(){
        return isAdmin = isAdmin;
    }
    public void setIsAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }
}
