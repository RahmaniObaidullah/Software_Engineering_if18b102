package at.technikum.rh.main_programs.Plugin;
/*
import at.technikum.rh.Interfaces.Plugin;
import at.technikum.rh.Interfaces.Response;
import at.technikum.rh.main_programs.request.Request_Class;
import at.technikum.rh.main_programs.response.Response_Class;
import org.xml.sax.InputSource;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import java.util.Map;
import java.util.SortedSet;

public class navi_plugin implements Plugin {

    private boolean xml_lesen = false;
    private boolean xml_parsed = false;
    private SortedSet<String> myset;
    private Map<String, SortedSet<String>> naviData = null;
    String tempereatur = "";

    @Override
    public float canHandle(Request_Class req) {
        if (req.getUrl().getRawUrl().startsWith("/navi")) {
            return (float) 1;
        }
        return 0;
    }

    /*
    @Override
    public Response handle(Request_Class req) {
        if (req.getUrl().getRawUrl().equals("/navi/parse") && (xml_lesen == false)) {
            try {
                //naviData = read_xml();
                xml_lesen = false;
                if (naviData == null) {
                    Response_Class _response_class = new Response_Class();
                    _response_class.setContent("<html>"
                            + "<body>"
                            + "<h2>"
                            + "Fehler ist passiert parsing"
                            + "</h2>"
                            + "</body>"
                            + "</html>");
                } else {
                    Response_Class _response_class = new Response_Class();
                    _response_class.setContent("<html>"
                            + "<body>"
                            + "<h1>"
                            + "Parsed funktioniert"
                            + "</h1>"
                            + "<br>"
                            + "<a href =\"http://localhost:8081/navi/parse'/>Parse</a>"
                            + "<body>"
                            + "</html>");
                    return _response_class;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}*/
        /*
        else if(req.getUrl().getRawUrl().startsWith("/navi")){
            if((xml_lesen == false) && (xml_parsed == true)){
                if(req.getUrl().getParameter().containsKey(""))
            }
        }
        else{

        }



    }
     */

    /*
    private Map<String, SortedSet<String>> read_xml() throws ParserConfigurationException, IOException{
        xml_lesen = true;
        try{
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser parser = parserFactory.newSAXParser();
            FileReader fr = new FileReader("");
            InputSource inputSource = new InputSource(fr);
            //..
            String a;
            parser.parse(inputSource, a);
            xml_parsed = true;
            return 0;
        }
    }
    */
    /*
    @Override
    public String toString(){
        return "NaviPlugin{}";
    }

     */

