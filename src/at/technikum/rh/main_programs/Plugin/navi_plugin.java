package at.technikum.rh.main_programs.Plugin;

import at.technikum.rh.Interfaces.Plugin;
import at.technikum.rh.Interfaces.Request;
import at.technikum.rh.Interfaces.Response;

public class navi_plugin implements Plugin {
    @Override
    public float canHandle(Request req) {
        return 0;
    }

    @Override
    public Response handle(Request req) {
        return null;
    }

    @Override
    public String toString(){
        return "NaviPlugin{}";
    }
}
