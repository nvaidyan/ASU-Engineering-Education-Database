package edu.asu.engineeringed.research
import edu.asu.engineeringed.AbstractPerson

class Researcher extends AbstractPerson {
    static hasMany = [investigations:Investigation]
    static constraints = {
        name blank:false, unique:true
        email blank:false, unique:true
    }
}
