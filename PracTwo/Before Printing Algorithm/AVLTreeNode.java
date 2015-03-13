package PracTwoSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Implements a node suitable for building AVL tree structures.
 * 
 * @author Stephan Jamieson
 * @version 3/3/2015
 */
public class AVLTreeNode {

    private Integer key;
    private String keyWord;
    private int height;
    
    private AVLTreeNode left;
    private AVLTreeNode right;
    private ArrayList nodeDict;
    private int wordsEntered = 0;
    
    public final static AVLTreeNode EMPTY_NODE = new AVLTreeNode();
    
    private AVLTreeNode() { this.key=null; this.height=-1; this.left=null; this.right=null;this.keyWord = ""; }
    
    
    /**
     * Create an AVLTreeNode that contains the given key
     */
    public AVLTreeNode(Integer key,String word) { this(null, key, word, null); }
    
    private AVLTreeNode(AVLTreeNode left, Integer key,String wrd, AVLTreeNode right) {
        assert(key!=null);
        this.left=left;
        this.right=right;
        this.key=key;
        this.height=0;
        this.keyWord = wrd;
        this.nodeDict = new ArrayList();
        this.nodeDict.add(wrd);
        this.wordsEntered = this.wordsEntered + 1;

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
     * Determine whether this node has a key.
     */
    public boolean hasKey() { return key!=null; }
        
    /**
     * Obtain the key stored in this node.
     */
    public Integer getKey() { return key; }

    public String getKeyWord(){ return  keyWord;}

    public void setKeyWord(String newWord){
        this.keyWord = newWord;
    }

    public boolean checkIfInAry(String word){
        boolean IfInArray = false;
        for (int i = 0; i< nodeDict.size();i++){
            if (word.equals(nodeDict.get(i))){
                IfInArray = true;
            }
        }
        return IfInArray;
    }

    public void addToDict(String newWord){
        //Checks if already exists under key
        boolean IfInArray = checkIfInAry(newWord);


        //if it doesn't then add to the list under key
        if (!IfInArray){
            this.nodeDict.add(newWord);
        }
        this.wordsEntered = wordsEntered + 1; //Another word entered under key
    }

    /**
     * Obtain the height value stored at this node. (Requirs that ka
     */
    public int getHeight() {
        return this.height;
    }
    
    /**
     * Obtain the balance factor for this node.
     */
    public int getBalanceFactor() { 
        int left = (this.hasLeft() ? this.getLeft().getHeight() : 0);
        int right = (this.hasRight() ? this.getRight().getHeight() : 0);
        return left-right;
    }

    //Method that changes a nodes key and keyWord value to a different nodes key and keyWord value, For delete method
    public void changeNodeDet(AVLTreeNode changedTo){
        this.keyWord = changedTo.keyWord;
        this.key = changedTo.key;
    }

    public ArrayList getDictionary(){
        return this.nodeDict;
    }

        
        /**
     * Obtain this node's left branch. Requires that <code>this.hasLeft()</code>.
     */
    public AVLTreeNode getLeft() { 
        return this.left; 
    }
    /**
     * Obtain this node's right branch. Requires that <code>this.hasRight()</code>.
     */
    public AVLTreeNode getRight() { 
        return this.right; 
    }
    
    /**
     * Set the height stored in this node.
     */
    public void setHeight(int height) { 
        assert(this!=EMPTY_NODE);
        this.height=height; 
    }
    
    
    /**
     * Set this node's left branch.
     */
    public void setLeft(AVLTreeNode tree) {
        assert(this!=EMPTY_NODE);
        this.left = tree;
    }
    
    /**
     * Set this node's right branch.
     */
    public void setRight(AVLTreeNode tree) {
        assert(this!=EMPTY_NODE);
        this.right = tree;
    }
    
    /**
     * Obtain the longest node label for nodes stored in this tree structure.
     */
    public Integer getLargest() {
        Integer largest = this.toString().length();
        if (this.hasLeft()) 
            largest = Math.max(largest, this.getLeft().getLargest());
        if (this.hasRight()) 
            largest = Math.max(largest, this.getRight().getLargest());
        
        return largest;
    }

            
    /**
     * Obtain a String representation of this node.
     */
    public String toString() {
        String toPrint = "";
        toPrint = "(" + Character.toUpperCase(((String) nodeDict.get(0)).charAt(0)) + ") (" + wordsEntered + ")";
        //toPrint = this.getKey().toString()+"("+this.getBalanceFactor()+")";

        return toPrint;
    }

    public String findString(){
        String stringFound = "(" + nodeDict.size() + ")" + "(" + nodeDict.get(0);
        for (int i = 1; i < nodeDict.size(); i++){
            stringFound = stringFound + ", " + nodeDict.get(i);
        }
        stringFound = stringFound + ")";
        return stringFound;
    }
    

}
