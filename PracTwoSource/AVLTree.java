import java.io.PrintStream;
/**
 * Implementation of an AVL Tree
 * 
 * @author Stephan Jamieson
 * @version 3/3/2015
 */
public class AVLTree {

    private AVLTreeNode root;

    /**
     * Create an empty AVL tree
     */
    public AVLTree() {
        root = null;
    }
    
    /**
     * Insert the given key into the tree.
     */
    public void insert(Integer key) {
        root = TreeUtils.insert(root, key);
    }

    /**
     * Use the given PrintStream object to output a textual representation of this tree.
     */
    public void print(PrintStream printStream) {
        SimpleTreeWriter writer = new SimpleTreeWriterImpl(printStream);
        writer.write(this.root);
    }
    
    /**
     * Determine whether the tree contains the given key.
     */
    public boolean contains(Integer key) {
        if (root==null) {
            return false;
        }
        else {
            return TreeUtils.contains(root, key);
        }
    }

}
