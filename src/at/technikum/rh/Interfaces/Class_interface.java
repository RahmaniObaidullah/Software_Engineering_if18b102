
package at.technikum.rh.Interfaces;

import java.util.Map;

public class Class_interface implements Url {
    private String url;
    @Override
    public String getRawUrl() {
        return url;

    }
    @Override
    public String getPath() {
        int tx = url.indexOf("/");
        int ty = url.indexOf("//");
        int tz = url.indexOf("?");
        if (tx < 0) return "";
        else{
        if (ty > 0 && tz > 0){
            String[] without_parameter= url.split("\\?");
            String[] without_https = without_parameter[0].split("//");
            String[] path_both = without_https[1].split("/", 2);
            return "/"+path_both[1];
        }
        else if(url.indexOf("//") > 0){
            String[] new_url = url.split("//");
            String[] path = new_url[1].split("/", 2);
            return "/" + path[1];
        }
        else if(url.indexOf("?") > 0){
            String[] without_parameter= url.split("\\?");
            String[] path_b = without_parameter[0].split("/", 2);
            return "/"+path_b[1];
        }
            return url;
        }
    }

    //Nicht
    @Override
    public Map<String, String> getParameter() {
        return null;
    }

    /*
    @Override
    public String getParameter(){
        String[] rawurl = url.split("\\?");
        String parameter_a = rawurl[1];
        String[] parameter_b = parameter_a.split("#");
        return parameter_b[1];
    }
    */
    @Override
    public int getParameterCount() {
        //int t = url.indexOf("\\?");
        String[] rawurl = url.split("\\?");
        if (rawurl.length>=2){
            String [] parameter = rawurl[1].split("=");
            int count = parameter.length-1;
            return count;
        }
        else{
            return 0;
        }
    }
    @Override
    public String[] getSegments() {
        int tx = url.indexOf("/");
        int ty = url.indexOf("//");
        int tz = url.indexOf("?");
            if (ty > 0 && tz > 0) {
                String[] without_parameter = url.split("\\?");
                String[] without_https = without_parameter[0].split("//");
                String[] path_both = without_https[1].split("/", 2);
                return path_both;
            } else if (url.indexOf("//") > 0) {
                String[] new_url = url.split("//");
                String[] path = new_url[1].split("/");
                return path;
            } else if (url.indexOf("?") > 0) {
                String[] without_parameter = url.split("\\?");
                String[] path_b = without_parameter[0].split("/");
                return path_b;
            }
            else{
                String[] path1 ={"Antwort"};
                return path1;
            }
    }
    @Override
    public String getFileName() {
        String ext = url.substring(url.lastIndexOf('/'));
        int index = ext.indexOf(".");
        String file = "";
        String[] et;
        if (index <= 0) return "";
        else {
            if (ext.contains("?")) {
                file = ext.substring(0, ext.indexOf("?"));
                et = file.split("/");
                return et[1];
            }
            et = ext.split("/");
            return et[1];
        }
    }
    @Override
    public String getExtension() {
        String ext1 = url.substring(url.lastIndexOf('/'));
        int index = ext1.indexOf(".");
        if (index <= 0) return "";
        else {
            String ext = url.substring(url.lastIndexOf('.'));
            if (ext.contains("?")) {
                return ext.substring(0, ext.indexOf('?'));
            }
            return ext;
        }
    }
    @Override
    public String getFragment() {
        int t = url.indexOf("#");
        if (t < 0) {
            return "";
        } else {
            String[] fragment = url.split("#");
            return fragment[1];
        }
    }

    public Class_interface(String url){
            this.url=url;
    }
    public static void main(String[] args){
        System.out.println("Test");
    }
}