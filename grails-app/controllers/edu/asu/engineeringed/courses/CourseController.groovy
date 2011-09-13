package edu.asu.engineeringed.courses

class CourseController {
    static scaffold = true
    def courseService
    
    def program(){
      def courses = courseService.getASUEngineeringEducationCourses()
      [courseInstanceList:courses]
    }
}
