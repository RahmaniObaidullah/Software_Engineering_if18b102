package at.technikum.rh.main_programs.Plugin;

import at.technikum.rh.Interfaces.Plugin;
import at.technikum.rh.Interfaces.Response;
import at.technikum.rh.main_programs.request.Request_Class;
import at.technikum.rh.main_programs.response.Response_Class;

import java.io.IOException;

public class to_lower_plugin implements Plugin {
    @Override
    public float canHandle(Request_Class req) {
        if(req.getUrl().getRawUrl().startsWith("lower"))
            return (float) 1;
        else
            return 0;
    }

    @Override
    public Response handle(Request_Class req) throws IOException {
        Response_Class _response_class = new Response_Class();

        if(req.getMethod().toLowerCase().equals("get")){
            if(req.getUrl().getParameter().get("test") == null){
                _response_class.setContent("<html>"+"<head>"+"</head>"
                                            +"<body>"
                                            +"<form <action='lower' method='post'"
                                            + "text: <input type='text' name='text'>"
                                            +"<input type='submit' value='Submit'>"
                                            +"</form>"
                                            +"<br><pre></pre>"
                                            +"</body>"
                                            +"</html>");
            }
            else{
                _response_class.setContent("<html>"+"<head>"+"</head>"
                        +"<body>"
                        +"<form <action='lower' method='post'"
                        + "text: <input type='text' name='text'>"
                        +"<input type='submit' value='Submit'>"
                        +"</form>"
                        +"<br><pre>"
                        +req.getUrl().getParameter().get("text").toLowerCase()
                        +"</pre>"
                        +"</body>"
                        +"</html>");
            }
        }
        else if(req.getMethod().toLowerCase().equals("post")){
            char _v = 0; String _my_Content = "";
            if(req.getContentLength() > 0){
                for(int i=0; i < req.getContentLength(); i++){
                    try {
                        _v = (char) req.getContentStream().read();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    if( _v == '+'){
                        _my_Content += ' ';
                    }
                    else {
                        _my_Content += _v;
                    }
                }
                _response_class.setContent("<html>"+"<head>"+"</head>"
                        +"<body>"
                        +"<form <action='lower' method='post'"
                        + "text: <input type='text' name='text'>"
                        +"<input type='submit' value='Submit'>"
                        +"</form>"
                        +"</body>"
                        +"</html>");
                }
            }
        return _response_class;
    }
}
