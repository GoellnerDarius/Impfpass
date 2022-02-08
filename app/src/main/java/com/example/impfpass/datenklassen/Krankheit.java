package com.example.impfpass.datenklassen;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

@Entity
public class Krankheit {
    @PrimaryKey(autoGenerate = true)
    private int krankheitID;
    private String name;
    private int gueltigkeit;

    public Krankheit(String name, int gueltigkeit) {
        this.name = name;
        this.gueltigkeit = gueltigkeit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGueltigkeit() {
        return gueltigkeit;
    }

    public int getKrankheitID() {
        return krankheitID;
    }

    public void setKrankheitID(int krankheitID) {
        this.krankheitID = krankheitID;
    }
}
