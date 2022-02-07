package com.example.impfpass;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.impfpass.dao.ImfungDAOInterface;
import com.example.impfpass.dao.KrankheitDAOInterface;
import com.example.impfpass.datenklassen.Impfung;
import com.example.impfpass.datenklassen.Krankheit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVRW {
    File f;
    KrankheitDAOInterface krankheiten;
    ImfungDAOInterface impfungen;

    public CSVRW(KrankheitDAOInterface krankheiten, ImfungDAOInterface impfungen, String filePath) {
        this.krankheiten = krankheiten;
        this.impfungen = impfungen;
        this.f = new File("/SDCARD/Download/test.csv");
        System.out.println(f.getPath());

    }

    public void readCSV() {
        try {
            String s2;
            String[] elements;
            BufferedReader bf = new BufferedReader(new FileReader(f));
            //Daten einlesen

            s2 = bf.readLine();
            while (!s2.contains("\\s")) {

                elements = s2.split(",");
                krankheiten.insert(new Krankheit(elements[0], Integer.parseInt(elements[1])));
                s2 = bf.readLine();

            }
            ;//Bis keine krankheiten mehr Existieren Lesen
            //Impfungen einlesen
            while (bf.ready()) {//Lesen bis zum dateiende
                s2 = bf.readLine();
                elements = s2.split(",");
                //Impfungen einlesen
                impfungen.insert(new Impfung(elements[0], elements[2], elements[3], krankheiten.selectIDWhereName(elements[1])));
                bf.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void writeCSV() {
        List<Krankheit> krankheitsList=krankheiten.selectAll();
        List<Impfung> impfungsList=impfungen.selectAll();
        //Krankheiten schreiben
        try {
            System.out.println(f.getPath());
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            for (int i = 0; i < krankheitsList.size(); i++) {
                bw.write(krankheitsList.get(i).getName() + "," + krankheitsList.get(i).getGueltigkeit() + "\n");

            }
            //Stopzeichen für CSVReader um zu signalisieren das die Krankheiten beendet sind
            bw.write("\\s\n");
            bw.flush();
            //Impfungen Schreiben
            for (int i = 0; i < impfungsList.size(); i++) {
                bw.write(impfungsList.get(i).getImpfungsName() + "," + krankheiten.selectIDWhereName(impfungsList.get(i).getImpfungsName()) + "," + impfungsList.get(i).getHersteller() + "," + impfungsList.get(i).getDatum() + "\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
