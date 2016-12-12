package CircuitLogique.Model.Observer;

import CircuitLogique.Model.Noeud;
import java.util.logging.Logger;


public class Entree implements Observer {
      private static final Logger logger = Logger.getLogger(Entree.class.getName());
	Connector connector;

    public Entree() {
           connector = new Connector();
    }
        
        

    @Override
    public void update() {
        connector.update();
    }

    @Override
    public Subject getSubject() {
        return connector.getSubject();
    }

    @Override
    public void setSubject(Subject subj) {
        connector.setSubject(subj);
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
        return getNoeud();
    }

    @Override
    public void setNoeud(Noeud noeud) {
        connector.setNoeud(noeud);
    }

    @Override
    public String toString() {
        return "Entree{" + "connector=" + connector + '}';
    }
    
    
    
    

}
