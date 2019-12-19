package at.technikum.rh.main_programs.Plugin;

import at.technikum.rh.Interfaces.Plugin;
import at.technikum.rh.Interfaces.Response;
import at.technikum.rh.main_programs.request.Request_Class;
import at.technikum.rh.main_programs.response.Response_Class;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StaticFilePlugin implements Plugin {
    @Override
    public float canHandle(Request_Class req) {
        try{
            FileReader _fr = new FileReader(System.getProperty("user.dir")+"/src/at/technikum/rh/myFiles/"
                    +req.getUrl().getPath().replaceAll("/",""));
            _fr.close();
        }catch (Exception e){
            return 0;
        }
        return 1;
    }


    @Override
    public Response handle(Request_Class req) {
        Response_Class _response_class = new Response_Class();
        String _my_content = ""; String _my_content_line;
        System.out.println(req.getUrl().getPath().replaceAll("/", ""));
        File newFile = new File(System.getProperty("user.dir")+"/src/at/technikum/rh/myFiles/"
                +req.getUrl().getPath().replaceAll("/",""));
        try{
            BufferedReader _contentReader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/at/technikum/rh/myFiles/"
                    +req.getUrl().getPath().replaceAll("/","")));
            while (true){
                _my_content_line = _contentReader.readLine();
                System.out.println(_my_content_line);
                if(_my_content_line == null){
                    break;
                }
                _my_content += _my_content_line;
            }
            _contentReader.close();
        }catch (Exception e){
            System.out.println("Die Files wurden nicht gefunden");
            _response_class.setStatusCode(404);
        }
        _response_class.setContent(_my_content);
        return _response_class;
    }
}
