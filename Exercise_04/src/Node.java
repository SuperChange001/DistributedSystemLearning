import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Node implements Serializable { @XmlElement
String name;
    @XmlElement(name="node")
    ArrayList<Node> children = new ArrayList<Node>(); public Node() {
    }
    public Node(String n) {
        this.name = n;
    } }