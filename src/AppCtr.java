
import CircuitLogique.Model.Circuit;
import CircuitLogique.Model.Observer.EntreeCircuit;
import CircuitLogique.Model.Porte.PorteAnd;
import CircuitLogique.Model.Porte.PorteNot;
import CircuitLogique.Model.Porte.PorteOr;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppCtr {

    public static void main(String[] args) {
        Logger.getLogger("").setLevel(Level.SEVERE);
        test20Circuit();
    }

    public static void test20Circuit() {
        //========================create circuit
        Circuit c = new Circuit();
        // ajouter 3e entree
        EntreeCircuit ec3 = new EntreeCircuit("E3");
        c.ajouterEntreeCircuit(ec3);
        
        // ajouter porte : 2 and, 1 or, 1 not
        PorteAnd pa1 = new PorteAnd();
        c.ajouterNoeud(pa1);
        
        PorteOr po1 = new PorteOr();
        c.ajouterNoeud(po1);
        
        PorteNot pn1 = new PorteNot();
        c.ajouterNoeud(pn1);

        PorteAnd pa2 = new PorteAnd();
        c.ajouterNoeud(pa2);
        
        // lier 
        c.lier(c.getEntreeCircuitByName("E1"), pa1, 0);
        c.lier(c.getEntreeCircuitByName("E2"), pa1, 1);

        c.lier(c.getEntreeCircuitByName("E2"), po1, 0);
        c.lier(c.getEntreeCircuitByName("E3"), po1, 1);
        
        
        c.lier(pa1, pn1, 0);
        
        c.lier(pn1, pa2, 0);
        c.lier(po1, pa2, 1);
        
        c.lier(pa2, c.getSortieCircuitByName("S1"));
        //affichier table verite
        c.getTableVerite().affichierTableVerite();

    }

   

}
