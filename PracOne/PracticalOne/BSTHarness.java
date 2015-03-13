package PracticalOne;

import java.util.Scanner;
/**
 * Asks the user to input a sequence of integers, constructs a SimpleBST from them, and prints
 * it out.
 * 
 * @author Stephan Jamieson 
 * @version 25/2/2015
 */
public class BSTHarness {


    public static void main(String args[]) {
        System.out.print("Enter a comma separated sequence of node values: ");
        Scanner scanner = new Scanner(System.in);
        scanner = new Scanner(scanner.nextLine()).useDelimiter("\\s*,\\s*");

        SimpleBST tree = new  SimpleBST();
        
        while (scanner.hasNextInt() ){
            tree.insert(scanner.nextInt());
        }
        SimpleBST.print(tree, new SimpleTreeWriterImpl(System.out));
    }
}
