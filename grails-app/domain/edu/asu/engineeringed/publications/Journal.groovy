package edu.asu.engineeringed.publications

class Journal {
    static belongsTo = Organization
    String name
    Integer volume
    Integer issue
    String url
    
    static constraints = {
        name blank:false, unique:true
        volume min:1
        issue min:1
        url nullable:true, url:true
    }
}
