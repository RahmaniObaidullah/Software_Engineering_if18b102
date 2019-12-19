package at.technikum.rh.main_function;

import at.technikum.rh.main_programs.server.Server;

import java.io.IOException;

public class main_function_for_plugin {

    public static void main(String[] args) throws IOException {
       Server myServer;
       String myInputFile;
       myInputFile = System.getProperty("user.home")+"/myFiles/staticFiles";
       int port = 8080;
       try{
           System.out.println("Server wird unter dem Port "+port+" gestartet");
           myServer = new Server();
           myServer.start_server(port,myInputFile);
           System.out.println("Server wurde gestartet ");
       }catch (Exception e){
           System.out.println("Fehler meldungen"+e.getMessage());
       }
    }
}
