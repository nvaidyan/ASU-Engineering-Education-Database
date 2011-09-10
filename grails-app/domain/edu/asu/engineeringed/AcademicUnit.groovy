package edu.asu.engineeringed
import edu.asu.engineeringed.courses.Course

class AcademicUnit extends AbstractEntity {
    static belongsTo = Institution
    Professor chair
    static constraints = {
        name blank:false
        chair nullable:true
    }
    static hasMany = [subUnits:AcademicUnit, 
                      parentUnits:AcademicUnit,
                      courses:Course,
                      faculty:Professor]
}
