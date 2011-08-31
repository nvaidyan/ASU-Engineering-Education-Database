package edu.asu.engineeringed.publications

class Conference {
    static belongsTo = Organization
    String name
    static constraints = {
        name blank:false, unique:true
    }
    static hasMany = [papers:ConferencePaper]
}
