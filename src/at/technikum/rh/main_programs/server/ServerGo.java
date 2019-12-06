package at.technikum.rh.main_programs.server;

//Interfaces

import at.technikum.rh.main_programs.request.Request_Class;
import at.technikum.rh.main_programs.response.Response_Class;

import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

//Classes

public class ServerGo implements Runnable {
    private Socket serversocket; private String input_fileName;
    //ServerGo wurde um eine Variable erweitert (Filename kann jetzt gelesen werden)

    /*
    ServerGo(Socket _serversocket, String _myFileName){
        this.serversocket = _serversocket;
        this.input_fileName = _myFileName;
    }
     */
    ServerGo(Socket _serversocket){
        this.serversocket = _serversocket;
    }
    @Override
    public void run(){
        try{
            Request_Class _request = new Request_Class(this.serversocket.getInputStream());
            Response_Class _response = new Response_Class();
            //Response_Class _response = null;
            if(!_request.isValid()){
                _response.setStatusCode(400);
                _response.setContent("Server-Code 400 - is a bad Request");
                serversocket.close();
            }
            else{
                _response.setStatusCode(200);
                /**
                _response.setContent("Software Engineering - Obaidullah Rahmani");
                Benutze ich wenn ich nur das ausgeben moechte, also kein File
                 */
                /** Files.readAllBytes(Paths.get(..) diese Funktion wird dafuer verwendet, damit die jeweiligen
                 * Filese gelesen werden. */
                //_response.setContent(Files.readAllBytes(Paths.get(input_fileName)));
                /** Ueber URL Bilder und texte lesen koennen*/
                _response.setContent(Files.readAllBytes(Paths.get(
                                            System.getProperty("user.dir")+
                                                    "/src/at/technikum/rh/myFiles/"+
                                                    _request.getUrl().getFileName())));
                //Java Funktion fuer pfad benutzen --> nachschauen paths.get -->
            }
            _response.send(this.serversocket.getOutputStream());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
