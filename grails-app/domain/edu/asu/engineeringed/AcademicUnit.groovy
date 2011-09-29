package edu.asu.engineeringed
import edu.asu.engineeringed.courses.Course

class AcademicUnit extends AbstractEntity {
    static belongsTo = [ owner : Institution ]
    
    static constraints = {
        name blank:false
    }
    SortedSet faculty
    static hasMany = [
                        subUnits:AcademicUnit, 
                        parentUnits:AcademicUnit,
                        courses:UnitCourse,
                        faculty:UnitFaculty,
                        students:Student
                     ]
    public Collection<Course> getAllCoursesForUnit(){
            courses.collect{ it.course }
    }
}
