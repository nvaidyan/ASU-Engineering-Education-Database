package edu.asu.engineeringed.research

class Investigator {
    String name
    String email
    
    static hasMany = [projects:Project]
    static constraints = {
        name blank:false, unique:true
        email blank:false, unique:true
    }
    
    @Override
    public String toString() { name }
}
