package edu.asu.engineeringed.courses
import edu.asu.engineeringed.publications.Textbook
import edu.asu.engineeringed.Institution
import edu.asu.engineeringed.Professor
import edu.asu.engineeringed.AcademicUnit
import edu.asu.engineeringed.DomainArea

class Course {
    String title
    String url
    String offeringPeriod
    Date offeringYear
    
    static belongsTo = [Institution,AcademicUnit]
    static constraints = {
        title maxSize:100
        url nullable:true, url:true
    }
    
    static hasMany = [textbooks:Textbook,
                      professors:Professor,
                      departments:AcademicUnit,
                      domains:DomainArea]
}
