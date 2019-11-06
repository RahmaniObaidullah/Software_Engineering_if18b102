package at.technikum.rh.Unit_Test;

import at.technikum.rh.main_programs.request.CreateRequest_test;
import at.technikum.rh.main_programs.request.Request_Class;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestClass_Test {

    private Request_Class request;
    private String test_url = "https://example.com/root/desktop/test.php?a=1&b=2&c=3#aa";

    /**               Header                  **/
    @Test
    void getHeaderCount() throws Exception {
        request = new Request_Class(CreateRequest_test.getValidRequestStream(test_url));
        assertEquals(request.getHeaders().size(), request.getHeaderCount());
    }

    /**               Content                  **/
    @Test
    void getContentLength() throws Exception {
        request = new Request_Class(CreateRequest_test.getValidRequestStream(test_url, "GET", "hello world"));
        assertEquals(11, request.getContentLength());
    }

    @Test
    void getContentType() throws Exception {
        request = new Request_Class(CreateRequest_test.getValidRequestStream(test_url, "GET", "hello world"));
        assertEquals(" application/x-www-form-urlencoded", request.getContentType());
    }

    @Test
    void getContentStream() throws Exception {
        InputStream inpStream = CreateRequest_test.getValidRequestStream(test_url, "GET", "hello world");
        request = new Request_Class(inpStream);
        assertEquals(inpStream, request.getContentStream());
    }

    @Test
    void getContentString() throws Exception {
        InputStream inputStream = CreateRequest_test.getValidRequestStream(test_url, "GET", "hello world");
        request = new Request_Class(inputStream);
        assertEquals(IOUtils.toString(inputStream, String.valueOf(StandardCharsets.UTF_8)), request.getContentString());
    }

    @Test
    void getContentBytes() throws Exception {
        InputStream inpStream = CreateRequest_test.getValidRequestStream(test_url, "GET", "hello world");
        request = new Request_Class(inpStream);
        assertEquals(IOUtils.toByteArray(inpStream), request.getContentBytes());
    }
    /**               URL                  **/
    @Test
    void getUrl() throws Exception {
        request = new Request_Class(CreateRequest_test.getValidRequestStream(test_url));
        assertEquals(test_url, request.getUrl().getRawUrl());
    }
    /**               other functions                  **/
    @Test
    void getUserAgent() throws Exception {
        request = new Request_Class(CreateRequest_test.getValidRequestStream(test_url));
        assertEquals(" Unit-Test-Agent/1.0", request.getUserAgent());
    }
    @Test
    void isValid() throws Exception {
        request = new Request_Class(CreateRequest_test.getValidRequestStream(test_url));
        assertTrue(request.isValid());
    }

    @Test
    void getMethod() throws Exception {
        request = new Request_Class(CreateRequest_test.getValidRequestStream(test_url));
        assertEquals("GET", request.getMethod());
    }

}
