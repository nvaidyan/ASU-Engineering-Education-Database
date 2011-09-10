package edu.asu.engineeringed

class Institution {
    String name
    String carnegieClassification
    String schedule
    static constraints = {
        name blank:false, unique:true
        carnegieClassification nullable:true
        schedule inList:(["semester","quarter"])
    }
}
