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
    
    def getSoftwareEngineeringCourses(){
        def swEng = DomainArea.findByName("Software Engineering")
        def courses = Course.findAllByDomains(swEng)
    }
}
