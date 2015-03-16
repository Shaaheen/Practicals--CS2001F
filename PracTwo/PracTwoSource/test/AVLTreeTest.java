package PracTwoSource.test;

import PracTwoSource.AVLTree;
import PracTwoSource.AVLTreeNode;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class AVLTreeTest extends TestCase {
    AVLTree testTree = new AVLTree();
    String path = new File(".").getAbsolutePath();


    @Test
    public void testCase1() throws Exception{
        //Set up
        testTree.insert("Alpha");
        testTree.insert("Beta");
        testTree.insert("Blue");
        testTree.insert("Charlie");
        testTree.insert("Delta");
        testTree.delete("Delta");

        //Check if contains method works
        boolean containsAll = false;
        if (testTree.contains("Alpha") && testTree.contains("Beta") && testTree.contains("Charlie")){
            containsAll = true;
        }
        boolean expected = true;
        assertEquals(expected,containsAll);

        //Checks if Find function works correctly
        String expectedOutput = "(2)(Beta, Blue)";
        assertEquals(expectedOutput, testTree.find("B"));
        expectedOutput = "(1)(Alpha)";
        assertEquals(expectedOutput,testTree.find("A"));

        //Test to see if tree is structured correctly and that insert & delete works correctly

          //Create file from methods to test against expected
        System.out.println(new File(".").getAbsolutePath());
        File testFile = new File( path + "//PracTwoSource//test//TestTxtFiles//testFile1.txt");
        PrintStream streamtoFile = new PrintStream(testFile);
        testTree.print(streamtoFile);

          //Get expected Test File
        File expectedTestFile = new File(path + "//PracTwoSource//test//TestTxtFiles//expectedTestFile1.txt");

          //Turn File into a string so we can compare the content of both files
        String actualFileString = turnToString(testFile);
        String expectedFileString = turnToString(expectedTestFile);

          //Check if tree structures are the same
        assertEquals(expectedFileString,actualFileString);

        tearDown();
    }

    //This Case will test the evidence case given for Task 4 as evidence that it works
    @Test
    public void testCase2() throws Exception{
        //Set up - From Task 4 evidence
        testTree.insert("alpha"); testTree.insert("beta"); testTree.insert("gamma"); testTree.insert("delta");
        testTree.insert("epsilon"); testTree.insert("zeta"); testTree.insert("eta"); testTree.insert("theta");
        testTree.insert("iota"); testTree.insert("kappa"); testTree.insert("lambda"); testTree.insert("mu");
        testTree.insert("ka"); testTree.insert("sa"); testTree.insert("ta"); testTree.insert("na");
        testTree.insert("ha"); testTree.insert("ma"); testTree.insert("ra"); testTree.insert("wa");

        //Check if contains method works
        boolean containsAll = false;
        if (testTree.contains("zeta") && testTree.contains("lambda") && testTree.contains("iota")){
            containsAll = true;
        }
        boolean expected = true;
        assertEquals(expected,containsAll);

        //Checks if Find function works correctly
        String expectedOutput = "(2)(kappa, ka)";
        assertEquals(expectedOutput, testTree.find("K"));
        expectedOutput = "(1)(delta)";
        assertEquals(expectedOutput,testTree.find("D"));

        //Test to see if tree is structured correctly and that insert & delete works correctly

        //Create file from methods to test against expected
        File testFile = new File(path + "//PracTwoSource//test//TestTxtFiles//testFileAVL5.txt");
        PrintStream streamtoFile = new PrintStream(testFile);
        testTree.print(streamtoFile);

        //Get expected Test File
        File expectedTestFile = new File(path + "//PracTwoSource//test//TestTxtFiles//expectedAVL5.txt");

        //Turn File into a string so we can compare the content of both files
        String actualFileString = turnToString(testFile);
        String expectedFileString = turnToString(expectedTestFile);

        //Check if tree structures are the same
        assertEquals(expectedFileString,actualFileString);

        //Will provide evidence for the AVL6 result
            //Checks if deletions are correct
        testTree.delete("eta");
        testTree.delete("theta");
        testTree.delete("iota");
        testTree.delete("kappa");
        testTree.delete("lambda");

        //Test to see if tree is structured correctly and that insert & delete works correctly

        //Create file from methods to test against expected
        File newTestFile = new File(path + "//PracTwoSource//test//TestTxtFiles//testFileAVL6.txt");
        PrintStream streamtoFileNew = new PrintStream(newTestFile);
        testTree.print(streamtoFileNew);

        //Get expected Test File
        File expectedTestFileNew = new File(path + "//PracTwoSource//test//TestTxtFiles//expectedAVL6.txt");

        //Turn File into a string so we can compare the content of both files
        String actualFileStringNew = turnToString(newTestFile);
        String expectedFileStringNew = turnToString(expectedTestFileNew);

        //Check if tree structures are the same
        assertEquals(expectedFileStringNew,actualFileStringNew);

        tearDown();
    }

    public void tearDown() throws Exception {
        testTree = new AVLTree();
    }

    public static String turnToString(File fileToString) throws IOException {
        FileInputStream fin =  new FileInputStream(fileToString);
        BufferedReader myInput = new BufferedReader(new InputStreamReader(fin));
        String returnString = "";
        String thisLine;
        while ((thisLine = myInput.readLine()) != null) {
            returnString = returnString + thisLine + "\r\n";
        }

        return returnString;

    }



}