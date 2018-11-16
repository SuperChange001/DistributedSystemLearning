import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class tcpMultiThreadClient {
    public static void main(String[] args) {

        // Config the server address and port.
        String hostName = "debby.vs.uni-due.de";
        int portNumber = 2223;

        try {
            // Start to TCP connection with Socket
            Socket client = new Socket(hostName, portNumber);

            // Create a thread to listen the Socket.
            SocketReadThread mSocketReadThread = new SocketReadThread(client);
            mSocketReadThread.start();      // Start the Socket listening thread.

            // Create a thread to listen the Keyboard input.
            KeyboardReadThread mKeyboardReadThread = new KeyboardReadThread(client);
            mKeyboardReadThread.start();    // Start the Keyboard listening thread.

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }

    }
}

// Thread for Socket reading asynchronously.
class SocketReadThread extends Thread{

    Socket serverSocket;
    BufferedReader bufferFromSocket=null;

    // Thread instantiation
    public SocketReadThread(Socket initServerSocket){
        this.serverSocket = initServerSocket;

    }

    // Thread running continuously.
    public void run(){
        try{
            if (bufferFromSocket==null){
                this.bufferFromSocket = new BufferedReader(
                        new InputStreamReader(this.serverSocket.getInputStream()));
            }

            String outputFromServer="";
            while((outputFromServer = this.bufferFromSocket.readLine())!= null){
                //This part is printing the output to console
                //Instead it should be appending the output to some file
                //or some swing element. Because this output may overlap
                //the user input from console
                System.out.println(outputFromServer);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

// Thread for keyboard input reading asynchronously.
class KeyboardReadThread extends Thread{

    Socket serverSocket;
    BufferedReader input_from_keyboard;
    PrintWriter printWriter2Socket;

    public KeyboardReadThread(Socket initServerSocket){
        this.serverSocket = initServerSocket;
        this.input_from_keyboard = new BufferedReader(
                new InputStreamReader(System.in));
        this.printWriter2Socket=null;
    }

    // Thread running continuously.
    public void run(){

        try{

            // Only execute the first time.
            if (printWriter2Socket==null){
                printWriter2Socket= new PrintWriter(serverSocket.getOutputStream(), true);
            }

            // If read is not empty then send to the server.
            String userInput;
            while ((userInput = this.input_from_keyboard.readLine()) != null) {
                printWriter2Socket.println(userInput);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}