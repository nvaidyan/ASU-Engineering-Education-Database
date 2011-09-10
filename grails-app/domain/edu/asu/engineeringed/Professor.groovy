package edu.asu.engineeringed

class Professor extends AbstractPerson {
    static belongsTo = AcademicUnit
    
    String homepageUrl
    Integer yearStartedTeaching
    String position
    Boolean tenured
    String comments
    DomainArea doctoralThesisDomain
    Institution doctoralAlmaMater
    
    static constraints = {
        name blank:false, unique:true
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
