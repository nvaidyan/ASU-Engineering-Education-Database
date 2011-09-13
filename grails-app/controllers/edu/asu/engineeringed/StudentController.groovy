package edu.asu.engineeringed

class StudentController {
    def studentService
    
    def program(){
        def students = studentService.getASUEngineeringEducationStudents()
        [studentInstanceList:students]
    }
}
