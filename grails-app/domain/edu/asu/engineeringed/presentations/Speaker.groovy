package edu.asu.engineeringed.presentations
import edu.asu.engineeringed.AbstractPerson

class Speaker extends AbstractPerson {
    static constraints = {
        name blank:false, unique:true
        email blank:false, unique:true, email:true
    }
}
