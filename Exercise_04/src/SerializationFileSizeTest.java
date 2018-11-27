import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

public class SerializationFileSizeTest {

    public static void main(String[] args) {
        Node[] nodes = new Node[1023];
        for (int i = 0; i < nodes.length; i++)
            nodes[i] = new Node("Node" + i);

        for (int i = 0; i < nodes.length/2; i++) {
            nodes[i].children.add(nodes[(2*i) + 1]);
            nodes[i].children.add(nodes[(2*i) + 2]);
        }

        // after add a root node to the leaf a JAXBException will be triggered.
        //nodes[nodes.length/2].children.add(nodes[1]);

        try{
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("./out/binary_object_serialization.bin"));
            objOut.writeObject(nodes[0]);
        }catch (IOException e) {
            System.err.println("IOException." );
            System.exit(1);
        }


        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        try{
            JAXBContext context = JAXBContext.newInstance(Node.class);
            Marshaller m = context.createMarshaller();
            m.marshal(nodes[0], byteOutStream);

            OutputStream outStream = null;
            try {
                outStream = new FileOutputStream("./out/xml_serialization.bin");

                // writing bytes in to byte output stream
                byteOutStream.writeTo(outStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (JAXBException e) {
            System.err.println("JAXBException." );
            System.exit(1);
        }

    }
}
