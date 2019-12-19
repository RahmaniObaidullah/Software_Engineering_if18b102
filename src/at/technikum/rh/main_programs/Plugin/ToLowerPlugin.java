package at.technikum.rh.main_programs.Plugin;

import at.technikum.rh.Interfaces.Plugin;
import at.technikum.rh.Interfaces.Response;
import at.technikum.rh.main_programs.request.Request_Class;
import at.technikum.rh.main_programs.response.Response_Class;

import java.io.IOException;

public class ToLowerPlugin implements Plugin {
    @Override
    public float canHandle(Request_Class req) {
        if (req.getUrl().getRawUrl().startsWith("/lower")) return (float) 1;
        return 0;
    }

    @Override
    public Response handle(Request_Class req) {
        Response _resp = new Response_Class();
//		System.out.println(arg0.getUrl().getParameter().get("text"));
        if (req.getMethod().toLowerCase().equals("get")) {
            if (req.getUrl().getParameter().get("text") == null) {
//				System.out.println("GET-Methode, ohne Inhalt");
                _resp.setContent("<html>"
                        + "<head>"
                        + "</head>"
                        + "<body>"
                        + "<form <action='lower' method='post'>"
                        + "text: <input type='text' name='text'>"
                        + "<input type='submit' value='Submit'>"
                        + "</form>"
                        + "<br><pre>"
                        + "</pre>"
                        + "</body>"
                        + "</html>");
            } else {
//				System.out.println("GET-Methode, mit Content");
                _resp.setContent("<html>"
                        + "<head>"
                        + "</head>"
                        + "<body>"
                        + "<form <action='lower' method='post'>"
                        + "text: <input type='text' name='text'>"
                        + "<input type='submit' value='Submit'>"
                        + "</form>"
                        + "<br><pre>"
                        + req.getUrl().getParameter().get("text").toLowerCase()
                        + "</pre>"
                        + "</body>"
                        + "</html>"
                );
            }
        } else if (req.getMethod().toLowerCase().equals("post")) {
            if (req.getContentLength() > 0) {
//				System.out.println("POST-Methode, mit Content");
                char c = 0;
                String _content = "";
                for (int i = 0; i < req.getContentLength(); i++) {
                    try {
                        c = (char) req.getContentStream().read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (c == '+') _content += ' ';
                    else _content += c;
                }
                _resp.setContent("<html>"
                        + "<head>"
                        + "</head>"
                        + "<body>"
                        + "<form <action='lower' method='post'>"
                        + "text: <input type='text' name='text'>"
                        + "<input type='submit' value='Submit'>"
                        + "</form>"
                        + "<br><pre>"
                        + _content.toLowerCase().replaceFirst("text=", "")
                        + "</pre>"
                        + "</body>"
                        + "</html>");
            } else {
//				System.out.println("POST-Methode, aber kein Inhalt");
                _resp.setContent("<html>"
                        + "<head>"
                        + "</head>"
                        + "<body>"
                        + "<form <action='lower' method='post'>"
                        + "text: <input type='text' name='text'>"
                        + "<input type='submit' value='Submit'>"
                        + "</form>"
                        + "</body>"
                        + "</html>");
            }
        }
        return _resp;
    }
}


    /*
    private String getHTML_text(String eingebene_text){
        String ausgabe = "<html>" +
                "<head>" +
                "Rahmeni Obaidullah" +
                "</head>" +
                "<body>" +
                "<form <action = 'lower' method='post'>" +
                "Text: <br><textarea rows = '4' cols='30' name='text'>" +
                "</textarea><br>" +
                "<input type='submit' value='Submit'>" +
                "</form>" +
                "<br>" +
                "<pre>" +
                (eingebene_text != null ? eingebene_text.toLowerCase() : "")+
                "</pre>" +
                "</body>" +
                "</html>";
        return ausgabe;
    }

    @Override
    public float canHandle(Request_Class req) throws Exception {
        if(req.isValid()){
            if(req.UrlClass.getRawUrl().startsWith("/lower")){
                System.out.println("Die Funktion ToLower wird ausgefuehrt");
                return (float) 1.0;
            }
        }
        return (float) 0.0;
    }

    @Override
    public Response handle(Request_Class req) throws IOException, ParserConfigurationException, SAXException, SQLException {
        Response_Class response_class = new Response_Class();
        if(req.getContentLength() < 0){
            response_class.setContent(getHTML_text(null));
        }
        else {
            String[] txt_eingabe = req.getContentString().split("=");
            response_class.setContent(getHTML_text(txt_eingabe[1]));
        }
        return response_class;
    }
}













    /*
    @Override
    public float canHandle(Request_Class req) {
        if(req.getUrl().getRawUrl().startsWith("/lower"))
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


     */