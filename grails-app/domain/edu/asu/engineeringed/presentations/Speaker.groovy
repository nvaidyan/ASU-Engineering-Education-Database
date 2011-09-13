package edu.asu.engineeringed.presentations
import edu.asu.engineeringed.AbstractPerson

class Speaker extends AbstractPerson {
    String bio
    
    static constraints = {
        name blank:false, unique:true
        email blank:false, unique:true, email:true
        bio nullable:true
    }
    
    static mapping = {
        bio type:'text'
    }
}
