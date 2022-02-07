package com.example.impfpass.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.impfpass.R;

public class ChoseCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_creation);
    }
    public void launchCreation(View view){
        RadioButton impfung= findViewById(R.id.rb_impfung);
        RadioButton krankheit= findViewById(R.id.rb_krankheit);
        if (impfung.isChecked()) {
            Intent intent = new Intent(this, AddImpfung.class);

            startActivity(intent);
            finish();
        }else if (krankheit.isChecked()){
            Intent intent = new Intent(this, AddKrankheit.class);
            startActivity(intent);
            finish();
        }
        else {
            TextView warning=findViewById(R.id.warning);
        warning.setText("Bitte wähle aus was du auswählen willst");
        }
    }
}