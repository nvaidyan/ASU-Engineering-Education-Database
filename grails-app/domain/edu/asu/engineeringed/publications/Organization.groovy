package edu.asu.engineeringed.publications
import edu.asu.engineeringed.AbstractEntity

class Organization extends AbstractEntity {
    String url
    Date established
    
    static constraints = {
        name blank:false, unique:true
        url nullable:true, url:true
        established(nullable:true)
    }
    
    static hasMany = [journals:Journal, conferences:Conference]
}
