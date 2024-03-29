package at.technikum.rh.Unit_Test;


import at.technikum.rh.main_programs.response.Response_Class;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class ResponseClass_Test {
//Test for Response
    private Response_Class response = new Response_Class();

    /**               Content                 **/
    @Test
    void getContent(){
        String _content_test="Test-Content";
        response.setContent(_content_test);
        assertEquals(_content_test.length(), response.getContentLength());
    }
    @Test
    void getContentType(){
        String _contentType_test = "html";
        response.setContent(_contentType_test);
        assertEquals(_contentType_test, response.getContentType());
    }
    /**               Header                  **/
    @Test
    void getHeader_Test1(){

        assertEquals(Map.of("Server_SW", "Rahmani-Obaidullah-Server"), response.getHeaders());
    }
    @Test
    void getHeader_Test2(){

        assertEquals(Map.of("Server_SW", "Rahmani-Obaidullah-Server"), response.getHeaders());
    }
    @Test
    void getServerHeader(){
        String myheader = "TestHeader";
        response.setServerHeader(myheader);
        assertEquals(Map.of("Server_SW", myheader), response.getHeaders());
    }
    /**               Status-test                  **/
    @Test
    void getStatusCode_test1(){
        int code_test = 200;
        response.setStatusCode(code_test);
        assertEquals(code_test, response.getStatusCode());
    }
    @Test
    void getStatusCode_test2(){
        int code_test = 404;
        response.setStatusCode(code_test);
        assertEquals(code_test, response.getStatusCode());
    }
    @Test
    void getStatusCode_test3(){
        int code_test = 500;
        response.setStatusCode(code_test);
        assertEquals(code_test, response.getStatusCode());
    }
    @Test
    void getStatus_test1() {
        int code_test = 200;
        String status = "200 OK";
        response.setStatusCode(code_test);
        assertEquals(status, response.getStatus());
    }
}
