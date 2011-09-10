package edu.asu.engineeringed.publications
import edu.asu.engineeringed.AbstractEntity

class Author extends AbstractEntity {
    static constraints = {
        name blank:false, unique:true
    }
    
    static hasMany = [publications:Publication]
}
