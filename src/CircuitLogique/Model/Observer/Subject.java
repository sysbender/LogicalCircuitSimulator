/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CircuitLogique.Model.Observer;

import CircuitLogique.Model.Noeud;
import java.util.List;

/**
 *
 * @author jason
 */
public interface Subject extends Connect {

    void attach(Observer obs);

    void detach(Observer obs);

    List<Observer> getObservers();

    void notifyObservers();
    
    



}
