package com.example.impfpass.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.provider.ContactsContract;

import com.example.impfpass.datenklassen.Impfung;
import com.example.impfpass.datenklassen.Krankheit;

import java.nio.channels.SelectableChannel;
import java.util.List;
@Dao
public interface ImfungDAOInterface {
    //Alle Krankheiten abrufen
    @Query("SELECT * FROM impfung")
    List<Impfung> selectAll();


    @Query("DELETE FROM impfung WHERE impfungsName LIKE :krankheitsname")
    void deleteByName(String krankheitsname) ;
    @Query("SELECT * FROM impfung WHERE impfungsName like :impfName")
    Impfung selectByName(String impfName);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Impfung impfung);


}
