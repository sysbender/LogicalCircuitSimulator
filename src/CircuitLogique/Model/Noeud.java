package CircuitLogique.Model;

import CircuitLogique.Model.Observer.Entree;
import CircuitLogique.Model.Observer.Observer;
import CircuitLogique.Model.Observer.Sortie;
import CircuitLogique.Model.Observer.Subject;
import java.util.List;

public interface Noeud {

    TableVerite getTableVerite();

    List<Boolean> calculer(List<Boolean> entrees);

    void calculer();
    
     List<? extends Subject> getSorties();
     List<? extends Observer> getEntrees();
     String getNom();
}
