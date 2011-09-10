package edu.asu.engineeringed.research

class Researcher {
    String name
    String email
    
    static hasMany = [investigations:Investigation]
    static constraints = {
        name blank:false, unique:true
        email blank:false, unique:true
    }
    
    @Override
    public String toString() { name }
}
