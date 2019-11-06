package at.technikum.rh.main_programs.request;



import at.technikum.rh.Interfaces.Request;
import at.technikum.rh.Interfaces.Url;
import at.technikum.rh.main_programs.url.Class_interface;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Request_Class implements Request {
    private String httpversion;
    private String methode;
    //Class for URL
    private Class_interface UrlClass = new Class_interface("/");
    private InputStream inputStream;
    private Map<String,String> myheader = new HashMap<>();

    private void header_InputStream() throws IOException {
        String line; String[] header_segments; String new_line;
        //getBytes for UTF_8
        BufferedReader br = new BufferedReader(new InputStreamReader(this.inputStream, StandardCharsets.UTF_8));
        line = br.readLine();

        if(line==null)  throw new IllegalStateException();

        //first line Header GET /URL HTTP/1.0
        header_segments = line.split(" ", 3);
        //URL in Class URL
        UrlClass = new Class_interface(header_segments[1]);
        //Used HTTP-Version
        httpversion = header_segments[2];
        //GET UpperCase
        methode = header_segments[0].toUpperCase();

        while ((new_line = br.readLine()) != null){
            //Header without the first line
            String[] header = new_line.split(":", 2);
            myheader.put(header[0].toLowerCase(), header[1]);
        }
    }


    public Request_Class(InputStream inputStream) throws Exception{
        this.inputStream = inputStream;
        header_InputStream();
    }

    /**
     * @return Returns true if the request is valid. A request is valid, if
     * method and url could be parsed. A header is not necessary.
     */

    @Override
    public boolean isValid() {
        if(methode.length() > 2){
            for(MethodOfHttp moh : MethodOfHttp.values()){
                if(moh.name().equals(methode)){
                    return true;
                }
            }
        }
        else if (methode.length() != 3){
            return false;
        }
        return false;
    }


    /**
     * @return Returns the request method in UPPERCASE. get -> GET
     */

    @Override
    public String getMethod() {

        return this.isValid() ? this.methode : null;
    }

    /**
     * @return Returns a URL object of the request. Never returns null.
     */
    @Override
    public Url getUrl() {
        if(this.isValid()){
            return this.UrlClass;
        }
        //return " ";
        return this.UrlClass;
        /*
        if(this.isValid()){
            return new Class_interface(UrlClass);
        }
        return "";
         */
    }

    /**
     * @return Returns the request header. Never returns null. All keys must be
     * lower case.
     */

    @Override
    public Map<String, String> getHeaders() {
        return this.myheader;
    }

    /**
     * @return Returns the number of header or 0, if no header where found.
     */
    @Override
    public int getHeaderCount() {
        return this.myheader.size();
    }

    /**
     * @return Returns the user agent from the request header
     */
    @Override
    public String getUserAgent() {
        //return this.myheader.getOrDefault("User-Agent", null);
        if(this.myheader.size() > 0){
            return this.myheader.getOrDefault("User-Agent", null);
        }
        return null;
    }

    /**
     * @return Returns the parsed content length request header. Never returns
     * null.
     */
    @Override
    public int getContentLength() {
        return Integer.parseInt(this.myheader.get("Content-Length"));
    }

    /**
     * @return Returns the parsed content type request header. Never returns
     * null.
     */
    @Override
    public String getContentType() {
        if(this.myheader.size() > 0){
            return this.myheader.get("Content-Type");
        }
        return "";
    }
    /**
     * @return Returns the request content (body) stream or null if there is no
     * content stream.
     */
    @Override
    public InputStream getContentStream() {
        return this.inputStream;
    }
    /**
     * @return Returns the request content (body) as string or null if there is
     * no content.
     */
    @Override
    public String getContentString() throws IOException{
        return this.inputStream != null ? IOUtils.toString(this.inputStream, String.valueOf(StandardCharsets.UTF_8)) : null;
    }
    /**
     * @return Returns the request content (body) as byte[] or null if there is
     * no content.
     */
    @Override
    public byte[] getContentBytes() throws IOException{
        return IOUtils.toByteArray(this.inputStream);
    }
}
