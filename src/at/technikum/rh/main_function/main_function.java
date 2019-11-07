package at.technikum.rh.main_function;


import at.technikum.rh.main_programs.server.Server;

public class main_function {
    //Test with the interfaces with my main_function
    public static void main(String[] args){
        Server my_newServer;
        int myport = 8080;
        try{
            System.out.println("The server will be started shortly");
            System.out.println("The server will be started on the port"+myport);
            my_newServer = new Server();
            my_newServer.start_server(myport);
            System.out.println("The server has now been started");
        } catch (Exception e){
            System.out.println("Error happened!\n Erro Meesage"+e.getMessage());
        }
    }
}
