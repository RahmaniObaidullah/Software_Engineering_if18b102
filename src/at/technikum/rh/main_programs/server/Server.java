package at.technikum.rh.main_programs.server;

//Interfaces
import at.technikum.rh.main_programs.PluginManager.PluginManager_CLass;

import java.io.IOException;
import java.net.ServerSocket;

//Classes

public class Server {
    static PluginManager_CLass pluginManager_cLass = new PluginManager_CLass();

    //public void start_server(int _port_number, String _myFileName) throws IOException{
    public void start_server(int _port_number, String _myFileName) throws IOException{
        ServerSocket serversocket = new ServerSocket(_port_number);

        while (!serversocket.isClosed()){
            //Filename wird dann in der Main-Methode hinzugefuegt (neu)
            //ServerGo serverGo = new ServerGo(serversocket.accept(),_myFileName);
            ServerGo serverGo = new ServerGo(serversocket.accept(),_myFileName);
            Thread thread_for_sockets = new Thread(serverGo);
            thread_for_sockets.start();
        }
    }

}
