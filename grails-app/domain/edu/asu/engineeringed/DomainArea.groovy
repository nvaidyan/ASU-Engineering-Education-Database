package edu.asu.engineeringed

class DomainArea {
    String name
    
    static constraints = {
        name blank:false, unique:true
    }
    
    static hasMany = [related:DomainArea, subAreas:DomainArea]
    
    @Override
    public String toString() { name }
}
