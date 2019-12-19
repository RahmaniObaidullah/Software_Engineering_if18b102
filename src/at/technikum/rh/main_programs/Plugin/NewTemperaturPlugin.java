package at.technikum.rh.main_programs.Plugin;

import at.technikum.rh.Interfaces.Plugin;
import at.technikum.rh.main_programs.request.Request_Class;
import at.technikum.rh.main_programs.response.Response_Class;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;

public class NewTemperaturPlugin implements Plugin {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /**
     * Returns a score between 0 and 1 to indicate that the plugin is willing to
     * handle the request. The plugin with the highest score will execute the
     * request.
     *
     * @param req
     * @return A score between 0 and 1
     */
    @Override
    public float canHandle(Request_Class req) throws Exception {
        if (req.isValid()) {
            if (req.UrlClass.getRawUrl().startsWith("/temp")) {
                System.out.println("TEMPERATURE PLUGIN VALID");
                return 1.0f;
            }
        }
        return 0.0f;
    }

    /**
     * Called by the server when the plugin should handle the request.
     *
     * @param req
     * @return A new response object.
     */
    @Override
    public Response_Class handle(Request_Class req) throws IOException, ParserConfigurationException, SAXException, SQLException {
        Response_Class response = new Response_Class();

        if (req.getUrl().getRawUrl().matches("/temp/\\d\\d\\d\\d/\\d\\d/\\d\\d")) {
            String time =
                    req.getUrl().getRawUrl().split("[/|?]")[2] + "-" +
                            req.getUrl().getRawUrl().split("[/|?]")[3] + "-" +
                            req.getUrl().getRawUrl().split("[/|?]")[4] + " 00:00:00";

            Timestamp ts = Timestamp.valueOf(time);
            response.setContent(getDatabasaDataXML(ts));
        } else if (req.getUrl().getRawUrl().startsWith("/temp/search")) {
            System.out.println("HIER SUCHE: " + req.getUrl().getParameter().get("date"));
            String date = req.getUrl().getParameter().get("date");
            Timestamp ts = Timestamp.valueOf(date + " 00:00:00");

            response.setContent("<html><body>" +
                    "<h1>Temperatures:</h1>" +
                    "<form action=\"/temp/search\">\n" +
                    "  <input type=\"date\" name=\"date\" value=" + date + ">\n" +
                    "  <input type=\"submit\" value=\"Search\">\n" +
                    "</form> " +
                    "<form action=\"/temp\">\n" +
                    "  <input type=\"submit\" value=\"Overview\">\n" +
                    "</form> " +
                    "" +
                    "" +
                    "<br>" + getDatabaseDataByDate(ts) + "</body></html>");
        } else {
            int offset = 0;
            int limit = 10;

            String param = null;

            try {
                param = req.getUrl().getParameter().get("offset");
                if (param != null) {
                    offset = Integer.parseInt(req.getUrl().getParameter().get("offset"));
                    if (offset < 0) offset = 0;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                param = req.getUrl().getParameter().get("limit");
                if (param != null) {
                    limit = Integer.parseInt(req.getUrl().getParameter().get("limit"));
                    if (limit < 0) limit = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("RAWURL: " + req.getUrl().getRawUrl());

            response.setContent("<html><body>" +
                    "<h1>Temperatures:</h1>" +
                    "<form action=\"/temp/search\">\n" +
                    "  Search date:<br>\n" +
                    "  <input type=\"date\" name=\"date\" value=\"2019-12-05\">\n" +
                    "  <input type=\"submit\" value=\"Submit\">\n" +
                    "</form> " +
                    "" +
                    "" +
                    "<br>" + getDatabaseData(limit, offset) + "</body></html>");
        }

        return response;
    }


    private String getDatabaseDataByDate(Timestamp date) {
        StringBuilder res = new StringBuilder(
                "<table border='1'><tr><td><a>Date&nbsp;&nbsp;</a></td><td><a>Temperature&nbsp;&nbsp;</a></td></tr>");

        try {
            Connection conn = getDbConnection();
            PreparedStatement query = conn.prepareStatement("SELECT * FROM temperatur_data WHERE date = ? ORDER BY date DESC;");
            query.setTimestamp(1, date);

            ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {
                res.append("<tr><td><a>");
                res.append(resultSet.getString("datum"));
                res.append("</a></td><td><a>");
                res.append(resultSet.getString("temperatur"));
                res.append("</a></td></tr>");
            }
            res.append("</table>");

            resultSet.close();
            query.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    private String getDatabaseData(int limit, int offset) {
        StringBuilder res = new StringBuilder(
                "<table border='1'><tr><td><a>Date&nbsp;&nbsp;</a></td><td><a>Temperature&nbsp;&nbsp;</a></td></tr>");

        try {
            Connection conn = getDbConnection();
            PreparedStatement query = conn.prepareStatement("select * from temperatur_data order by date desc limit ? offset ?");
            query.setInt(1, limit);
            query.setInt(2, offset);
            ResultSet resultSet = query.executeQuery();

            int i = 0;
            while (resultSet.next()) {
                if (i >= limit) break;
                res.append("<tr><td><a>");
                res.append(resultSet.getString("datum"));
                res.append("</a></td><td><a>");
                res.append(resultSet.getString("temperatur"));
                res.append("</a></td></tr>");
                i++;
            }

            res.append("</table>");

            if (offset == 0) {
                res.append("<a href='?offset=");
                res.append(offset + limit);
                res.append("'>next</a>");
            } else if (offset > 0) {
                res.append("<a href='?offset=");
                res.append(offset - limit);
                res.append("'>prev</a>&nbsp;&nbsp;");
                res.append("<a href='?offset=");
                res.append(offset + limit);
                res.append("'>next</a>");
            }

            resultSet.close();
            query.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res.toString();
    }

    private Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tempplugin", "JavaUser", "abc123");
    }

    private String getDatabasaDataXML(Timestamp time) {
        StringBuilder res = new StringBuilder("<?xml version='1.0'?><temperatures>");

        try {
            Connection conn = getDbConnection();

            PreparedStatement query = conn.prepareStatement("SELECT * FROM temperatur_data WHERE date = ? ORDER BY date DESC;");
            query.setTimestamp(1, time);

            ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {
                res.append("<entry><daytemperature>");
                res.append(resultSet.getString("temperatur"));
                res.append("</daytemperature><date>");
                res.append(resultSet.getString("datum"));
                res.append("</date></entry>");
            }
            res.append("</temperatures>");

            resultSet.close();
            query.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toString();
    }
}
    /*
    @Override
    public float canHandle(Request_Class req) {

        if(req.getUrl().getRawUrl().startsWith("/temp")) return (float) 1;
        return 0;
    }

    @Override
    public Response handle(Request_Class req) {
        Response _resp = new Response_Class();

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
            //Class.forName("org.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:5432/temp", "JavaUser", "abc123");
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
        StringBuilder res = new StringBuilder("<?xml version='1.0'?><temperatures>");
        Connection c = null;
        PreparedStatement query = null;
        ResultSet resset = null;
        try{
            //Class.forName("org.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:5432/temp", "JavaUser", "abc123");
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
                //System.out.println("DB: " + res);
            }
            res.append("</temperatures>");

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
*/