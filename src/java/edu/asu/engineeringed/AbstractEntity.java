/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.engineeringed;

/**
 *
 * @author nvaidyan
 */
public class AbstractEntity implements Entity {
    private String name;
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() { return getName(); }
}
