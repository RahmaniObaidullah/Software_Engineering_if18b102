package at.technikum.rh.Unit_Test;

import at.technikum.rh.main_programs.request.Request_Class;

public class RequestClass_Test {

    private Request_Class request;
    private String test_url = "https://example.com/root/desktop/test.php?a=1&b=2&c=3#aa";

    void isValid() throws Exception {
        request = new Request_Class();
    }
}
