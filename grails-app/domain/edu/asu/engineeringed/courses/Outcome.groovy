package edu.asu.engineeringed.courses
import edu.asu.engineeringed.AbstractEntity

class Outcome extends AbstractEntity {
    Boolean measurable
    
    static constraints = {
        name blank:false
        measurable nullable:true
    }
}
