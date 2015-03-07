package PracTwoSource;

import java.io.PrintStream;
import java.util.*;

/**
 * Created by scrsha001 on 2015/02/27.
 * Shaaheen Sacoor
 * provides a method that implements a printing algorithm for the tree
 */
public class SimpleTreeWriterImpl implements SimpleTreeWriter{

    String wholeTree = ""; //Will contain whole tree inside string so whole tree can be printed to a file as well as the program
    PrintStream streamer; //instance variable of printstream object

    //contructor that accepts printstream parameter
    public SimpleTreeWriterImpl(PrintStream out) {
        streamer = out; //initilises instance variables

    }

    //method that will print to file passed to it through its parameter
    public void setDestination(PrintStream stream){
        stream.print(wholeTree); //prints to stream which has been previously assigned to a specific file

    }

    //Method to Print out Binary Trees in correct format and to write same output to a file
    public void write(AVLTreeNode currentTree) {
        AVLTreeNode[] rootLevel = TreeUtils.createLvlZero(currentTree); //Use TreeUtils method to create a level 0 list
        ArrayList NodeLevels = TreeUtils.createSubTreeList(rootLevel); //Use TreeUtils to get an ArrayList that contains
                                                                       //a list for every level in Binary Tree
        int height = currentTree.getHeight();//Gets height of tree
        int leadingStrNum = 0;
        int betweenStrNum = 0;  //These variable will be used to store the amount of space that there should be between numbers
        String betweenStr;
        String leadingStr;
        String branchRep = "";
        int resetter = 0;
        String tmpStr = "";

        //for loop will go through each level and then each item in that level
        for (int y = 0; y < currentTree.getHeight(); y++){ //each level
            ArrayList currentPrintList = (ArrayList) NodeLevels.get(y); //gets list for that level
            resetter = 0; //variable used for the tree representation string, it is used to see when to move to a new line
            branchRep = ""; //string that contains the branch representation for the children Nodes
            String currLine = ""; //String containing the current line of nodes
            for (int x = 0; x < currentPrintList.size(); x++ ){ //each item
                AVLTreeNode workingNode = (AVLTreeNode) currentPrintList.get(x); //retrieves Binary Node at current loop index

                betweenStrNum = (int) (Math.pow(2,height - y) - 1); //Calculates the amount of space that should be between items in that level
                leadingStrNum = (int) betweenStrNum/2; //Calculates amount of space that should be at the ends of each level
                betweenStr = createSpaces(betweenStrNum); //Creates a string of correct amount of spaces
                leadingStr = createSpaces(leadingStrNum);
                if (x == 0){
                    currLine = currLine + leadingStr; //if beginning of line then add beginning space
                }
                if (workingNode != null){ //if its not a place holder
                    String itemFormatted = String.format("%-6s",workingNode.toString()); //format number so the width will always be 3 spaces
                    currLine = currLine + itemFormatted;
                    tmpStr = tmpStr + " The Key is " + workingNode.getKey() + "  the height is " + workingNode.getHeight() + "\r\n";
                }
                else{
                    currLine = currLine + "      ";//if  a placeholder then add three spaces
                }

                //For representation of branches
                if (workingNode != null) {
                    //Variable that will be used to find out if the current Node has a left node value
                    boolean leftAlready = false;
                    //will check if there is a left node
                    if (workingNode.hasLeft()) {
                        if (resetter == 0) { //if hasn't already then moved to new line, if resetter is bigger than 0 then it already is at new line
                            branchRep = branchRep + "\r\n"; //moved to new line
                            resetter = resetter + 1; //done to make sure it won't moved to the next line while still working with same level
                        }
                        leftAlready = true; //current node has done a representation for left node]
                        //Creates single spaces to fill up the current length of currentLine string variable,
                        //so spaces will be added right up till it is below the node number then place the star on the left of the number
                        branchRep = branchRep + createSpaces2(currLine.length() - branchRep.length() - 6) + "*";
                    }
                    if (workingNode.hasRight()) {
                        if (resetter == 0) { //if hasn't already moved to next line then move
                            branchRep = branchRep + "\r\n";
                            resetter = resetter + 1;
                        }
                        if (leftAlready){ //if already went through the left node then add a star on the right of the number
                            branchRep = branchRep + "      *";
                        }
                        else{
                            //same as above but will place star on right
                            branchRep = branchRep + createSpaces2(currLine.length()- branchRep.length() + 3) + "*";
                        }

                    }
                }
                currLine = currLine + betweenStr + "";
            }
            wholeTree = wholeTree + currLine; //add the line to main string
            wholeTree = wholeTree + branchRep;//add the branch representation line also
            wholeTree = wholeTree + "\r\n"; //Next level of Binary Tree so move to next line
        }
        System.out.print(tmpStr);
        setDestination(streamer); //passes streamer to setDestination method to write tree to file or screen

    }
    //Method to create strings of spaces with length given
    public static String createSpaces(int numOfspaces){
        String spaces = "";
        for (int i = 0; i < numOfspaces; i++){
            spaces = spaces + "      "; //each block is 3 spaces
        }
        return spaces;
    }

    //Method to create single spaces
    public static String createSpaces2(int numOfspaces){
        String spaces = "";
        for (int i = 0; i < numOfspaces; i++){
            spaces = spaces + " "; //each block is 3 spaces
        }
        return spaces;
    }

}
//
