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

    /** 
     * Determine whether the given tree structure contains the given key.
     */
    public static boolean contains(AVLTreeNode node, String keyWord) {
        int key = Character.toUpperCase(keyWord.charAt(0)) - 64;
        if ((node.getKeyWord()).equals(keyWord)){ //if node key is equal to key we want to find then return true
            return true;
        }
        if (key < node.getKey()){ //if key searching for is smaller than current key then try go to the left subtree
            if (node.hasLeft()) { //go down left subtree
                return contains(node.getLeft(), keyWord);
            }
            else { //if there is no subtree and key is smaller than current node key then there can't be key in tree, therefore false
                return false;
            }
        }
        else {
            if (node.hasRight()){ //same as left
                return contains(node.getRight(),keyWord);
            }
            else{
                return false;
            }
        }

    }

    public static String find(AVLTreeNode node, String keyWord) {
        int key = Character.toUpperCase(keyWord.charAt(0)) - 64;
        if (node.getKey() == key){ //if node key is equal to key we want to find then return true
            return node.findString();
        }
        if (key < node.getKey()){ //if key searching for is smaller than current key then try go to the left subtree
            if (node.hasLeft()) { //go down left subtree
                return find(node.getLeft(), keyWord);
            }
            else { //if there is no subtree and key is smaller than current node key then there can't be key in tree, therefore false
                return "Does not exist in tree";
            }
        }
        else {
            if (node.hasRight()){ //same as left
                return find(node.getRight(), keyWord);
            }
            else{
                return "Does not exist in tree";
            }
        }

    }


    /**
     * Iterative implementation of insert on an AVLTreeNode structure.
     */
    public static AVLTreeNode insert(AVLTreeNode node, String newItem) {
        int key = Character.toUpperCase(newItem.charAt(0)) - 64;
        boolean inserted = false; //used to check if a node has been added yet
        AVLTreeNode currNode = node; //current node working with, changes over each loop
        Stack balanceStack = new Stack<AVLTreeNode>(); //Stack that acts to store the path taken to add object so as to balance tree easily
        balanceStack.add(currNode); //add root node to stack
        while (!inserted){ //while nothing has been inserted keep going
            if (node == null){ //if empty tree then create root node
                node = new AVLTreeNode(key,newItem); //create root node
                node.setHeight(heightSetter(node)); //set the height of the node
                return node;//end method and return root
            }
            else if (currNode.getKey() == key){ //if duplicate then do nothing
                currNode.addToDict(newItem);
                return node;
            }
            else if (key < currNode.getKey()){ //if new key is smaller than current node key value then try go left
                if (currNode.hasLeft()){ //try go left
                    currNode = currNode.getLeft();
                }
                else{ //if no left value already then add new node here
                    currNode.setLeft(new AVLTreeNode(key,newItem));
                    currNode.getLeft().setHeight(heightSetter(currNode.getLeft()));
                    currNode.setHeight(heightSetter(currNode));
                    inserted = true;
                    currNode = currNode.getLeft();
                }
            }
            else if (key > currNode.getKey()){
                if (currNode.hasRight()){
                    currNode = currNode.getRight();
                }
                else{
                    currNode.setRight(new AVLTreeNode(key,newItem));
                    currNode.getRight().setHeight(heightSetter(currNode.getRight()));
                    node.setHeight(heightSetter(node));
                    currNode.setHeight(heightSetter(currNode));
                    inserted = true;
                    currNode = currNode.getRight();
                }
            }
            balanceStack.add(currNode);
        }
        node = checkBalance(balanceStack,node);
        refreshAllHts(node);
        //node = checkBalance(balanceStack,node);
        //node.setHeight(heightSetter(node));

        return node;
    }

    public static AVLTreeNode delete(AVLTreeNode node, String deleteItem) {
        int key = Character.toUpperCase(deleteItem.charAt(0)) - 64;
        Stack<AVLTreeNode> deleteStack = new Stack<AVLTreeNode>();
        AVLTreeNode currNode = node;
        for (int i = 0; i < node.getHeight(); i++){
            deleteStack.add(currNode);
            if (key == currNode.getKey()){
                if (currNode.getDictionary().size() > 1){
                    currNode.getDictionary().remove(deleteItem);
                }
                else if (deleteItem.equals(currNode.getDictionary().get(0))){
                    if (deleteStack.size()  == 1){
                        node = deleteTheNode(currNode);
                    }
                    else if (deleteStack.size() > 1){
                        deleteStack.pop();
                        AVLTreeNode parentNode = deleteStack.peek();
                        if (parentNode.hasLeft() && !parentNode.hasRight()){
                            parentNode.setLeft(deleteTheNode(currNode));
                        }
                        else if (!parentNode.hasLeft() && parentNode.hasRight()){
                            parentNode.setRight(deleteTheNode(currNode));
                        }
                        else if (parentNode.hasLeft() && parentNode.hasRight()){
                            if (parentNode.getLeft().getKey() == currNode.getKey()){
                                parentNode.setLeft(deleteTheNode(currNode));
                            }
                            else{
                                parentNode.setRight(deleteTheNode(currNode));
                            }
                        }
                    }
                    break;

                }
                else{
                    System.out.println("Item does not exist in Tree");
                    break;
                }
            }
            else if (key < currNode.getKey()){
                if (currNode.hasLeft()){
                    currNode = currNode.getLeft();
                }
                else{
                    System.out.println("Item does not exist in Tree");
                    break;
                }
            }
            else if (key > currNode.getKey()){
                if (currNode.hasRight()){
                    currNode = currNode.getRight();
                }
                else{
                    System.out.println("Item does not exist in Tree");
                    break;
                }
            }
        }

        node = checkBalance(deleteStack,node);
        refreshAllHts(node);
        return node;

    }

    public static AVLTreeNode deleteTheNode(AVLTreeNode toBeDeleted){
        if (!toBeDeleted.hasRight() && !toBeDeleted.hasLeft()){
            return null;
        }
        else if (!toBeDeleted.hasRight() && toBeDeleted.hasLeft()){
            return toBeDeleted.getLeft();
        }
        else if(toBeDeleted.hasRight() && !toBeDeleted.hasLeft()){
            return toBeDeleted.getRight();
        }
        else{
            AVLTreeNode successor = toBeDeleted.getRight();
            AVLTreeNode beforeSuccessor = toBeDeleted;
            if (successor.hasLeft()){
                beforeSuccessor = beforeSuccessor.getRight();
                successor = successor.getLeft();
            }
            while (successor.hasLeft()){
                successor = successor.getLeft();
                beforeSuccessor = beforeSuccessor.getLeft();
            }
            if (beforeSuccessor.getKey() != toBeDeleted.getKey()){
                beforeSuccessor.setLeft(successor.getRight());
                toBeDeleted.changeNodeDet(successor);
            }
            else{
                successor.setLeft(toBeDeleted.getLeft());
                return successor;
            }

        }

        return toBeDeleted;

    }

    public static AVLTreeNode checkBalance(Stack<AVLTreeNode> stackBalance, AVLTreeNode root){
        refreshAllHts(root);
        AVLTreeNode insertedNode = stackBalance.peek();
        //System.out.println("This is the inserted node " + insertedNode);
        AVLTreeNode currStackNode;
        AVLTreeNode beforeImbalanceNode = root;
        //System.out.println("the node at bottom of stack is " + stackBalance.get(stackBalance.size() - 1));
        //System.out.println("the node at top of stack is " + stackBalance.get(0));
        //System.out.println("the stack size is " + stackBalance.size());*/
        for (int i = (stackBalance.size()-1); i >= 0; i--){
            currStackNode = stackBalance.get(i);
            if (i>0){

                beforeImbalanceNode = stackBalance.get(i-1);
            }
            if (currStackNode.getBalanceFactor() > 1){
                if (i == 0){
                    AVLTreeNode newRootNode = rebalanceLeft(currStackNode, currStackNode.getLeft().getKey());
                    newRootNode.setHeight(heightSetter(newRootNode));
                    return newRootNode;
                }
                else{
                    if (beforeImbalanceNode.getKey() >= currStackNode.getKey() ){
                        beforeImbalanceNode.setLeft(rebalanceLeft(currStackNode, currStackNode.getLeft().getKey()));
                        beforeImbalanceNode.getLeft().setHeight(heightSetter(beforeImbalanceNode.getLeft()));
                        if (beforeImbalanceNode.getLeft().hasLeft()){
                            beforeImbalanceNode.getLeft().getLeft().setHeight(heightSetter(beforeImbalanceNode.getLeft().getLeft()));
                        }
                        else if (beforeImbalanceNode.getLeft().hasRight()){
                            beforeImbalanceNode.getLeft().getRight().setHeight(heightSetter(beforeImbalanceNode.getLeft().getRight()));
                        }
                    }
                    else{
                        beforeImbalanceNode.setRight(rebalanceLeft(currStackNode, currStackNode.getLeft().getKey()));
                        beforeImbalanceNode.getRight().setHeight(heightSetter(beforeImbalanceNode.getRight()));
                        if (beforeImbalanceNode.getRight().hasLeft()){
                            beforeImbalanceNode.getRight().getLeft().setHeight(heightSetter(beforeImbalanceNode.getRight().getLeft()));
                        }
                        else if (beforeImbalanceNode.getRight().hasRight()){
                            beforeImbalanceNode.getRight().getRight().setHeight(heightSetter(beforeImbalanceNode.getRight().getRight()));
                        }
                    }

                }
                //refreshAllHts(root);
                break;
            }
            else if (currStackNode.getBalanceFactor() < -1){
                if (i == 0){
                    AVLTreeNode newRootNode = rebalanceRight(currStackNode, currStackNode.getRight().getKey());
                    newRootNode.setHeight(heightSetter(newRootNode));
                    return newRootNode;
                }
                else{
                    if (beforeImbalanceNode.getKey() <= currStackNode.getKey()){
                        beforeImbalanceNode.setRight(rebalanceRight(currStackNode, currStackNode.getRight().getKey()));
                        beforeImbalanceNode.getRight().setHeight(heightSetter(beforeImbalanceNode.getRight()));
                        if (beforeImbalanceNode.getRight().hasLeft()){
                            beforeImbalanceNode.getRight().getLeft().setHeight(heightSetter(beforeImbalanceNode.getRight().getLeft()));
                        }
                        else if (beforeImbalanceNode.getRight().hasRight()){
                            beforeImbalanceNode.getRight().getRight().setHeight(heightSetter(beforeImbalanceNode.getRight().getRight()));
                        }
                    }
                    else{
                        beforeImbalanceNode.setLeft(rebalanceRight(currStackNode, currStackNode.getRight().getKey()));
                        beforeImbalanceNode.getLeft().setHeight(heightSetter(beforeImbalanceNode.getLeft()));
                        if (beforeImbalanceNode.getLeft().hasLeft()){
                            beforeImbalanceNode.getLeft().getLeft().setHeight(heightSetter(beforeImbalanceNode.getLeft().getLeft()));
                        }
                        else if (beforeImbalanceNode.getLeft().hasRight()){
                            beforeImbalanceNode.getLeft().getRight().setHeight(heightSetter(beforeImbalanceNode.getRight().getRight()));
                        }
                    }

                }
                //refreshAllHts(root);
            }
        }
        return stackBalance.get(0);
    }

    
    public static void refreshAllHts(AVLTreeNode allTreeNodes) {
        //System.out.println("Every node here " + allTreeNodes.getKey());
        allTreeNodes.setHeight(heightSetter(allTreeNodes));
        //System.out.println("Every node here " + allTreeNodes);
        if (allTreeNodes.hasLeft()) {
            refreshAllHts(allTreeNodes.getLeft());
        }
        if (allTreeNodes.hasRight()) {
            refreshAllHts(allTreeNodes.getRight());
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

    public static AVLTreeNode rebalanceLeft(AVLTreeNode ancestor,int parentKey){
        int left = (ancestor.getLeft().hasLeft() ? ancestor.getLeft().getLeft().getKey() : 0);
        if (ancestor.getLeft().hasLeft()){
            return rotateWithLeftChild(ancestor);
        }
        else{
            return doubleRotateWithLeftChild(ancestor);
        }
        /*if (left < parentKey){
            return rotateWithLeftChild(ancestor);
        }
        else{
            return doubleRotateWithLeftChild(ancestor);
        }*/
    }
    
    public static AVLTreeNode rebalanceRight(AVLTreeNode ancestor,int parentKey){
        int right = (ancestor.getRight().hasRight() ? ancestor.getRight().getRight().getKey() : 0);
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
        
        AVLTreeNode AVLk1 = AVLk2.getLeft();
        //System.out.println("Parameter(k2) is " + AVLk2.getKey());
        //System.out.println("k1 is " + AVLk1.getKey());
        AVLk2.setLeft(AVLk1.getRight());
        AVLk1.setRight(AVLk2);
        //System.out.println("Centre = " + AVLk1.getKey() + "Right = " + AVLk1.getRight() + "Left is " + AVLk1.getLeft());
        AVLk1.setHeight(AVLk1.getHeight()+1);
        AVLk1.getRight().setHeight(AVLk1.getRight().getHeight() - 1);
        if (AVLk1.hasLeft()){
            AVLk1.getLeft().setHeight(AVLk1.getLeft().getHeight() - 1);
        }
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
