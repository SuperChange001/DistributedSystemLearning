
import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class tcpClient {
    public static void main(String[] args) throws IOException{
        // Here client try to connect the remote sever
        Socket client = new Socket("debby.vs.uni-due.de",2223);
        client.setSoTimeout(3000);
        if (client!=null) {

            // Now set the input source from the keyboard.
            BufferedReader input_from_keyboard = new BufferedReader(new InputStreamReader(System.in));

            // Init the Output Stream of the socket, that can be used to send data to the server.
            PrintStream outStream = new PrintStream(client.getOutputStream());

            // Use bufferedReader to get the input Stream of the socket.
            BufferedReader inStream = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // Since we have connected to the server, try to read the stream from the socket.
            try{
                String echo = inStream.readLine();
                System.out.println("[Receive]: " + echo);
            }catch(SocketTimeoutException e){
                System.out.println("[Error]: Time out, No response");
            }

            // Running_flag is used for hold the connection.
            boolean running_flag = true;

            while(running_flag) {

                // try to read the input data from the command line
                String str = input_from_keyboard.readLine();



                // Decide if we should close the socket
                if("exit()".equals(str)){
                    running_flag = false;
                }else{

                    // send to the Socket
                    outStream.println(str);

                    // Try to get a feedback from the server.
                    try{
                        String echo = inStream.readLine();
                        System.out.println("[Receive]: " + echo);
                    }catch(SocketTimeoutException e){
                        System.out.println("[Error]: Time out, No response");
                    }
                }
            }

            // We need to close the keyboard input source
            input_from_keyboard.close();

            // Close the socket, it will close the in- and output streams automatically.
            client.close();

        }
    }
}
