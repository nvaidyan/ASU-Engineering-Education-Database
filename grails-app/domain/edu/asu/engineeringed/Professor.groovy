package edu.asu.engineeringed

class Professor extends AbstractPerson {
    
    String homepageUrl
    String headshotUrl
    Integer yearStartedTeaching
    String position
    Boolean tenured
    String researchInterests
    String comments
    
    static constraints = {
        name blank:false, unique:true
        email nullable:true, email:true
        homepageUrl nullable:true, url:true
        headshotUrl nullable:true, url:true
        yearStartedTeaching nullable:true
        position nullable:true
        tenured nullable:true
        researchInterests nullable:true
        comments nullable:true, maxSize:65535
    }
    
    static hasMany = [ interests:DomainArea, 
                       affiliations:UnitFaculty,
                       degrees:Degree
                     ]
}
