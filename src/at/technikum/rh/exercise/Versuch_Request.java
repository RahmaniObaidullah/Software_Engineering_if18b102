package at.technikum.rh.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Versuch_Request {
    private String request_output;
    private BufferedReader br;
    private String[] parameter_output;
    private String body;

    /*
    private static void listen() throws IOException {
        Map<String,String> header = new HashMap<String, String>();
        ServerSocket listener = new ServerSocket(8080);
        Socket s = listener.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line;
        while ((line=in.readLine()) != null){
            System.out.println(line);
            String[] _parts = line.split(": ");
            header.put(_parts[0],_parts[1]);
            //header.put(_parts[0].toLowerCase(), _parts[1]);
        }

    }


     */
    /*
    private static void setHeader() throws IOException {
        Map<String,String> header = new HashMap<String, String>();
        ServerSocket listener = new ServerSocket(8080);
        Socket s = listener.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line;
        if(in != null){
            String _dash = in.readLine();
            if(_dash != null){
                while (!_dash.equals("")){
                    String _parts[] = _dash.split(": ");
                    header.put(_parts[0].toLowerCase(), _parts[1]);
                    for(String key : header.keySet())
                    {
                        System.out.print("Key: " + key + " - ");
                        System.out.print("Value: " + header.get(key) + "\n");
                    }
                    _dash = in.readLine();
                    if(!in.ready()){
                        break;
                    }
                }
            }
        }
    }

     */

    public static void main(String[] args) throws IOException {
        Map<String, String> header = new HashMap<String, String>();

        ServerSocket listener = new ServerSocket(8080);
        Socket s = listener.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        in.readLine();
        //String line;
        if (in != null) {
            String line = in.readLine();
            if (line != null) {
                while (!line.equals("")) {
                    String[] _parts = line.split(": ");
                    System.out.println(_parts.length);
                    //System.out.println("Value " + _parts[2]);
                    line = in.readLine();
                    if (!in.ready()) {
                        break;
                    }

                }
            }


            String get = "Key: abcbababababa";
            String[] get_a = get.split(": ");
            System.out.println(get_a[0] + " und value " + get_a[1]);
        }
    }
}


        /*
        for(String key : header.keySet())
        {
            System.out.println("Test");
            System.out.print("Key: " + key + " - ");
            System.out.print("Value: " + header.get(key) + "\n");
        }

         */
        //header.put(_parts[0].toLowerCase(), _parts[1]);




/*
private void setHeader() throws IOException {
        if(this.br != null){
            String _dash = this.br.readLine();
            if(_dash != null){
                while (!_dash.equals("")){
                    String _parts[] = _dash.split(": ");
                    this.header.put(_parts[0].toLowerCase(), _parts[1]);
                    _dash = br.readLine();
                    if(!br.ready()){
                        break;
                    }
                }
            }
        }
    }

 */
