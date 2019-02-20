package com.example.minesh.navigationd;

public class Faculty_retrieve_class {
    public String Day,Slot,Faculty,Room;

    public Faculty_retrieve_class(){}

    public Faculty_retrieve_class(String day, String slot, String faculty, String room) {
        Day = day;
        Slot = slot;
        Faculty = faculty;
        Room = room;
    }

    public String gDay() {
        return Day;
    }

    public void sDay(String day) {
        Day = day;
    }

    public String gSlot() {
        return Slot;
    }

    public void sSlot(String slot) {
        Slot = slot;
    }

    public String gFaculty() {
        return Faculty;
    }

    public void sFaculty(String faculty) {
        Faculty = faculty;
    }

    public String gRoom() {
        return Room;
    }

    public void sRoom(String room) {
        Room = room;
    }
}
