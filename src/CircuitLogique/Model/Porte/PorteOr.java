package CircuitLogique.Model.Porte;

import CircuitLogique.Model.Observer.Entree;
import CircuitLogique.Model.TableVerite;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PorteOr extends Porte {

    private static final Logger logger = Logger.getLogger(PorteOr.class.getName());

    public PorteOr() {
        super();
        creerSortie();
        creerEntree();
        creerEntree();

    }

    @Override
    public TableVerite getTableVerite() {
        TableVerite tv = new TableVerite(2, 1);
        // tv.setSorties(new Boolean[][]{{true}, {false}});
        tv.calculerTableVerite(this);
        return tv;
    }

    @Override
    public List<Boolean> calculer(List<Boolean> entrees) {
        if (entrees.get(0) == null || entrees.get(1) == null) {
            return null;
        } else {

            boolean b = entrees.get(0) | entrees.get(1);
            List<Boolean> listB = new ArrayList<>();
            listB.add(b);
            return listB;
        }
    }
}
