package at.technikum.rh.main_programs.Plugin;

import at.technikum.rh.Interfaces.Plugin;
import at.technikum.rh.Interfaces.Response;
import at.technikum.rh.main_programs.request.Request_Class;
import at.technikum.rh.main_programs.response.Response_Class;

import java.io.IOException;

public class Myhomepage implements Plugin {
    @Override
    public float canHandle(Request_Class req) {
        if(req.getUrl().getRawUrl().endsWith("/") || req.getUrl().getRawUrl().startsWith("/test/"))
            return (float) 1;
        return (float) 0;
        /** Dieser Request wird immer bearbeitet, die Homepage wird immer angezeigt */
    }

    @Override
    public Response handle(Request_Class req) throws IOException {
        Response_Class _response_class = new Response_Class();
        String _myWebSeite;
        _myWebSeite = "<html>" +
                "<body>" +
                "<h1> This is a default Webpage from Rahmani.\n" +
                "The Server works (SWE) </h1>";
        _response_class.setContent(_myWebSeite);
        return _response_class;
    }
}
