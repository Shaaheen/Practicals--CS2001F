package PracTwoSource;
import java.io.PrintStream;
/**
 * Implementation of an AVL Tree
 * 
 * @author Stephan Jamieson
 * @version 3/3/2015
 *
 * Modified by Shaaheen Sacoor
 * SCRSHA001
 * Added methods for delete and find
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
    public void insert(String keyWord) {
        root = TreeUtils.insert(root, keyWord);
    }


    //Finds key word(first letter) and prints all values in dictionary
    public String find(String keyWord) {
        return TreeUtils.find(root,keyWord);
    }

    //Delete given node from tree
    public void delete(String keyWord) {
        root = TreeUtils.delete(root, keyWord);
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
    public boolean contains(String keyWord) {
        if (root==null) {
            return false;
        }
        else {
            return TreeUtils.contains(root, keyWord);
        }
    }

}
