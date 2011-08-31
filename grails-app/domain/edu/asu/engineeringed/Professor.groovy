package edu.asu.engineeringed
import edu.asu.engineeringed.publications.Author

class Professor extends Author {
    static belongsTo = AcademicUnit
    
    String email
    String homepageUrl
    Integer yearsTeaching
    String position
    Boolean tenured
    String comments
    DomainArea doctoralThesis
    Institution home
    
    static constraints = {
        email nullable:true, email:true
        yearsTeaching nullable:true
        position nullable:true
        tenured nullable:true
        comments nullable:true, maxSize:65535
    }
    
    static hasMany = [interests:DomainArea, 
                      affiliations:AcademicUnit]
}
