package com.example.impfpass.Activities;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.impfpass.R;
import com.example.impfpass.dao.AppDatabase;
import com.example.impfpass.dao.ImfungDAOInterface;
import com.example.impfpass.dao.KrankheitDAOInterface;
import com.example.impfpass.datenklassen.Impfung;

import java.util.List;

public class AddImpfung extends AppCompatActivity {
    private AppDatabase db;
    private ImfungDAOInterface impfungen;
    private KrankheitDAOInterface krankheiten;
    private EditText name;
    private EditText hersteller;
    private EditText datum;
    private EditText krankheit;
    private TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ImpfpassDaten").allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();
        impfungen = db.impfungDAO();
        krankheiten = db.krankheitDAO();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_impfung);
        name = findViewById(R.id.impfName);
        hersteller = findViewById(R.id.hersteller_edit);
        datum = findViewById(R.id.datum);
        krankheit = findViewById(R.id.krankheit_impfung_edit);
        status = findViewById(R.id.warning_add);

    }

    public void addImpfung(View view) {
        System.out.println(krankheit.getText());
        int id = krankheiten.selectIDWhereName(krankheit.getText().toString());
        Impfung impfung = new Impfung(name.getText().toString(), hersteller.getText().toString(), datum.getText().toString(), id);
        System.out.println(impfung.getImpfungsName());
        System.out.println(impfung.getHersteller());
        System.out.println(impfung.getDatum());
        System.out.println(impfung.getImpfID());
        if (id != 0) {
            impfungen.insert(new Impfung(name.toString(), hersteller.toString(), datum.toString(), id));
            status.setTextColor(getResources().getColor(R.color.ok));
            status.setText("Impfung erfolgreich hinzugefügt");
            name.setText("");
            hersteller.setText("");
            datum.setText("");
            krankheit.setText("");
            status.setText("");
        } else {
            status.setTextColor(getResources().getColor(R.color.warning));
            status.setText("Krankheit Exestiert nicht");
        }
    }

}
