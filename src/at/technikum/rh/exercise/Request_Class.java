package at.technikum.rh.exercise;
/*
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Request_Class implements Request {

    private String request_output;
    private BufferedReader br;
    private String[] parameter_output;
    private Map<String,String> header = new HashMap<String, String>();
    private String body;

    private static void listen() throws IOException {
        ServerSocket listener = new ServerSocket(8080);
        Socket s = listener.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line;
        while ((line=in.readLine()) != null){
            System.out.println(line);
        }
    }

    private void setHeader() throws IOException {
        ServerSocket listener = new ServerSocket(8080);
        Socket s = listener.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line;
        if(in != null){
            String _dash = in.readLine();
            if(_dash != null){
                while (!_dash.equals("")){
                    String _parts[] = _dash.split(": ");
                   this.header.put(_parts[0].toLowerCase(), _parts[1]);
                    _dash = in.readLine();
                    if(!in.ready()){
                        break;
                    }
                }
            }
        }
    }
    private void setBody() {
    //    boolean _postmethode = isEqualTo(HttpMethods.class, getMethod(), HttpMethods.POST);
    }

    private void setParameter() throws IOException {
        this.request_output = br.readLine();
        this.parameter_output=request_output.split("[ ]");
    }

    private synchronized void parseRequest(){
        try {
            setParameter();
            setHeader();
            setBody();
        } catch (IOException e){
            System.out.println("Fehler bei ausfueheren von SET Methoden passiert");
        }
    }

    public Request_Class(InputStream in, String body){
        this.br = new BufferedReader(new InputStreamReader(in));
        this.body = body;
    }



    @Override
    public boolean isValid() {
        if(parameter_output.length != 3){
            return false;
        }
        else {
            return true;
        }
    }



    @Override
    public String getMethod() {
        if(this.isValid()) return parameter_output[0];
        return null;
    }



    @Override
    public Url getUrl() {
        if(this.isValid()){
            return new Class_interface(parameter_output[1]);
        }
        return "";
    }



    @Override
    public void getHeaders() {
        return ;
                //Ensurer.ensureNotNull(header, "Headers");
    }


    @Override
    public int getHeaderCount() {
        return header.size();
    }



    @Override
    public String getUserAgent() {
        if(header.size() > 0){
            return header.get("user-agent");
        }
        return null;
    }


    @Override
    public int getContentLength() {
        return Integer.parseInt(header.get("content-length"));
    }


    @Override
    public String getContentType() {
        if(header.size() > 0){
            return header.get("content-type");
        }
        return null;
    }


    @Override
    public InputStream getContentStream() {
        if(body != null){
            return new ByteArrayInputStream(body.getBytes());
        }
        return null;
    }


    @Override
    public String getContentString() {
        if(body != null){
            return body;
        }
        return null;
    }




    @Override
    public byte[] getContentBytes() {
        byte[] _contentBytes = new byte[0];
        try {
            _contentBytes = body.getBytes("UTF-8");
        }catch (Exception e){
            System.out.println("Fehler ist passiert");
        }
        return _contentBytes;
    }
}

 */
