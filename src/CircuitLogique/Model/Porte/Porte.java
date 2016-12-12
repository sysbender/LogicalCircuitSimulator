package CircuitLogique.Model.Porte;

import CircuitLogique.Model.CircuitException;
import CircuitLogique.Model.Noeud;
import CircuitLogique.Model.Observer.Sortie;
import CircuitLogique.Model.Observer.Entree;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Porte implements Noeud {

    private static final Logger logger = Logger.getLogger(Porte.class.getName());
    private static int counter = 0; // count the number of all gates
    private String nom;     //name of gate

    List<Sortie> sorties;   // output of gate
    List<Entree> entrees;   // input of gate

    // constructure 0  sortie, 0 entrees
    public Porte() {
        sorties = new ArrayList<>();
        entrees = new ArrayList<>();
        nom = "porte" + "-" + counter;
    }



    public final void creerEntree() {
        Entree e = new Entree();
        e.setNoeud(this);
        this.entrees.add(e);
    }

    public final void creerSortie() {
        Sortie s = new Sortie();
        s.setNoeud(this);
        this.sorties.add(s);
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        Porte.counter = counter;
    }

    public static void incrementerCounter() {
        ++counter;
    }

    @Override
    public List<Sortie> getSorties() {
        return sorties;
    }

    @Override
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public List<Entree> getEntrees() {
        return entrees;
    }

    public void setEntrees(List<Entree> entrees) {
        this.entrees = entrees;
        for (Entree e : entrees) {
            e.setNoeud(this);
        }

    }

    public void setSorties(List<Sortie> sorties) {
        this.sorties = sorties;
        for (Sortie s : sorties) {
            s.setNoeud(this);
        }

    }

    /**
     * according the current input state, calculate and set the output state
     */
    @Override
    public void calculer() {
        Boolean s = null;
        // state list of all inputs
        List<Boolean> bInput = new ArrayList<>();
        // state list of all output
        List<Boolean> bOutput = new ArrayList<>();

        // populate the input list from input state, throw exception if one input is null
        for (int i = 0; i < getEntrees().size(); i++) {
            s = getEntrees().get(i).getState();
            if (s == null) {
                throw new CircuitException(" Porte.calculer : one input is null, gate name = " + this.getNom());
            } else {
                bInput.add(s);
            }
        }
        //calculate the output
        bOutput = this.calculer(bInput);
        
        logger.log(Level.INFO, "***********************porte.calculer(bInput) , gate name = {0} bInput = {1} bOutput = {2}", new Object[]{this.getNom(), bInput, bOutput});
        // update output state
        if (bOutput != null || bInput.size() != this.getSorties().size()) {
            for (int i = 0; i < this.getSorties().size(); i++) {
                this.getSorties().get(i).setState(bOutput.get(i));
            }
        } else {
            logger.log(Level.SEVERE, " Porte.calculer : result doesn''t match output of gagte, gate name = {0}", this.getNom());

            throw new CircuitException(" Porte.calculer : result doesn't match output of gagte, gate name = " + this.getNom());
        }
    }

    @Override
    public String toString() {
        String r = "";
        r += "Porte{" + "counter=" + counter;
        r += " sorties=" + Arrays.toString(sorties.toArray());;
        r += ", entrees=" + Arrays.toString(entrees.toArray());
        r += '}';
        return r;

    }

}
