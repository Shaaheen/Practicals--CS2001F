package PracticalOne;

/**
 * Simple Binary Search Tree for Integers.
 * 
 * @author Stephan Jamieson
 * @version 24/2/2015
 */
public class SimpleBST {

    private BinaryTreeNode root;
    
    /**
     * Create an empty binary search tree.
     */
    public SimpleBST() {
        this.root=null;
    }
    
    
    private BinaryTreeNode getRoot() { return this.root; }
    
    /**
     * Insert the given value in the tree. 
     */
    public void insert(Integer item) {
        assert(item!=null);
        if (this.isEmpty()) {
            root = new BinaryTreeNode(item);
        }
        else {
            this.insert(item, root);
        }
    }
    
    /*
     * Implements the ordering required for a binary search tree.
     */
    private void insert(Integer item, BinaryTreeNode node) {
        if (item<node.getItem()) {
            if (node.hasLeft()) {
                insert(item, node.getLeft());
            }
            else {
                node.setLeft(new BinaryTreeNode(item));
            }
        }
        else {
            if (node.hasRight()) {
                insert(item, node.getRight());
            }
            else {
                node.setRight(new BinaryTreeNode(item));
            }
        }
    }
       
    /**
     * Determine whether this tree is empty.
     */
    public boolean isEmpty() { return root == null; }
    
    /**
     * Obtain the height of this tree. 
     */
    public Integer getHeight() { 
        if (root == null) {
            return 0;
        }
        else {
            return root.getHeight(); 
        }
    }
    
    /**
     * Obtain the largest integer value stored in this tree. Requires that <code>!this.isEmpty()</code>.
     */
    public Integer getLargest() { 
        assert(!this.isEmpty());
        return root.getLargest(); 
    }
    
    /**
     * Determine whether this BST is similar to the other BST.
     */
    public boolean similar(SimpleBST other) {
        return TreeUtils.similar(this.getRoot(), other.getRoot());
    }

    /**
     * Print tree using given writer.
     */
    public static void print(SimpleBST tree, SimpleTreeWriter writer) {
        writer.write(tree.getRoot());
    }
}
