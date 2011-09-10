package edu.asu.engineeringed
import edu.asu.engineeringed.publications.Author

class Professor extends Author {
    static belongsTo = AcademicUnit
    
    String email
    String homepageUrl
    Integer startedTeachingYear
    String position
    Boolean tenured
    String comments
    DomainArea doctoralThesis
    Institution almaMater
    
    static constraints = {
        email nullable:true, email:true
        homepageUrl nullable:true, url:true
        startedTeachingYear nullable:true
        position nullable:true
        tenured nullable:true
        comments nullable:true, maxSize:65535
        doctoralThesis nullable:true
        almaMater nullable:true
    }
    
    static hasMany = [interests:DomainArea, 
                      affiliations:AcademicUnit]
}
