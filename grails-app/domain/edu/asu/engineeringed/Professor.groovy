package edu.asu.engineeringed
import edu.asu.engineeringed.publications.Author

class Professor extends Author {
    static belongsTo = AcademicUnit
    
    String email
    String homepageUrl
    Integer yearStartedTeaching
    String position
    Boolean tenured
    String comments
    DomainArea doctoralThesisDomain
    Institution doctoralAlmaMater
    
    static constraints = {
        email nullable:true, email:true
        homepageUrl nullable:true, url:true
        yearStartedTeaching nullable:true
        position nullable:true
        tenured nullable:true
        comments nullable:true, maxSize:65535
        doctoralThesisDomain nullable:true
        doctoralAlmaMater nullable:true
    }
    
    static hasMany = [interests:DomainArea, 
                      affiliations:AcademicUnit]
}
