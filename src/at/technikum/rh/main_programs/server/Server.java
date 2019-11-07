package at.technikum.rh.main_programs.server;

//Interfaces
import java.io.IOException;
import java.net.ServerSocket;

//Classes

public class Server {

    public void start_server(int port_number) throws IOException{
        ServerSocket serversocket = new ServerSocket(port_number);
        while (!serversocket.isClosed()){
            ServerGo serverGo = new ServerGo(serversocket.accept());
            Thread t = new Thread(serverGo);
            t.start();
        }
    }

}
