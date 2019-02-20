package com.example.minesh.navigationd;

public class LabInfo_class {
    public String Day,Lab_No,Slot;

    public LabInfo_class(){}

    public LabInfo_class(String day, String lab_No, String slot) {
        Day = day;
        Lab_No = lab_No;
        Slot = slot;
    }

    public String gDay() {
        return Day;
    }

    public void sDay(String day) {
        Day = day;
    }

    public String gLab_No() {
        return Lab_No;
    }

    public void sLab_No(String lab_No) {
        Lab_No = lab_No;
    }

    public String gSlot() {
        return Slot;
    }

    public void sSlot(String slot) {
        Slot = slot;
    }
}
