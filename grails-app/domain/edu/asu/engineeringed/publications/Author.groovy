package edu.asu.engineeringed.publications

class Author {
    String name
    
    static constraints = {
        name blank:false, unique:true
    }
    
    static hasMany = [publications:Publication]
}
