package edu.asu.engineeringed

class DomainArea extends AbstractEntity {
    static constraints = {
        name blank:false, unique:true
    }
    
    static hasMany = [related:DomainArea, subAreas:DomainArea]
}
