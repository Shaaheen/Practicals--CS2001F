package PracticalOne;

/**
 * Simple binary tree node for integer values.
 * 
 * @author Stephan Jamieson 
 * @version 24/2/2015
 */
public class BinaryTreeNode {

    private Integer item;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    /**
     * Convenience object for use as place holder in printing.
     */
    public static BinaryTreeNode EMPTY_NODE = new BinaryTreeNode();
    
    private BinaryTreeNode() { this.left=null; this.item=null; this.right=null; }
    
    /**
     * Create a node containing the given item.
     */
    public BinaryTreeNode(Integer item) {
        this(null, item, null);
    }
    
    /**
     * Create a node containing the given item and left and right sub trees.
     */
    private BinaryTreeNode(BinaryTreeNode left, Integer item, BinaryTreeNode right) {
        assert(item!=null);
        this.left=left;
        this.item=item;
        this.right=right;
    }
    
    /* Low level structural operations */  
    /**
     * Determine whether this node has a left branch.
     */
    public boolean hasLeft() { return left!=null; }
    /**
     * Determine whether this node has a right branch.
     */
    public boolean hasRight() { return right!=null; }
    
    /**
     * Obtain the value stored in this node.
     */
    public Integer getItem() { return item; }
    /**
     * Obtain this node's left branch. Requires that <code>this.hasLeft()</code>.
     */
    public BinaryTreeNode getLeft() {
        assert(this.hasLeft());
        return this.left; 
    }
    /**
     * Obtain this node's right branch. Requires that <code>this.hasRight()</code>.
     */
    public BinaryTreeNode getRight() { 
        assert(this.hasRight());
        return this.right; 
    }
    
    /**
     * Set this node's left branch.
     */
    public void setLeft(BinaryTreeNode tree) {
        assert(tree!=EMPTY_NODE);
        this.left = tree;
    }
    
    /**
     * Set this node's right branch.
     */
    public void setRight(BinaryTreeNode tree) {
        assert(tree!=EMPTY_NODE);
        this.right = tree;
    }
    
    /**
     * Obtain the height of this tree structure.
     */
    public Integer getHeight() {
        if (this.hasLeft() && this.hasRight()) {
            return Math.max(this.getLeft().getHeight(), this.getRight().getHeight())+1;
        }
        else if (this.hasLeft()) {
            return this.getLeft().getHeight()+1;
        }
        else if (this.hasRight()) {
            return this.getRight().getHeight()+1;
        }
        else {
            return 1;
        }
    }
    
    /**
     * Obtain the largest integer value stored in this tree structure.
     */
    public Integer getLargest() {
        Integer largest = this.getItem();
        if (this.hasLeft()) 
            largest = Math.max(largest, this.getLeft().getLargest());
        if (this.hasRight()) 
            largest = Math.max(largest, this.getRight().getLargest());
        
        return largest;
    }


}
