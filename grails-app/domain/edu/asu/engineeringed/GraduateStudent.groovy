package edu.asu.engineeringed

class GraduateStudent extends Student {
    Professor advisor
    
    static hasMany = [
                        degrees:Degree,
                        interests:DomainArea
                     ]
                     
    static constraints = {
        advisor nullable:true
        yearInSchool inList:["Masters", "PhD"]
    }
}
