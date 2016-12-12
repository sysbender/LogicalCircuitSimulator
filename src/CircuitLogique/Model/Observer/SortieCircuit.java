package CircuitLogique.Model.Observer;

import java.util.logging.Logger;

public class SortieCircuit extends Connector {

    private static final Logger logger = Logger.getLogger(SortieCircuit.class.getName());
    private static int counter;

    public SortieCircuit() {
        super();
        counter++;
        setNom("S" + counter);
    }

    public SortieCircuit(String nom) {
        super();
        counter++;
        setNom(nom);
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        SortieCircuit.counter = counter;
    }

    @Override
    public String toString() {
        return "SortieCircuit{" + super.toString() + '}';
    }

}
