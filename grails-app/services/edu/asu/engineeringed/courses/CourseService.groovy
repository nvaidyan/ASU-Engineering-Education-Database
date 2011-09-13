package edu.asu.engineeringed.courses
import edu.asu.engineeringed.Institution
import edu.asu.engineeringed.AcademicUnit
class CourseService {

    def getASUEngineeringEducationCourses() {
        def asu = Institution.findByName("Arizona State University")
        def engEd = "Engineering Education"
        def unit = AcademicUnit.findByOwnerAndName(asu, engEd )
        unit.courses
    }
}
