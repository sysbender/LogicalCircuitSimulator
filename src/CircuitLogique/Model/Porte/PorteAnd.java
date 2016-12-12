package CircuitLogique.Model.Porte;

import CircuitLogique.Model.Observer.Entree;
import CircuitLogique.Model.TableVerite;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PorteAnd extends Porte {

    private static final Logger logger = Logger.getLogger(PorteAnd.class.getName());

    public PorteAnd() {
        super();
        // add 2 entree
        creerSortie();
        creerEntree();
        creerEntree();
    }

    @Override
    public List<Boolean> calculer(List<Boolean> entrees) {
        logger.log(Level.INFO, "PorteAnd.calculer : input = {0}",  entrees.toString());
        if (entrees == null || entrees.size() < 2 || entrees.get(0) == null || entrees.get(1) == null) {
            return null;
        } else {
            boolean b = entrees.get(0) & entrees.get(1);
            List<Boolean> listB = new ArrayList<>();
            listB.add(b);
            return listB;
        }
    }

    @Override
    public TableVerite getTableVerite() {
        TableVerite tv = new TableVerite(2, 1);
        // tv.setSorties(new Boolean[][]{{true}, {false}});
        tv.calculerTableVerite(this);
        return tv;
    }

}
