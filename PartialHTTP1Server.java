import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.*;

public class PartialHTTP1Server {

        public static void main(String args[]){

                //initialize socket and input stream
                private Socket socket = null;
                private ServerSocket server = null;
                private DataInputStream din = null;
                private DataOutputStream dos = null;

                private ArrayList<Thread> thread_ids = new ArrayList<Thread>(50);

                try{
                        server = new ServerSocket(Integer.parseInt(args[0]));
                        System.out.println("Server started");

                        System.out.println("Waiting for a client ...");

                        while(true){
                                //Accept blocking call
                                socket = server.accept();

                                System.out.println("Client accepted");

                                // setting input and output streams
                                din = new DataInputStream(socket.getInputStream());
                                dos = new DataOutputStream(socket.getOutputStream());

                                System.out.println("Assigning new thread for this client");

                                thread_ids.add(new ClientHandler(socket, dis, dos));

                        }

                        System.out.println("Stopping Accept...");

                        // close connection
                        socket.close();
                        din.close();
                        dos.close();

                }catch(IOException i){
                        System.out.println(i);
                }
        }

        class ClientHandler extends Thread{
                final DataInputStream dis;
                final DataOutputStream dos;
                final Socket socket;

                public ClientHandler(Socket socket, DataInputStream dis, DataOutputStream dos){
                        this.socket = socket;
                        this.dis = dis;
                        this.dos = dos;
                }

                @Override
                public void run(){
                //Does something
                }

                //terminate the thread:

                try{
                        this.dis.close();
                        this.dos.close();
                        this.socket.close();

                }catch(IOException e){
                        e.printStackTrace();
                }
        }

}
