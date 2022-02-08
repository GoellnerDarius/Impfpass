package com.example.impfpass.Activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.impfpass.R;
import com.example.impfpass.dao.AppDatabase;
import com.example.impfpass.dao.ImfungDAOInterface;
import com.example.impfpass.dao.KrankheitDAOInterface;
import com.example.impfpass.dao.ListAdapter;
import com.example.impfpass.datenklassen.Impfung;
import com.example.impfpass.datenklassen.Krankheit;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //DAO
    private AppDatabase db;
    private ImfungDAOInterface impfungen;
    private KrankheitDAOInterface krankheiten;
    //RecyclerView
    private final static LinkedList<String> impflist = new LinkedList<>();
    private static RecyclerView iRecyclerView;
    private static ListAdapter iAdapter;
    public static final String EXTRA_MESSAGE
            = "com.example.android.impfpass.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getColor(R.color.purple_500));
        setSupportActionBar(toolbar);

        //Datenbank und DAO Interfaces anlegen
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ImpfpassDaten").allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();
        impfungen = db.impfungDAO();
        krankheiten = db.krankheitDAO();

      // createDatabase();
        //Daten aus der Datenbank holen
        List impfung = impfungen.selectAll();


        //Elemente zur Recyclerview hinzufügen
        for (int i = 0; i < impfung.size(); i++) {
            impflist.addLast(((Impfung) impfung.get(i)).getImpfungsName() + "\t\t\t\t" + ((Impfung) impfung.get(i)).getDatum());
            System.out.println(impflist.get(i));
        }

        // Create recycler view.
        iRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        iAdapter = new ListAdapter(this, impflist);
        // Connect the adapter with the recycler view.
        iRecyclerView.setAdapter(iAdapter);
        // Give the recycler view a default layout manager.
        iRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public void update(View view){
        impflist.addLast("TTTTTTTTTTTTTTTTT");
        iRecyclerView.setAdapter(iAdapter);
    }
    public static void update2(String s){
        impflist.addLast(s);
        iRecyclerView.setAdapter(iAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar
        // if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // If option menu item is Settings, return true.
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,
                    Settings.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void launchErstellung(View view) {
        Intent intent = new Intent(this, ChoseCreation.class);
        // String message = mMessageEditText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void showImpfung(View view) {
        // Get the position of the item that was clicked.
        Intent intent = new Intent(this, ImpfungAktivity.class);
        Button b = (Button) view;

        Impfung im = impfungen.selectByName(b.getText().toString().split("\t")[0]);
        Krankheit kh=krankheiten.selectKrankheitbyID(im.getKrankheitsID());
        System.out.println(kh.getName());
        System.out.println(im.getImpfungsName());
        intent.putExtra(EXTRA_MESSAGE, im.getImpfungsName()+","+kh.getName()+","+im.getHersteller()+","+im.getDatum()+","+kh.getGueltigkeit()+","+im.getEmpfaehlung());
        startActivity(intent);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.printf("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

    }

    private void createDatabase() {
        //Zum anlegen von Testdaten

        //Krankheiten
        krankheiten.deleteByName("Tetanus");
        krankheiten.deleteByName("Tetanus");
        krankheiten.deleteByName("Diefteri");
        krankheiten.deleteByName("Polio");
        krankheiten.deleteByName("FSME");
        krankheiten.deleteByName("Covid");
        krankheiten.insert(new Krankheit("Tetanus", 265));
        krankheiten.insert(new Krankheit("Diefteri", 265));
        krankheiten.insert(new Krankheit("Polio", 360));
        krankheiten.insert(new Krankheit("FSME", 53));
        krankheiten.insert(new Krankheit("Covid", 20));
        //Impfungen
        impfungen.deleteByName("FSME Imun");
        impfungen.deleteByName("android.support.v7.widget.AppCompatEditText{910e2a9 VFED..CL. ........ 66,332-646,456 #7f08008b app:id/impfName aid=1073741824}");
        impfungen.deleteByName("Polio");
        impfungen.deleteByName("Difteri");
        impfungen.deleteByName("Tetanus");
        impfungen.deleteByName("Covid-19");
        System.out.println(krankheiten.selectIDWhereName("FSME"));
        System.out.println(krankheiten.selectIDWhereName("Polio"));
        System.out.println(krankheiten.selectIDWhereName("Tetanus"));
        impfungen.insert(new Impfung("FSME Imun", "Baxter", "2020.02.03", krankheiten.selectIDWhereName("FSME")));
        impfungen.insert(new Impfung("Polio", "HEX AVAC", "2020.02.04", krankheiten.selectIDWhereName("Diefteri"),"3Y 6Y 12Y, 10Y"));
        impfungen.insert(new Impfung("Difteri", "HEX AVAC", "2020.05.02", krankheiten.selectIDWhereName("Polio")));
        impfungen.insert(new Impfung("Tetanus", "HEX AVAC", "2001.02.02", krankheiten.selectIDWhereName("Tetanus")));
        impfungen.insert(new Impfung("Covid-19", "Biontec/Pfizer", "2022.01.22", krankheiten.selectIDWhereName("Covid")));
    }



}
