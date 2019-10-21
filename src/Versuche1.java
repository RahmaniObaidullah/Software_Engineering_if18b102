public class Versuche1 {

    public static void main (String[] args){
        /*
        String phone = "012-3456789";
        String[] output = phone.split("-");
        System.out.println(output[0]);
        String b= "https://example.org/lala/lala/lala/asdas/asdas/asdas/?a=1&b=2&c=3&d=4&c=5";
        System.out.println(b);
        String[] a = b.split("\\?");
        System.out.println(a[0]);
        String [] parameter = a[1].split("=");
        for (int i = 0; i < parameter.length; i++){
            System.out.println(parameter[i]);
        }
        int anzahl = parameter.length;
        System.out.println(anzahl-1);
        String [] c = a[0].split("/");
        for (int i = 0; i < c.length; i++){
            System.out.println(c[i]);
        }
         */
        String my_pfad= "https://example.org/lala/lala/lala/asdas/asdas/asdas/hahah/index.php/?a=1&b=2&c=3&d=4" +
                "#sdfsdfsdfsdfsf";
        String[] only_url = my_pfad.split("\\?");
        System.out.println(only_url[0]);
        String[] http = only_url[0].split("//");
        System.out.println(http[0]+" my protocol");
        String[] website = http[1].split("/");
        System.out.println(website[0] +" my website");
        String[] my_pfad1 = http[1].split("/",2);
        System.out.println("..............."+my_pfad1[1].toString());
        for(int i=1; i<my_pfad1.length; i++){
            System.out.print(my_pfad1[i]+"/");
        }
        String segmente = only_url[1];
        System.out.println("\n Parameter"+segmente);
        String test = null;
        if(test == null){
            System.out.println("nichts ist drinnen");
        }
        else{
            System.out.println("Es ist was drinnen");
        }
        String[] regex = http[1].split("/", 2);
        System.out.println("-----");
        String antwort= null;
        for(int i=1; i<regex.length; i++){
            String antwort1 = regex[i];
            antwort=antwort+antwort1;

        }
        System.out.println("aaa"+regex[1]);
        String[] segments_without = regex[1].split("/");
        System.out.println(".............."+segments_without[0]);
        System.out.println(antwort);
        System.out.println("Parameter----------");
        String parameter_a = only_url[1];
        System.out.println(parameter_a);
        String[] parameter_b = parameter_a.split("#");
        //int counter = parameter_b[0].indexOf("=", 2);
        /*
        for(int i=0; (i=parameter_b[0].indexOf("=",i)) != -1;){
            int counter = counter+1;
        }
        */
        String[] parameter_count = parameter_b[0].split("=");
        int count = parameter_count.length-1;
        System.out.println(count);
        System.out.println(parameter_b[0]);
        System.out.println("--------filname------------");
        String[] filename = regex[1].split("/");
        int g = filename.length-1;
        System.out.println(filename[g]);
        String[] extension;
        String extension_output=null;
        int z = filename[g].indexOf(".");
        System.out.println(z);
        if(z >= 0){
            extension = filename[g].split("\\.");
            extension_output="."+extension[1];
            System.out.println(extension_output);
        }
        else{
            System.out.println("no");
        }
        System.out.println("--Fragment--------");
        String[] fragment;
        int t = only_url[1].indexOf("#");
        if(t >=0){
            fragment = only_url[1].split("#");
            System.out.println(fragment[1]);
        }
        else{
            System.out.println("keine");
        }
        System.out.println("----------------");
        String url = "https://www.geeksforgeeks.org/interfaces-in-java/test.php?url=Test";
        String[] rawurl = url.split("\\?");
        int tx = rawurl[0].indexOf("//");
        if(tx >= 0){
            String[] without_https = rawurl[0].split("//");
            String[] path = without_https[1].split("/",2);
            System.out.println(path[1]);
        }
        else{
            String[] path1 = rawurl[0].split("/",2);
            System.out.println(path1[1]);
        }

        String ext = url.substring(url.lastIndexOf('/'));
        int index = ext.indexOf(".");
        System.out.println(index);
        System.out.println(ext);
        String ext2 = ext.substring(ext.lastIndexOf('.'));
        System.out.println(ext2);


        System.out.println("--------------------");

        int ta = url.indexOf("/");
        int ty = url.indexOf("//");
        System.out.println(ty);
        int tz = url.indexOf("?");
        System.out.println(tz);
        /*
        if (ty > 0 && tz > 0){
            String[] without_parameter= url.split("\\?");
            String[] without_https = without_parameter[0].split("//");
            String[] path_both = without_https[1].split("/", 2);
            System.out.println(path_both);
        }
        */

         //if(url.indexOf("//") > 0){

        //}
         /*
        else if(url.indexOf("\\?") > 0){
            String[] without_parameter= url.split("//?");
            String[] path_b = without_parameter[0].split("/", 2);
            System.out.println(path_b[1]);

          */
         boolean Antwort_a = false;
         boolean Antwort_b = false;

         if (url.contains("?")) Antwort_a = true;
         if(url.contains("\\//")) Antwort_b= true;

        if ( ty > 0 && tz >0){
            System.out.println("ssss");
        }
    }
}

