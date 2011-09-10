package edu.asu.engineeringed.publications
import edu.asu.engineeringed.AbstractEntity

class Conference extends AbstractEntity {
    static belongsTo = Organization
    static constraints = {
        name blank:false, unique:true
    }
    static hasMany = [papers:ConferencePaper]
}
