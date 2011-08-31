package edu.asu.engineeringed

class Institution {
    String name
    String schedule
    static constraints = {
        name blank:false, unique:true
        schedule inList:(["semester","quarter"])
    }
}
