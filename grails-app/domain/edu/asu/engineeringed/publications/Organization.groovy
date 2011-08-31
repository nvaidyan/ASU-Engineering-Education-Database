package edu.asu.engineeringed.publications

class Organization {
    String name
    String url
    Date established
    
    static constraints = {
        name blank:false, unique:true
        url nullable:true, url:true
        established(nullable:true)
    }
    
    static hasMany = [journals:Journal, conferences:Conference]
}
