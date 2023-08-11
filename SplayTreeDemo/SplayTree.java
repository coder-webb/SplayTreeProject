import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.LinkedList;

public class SplayTree {
    // Fields
    Node root;

    // Methods
    //rotateRight (rotate right once)
    private Node rotateRight(Node node)
    {
        Node y = node.left;
        node.left = y.right;
        y.right = node;
        return y;
    }
    //rotateLeft (rotate left once)
    private Node rotateLeft(Node node)
    {
        Node y = node.right;
        node.right = y.left;
        y.left = node;
        return y;
    }
    // splay (perform rotations until input node is the root of the tree)
    public void splay(Node node)
    {
        root = splay(root, node); // splay input node
    }
    private Node splay(Node root, Node nodeToSplay)
    {        
        if (root == nodeToSplay) // If root is the node to splay
            return root; // splaying finished

        if (root.value > nodeToSplay.value) // if current root's value has a higher value than the splaying node
        {
            if (root.left == null) // if there is no left node from the tree or subtree root
                return root; // do not splay

            if (root.left.value > nodeToSplay.value) // if root's left node has a greater value that the splaying node
            {
                root.left.left = splay(root.left.left, nodeToSplay); // restructure root.left.left so it fits under the splaying node
                root = rotateRight(root); // rotate the current subtree to the right
            }
            else if (root.left.value < nodeToSplay.value) // if current subtree's left node has a lesser value than the splaying node
            {
                root.left.right = splay(root.left.right, nodeToSplay); // restructure root.left.right so it fits under the splaying node
                
                if (root.left.right != null) // if root.left.right is a valid node
                    root.left = rotateLeft(root.left); // rotate current subtree's left node to the left
            }
            // if the current subtree has no left node, rotate the tree to the right
            return (root.left == null) ? root : rotateRight(root);
        }
        else
        {
            if (root.right == null) // if root is null
                return root;

            if (root.right.value > nodeToSplay.value) 
            {
                root.right.left = splay(root.right.left, nodeToSplay);
                if (root.right.left != null)
                    root.right = rotateRight(root.right);
            }
            else if (root.right.value < nodeToSplay.value) 
            {
                root.right.right = splay(root.right.right, nodeToSplay);
                root = rotateLeft(root);
            }
            return (root.right == null) ? root : rotateLeft(root);
        }
    }
    //insertNode, overload to create a node with value
    public void insertNode(Node node)
    {
        if (root == null) // if there is no root
        {
            root = node; // new node is the root
        }
        else { // else
            // point a finger at the current root
            Node finger = root;
            // traverse the tree and add the node
            while(true) {
                // if new node's value is less than finger's value
                if(node.value < finger.value) {
                    if (finger.left != null) // if there is a left node
                        finger = finger.left; // then move finger to left
                    else {
                        // else link node to the left
                        finger.left = node;
                        splay(finger.left);
                        break; //done with the loop
                    }
                }
                // else if node's value is greater than finger's value
                else {
                    if (finger.right != null) // if there is a right node
                        finger = finger.right; // then move finger to right
                    else {
                        //if there is no right node then link in new node to the right
                        finger.right = node;
                        splay(finger.right);
                        break; // done with the loop
                    }
                }
            }
        }
    }
    //deleteNode
    public void delete(int value) throws Exception
    {
        throw new NotActiveException("TODO");
    }

    public ArrayList<LinkedList<Node>> breadthFirstTraversal() { //Running time O(n)
        
        // Create an array list of linked lists to hold the results
        ArrayList<LinkedList<Node>> bfsResults = new ArrayList<LinkedList<Node>>();

        // Starting point for the traversal (at the root level)
        int currentLevel = 0;

        // Create a linked list for the root node and add it to bfsResults
        LinkedList<Node> rootNode = new LinkedList<Node>();
        rootNode.add(root);
        bfsResults.add(currentLevel, rootNode);

        boolean changesMade = true;; // Tracks if there were additions make to future linked lists
        int currentDepth = 0; // Tracks current depth in the tree

        while (changesMade) // Loop continues while changes are made
        {
            changesMade = false; // Sets changes made to false at the start of every loop

            // Get a list of nodes from the previous depth
            LinkedList<Node> previousNodes = bfsResults.get(currentDepth);
            // Initialize a new linked list of nodes
            LinkedList<Node> newLinkedList = new LinkedList<Node>();
            
            // Traverse the previous nodes
            for (Node node : previousNodes)
            {
                if (node.left != null) // If the node has a left
                {
                    newLinkedList.add(node.left);  // Add node to the new LinkedList
                    changesMade = true; // Set changes were made to true
                }
                if (node.right != null)  // If the node has a right
                {
                    newLinkedList.add(node.right); // Add node to the new LinkedList
                    changesMade = true; // Set changes were made to true
                }
            }

            if (!changesMade) // If no changes were made, break the loop
                break;

            currentDepth++; // Go 1 level deeper
            bfsResults.add(currentDepth, newLinkedList); // Add the newLinkedList to the index matching the current depth
        }

        return bfsResults; // Return the result list of linked lists
    }

    // Constructors - None
}
