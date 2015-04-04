package com.hackaton.questapp.entity;

/**
 * Created by Sheremeta on 04.04.2015.
 */
public enum TaskType {

    GPS,
    INPUT,
    QRCODE,
    CUSTOM;


    public static TaskType getTaskTypeByString(String string){
        switch(string){
            case "GPS": return GPS;
            case "INPUT": return INPUT;
            case "QRCODE": return QRCODE;
            case "CUSTOM": return CUSTOM;
            default: return null;
        }
    }
}
