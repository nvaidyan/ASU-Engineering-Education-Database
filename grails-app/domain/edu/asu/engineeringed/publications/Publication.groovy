package edu.asu.engineeringed.publications
import edu.asu.engineeringed.DomainArea
import edu.asu.engineeringed.Media

abstract class Publication {
    static belongsTo = Author
    static hasMany = [authors:Author, domains:DomainArea]
    
    String title
    String summary
    Date published
    Media content
    
    static constraints = {
        title blank:false, unique:true
        summary maxSize:65535
        published(nullable:true)
    }
    
    
}