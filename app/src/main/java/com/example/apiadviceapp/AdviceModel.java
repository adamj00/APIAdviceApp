package com.example.apiadviceapp;

public class AdviceModel {
    private int slip_id;
    private String advice;

    public AdviceModel(int slip_id, String advice) {
        this.slip_id = slip_id;
        this.advice = advice;
    }

    public int getSlip_id() {
        return slip_id;
    }

    public void setSlip_id(int slip_id) {
        this.slip_id = slip_id;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    @Override
    public String toString() {
        return '"' + advice + '"';
    }
}
