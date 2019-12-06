package at.technikum.rh.main_programs.Plugin;

import at.technikum.rh.Interfaces.Plugin;
import at.technikum.rh.Interfaces.Response;
import at.technikum.rh.main_programs.request.Request_Class;
import at.technikum.rh.main_programs.response.Response_Class;

import java.io.IOException;
import java.sql.*;

public class temperatur_plugin implements Plugin {
    @Override
    public float canHandle(Request_Class req) {
        if(req.getUrl().getRawUrl().startsWith("/temp")){
            return 1f;
        }
        else{
            return 0f;
        }
    }

    @Override
    public Response handle(Request_Class req) throws IOException {
        String _zeit; int of; int grenze;
        Response_Class _response_class = new Response_Class();
        if(req.getUrl().getRawUrl().matches("/temp/\\d\\d\\d\\d/\\d\\d/\\d\\d/?.*") == true){
            String a = req.getUrl().getRawUrl().split("[/|?]")[2];
            String b = req.getUrl().getRawUrl().split("[/|?]")[3];
            String c = req.getUrl().getRawUrl().split("[/|?]")[4];
            _zeit=a+"-"+b+"-"+c+"00:00:00";
            Timestamp timestamp = Timestamp.valueOf(_zeit);
            _response_class.setContent(getSQLData(timestamp));
        }else{
            try{
                of = Integer.parseInt(req.getUrl().getParameter().get("offset"));
                if (of < 0) of = 0;
            }catch(Exception e){
                of=0;
            }try{
                grenze = Integer.parseInt(req.getUrl().getParameter().get("limit"));
                if(grenze < 0) grenze = 0;
            }catch (Exception e){
                grenze=30;
            }
            System.out.println(getDaten(grenze, of));
            _response_class.setContent("<html>"
                    +"<body>"
                    +"<h1>Temperaturen: </h1>"
                    + "<br>\" + getData(limit, offset) + \"</body></html>");

        }
        System.out.println(" The Result: "+_response_class);
        return _response_class;
    }

    private String getSQLData(Timestamp time){
        StringBuilder stringBuilder = new StringBuilder("<?xml version='1.0'?><temperatur>");
        Connection connection = null; PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:4690/temp?user=JavaUser&password=abc123");
            preparedStatement = connection.prepareStatement("SELECT * from temperatur_data WHERE datum >= ? " +
                    "AND datum < ? ORDER BY datum desc;");
            preparedStatement.setTimestamp(1, time);
            time.setTime(time.getTime()+(1000*60*60*24));
            preparedStatement.setTimestamp(2, time);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                stringBuilder.append("<entry><id>");
                stringBuilder.append(resultSet.getString("id"));
                stringBuilder.append("</id><temp>");
                stringBuilder.append(resultSet.getString("temperatur"));
                stringBuilder.append("</temp><date>");
                stringBuilder.append(resultSet.getString("datum"));
                stringBuilder.append("</date></entry>");
                System.out.println("Die Datenbank Daten: "+stringBuilder);
            }
            stringBuilder.append("</Temperatur>");
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return resultSet.toString();
    }
    private String getDaten(int grenze, int off){
        StringBuilder stringBuilder = new StringBuilder("<table border='1'>"
                +"<tr><td>"
                + "<a>ID&nbsp;&nbsp;</a>"
                +"</td><td>"
                +"<a>Temperature&nbsp;&nbsp;</a>"
                +"</td><td>"
                +"<a>Date&nbsp;&nbsp;</a>"
                +"</td></tr>");
        Connection connection = null; PreparedStatement preparedStatement;
        ResultSet resultSet; int zaehler_variable = 0;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:4690/temp", "JavaUser", "abc123");
            preparedStatement = connection.prepareStatement("" +
                    "SELECT * from temperatur_data order by datum desc limit ? offset ?;");
            preparedStatement.setInt(1, grenze);
            preparedStatement.setInt(2, off);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if(zaehler_variable >= grenze) break;
                stringBuilder.append("<tr><td><a>");
                stringBuilder.append(resultSet.getString("id"));
                stringBuilder.append("</a></td><td><a>");
                stringBuilder.append(resultSet.getString("temperatur"));
                stringBuilder.append("</a></td><td><a>");
                stringBuilder.append(resultSet.getString("datum"));
                stringBuilder.append("</a></td></tr>");
                zaehler_variable++;
                System.out.println(" Die DB Daten sind "+stringBuilder);
            }
            stringBuilder.append("</table>");
            if(off > 0){
                stringBuilder.append("<a href='?offset=");
                stringBuilder.append(off-grenze);
                stringBuilder.append("'>prev</a>&nbsp;&nbsp;");
                stringBuilder.append("<a href='?offset=");
                stringBuilder.append(grenze+off);
                stringBuilder.append("'>next</a>");
            }
            else if (off == 0){
                stringBuilder.append("<a href='?offset=");
                stringBuilder.append(grenze+off);
                stringBuilder.append("'>next</a>");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            //System.out.println(e,getClass().getName()+" : "+e.getMessage());
        }
        return stringBuilder.toString();
    }
}
