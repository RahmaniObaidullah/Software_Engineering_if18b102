package at.technikum.rh.main_programs.Plugin;

import at.technikum.rh.Interfaces.Plugin;
import at.technikum.rh.Interfaces.Response;
import at.technikum.rh.main_programs.request.Request_Class;
import at.technikum.rh.main_programs.response.Response_Class;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.SortedSet;

public class NaviPlugin implements Plugin{

    private boolean xml_read = false;
    private boolean xml_got_parsed = false;
    private SortedSet<String> set;
    private Map<String, SortedSet<String>> my_navi_data = null;

    @Override
    public float canHandle(Request_Class req) {
        if(req.getUrl().getRawUrl().startsWith("/navi"))
            return (float) 1;
        return (float) 0;
    }

    @Override
    public Response handle(Request_Class req) throws IOException {

        if(req.getUrl().getRawUrl().equals("/navi/parse") && (xml_read == false)){
            try {
                my_navi_data = read_the_xml();
                xml_read = false;
                if(my_navi_data == null){
                    Response_Class _response_class = new Response_Class();
                    _response_class.setContent("<html>" +
                            "html" +
                            "<h2>" +
                            "Erro while Parsind" +
                            "</h2>" +
                            "</body>" +
                            "</html>"
                    );
                    return _response_class;
                }
                else{
                    Response_Class _response_class = new Response_Class();
                    _response_class.setContent("<html><body><h2>" +
                            "Successfully parsed" +
                            "</h2>" +
                            "<br>" +
                            "<a href =\"http://localhost:8081/navi\"/>back to the Homepage</a>" +
                            "</body>" +
                            "</html>");
                    return _response_class;
                }
            }catch (ParserConfigurationException e){
                    e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (req.getUrl().getRawUrl().startsWith("/navi")){
            if((xml_read == false) && (xml_got_parsed == true)){
                String my_cities = "";
                if(req.getUrl().getParameter().containsKey("street")){
                    String _street = req.getUrl().getParameter().get("street");
                    _street = _street.replaceAll("\\+"," ");
                    _street = _street.toLowerCase();
                    _street = _street.replaceAll("%df","ß");
                    _street = _street.replaceAll("%e4", "ä");
                    _street = _street.replaceAll("%f6", "ö");
                    _street = _street.replaceAll("%fc", "ü");
                    System.out.println(_street);
                    System.out.println("--");
                    if(my_navi_data.containsKey(_street)){
                        set = my_navi_data.get(_street);
                        for (String s : set){
                            my_cities+=s.replaceAll("ß", "&szlig")
                                    .replaceAll("ä","&auml")
                                    .replaceAll("ö", "&ouml")
                                    .replaceAll("ü", "&uuml")
                                    .replaceAll("Ä","&Auml")
                                    .replaceAll("Ö", "&Ouml")
                                    .replaceAll("Ü","&Uuml");
                            my_cities+="<br>";
                        }
                    }
                    else {
                        my_cities = "street does not exist.";
                    }
                }
                Response_Class _response_class = new Response_Class();
                _response_class.setContent("<html>" +
                        "<head>" +
                        "</head>" +
                        "<body>" +
                        "<a href = 'http://localhost:8081/navi/parse'/>" +
                        "Reparse" +
                        "</a>" +
                        "<br>" +
                        "<br>" +
                        "<form <action='navi' method='get'>" +
                        "Streetname: <input type ='text' name ='street'>" +
                        "<input type='submit' value='Submit'>" +
                        "</form>" +
                        "<br><pre>" +
                        "cities" +
                        "</pre>" +
                        "</body>" +
                        "</html>");
                return _response_class;
            }
            else if(xml_read == true){
                Response_Class _response_class = new Response_Class();
                _response_class.setContent("<html>" +
                        "<body>" +
                        "Map gets parsed. Please wait 2 minutes to finish and than reload this site." +
                        "</body>" +
                        "</html>");
                return _response_class;
            }
            else if(xml_got_parsed == false){
                Response_Class _response_class = new Response_Class();
                _response_class.setContent("<html>" +
                        "<body>" +
                        "Map is not loaded. Please parse the XML." +
                        "<br>" +
                        "<a href='http://localhost:8081/navi/parse'/>" +
                        "Parse" +
                        "</a>" +
                        "</body>" +
                        "</html>");
                return _response_class;
            }
        }
        return null;
    }

    private Map<String, SortedSet<String>> read_the_xml() throws ParserConfigurationException, IOException{
        xml_read = true;
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser parser = parserFactory.newSAXParser();
            FileReader fread = new FileReader(get_the_FileName());
            InputSource input = new InputSource(fread);
            Osm_handler handler = new Osm_handler();
            parser.parse(input, handler);
            xml_got_parsed = true;
            return handler.return_navi_data();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String get_the_FileName() throws FileNotFoundException {
        String filename = "austria-latest.osm";
        String inputFile = System.getProperty("user.dir") + "/src/at/technikum/rh/myFiles" + filename;
        System.out.println("Valid input file");
        return inputFile;
    }
}