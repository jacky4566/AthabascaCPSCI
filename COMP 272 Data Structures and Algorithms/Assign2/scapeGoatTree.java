/**
* title: scapeGoatTree.java
* date:  19/01/2022
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* BinaryTreeNumbers
*
* Purpose and Description:
*  Impliments a scapegoat tree with the credit system described in Lemma 8.3.
*  Showcase the tree by printing it out horozontally to the terminal. 
*
* Dependent Classes:
*  BTNode.java
*
* Constructors:
*  None
*
* Class Variables:
*  BTNode root
*   Contains the root of our binary tree
*
*  int depth
*   Keeps count of the total nodes in our tree
*
* Public Methods:
*  void printTree( )
*   Prints out the tree to the terminal for easy viewing. Also includes the credits per node.
*
*  void addData( int )
*   Add new data to our tree
*
* Private Methods:
*  void rebuild( BTNode )
*   Rebuild the tree from a target node.
*
*  int size( BTNode )
*   Returns the height of a node in our tree
*
*  int log32( int )
*   Calculates the LOG32 of a specific number. Used to calculate tree balence.
*
*  int packIntoArray( BTNode, BTNode[] , int)
*   Decosstructs the tree and packs int array
*
*  BTNode buildBalanced( BTNode, int, int)
*   Rebuilds the tree from target with array
*
*  int addWithDepth( BTNode )
*   Adds a new node into the tree and keeps track of the depth inserted
*
*  String traversePreOrder( BTNode )
*   Traverses the tree in pre-order for printing
*
*  traverseNodes( StringBuilder, String, String, BTNode, boolean )
*   traveses the nodes of the tree returning a string for printing
*/

public class scapeGoatTree{
    private BTNode root;
    private int depth;

    public void printTree(){
        //Print out the tree in a readable fashion
        System.out.println("(Data, Credits)");
        System.out.println(traversePreOrder(root));
    }
 
    public void addData(int x){
        //Add new data to our tree
        BTNode u = new BTNode(x);
        int d = addWithDepth(u);
        System.out.println("Adding Data " + x + " Added at Depth: " + d );
        if (d > log32(depth)) {
            System.out.println("depth exceeded, find scapegoat");
            BTNode w = u.parent;
            while (3*size(w) <= 2*size(w.parent))
                w = w.parent;
            rebuild(w.parent);
        }
    }
    
    private void rebuild(BTNode target){
        //REbuild the tree from here
        int ns = size(target);
        BTNode pointer = target.parent;
        BTNode[] a = new BTNode[ns];
        packIntoArray(target, a, 0);
        target.scapeGoatCredits--; //pay for this rebuild with credits
        if (pointer == null) 
        {
            root = buildBalanced(a, 0, ns);
            root.parent = null;
        } 
        else if (pointer.right == target) 
        {
            pointer.right = buildBalanced(a, 0, ns);
            pointer.right.parent = pointer;
        } 
        else 
        {
            pointer.left = buildBalanced(a, 0, ns);
            pointer.left.parent = pointer;
        }
    }

    private int size(BTNode r){
        //Calculate the height of this node
        if (r == null){
            return 0;
        }
        else{
            int l = 1;
            l += size(r.left);
            l += size(r.right);
            return l;
        }
    }
   
    private static final int log32(int q){
        //Calculate the Log32 for comparison
        final double log23 = 2.4663034623764317;
        return (int)(log23*Math.log(q));
    }
    
    private int packIntoArray(BTNode u, BTNode[] a, int i){
        //Pack data into an array for rebuilding
        if (u == null) 
        {
            return i;
        }
        i = packIntoArray(u.left, a, i);
        a[i++] = u;
        return packIntoArray(u.right, a, i);
    }
   
    private BTNode buildBalanced(BTNode[] a, int i, int ns) {
        //rebuild the array 
        if (ns == 0)
            return null;
        int m = ns / 2;
        a[i + m].left = buildBalanced(a, i, m);
        if (a[i + m].left != null)
            a[i + m].left.parent = a[i + m];
        a[i + m].right = buildBalanced(a, i + m + 1, ns - m - 1);
        if (a[i + m].right != null)
            a[i + m].right.parent = a[i + m];
        return a[i + m];
    }

    private int addWithDepth(BTNode u){
        //Add node and return the depth
        BTNode w = root;
        if (w == null) 
        {
            root = u;
            depth++;
            return 0;
        }
        boolean done = false;
        int d = 0;
        do {
            w.scapeGoatCredits++;
            if (u.getValue() < w.getValue()) 
            {
                if (w.left == null) 
                {
                    w.left = u;
                    u.parent = w;
                    done = true;
                } 
                else 
                {
                    w = w.left;
                }
            } 
            else if (u.getValue() > w.getValue()) 
            {
                if (w.right == null) 
                {
                    w.right = u;
                    u.parent = w;
                    done = true;
                }
                w = w.right;
            } 
            else 
            {
                return -1;
            }
            d++;
        } while (!done);
        depth++;
        return d;
    }

    private String traversePreOrder(BTNode root) {
    //With help from: https://www.baeldung.com/java-print-binary-tree-diagram
        if (root == null) {
            return "";
        }
    
        StringBuilder sb = new StringBuilder();
        sb.append("(" + root.getValue() + ", " + root.scapeGoatCredits + ")");
    
        String pointerRight = "|R--";
        String pointerLeft = (root.right != null) ? "|L--" : "|L--";
    
        traverseNodes(sb, "", pointerLeft, root.left, root.right != null);
        traverseNodes(sb, "", pointerRight, root.right, false);

        sb.append("\n");
    
        return sb.toString();
    }

    private void traverseNodes(StringBuilder sb, String padding, String pointer, BTNode node, boolean hasRightSibling) {
    //With help from: https://www.baeldung.com/java-print-binary-tree-diagram
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append("(" + node.getValue() + ", " + root.scapeGoatCredits + ")");

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("|    ");
            } else {
                paddingBuilder.append("    ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "|R--";
            String pointerLeft = (node.right != null) ? "|L--" : "|L--";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.left, node.right != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.right, false);
        }
    }
}