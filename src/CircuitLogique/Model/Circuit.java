package CircuitLogique.Model;

import CircuitLogique.Model.Observer.Entree;
import CircuitLogique.Model.Observer.EntreeCircuit;
import CircuitLogique.Model.Observer.Sortie;
import CircuitLogique.Model.Observer.SortieCircuit;
import CircuitLogique.Model.Observer.Subject;
import CircuitLogique.Model.Observer.Observer;
import CircuitLogique.Model.Porte.Porte;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Circuit implements Noeud {

    private static final Logger logger = Logger.getLogger(Circuit.class.getName());

    public final static int MAX_PORTE = 50;
    public final static int MIN_SORTIE = 1;
    public final static int MAX_SORTIE = 5;
    public final static int MIN_ENTREE = 1;
    public final static int MAX_ENTREE = 5;

    private List<Noeud> noeuds;
    private List<SortieCircuit> sorties;
    private List<EntreeCircuit> entrees;
    private String nom;

    //default constructor
    public Circuit() {

        noeuds = new ArrayList<>();
        sorties = new ArrayList<>();
        entrees = new ArrayList<>();
        // add 2 entree, 1 sortee
        entrees.add(new EntreeCircuit());
        entrees.add(new EntreeCircuit());
        sorties.add(new SortieCircuit());
    }

    /**
     *
     * @param name
     * @return
     */
    public EntreeCircuit getEntreeCircuitByName(String name) {
        for (EntreeCircuit ec : entrees) {
            if (ec.getNom() == null ? name == null : ec.getNom().equals(name)) {
                return ec;
            }
        }
        return null;
    }

    /**
     *
     * @param name
     * @return
     */
    public SortieCircuit getSortieCircuitByName(String name) {
        for (SortieCircuit sc : sorties) {
            if (sc.getNom() == null ? name == null : sc.getNom().equals(name)) {
                return sc;
            }
        }
        return null;
    }

    public List<Noeud> getNoeuds() {
        return noeuds;
    }

    public void setNoeuds(List<Noeud> noeuds) {
        this.noeuds = noeuds;
    }

    @Override
    public List<SortieCircuit> getSorties() {
        return sorties;
    }

    public void setSorties(List<SortieCircuit> sorties) {
        this.sorties = sorties;
    }

    @Override
    public List<EntreeCircuit> getEntrees() {
        return entrees;
    }

    public void setEntrees(List<EntreeCircuit> entrees) {
        this.entrees = entrees;
    }

    /**
     *
     * @param n
     */
    public void ajouterNoeud(Noeud n) {
        if (noeuds.size() < MAX_PORTE) {
            noeuds.add(n);
        } else {
            logger.log(Level.SEVERE, "already have  {0} noeuds, can not add another noeud :  {1}", new Object[]{noeuds.size(), n});
            throw new CircuitException("Circuit.ajouterNoeud : too many portes ");
        }
    }

    /**
     *
     * @param n
     */
    public void supprimerNoeud(Noeud n) {
        
        // remove connection for inputs
        List<Observer> obsList = (List<Observer>) n.getEntrees();
        for(Observer obs : obsList){
            delier(obs);
        }
        // remove connection for outputs
        List<Subject> subjList = (List<Subject>) n.getSorties();
        for(Subject subj : subjList){
            for(Observer obs : subj.getObservers()){
                delier(obs);
            }
        }
        // remomve node
        boolean r = noeuds.remove(n);
        if (!r) {
            logger.log(Level.SEVERE, "fail to remove {0} , can not find", new Object[]{n});
        }
    }

    public void lier(Subject subj, Observer obs) {
        if (subj != null && obs != null && obs.getSubject() != null) {
            obs.setSubject(subj);
            subj.attach(obs);

        } else {
            logger.log(Level.SEVERE, "Circuit.lier : Subject or Observer is null");
        }
    }

    public void lier(Porte porte1, Porte porte2, int entreeIndice) {
        if (porte1 != null && porte2 != null && entreeIndice < porte2.getEntrees().size()) {
            porte1.getSorties().get(0).attach(porte2.getEntrees().get(entreeIndice));
        } else {
            throw new CircuitException("  --------- Circuit.lier(porte1,porte2, entreeIndice)  : ");
        }

    }

    public void lier(EntreeCircuit ec, Porte porte, int entreeIndice) {
        if (ec != null && porte != null && entreeIndice < porte.getEntrees().size()) {
            ec.attach(porte.getEntrees().get(entreeIndice));
        } else {
            throw new CircuitException("  --------- Circuit.lier(ec, porte, entreeIndice)  : " + ec + porte + entreeIndice);
        }

    }

    public void lier(Porte porte, SortieCircuit sc) {
        if (porte != null && sc != null) {

            porte.getSorties().get(0).attach(sc);
        } else {
            throw new CircuitException("  --------- Circuit.lier(porte, sc : ");
        }

    }

    /**
     *
     * @param ec
     */
    public void ajouterEntreeCircuit(EntreeCircuit ec) {
        if (entrees.size() < MAX_ENTREE) {
            this.getEntrees().add(ec);
            ec.setNoeud(this);
        } else {
            throw new CircuitException("Circuit.ajouterEntreeCircuit : too many Entrees ");
        }

    }

    /**
     *
     * @param ec
     */
    public void supprimerEntreeCircuit(EntreeCircuit ec) {
        if (entrees.size() > MIN_ENTREE) {
            // clear noeud, ob
            ec.setNoeud(null);
            for (Observer obs : ec.getObservers()) {
                ec.detach(obs);
            }
            if (ec.getSubject() != null) {
                ec.getSubject().detach(ec);
            }

            boolean r = this.getEntrees().remove(ec);
            if (!r) {
                logger.log(Level.SEVERE, "Circuit.supprimerEntreeCircuit : too few Entrees, fail to remove : {} ", new Object[]{ec});
            }
        } else {
            throw new CircuitException("Circuit.supprimerEntreeCircuit : too few Entrees ");
        }
    }

    public void ajouterEntreeSortie(SortieCircuit sc) {
        if (sorties.size() < MAX_SORTIE) {
            this.getSorties().add(sc);
            sc.setNoeud(this);
        } else {
            throw new CircuitException("Circuit.ajouterEntreeSortie : too many Sorties ");
        }

    }

    /**
     *
     * @param ec
     */
    public void supprimerSortieCircuit(SortieCircuit sc) {
        if (sorties.size() > MIN_SORTIE) {
            sc.setNoeud(null);
            for (Observer obs : sc.getObservers()) {
                sc.detach(obs);
            }
            if (sc.getSubject() != null) {
                sc.getSubject().detach(sc);
            }
            boolean r = this.getSorties().remove(sc);
            logger.log(Level.SEVERE, "Circuit.supprimerSortieCircuit , fail, can not find : {}", new Object[]{sc});
        } else {
            throw new CircuitException("Circuit.supprimerSortieCircuit : too few Sorties ");
        }
    }

    /**
     *
     * @param s
     * @param e
     */
    public void relier(Observable s, Observer e) {
        // TODO - implement Circuit.relier
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param o
     */
    public void delier(Observer obs) {
        if (obs.getSubject() != null) {
            obs.getSubject().detach(obs);
            obs.setSubject(null);
        } else {
            logger.log(Level.SEVERE, "Circuit.delier : not connected");
        }

    }

    @Override
    public TableVerite getTableVerite() {
        TableVerite tv = new TableVerite(this.getEntrees().size(), this.getSorties().size());
        tv.calculerTableVerite(this);
        return tv;
    }

    @Override
    public List<Boolean> calculer(List<Boolean> bInput) {
        if (entrees.size() != bInput.size()) {
            throw new CircuitException(" Circuit.calculer : boolean liste entree != entree de circuit ");
        }
        // set state for input
        for (int i = 0; i < entrees.size(); i++) {
            entrees.get(i).setState(bInput.get(i));
        }
        // calculer each noeud 
        calculer();
        //get state from output
        List<Boolean> bOutput = new ArrayList<>();
        for (int j = 0; j < sorties.size(); j++) {
            bOutput.add(sorties.get(j).getState());
        }
        return bOutput;
    }

    @Override
    public void calculer() {
        logger.log(Level.INFO, "Circuit.Calculer @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        // calculate each noeud
        for (int i = 0; i < noeuds.size(); i++) {
            logger.log(Level.INFO, "Circuit.Calculer @ {0}", noeuds.get(i).getNom());
            noeuds.get(i).calculer();
            
//            if (noeuds.get(i) instanceof Porte) {
//                
//            }
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Circuit{" + "noeuds=" + noeuds.size()
                + ", \n sorties=" + Arrays.toString(sorties.toArray())
                + ", \n entrees=" + Arrays.toString(entrees.toArray()) + '}';
    }

}
