import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Shaaheen on 4/30/2015.
 */
public class EfficiencyCheck {
    
    int[] LPList = new int[3];
    int[] QPList = new int[3];
    int[] SCList = new int[3];
    
    public static void main(String[] args) throws IOException {
        Scanner user_input = new Scanner(System.in);
        int caseIn = 0;
        
        while (caseIn != 4) {
            System.out.println("1)Print total probes for each case  2)Check Percentage difference 3)Print total number of probes needed for word searches 4)Quit");
            caseIn = user_input.nextInt();

            if (caseIn == 1) {
                double load = 0.5;
                for (int j = 0; j < 3; j++){
                    Dictionary[] hashTbles = getDictionarys(load);
                    System.out.println("LP: Total number of Probes is " + ((LPHashtable) hashTbles[0]).totalProbes + " for a load factor " + load);
                    if (hashTbles[1] != null)
                        System.out.println("QP: Total number of Probes is " + ((QPHashtable) hashTbles[1]).totalProbes + " for a load factor " + load);
                    else
                        System.out.println("QP: Failed at load factor " + load);
                    System.out.println("SC: Total number of Probes is " + ((SCHashtable) hashTbles[2]).totalProbes + " for a load factor " + load);
                    System.out.println();
                    load = load + 0.25;
                }
            }
            else if (caseIn == 2){
                System.out.println("Percentage differences ");
                double load = 0.5;
                for (int j = 0; j < 3; j++){
                    Dictionary[] hashTbles = getDictionarys(load);
                    int LPProbes = ((LPHashtable) hashTbles[0]).totalProbes;
                    int SCProbes = ((SCHashtable) hashTbles[2]).totalProbes;
                    if (hashTbles[1] !=null) {
                        int QPProbes = ((QPHashtable) hashTbles[1]).totalProbes;
                        System.out.println("Quadratic and Linear Probing : " + Math.abs(Math.round((((double) (QPProbes - LPProbes) / QPProbes) * 100))) + "% for a load factor " + load);
                    }
                    else
                        System.out.println("QP: Failed at load factor " + load);

                    System.out.println("Sequential chaining and Linear Probing : " + Math.abs(Math.round((((double)SCProbes - LPProbes)/SCProbes)*100)) + "% for a load factor " + load);

                    if (hashTbles[1]  != null) {
                        int QPProbes = ((QPHashtable) hashTbles[1]).totalProbes;
                        System.out.println("Sequential chaining and Quadratic Probing : " + Math.abs(Math.round((((double) SCProbes - QPProbes) / SCProbes) * 100)) + "% for a load factor " + load);
                    }
                    else
                        System.out.println("QP: Failed at load factor " + load);
                    System.out.println();
                    load = load + 0.25;
                }
            } else if (caseIn == 3){
                for (double load = 0.5; load <= 1 ; load = load + 0.25){
                    Dictionary[] hashTables = getDictionarys(load);

                }
                System.out.println(SearchPerformanceUtil.getNonsenseString());
            }
        }
    }
    
    public static Dictionary[] getDictionarys(double load) throws IOException {
        Dictionary[] hashTables = new Dictionary[3];
        
        LPHashtable LP = new LPHashtable(getSize(load));
        LP.performanceTest = true;
        FileUtil.load(LP, "lexicon.txt");

        QPHashtable QP = new QPHashtable(getSize(load));
        QP.performanceTest = true;
        try {
            FileUtil.load(QP, "lexicon.txt");

        } catch (IndexOutOfBoundsException e) {
            QP = null;
        }


        SCHashtable SC = new SCHashtable(getSize(load));
        FileUtil.load(SC, "lexicon.txt");

        hashTables[0] = LP;
        hashTables[1] = QP;
        hashTables[2] = SC;
        
        return hashTables;
    }
    
    private static int getSize(double loadFactor){
        return ((int) (3739/loadFactor)); // 3739 is the number of entries from the lexicon list
    }
}
