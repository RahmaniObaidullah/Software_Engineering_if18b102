package at.technikum.rh.Unit_Test;


import at.technikum.rh.Unit_Test.TestFile.MySQLAccess;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class Plugin_Test {

    @BeforeClass
    public static void initialize() throws SQLException,ClassNotFoundException{
        MySQLAccess mySQLAccess = MySQLAccess.newInstance();
        mySQLAccess.initializeConnection();
    }
    @AfterClass
    public static void closeConnection() throws SQLException {
        MySQLAccess mySQLAccess = MySQLAccess.newInstance();
        mySQLAccess.closeAllConnections();
    }

    @Test
    public void test01() throws Exception {
        File file = new File(
                "/Users/Obaidullah/IdeaProjects/Uebung_Vorlesung_1/src/at/technikum/rh/myFiles/Broadcast1.png"
        );
        boolean exists = file.exists();
        boolean directory = file.isDirectory();
        boolean is_file = file.isFile();
        assertEquals(false,exists);
        assertEquals(false, directory);
        assertEquals(false,is_file);
    }
    @Test
    public void test02() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("myFiles/index.html");
        final StringBuilder path = new StringBuilder();
        //path.append("file:" +SystemUtil.USER)
    }
}
