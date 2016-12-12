package CircuitLogique.Model.Observer;

import CircuitLogique.Model.Noeud;
import java.util.List;
import java.util.logging.Logger;

    public class Sortie implements Subject {

    private static final Logger logger = Logger.getLogger(Sortie.class.getName());

	Connector connector;

    public Sortie() {
        connector = new Connector();
    }
        
        

    @Override
    public void attach(Observer obs) {
        connector.attach(obs);
    }

    @Override
    public void detach(Observer obs) {
        connector.detach(obs);
    }

    @Override
    public List<Observer> getObservers() {
        return connector.getObservers();
    }

    @Override
    public void notifyObservers() {
        connector.notifyObservers();
    }

    @Override
    public int getDistance() {
        return connector.getDistance();
    }

    @Override
    public Boolean getState() {
        return connector.getState();
    }

    @Override
    public void setState(Boolean s) {
        connector.setState(s);
    }

    @Override
    public void setDistance(int d) {
        connector.setDistance(d);
    }

    @Override
    public Noeud getNoeud() {
        return connector.getNoeud();
    }

    @Override
    public void setNoeud(Noeud noeud) {
        connector.setNoeud(noeud);
    }

    @Override
    public String toString() {
        return "Sortie{" + "connector=" + connector + '}';
    }


    


}
