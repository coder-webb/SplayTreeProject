import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        SplayTree tree = new SplayTree();

        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(5);
        Node d = new Node(6);
        Node e = new Node(26);
        Node f = new Node(79);
        Node g = new Node(4);
        Node h = new Node(8);

        tree.insertNode(a);
        tree.insertNode(b);
        tree.insertNode(c);
        tree.insertNode(d);
        tree.insertNode(e);
        tree.insertNode(f);
        tree.insertNode(g);
        tree.insertNode(h);
        ArrayList<LinkedList<Node>> results2 = tree.breadthFirstTraversal();
        for (LinkedList<Node> linkedList : results2) {
            for (Node node : linkedList) {
                System.out.print(node.value + " ");
            }
            System.out.println();
        }
        tree.splay(a);

       
        tree.splay(h);


    }
}