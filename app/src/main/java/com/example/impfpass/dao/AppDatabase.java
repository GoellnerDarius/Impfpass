package com.example.impfpass.dao;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import com.example.impfpass.datenklassen.Impfung;
import com.example.impfpass.datenklassen.Krankheit;

@Database(entities={Impfung.class, Krankheit.class},version=5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract KrankheitDAOInterface krankheitDAO();
    public abstract ImfungDAOInterface impfungDAO();

}
