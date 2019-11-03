package at.technikum.rh.Unit_Test;

import at.technikum.rh.main_programs.url.Class_interface;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Class_interfaceTest {

    @Test
    public void raw_url_test_1() {
        Class_interface object = new Class_interface("https://example.com/root/desktop/test.php?a=1&b=2&c=3#aa");
        String output = object.getRawUrl();
        //Assertions.assertNotNull("", object);
        Assertions.assertEquals("https://example.com/root/desktop/test.php?a=1&b=2&c=3#aa", output);
    }

    @Test
    public void raw_url_test_2() {
        Class_interface object = new Class_interface("www.example.com/root/desktop/test.php");
        String output = object.getRawUrl();
        //Assertions.assertNotNull("Wrong result", object);
        Assertions.assertEquals("www.example.com/root/desktop/test.php", output);
    }

    @Test
    public void raw_url_test_3() {
        Class_interface object = new Class_interface("/root/desktop/test.php");
        String output = object.getRawUrl();
        //Assertions.assertNotNull("Wrong result", object);
        Assertions.assertEquals("/root/desktop/test.php", output);
    }

    @Test
    public void raw_url_test_4() {
        Class_interface object = new Class_interface("/desktop/test.php?a=1&b=2&c=3#aa");
        String output = object.getRawUrl();
        //Assertions.assertNotNull("Wrong result", object);
        Assertions.assertEquals("/desktop/test.php?a=1&b=2&c=3#aa", output);
    }

    //Parameter function
    @Test
    public void path_test_1() {
        Class_interface object = new Class_interface("https://example.com/root/desktop/test.php?a=1&b=2&c=3#aa");
        String output = object.getPath();
        Assertions.assertEquals("/root/desktop/test.php", output);
    }
    @Test
    public void path_test_2() {
        Class_interface object = new Class_interface("/desktop/test.php?a=1&b=2&c=3#aa");
        String output = object.getPath();
        Assertions.assertEquals("/desktop/test.php", output);
    }

    @Test
    public void path_test_3() {
        Class_interface object = new Class_interface("www.example.com/desktop/test.php?a=1&b=2&c=3#aa");
        String output = object.getPath();
        Assertions.assertEquals("/desktop/test.php", output);
    }

    @Test
    public void path_test_4() {
        Class_interface object = new Class_interface("/test.php?a=1#aa");
        String output = object.getPath();
        Assertions.assertEquals("/test.php", output);
    }

    @Test
    public void path_test_5() {
        Class_interface object = new Class_interface("/root/desktop/test/");
        String output = object.getPath();
        Assertions.assertEquals("/root/desktop/test/", output);
    }

    //Funktion getParameterCount
    @Test
    public void parameterCount_test_1() {
        Class_interface object = new Class_interface("/test.php?a=1#aa");
        int output = object.getParameterCount();
        Assertions.assertEquals(1, output);
    }

    @Test
    public void parameterCount_test_2() {
        Class_interface object = new Class_interface("www.example.com/desktop/test.php?a=1&b=2&c=3#aa");
        int output = object.getParameterCount();
        Assertions.assertEquals(3, output);
    }

    @Test
    public void parameterCount_test_3() {
        Class_interface object = new Class_interface("/desktop/test.php?a=1&b=2#aa");
        int output = object.getParameterCount();
        Assertions.assertEquals(2, output);
    }

    @Test
    public void parameterCount_test_4() {
        Class_interface object = new Class_interface("test.php?a=1#aa");
        int output = object.getParameterCount();
        Assertions.assertEquals(1, output);
    }

    @Test
    public void parameterCount_test_5() {
        Class_interface object = new Class_interface("www.example.com/desktop/test.php");
        int output = object.getParameterCount();
        Assertions.assertEquals(0, output);
    }

    //Function getfilename
    @Test
    public void getFileName_test_1() {
        Class_interface object = new Class_interface("www.example.com/desktop/test.php?");
        String output = object.getFileName();
        Assertions.assertEquals("test.php", output);
    }

    @Test
    public void getFileName_test_2() {
        Class_interface object = new Class_interface("https://www.example.com/desktop/test.php");
        String output = object.getFileName();
        Assertions.assertEquals("test.php", output);
    }

    @Test
    public void getFileName_test_3() {
        Class_interface object = new Class_interface("www.example.com/desktop/test/");
        String output = object.getFileName();
        Assertions.assertEquals("", output);
    }

    @Test
    public void getFileName_test_4() {
        Class_interface object = new Class_interface("www.example.com/desktop/test.php?a=1&b=2&c=3#aa");
        String output = object.getFileName();
        Assertions.assertEquals("test.php", output);
    }

    //Function getExtension
    @Test
    void getExtension_test_01() {
        Class_interface url = new Class_interface("https://example.com/root/desktop/test?a=1&b=2&c=3#aa");
        String ext = url.getExtension();
        Assertions.assertEquals("", ext);
    }

    @Test
    void getExtension_test_02() {
        Class_interface url = new Class_interface("www.example.com/desktop/test/");
        String ext = url.getExtension();
        Assertions.assertEquals("", ext);
    }

    @Test
    void getExtension_test_03() {
        Class_interface url = new Class_interface("www.example.com/desktop/test.php");
        String ext = url.getExtension();
        Assertions.assertEquals(".php", ext);
    }

    @Test
    void getExtension_test_04() {
        Class_interface url = new Class_interface("https://example.com/root/desktop/test?");
        String ext = url.getExtension();
        Assertions.assertEquals("", ext);
    }

    //Funktion getFragment
    @Test
    void getFragment_test_01() {
        Class_interface url = new Class_interface("https://example.com");
        String ext = url.getFragment();
        Assertions.assertEquals("", ext);
    }

    @Test
    void getFragment_test_02() {
        Class_interface url = new Class_interface("https://example.com/root/desktop/test?a=1&b=2&c=3#aa");
        String ext = url.getFragment();
        Assertions.assertEquals("aa", ext);
    }

    @Test
    void getFragment_test_03() {
        Class_interface url = new Class_interface("/test?a=1&b=2&c=3#aa");
        String ext = url.getFragment();
        Assertions.assertEquals("aa", ext);
    }

    //Segment
    @Test
    void getSegment_test_01() {
        Class_interface url = new Class_interface("https://example.com/root/desktop/Ordner/test.php?a=1&b=2&c=3");
        String[] ext = url.getSegments();
        String[] answer = {"root", "desktop", "test.php"};
        Assert.assertArrayEquals(new String[] {"root","desktop", "Ordner"} ,ext);
        //Assert.assertArrayEquals(answer,ext);
        //Assertions.assertEquals(ext.Seq
    }
    @Test
    void getParameter_test_01() {
        Class_interface url = new Class_interface("https://example.com/root/desktop/test?a=1&b=2&c=3");
        String ext = url.getParameter().get("a");
        Assertions.assertEquals("1", ext);

        String ext1 = url.getParameter().get("b");
        Assertions.assertEquals("2",ext1);

        String ext2 = url.getParameter().get("c");
        Assertions.assertEquals("3", ext2);
    }


    @Test
    void getParameter_test_02() {
        Class_interface url = new Class_interface("/test?x=1&y=2&z=3");
        String ext = url.getParameter().get("x");
        Assertions.assertEquals("1", ext);

        String ext1 = url.getParameter().get("y");
        Assertions.assertEquals("2",ext1);

        String ext2 = url.getParameter().get("z");
        Assertions.assertEquals("3", ext2);


    }



}



