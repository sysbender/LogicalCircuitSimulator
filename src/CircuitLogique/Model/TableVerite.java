package CircuitLogique.Model;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableVerite {

    private static final Logger logger = Logger.getLogger(TableVerite.class.getName());

    // [lines] [input number] 
    private final Boolean[][] entrees;
    // [lines] [output number]
    private Boolean[][] sorties;

    public TableVerite(int nombreEntrees, int nombreSorties) {

        int numeroLigne = (int) Math.pow(2, nombreEntrees);
        //entrees = new Boolean[numeroLigne][nombreEntrees];
        entrees = genererEntrees(nombreEntrees);
        sorties = new Boolean[numeroLigne][nombreSorties];

    }

    public void setSorties(Boolean[][] sorties) {
        this.sorties = sorties;
    }

    public Boolean[][] getEntrees() {
        return entrees;
    }

    public Boolean[][] getSorties() {
        return sorties;
    }

    /**
     * use noeud.calculer(inputs) to calculate truth table
     *
     * @param noeud
     */
    public void calculerTableVerite(Noeud noeud) {

        for (int i = 0; i < entrees.length; i++) {
            Boolean[] arrayEntrees = entrees[i];
            List<Boolean> listB = noeud.calculer(Arrays.asList(arrayEntrees));
            listB.toArray(sorties[i]);
        }
    }

    /**
     * generate inputs
     *
     * @param n
     * @return
     */
    public static Boolean[][] genererEntrees(int n) {
        int ligne = (int) Math.pow(2, n);
        Boolean[][] tableau = new Boolean[ligne][n];

        String b;
        for (int i = 0; i < ligne; i++) {
            b = String.format("%" + n + "s", Integer.toBinaryString(i)).replace(' ', '0');

            for (int j = 0; j < n; j++) {
                tableau[i][j] = char2boolean(b.charAt(j));
            }
        }
        logger.log(Level.INFO, " -> generated inputs for truth table: {0}", Arrays.deepToString(tableau));

        return tableau;
    }

    public static boolean char2boolean(char c) {
        return (c != '0');
    }

    public void affichierTableVerite() {
        System.out.println("-<<<<<<<<<<<<<<<<<<  commence table de verite---------------------");
        // print heading
        for (int i = 0; i < entrees[0].length; i++) {System.out.print("E"+i+" ");};
        System.out.print(" => ");
        for (int i = 0; i < sorties[0].length; i++) {System.out.print("S"+i+" ");};
         System.out.println("");
        // print 
        
        
        for (int i = 0; i < entrees.length; i++) {
            for (int j = 0; j < entrees[i].length; j++) {
                System.out.print(boolean2Str(entrees[i][j])   );

            }
            System.out.print(" => ");
            for (int j = 0; j < sorties[i].length; j++) {
                System.out.print(boolean2Str(sorties[i][j]) );

            }
            System.out.println(" ");
        }
        System.out.println("->>>>>>>>>>>>>>>>>>fin table de verite---------------------");
    }

    public static String boolean2Str(Boolean b) {
        if (b == null) {
            return "ND ";
        } else {
            return b ? "1  " : "0  ";
        }

    }

}
