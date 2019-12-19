package at.technikum.rh.main_programs.Plugin;

import at.technikum.rh.Interfaces.Plugin;
import at.technikum.rh.main_programs.request.Request_Class;
import at.technikum.rh.main_programs.response.Response_Class;

import java.sql.*;

public class NewTemperaturPlugin implements Plugin {

    @Override
    public float canHandle(Request_Class req) {

        if(req.getUrl().getRawUrl().startsWith("/temp")) return (float) 1;
        return 0;
    }

    @Override
    public Response_Class handle(Request_Class req) {
        Response_Class _resp = new Response_Class();

        if(req.getUrl().getRawUrl().matches("/temp/\\d\\d\\d\\d/\\d\\d/\\d\\d/?.*") == true){
            String time = req.getUrl().getRawUrl().split("[/|?]")[2] + "-" +
                    req.getUrl().getRawUrl().split("[/|?]")[3] + "-" +
                    req.getUrl().getRawUrl().split("[/|?]")[4] + " 00:00:00";
            Timestamp ts = Timestamp.valueOf(time);
            _resp.setContent(getDbDataRest(ts));
        }
        else{
            int offset = 0;
            int limit = 0;
            try{
                offset = Integer.parseInt(req.getUrl().getParameter().get("offset"));
                if(offset < 0) offset = 0;
            } catch(Exception e){
                offset = 0;
            }
            try{
                limit = Integer.parseInt(req.getUrl().getParameter().get("limit"));
                if(limit < 0) limit = 0;
            } catch(Exception e){
                limit = 25;
            }
            System.out.println(getDbData(limit, offset));
            _resp.setContent("<html><body><h1>Temperatures:</h1><br>" + getDbData(limit, offset) + "</body></html>");
        }

        return _resp;
    }

    private String getDbData(int limit, int offset){
        StringBuilder res = new StringBuilder("<table border='1'><tr><td><a>ID&nbsp;&nbsp;</a></td><td><a>Temperature&nbsp;&nbsp;</a></td><td><a>Date&nbsp;&nbsp;</a></td></tr>");
        Connection c = null;
        PreparedStatement query = null;
        ResultSet resset = null;
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/temperaturdaten", "JavaUser", "abc123");
            query = c.prepareStatement("select * from temperatur_data order by datum desc limit ? offset ?;");
            query.setInt(1, limit);
            query.setInt(2, offset);

            //String sql = "select * from tempdata limit " + limit + " offset " + offset + ";";
            resset = query.executeQuery();

            int i = 0;
            while(resset.next()){
                if(i >= limit) break;
                res.append("<tr><td><a>");
                res.append(resset.getString("id"));
                res.append("</a></td><td><a>");
                res.append(resset.getString("temperatur"));
                res.append("</a></td><td><a>");
                res.append(resset.getString("datum"));
                res.append("</a></td></tr>");
                i++;
                //System.out.println("DB: " + res);
            }
            res.append("</table>");
            if(offset == 0){
                res.append("<a href='?offset=");
                res.append(offset + limit);
                res.append("'>next</a>");
            }
            else if(offset > 0){
                res.append("<a href='?offset=");
                res.append(offset - limit);
                res.append("'>prev</a>&nbsp;&nbsp;");
                res.append("<a href='?offset=");
                res.append(offset + limit);
                res.append("'>next</a>");
            }

            resset.close();
            query.close();
            c.close();
        } catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return res.toString();
    }

    private String getDbDataRest(Timestamp time){
        //StringBuilder res = new StringBuilder("<?xml version='1.0'?><temperatures>");
        StringBuilder res = new StringBuilder(
                "<table border='1'><tr><td><a>ID&nbsp;&nbsp;</a></td>" +
                        "<td><a>Temperature&nbsp;&nbsp;</a></td>" +
                        "<td><a>Date&nbsp;&nbsp;</a></td></tr>"
        );
        Connection c = null;
        PreparedStatement query = null;
        ResultSet resset = null;
        try{
            //Class.forName("org.postgresql.Driver");
            c =  c = DriverManager.getConnection("jdbc:mysql://localhost:3306/temperaturdaten", "JavaUser", "abc123");
            query = c.prepareStatement("select * from temperatur_data where datum >= ? and datum < ? order by datum desc;");
            query.setTimestamp(1, time);
            time.setTime(time.getTime() + (1000 * 60 * 60 * 24));
            query.setTimestamp(2, time);
            resset = query.executeQuery();

            while(resset.next()){
                res.append("<entry><id>");
                res.append(resset.getString("id"));
                res.append("</id><temp>");
                res.append(resset.getString("temperatur"));
                res.append("</temp><date>");
                res.append(resset.getString("datum"));
                res.append("</date></entry>");
            }
             /*
            while(resset.next()){
                res.append("<tr><td><a>");
                res.append(resset.getString("id"));
                res.append("</a></td><td><a>");
                res.append(resset.getString("temperatur"));
                res.append("</a></td><td><a>");
                res.append(resset.getString("datum"));
                res.append("</a></td></tr>");
            }
            */
             */
            res.append("</temperatur>");
            resset.close();
            query.close();
            c.close();
        } catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return res.toString();
    }
}