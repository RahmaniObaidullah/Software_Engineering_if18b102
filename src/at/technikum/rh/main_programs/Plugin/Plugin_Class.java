package at.technikum.rh.main_programs.Plugin;

import at.technikum.rh.Interfaces.Plugin;
import at.technikum.rh.Interfaces.Response;
import at.technikum.rh.main_programs.request.Request_Class;
import at.technikum.rh.main_programs.response.Response_Class;

public class Plugin_Class implements Plugin {
    /**
     * Returns a score between 0 and 1 to indicate that the plugin is willing to
     * handle the request. The plugin with the highest score will execute the
     * request.
     * @param req
     * @return A score between 0 and 1
     */
    @Override
    public float canHandle(Request_Class req) {
        System.out.println("my Request"+req);
        float score_between_zero_one = (float) 0.1;
        //das mit den hoeheren Punkte Anzahl kann es dann ausfuehen, die Punkte-Anzahl ist zwischen 0 und 1
        if(req.isValid() && req.UrlClass.getRawUrl().equals("/test")) {
            score_between_zero_one = (float) 1.0;
        }
        return score_between_zero_one;
    }
    /**
     * Called by the server when the plugin should handle the request.
     * @param req
     * @return A new response object.
     */
    @Override
    public Response handle(Request_Class req) {
        Response_Class _response_class = new Response_Class();
        if(canHandle(req) < 0.0f){
            _response_class.status_code = 500;
        }
        else{
            _response_class.status_code = 200;
        }
        _response_class.content_type = "text/html; charset=utf-8";
        _response_class.addHeader("Accept the Language", "de-AT");
        _response_class.setContent("Test // Test //Test");
        return _response_class;
    }
}
