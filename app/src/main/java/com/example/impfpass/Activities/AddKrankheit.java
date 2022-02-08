package com.example.impfpass.Activities;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.impfpass.R;
import com.example.impfpass.dao.AppDatabase;
import com.example.impfpass.dao.ImfungDAOInterface;
import com.example.impfpass.dao.KrankheitDAOInterface;
import com.example.impfpass.datenklassen.Krankheit;

public class AddKrankheit extends AppCompatActivity {
    private AppDatabase db;
    private ImfungDAOInterface impfungen;
    private KrankheitDAOInterface krankheiten;
    private EditText krankheitsname;
    private EditText impfabstand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ImpfpassDaten").allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();
        impfungen = db.impfungDAO();
        krankheiten = db.krankheitDAO();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_krankheit);
        krankheitsname=findViewById(R.id.krankheit_edit);
        impfabstand=findViewById(R.id.impfabstand);



    }

    public void addKrankheit(View view){

        krankheiten.insert(new Krankheit(krankheitsname.getText().toString(),Integer.parseInt(impfabstand.getText().toString().replaceAll("\\D",""))));



    }
}
