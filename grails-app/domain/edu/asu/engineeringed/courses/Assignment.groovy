package edu.asu.engineeringed.courses
import edu.asu.engineeringed.AbstractEntity

class Assignment extends AbstractEntity {
    static belongsTo = [ offering : CourseOffering ]
    String description
    Integer pointsPossible
    
    static hasMany = [ 
                        objectives : Objective, 
                        outcomes : Outcome
                     ]
    static constraints = {
        name blank:false
        description nullable:true
        pointsPossible nullable:true
    }
}
