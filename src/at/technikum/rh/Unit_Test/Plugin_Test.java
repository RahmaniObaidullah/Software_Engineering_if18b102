package at.technikum.rh.Unit_Test;


import at.technikum.rh.main_programs.Plugin.NaviPlugin;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class Plugin_Test {
    @Test
    public void test01() throws Exception {
        NaviPlugin plug = new NaviPlugin();
        String output = "GET /navi/parse HTTP/1.1\\nHost: localhost";
        ByteArrayInputStream in = new ByteArrayInputStream(output.getBytes());
        //Request_Class request_class = new Request_Class(output);
        //assertEquals(1.0, plug.canHandle(request_class),0);

    }
}
