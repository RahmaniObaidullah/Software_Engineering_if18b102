package at.technikum.rh.main_programs.response;

import at.technikum.rh.Interfaces.Response;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Response_Class implements Response {

    private Map<String,String> myheader = new HashMap<>();
    //vari for content methods
    private byte[] content; private String content_type; private int content_length;
    //vari for status methods
    private String status_server; private int status_code; private String server_header;

    public Response_Class(){
        this.myheader.put("Server_SW", "Rahmani-Obaidullah-Server");
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.myheader;
    }

    @Override
    public int getContentLength() {
        return this.content_length;
    }

    @Override
    public String getContentType() {
        //return this.content_type;
        return myheader.get("Content-Type");
    }

    @Override
    public void setContentType(String contentType) {
        myheader.put("Content-Type", contentType);
    }

    @Override
    public int getStatusCode() {
        if(this.status_code == 0){
            throw new IllegalArgumentException("null");
        }
        else{
            return this.status_code;
        }
        //return 0;
    }

    @Override
    public void setStatusCode(int status) {
        this.status_code=status;
        if(status == 200)
            this.status_server = "200 OK";
        else if (status == 404)
            this.status_server = "404 Was Not Found";
        else if (status == 500)
            this.status_server = "500 Internal Server Error";
    }

    @Override
    public String getStatus() {
        if (this.status_code == 0)
            throw new IllegalArgumentException("null");
        else
            return this.status_server;
    }

    @Override
    public void addHeader(String header, String value) {
        this.myheader.put(header, value);
    }

    @Override
    public String getServerHeader() {
        return this.server_header;
    }

    @Override
    public void setServerHeader(String server) {
        this.myheader.put("Server", server);
    }

    @Override
    public void setContent(String content) {
        this.content = content.getBytes();
        this.content_length = this.content.length;
    }

    @Override
    public void setContent(byte[] content) {
        this.content=content;
        this.content_length=this.content.length;
    }

    @Override
    public void setContent(InputStream stream) {
        try{
            this.content = stream.readAllBytes();
            this.content_length=content.length;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void send(OutputStream network) {

    }
}
