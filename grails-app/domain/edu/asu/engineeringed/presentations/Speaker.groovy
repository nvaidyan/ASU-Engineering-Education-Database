package edu.asu.engineeringed.presentations

class Speaker {
    String name
    String email
    
    static constraints = {
        name blank:false, unique:true
        email blank:false, unique:true, email:true
    }
    
    @Override
    public String toString() { name }
}
