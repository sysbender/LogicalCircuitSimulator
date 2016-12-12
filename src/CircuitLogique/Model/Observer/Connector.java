package CircuitLogique.Model.Observer;

import CircuitLogique.Model.Noeud;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector implements Observer, Subject {

    private static final Logger logger = Logger.getLogger(Connector.class.getName());
    private Boolean state;
    private int distance;
    private String nom;
    private Noeud noeud;
    private int MAXNOMLONGEUR;
    private Subject subject;
    private List<Observer> observers;

    public Connector() {
        nom = "Connector";
        observers = new ArrayList<>();
    }

    @Override
    public void update() {
        if(subject !=null){
        state = subject.getState();
        distance = subject.getDistance() + 1;
        logger.log(Level.INFO, "Connector.update : setState {0} ", subject.getState());
        }else{
            logger.log(Level.WARNING, "Connector.update : Subject is null ");
        }
    }

    @Override
    public Subject getSubject() {
        return subject;
    }

    @Override
    public void setSubject(Subject subj) {
        subject = subj;
    }

    @Override
    public void attach(Observer obs) {
        if (obs != null) {
            observers.add(obs);
            obs.setSubject(this);
        }
    }

    @Override
    public void detach(Observer obs) {
        if (obs != null) {
            observers.remove(obs);
            obs.setSubject(null);
        }
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            if(o!=null){
            o.update();
            }else{
                logger.log(Level.WARNING, "Connector.notifyObservers : observer is null ");
            }
        }
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public Boolean getState() {
        return state;
    }

    // setter getters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public Noeud getNoeud() {
        return noeud;
    }

    @Override
    public void setNoeud(Noeud noeud) {
        this.noeud = noeud;
    }

    @Override
    public void setState(Boolean state) {
        this.state = state;
        this.notifyObservers();
        logger.log(Level.INFO, "Connector.setState : {0}", state);
    }

    @Override
    public void setDistance(int distance) {
        this.distance = distance;
        this.notifyObservers();
    }

    @Override
    public String toString() {
        return "Connector{" + "state=" + state + ", distance=" + distance + ", nom=" + nom + ", subject=-" + ", observers=" + observers.size() + '}';
    }

}
