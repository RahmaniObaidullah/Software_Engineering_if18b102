package at.technikum.rh.Datenbank;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.Random;
public class Datenbank_hinzufuegen_Werte {
/** Die Daten richtig in der Datenbanktabelle speichern.
 *Die Temperatur-Daten werden von 2009 bis 2017 pro Tag 4 gespeichert.
 * Gesamt sind es 10416 Daten*/
    public static void main (String[] args) throws SQLException {
        /** 1. Verbindung mit der Datenbank */
        Connection myconnection = DriverManager.getConnection("jdbc:mysql://localhost", "JavaUser", "abc123");
        /** 2. ein Statement erstellen */
        Statement mystatement = myconnection.createStatement();
        /** 3. Execute SQL query */
        ResultSet selectdatabase = mystatement.executeQuery("use temperaturdaten");
        DecimalFormat f = new DecimalFormat("#0.00");
        Random r = new Random();
        //System.out.println(a);
        int jahr = 2009; int monat = 12; int tag = 0; int wiederholung = 0;
        String ausgabe; String time; String temperatur_data;
        String date; String sql_insert; int tag_test; int anzahl = 0;

        while( jahr <= 2020) {
            for (int i = 1; i <= monat; i++) {
                tag_test = 1;
                if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                    tag = 31;
                    while (tag_test <= tag) {
                        wiederholung = tag_test;
                        while (wiederholung <= tag_test + 3) {
                            temperatur_data = f.format(temperaturdata());
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){}
                            time = time_data();
                            date = date(jahr, i, tag_test);
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){}
                            sql_insert = sql_insert(temperatur_data, date, time);
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){}
                            mystatement.executeUpdate(sql_insert);
                            System.out.println(sql_insert);
                            wiederholung++;
                            anzahl++;
                        }
                        tag_test++;
                        try{
                            Thread.sleep(150);
                        }catch(InterruptedException e){}
                    }
                } else if (i == 4 || i == 6 || i == 9 || i == 11) {
                    tag = 30;
                    while (tag_test <= tag) {
                        wiederholung = tag_test;
                        while (wiederholung <= tag_test + 3) {
                            temperatur_data = f.format(temperaturdata());
                            try{
                                Thread.sleep(70);
                            }catch(InterruptedException e){}
                            time = time_data();
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){}
                            date = date(jahr, i, tag_test);
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){}
                            sql_insert = sql_insert(temperatur_data, date, time);
                            mystatement.executeUpdate(sql_insert);
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){}
                            System.out.println(sql_insert);
                            wiederholung++;
                        }
                        tag_test++;
                        try{
                            Thread.sleep(150);
                        }catch(InterruptedException e){}
                    }
                } else {
                    tag = 28;
                    while (tag_test <= tag) {
                        wiederholung = tag_test;
                        while (wiederholung <= tag_test + 3) {
                            temperatur_data = f.format(temperaturdata());
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){}
                            time = time_data();
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){}
                            date = date(jahr, i, tag_test);
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){}
                            sql_insert = sql_insert(temperatur_data, date, time);
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){}
                            mystatement.executeUpdate(sql_insert);
                            System.out.println(sql_insert);
                            wiederholung++;
                        }
                        tag_test++;
                        try{
                            Thread.sleep(150);
                        }catch(InterruptedException e){}
                    }
                }
            }
            jahr++;
        }
        System.out.println(anzahl);
        myconnection.close();
    }
    public static double temperaturdata(){
        double temperatur_wert = Math.random()*(40 - 5);
        return temperatur_wert;
    }
    public static String time_data(){
        Random r = new Random();
        int stunde = r.nextInt(24);
        int minute = r.nextInt(60);
        int sekunde = r.nextInt(60);
        return ""+stunde+":"+minute+":"+sekunde;
    }
    public static String date(int year, int month, int day){
        return ""+year+"-"+month+"-"+day;
    }
    public static String sql_insert(String temperatur, String date, String time){
        return "insert into temperatur_data (temperatur,datum)" +
                " values ("+temperatur+",'"+date+" "+time+"');";
    }
}
