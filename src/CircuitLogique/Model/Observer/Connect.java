/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CircuitLogique.Model.Observer;

import CircuitLogique.Model.Noeud;

/**
 *
 * @author jason
 */
public interface Connect {
        int getDistance();

    Boolean getState();

    void setState(Boolean s);

    void setDistance(int d);

    Noeud getNoeud();
    void setNoeud(Noeud noeud);
    
}
