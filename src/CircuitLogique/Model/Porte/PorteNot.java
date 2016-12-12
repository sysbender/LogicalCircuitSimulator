package CircuitLogique.Model.Porte;

import CircuitLogique.Model.Observer.Entree;
import CircuitLogique.Model.TableVerite;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PorteNot extends Porte {

    private static final Logger logger = Logger.getLogger(PorteNot.class.getName());

    public PorteNot() {
        super();
        creerSortie();
        creerEntree();
    }

    @Override
    public TableVerite getTableVerite() {
        TableVerite tv = new TableVerite(1, 1);
       // tv.setSorties(new Boolean[][]{{true}, {false}});
       logger.log(Level.INFO, "PorteNot.getTableVerite() - ");
       tv.calculerTableVerite(this);
        return tv;
    }

    @Override
    public List<Boolean> calculer(List<Boolean> entrees) {

        logger.log(Level.INFO," PorteNot.calculer(list) " );
        // get one input
        Boolean e = entrees.get(0);
        if (e == null) {
            throw new IllegalArgumentException(" porteNot.calculer(input) : input is null");
        } else {
            // logic NOT
            boolean b = (e != true);
            List<Boolean> listB = new ArrayList<>();
            listB.add(b);
            return listB;
        }
    }

}
