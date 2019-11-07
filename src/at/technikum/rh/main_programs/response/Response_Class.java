package at.technikum.rh.main_programs.response;
//Response
import at.technikum.rh.Interfaces.Response;

import java.io.IOException;
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
        return this.content_type;
        //return myheader.get("content-type");
    }

    @Override
    public void setContentType(String contentType) {
        myheader.put("Content-Type", contentType);
        this.content_type = contentType;
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
        /** 200 Statuscode */
        if(status == 200)
            this.status_server = "200 OK";
        else if(status == 202)
            this.status_server = "202 Accepted";
        else if (status == 205)
            this.status_server = "205 Reset Content";
        else if (status == 207)
            this.status_server = "207 Multi-Status";
        else if (status == 226)
            this.status_server = "IM Used";
        /** 300 Statuscode */
        else if(status == 300)
            this.status_server = "300 Multiple Choices";
        else if (status == 304)
            this.status_server = "304 Not Modified";
        /** 400 Statuscode */
        else if(status == 400)
            this.status_server = "400 Bad Request\t";
        else if (status == 401)
            this.status_server = "401 Unauthorized";
        else if (status == 404)
            this.status_server = "404 Was Not Found";
        else if (status == 408)
            this.status_server = "408 Request Timeout\t";
        /** 500 Statuscode */
        else if (status == 500)
            this.status_server = "500 Internal Server Error";
        else if (status == 502)
            this.status_server = "502 Bad Gateway";
        else if (status == 505)
            this.status_server = "505 HTTP Version not supported";
        else if (status == 511)
            this.status_server = "511 Network Authentication Required";
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
        this.myheader.put("Server_SW", server);
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
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void send(OutputStream network) {
        try{
            if(this.status_server == null || this.content.length == 0)
                throw new IllegalStateException("No contend or status code set.");
            StringBuilder responseHeader = new StringBuilder();
            responseHeader.append("HTTP/1.1").append(getStatus()).append("\n");
            responseHeader.append("Content-Length: ").append(getContentLength()).append("\n");

            for (Map.Entry<String,String> entry: myheader.entrySet()){
                responseHeader.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            responseHeader.append("\n");

            network.write(responseHeader.toString().getBytes());
            network.write(content);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
