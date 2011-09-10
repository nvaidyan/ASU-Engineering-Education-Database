package edu.asu.engineeringed
import edu.asu.engineeringed.courses.Course

class AcademicUnit {
    static belongsTo = Institution
    String name
    Professor chair
    static constraints = {
        name blank:false
        chair nullable:true
    }
    static hasMany = [subUnits:AcademicUnit, 
                      parentUnits:AcademicUnit,
                      courses:Course,
                      faculty:Professor]
    @Override
    public String toString() { name }
}
