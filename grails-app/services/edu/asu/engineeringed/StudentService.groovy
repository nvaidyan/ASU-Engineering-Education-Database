package edu.asu.engineeringed

class StudentService {

    def getASUEngineeringEducationStudents() {
        def asu = Institution.findByName("Arizona State University")
        def engEd = "Engineering Education"
        def unit = AcademicUnit.findByOwnerAndName(asu, engEd )
        unit.students
    }
}
