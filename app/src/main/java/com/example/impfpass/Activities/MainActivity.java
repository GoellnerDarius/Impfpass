package com.example.impfpass.Activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.impfpass.CSVRW;
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
    private final LinkedList<String> impflist = new LinkedList<>();
    private RecyclerView iRecyclerView;
    private ListAdapter iAdapter;
    private static List<Impfung> impfungs;

    public static final String EXTRA_MESSAGE
            = "com.example.android.impfpass.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  FloatingActionButton fab = findViewById(R.id.fab);
        /*fab.setOnClickListener(view -> {
         //   getPath();
            showPopup();

            //                int wordListSize = mWordList.size();
//                // Add a new word to the wordList.
//                mWordList.addLast("+ Word " + wordListSize);
//                // Notify the adapter, that the data has changed.
//                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
//                // Scroll to the bottom.
//                mRecyclerView.smoothScrollToPosition(wordListSize);
        });
*/

        //Datenbank und DAO Interfaces anlegen
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ImpfpassDaten").allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();
        impfungen = db.impfungDAO();
        krankheiten = db.krankheitDAO();

      //  createDatabase();
        //Daten aus der Datenbank holen
        List impfung = impfungen.selectAll();
        List krankheit = krankheiten.selectAll();


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

    public void getPath(View view) {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        startActivityForResult(Intent.createChooser(i, "Choose directory"), 9999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 9999:
                Log.i("Test", "Result URI " + data.getData());
                Log.i("Test", "Result path " + data.getData().getPath());
                Log.i("Test", "Result string" + data.getData().toString());
                final String[] split = data.getData().toString().split(":");//split the path.
                System.out.println("************************************************************************************");
                System.out.println(split[0]);
                System.out.println(split[1]);
                new CSVRW(krankheiten, impfungen, data.getData().getPath()).writeCSV();
                break;
        }
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

    public ImfungDAOInterface getImpfungen() {
        return impfungen;
    }

    public void setImpfungen(ImfungDAOInterface impfungen) {
        this.impfungen = impfungen;
    }

    public KrankheitDAOInterface getKrankheiten() {
        return krankheiten;
    }

    public void setKrankheiten(KrankheitDAOInterface krankheiten) {
        this.krankheiten = krankheiten;
    }
}
/*
class Comp implements Comparator<Impfung> {
    @Override
    public int compare(Impfung i1, Impfung i2) {
        return i1.getDatum().compareTo(i2.getDatum());

    }

}*/