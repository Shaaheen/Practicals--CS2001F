package PracticalOne;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by scrsha001 on 2015/02/27.
 * Shaaheen Sacoor
 * provides methods for: comparing two trees for similarity, creating a level 0 node list
 * from a tree, creating a level N+1 node list given a level N list.
 */
public class TreeUtils {

    //Method that will check if trees are similar in shape
    public static boolean similar(BinaryTreeNode T1, BinaryTreeNode T2){

        if (T1 == null && T2 ==null){ //If both trees are empty then both trees are the same
            return true;
        }
        else if (((T1 !=null)&&(T2 == null)) || ((T1 ==null)&&(T2 != null))){ //if one tree is empty and the other isn't then they can't be the same
            return false;
        }

        //Bunch of statements that will end recursion and return false if can find that tree is not similar
        if (T1.hasLeft() && !T2.hasLeft()){ //if one tree has a left subtree and one doesn't then return false
            return false;
        }
        if (!T1.hasLeft() && T2.hasLeft()){ //similar to above
            return false;
        }
        if (T1.hasRight() && !T2.hasRight()){
            return false;
        }
        if (!T1.hasRight() && T2.hasRight()){
            return false;
        }

        //if both trees have both left and right sides then use a recursive sequence to traverse through tree
        if (T1.hasLeft() && T2.hasLeft() && T1.hasRight() && T2.hasRight()) {
            return ((similar(T1.getLeft(), T2.getLeft())) && (similar(T1.getRight(), T2.getRight())));
        }
        else if (T1.hasLeft() && T2.hasLeft()){
            return ((similar(T1.getLeft(), T2.getLeft())));
        }
        else if (T1.hasRight() && T2.hasRight()){
            return ((similar(T1.getRight(), T2.getRight())));
        }
        else{
            return true;
        }




    }

    //method that will return a list of nodes in the first level
    public static BinaryTreeNode[] createLvlZero(BinaryTreeNode root){
        BinaryTreeNode[] levelZero = new BinaryTreeNode[1];
        levelZero[0] = root;
        return levelZero;
    }

    //Method to create a Arraylist that contains a list for each level in a Binary Tree
    public static ArrayList createSubTreeList(BinaryTreeNode[] parentList){
        BinaryTreeNode currentTree = parentList[0]; //Gets root Node from List
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
            BinaryTreeNode currTreeNode = (BinaryTreeNode) NodesQueue.pop();
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
