import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Shaaheen on 4/30/2015.
 */
public class EfficiencyCheck {

    public static void main(String[] args) throws IOException {
        Scanner user_input = new Scanner(System.in);
        int caseIn = 0;
        int[] LPList = new int[3];
        int[] QPList = new int[3];
        int[] SCList = new int[3];
        int i = 0;
        for (double load = 0.5; load <= 1; load = load + 0.25) {
            LPHashtable LP = new LPHashtable(getSize(load));
            LP.performanceTest = true;
            FileUtil.load(LP, "lexicon.txt");
            LPList[i] = LP.totalProbes;

            QPHashtable QP = new QPHashtable(getSize(load));
            QP.performanceTest = true;
            try {
                FileUtil.load(QP, "lexicon.txt");
                QPList[i] = QP.totalProbes;

            } catch (IndexOutOfBoundsException e) {
                QPList[i] = -1;
            }


            SCHashtable SC = new SCHashtable(getSize(load));
            FileUtil.load(SC, "lexicon.txt");
            SCList[i] = SC.totalProbes;
            
            i++;
        }
        while (caseIn != 3) {
            System.out.println("1)Print total probes for each case  2)Check Percentage difference 3)Quit");
            caseIn = user_input.nextInt();

            if (caseIn == 1) {
                double load = 0.5;
                for (int j = 0; j < 3; j++){
                    System.out.println("LP: Total number of Probes is " + LPList[j] + " for a load factor " + load);
                    if (QPList[j] !=-1)
                        System.out.println("QP: Total number of Probes is " + QPList[j] + " for a load factor " + load);
                    else
                        System.out.println("QP: Failed at load factor " + load);
                    System.out.println("SC: Total number of Probes is " + SCList[j] + " for a load factor " + load);
                    System.out.println();
                    load = load + 0.25;
                }
            }
            else if (caseIn == 2){
                System.out.println("Percentage differences ");
                double load = 0.5;
                for (int j = 0; j < 3; j++){
                    if (QPList[j] !=-1) {
                        int x = (int) (((QPList[j] - LPList[j]) / QPList[j]) * 100);
                        System.out.println("Quadratic and Linear Probing : " + (((QPList[j] - LPList[j]) / QPList[j]) * 100) + "% for a load factor " + load);
                    }
                    else
                        System.out.println("QP: Failed at load factor " + load);

                    System.out.println("Sequential chaining and Linear Probing : " + (((SCList[j] - LPList[j])/SCList[j])*100) + "% for a load factor " + load);

                    if (QPList[j] !=-1)
                        System.out.println("Sequential chaining and Quadratic Probing : " + (((SCList[j] - QPList[j])/SCList[j])*100) + "% for a load factor " + load);
                    else
                        System.out.println("QP: Failed at load factor " + load);
                    System.out.println();
                    load = load + 0.25;
                }
            }
        }
    }
    
    private static int getSize(double loadFactor){
        return ((int) (3739/loadFactor)); // 3739 is the number of entries from the lexicon list
    }
}
