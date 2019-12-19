package at.technikum.rh.main_programs.Plugin;

import at.technikum.rh.Interfaces.Plugin;
import at.technikum.rh.Interfaces.Response;
import at.technikum.rh.main_programs.PluginManager.PluginManager_CLass;
import at.technikum.rh.main_programs.request.Request_Class;
import at.technikum.rh.main_programs.response.Response_Class;

import java.net.Socket;

public class Threads_for_the_plugin implements Runnable{
    private Socket _my_socket = null;
    private Plugin _plugin_class = null;
    private PluginManager_CLass _pluginManager_cLass = null;

    /**
     * übergibt der Klasse SimpleThread die Socketdaten und den Pluginmanager
     */

    public Threads_for_the_plugin(Socket socket, PluginManager_CLass pluginManager){
        this._my_socket = socket;
        //Ist die Socket an dem die Anforderung auftritt
        this._pluginManager_cLass =  pluginManager;
        //Ist im Main PluginManager implementiert
    }
    //Thread laeuft fuer mehrere Benutzer auf dem Server
    @Override
    public void run() {
        Request_Class _request_class;
        Response _response_class = null;
        try {
            _request_class = new Request_Class(_my_socket.getInputStream());
            _response_class = new Response_Class();
            //_plugin_class=_pluginManager_cLass.getBest(_request_class);
            _response_class = _plugin_class.handle(_request_class);
            if(_response_class == null){
                _response_class = new Response_Class();
                _response_class.setStatusCode(404);
            }
            _response_class.send(_my_socket.getOutputStream());
            _my_socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
