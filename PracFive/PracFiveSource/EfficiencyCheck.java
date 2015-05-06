package PracFiveSource;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Shaaheen on 4/30/2015.
 * Program to check the efficiency statistics of the implemented hash tables
 */
public class EfficiencyCheck {

    public static void main(String[] args) throws IOException {
        Scanner user_input = new Scanner(System.in);
        int choice=0;
        //Loops until user wants to quit
        while (choice != 3) {
            System.out.println("Check Efficiency of 1)Load Performance or 2)Search Performance or 3)Quit");
            choice = user_input.nextInt();
            int caseIn = 0;
            if (choice == 1) {
                while (caseIn!=3) {
                    System.out.println("1)Print total probes for each case  2)Check Percentage difference 3)Back");
                    caseIn = user_input.nextInt();
                    if (caseIn == 1) {
                        //Goes through each load factor
                        for ( double load = 0.5; load <= 1; load = load + 0.25) {
                            //Gets hashtables for each load factor loaded with the lexicon dictionary
                            Dictionary[] hashTbles = getDictionarys(load);

                            //Prints the total number of probes used to insert all of lexicon words and definitions
                            System.out.println("Total number of probes used to insert all of lexicon words and definitions");
                            System.out.println("LP: Total number of Probes is " + ((LPHashtable) hashTbles[0]).totalProbes + " for a load factor " + load);
                            if (hashTbles[1] != null)
                                System.out.println("QP: Total number of Probes is " + ((QPHashtable) hashTbles[1]).totalProbes + " for a load factor " + load);
                            else
                                System.out.println("QP: Failed at load factor " + load);
                            System.out.println("SC: Total number of Probes is " + ((SCHashtable) hashTbles[2]).totalProbes + " for a load factor " + load);
                            System.out.println();
                        }
                    } else if (caseIn == 2) {
                        System.out.println("Percentage differences ");
                        double load = 0.5;
                        for (int j = 0; j < 3; j++) {
                            //Same as above but calculates the differences
                            Dictionary[] hashTbles = getDictionarys(load);
                            int LPProbes = ((LPHashtable) hashTbles[0]).totalProbes;
                            int SCProbes = ((SCHashtable) hashTbles[2]).totalProbes;
                            if (hashTbles[1] != null) {
                                int QPProbes = ((QPHashtable) hashTbles[1]).totalProbes;
                                System.out.println("Quadratic and Linear Probing : " + Math.abs(Math.round((((double) (QPProbes - LPProbes) / QPProbes) * 100))) + "% for a load factor " + load);
                                System.out.println("Sequential chaining and Quadratic Probing : " + Math.abs(Math.round((((double) SCProbes - QPProbes) / SCProbes) * 100)) + "% for a load factor " + load);
                            } else
                                System.out.println("QP: Failed at load factor " + load);

                            System.out.println("Sequential chaining and Linear Probing : " + Math.abs(Math.round((((double) SCProbes - LPProbes) / SCProbes) * 100)) + "% for a load factor " + load);

                            System.out.println();
                            load = load + 0.25;
                        }
                    }
                }
            }
            else if (choice == 2){
                int case2 = 0;
                while (case2 !=2 ) {
                    System.out.println("1)Output performance stats 2)Back");
                    case2 = user_input.nextInt();
                    if (case2 == 1) {
                        System.out.println("Loading ...");
                        for (double load = 0.5; load <= 1; load = load + 0.25) {
                            Dictionary[] hashTables = getDictionarys(load);
                            int LPTrials = 0;
                            int QPTrials = 0; //Counts the number of probes throughout all the 100 trials
                            int SCTrials = 0;
                            for (int j = 0; j < 20; j++) { //Goes through a 20 trials
                                for (int i = 0; i < 100; i++) { //Searches through a hundred random words
                                    if (i < 20) {
                                        String nonSense = FileUtil.getNonsenseString(); //Gets a nonsense word which will be searched for in all hashtables
                                        hashTables[0].getDefinitions(nonSense); //same nonsense word used for fairness
                                        hashTables[2].getDefinitions(nonSense);
                                        if (hashTables[1] != null)
                                            hashTables[1].getDefinitions(nonSense);
                                    } else {
                                        String rndLexicon = FileUtil.getRandomLexiconWord(); //Gets random word from lexicon dictionary
                                        hashTables[0].getDefinitions(rndLexicon); //Searches for nonsense word in all hashtables
                                        if (hashTables[1] != null)
                                            hashTables[1].getDefinitions(rndLexicon);
                                        hashTables[2].getDefinitions(rndLexicon);
                                    }
                                }
                                //Adds the total search probes for every trial together so as to average it later for an approximation
                                LPTrials = LPTrials + ((LPHashtable) hashTables[0]).searchProbes;
                                if (hashTables[1] != null)
                                    QPTrials = QPTrials + ((QPHashtable) hashTables[1]).searchProbes;
                                SCTrials = SCTrials + ((SCHashtable) hashTables[2]).searchProbes;
                            }
                            //Average all Values
                            LPTrials = LPTrials / 20;
                            QPTrials = QPTrials / 20;
                            SCTrials = SCTrials / 20;
                            //Prints out the Statistics
                            System.out.println("The total average number of probes over 20 trials to search for a different random 100 words at each trial for load factor " + load);
                            System.out.println("Searching in LP is " + LPTrials);
                            if (hashTables[1] != null)
                                System.out.println("Searching in QP is " + QPTrials);
                            else
                                System.out.println("QP failed at probing");
                            System.out.println("Searching in SC is " + SCTrials);
                            System.out.println();

                            System.out.println("Percentage difference");
                            if (hashTables[1] != null) {
                                System.out.println("Linear Probing and Quadratic Probing : " + Math.abs(Math.round((((double) (QPTrials - LPTrials)) / QPTrials) * 100)) + "%");
                                System.out.println("Sequential Chaining and Quadratic Probing : " + Math.abs(Math.round((((double) (SCTrials - QPTrials)) / SCTrials) * 100)) + "%");
                            }else{
                                System.out.println("QP failed - Can't work out percentage difference with Quadratic Probing");
                            }

                            System.out.println("Linear Probing and Sequential Chaining : " + Math.abs(Math.round((((double) (SCTrials - LPTrials)) / SCTrials) * 100)) + "%");
                            System.out.println();

                        }
                    }
                }
            }
        }
    }

    //Method to create all hashtables and insert all words from the lexicon dictionary into the hashtables
    public static Dictionary[] getDictionarys(double load) throws IOException {
        Dictionary[] hashTables = new Dictionary[3]; //3 hashtables

        LPHashtable LP = new LPHashtable(getSize(load));
        LP.performanceTest = true;
        FileUtil.load(LP, "lexicon.txt");

        QPHashtable QP = new QPHashtable(getSize(load));
        QP.performanceTest = true;
        try {
            FileUtil.load(QP, "lexicon.txt");

        } catch (IndexOutOfBoundsException e) {
            QP = null; //If Quadratic probing failes then put null value in place of QP hashtable
        }


        SCHashtable SC = new SCHashtable(getSize(load));
        FileUtil.load(SC, "lexicon.txt");

        hashTables[0] = LP;
        hashTables[1] = QP;
        hashTables[2] = SC;

       // System.out.println("The load factor here is " + ((QPHashtable) hashTables[1]).loadFactor());


        return hashTables;
    }

    //Method to get the size that a table should be for the given load factor - based on lexicon
    private static int getSize(double loadFactor){
        return (primeSize((int) (3739/loadFactor))); // 3739 is the number of entries from the lexicon list
    }

    //Returns a prime size number
    private static int primeSize(int size){
        while (!checkPrime(size)){ //keep adding size by 1 until finds a prime size
            size++;
        }
        return size;
    }

    //checks if a number is a prime number
    private static boolean checkPrime(int size){
        boolean prime = true;
        //if can divide by two then it is not prime
        if (size%2==0)  prime = false;
        //if not, then just check the odds as all even numbers are divisible by 2
        for(int i = 3; i*i <= size; i += 2) {
            if(size % i == 0)
                prime = false;
        }
        return prime;
    }
}
