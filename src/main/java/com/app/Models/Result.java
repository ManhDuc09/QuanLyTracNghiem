package com.app.Models;

import java.math.BigDecimal;
import java.sql.Date;

public class Result {
    private int rs_num;
    private int userID;
    private String exCode;
    private String rs_answer;
    private BigDecimal rs_mark;
    private Date rs_Date;

    public Result(int rs_num, int userID, String exCode, String rs_answer, BigDecimal rs_mark, Date rs_Date){
        this.rs_num = rs_num;
        this.userID = userID;
        this.exCode = exCode;
        this.rs_answer = rs_answer;
        this.rs_mark = rs_mark;
        this.rs_Date = rs_Date;
    }

    public int getRsNum() {
        return rs_num;
    }

    public void setRsNum(int rs_num) {
        this.rs_num = rs_num;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public String getRsAnswer() {
        return rs_answer;
    }

    public void setRsAnswer(String rs_answer) {
        this.rs_answer = rs_answer;
    }

    public BigDecimal getRsMark() {
        return rs_mark;
    }

    public void setRsMark(BigDecimal rs_mark) {
        this.rs_mark = rs_mark;
    }

    public Date getRsDate() {
        return rs_Date;
    }

    public void setRsDate(Date rs_Date) {
        this.rs_Date = rs_Date;
    }
}
