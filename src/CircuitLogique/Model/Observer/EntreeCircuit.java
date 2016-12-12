package CircuitLogique.Model.Observer;

import java.util.logging.Logger;

public class EntreeCircuit extends Connector {

    private static final Logger logger = Logger.getLogger(EntreeCircuit.class.getName());
    private static int counter;

    public EntreeCircuit() {
        super();
        counter++;
        this.setNom("E" + counter);
    }

    public EntreeCircuit(String nom) {
        super();
        counter++;
        this.setNom(nom);
    }

    // getters and setters
    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        EntreeCircuit.counter = counter;
    }

    @Override
    public String toString() {
        return "EntreeCircuit{" + super.toString() + '}';
    }

}
