package com.example.impfpass.Activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.impfpass.CSVRW;
import com.example.impfpass.R;
import com.example.impfpass.dao.AppDatabase;
import com.example.impfpass.dao.ImfungDAOInterface;
import com.example.impfpass.dao.KrankheitDAOInterface;

public class Settings extends AppCompatActivity {
    private AppDatabase db;
    private ImfungDAOInterface impfungen;
    private KrankheitDAOInterface krankheiten;
    private RadioButton export;
    private RadioButton importieren;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ImpfpassDaten").allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();
        impfungen = db.impfungDAO();
        krankheiten = db.krankheitDAO();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        export = findViewById(R.id.export);
        importieren = findViewById(R.id.importieren);
        editText=findViewById(R.id.pfad_text);

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
                editText.setText(data.getData().getPath().toString());
                break;
        }
    }

    public void exportieren(View view) {
        if (export.isChecked())
        new CSVRW(krankheiten, impfungen, editText.getText().toString()).writeCSV();
else if (importieren.isChecked())
            new CSVRW(krankheiten, impfungen, editText.getText().toString()).readCSV();

    }
}
