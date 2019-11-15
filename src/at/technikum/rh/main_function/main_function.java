package at.technikum.rh.main_function;


import at.technikum.rh.main_programs.server.Server;

public class main_function {
    //Test with the interfaces with my main_function
    public static void main(String[] args){
        Server my_newServer;
        int myport = 8080;
        /**Mehrere varianten koennen getestet werden. Deshalb wurden mehrere variablen
         * mit unterschiedlichen Files erstellt*/
        //user.dir nimmt die jeweilige Verzeichnis von dem Benutzer
        String myInputFile1 = System.getProperty("user.dir")+ "/src/at/technikum/rh/myFiles/Broadcast.png";
        String myInputFile2 = System.getProperty("user.dir")+ "/src/at/technikum/rh/myFiles/stevejobs.jpg";
        String myInputFile3 = System.getProperty("user.dir")+ "/src/at/technikum/rh/myFiles/stevejobs.txt";
        String myInputFile4 = System.getProperty("user.dir")+ "/src/at/technikum/rh/myFiles/htmlSeite.html";
        /**
        Bei my_newServer.start_server(... die gewuenschte variable aendern, damit zum beispiel png
        statt jpg getestet wird
         */
        try{
            System.out.println("The server will be started shortly");
            System.out.println("The server will be started on the port"+myport);
            my_newServer = new Server();
            my_newServer.start_server(myport,myInputFile3);
            System.out.println("The server has now been started");
        } catch (Exception e){
            System.out.println("Error happened!\n Erro Meesage"+e.getMessage());
        }
    }
}


/**
 * Antwort auf die Frage:
 * Durch die Google-Recherche habe ich mehrere Wege gefunden, wie man ein File (Text oder Bild),
 * auslesen konnte, wie z. B. mit konnte es auch mit BufferedReader die Files, sei es auch Bild auslesen,
 * die veriante besteht aus mehreren Code-Zeilen.
 * Durch die Bibliotheken Files & Paths braucht man wenige Befehle um das ganze umzusetzen.
 * Links, die fuer die Recherche verwendet wurden:
    * https://docs.oracle.com/javase/7/docs/api/java/io/File.html
    * https://docs.oracle.com/javase/7/docs/api/java/io/File.html#toPath()
    * https://docs.oracle.com/javase/7/docs/api/java/nio/file/Path.html
    * https://examples.javacodegeeks.com/java-basics/java-library-path-what-is-it-and-how-to-use/
    * https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
 */