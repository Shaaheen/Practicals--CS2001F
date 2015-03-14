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
 *
 * Modified by Shaaheen Sacoor at 10/03/2015
 * SCRSHA001
 * Implemented code to allow for Tree to contain dictionaries
 * Does not contain height or height methods - For last 10%
 */
public class AVLTreeNode {

    private Integer key;
    private String keyWord;

    private AVLTreeNode left;
    private AVLTreeNode right;
    private ArrayList nodeDict; //node dictionary contains all values entered with the same first letter
    private int wordsEntered = 0; //number of words entered into the dictionary, doesn't matter if deleted
    
    public final static AVLTreeNode EMPTY_NODE = new AVLTreeNode();
    
    private AVLTreeNode() { this.key=null; this.left=null; this.right=null;this.keyWord = ""; }
    
    
    /**
     * Create an AVLTreeNode that contains the given key
     */
    public AVLTreeNode(Integer key,String word) { this(null, key, word, null); }
    
    private AVLTreeNode(AVLTreeNode left, Integer key,String wrd, AVLTreeNode right) {
        assert(key!=null);
        this.left=left;
        this.right=right;
        this.key=key;
        this.keyWord = wrd;
        this.nodeDict = new ArrayList(); // Creates and arraylist that will contain all the words
        this.nodeDict.add(wrd); //adds the first word into the dictionary
        this.wordsEntered = this.wordsEntered + 1; //Another word entered, adds one

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

    //Will check if Arraylist contains the parameter word
    public boolean checkIfInAry(String word){
        boolean IfInArray = false;
        for (int i = 0; i< nodeDict.size();i++){
            if (word.equals(nodeDict.get(i))){ //if word found in array return true
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
     * Obtain the balance factor for this node.
     */
    public int getBalanceFactor() { 
        int left = TreeUtils.height(this.getLeft());
        int right = TreeUtils.height(this.getRight());
        return left-right;
    }

    //Method that changes nodes key, dictionary and wordsEntered to another nodes instance variables. For delete method
    public void changeNodeDet(AVLTreeNode changedTo){
        this.nodeDict = changedTo.nodeDict;
        this.key = changedTo.key;
        this.wordsEntered = changedTo.wordsEntered;
    }

    //returns Arraylist
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
        if (nodeDict != null){
            toPrint = "(" + Character.toUpperCase(((String) nodeDict.get(0)).charAt(0)) + ") (" + wordsEntered + ")";
            for (int i = 0;i < nodeDict.size();i++){
                toPrint = toPrint + "\r\n" + nodeDict.get(i);
            }
        }
        else{

        }
        //toPrint = this.getKey().toString()+"("+this.getBalanceFactor()+")";

        return toPrint;
    }

    //Obtains string representation of dictionary specifically for find function
    public String findString(){
        String stringFound = "(" + nodeDict.size() + ")" + "(" + nodeDict.get(0);
        for (int i = 1; i < nodeDict.size(); i++){
            stringFound = stringFound + ", " + nodeDict.get(i);
        }
        stringFound = stringFound + ")";
        return stringFound;
    }
    

}
