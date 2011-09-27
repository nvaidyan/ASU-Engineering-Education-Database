package edu.asu.engineeringed.courses
import edu.asu.engineeringed.UnitCourse
import edu.asu.engineeringed.DomainArea

class Course {
    String title
    String description
    String url
    
    static hasMany = [
                      offerings: CourseOffering,
                      departments:UnitCourse,
                      domains:DomainArea,
                      objectives:Objective,
                      outcomes:Outcome
                      
                     ]
    static constraints = {
        title blank:false, maxSize:100
        departments()
        description nullable:true, maxSize:65536
        url nullable:true, url:true
    }
    
    @Override
    public String toString() { title }
}
