import java.util.ArrayList;
import java.util.LinkedList;

public class SplayTree {
    // Fields
    Node root;

    // Methods
    //rotateRight (rotate right once) - zig rotation -- O(1)
    private Node rotateRight(Node node)
    {
        Node y = node.left;
        node.left = y.right;
        y.right = node;
        return y;
    }
    //rotateLeft (rotate left once) - zag rotation -- O(1)
    private Node rotateLeft(Node node)
    {
        Node y = node.right;
        node.right = y.left;
        y.left = node;
        return y;
    }
    // splay (perform rotations until input node is the root of the tree) -- O(log n)
    public void splayNode(Node node)
    {
        root = splayNode(root, node); // splay input node
    }
    // recursively find subtree node to splay is in and reorganize tree -- O(log n)
    private Node splayNode(Node root, Node nodeToSplay)
    {        
        if (root == nodeToSplay) // If root is the node to splay
            return root; // splaying finished

        if (root.value > nodeToSplay.value) // if current root's value has a higher value than the splaying node
        {
            if (root.left == null) // if there is no left node from the tree or subtree root
                return root; // do not splay

            if (root.left.value > nodeToSplay.value) // if root's left node has a greater value that the splaying node
            {   // zigzig rotation
                root.left.left = splayNode(root.left.left, nodeToSplay); 
                root = rotateRight(root); // rotate the current subtree to the right
            }
            else if (root.left.value < nodeToSplay.value) // if current subtree's left node has a lesser value than the splaying node
            {   // zigzag rotation
                root.left.right = splayNode(root.left.right, nodeToSplay); // restructure root.left.right so it fits under the splaying node
                
                if (root.left.right != null) // check if root.left.right is a valid node
                    root.left = rotateLeft(root.left); // zag rotation on root.left
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
                root.right.left = splayNode(root.right.left, nodeToSplay);
                if (root.right.left != null)
                    root.right = rotateRight(root.right);
            }
            else if (root.right.value < nodeToSplay.value) 
            {
                root.right.right = splayNode(root.right.right, nodeToSplay);
                root = rotateLeft(root);
            }
            return (root.right == null) ? root : rotateLeft(root);
        }
    }
    //insertNode, overload to create a node with value  -- O(log n)
    public void splayValue(int value)
    {
        root = splayValue(root, value); // splay input node
    }
    // recursively find subtree node to splay is in and reorganize tree  -- O(log n)
    private Node splayValue(Node root, int valueToSplay)
    {        
        if (root.value == valueToSplay) // If root is the node to splay
            return root; // splaying finished

        if (root.value > valueToSplay) // if current root's value has a higher value than the splaying node
        {
            if (root.left == null) // if there is no left node from the tree or subtree root
                return root; // do not splay

            if (root.left.value > valueToSplay) // if root's left node has a greater value that the splaying node
            {   // zigzig rotation
                root.left.left = splayValue(root.left.left, valueToSplay); 
                root = rotateRight(root); // rotate the current subtree to the right
            }
            else if (root.left.value < valueToSplay) // if current subtree's left node has a lesser value than the splaying node
            {   // zigzag rotation
                root.left.right = splayValue(root.left.right, valueToSplay); // restructure root.left.right so it fits under the splaying node
                
                if (root.left.right != null) // check if root.left.right is a valid node
                    root.left = rotateLeft(root.left); // zag rotation on root.left
            }
            // if the current subtree has no left node, rotate the tree to the right
            return (root.left == null) ? root : rotateRight(root);
        }
        else
        {
            if (root.right == null) // if root is null
                return root;

            if (root.right.value > valueToSplay) 
            {
                root.right.left = splayValue(root.right.left, valueToSplay);
                if (root.right.left != null)
                    root.right = rotateRight(root.right);
            }
            else if (root.right.value < valueToSplay) 
            {
                root.right.right = splayValue(root.right.right, valueToSplay);
                root = rotateLeft(root);
            }
            return (root.right == null) ? root : rotateLeft(root);
        }
    }
    // add a new node to the tree and move it to the root -- O(log n)
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
                        splayNode(finger.left);
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
                        splayNode(finger.right);
                        break; // done with the loop
                    }
                }
            }
        }
    }
    // add a new node to the tree using a value and move it to the root -- O(log n)
    public void insertValue(int value)
    {
        Node newNode = new Node(value);
        if (root == null) // if there is no root
        {
            root = newNode; // new node is the root
        }
        else { // else
            // point a finger at the current root
            Node finger = root;
            // traverse the tree and add the node
            while(true) {
                // if new node's value is less than finger's value
                if(newNode.value < finger.value) {
                    if (finger.left != null) // if there is a left node
                        finger = finger.left; // then move finger to left
                    else {
                        // else link node to the left
                        finger.left = newNode;
                        splayNode(finger.left);
                        break; //done with the loop
                    }
                }
                // else if node's value is greater than finger's value
                else {
                    if (finger.right != null) // if there is a right node
                        finger = finger.right; // then move finger to right
                    else {
                        //if there is no right node then link in new node to the right
                        finger.right = newNode;
                        splayNode(finger.right);
                        break; // done with the loop
                    }
                }
            }
        }
    }
    // Delete a node with a value -- O(log n)
    public void deleteValue(int value)
    {
        // splay value
        splayValue(root, value);

        // add pointer to root.left and root.right
        Node rootRightChild = root.right;
        Node rootLeftChild = root.left;
        
        // delete root which holds value to be deleted
        root = null;

        // find node with largest value in left tree
        Node highestValInLeft = Max(rootLeftChild);

        // splay largest element of left tree and set it to overall tree root
        root = splayNode(rootLeftChild, highestValInLeft);

        // left tree root.right = right tree root
        root.right = rootRightChild;
    }
    // Find highest value of a tree -- O(n)
    private Node Max(Node root)
    {
        Node finger = root;
        while(finger.right != null)
        {
            finger = finger.right;
        }

        //finger now points at right most node
        return finger; // return that node
    }
    // Perform a breadth first traversal to show current nodes O(n)
    public ArrayList<LinkedList<Node>> breadthFirstTraversal()
    {
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
