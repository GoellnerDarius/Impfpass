package com.example.impfpass.Activities;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.impfpass.R;
import com.example.impfpass.dao.AppDatabase;
import com.example.impfpass.dao.ImfungDAOInterface;
import com.example.impfpass.dao.KrankheitDAOInterface;

public class AddKrankheit extends AppCompatActivity {
  //  private AppDatabase db;
   // private ImfungDAOInterface impfungen;
   // private KrankheitDAOInterface krankheiten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //   db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ImpfpassDaten").allowMainThreadQueries().fallbackToDestructiveMigration()
       //         .build();
       // impfungen = db.impfungDAO();
       // krankheiten = db.krankheitDAO();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_krankheit);
    }
    public void addKrankheit(View view){

    }
}
