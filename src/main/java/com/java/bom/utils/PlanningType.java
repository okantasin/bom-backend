package com.java.bom.utils;

public enum PlanningType {
    SABIT, AYLIK, HAFTALIK;

    private String shortCode;


    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }
}