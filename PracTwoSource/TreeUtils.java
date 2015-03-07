package PracTwoSource;

import java.util.*;

/**
 * Utility procedures for binary tree structures.
 * 
 * @author Stephan Jamieson
 * @version 25/2/2015
 *
 * Modified by SCRSHA001 at 06/03/2015
 * Shaaheen Sacoor
 * Implemented code for empty methods contain, insert, rotate methods and double rotate methods and added methods from previous assignment
 */
public class TreeUtils {
    /**
     * Obtain the height value of the given node.
     * @return 0 if <code>node==null</code>, otherwise <code>node.getHeight()</code>.
     */
    public static int height(AVLTreeNode node) {
        if (node==null) {
            return 0;
        }
        else {
            return node.getHeight();
        }
    }

    //method that will return a list of nodes in the first level
    public static AVLTreeNode[] createLvlZero(AVLTreeNode root){
        AVLTreeNode[] levelZero = new AVLTreeNode[1];
        levelZero[0] = root;
        return levelZero;
    }

    //Method to create a Arraylist that contains a list for each level in a Binary Tree
    public static ArrayList createSubTreeList(AVLTreeNode[] parentList){
        AVLTreeNode currentTree = parentList[0]; //Gets root Node from List
        int height = currentTree.getHeight();//Gets height of Binary Tree

        LinkedList NodesQueue = new LinkedList(); //Linked list used to contain Nodes in a Tree in a Queue format
        LinkedList numNodesInLevel = new LinkedList(); //Keeps the number of nodes that are supposed to be in a level
        ArrayList NodeLevels = new ArrayList(); //will keep all levels of node, Main list which will have inner lists

        for (int i = 0; i < height; i++){
            int noOfNodes = (int) Math.pow(2,i); //Calculates number of nodes in each level
            numNodesInLevel.add(noOfNodes); //Adds number to linkedlist so as to use later
            NodeLevels.add(new ArrayList()); //Create a list for every level, Put into main list
        }
        NodesQueue.add(currentTree); //Adds root node into Linked List. Will work with it first

        //While loop will retrieve and delete first item in linked list Queue and work with that item for current loop
        //Then it will add that Tree Node to appropriate Node level List, eg root node into Level 0 Arraylist
        //After this it will determine if there are left or right sub trees, if there are it will add that subtree
        //node to the Linked List Queue with Left preferenced.

        int count = 0; //Determines which Level List should be on by using the numNodesInLevel list
        int currNodeLevel = 0;//Index for the Node level list
        ArrayList currAryList; //current working Arraylist

        while(currNodeLevel < height){ //Goes until it reaches end of last level
            count = count + 1;
            AVLTreeNode currTreeNode = (AVLTreeNode) NodesQueue.pop();
            currAryList = (ArrayList) (NodeLevels.get(currNodeLevel));
            currAryList.add(currTreeNode);
            if (currTreeNode == null){ //For the placeholder Nodes, if a placeholder then create placeholder children

                NodesQueue.add(null);//have to create placeholder children to space
                NodesQueue.add(null);//it out appropriately for printing algorithm
            }
            else { //if legit Node, (Node != null)

                if (currTreeNode.hasLeft()) {
                    NodesQueue.add(currTreeNode.getLeft());//add Left sub tree to Queue so it can be processed later
                }
                else {
                    NodesQueue.add(null); //if no left tree, put a placeholder
                }

                if (currTreeNode.hasRight()) {
                    NodesQueue.add(currTreeNode.getRight()); //same as left
                }
                else {
                    NodesQueue.add(null);
                }

            }

            //if the number of nodes added so far in the loop equal to what the number
            //of nodes in a level should be then reset counter and start adding till
            //the number of nodes equal the next levels predicted number of nodes

            if (count == numNodesInLevel.peek()) {
                currNodeLevel = currNodeLevel + 1; //New level of Tree
                count = 0; //reset counter
                numNodesInLevel.pop(); //remove old levels Node number(number of nodes in that level)
            }

        } //end of while loop

        return NodeLevels; //returns arraylist containing lists of every level
    }

    /** 
     * Determine whether the given tree structure contains the given key.
     */
    public static boolean contains(AVLTreeNode node, Integer key) {
        if (node.getKey() == key){ //if node key is equal to key we want to find then return true
            return true;
        }
        if (key < node.getKey()){ //if key searching for is smaller than current key then try go to the left subtree
            if (node.hasLeft()) { //go down left subtree
                return contains(node.getLeft(), key);
            }
            else { //if there is no subtree and key is smaller than current node key then there can't be key in tree, therefore false
                return false;
            }
        }
        else {
            if (node.hasRight()){ //same as left
                return contains(node.getRight(),key);
            }
            else{
                return false;
            }
        }

    }

    /**
     * Recursive implementation of insert on an AVLTreeNode structure.
     */
    public static AVLTreeNode insert(AVLTreeNode node, Integer key) {
        boolean inserted = false;
        AVLTreeNode currNode = node;
        Stack balanceStack = new Stack<AVLTreeNode>();
        while (!inserted){
            if (node == null){
                node = new AVLTreeNode(key);
                node.setHeight(heightSetter(node));
                break;
            }
            else if (currNode.getKey() == key){
                break;
            }
            else if (key < currNode.getKey()){
                if (currNode.hasLeft()){
                    currNode = currNode.getLeft();
                }
                else{
                    currNode.setLeft(new AVLTreeNode(key));
                    currNode.getLeft().setHeight(heightSetter(currNode.getLeft()));
                    node.setHeight(heightSetter(node));
                    currNode.setHeight(heightSetter(currNode));
                    balanceStack.add(currNode);
                    inserted = true;
                }
            }
            else if (key > currNode.getKey()){
                if (currNode.hasRight()){
                    currNode = currNode.getRight();
                }
                else{
                    currNode.setRight(new AVLTreeNode(key));
                    currNode.getRight().setHeight(heightSetter(currNode.getRight()));
                    node.setHeight(heightSetter(node));
                    currNode.setHeight(heightSetter(currNode));
                    inserted = true;
                    //HAVE TO SORT OUT HEIGHT PROBLEM
                }
            }
            balanceStack.add(currNode);
            checkBalance(balanceStack,node);
        }

        return node;
    }

    public static void checkBalance(Stack<AVLTreeNode> stackBalance, AVLTreeNode root){
        AVLTreeNode insertedNode = stackBalance.peek();
        for (int i = 0; i < stackBalance.size(); i++){
            if (stackBalance.get(i).getBalanceFactor() > 1){
                rebalanceLeft(stackBalance.get(i), stackBalance.get(i).getLeft().getKey());
                refreshAllHts(root);
                break;
            }
            else if (stackBalance.get(i).getBalanceFactor() < -1){
                rebalanceRight(stackBalance.get(i), stackBalance.get(i).getRight().getKey());
                refreshAllHts(root);
                break;
            }
        }
    }
    
    public static void refreshAllHts(AVLTreeNode allTreeNodes){
        allTreeNodes.setHeight(heightSetter(allTreeNodes));
        if (allTreeNodes.hasLeft()){
            refreshAllHts(allTreeNodes.getLeft());
        }
        else if (allTreeNodes.hasRight()){
            refreshAllHts(allTreeNodes.getRight());
        }
    }

    public static void rebalanceLeft(AVLTreeNode ancestor,int parentKey){
        int left = (ancestor.getLeft().hasLeft() ? ancestor.getLeft().getLeft().getKey() : 0);
        if (left < parentKey){
            rotateWithLeftChild(ancestor);
        }
        else{
            doubleRotateWithLeftChild(ancestor);
        }
    }
    
    public static void rebalanceRight(AVLTreeNode ancestor,int parentKey){
        int right = (ancestor.getRight().hasRight() ? ancestor.getRight().getRight().getKey() : 0);
        if (right > parentKey){
            rotateWithRightChild(ancestor);
        }
        else{
            doubleRotateWithRightChild(ancestor);
        }
    }


    public static int heightSetter(AVLTreeNode nodeToChangeHt){
            if (nodeToChangeHt.hasLeft() && nodeToChangeHt.hasRight()) {
                return Math.max(heightSetter(nodeToChangeHt.getLeft()), heightSetter(nodeToChangeHt.getRight())) + 1;
            }
            else if (nodeToChangeHt.hasLeft()) {
                return heightSetter(nodeToChangeHt.getLeft()) + 1;
            }
            else if (nodeToChangeHt.hasRight()) {
                return heightSetter(nodeToChangeHt.getRight()) + 1;
            }
            else {
                return 1;
            }
    }
    
    /**
     * Rotate binary tree node with left child.
     * This is a single rotation for case 1.
     */
    public static AVLTreeNode rotateWithLeftChild( AVLTreeNode AVLk2 ) {
        
        AVLTreeNode AVLk1 = AVLk2.getLeft();
        AVLk2.setLeft(AVLk1.getRight());
        AVLk1.setRight(AVLk2);        
        return AVLk1;
    }

    /**
     * Rotate binary tree node with right child.
     * This is a single rotation for case 4.
     */
    public static AVLTreeNode rotateWithRightChild( AVLTreeNode AVLk1 ) {
        
        AVLTreeNode AVLk2 = AVLk1.getRight();
        AVLk1.setRight(AVLk2.getLeft());
        AVLk2.setLeft(AVLk1);
        
        return AVLk2;
    }

    /**
     * Double rotate binary tree node: first rotate k3's left child
     * with its right child; then rotate node k3 with the new left child.
     * This is a double rotation for case 2.
     */
    public static AVLTreeNode doubleRotateWithLeftChild( AVLTreeNode AVLk3 ) {
        AVLk3.setLeft(rotateWithRightChild(AVLk3.getLeft()));
        return rotateWithLeftChild(AVLk3);
        
    }

    /**
     * Double rotate binary tree node: first rotate k1's right child
     * with its left child; then rotate node k1 with the new right child.
     * This is a double rotation for case 3.
     */
    public static AVLTreeNode doubleRotateWithRightChild( AVLTreeNode AVLk3 )
    {
        AVLk3.setRight(rotateWithLeftChild(AVLk3.getRight()));
        return rotateWithRightChild(AVLk3);
    }
    
    

    
    /**
     * Obtain a list containing the root node of the given structure i.e. tNode itself.
     */
    public static List<AVLTreeNode> levelZero(AVLTreeNode tNode) {
        List<AVLTreeNode> level = new ArrayList<AVLTreeNode>();
        level.add(tNode);
        return level;
    }
    
    
    /**
     * Given a list of nodes, obtain the next level. 
     * 
     * <p>
     * If the tree structure is incomplete, <code>AVLTreeNode.EMPTY_NODE</code> is inserted as a place holder for each
     * missing node.
     * </p>
     */
    public static List<AVLTreeNode> nextLevel(List<AVLTreeNode> level) {
        List<AVLTreeNode> nextLevel = new ArrayList<AVLTreeNode>(); 
        
        for (AVLTreeNode node : level) {
            nextLevel.add(node.hasLeft() ? node.getLeft() : AVLTreeNode.EMPTY_NODE); 
            nextLevel.add(node.hasRight() ? node.getRight() : AVLTreeNode.EMPTY_NODE);
        }
        return nextLevel;
    }
    
    /**
     * Determine whether node is a place holder i.e. <code>node==AVLTreeNode.EMPTY_NODE</code>
     */
    public static boolean isPlaceHolder(AVLTreeNode node) {
        return node==AVLTreeNode.EMPTY_NODE;
    }
    
}
