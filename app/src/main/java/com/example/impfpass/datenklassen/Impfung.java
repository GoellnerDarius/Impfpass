package com.example.impfpass.datenklassen;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.Nullable;

@Entity
public class Impfung {
   @PrimaryKey(autoGenerate = true)
   private int impfID;
   private String impfungsName;
   private int krankheitsID;
   private String hersteller;
   private String datum;
   @Nullable
   private String empfaehlung;

   @Ignore
   public Impfung(String impfungsName, String hersteller, String datum, int krankheitsID) {
      this.impfungsName = impfungsName;
      this.hersteller = hersteller;
      this.datum = datum;
      this.krankheitsID=krankheitsID;

   }
   public Impfung(String impfungsName, String hersteller, String datum, int krankheitsID, String empfaehlung) {
      this.impfungsName = impfungsName;
      this.hersteller = hersteller;
      this.datum = datum;
      this.krankheitsID=krankheitsID;
      this.empfaehlung =empfaehlung;

   }
   public String getImpfungsName() {
      return impfungsName;
   }

   public void setImpfungsName(String impfungsName) {
      this.impfungsName = impfungsName;
   }

   public int getKrankheitsID() {
      return krankheitsID;
   }

   public void setKrankheitsID(int krankheitsID) {
      this.krankheitsID = krankheitsID;
   }

   public String getHersteller() {
      return hersteller;
   }

   public void setHersteller(String hersteller) {
      this.hersteller = hersteller;
   }

   public String getDatum() {
      return datum;
   }

   public void setDatum(String datum) {
      this.datum = datum;
   }

   public int getImpfID() {
      return impfID;
   }

   public void setImpfID(int impfID) {
      this.impfID = impfID;
   }

   @Nullable
   public String getEmpfaehlung() {
      return empfaehlung;
   }

   public void setEmpfaehlung(@Nullable String empfaehlung) {
      this.empfaehlung = empfaehlung;
   }
}
