package PracticalOne;

import java.io.*;
import java.util.*;

/**
 * Created by scrsha001 on 2015/02/27.
 * Shaaheen Sacoor
 * Main Program that implements the User Interface that will accept user input for two trees
 * and proceed to print them out and check for similarity (Does this by using SimpleTreeWriterImpl and TreeUtils)
 */

public class MainClass {

    public static void main(String args[]) {
        System.out.print("Enter a comma separated list of numbers for tree one: ");
        Scanner scanner = new Scanner(System.in);
        scanner = new Scanner(scanner.nextLine()).useDelimiter("\\s*,\\s*");

        SimpleBST tree1 = new  SimpleBST();

        while (scanner.hasNextInt() ){
            tree1.insert(scanner.nextInt());
        }

        //Same code as above as user will be asked to input another tree in a similar way
        System.out.print("Enter a comma separated list of numbers for tree two: ");

        Scanner scanner2 = new Scanner(System.in);
        scanner2 = new Scanner(scanner2.nextLine()).useDelimiter("\\s*,\\s*");

        SimpleBST tree2 = new  SimpleBST();

        while (scanner2.hasNextInt() ){
            tree2.insert(scanner2.nextInt());
        }

        //Created PrintStream objects so that methods know which file to save to
        //Which is the root directory of project T1.out or T2.out

        PrintStream tree1Stream = null;
        PrintStream tree2Stream = null;

        try {
            tree1Stream = new PrintStream(new File("T1.out"));
            tree2Stream = new PrintStream(new File("T2.out"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Prints Trees one after the other using methods from other classes
        System.out.println("Tree one:");
        SimpleBST.print(tree1, new SimpleTreeWriterImpl(tree1Stream));

        System.out.println();

        System.out.println("Tree two:");
        SimpleBST.print(tree2, new SimpleTreeWriterImpl(tree2Stream));

        System.out.println(); //leave a line for formatting reasons

        //will use method in treeUtils to check if trees are similar
        if (tree1.similar(tree2)){
            System.out.println("The trees are similar.");
        }
        else{
            System.out.println("The trees are not similar.");
        }


    }
}
