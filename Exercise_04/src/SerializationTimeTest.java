import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import javax.xml.bind.JAXBException;

public class SerializationTimeTest {

    public static void main(String[] args) {
        Node[] nodes = new Node[1023];
        for (int i = 0; i < nodes.length; i++)
            nodes[i] = new Node("Node" + i);

        for (int i = 0; i < nodes.length/2; i++) {
            nodes[i].children.add(nodes[(2*i) + 1]);
            nodes[i].children.add(nodes[(2*i) + 2]);
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i<100; i++) {
            try{
                ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("../obj.object"));
                objOut.writeObject(nodes[0]);
            }catch (IOException e) {
                System.err.println("IOException." );
                System.exit(1);
            }
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Execution times of binary object serialization: "+(stopTime-startTime)+"ms.");

        startTime = System.currentTimeMillis();
        for (int i = 0; i<100; i++) {
        ByteArrayOutputStream out_stream = new ByteArrayOutputStream();
        try{
            JAXBContext context = JAXBContext.newInstance(Node.class);
            Marshaller m = context.createMarshaller();
            m.marshal(nodes[0], out_stream);
        }catch (JAXBException e) {
            System.err.println("JAXBException." );
            System.exit(1);
        }
        }
        stopTime = System.currentTimeMillis();
        System.out.println("Execution times of XML serialization:"+(stopTime-startTime)+"ms");
    }
}
