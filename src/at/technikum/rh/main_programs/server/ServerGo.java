package at.technikum.rh.main_programs.server;

//Interfaces
import at.technikum.rh.main_programs.request.Request_Class;
import at.technikum.rh.main_programs.response.Response_Class;

import java.net.Socket;

//Classes

public class ServerGo implements Runnable {
    private Socket serversocket;
    ServerGo(Socket _serversocket){
        this.serversocket = _serversocket;
    }
    @Override
    public void run(){
        try{
            Request_Class _request = new Request_Class(this.serversocket.getInputStream());
            Response_Class _response = new Response_Class();

            if(!_request.isValid()){
                _response.setStatusCode(400);
                _response.setContent("Server-Code 400");
                serversocket.close();
            }
            else{
                _response.setStatusCode(200);
                _response.setContent("Software Engineering - Obaidullah Rahmani");
            }
            _response.send(this.serversocket.getOutputStream());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
