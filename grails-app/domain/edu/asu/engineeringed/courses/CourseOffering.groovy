package edu.asu.engineeringed.courses
import edu.asu.engineeringed.Professor
import edu.asu.engineeringed.publications.Textbook

class CourseOffering {
    static belongsTo = [ course: Course ]
    Professor instructor
    Date start
    Date end
    List meetingDays
    Date startMeetingTime
    Date endMeetingTime
    String location
    Integer registeredStudents
    Integer numberOfStudentswhoFinished
    String homePage
    
    
    static hasMany = [
                      textbooks:Textbook,
                      professors:Professor,
                      assignments:Assignment,
                      objectives:Objective,
                      outcomes:Outcome
                     ]
    
    static constraints = {
        instructor()
        start nullable:true, validator: {
            val,obj -> val <= obj.end
        }
        end nullable:true, validator: {
            val,obj -> val >= obj.start
        }
        meetingDays nullable:true
        startMeetingTime nullable:true
        endMeetingTime nullable:true
        location nullable:true
        registeredStudents nullable:true
        numberOfStudentswhoFinished nullable:true
        homePage nullable:true, url:true
    }
}
