package at.technikum.rh.main_programs.server;

//Interfaces
import at.technikum.rh.Interfaces.Request;
import at.technikum.rh.Interfaces.Response;

//Classes
import at.technikum.rh.main_programs.request.*;
import at.technikum.rh.main_programs.response.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.io.*;

public class Server {

    public void start_server(int port_number) throws IOException{
        ServerSocket serversocket = new ServerSocket(port_number);
        while (!serversocket.isClosed()){
            ServerGo serverRun = new ServerGo(serversocket.accept());
            Thread t = new Thread(serverRun);
            t.start();
        }
    }

}
