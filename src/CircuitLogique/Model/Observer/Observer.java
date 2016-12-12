/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CircuitLogique.Model.Observer;

/**
 *
 * @author jason
 */
public interface Observer extends Connect{
    void update();
    Subject getSubject();
    void setSubject(Subject subj);
    

    
}
