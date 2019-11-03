package at.technikum.rh.exercise;
/*
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Response_Class implements Response {
    private boolean status;
    private String content_response;

    private Map<String,String>  header_response = new HashMap<>();
    private static final String leer_zeichen = " ";
    private static final String local_server_time = "";
    private static final String default_server_header = "Name-Server";
    private static final String http_default_version = "HTTP/1.1";

    public Response_Class(){
        header_response.put("Date", local_server_time);
        header_response.put("Server", default_server_header);
    }

    @Override
    public Map<String, String> getHeaders() {
        return header_response;
    }

    @Override
    public int getContentLength() {
        byte[] _responseBytes = new byte[0];
        try{
            if(content_response != null) {
                _responseBytes = content_response.getBytes("UTF-8");
            }
            } catch (Exception e){
            System.out.println("Fehler");
        }
        return _responseBytes.length;
    }

    @Override
    public String getContentType() {
        return header_response.get("Content-Type");
    }
    @Override
    public void setContentType(String contentType) {
        header_response.put("Content-Type");
    }


    @Override
    public int getStatusCode() {
        return 0;
    }

    @Override
    public void setStatusCode(int status) {

    }

    @Override
    public String getStatus() {
        return null;
    }

    @Override
    public void addHeader(String header, String value) {

    }

    @Override
    public String getServerHeader() {
        return header_response.get("Server");
        return null;
    }

    @Override
    public void setServerHeader(String server) {

    }

    @Override
    public void setContent(String content) {

    }
    @Override
    public void setContent(byte[] content) {

    }

    @Override
    public void setContent(InputStream stream) {

    }

    @Override
    public void send(OutputStream network) {

    }
}
*/