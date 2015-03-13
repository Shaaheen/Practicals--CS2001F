package PracTwoSource;

import java.util.*;

/**
 * Utility procedures for AVL tree structures.
 * 
 * @author Stephan Jamieson
 * @version 25/2/2015
 *
 * Modified by SCRSHA001 at 06/03/2015
 * Shaaheen Sacoor
 * Implemented code for empty methods contain, insert,delete,find, rotate methods and double rotate methods and added printing methods from previous assignment
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

    //Deleted old levels methods because those were used by my printing algorithm for previous tasks

    /** 
     * Determine whether the given tree structure contains the given key.
     */
    public static boolean contains(AVLTreeNode node, String keyWord) {
        int key = Character.toUpperCase(keyWord.charAt(0)) - 64;
        if ((node.getKey()) == key ){ //if node key is equal to key we want to find then return true

            return node.checkIfInAry(keyWord);
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

    //Will find the dictionary that contains the words starting with letter provided(keyWord) and prints all items in list out
    public static String find(AVLTreeNode node, String keyWord) {
        int key = Character.toUpperCase(keyWord.charAt(0)) - 64;
        if (node.getKey() == key){ //if node key is equal to key we want to find then return string containing items in dictionary
            return node.findString();
        }
        if (key < node.getKey()){ //if key searching for is smaller than current key then try go to the left subtree
            if (node.hasLeft()) { //go down left subtree
                return find(node.getLeft(), keyWord);
            }
            else { //if there is no subtree and key is smaller than current node key then there can't be key in tree, therefore print that it doesn't exist
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
     * Iterative for last 10%
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
                    inserted = true;
                    currNode = currNode.getLeft();
                }
            }
            else if (key > currNode.getKey()){ //Checks if key should be on the right side
                if (currNode.hasRight()){
                    currNode = currNode.getRight();
                }
                else{
                    currNode.setRight(new AVLTreeNode(key,newItem));
                    inserted = true;
                    currNode = currNode.getRight();
                }
            }
            balanceStack.add(currNode); //add node to stack to keep track of insert path
        }
        node = checkBalance(balanceStack,node); //balances node

        return node;
    }

    // deletes given item from tree
    public static AVLTreeNode delete(AVLTreeNode node, String deleteItem) {

        int key = Character.toUpperCase(deleteItem.charAt(0)) - 64; //gets key value of delete item
        Stack<AVLTreeNode> deleteStack = new Stack<AVLTreeNode>(); //delete stack so as to follow delete path
        AVLTreeNode currNode = node; //current working node = root node

        for (int i = 0; i < height(node); i++){ //loop till height of tree reached
            deleteStack.add(currNode); //add node to stack
            if (key == currNode.getKey()){ //if found the node with the same first letter
                if (currNode.getDictionary().size() > 1){ //if theres multiple words in dictionary then just delete from dictionary list
                    if (currNode.getDictionary().contains(deleteItem)){ //if delete item is in dictionary
                        currNode.getDictionary().remove(deleteItem); //remove from dictionary list
                    }
                    else { //if not in dictionary
                        System.out.println("Item not does not exist in Tree"); //print that it doesn't exist
                    }

                    return node; //return root node back since there should be no change to tree
                }
                //if deleted item is equal the first item in list , then delete that node
                //Can say get(0) as if dictionary was bigger than 1 then would follow if statement from above so there has to be one value in dict or it wouldn't exist
                else if (deleteItem.equals(currNode.getDictionary().get(0))){
                    if (deleteStack.size()  == 1){ //if delete stack is one then we were told to delete root node so have to change root node
                        node = deleteTheNode(currNode); // set root node to new root node
                        return node; //return node straight away
                    }
                    else if (deleteStack.size() > 1){ //if not deleting root node
                        deleteStack.pop(); //delete node at top of stack as that was node to be deleted
                        AVLTreeNode parentNode = deleteStack.peek(); //gets parent node of node to be deleted
                        //Checks which side to set the new node from parent
                        if (parentNode.hasLeft() && !parentNode.hasRight()){ //node to be deleted must be on left side
                            parentNode.setLeft(deleteTheNode(currNode)); //sets left
                        }
                        else if (!parentNode.hasLeft() && parentNode.hasRight()){ //must be on right
                            parentNode.setRight(deleteTheNode(currNode)); //sets right
                        }
                        else if (parentNode.hasLeft() && parentNode.hasRight()){ //if parent of deleted node has two children
                            //to be deleted node has to be either left or right
                            if (parentNode.getLeft().getKey() == currNode.getKey()){ //to be deleted node is on left
                                parentNode.setLeft(deleteTheNode(currNode)); //sets left
                            }
                            else{ //on right
                                parentNode.setRight(deleteTheNode(currNode)); //sets right
                            }
                        }
                    }
                    System.out.println("Deletetion completed successfully");
                    break; //delete complete break out of loop

                }
                else{ //if word not in tree
                    System.out.println("Item does not exist in Tree");
                    break;
                }
            }
            else if (key < currNode.getKey()){ //Goes left if to be deleted key is smaller than current node key
                if (currNode.hasLeft()){
                    currNode = currNode.getLeft();
                }
                else{ //if no left value then item doesn't exist in tree
                    System.out.println("Item does not exist in Tree");
                    break;
                }
            }
            else if (key > currNode.getKey()){ //same as left
                if (currNode.hasRight()){
                    currNode = currNode.getRight();
                }
                else{
                    System.out.println("Item does not exist in Tree");
                    break;
                }
            }
        }

        node = checkBalance(deleteStack,node); //rebalances tree before completes operation
        return node;

    }

    //Method that goes through each of the 4 possible cases when deleting a node
    public static AVLTreeNode deleteTheNode(AVLTreeNode toBeDeleted){
        if (!toBeDeleted.hasRight() && !toBeDeleted.hasLeft()){ //if a leaf node then return nothing
            return null;
        }
        else if (!toBeDeleted.hasRight() && toBeDeleted.hasLeft()){ //if it has left node only then replace it with that
            return toBeDeleted.getLeft();
        }
        else if(toBeDeleted.hasRight() && !toBeDeleted.hasLeft()){ //similar to above
            return toBeDeleted.getRight();
        }
        else{ //if to be deleted node has two children
            AVLTreeNode successor = toBeDeleted.getRight(); //gets the successor which will replace to be deleted node
            AVLTreeNode beforeSuccessor = toBeDeleted; //get before successor node so as to get rid of successor nodes old place
            //Successor is the left most node from the right subtree
            if (successor.hasLeft()){
                beforeSuccessor = beforeSuccessor.getRight();
                successor = successor.getLeft();
            }
            while (successor.hasLeft()){
                successor = successor.getLeft();
                beforeSuccessor = beforeSuccessor.getLeft();
            }
            //if there is a succesor node that is not just to the right of the to be deleted node
            if (beforeSuccessor.getKey() != toBeDeleted.getKey()){
                beforeSuccessor.setLeft(successor.getRight()); //set the left of the parent of successor to the right of successor node sp as to not lose any nodes
                toBeDeleted.changeNodeDet(successor); //change the details of to be deleted node
            }
            else{
                successor.setLeft(toBeDeleted.getLeft()); //change the left node of successor to left node of deleted so as to not leave out nodes
                return successor; //return successor 
            }

        }

        return toBeDeleted;

    }

    public static AVLTreeNode checkBalance(Stack<AVLTreeNode> stackBalance, AVLTreeNode root){
        //refreshAllHts(root);
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
                    return newRootNode;
                }
                else{
                    if (beforeImbalanceNode.getKey() >= currStackNode.getKey() ){
                        beforeImbalanceNode.setLeft(rebalanceLeft(currStackNode, currStackNode.getLeft().getKey()));
                    }
                    else{
                        beforeImbalanceNode.setRight(rebalanceLeft(currStackNode, currStackNode.getLeft().getKey()));
                    }

                }
                //refreshAllHts(root);
                break;
            }
            else if (currStackNode.getBalanceFactor() < -1){
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
                //refreshAllHts(root);
            }
        }
        return stackBalance.get(0);
    }

    
    /*public static void refreshAllHts(AVLTreeNode allTreeNodes) {
        //System.out.println("Every node here " + allTreeNodes.getKey());
        allTreeNodes.setHeight(heightSetter(allTreeNodes));
        //System.out.println("Every node here " + allTreeNodes);
        if (allTreeNodes.hasLeft()) {
            refreshAllHts(allTreeNodes.getLeft());
        }
        if (allTreeNodes.hasRight()) {
            refreshAllHts(allTreeNodes.getRight());
        }
    }*/

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
