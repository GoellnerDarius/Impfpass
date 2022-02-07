package com.example.impfpass.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.impfpass.R;
import com.example.impfpass.dao.ImfungDAOInterface;
import com.example.impfpass.dao.KrankheitDAOInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class ImpfungAktivity extends AppCompatActivity {
    private final LinkedList<String> impflist = new LinkedList<>();
    private ImfungDAOInterface imfungDAOInterface;
    private KrankheitDAOInterface krankheitDAOInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.impfung_screen);


        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView empfaelung = findViewById(R.id.empfolen);
        TextView tv[] = {
                findViewById(R.id.name),
                findViewById(R.id.schutz),
                findViewById(R.id.hersteller),
                findViewById(R.id.impfdatum),
                findViewById(R.id.nochmal),
                findViewById(R.id.empfaelungen)
        };
        String[] split = message.split(",");
        for (int i = 0; i < tv.length; i++) {
            tv[i].setText(split[i]);
        }
        //Falls es empfoholene Impfdaten gibt
        if (!split[5].isEmpty()&&!split[5].equals("null"))
        {
            tv[5].setText("");
            empfaelung.setText("Empfolenen Impfung");
            String []empehlungen=split[5].split("");
            String tmp;
            for (int i = 0; i <empehlungen.length ; i++) {
               tmp= empehlungen[i].replaceAll("(\\D)","");
                System.out.println(tmp);
                System.out.println(empehlungen[i]);

                tv[5].append((i+1)+". Impfung nach "+tmp+" Jahren ");
            }

        }else {
            empfaelung.setText("");
            tv[5].setText("");
        }
        //Datum der Nächsten impfung
        try {
            Date date = new SimpleDateFormat("yyyy.mm.dd").parse(split[3]);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DAY_OF_YEAR, Integer.parseInt(split[4]) * 7);
            tv[4].setText(c.get(Calendar.DAY_OF_YEAR) + "." + c.get(Calendar.MONTH) + "." + c.get(Calendar.YEAR));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
