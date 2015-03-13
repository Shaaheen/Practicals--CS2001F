package PracTwoSource;
import java.io.PrintStream;
/**
 * Abstract definition of an object for printing a simple binary tree structure. 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface SimpleTreeWriter{

    public void setDestination(PrintStream stream);
    
    public void write(AVLTreeNode tree); 
}
