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
            return heightSetter(node);
        }
    }

    /** 
     * Determine whether the given tree structure contains the given key.
     */
    public static boolean contains(AVLTreeNode node, Integer key) {
        if ((node.getKey()).equals(key)){ //if node key is equal to key we want to find then return true
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
                return contains(node.getRight(), key);
            }
            else{
                return false;
            }
        }

    }

    /**
     * Iterative implementation of insert on an AVLTreeNode structure.
     */
    public static AVLTreeNode insert(AVLTreeNode node, Integer key) {
        boolean inserted = false; //used to check if a node has been added yet
        AVLTreeNode currNode = node; //current node working with, changes over each loop
        Stack balanceStack = new Stack<AVLTreeNode>(); //Stack that acts to store the path taken to add object so as to balance tree easily
        balanceStack.add(currNode); //add root node to stack
        while (!inserted){ //while nothing has been inserted keep going
            if (node == null){ //if empty tree then create root node
                node = new AVLTreeNode(key); //create root node
                return node;//end method and return root
            }
            else if (currNode.getKey() == key){ //if duplicate then do nothing
                return node;
            }
            else if (key < currNode.getKey()){ //if new key is smaller than current node key value then try go left
                if (currNode.hasLeft()){ //try go left
                    currNode = currNode.getLeft();
                }
                else{ //if no left value already then add new node here
                    currNode.setLeft(new AVLTreeNode(key));
                    inserted = true;
                    currNode = currNode.getLeft();
                }
            }
            else if (key > currNode.getKey()){
                if (currNode.hasRight()){
                    currNode = currNode.getRight();
                }
                else{
                    currNode.setRight(new AVLTreeNode(key));
                    inserted = true;
                    currNode = currNode.getRight();
                }
            }
            balanceStack.add(currNode);
        }
        node = checkBalance(balanceStack,node);

        return node;
    }


    public static AVLTreeNode checkBalance(Stack<AVLTreeNode> stackBalance, AVLTreeNode root){
        AVLTreeNode insertedNode = stackBalance.peek(); //Gets last node in the path to down to inserted or deleted node
        AVLTreeNode currStackNode; //current node working with from stack
        AVLTreeNode beforeImbalanceNode = root; //gets node before the imbalanced node so as to know where to place rebalanced node
        for (int i = (stackBalance.size()-1); i >= 0; i--){ //loop to go through stack from bottom to top
            currStackNode = stackBalance.get(i); //gets nodes starting from bottom of path
            if (i>0){ //if not at root node

                beforeImbalanceNode = stackBalance.get(i-1); //before imbalance node set as node just before current node
            }
            if (currStackNode.getBalanceFactor() > 1){ //if balance factor is greater than 1, then rebalance on left
                if (i == 0){ //if at root node
                    //change root node and return new root
                    AVLTreeNode newRootNode = rebalanceLeft(currStackNode, currStackNode.getLeft().getKey());
                    return newRootNode;
                }
                else{ //if not at root
                    //if the current working node's key is smaller then its parent then the parent should set its left to be the new rebalanced node
                    if (beforeImbalanceNode.getKey() >= currStackNode.getKey() ){
                        beforeImbalanceNode.setLeft(rebalanceLeft(currStackNode, currStackNode.getLeft().getKey()));
                    }
                    else{ //if current nodes key is smaller than parent then set on right
                        beforeImbalanceNode.setRight(rebalanceLeft(currStackNode, currStackNode.getLeft().getKey()));
                    }

                }
                break;
            }
            else if (currStackNode.getBalanceFactor() < -1){ //similar to rebalancing left but balance factor would have been negative
                if (i == 0){
                    AVLTreeNode newRootNode = rebalanceRight(currStackNode, currStackNode.getRight().getKey());
                    return newRootNode;
                }
                else{
                    if (beforeImbalanceNode.getKey() <= currStackNode.getKey()){
                        beforeImbalanceNode.setRight(rebalanceRight(currStackNode, currStackNode.getRight().getKey()));
                    }
                    else{
                        beforeImbalanceNode.setLeft(rebalanceRight(currStackNode, currStackNode.getRight().getKey()));
                    }

                }
            }
        }
        //returns root
        return stackBalance.get(0);
    }

    //Returns the height of node given using recursion
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

    //rebalances left
    public static AVLTreeNode rebalanceLeft(AVLTreeNode ancestor,int parentKey){
        //if middle node has a left child then do a single rotate left
        if (ancestor.getLeft().hasLeft()){
            return rotateWithLeftChild(ancestor);
        }
        else{ //else do a double rotate
            return doubleRotateWithLeftChild(ancestor);
        }
    }
    //Rebalances Right - similar to left
    public static AVLTreeNode rebalanceRight(AVLTreeNode ancestor,int parentKey){

        if (ancestor.getRight().hasRight()){
            return rotateWithRightChild(ancestor);
        }
        else{
            return doubleRotateWithRightChild(ancestor);
        }
    }


    /**
     * Rotate binary tree node with left child.
     * This is a single rotation for case 1.
     */
    public static AVLTreeNode rotateWithLeftChild( AVLTreeNode AVLk2 ) {

        AVLTreeNode AVLk1 = AVLk2.getLeft(); //sets middle node of three nodes as k1
        AVLk2.setLeft(AVLk1.getRight()); //sets top node to take current children of middle node since they will switch places
        AVLk1.setRight(AVLk2); //set the right of middle node to the top node
        //returns new rebalanced node
        return AVLk1;
    }

    /**
     * Rotate binary tree node with right child.
     * This is a single rotation for case 4.
     */
    public static AVLTreeNode rotateWithRightChild( AVLTreeNode AVLk1 ) {
        //similar to left
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
        AVLk3.setLeft(rotateWithRightChild(AVLk3.getLeft())); //rotate with right child will change the three node values to be all smaller than each other in a row in the tree
        return rotateWithLeftChild(AVLk3); //do a single rotation

    }

    /**
     * Double rotate binary tree node: first rotate k1's right child
     * with its left child; then rotate node k1 with the new right child.
     * This is a double rotation for case 3.
     */
    public static AVLTreeNode doubleRotateWithRightChild( AVLTreeNode AVLk3 )
    {   //similar to left
        AVLk3.setRight(rotateWithLeftChild(AVLk3.getRight()));
        return rotateWithRightChild(AVLk3);
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
        int height = height(currentTree);//Gets height of Binary Tree

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
}
