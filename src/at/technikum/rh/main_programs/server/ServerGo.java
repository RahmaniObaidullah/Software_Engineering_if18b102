package at.technikum.rh.main_programs.server;

//Interfaces
import at.technikum.rh.main_programs.request.Request_Class;
import at.technikum.rh.main_programs.response.Response_Class;

import java.net.Socket;

//Classes

public class ServerGo {
    private Socket serversocket;

    //Getter
    ServerGo(Socket _serversocket){
        this.serversocket = _serversocket;
    }

    //@Override
    public void run(){
        try{
            Request_Class _request = new Request_Class(this.serversocket.getInputStream());

            if(!_request.isValid()) this.serversocket.close();

            Response_Class response = new Response_Class();
            response.setStatusCode(200);
            response.setContent("Test_SW_01");
            response.send(this.serversocket.getOutputStream());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
