package at.technikum.rh.Datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Datenbank_verbindung {

    public static void main (String[] args){
        try{
            /** Uebung fuer mich selber, wie man ein auf einen Datenbankserver zugreift*/
            /** 1. Verbindung mit der Datenbank */
            Connection myconnection = DriverManager.getConnection("jdbc:mysql://localhost", "JavaUser", "abc123");
            /** 2. ein Statement erstellen */
            Statement mystatement = myconnection.createStatement();
            /** 3. Execute SQL query */
            ResultSet selectdatabase = mystatement.executeQuery("use temperaturdaten");
            ResultSet myresultset = mystatement.executeQuery("select * from temperatur_data");
            /** 4. Process the result set */
            while (myresultset.next()){
                System.out.println(myresultset.getString(
                        "id") + "," + myresultset.getString("temperatur")
                + ", "+myresultset.getString("datum"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    try{
        Class.forName("com.mysql.jdbc.Driver"); --
        Connection conn = null;
        conn = DriverManager.getConnection(jdbc:mysql://localhost,,root,"");

    }catch (Exception e){
        System.out.println("Erro "+ e);
    }

    //zurückgeben der neuen Verbindung

     */
}



//Daten mit Blacken schicken, geht schneller und ist einfacher
//laden der Treiberklasse