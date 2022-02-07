package com.example.impfpass.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomDatabase;
import android.os.Parcelable;

import com.example.impfpass.datenklassen.Impfung;
import com.example.impfpass.datenklassen.Krankheit;

import java.util.List;
@Dao
public interface KrankheitDAOInterface  {

     //@Insert
     //void insertAll(Krankheit... krankheits) ;

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insert(Krankheit krankheit);
     @Query("DELETE FROM krankheit WHERE name LIKE :krankheitsname")
     void deleteByName(String krankheitsname) ;
     @Query("DELETE FROM krankheit WHERE krankheitID LIKE :krankheitsID")
     void deleteByID(int krankheitsID) ;
     @Query("SELECT * FROM krankheit")
     List<Krankheit> selectAll();
     @Query("SELECT krankheitID FROM krankheit WHERE name LIKE :krankeitsName")
     int selectIDWhereName(String krankeitsName);
     @Query("SELECT krankheitID FROM krankheit WHERE krankheitID LIKE :id")
     int selectNameWhereID(int id);
     @Query("SELECT * FROM krankheit WHERE krankheitID LIKE :id")
     Krankheit selectKrankheitbyID(int id);


}


